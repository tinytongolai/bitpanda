package com.bitpanda.homework.automation.pageobject;

import com.bitpanda.homework.automation.driver.DriverManager;
import com.bitpanda.homework.automation.driver.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EntryPage extends PageObject {

    @FindBy(xpath = "//ul[@id='homefeatured' or @id='blockbestsellers'][contains(@class, 'active')]//li[contains(@class, 'ajax_block_product')]")
    private List<WebElement> products;

    public EntryPage(DriverManager driverManager) {
        super(driverManager);
    }

    @Override
    public void synchronize() {

    }

    public ProductView chooseProduct(int index) {
        products.get(index).click();
        return new ProductView(driverManager);
    }
}
