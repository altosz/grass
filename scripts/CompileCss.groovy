includeTargets << grailsScript("Init")
includeTargets << grailsScript("_GrailsSettings")

target(main: "The description of the script goes here!") {
	compileCss()
}

target(compileCss: "Compile sass stylesheets") {
	GroovyClassLoader loader = new GroovyClassLoader(getClass().getClassLoader())
	Class clazz = loader.parseClass(new File("$basedir/grails-app/conf/CompassConfig.groovy"))
	def config = new ConfigSlurper().parse(clazz)

	def sass_dir = config.compass?.sass_dir
	def css_dir = config.compass?.css_dir
	def images_dir = config.compass?.images_dir
	def relative_assets = config.compass?.relative_assets ?: true
	
	check sass_dir, "sass_dir is not set (CompassConfig.groovy)"
	check css_dir, "css_dir is not set (CompassConfig.groovy)"
	check images_dir, "images_dir is not set (CompassConfig.groovy)"
	
	println """
sass_dir = '${sass_dir}'"
css_dir = '${css_dir}'
images_dir = '${images_dir}'
relative_assets = ${relative_assets}
"""
	
	println "Compiling sass stylesheets..."
	
	ant.exec(executable: "compass") {
		def relative_assets_arg = relative_assets ? "--relative-assets" : ""
		arg(line: "--sass-dir ${sass_dir} --css-dir ${css_dir} --images-dir ${images_dir} ${relative_assets_arg}")
	}
}

def check(value, msg) {
	if (!value) {
		event("StatusError", [msg])		
		exit(-1)
	}
}

setDefaultTarget(main)
