package com.ecommerce.page;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

public abstract class EComBasePage extends Page {
    /*
     * Common WebElements that should be available to all Page Objects
     */

    @FindBy(css="div.header_user_info > a.account")
    public FluentWebElement accountElement;

    @FindBy(css="div.header_user_info > a.logout")
    public FluentWebElement logoutElement;

    @FindBy(id="contact-link")
    public FluentWebElement contactElement;

    @FindBy(css="div.header_user_info > a.login")
    public FluentWebElement loginElement;

    @FindBy(xpath="//a[@title='Manage my customer account']")
    public FluentWebElement myaccountElement;

    public String GetAccountValue(){
        return accountElement.text();
    }

    public void ClickMyAccount() {
        clickOnElement(myaccountElement);
    }

    public void LogoutSystem() {
        clickOnElement(logoutElement);
    }
}
