'use strict';

var path = require('path');
var fs = require('fs');
var Q = require('q');
var flatmap = require('flatmap');
var merge = require('merge');
var mkdirp = require('mkdirp');
var traverse = require('traverse');

module.exports = function(grunt) {
    grunt.registerTask('messages', function() {
        var options = this.options();
        grunt.log.writeln('Reading message inputs from ' + options.inputs);
        grunt.log.writeln('Writing combined files to ' + options.output);
        grunt.log.writeln('Writing locale lists to ' + options.output + '/locales.json');

        var defaultMessages = path.join(options.inputs, 'messages.json');

        var done = this.async();

        grunt.log.writeln('Processing messages in: ' + options.inputs);
        Q.nfcall(fs.readdir, options.inputs).then(function(files) {
            // Get all of the files in the top-level messages folder
            return Q.all(files.map(function(file) {
                var fullPath = path.join(options.inputs, file);
                return Q.nfcall(fs.stat, fullPath).then(function(stat) {
                    return {
                        language: file,
                        country: undefined,
                        fullPath: fullPath,
                        stat: stat
                    };
                });
            }));
        }).then(function(files) {
            // Filter out the ones that are not directories
            return files.filter(function(file) {
                return file.stat.isDirectory();
            }).map(function(file) {
                delete file.stat;
                return file;
            });
        }).then(function(languageFiles) {
            // This monstrosity will then get all of the sub-directories o fthe language directories
            return Q.all(languageFiles.map(function(languageFile) {
                return Q.nfcall(fs.readdir, languageFile.fullPath).then(function(countryFiles) {
                    return Q.all(countryFiles.map(function(countryFile) {
                        var fullPath = path.join(languageFile.fullPath, countryFile);
                        return Q.nfcall(fs.stat, fullPath).then(function(stat) {
                            return {
                                language: languageFile.language,
                                country: countryFile,
                                fullPath: fullPath,
                                stat: stat,
                            };
                        });
                    })).then(function(countryFiles) {
                        return {
                            language: languageFile.language,
                            country: undefined,
                            fullPath: languageFile.fullPath,
                            stat: languageFile.stat,
                            countryFiles: countryFiles.filter(function(file) {
                                return file.stat.isDirectory();
                            }).map(function(file) {
                                delete file.stat;
                                return file;
                            })
                        };
                    });
                });
            }));
        }).then(function(files) {
            // Flatten it all out so we have an array of all of our messages directories
            return flatmap(files, function(file) {
                var countryFiles = file.countryFiles.slice();
                countryFiles.push({
                    language: file.language,
                    fullPath: file.fullPath
                });
                return countryFiles;
            });
        }).then(function(files) {
            return Q.all(files.map(function(file) {
                file.messagesPath = path.join(file.fullPath, 'messages.json');
                return file;
            }).map(function(file) {;
                var deferred = Q.defer();
                fs.exists(file.messagesPath, function(exists) {
                    deferred.resolve(exists);
                });

                return deferred.promise.then(function(exists) {
                    file.messagesExist = exists;
                    return file;
                });
            }));
        }).then(function(files) {
            return files.filter(function(file) {
                return file.messagesExist;
            }).map(function(file) {
                delete file.messagesExist;
                return file;
            }).map(function(file) {
                file.contents = require(file.messagesPath);
                return file;
            });
        }).then(function(files) {
            var languages = {};

            var defaultContents = require(defaultMessages);
            languages[''] = defaultContents;

            files.filter(function(f) {
                return f.country == undefined;
            }).forEach(function(f) {
                grunt.log.writeln('Storing language list for ' + f.language);
                languages[f.language] = merge.recursive(true, defaultContents, f.contents);
            });

            files.filter(function(f) {
                return f.country != undefined;
            }).forEach(function(f) {
                var languageList = languages[f.language];
                languages[f.language + "_" + f.country] = merge.recursive(true, languageList, f.contents);
            });

            return languages;
        }).then(function(languages) {
            return Q.nfcall(mkdirp.mkdirp, options.output).then(function() {
                try {
                    Object.keys(languages).forEach(function(language) {
                        var contents = languages[language];

                        var traversed = traverse(contents);
                        var contentsProperties = traversed.paths().filter(function(path) {
                            return typeof traversed.get(path) === 'string';
                        }).map(function(path) {
                            return path.join(".") + "=" + traversed.get(path);
                        });
                        contentsProperties.push(' ');

                        var filenameJson;
                        var filenameProperties;
                        if (language == "") {
                            filenameJson = "strings.json";
                            filenameProperties = "messages.properties";
                        } else {
                            filenameJson = "strings_" + language + ".json";
                            filenameProperties = "messages_" + language + ".properties";
                        }
                        var outputFilenameJson = path.join(options.output, filenameJson);
                        var outputFilenameProperties = path.join(options.output, filenameProperties);

                        grunt.log.writeln("Writing language " + language + " to file " + outputFilenameJson);
                        fs.writeFileSync(outputFilenameJson, JSON.stringify(contents), {
                            encoding: "utf8",
                            flag: "w"
                        });
                        fs.writeFileSync(outputFilenameProperties, contentsProperties.join('\n'), {
                            encoding: "utf8",
                            flag: "w"
                        });
                    });

                    var localesFilename = path.join(options.output, "locales.json");
                    fs.writeFileSync(localesFilename, JSON.stringify(Object.keys(languages)), {
                        encoding: "utf8",
                        flag: "w"
                    });
                } catch (e) {
                    console.dir(e.stack);
                    throw e;
                }
            });
        }).then(function(languages) {
            done();
        }, function(err) {
            grunt.log.writeln('Error processing files: ' + err);
            done();
        });
    });
}
