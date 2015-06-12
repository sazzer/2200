var webpack = require("webpack");

module.exports = {
    cache: true,
    entry: "${project.basedir}/src/main/javascript/main.js",
    output: {
        path: "${project.build.outputDirectory}/resources/javascript",
        filename: "[name].bundle.js"
    },
    module: {
        loaders: [ {
            test: /\.js$/,
            loaders: [ "${project.build.directory}/frontend/node_modules/babel-loader" ]
        } ]
    },
    resolve: {
        root: [ "${project.build.directory}/frontend/bower_components" ],
        extensions: [ "", ".js", ".jsx", ".json" ],
        alias: {}
    },
    stats: {
        colors: true,
        modules: true,
        errors: true
    },
    plugins: [ 
        new webpack.dependencies.LabeledModulesPlugin(), 
        new webpack.ProvidePlugin({
            'jQuery': 'jquery'
        }),
        new webpack.ResolverPlugin(new webpack.ResolverPlugin.DirectoryDescriptionFilePlugin("bower.json", [ "main" ])), 
        new webpack.DefinePlugin({ VERSION: JSON.stringify("${project.version}") }) 
    ]
};
