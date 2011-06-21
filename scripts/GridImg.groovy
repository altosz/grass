includeTargets << grailsScript("Init")

target(main: "The description of the script goes here!") {
	gridImg()
}

target(gridImg: "Generate grid.png") {
	if (args) {
		dimensions = args.trim()
	} else {
		Ant.input(addProperty:"compass.grid.dimensions", message:"Specify grid dimensions as <column-width>+<gutter-width>, e.g. 30+10:")
        dimensions = Ant.antProject.properties."compass.grid.dimensions"
	}
	
	GroovyClassLoader loader = new GroovyClassLoader(getClass().getClassLoader())
	
	Class compassInvokerClass = loader.parseClass(
		new File("$grassPluginDir/src/groovy/com/stefankendall/CompassInvoker.groovy"))
    def compassInvoker = compassInvokerClass.newInstance(new File("$basedir/grails-app/conf/GrassConfig.groovy"))
		
	compassInvoker.makeGridImage(dimensions)
}

setDefaultTarget(main)
