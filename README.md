# FluentLenium Cucumber


### What is this project?

This is a Web UI automation test framework. The goal is to run automatic tests to ensure http://automationpractice.com web page works as expected on local environment.

### Build With

The framework was built on:
- Web and Mobile automation framework: FluentLenium
- Test Framework: Cucumber
- Language: Java
- Build tool: Apache Maven

### Getting Started 

Install the following tools:
- Java Development Kit: 1.8 or later.
- Apache maven: suggested 3.3.1 to compatible with Java 7 or later.
- FluentLenium Core and Cucumber: 3.3.0

### Usage

To clean install dependencies and compile code without running tests
```
mvn clean install -DskipTests=true
```

To clean install dependencies and running all tests
```
mvn clean test
``` 

To run smoke test
```
mvn clean test -Dcucumber.options="--tags @Smoke"
```

### Run tests in parallel

There are 3 suggestions for running the same set of test cases in parallel (on different browsers). The goals are trying to use different credential and different test data for each run.

Solution 1: Starting test in serial, one after another. Everything is generated on-the-fly. No control on data/creds.
Starting the test with registration a new creds. Test data will also be randomly generated where needed.

Solution 2: Starting test in serial, one after another. Data/Creds are defined up-front. Each test will consume a test id which decide its creds and data.

Solution 3: Running multiple runners, those runners will pick up test features from resources/features. Runners will have its own thread with a WebDriver and a set of data/creds. When a runner finishes a test feature file, it will continue to pick up another feature file to run (because <reuseForks>true</reuseForks>). Each runner will connect to a persistence-database to confirm it has picked up a test id and test id can't be selected by other runners.

To run multiple runners
```
mvn clean test -Pparallel
```

Number of runners will be set by
```
<forkCount>2</forkCount>
```


### Report

By default, maven has surefire plugin to help us run tests out of the box. Exploring surefire-report for `index.html` file to see the report.

### Database

UBUNTU
Start container
```
docker container run -d --name mysql -e MYSQL_RANDOM_ROOT_PASSWORD=yes -e MYSQL_DATABASE=fluentcucumber -e MYSQL_USER=root -e MYSQL_PASSWORD=pass1234 --publish 3306:3306 mysql:5.7
```
WINS
Mysql 5.7.10 (root/pass1234)
username: fluentcucumber
password: pass1234

Connect to Mysql database
```
mysql -h 127.0.0.1 -u root -p
```

Create database and table
```
CREATE DATABASE fluentlenium_cucumber;
USE fluentlenium_cucumber;
CREATE TABLE IF NOT EXISTS test_ids (
    test_id INT AUTO_INCREMENT PRIMARY KEY,
    test_status TEXT
)  ENGINE=INNODB;
```

Create initial data
```
INSERT into test_ids value (1,"available");
INSERT into test_ids value (2,"available");
```

Trouble shooting
```
use fluentlenium_cucumber;
INSERT into test_ids value (3,"available");
UPDATE test_ids SET test_status="available" WHERE test_id=1;
select * from test_ids;

show databases;
use mysql;
show tables;
SELECT MAX_USER_CONNECTIONS FROM user WHERE user='root';
ALTER USER 'root'@'localhost' WITH MAX_USER_CONNECTIONS 0;
```

### Contact

Michael Ho - @michaelho