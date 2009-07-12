includeTargets << grailsScript("Init")
includeTargets << new File("${pluginBasedir}/scripts/_CompassFrameworks.groovy")

target(main: "The description of the script goes here!") {
	listCompassFrameworks()
}

target(listCompassFrameworks: 'Show the list of available compass frameworks') {
    println "\nAvailable Compass frameworks:"
    availableCompassFrameworks.each { name, frameworkInfo ->
        println "    $name : ${frameworkInfo.description}"
    }
    println "\nUse 'grails compass-init [frameworkName]' to initialize compass framework."	
}

setDefaultTarget(main)
