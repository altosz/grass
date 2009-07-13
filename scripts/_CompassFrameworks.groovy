
availableCompassFrameworks = [
	
	
	"blueprint": [
		description: "Blueprint is a CSS framework, which aims to cut down on your development time. It gives you a solid foundation to build your project on top of, with an easy-to-use grid, sensible typography, useful plugins.",
		documentation: "http://wiki.github.com/chriseppstein/compass/blueprint-documentation",
		dir: "blueprint",
		import: """
<head>
  <link href="/stylesheets/screen.css" media="screen, projection" rel="stylesheet" type="text/css" />
  <link href="/stylesheets/print.css" media="print" rel="stylesheet" type="text/css" />
  <!--[if lt IE 8]>
      <link href="/stylesheets/ie.css" media="screen, projection" rel="stylesheet" type="text/css" />
  <![endif]-->
</head>
"""
	],
	
	
	"yui": [
		description: "Port of YUI's CSS to Sass",
		documentation: "http://wiki.github.com/chriseppstein/compass/yui-documentation",
		dir: "yui",
		import: """
<head>
  <link href="/stylesheets/screen.css" media="screen, projection" rel="stylesheet" type="text/css" />
</head>
"""
	]
	
	
]
