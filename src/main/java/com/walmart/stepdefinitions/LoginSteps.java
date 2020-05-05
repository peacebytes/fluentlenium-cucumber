package com.walmart.stepdefinitions;

import cucumber.api.java.en.Given;
import org.fluentlenium.adapter.cucumber.FluentCucumberTest;
import static org.junit.Assert.assertEquals;
import static com.walmart.stepdefinitions.FluentHooks.*;
import static org.junit.Assert.assertTrue;

public class LoginSteps extends FluentCucumberTest {

    @Given("I have logged into Automation Practice")
    public void loginAutomationPractice() {
        loginPage.goTo(loginPage);
        loginPage.login(testData.creds.username,testData.creds.password);
        //assert log in was successful
        String actualTextaccount= loginPage.GetAccountValue();
        assertTrue(actualTextaccount.toLowerCase().contains("automation"));
    }
}
