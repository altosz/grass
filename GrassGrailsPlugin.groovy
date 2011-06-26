class GrassGrailsPlugin {
    def version = "0.1"
    def grailsVersion = "1.3.7 > *"
    def dependsOn = [:]
    def pluginExcludes = [
    ]

    def author = "Stefan Kendall"
    def authorEmail = "stefankendall@gmail.com"
    def title = "Compass SCSS/SASS compilation plugin, based on the original grass compass plugin."
    def description = '''\\
Compass is a stylesheet authoring tool that uses compass (http://compass-style.org/) to bring scss and sass support to grails.
	'''

    def documentation = "http://grails.org/Compass+SCSS+Integration+Plugin"

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