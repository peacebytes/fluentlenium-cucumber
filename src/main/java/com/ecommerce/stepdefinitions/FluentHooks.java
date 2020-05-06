package com.ecommerce.stepdefinitions;

import com.ecommerce.page.*;
import com.ecommerce.utility.JsonUtil;
import com.ecommerce.utility.MySqlUtil;
import com.ecommerce.utility.TestData;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.util.Arrays;
import java.util.List;

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
        System.out.println("scenario.getClass();:" + scenario.getClass());
        synchronized (scenario) {
            System.out.println("inside synchronized" );
            currentTestId = MySqlUtil.getAvailableTestId();
            MySqlUtil.updateTestId(currentTestId, "running");
            System.out.println("inside synchronized currentTestId " + currentTestId );
        }
        long mili = System.currentTimeMillis();
        currentTestId = (int)(mili % browsers.size());
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
        after(scenario);
    }
}
