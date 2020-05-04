package com.walmart.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.fluentlenium.adapter.cucumber.FluentCucumberTest;
import static org.junit.Assert.assertEquals;
import static com.walmart.stepdefinitions.FluentHooks.*;

public class CommonSteps extends FluentCucumberTest {

    @Given("^I should (.*) (.*) button$")
    public void item_page_is_loaded(String arg1, String arg2) throws Throwable {
        if (arg1.equals("not see")) {
            boolean actualFlag = loginPage.LoginButtonIsVisible();
            System.out.println("actualFlag: " + actualFlag);
            assertEquals(actualFlag, true);
        } else {
            boolean actualFlag = loginPage.LoginButtonIsVisible();
            System.out.println("actualFlag: " + actualFlag);
            assertEquals(actualFlag, true);
        }
    }

    @Then("I log out Automation Practice")
    public void i_log_out_Automation_Practice() {
        loginPage.LogoutSystem();
        //assert log out was successful
        String actualTextalreadyRegistered = loginPage.GetAlreadyRegisteredValue();
        assertEquals(actualTextalreadyRegistered, "ALREADY REGISTERED?");
    }
}
