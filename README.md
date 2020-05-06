# FluentLenium Cucumber


### What is this for?

This is a Web UI automation test framework. The goal is to run automatic end-to-end tests against http://automationpractice.com.

### Build With

The framework was built on:
- FluentLenium Core and Cucumber: 3.3.0
- Cucumber core, junit, java: 1.2.5
- Language: Java
- Build tool: Apache Maven

### Getting Started 

Install the following tools:
- Java Development Kit: 1.8 or later.
- WebDriver for Chrome, Firefox, IE, ...
http://chromedriver.chromium.org/downloads
https://github.com/mozilla/geckodriver/releases
https://selenium-release.storage.googleapis.com/3.150/IEDriverServer_Win32_3.150.1.zip

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

Solution 1: Starting test in serial, one after another. Everything is generated on-the-fly. No control on data/creds. Starting the test with a brand new credential registration. Test data will also be randomly generated where needed.

Solution 2: Starting test in serial, one after another. Data/Creds are defined up-front. Each test will consume a test id which decide its creds and data.

Solution 3: Running multiple runners, those runners will pick up test features from resources/features. Runners will have its own JVM processe with a WebDriver and a set of data/creds. All JVM processes will connect to a persistence-database to pick up a test id which is available.
Read more here: https://maven.apache.org/surefire/maven-surefire-plugin/examples/fork-options-and-parallel-execution.html

This starter kit implements solution 3.

To run multiple runners
```
mvn clean test -Pparallel
```

Number of runners will be set by
```
<forkCount>2</forkCount>
```

### Report

Exploring target/surefire-report or target/cucumber-report for `index.html` file to see some simple reports.

To do:
- Integrate with Allure
- Integrate with Serenity (Live Documentation)

### Database

For Ubuntu, start a docker container
```
docker container run -d --name mysql -e MYSQL_RANDOM_ROOT_PASSWORD=yes -e MYSQL_DATABASE=fluentlenium_cucumber -e MYSQL_USER=root -e MYSQL_PASSWORD=pass1234 --publish 3306:3306 mysql:5.7
```
For Windows, download mysql 5.7 server from https://dev.mysql.com/downloads/mysql/5.7.html

Connect to Mysql database
```
mysql -h 127.0.0.1 -u root -p
```

Create a database then a table
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

Parallel test running will have multiple JVM runners connect to DB at the same time. Allow only 1 connection to database to prevent race-condition from happening.
```
use mysql;
SELECT MAX_USER_CONNECTIONS FROM user WHERE user='root';
ALTER USER 'root'@'localhost' WITH MAX_USER_CONNECTIONS 1;
```

Trouble shooting commands.
```
use fluentlenium_cucumber;
INSERT into test_ids value (1,"available");
UPDATE test_ids SET test_status="available" WHERE test_id=1;
select * from test_ids;
```

### Contact

Michael Ho - Gmail: hogiamisan@gmail.com