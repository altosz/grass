eventConfigureTomcat = {
    GroovyClassLoader loader = new GroovyClassLoader(getClass().getClassLoader())
    def config = new ConfigSlurper().parse(loader.parseClass(new File("$basedir/grails-app/conf/GrassConfig.groovy")))

    Class compassCompileClass = loader.parseClass(
            new File("$grassPluginDir/src/groovy/com/stefankendall/CompassInvoker.groovy"))

    def compass = compassCompileClass.newInstance()
    compass.watch( config )
}

