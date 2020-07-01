# Project 1
This application is an ETL batch processor that extracts data from a file, transforms the data using Apache Spark's Resilient Distributed Dataset (RDD), and loads the transformed data into an SQL database. The input file is in CSV format and has 7,997 rows; each row contains information on a U.S. government real estate lease. The batch process reduces the dataset to determine the total number of U.S. government leases for each U.S. state. The resulting output is persisted to an SQL database. The application also includes REST services which provide the status of the batch process, a mechanism for triggering the batch process, and a view of the generated results of the batch process.

## Build
#### Package Maven project:
    mvn clean package

## Usage
#### Build & Execute:
    mvn clean package
    java -jar target/project-1-sutter-0.0.1.jar
#### Build & Execute (format reference):
    mvn clean package
    java -jar target/*.jar arg1 arg2 [arg3/path/to/file.csv]
#### Build & Execute (shortcut):
    ./start-app.sh
#### More options:
    mvn exec:java
    mvn clean exec:java
    mvn clean compile exec:java
    mvn clean package exec:java
    mvn package exec:java
>Requirement: uncomment "exec-maven-plugin" in pom.xml - 
>it will not suffice to only uncomment this pom.xml element: <exec.mainClass>app.App</exec.mainClass>

## HTTP Requests
#### Check status of batch process:
    curl localhost:8080/project-1-sutter/status
#### Execute batch process:
    curl localhost:8080/project-1-sutter/batch
#### View results of batch process:
    curl localhost:8080/project-1-sutter/leases?state=[abbreviation]
    curl localhost:8080/project-1-sutter/leases?state=NY
    curl localhost:8080/project-1-sutter/leases?state=TX

## Tests
#### Execute JUnit tests (format reference):
    mvn clean test -Dtest=TestClassName#testOne+testTwo
#### Test utility classes:
    mvn clean test -Dtest=AppPropertiesTest
    mvn clean test -Dtest=DatabaseTest
#### Test Direct Access Objects (DAO):
    mvn clean test -Dtest=StatesTest
    mvn clean test -Dtest=StatesTest#testInsertMany
    mvn clean test -Dtest=StatesTest#testSelect
#### Test batch job:
    mvn clean test -Dtest=BatchJobTest
#### Test Spark SQL transformations:
    mvn clean test -Dtest=SparkSqlTest

## Database
#### Connect:
    psql --host=ec2-3-22-171-75.us-east-2.compute.amazonaws.com --port=5432 --username=user1 --password --dbname=postgres
    psql postgresql://user1@ec2-3-22-171-75.us-east-2.compute.amazonaws.com:5432/postgres
#### Build schema:
    psql -h ec2-3-22-171-75.us-east-2.compute.amazonaws.com -p 5432 -d postgres -U user1 -a -q -f src/main/resources/schema.sql
#### Show data:
    psql -h ec2-3-22-171-75.us-east-2.compute.amazonaws.com -p 5432 -d postgres -U user1 -a -q -f src/main/resources/display.sql

## More
#### Connect to EC2:
    ssh -i ~/.ssh/ec2-project-1-sutter.pem ec2-user@3.22.171.75
#### View Spark results:
>localhost:4040/jobs
