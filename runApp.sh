#!/bin/bash

#put commands here to compile and run your app from command line

mkdir bin
javac src/main/*.java -d bin # Put compiled .class files into the bin folder. 
java -cp bin main.MainMenu # Run the MainMenu class from the bin folder. 