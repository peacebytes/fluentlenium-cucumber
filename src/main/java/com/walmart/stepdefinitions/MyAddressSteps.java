package com.walmart.stepdefinitions;

import com.google.common.base.Strings;
import static com.walmart.utility.TestData.*;
import cucumber.api.java.en.Then;
import org.fluentlenium.adapter.cucumber.FluentCucumberTest;
import static com.walmart.stepdefinitions.FluentHooks.*;

import java.util.HashMap;

public class MyAddressSteps extends FluentCucumberTest {

    @Then("I delete address \"([^\"]*)\"")
    public void i_delete_address(String addressToDelete) {
        if (myAddressPage.deleteAddress(addressToDelete)) {
            //Verify deletion of address works as expected
            assert (!myAddressPage.isAddressExisted(addressToDelete));
        }
    }

    @Then("I update address \"([^\"]*)\"")
    public void i_updates_address(String addressToUpdated) {
        String newAlias = myAddressPage.updateAddress(addressToUpdated);
        if (!Strings.isNullOrEmpty(newAlias)){
            //Verify updating address works as expected
            assert(myAddressPage.isAddressExisted(newAlias));
        }
    }

    @Then("I add an address from test data")
    public void i_add_an_address() {
        if (myAddressPage.addAddress(testData.addresses.get(0))){
            //Verify adding address works as expected
            assert(myAddressPage.isAddressExisted(testData.addresses.get(0).alias));
        }
    }

    @Then("I add all addresses from test data")
    public void i_add_all_address() {
        for (Address dataAddress: testData.addresses){
            if (myAddressPage.addAddress(dataAddress)){
                //Verify adding address works as expected
                assert(myAddressPage.isAddressExisted(dataAddress.alias));
            }
        }
    }
}
