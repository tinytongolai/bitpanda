package com.bitpanda.homework.automation.driver;

import com.bitpanda.homework.automation.selenium.utils.Utils;
import com.bitpanda.homework.automation.selenium.utils.WaitAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class PageObject {

    protected DriverManager driverManager;

    public PageObject(DriverManager driverManager) {
        this.driverManager = driverManager;
        PageFactory.initElements(driverManager.getDriver(), this);
        Utils.waitForPageToLoad(driverManager.getDriver());
        synchronize();
    }

    public abstract void synchronize();

    // Methods reducing typing "driverManager.getDriver on each PageObject classes
    protected void waitForElement(WebElement element) {
        Utils.waitForElement(driverManager.getDriver(), element);
    }

    protected boolean waitUntil(WaitAction waitAction, String xpath) {
        return Utils.waitUntil(driverManager.getDriver(), waitAction, driverManager.getDriver().findElement(By.xpath(xpath)));
    }

    protected boolean waitUntil(WaitAction waitAction, WebElement element) {
        return Utils.waitUntil(driverManager.getDriver(), waitAction, element);
    }

    public void switchToFrameStartWithId(WebElement element) {
        driverManager.getDriver().switchTo().frame(element);
    }
}
