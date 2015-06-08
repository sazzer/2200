var webpack = require('webpack');

module.exports = {
    cache: true,
    entry: '${project.basedir}/src/main/javascript/main.js',
    output: {
        path: '${project.build.outputDirectory}/javascript',
        filename: '[name].bundle.js'
    },
    module: {
        loaders: [
        ]
    },
    resolve: {
        root: [
            '${project.build.directory}/frontend/bower_components'
        ],
        extensions: [
            '', 
            '.js',
            '.jsx',
            '.json'
        ],
        alias: {
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
        }),
        new webpack.ResolverPlugin(
            new webpack.ResolverPlugin.DirectoryDescriptionFilePlugin('bower.json', ['main'])
        ),
        new webpack.DefinePlugin({
            VERSION: JSON.stringify('${project.version}')
        })
    ]
};

