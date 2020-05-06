package com.ecommerce.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.fluentlenium.adapter.cucumber.FluentCucumberTest;
import static com.ecommerce.stepdefinitions.FluentHooks.*;

public class MyAccountSteps extends FluentCucumberTest {

    @Then("I click My Account")
    public void i_click_myaccount() {
        myAccountPage.ClickMyAccount();
        //assert i am at My Account
        String actualTextmyaccountWelcome = myAccountPage.GetMyccountWelcomeValue();
        assert(actualTextmyaccountWelcome.contains("Welcome to your account"));
    }

    @And("I click on \"([^\"]*)\"")
    public void i_click_on(String optionToClick) {
        myAccountPage.selectOption(optionToClick);
        //assert i clicked on expected option successfully
        assert(optionToClick.toLowerCase().contains(myAccountPage.GetPageHeaderValue().toLowerCase()));
    }
}
