package com.bitpanda.homework.automation.pageobject;

import com.bitpanda.homework.automation.driver.DriverManager;
import com.bitpanda.homework.automation.driver.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchCartContainer extends PageObject {

    @FindBy(id = "search_query_top")
    private WebElement search;
    @FindBy(xpath = "//button[@name='submit_search']")
    private WebElement submitSearch;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    private WebElement viewCart;

    @FindBy(xpath = "//div[@class='ac_results')][contains(@style, 'display: block]")
    private WebElement resultContainer;
    @FindAll({
        @FindBy(xpath = "//div[@class='ac_results']//li")
    })
    private List<WebElement> resultSearch;

    public SearchCartContainer(DriverManager driverManager) {
        super(driverManager);
    }

    @Override
    public void synchronize() {

    }

    public SearchResultPage searchProduct(String searchProduct) {
        search.sendKeys(searchProduct);
        submitSearch.click();

        return new SearchResultPage(driverManager);
    }

    public Boolean validateProductNumberInCard(Integer number) {
        return (viewCart.getText().contains(String.valueOf(number)) ? true : false);
    }
}
