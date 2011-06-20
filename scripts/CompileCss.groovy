includeTargets << grailsScript("Init")
includeTargets << grailsScript("_GrailsSettings")

target(main: "The description of the script goes here!") {
	compileCss()
}

target(compileCss: "Compile sass stylesheets") {
	GroovyClassLoader loader = new GroovyClassLoader(getClass().getClassLoader())
	def config = new ConfigSlurper().parse(loader.parseClass(new File("$basedir/grails-app/conf/GrassConfig.groovy")))
	
	Class compassCompileClass = loader.parseClass(
		new File("$grassPluginDir/src/groovy/com/stefankendall/CompassInvoker.groovy"))

    def compass = compassCompileClass.newInstance()
	compass.compile(config) { msg ->
		event("StatusError", [msg])
		exit(-1)
	}
}

setDefaultTarget(main)
