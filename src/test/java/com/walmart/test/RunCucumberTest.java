package com.walmart.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "com.walmart.stepdefinitions",
        strict = true,
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
        tags = {"~@skip"}
)
public class RunCucumberTest {

}
