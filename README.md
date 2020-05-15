# Project 1
This application is an ETL batch processor that extracts data from a file, transforms the data using Apache Spark's Resilient Distributed Dataset (RDD), and loads the transformed data into an SQL database. The input file is in CSV format and has 7,997 rows. Each row contains information on a U.S. government real estate lease. The batch process reduces the dataset to determine the total number of U.S. government leases for each U.S. state. The results of the batch process are stored in a PostgreSQL database. The application also includes REST services which provide the status of the batch process, a mechanism for triggering the batch process, and a view of the generated results of the batch process.

## Design
### Architecture
### Batch
### Services

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
    ./start-app.sh
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

    curl localhost:8080/project-1-sutter/status
    curl localhost:8080/project-1-sutter/batch
    curl localhost:8080/project-1-sutter/leases?state=TX

### Unit Tests

    mvn clean test -Dtest=TestClassName#testOne+testTwo

    mvn test -Dtest=DatabaseTest
    mvn test -Dtest=DatabaseTest#testConnection
    mvn clean test -Dtest=DatabaseTest#testConnection
    
    mvn clean test -Dtest=EmployeesTest

    mvn clean test -Dtest=StatesTest
    mvn clean test -Dtest=StatesTest#testInsertMany
    mvn clean test -Dtest=StatesTest#testSelect

    mvn clean test -Dtest=BatchJobTest

### Connect to EC2

    ssh -i ~/.ssh/ec2-project-1-sutter.pem ec2-user@3.22.171.75

### Connect to Database

    psql --host=ec2-3-22-171-75.us-east-2.compute.amazonaws.com --port=5432 --username=user1 --password --dbname=postgres

    psql postgresql://user1@ec2-3-22-171-75.us-east-2.compute.amazonaws.com:5432/postgres

### Execute SQL scripts

#### Build Schema

    psql -h ec2-3-22-171-75.us-east-2.compute.amazonaws.com -p 5432 -d postgres -U user1 -a -q -f src/main/resources/schema.sql

#### Show Schema

    psql -h ec2-3-22-171-75.us-east-2.compute.amazonaws.com -p 5432 -d postgres -U user1 -a -q -f src/main/resources/display.sql
