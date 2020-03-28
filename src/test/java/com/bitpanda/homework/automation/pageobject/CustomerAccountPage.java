package com.bitpanda.homework.automation.pageobject;

import com.bitpanda.homework.automation.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CustomerAccountPage extends AutomationPracticeObject {

    @FindBy(xpath = "//a[@class='home']/following-sibling::span[@class='navigation_page' and contains(text(),'My account')]")
    private WebElement navigationFlag;

    @FindBy(xpath = "//h1[@class='page-heading'][text()='My account']")
    private WebElement title;

    @FindBy(xpath = "//p[@class='info-account']")
    private WebElement info;

    @FindAll({
            @FindBy(xpath = "//ul[@class='myaccount-link-list']/li")
    })
    private List<WebElement> links;


    @FindBy(xpath = "//a[contains(@title, \"Home\")]")
    private WebElement home;

    public CustomerAccountPage(DriverManager driverManager) {
        super(driverManager);
    }

    @Override
    public void synchronize() {
        super.synchronize();
        waitForElement(navigationFlag);
        waitForElement(title);

        waitForElement(home);
    }

    public String getConnectedCustomer() {
        return headerPage.navContainer.getConnectedCustomer();
    }
}
