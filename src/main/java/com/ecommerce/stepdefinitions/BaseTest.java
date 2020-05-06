package com.ecommerce.stepdefinitions;

import com.ecommerce.page.LoginPage;
import com.ecommerce.page.MyAccountPage;
import com.ecommerce.page.MyAddressPage;
import org.fluentlenium.adapter.cucumber.FluentCucumberTest;
import org.fluentlenium.core.annotation.Page;

public class BaseTest extends FluentCucumberTest {
    @Page
    public static LoginPage loginPage;
    
    @Page
    public static MyAccountPage myAccountPage;

    @Page
    public static MyAddressPage myAddressPage;
}
