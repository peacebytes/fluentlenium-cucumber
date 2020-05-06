package com.ecommerce.stepdefinitions;

import com.ecommerce.page.*;
import com.ecommerce.utility.JsonUtil;
import com.ecommerce.utility.MySqlUtil;
import com.ecommerce.utility.TestData;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.fluentlenium.adapter.cucumber.FluentCucumberTest;
import org.fluentlenium.core.annotation.Page;

import java.util.Arrays;
import java.util.List;


public class FluentHooks extends FluentCucumberTest {
    @Page
    public static LoginPage loginPage;
    @Page
    public static MyAccountPage myAccountPage;
    @Page
    public static MyAddressPage myAddressPage;

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
        currentTestId = MySqlUtil.getAvailableTestId();
        before(scenario);
        loginPage = newInstance(LoginPage.class);
        myAccountPage = newInstance(MyAccountPage.class);
        myAddressPage = newInstance(MyAddressPage.class);
        window().maximize();
        testData = JsonUtil.loadTestData(currentTestId);
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("Called after scenario :" + scenario.getName());
        MySqlUtil.updateTestId(currentTestId, "available");
        after(scenario);
    }
}
