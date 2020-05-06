package com.ecommerce.page;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import java.util.concurrent.TimeUnit;
import static com.ecommerce.utility.MyTimeOut.*;

public abstract class Page extends FluentPage {

    public String getTextOfElement(FluentWebElement element) {
        // awaiting for element is clickable
        waitForElementClickable(element);
        // returning value of the element
        return element.value();
    }

    public void clickOnElement(FluentWebElement element) {
        // awaiting for element is clickable
        waitForElementDisplayed(element);
        // clicking on the element
        element.click();
    }

    public void typeOnElement(FluentWebElement element, String text) {
        // awaiting for element is clickable
        waitForElementClickable(element);
        // clicking on the element
        element.clear().fill().with(text);
    }

    public void selectOption(FluentWebElement element, String text) {
        element.fillSelect().withText(text);
    }

    public void waitForElementClickable(FluentWebElement element) {
        await().atMost(LARGE_TIME_OUT, TimeUnit.MILLISECONDS).until(element).clickable();
    }

    public void waitForElementDisplayed(FluentWebElement element) {
        await().atMost(MEDIUM_TIME_OUT,TimeUnit.MILLISECONDS).until(element).displayed();
    }

    public void waitForElementNotDisplayed(FluentWebElement element) {
        await().atMost(MEDIUM_TIME_OUT).until(element).not().displayed();
    }

    public void sleep (int duration){
        try{
            Thread.sleep(duration);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
