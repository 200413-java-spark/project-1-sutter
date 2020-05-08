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
### Run Apache Tomcat Server
    mvn tomcat7:run
### View Spark Results
>localhost:4040
>localhost:4040/jobs

### Run Batch Process
#### Option 1
    mvn clean compile exec:java
>Note: requires this element inside of pom.xml>project>properties: <exec.mainClass>app.App</exec.mainClass>
#### Option 2
    mvn clean package
    java -jar target/*.jar arg1 arg2 [arg3/path/to/file.csv]

### HTTP Requests

    curl localhost:8080/project-1-sutter/main

    curl "localhost:8080/project-1-sutter/main?firstName=Sutter&lastName=Grune"

### Unit Tests

    mvn clean test -Dtest=TestClassName#testOne+testTwo

    mvn test -Dtest=DatabaseTest

    mvn test -Dtest=DatabaseTest#testConnection

    mvn clean test -Dtest=DatabaseTest#testConnection
