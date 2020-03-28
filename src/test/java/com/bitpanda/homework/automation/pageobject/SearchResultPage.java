package com.bitpanda.homework.automation.pageobject;

import com.bitpanda.homework.automation.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultPage extends AutomationPracticeObject {

    @FindBy(xpath = "//ul[contains(@class,'product_list')]/li[contains(@class, 'ajax_block_product')]")
    private List<WebElement> products;


    public SearchResultPage(DriverManager driverManager) {
        super(driverManager);
    }

    @Override
    public void synchronize() {
        super.synchronize();
    }

    public ProductView chooseProductIndex(int index) {
        RemoteWebDriver driver = driverManager.getDriver();
        Actions builder = new Actions(driver);
        builder.moveToElement(products.get(index))
                .moveToElement(driver.findElement(By.xpath("//a[@title='View']")))
                .click()
                .build()
                .perform();
        return  new ProductView(driverManager);
    }
}
