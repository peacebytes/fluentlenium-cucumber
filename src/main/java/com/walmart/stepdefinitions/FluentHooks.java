package com.walmart.stepdefinitions;

import com.walmart.page.*;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.fluentlenium.adapter.FluentTestRunnerAdapter;
import org.fluentlenium.adapter.cucumber.FluentCucumberTest;
import org.fluentlenium.configuration.FluentConfiguration;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class FluentHooks extends BaseTest {

    @Before
    public void beforeScenario(Scenario scenario){
        before(scenario);
        System.out.println("Called before scenario :" + scenario.getName());
        loginPage = newInstance(LoginPage.class);
        myAccountPage = newInstance(MyAccountPage.class);
        myAddressPage = newInstance(MyAddressPage.class);
        window().maximize();
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("Called after scenario :" + scenario.getName());
        after(scenario);
    }

//    @Override
//    public String getWebDriver(){
//        return "ie";
//    }

//    @Override
//    public Capabilities getCapabilities(){
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("chromeOptions", options);
//        return capabilities;
//    }
}
