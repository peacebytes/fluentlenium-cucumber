package com.ecommerce.page;

import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

@PageUrl("/index.php?controller=authentication&back=my-account")
public class LoginPage extends EComBasePage {
    @FindBy(id="email")
    public FluentWebElement loginEmailField;

    @FindBy(id="passwd")
    public FluentWebElement loginPasswordField;

    @FindBy(id="SubmitLogin")
    public FluentWebElement buttonToLogin;

    @FindBy(css="#login_form > h3.page-subheading")
    public FluentWebElement alreadyRegistered;

    public boolean LoginButtonIsVisible() {
        waitForElementDisplayed(buttonToLogin);
        return buttonToLogin.present();
    }

    public void login(String email, String password) {
        clickOnElement(loginEmailField);
        typeOnElement(loginEmailField, email);
        typeOnElement(loginPasswordField, password);
        clickOnElement(buttonToLogin);
    }

    public String GetAlreadyRegisteredValue(){
        return alreadyRegistered.text();
    }
}
