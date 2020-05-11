# Project 1
An ETL batch processor that extracts data from a CSV file, transforms the data using Apache Spark's Resilient Distributed Dataset (RDD), and loads the transformed data into a PostgreSQL database.

## Design
### Architecture
### Batch
### Services
-Apache Tomcat
    -HttpServlet
        -javax.servlet-api

## Build
### Java
    mvn clean package

## Usage
### Run Apache Tomcat Server (INVALID)
    mvn tomcat7:run
### View Spark Results
>localhost:4040
>localhost:4040/jobs
### Run Main Program
#### Option 1
    mvn clean package
    java -jar target/project-1-sutter-0.0.1.jar
#### Option 1 (format reference)
    mvn clean package
    java -jar target/*.jar arg1 arg2 [arg3/path/to/file.csv]
#### Option 1 (shortcut)
    ./run-batch-job.sh
#### Other options?
    mvn exec:java
    mvn clean exec:java
    mvn clean compile exec:java
    mvn clean package exec:java
    mvn package exec:java
>Requirement: uncomment "exec-maven-plugin" in pom.xml
>Note: uncommenting the following pom.xml element will not suffice: <exec.mainClass>app.App</exec.mainClass>


### HTTP Requests

    curl localhost:8080/project-1-sutter/main

    curl "localhost:8080/project-1-sutter/main?firstName=Sutter&lastName=Grune"

### Unit Tests

    mvn clean test -Dtest=TestClassName#testOne+testTwo

    mvn test -Dtest=DatabaseTest

    mvn test -Dtest=DatabaseTest#testConnection

    mvn clean test -Dtest=DatabaseTest#testConnection

### Connect to EC2

    ssh -i ~/.ssh/ec2-project-1-sutter.pem ec2-user@3.22.171.75
