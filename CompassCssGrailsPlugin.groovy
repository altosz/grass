class CompassCssGrailsPlugin {
	def version = "0.1"
	def grailsVersion = "1.1.1 > *"
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

	def documentation = "http://grails.org/CompassCss+Plugin"

	def doWithSpring = {
		// TODO Implement runtime spring config (optional)
	}

	def doWithApplicationContext = { applicationContext ->
		// TODO Implement post initialization spring config (optional)
	}

	def doWithWebDescriptor = { xml ->
		// TODO Implement additions to web.xml (optional)
	}

	def doWithDynamicMethods = { ctx ->
		// TODO Implement registering dynamic methods to classes (optional)
	}

	def watchedResources = "file:./src/stylesheets/*.sass"

	def onChange = { event ->
		// TODO Implement code that is executed when any artefact that this plugin is
		// watching is modified and reloaded. The event contains: event.source,
		// event.application, event.manager, event.ctx, and event.plugin.

		GroovyClassLoader classLoader = new GroovyClassLoader(getClass().getClassLoader())
		ConfigObject config = new ConfigSlurper().parse(classLoader.loadClass('CompassConfig'))

		try {
			CompassCompile.compile(config, new AntBuilder()) { v, msg -> 
				if (!v) throw new Exception(msg)
			}
		} catch (Exception e) {
			println e.message
		}
		
	}

	def onConfigChange = { event ->
		// TODO Implement code that is executed when the project configuration changes.
		// The event is the same as for 'onChange'.
	}
}
