package com.walmart.stepdefinitions;

import com.walmart.page.LoginPage;
import com.walmart.page.MyAccountPage;
import com.walmart.page.MyAddressPage;
import org.fluentlenium.adapter.cucumber.FluentCucumberTest;
import org.fluentlenium.core.annotation.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaseTest extends FluentCucumberTest {
    @Page
    public static LoginPage loginPage;
    
    @Page
    public static MyAccountPage myAccountPage;

    @Page
    public static MyAddressPage myAddressPage;

    public static HashMap<String,String> dataAddress = new HashMap<>();
    static {
        dataAddress.put("address1", "Random Name Address");
        dataAddress.put("city", "city Random Name");
        dataAddress.put("state_option", "Washington");
        dataAddress.put("postcode", "02123");
        dataAddress.put("country_option", "United States");
        dataAddress.put("phone", "3424443333");
        dataAddress.put("alias", "Random Name");
    }

    public static List<HashMap<String, String>> addresses = new ArrayList<>();
    static {
        addresses.add(dataAddress);
    }
}
