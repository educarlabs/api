#!/bin/bash

path_to_javac=$(which javac)
path_to_java=$(which java)

if [ ! -x "$path_to_javac" ] || [ ! -x "$path_to_java" ] ; then
	echo "No existe JDK en el PATH"
	exit;
fi

#Compile
echo "Compilando..."
javac -classpath .:lib/commons-httpclient-3.0.jar \
-Xlint:deprecation \
NoticiasRecientes.java

#Exec
echo "Ejecuntando Test..."
java -cp .:lib/commons-httpclient-3.0.jar:lib/commons-logging-1.1.3.jar:lib/commons-codec-1.6.jar NoticiasRecientes

#Happiness :)
