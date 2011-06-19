import grails.test.GrailsUnitTestCase
import org.jruby.Main
import org.jruby.cext.JRuby

class CompassCompilerTest extends GrailsUnitTestCase {
    CompassCompiler compiler

    public void setUp() {
        compiler = new CompassCompiler()
    }

    public void test_compile() {
        CompassCompiler compiler = new CompassCompiler()

        def config = [
                grass:
                [
                        sass_dir: 'src/stylesheets',
                        css_dir: 'src/web-app/css',
                        images_dir: 'src/web-app/images',
                        relative_assets: true
                ]
        ]
        compiler.compile(config) {}
    }

    public void test_jruby_is_runnable() {
        def output = new ByteArrayOutputStream()
        System.out = new PrintStream(output)
        compiler.runJRubyWithArgs(['--version'] as String[])
        assertTrue( "jruby does not appear to be runnable", output.toString().contains( "jruby 1.6.2" ) )
    }

    public void test_compass_gem_is_installed(){
        compiler.runJRubyWithArgs(['-S', 'compass'] as String[])
    }
}
