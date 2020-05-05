package com.walmart.stepdefinitions;

import com.walmart.page.*;
import com.walmart.utility.JsonUtil;
import com.walmart.utility.MySqlUtil;
import com.walmart.utility.TestData;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.runtime.model.CucumberBackground;
import cucumber.runtime.model.CucumberScenario;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class FluentHooks extends BaseTest {

    public static TestData testData;
    public static int currentTestId;
    private final List<String> browsers = Arrays.asList("chrome","firefox");

    @Override
    public String getWebDriver(){
        return browsers.get(currentTestId);
    }

    @Before
    public void beforeScenario(Scenario scenario){
        System.out.println("Called before scenario :" + scenario.getName());
        long mili = System.currentTimeMillis();
        currentTestId = (int)(mili % browsers.size());
        before(scenario);
        loginPage = newInstance(LoginPage.class);
        myAccountPage = newInstance(MyAccountPage.class);
        myAddressPage = newInstance(MyAddressPage.class);
        window().maximize();
        System.out.println("===> Running with test data of " + currentTestId + " on browser " + getWebDriver());
        testData = JsonUtil.loadTestData(currentTestId);
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("Called after scenario :" + scenario.getName());
        after(scenario);
    }
}
