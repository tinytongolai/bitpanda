package com.bitpanda.homework.automation.pageobject;

import com.bitpanda.homework.automation.driver.DriverManager;
import com.bitpanda.homework.automation.driver.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthenticationPage extends PageObject {

    @FindBy(xpath = "//a[@class='home']/following-sibling::span[@class='navigation_page' and contains(text(),'Authentication')]")
    private WebElement navigationFlag;

    @FindBy(xpath = "//h1[@class='page-heading'][text()='Authentication']")
    private WebElement title;

    @FindBy(id = "email_create")
    private WebElement createEmail;
    @FindBy(id = "SubmitCreate")
    private WebElement submitCreate;

    @FindBy(id = "email")
    private WebElement registeredEmail;
    @FindBy(id = "passwd")
    private WebElement passwordEmail;
    @FindBy(id = "SubmitLogin")
    private WebElement signIn;

    public AuthenticationPage(DriverManager driverManager) {
        super(driverManager);
    }

    @Override
    public void synchronize() {
        waitForElement(createEmail);
        waitForElement(submitCreate);
        waitForElement(registeredEmail);
        waitForElement(passwordEmail);
        waitForElement(signIn);
    }

    public AuthenticationPage typeCreateEmail(String email) {
        createEmail.sendKeys(email);
        return this;
    }

    public CreateAccountPage submitCreateEmail() {
        submitCreate.click();
        return new CreateAccountPage(driverManager);
    }

    public ShoppingCartSummaryPage typeRegisteredAccount(String email, String passwd) {
        registeredEmail.sendKeys(email);
        passwordEmail.sendKeys(passwd);
        signIn.click();
        return new ShoppingCartSummaryPage(driverManager);
    }
}
