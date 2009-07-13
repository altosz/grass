includeTargets << grailsScript("Init")
includeTargets << new File("${compassCssPluginDir}/scripts/_CompassFrameworks.groovy")

target(main: "The description of the script goes here!") {
	listCompassFrameworks()
}

target(listCompassFrameworks: 'Show the list of available compass frameworks') {
    println "\nAvailable Compass frameworks:"
    availableCompassFrameworks.each { name, frameworkInfo ->
        println "    $name : ${frameworkInfo.description}"
    }
    println "\nUse 'grails compass-init [framework-name]' to initialize compass framework."	
}

setDefaultTarget(main)
