package com.walmart.section;

import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.components.ComponentInstantiator;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddToCartConfirmation extends FluentWebElement {

    @FindBy(css = "[data-automation=continue-shopping]")
    public FluentWebElement continueShoppingButton;

    @FindBy(css = "[data-automation=quantity] input")
    public FluentWebElement quantity;

    public AddToCartConfirmation(WebElement element, FluentControl control, ComponentInstantiator instantiator) {
        super(element, control, instantiator);
    }
}
