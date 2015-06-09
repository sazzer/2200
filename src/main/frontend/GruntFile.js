module.exports = function(grunt) {
    require("time-grunt")(grunt);
    require("jit-grunt")(grunt, {
        bower: "grunt-bower-task"
    });
    var webpack = require("webpack");
    var webpackConfig = require("./webpack.config.js");
    grunt.initConfig({
        bower: {
            build: {
                options: {
                    targetDir: "${project.build.outputDirectory}/external",
                    install: true,
                    cleanup: false,
                    copy: true,
                    layout: "byType",
                    verbose: true
                }
            }
        },
        webpack: {
            options: webpackConfig,
            build: {
                plugins: webpackConfig.plugins.concat(new webpack.DefinePlugin({
                    "process.env": {
                        NODE_ENV: JSON.stringify("production")
                    }
                }), new webpack.optimize.DedupePlugin())
            }
        }
    });
    grunt.registerTask("default", [ "bower", "webpack" ]);
};