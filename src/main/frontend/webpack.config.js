var webpack = require("webpack");

module.exports = {
    cache: true,
    entry: "${project.basedir}/src/main/javascript/main.js",
    devtool: "source-map",
    output: {
        path: "${project.build.outputDirectory}/resources/javascript",
        filename: "[name].bundle.js"
    },
    module: {
        loaders: [ {
            test: /\.js$/,
            loaders: [ "${project.build.directory}/frontend/node_modules/babel-loader" ]
        }, {
            test: /\.jsx$/,
            loaders: [
                "${project.build.directory}/frontend/node_modules/jsx-loader",
                "${project.build.directory}/frontend/node_modules/babel-loader"
            ]
        } ]
    },
    resolve: {
        root: [
            "${project.basedir}/src/main/javascript",
            "${project.build.directory}/frontend/bower_components",
        ],
        extensions: [ "", ".js", ".jsx", ".json" ],
        alias: {
            'dirt-locales': '${project.build.outputDirectory}/resources/messages/locales.json',
            'reduce-component': '${project.basedir}/src/main/frontend/external/reduce.js',
            'react': '${project.build.directory}/frontend/node_modules/react/react.js',
            'react-intl': '${project.build.directory}/frontend/node_modules/react-intl/index.js'
        }
    },
    stats: {
        colors: true,
        modules: true,
        errors: true
    },
    plugins: [ 
        new webpack.dependencies.LabeledModulesPlugin(), 
        new webpack.ProvidePlugin({
            'jQuery': 'jquery',
            'React': 'react'
        }),
        new webpack.ResolverPlugin(new webpack.ResolverPlugin.DirectoryDescriptionFilePlugin("bower.json", [ "main" ])), 
        new webpack.DefinePlugin({ VERSION: JSON.stringify("${project.version}") }) 
    ]
};
