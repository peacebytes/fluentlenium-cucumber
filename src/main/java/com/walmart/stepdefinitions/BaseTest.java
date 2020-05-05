package com.walmart.stepdefinitions;

import com.walmart.page.LoginPage;
import com.walmart.page.MyAccountPage;
import com.walmart.page.MyAddressPage;
import com.walmart.utility.JsonUtil;
import com.walmart.utility.MySqlUtil;
import com.walmart.utility.TestData;
import org.fluentlenium.adapter.cucumber.FluentCucumberTest;
import org.fluentlenium.core.annotation.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class BaseTest extends FluentCucumberTest {
    @Page
    public static LoginPage loginPage;
    
    @Page
    public static MyAccountPage myAccountPage;

    @Page
    public static MyAddressPage myAddressPage;
}
