includeTargets << grailsScript("Init")

target(main: "The description of the script goes here!") {
	gridImg()
}

target(gridImg: "Generate grid.png") {
	println """
Not implemented yet.
You may get grid.png for your css in http://kematzy.com/blueprint-generator/
Place it under ./web-app/images
To show grid include class="showgrid" in your container element
"""
}

setDefaultTarget(main)
