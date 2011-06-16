eventConfigureTomcat = {
    startCompassSassWatcher(getGrassConfig())
}

private ConfigObject getGrassConfig() {
    GroovyClassLoader classLoader = new GroovyClassLoader(getClass().getClassLoader())
    ConfigObject config = new ConfigSlurper().parse(classLoader.loadClass('GrassConfig'))
    return config.grass
}

private def startCompassSassWatcher(def grassConfig) {
    def watchCommand = ['jruby', '-S', 'compass', 'watch', '--sass-dir', grassConfig.sass_dir,
            '--css-dir', grassConfig.css_dir, '--images-dir', grassConfig.images_dir,
            '--output-style', grassConfig.output_style]

    println "Starting compass sass watcher: ${watchCommand.join(' ')}"

    Process executingProcess = watchCommand.execute()

    GroovyClassLoader loader = new GroovyClassLoader(getClass().getClassLoader())
    Class streamFilePrinter = loader.parseClass(new File("$grassPluginDir/src/groovy/StreamFilePrinter.groovy"))

    def outputFile = new File("compass.out")
    Thread errorStreamPrinter = streamFilePrinter.newInstance(executingProcess.err, outputFile)
    Thread outputStreamPrinter = streamFilePrinter.newInstance(executingProcess.in, outputFile)
    [errorStreamPrinter, outputStreamPrinter]*.start()

    Runtime.runtime.addShutdownHook {
        println 'Stopping compass sass watcher.'
        executingProcess?.destroy()
        outputStreamPrinter?.stop()
        errorStreamPrinter?.stop()
    }
}
