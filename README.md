Coverage: 84.3%
# IMS-Starter

IMS-Starter Project is a projected that allows a user to control their inventory. 
It allows the user to control all their customers data, orders as well as items.
This includes adding, deleting and updating.
The project is programmed in Java and uses GCP SQL to store the data and it does teh testing using Junit and Mockito.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

To get the project running you must install java into your computer as well as maven.
```
Install Java (jdk14): https://docs.oracle.com/en/java/javase/12/install/installation-jdk-microsoft-windows-platforms.html#GUID-A7E27B90-A28D-4237-9383-A58B416071CA
Install for Maven: https://mkyong.com/maven/how-to-install-maven-in-windows/ 
```

### Installing

To get the project running at first you must clone the repository to a local folder. Then you must install maven and initialise the porject.

```
git clone
mvn clean install
mvn clean package
java -jar ims-0.0.1-jar-with-dependencies.jar
```

## Running the tests

If you have any IDE installed such as Eclipse you can click on a project and run "Coverage As" then "Junit" and it will test all the tests.
Or you can go into the CMD and run "mvn clean install".
The test go through all DAOs that have been made "CustomerDAO" "OrderDAO" and "ItemDAO", there is also a test for failures.
So if they cannot connect it will test that it will give the right output.
There is also test that covers all the controllers.
These test makes sure that the programme is fully functional and thet the user can add, delete or update.

### Unit Tests 

All tests are stored in src/test/java in the package com.qa.ims

```
CustomerDAOTest.java
CustomerDAOTestFail.java
ItemDAOTest.java
ItemDAOTestFail.java
OrderDAOTest.java
OrderDAOTestFail.java
CustomerControllerTest.java
OrderControllerTest.java
ItemControllerTest.java
```

### Integration Tests 
Explain what these tests test, why and how to run them

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
