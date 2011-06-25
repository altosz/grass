package com.stefankendall

class CompassInvoker {
    def config

    public CompassInvoker(File grassConfigLocation) {
        config = new ConfigSlurper().parse(grassConfigLocation.toURL())
    }

    public CompassInvoker(Map config) {
        this.config = config
    }

    public void compile(callback) {
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

    public void watch() {
        runCompassCommandInThread(['watch', '--sass-dir', config.grass.sass_dir,
                '--css-dir', config.grass.css_dir, '--images-dir', config.grass.images_dir,
                '--output-style', config.grass.output_style])
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
            Process process
            try {
                process = command.execute()
            }
            catch (IOException e) {
                System.err.println("JRuby could not be started. Make sure 'jruby' exists on the PATH and try again.")
                System.err.println("No SCSS/SASS compilation will be performed.")
                return
            }

            Runtime.runtime.addShutdownHook {
                println "Attempting to kill compass poller. You may need to kill javaw.exe."
                process.destroy()
            }

            process.consumeProcessOutput(System.out, System.err)
            process.waitFor()
        }
    }

    protected void ensureParameterSet(parameter, message, callback) {
        if (!parameter) {
            callback(message)
        }
    }
}