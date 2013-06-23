# Build a directory of compass and its dependencies
echo "Downloading compass gems"
java -jar ../lib/jruby-complete-*.jar -S gem install -i ./compass-gems compass --no-rdoc --no-ri

# Package the files as a jar
echo "Building compass jar"
jar cf compass-gems.jar -C compass-gems .

# Cleanup the intermediate folder
echo "Cleaning up intermediate directories"
rm -rf ./compass-gems/

# Deploy new jar
echo "Moving built jar to ../lib"
mv -f ./compass-gems.jar ../lib/
