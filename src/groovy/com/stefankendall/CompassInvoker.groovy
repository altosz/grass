package com.stefankendall

class CompassInvoker {
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

        def sassCompileCommandLineArgs = ['compile',
                '--sass-dir', "${sass_dir}",
                '--css-dir', "${css_dir}",
                '--images-dir', "${images_dir}",
                relative_assets ? "--relative-assets" : "",
                '--output-style', "${output_style}"]

        runCompassCommand(sassCompileCommandLineArgs)
    }

    public void watch(config) {
        runCompassCommandInThread(['watch', '--sass-dir', config.grass.sass_dir,
                '--css-dir', config.grass.css_dir, '--images-dir', config.grass.images_dir,
                '--output-style', config.grass.output_style])
    }

    public void makeGridImage(config, dimensions) {
        def images_dir = config.grass?.images_dir
        runCompassCommand(["--grid-img", "${dimensions}", "--images-dir", "${images_dir}", "--force"])
    }


    protected def runCompassCommand(def compassArgs) {
        String[] command = ['jruby', '-S', 'compass', compassArgs].flatten()
        println "Executing: ${command.join(' ')}"

        Process p = command.execute()
        p.consumeProcessOutput(System.out, System.err)
        p.waitFor()
    }

    protected def runCompassCommandInThread(def compassArgs) {
        String[] command = ['jruby', '-S', 'compass', compassArgs].flatten()
        println "Executing: ${command.join(' ')}"

        Thread.start {
            Process p = command.execute()

            Runtime.runtime.addShutdownHook {
                println "Attempting to kill compass poller. You may need to kill javaw.exe."
                p.destroy()
            }

            p.consumeProcessOutput(System.out, System.err)
            p.waitFor()
        }
    }

    protected void ensureParameterSet(parameter, message, callback) {
        if (!parameter) {
            callback(message)
        }
    }
}

