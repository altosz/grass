class GrassGrailsPlugin {
    def version = "0.4"
    def grailsVersion = "1.3.7 > *"
    def dependsOn = [:]
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def author = "Alexei Tishin"
    def authorEmail = "altos.z@gmail.com"
    def title = "Compass stylesheet authoring tool plugin"
    def description = '''\\
Compass is a stylesheet authoring tool that uses the Sass stylesheet language to make your stylesheets smaller and your web site easier to maintain. This plugin brings compass advantages to grails community.
	'''

    def documentation = "http://grails.org/Grass+Plugin"

    def doWithSpring = {
    }

    def doWithApplicationContext = { applicationContext ->
    }

    def doWithWebDescriptor = { xml ->
    }

    def doWithDynamicMethods = { ctx ->
    }

    def onChange = { event ->
    }
}