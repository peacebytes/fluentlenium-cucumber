package com.walmart.stepdefinitions;

import cucumber.api.java.en.Given;
import org.fluentlenium.adapter.cucumber.FluentCucumberTest;
import static org.junit.Assert.assertEquals;
import static com.walmart.stepdefinitions.FluentHooks.*;

public class LoginSteps extends FluentCucumberTest {

    @Given("I have logged into Automation Practice")
    public void loginAutomationPractice() {
        loginPage.goTo(loginPage);
        loginPage.login("automationqa@test.com","Pass1234");
        //assert log in was successful
        String actualTextaccount= loginPage.GetAccountValue();
        assertEquals(actualTextaccount.toLowerCase(), "automation test");
    }
}
