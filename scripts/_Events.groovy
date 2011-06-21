eventConfigureTomcat = {
    GroovyClassLoader loader = new GroovyClassLoader(getClass().getClassLoader())

    Class compassCompileClass = loader.parseClass(
            new File("$grassPluginDir/src/groovy/com/stefankendall/CompassInvoker.groovy"))

    def compass = compassCompileClass.newInstance(new File("$basedir/grails-app/conf/GrassConfig.groovy"))
    compass.watch()
}

