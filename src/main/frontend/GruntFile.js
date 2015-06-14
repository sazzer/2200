module.exports = function(grunt) {
    require('time-grunt')(grunt);
    require('jit-grunt')(grunt, {
        bower: 'grunt-bower-task'
    });
    require('./grunt-messages-task')(grunt);

    var webpack = require('webpack');
    var webpackConfig = require('./webpack.config.js');
    grunt.initConfig({
        messages: {
            options: {
                inputs: '${project.basedir}/src/main/languages',
                output: '${project.build.outputDirectory}/resources/messages'
            }
        },
        bower: {
            build: {
                options: {
                    targetDir: '${project.build.outputDirectory}/resources/external',
                    install: true,
                    cleanup: false,
                    copy: true,
                    layout: 'byType',
                    verbose: true
                }
            }
        },
        sass: {
            build: {
                options: {
                    sourceComments: true,
                    sourceMap: true,
                    sourceMapEmbed: true
                },
                files: {
                    '${project.build.outputDirectory}/resources/css/main.bundle.css': '${project.basedir}/src/main/scss/main.scss'
                }
            }
        },
        webpack: {
            options: webpackConfig,
            build: {
                plugins: webpackConfig.plugins.concat(new webpack.DefinePlugin({
                    'process.env': {
                        NODE_ENV: JSON.stringify('production')
                    }
                }), new webpack.optimize.DedupePlugin())
            }
        }
    });
    grunt.registerTask('default', [ 'bower', 'messages', 'sass', 'webpack' ]);
};
