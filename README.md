# Automation Test Interview


### What is this project?

This repository contains Web UI automation tests. The goal is to run automatic tests to ensure http://automationpractice.com web page works as expected on local environment.

### Build With

The automation test framework was built on:
- Web and Mobile automation framework: FluentLenium, Selenium
- Test Framework: Cucumber
https://github.com/chaminda204/cucumber-fluentlenium/tree/master/src/test/java/com/example/au/common
- Language: Java
- Build tool: Apache Maven
- Target Environment: chrome browser on local

### Getting Started 

Install the following tools:
- Java Development Kit: 1.8 or later.
- Apache maven: suggested 3.3.1 to compatible with Java 7 or later.
- Chromedriver (download from http://chromedriver.chromium.org). Also putting Chromedriver on system path. Make sure the chromedriver is compatible with current Chrome Browser version on local.

### Usage

To clean install dependencies and compile code without running tests
```
mvn clean install -DskipTests=true
```

To clean install dependencies and running tests
```
mvn clean test
``` 

### Run tests in parallel
There are 3 suggestions for running the same set of test cases in parallel (on different browsers). The goals are trying to use different credential and different test data for each run.

Solution 1: Everything is generated on-the-fly. No control on data/creds.
Starting the test with registration a new creds. Test data will also be randomly generated where needed.

Solution 2: Data/Creds are defined up-front. Starting test in serial, one after another. Each test will consume a test id which decide its creds and data.

Solution 3: Running multiple runners, those runners will pick up test features from the pool.
### Report

By default, maven has surefire plugin to help us run tests out of the box. Exploring surefire-report for `index.html` file to see the report.

### Database
Mysql 5.7.10 (root/pass1234)
username: fluentcucumber
password: pass1234

All rights reserved.

### Contact

Michael Ho - @michaelho