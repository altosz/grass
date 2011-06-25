includeTargets << grailsScript("Init")
includeTargets << grailsScript("_GrailsSettings")

target(compileCss: "Compile sass stylesheets") {
	GroovyClassLoader loader = new GroovyClassLoader(getClass().getClassLoader())
	Class compassCompileClass = loader.parseClass(
		new File("$grassPluginDir/src/groovy/com/stefankendall/CompassInvoker.groovy"))

    def compass = compassCompileClass.newInstance(new File("$basedir/grails-app/conf/GrassConfig.groovy"))
	compass.compile() { msg ->
		event("StatusError", [msg])
		exit(-1)
	}
}

setDefaultTarget(compileCss)
