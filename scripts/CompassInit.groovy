includeTargets << grailsScript("Init")
includeTargets << new File("${pluginBasedir}/scripts/_CompassFrameworks.groovy")

target(main: "The description of the script goes here!") {
    initCompass()
}

target(initCompass: 'Initialize compass framework') {
	if (args) {
		frameworkName = args(trim)
	} else {
		Ant.input(addProperty:"compass.init.framework.name", message:"Enter the framework name")
        pluginName = Ant.antProject.properties."compass.init.framework.name"
	}
	initCompassFramework()
}

target(initCompassFramework: 'Initialize compass framework') {
	if (frameworkName && availableCompassFrameworks."$frameworkName") {
		
	} else {
		event("StatusError", ["Cannot find specified compass framework, use 'grails list-compass-frameworks' to get the list of available frameworks"])
	}
}

setDefaultTarget(main)


/*
Ant.copy(todir: "${basedir}/src/templates/scaffolding", overwrite: true) {
    fileset(dir: "${pluginBasedir}/src/grails/templates/scaffolding")
}
*/


/*  --blueprint
Congratulations! Your compass project has been created.
You must recompile your sass stylesheets when they change.
This can be done in one of the following ways:
  1. From within your project directory run:
     compass
  2. From any directory run:
     compass -u path/to/project
  3. To monitor your project for changes and automatically recompile:
     compass --watch [path/to/project]

To import your new stylesheets add the following lines of HTML (or equivalent) to your webpage:
<head>
  <link href="/stylesheets/screen.css" media="screen, projection" rel="stylesheet" type="text/css" />
  <link href="/stylesheets/print.css" media="print" rel="stylesheet" type="text/css" />
  <!--[if lt IE 8]>
      <link href="/stylesheets/ie.css" media="screen, projection" rel="stylesheet" type="text/css" />
  <![endif]-->
</head>

*/

/*  --yui
Congratulations! Your compass project has been created.
You must recompile your sass stylesheets when they change.
This can be done in one of the following ways:
  1. From within your project directory run:
     compass
  2. From any directory run:
     compass -u path/to/project
  3. To monitor your project for changes and automatically recompile:
     compass --watch [path/to/project]

To import your new stylesheets add the following lines of HTML (or equivalent) to your webpage:
<head>
  <link href="/stylesheets/screen.css" media="screen, projection" rel="stylesheet" type="text/css" />
</head>
*/
