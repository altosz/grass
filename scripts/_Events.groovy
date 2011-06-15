eventConfigureTomcat = {
    startCompassSassWatcher(getGrassConfig())
}

private ConfigObject getGrassConfig() {
    GroovyClassLoader classLoader = new GroovyClassLoader(getClass().getClassLoader())
    ConfigObject config = new ConfigSlurper().parse(classLoader.loadClass('GrassConfig'))
    return config.grass
}

private def startCompassSassWatcher(def grassConfig) {
    Thread.start {
        def watchCommand = ['jruby', '-S', 'compass', 'watch', '--sass-dir', grassConfig.sass_dir,
                '--css-dir', grassConfig.css_dir, '--images-dir', grassConfig.images_dir,
                '--output-style', grassConfig.output_style]

        println "Starting compass sass watcher: ${watchCommand.join(' ')}"

        Process executingProcess = watchCommand.execute()

        Runtime.runtime.addShutdownHook {
            println 'Stopping compass sass watcher.'
            executingProcess.destroy()
        }

        GroovyClassLoader loader = new GroovyClassLoader(getClass().getClassLoader())
        Class streamFilePrinter = loader.parseClass(new File("$grassPluginDir/src/groovy/StreamFilePrinter.groovy"))

        def outputFile = new File("compass.out")
        def errorStreamPrinter = streamFilePrinter.newInstance(executingProcess.err, outputFile)
        def outputStreamPrinter = streamFilePrinter.newInstance(executingProcess.in, outputFile)
        [errorStreamPrinter, outputStreamPrinter]*.start()
    }
}
