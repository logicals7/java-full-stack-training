#!/bin/bash
set -e  # Stop script on any error

echo "========================="
echo "SETTING JAVA 8 ENVIRONMENT"
echo "========================="

export JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-8.jdk/Contents/Home
export JRE_HOME=$JAVA_HOME
export PATH=$JAVA_HOME/bin:$PATH

echo "JAVA VERSION:"
java -version

# Validate Java version
if [[ $(java -version 2>&1) != *"1.8"* ]]; then
  echo "❌ ERROR: Java 8 is NOT active. Exiting..."
  exit 1
fi

echo "========================="
echo "STOPPING TOMCAT"
echo "========================="

TOMCAT_HOME=~/Desktop/java-full-stack-training/00-microservices-00/apache-tomcat-9.0.116
PROJECT_HOME=~/Desktop/java-full-stack-training/02-microservices-03-spring-rest-employee-service-custom-queries-final

cd $TOMCAT_HOME/bin
./shutdown.sh || true
sleep 5

echo "========================="
echo "CLEANING TOMCAT CACHE"
echo "========================="

cd $TOMCAT_HOME
rm -rf work/*
rm -rf temp/*

echo "========================="
echo "REMOVING OLD DEPLOYMENT"
echo "========================="

cd webapps
rm -rf employee-service
rm -rf employee-service.war

echo "========================="
echo "CREATING FRESH STRUCTURE"
echo "========================="

mkdir -p employee-service/WEB-INF/classes
mkdir -p employee-service/WEB-INF/lib

echo "========================="
echo "COPYING CONFIG FILES"
echo "========================="

cp $PROJECT_HOME/src/main/webapp/WEB-INF/*.xml employee-service/WEB-INF/

echo "========================="
echo "COPYING JARS"
echo "========================="

cp $PROJECT_HOME/src/main/webapp/WEB-INF/lib/* employee-service/WEB-INF/lib/

echo "========================="
echo "COMPILING PROJECT"
echo "========================="

cd $PROJECT_HOME

rm -rf compiled
mkdir compiled

javac -d compiled -cp "src/main/webapp/WEB-INF/lib/*" $(find src/main/java -name "*.java")

echo "========================="
echo "COPYING COMPILED CLASSES"
echo "========================="

cp -r compiled/* $TOMCAT_HOME/webapps/employee-service/WEB-INF/classes/

echo "========================="
echo "COPYING PROPERTIES FILE"
echo "========================="

cp src/main/resources/* $TOMCAT_HOME/webapps/employee-service/WEB-INF/classes/

echo "========================="
echo "STARTING TOMCAT"
echo "========================="

cd $TOMCAT_HOME/bin
./startup.sh

echo "========================="
echo "DEPLOYMENT COMPLETE"
echo "========================="

echo "App URL:"
echo "http://localhost:8080/employee-service/hello"

echo "========================="
echo "TAILING LOGS"
echo "========================="

tail -f $TOMCAT_HOME/logs/catalina.out