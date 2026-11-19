#!/bin/bash

#put commands here to compile and run your app from command line

cd "$(dirname "$0")" # Move into the script's own folder. 
mkdir -p bin # Make /bin. 
javac -cp lib/gson-2.13.2.jar src/main/*.java -d bin # Put compiled .class files into the bin folder.
java -cp bin:lib/gson-2.13.2.jar main.MainMenu # Run the MainMenu class from the bin folder.