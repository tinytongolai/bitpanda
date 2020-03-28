package com.bitpanda.homework.automation.pageobject;

import com.bitpanda.homework.automation.driver.DriverManager;
import com.bitpanda.homework.automation.driver.PageObject;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.bitpanda.homework.automation.selenium.utils.WaitAction.*;

public class NavContainer extends PageObject {

    @FindBy(xpath = "//a[@class='login']")
    private WebElement signIn;

    @FindBy(xpath = "//a[@class='logout']")
    private WebElement signOut;

    @FindBy(xpath = "//a[@class='account'][@title='View my customer account']")
    private WebElement customerAccount;

    public NavContainer(DriverManager driverManager) {
        super(driverManager);
    }

    @Override
    public void synchronize() {
    }

    public AuthenticationPage signIn() {
        try {
            waitForElement(signIn);
            signIn.click();
        } catch ( StaleElementReferenceException sere) {
            waitUntil(COND_ELEMENT_CLICKABLE, signIn);
            signIn.click();
        }
        return new AuthenticationPage(driverManager);
    }

    public MainPage signOut() {
        waitForElement(signOut);
        return new MainPage(driverManager);
    }

    public String getConnectedCustomer() {
        return customerAccount.getText();
    }
}
