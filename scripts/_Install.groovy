//
// This script is executed by Grails after plugin was installed to project.
// This script is a Gant script so you can use all special variables provided
// by Gant (such as 'baseDir' which points on project base dir). You can
// use 'ant' to access a global instance of AntBuilder
//
// For example you can create directory under project tree:
//
//    ant.mkdir(dir:"${basedir}/grails-app/jobs")
//

def getOutputFromCommand(String command) {
    def process = command.execute()
    def out = new StringBuffer()
    def err = new StringBuffer()
    process.consumeProcessOutput(out, err)
    process.waitFor()

    return out.toString()
}

def isJRubyInstalled() {
    try {
        println "JRuby version: ${getOutputFromCommand('jruby --version')}"
    }
    catch (Exception e) {
        return false
    }

    return true
}

def isCompassGemInstalled() {
    String gems = getOutputFromCommand("jruby -S gem list")
    println( "Installed gems:" )
    println gems
    gems.contains("compass")
}

def installCompass() {
    Process p = "jruby -S gem install compass".execute()
    p.consumeProcessOutput(System.out, System.err)
    p.waitFor()
}

println "Testing to see if JRuby is installed..."
if (!isJRubyInstalled()) {
    println '*' * 20
    println "JRuby could not be found on your system. Make sure it is on your PATH and re-install this plugin."
    println '*' * 20
    return
}

println "Testing to see if Compass gem is installed..."
if (!isCompassGemInstalled()) {
    println "Compass gem not found; attempting to install automatically..."
    installCompass()
}