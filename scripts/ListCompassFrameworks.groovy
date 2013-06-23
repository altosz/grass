includeTargets << grailsScript("Init")
includeTargets << new File("${grassPluginDir}/scripts/_CompassFrameworks.groovy")

target(listCompassFrameworks: 'Show the list of available compass frameworks') {
    println "\nAvailable Compass frameworks:"
    availableCompassFrameworks.each { name, frameworkInfo ->
        println "    $name : ${frameworkInfo.description}"
    }
    println "\nUse 'grails compass-init [framework-name]' to initialize compass framework."	
}

setDefaultTarget(listCompassFrameworks)
