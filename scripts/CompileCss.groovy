includeTargets << grailsScript("Init")
includeTargets << grailsScript("_GrailsSettings")

target(main: "The description of the script goes here!") {
	compileCss()
}

target(compileCss: "Compile sass stylesheets") {
	GroovyClassLoader loader = new GroovyClassLoader(getClass().getClassLoader())
	Class clazz = loader.parseClass(new File("$basedir/grails-app/conf/CompassConfig.groovy"))
	def config = new ConfigSlurper().parse(clazz)
	println config

}

setDefaultTarget(main)
