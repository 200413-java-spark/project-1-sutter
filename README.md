# Project 1



### Default Spark Server

localhost:4040

localhost:4040/jobs

### Run Main Program

mvn clean compile exec:java
    
    Note: requires this element inside of pom.xml>project>properties: <exec.mainClass>your.main.App</exec.mainClass>

### Example Requests

curl localhost:8080/project-1-sutter/main

curl "localhost:8080/project-1-sutter/main?firstName=Sutter&lastName=Grune"

### Example Tests

mvn clean test -Dtest=TestClassName#testOne+testTwo

mvn test -Dtest=DatabaseTest

mvn test -Dtest=DatabaseTest#testConnection

mvn clean test -Dtest=DatabaseTest#testConnection
