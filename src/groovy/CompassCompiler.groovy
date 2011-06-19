import org.jruby.Main

class CompassCompiler {
    public void compile(config, callback) {
        def sass_dir = config.grass?.sass_dir
        def css_dir = config.grass?.css_dir
        def images_dir = config.grass?.images_dir
        def relative_assets = config.grass?.relative_assets == null ? true : config.compass?.relative_assets
        def output_style = config.grass?.output_style ?: 'compact'

        ensureParameterSet sass_dir, "sass_dir is not set (GrassConfig.groovy)", callback
        ensureParameterSet css_dir, "css_dir is not set (GrassConfig.groovy)", callback
        ensureParameterSet images_dir, "images_dir is not set (GrassConfig.groovy)", callback
        ensureParameterSet output_style, "output_style is not set (GrassConfig.groovy)", callback

        println """
            sass_dir = '${sass_dir}'
            css_dir = '${css_dir}'
            images_dir = '${images_dir}'
            relative_assets = ${relative_assets}
            output_style = ${output_style}
        """

        println "Compiling sass stylesheets..."

        def relative_assets_arg = relative_assets ? "--relative-assets" : ""
        String[] sassCompileCommandLineArgs = ['-S', 'compass', 'compile', '--sass-dir', "${sass_dir}", '--css-dir',
                "${css_dir}", '--images-dir', "${images_dir}", "${relative_assets_arg}", '--output-style',
                "${output_style}"]
        println "jruby ${sassCompileCommandLineArgs.join(' ')}"

        runJRubyWithArgs(sassCompileCommandLineArgs)
    }

    protected def runJRubyWithArgs(String[] sassCompileCommandLineArgs) {
        Main jrubyRunner = new Main()
        jrubyRunner.main(sassCompileCommandLineArgs)
    }

    protected void ensureParameterSet(parameter, message, callback) {
        if (!parameter)
            callback(message)
    }
}
