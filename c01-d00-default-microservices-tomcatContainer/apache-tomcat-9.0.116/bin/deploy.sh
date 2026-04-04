# =========================
# STEP 1: STOP TOMCAT
# =========================
cd ~/Desktop/java-full-stack-training/00-microservices-00/apache-tomcat-9.0.116/bin
./shutdown.sh

# =========================
# STEP 2: CLEAN OLD DEPLOYMENT
# =========================
cd ../webapps
rm -rf employee-service
rm -rf employee-service.war

# =========================
# STEP 3: CREATE STRUCTURE
# =========================
mkdir -p employee-service/WEB-INF/classes
mkdir -p employee-service/WEB-INF/lib

# =========================
# STEP 4: COPY XML CONFIG FILES
# =========================
cp ~/Desktop/java-full-stack-training/01-microservices-01-spring-rest-employee-service-final/src/main/webapp/WEB-INF/*.xml \
employee-service/WEB-INF/

# =========================
# STEP 5: COPY JARS
# =========================
cp ~/Desktop/java-full-stack-training/01-microservices-01-spring-rest-employee-service-final/src/main/webapp/WEB-INF/lib/* \
employee-service/WEB-INF/lib/

# =========================
# STEP 6: REMOVE DUPLICATE JARS (IMPORTANT)
# =========================
cd employee-service/WEB-INF/lib
rm -f spring-web-5.3.30.jar 2>/dev/null
cd ../../..

# =========================
# STEP 7: COMPILE PROJECT
# =========================
cd ~/Desktop/java-full-stack-training/01-microservices-01-spring-rest-employee-service-final

rm -rf compiled
mkdir compiled

javac -d compiled -cp "src/main/webapp/WEB-INF/lib/*" $(find src/main/java -name "*.java")

# =========================
# STEP 8: COPY COMPILED CLASSES
# =========================
cp -r compiled/* \
~/Desktop/java-full-stack-training/00-microservices-00/apache-tomcat-9.0.116/webapps/employee-service/WEB-INF/classes/

# =========================
# STEP 9: COPY PROPERTIES FILE
# =========================
cp src/main/resources/* \
~/Desktop/java-full-stack-training/00-microservices-00/apache-tomcat-9.0.116/webapps/employee-service/WEB-INF/classes/

# =========================
# STEP 10: START TOMCAT
# =========================
cd ~/Desktop/java-full-stack-training/00-microservices-00/apache-tomcat-9.0.116/bin
./startup.sh

# =========================
# STEP 11: CHECK LOGS
# =========================
tail -f ../logs/catalina.out
