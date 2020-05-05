package com.walmart.page;

import static com.walmart.utility.TestData.*;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;

import static org.fluentlenium.core.filter.FilterConstructor.withText;

public class MyAddressPage extends EComBasePage {
    @FindBy(xpath="//a[@title='Add an address']")
    public FluentWebElement addAddressButton;

    @FindBy(id="address1")
    public FluentWebElement address1;

    @FindBy(id="city")
    public FluentWebElement city;

    @FindBy(id="id_state")
    public FluentWebElement id_state;

    @FindBy(id="postcode")
    public FluentWebElement postcode;

    @FindBy(id="id_country")
    public FluentWebElement id_country;

    @FindBy(id="phone")
    public FluentWebElement phone;

    @FindBy(id="alias")
    public FluentWebElement alias;

    @FindBy(id="submitAddress")
    public FluentWebElement submitAddressButton;

    public FluentList<FluentWebElement> addressesList() {
        return $("div.address");
    }

    public FluentList<FluentWebElement> getAddressDetails(FluentWebElement parentWebElement) {
        return parentWebElement.$("ul > li");
    }

    public FluentWebElement getUpdateButton(FluentWebElement parentWebElement) {
        return parentWebElement.$(By.xpath("a[@title='Update']")).first();
    }

    public FluentWebElement getDeleteButton(FluentWebElement parentWebElement) {
        return parentWebElement.$(By.xpath("a[@title='Delete']")).first();
    }

    //Return false if no address found; otherwise, return true
    public Boolean isAddressExisted(String addressName) {
        Boolean flag = Boolean.FALSE;
        for (FluentWebElement webEle : addressesList()) {
            String addressDetails = getAddressDetails(webEle).first().text().toLowerCase();
            System.out.println("seeing: " + addressDetails);
            if (addressDetails.equals(addressName.toLowerCase())) {
                flag = Boolean.TRUE;
            }
        }
        return flag;
    }

    //Return false if no address found for delete; return true if deletion is complete
    public Boolean deleteAddress(String addressName) {
        Boolean flag = Boolean.FALSE;
        for (FluentWebElement webEle : addressesList()) {
            FluentList<FluentWebElement> addressDetails = getAddressDetails(webEle);
            String foundAddressName = addressDetails.first().text().toLowerCase();
            System.out.println("should I delete: " + foundAddressName);
            if (foundAddressName.toLowerCase().equals(addressName.toLowerCase())) {
                FluentWebElement btnDelete = getDeleteButton(addressDetails.get(addressDetails.size()-1));
                clickOnElement(btnDelete);
                alert().accept();
                flag = Boolean.TRUE;
                break;
            }
        }
        return flag;
    }

    //Return null if no address found for update; return new alias if update is complete
    public String updateAddress(String addressName) {
        String newAlias = null;
        //Looking for address to be updated
        for (FluentWebElement webEle : addressesList()) {
            FluentList<FluentWebElement> addressDetails = getAddressDetails(webEle);
            String foundAddressName = addressDetails.get(0).text().toLowerCase();
            if (foundAddressName.toLowerCase().equals(addressName.toLowerCase())) {
                FluentWebElement btnUpdate = getUpdateButton(addressDetails.get(addressDetails.size()-1));
                clickOnElement(btnUpdate);
                //Updating
                typeOnElement(address1, address1.value()+"Updated");
                typeOnElement(city, city.value()+"Updated");
                newAlias = alias.value()+"Updated";
                typeOnElement(alias, newAlias);
                clickOnElement(submitAddressButton);
                break;
            }
        }
        return newAlias;
    }

    //Return false if address is already existed; return true if addition is complete
    public Boolean addAddress(Address dataAddress) {
        Boolean flag = isAddressExisted(dataAddress.alias);
        if (!flag){
            clickOnElement(addAddressButton);
            typeOnElement(address1, dataAddress.address1);
            typeOnElement(city, dataAddress.city);
            selectOption(id_state, dataAddress.state);
            typeOnElement(postcode, dataAddress.postcode);
            selectOption(id_country, dataAddress.country);
            typeOnElement(phone, dataAddress.phone);
            typeOnElement(alias, dataAddress.alias);
            clickOnElement(submitAddressButton);
        }
        return !flag;
    }
}
