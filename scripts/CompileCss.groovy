includeTargets << grailsScript("Init")
includeTargets << grailsScript("_GrailsSettings")

target(main: "The description of the script goes here!") {
	compileCss()
}

target(compileCss: "Compile sass stylesheets") {
	GroovyClassLoader loader = new GroovyClassLoader(getClass().getClassLoader())
	
	Class configClazz = loader.parseClass(new File("$basedir/grails-app/conf/GrassConfig.groovy"))
	def config = new ConfigSlurper().parse(configClazz)
	
	Class compassCompileClass = loader.parseClass(
		new File("$grassPluginDir/src/groovy/CompassCompiler.groovy"))

    def compiler = compassCompileClass.newInstance()
	compiler.compile(config) { msg ->
		event("StatusError", [msg])
		exit(-1)
	}
}

setDefaultTarget(main)
