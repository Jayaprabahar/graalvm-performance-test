# GraalVM - OpenJDK Performance comparison

### Intro
* To compare the performance of JDK & GraalVM Native Image.
* Created a Simple Employee CRUD API application using SpringBoot 4.0.0 and Java 25. 
* Created controller, Service, Repository & Entity Classes.
* CRUD operations with MongoDB.

### Created few APIs for testing:
1. POST /api/employees/new - Create new employee
2. GET /api/employees/ - Fetch employee by random empId
3. GET /api/employees/{id} - Fetch employee by id
4. GET /api/employees - Fetch all employees
5. PUT /api/employees/{id} - Update employee by id
6. DELETE /api/employees/{id} - Delete employee by id

### Testing
* We are going to test only first 2 endpoints.
* We are going to use oha tool to do load testing on these APIs.

## Installation of oha
oha is a tiny TUI based program that sends some load to a web application and show realtime tui inspired by rakyll/hey.
We are going to use this tool for load testing our application.

https://github.com/hatoo/oha

Installation: -

![screenshot](test_results/install_oha.png)
