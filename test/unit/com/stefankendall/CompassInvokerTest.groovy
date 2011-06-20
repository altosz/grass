package com.stefankendall

import grails.test.GrailsUnitTestCase

class CompassInvokerTest extends GrailsUnitTestCase {
    CompassInvoker compass

    public void setUp() {
        compass = new CompassInvoker()
    }

    public void test_compile() {
        CompassInvoker compiler = new CompassInvoker()

        def config = [
                grass:
                [
                        sass_dir: 'src/stylesheets',
                        css_dir: 'src/web-app/css',
                        images_dir: 'src/web-app/images',
                        relative_assets: true
                ]
        ]
        def blueprintCssFiles = [
                new File('src/web-app/css/blueprint/ie.css'),
                new File('src/web-app/css/blueprint/print.css'),
                new File('src/web-app/css/blueprint/screen.css')
        ]

        blueprintCssFiles*.delete()

        compiler.compile(config) {}

        boolean someFileNotCreated = blueprintCssFiles.any { !it.exists() }
        assertFalse( "One of ${blueprintCssFiles} not created", someFileNotCreated )
    }

    public void test_compass_gem_is_installed() {
        def output = new ByteArrayOutputStream()
        System.out = new PrintStream(output)
        compass.runCompassCommand(['--version'] as String[])

        assertTrue("Compass gem does not seem to be runnable", output.toString().contains("0.11"))
    }
}
