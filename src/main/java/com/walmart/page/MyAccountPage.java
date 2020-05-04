package com.walmart.page;

import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends EComBasePage {
    @FindBy(css="p.info-account")
    public FluentWebElement myaccountWelcome;

    @FindBy(css="h1")
    public FluentWebElement pageHeader;

    public String GetPageHeaderValue() {
        return pageHeader.text();
    }

    public String GetMyccountWelcomeValue() {
        return myaccountWelcome.text();
    }

    public void selectOption(String optionToClick) {
        FluentList<FluentWebElement> myaccountLinkList = $("ul.myaccount-link-list > li");
        for (FluentWebElement webEle : myaccountLinkList) {
            if (webEle.text().toLowerCase().equals(optionToClick.toLowerCase())) {
                clickOnElement(webEle);
                break;
            }
        }
    }
}
