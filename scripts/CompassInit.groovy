includeTargets << grailsScript("Init")
includeTargets << new File("${compassCssPluginDir}/scripts/_CompassFrameworks.groovy")

target(main: "The description of the script goes here!") {
    initCompass()
}

target(initCompass: 'Initialize compass framework') {
	if (args) {
		frameworkName = args.trim()
	} else {
		Ant.input(addProperty:"compass.init.framework.name", message:"Enter the framework name:")
        frameworkName = Ant.antProject.properties."compass.init.framework.name"
	}
	initCompassFramework()
}

target(initCompassFramework: 'Initialize compass framework') {
	def framework = frameworkName ? availableCompassFrameworks."$frameworkName" : null
	
	if (framework) {

		println "\nCoping sass stylesheets to ./src/stylesheets"
		Ant.copy(todir: "${basedir}/src/stylesheets", overwrite: true) {
		    fileset(dir: "${compassCssPluginDir}/src/stylesheets/${framework.dir}")
		}
		
		// TODO manage overwriting
		println "\nCoping CompassConfig (overwriting if exists)"
		Ant.copy(
			todir: "${basedir}/grails-app/conf", overwrite: true, 
			file: "${compassCssPluginDir}/grails-app/conf/CompassConfig.groovy") 

		println """
Congratulations! Compass sass files have been installed.
Sass stylesheets are recompiled automatically when running 'grails run-app'.
To compile sass stylesheets manually use 'grails compile-css'.

To import your new stylesheets add the following lines of HTML (or equivalent) to your gsp:
${framework.import}
"""

	} else {
		event("StatusError", ["Cannot find specified compass framework\nUse 'grails list-compass-frameworks' to get the list of available frameworks"])
	}
}

setDefaultTarget(main)
