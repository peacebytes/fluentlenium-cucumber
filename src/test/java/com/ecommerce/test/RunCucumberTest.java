package com.ecommerce.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "com.ecommerce.stepdefinitions",
        strict = true,
        plugin = {"html:target/cucumber", "json:target/cucumber.json"},
        tags = {"~@skip"}
)
public class RunCucumberTest {

}
