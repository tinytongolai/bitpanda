package com.bitpanda.homework.automation.pageobject;

import com.bitpanda.homework.automation.driver.DriverManager;
import com.bitpanda.homework.automation.driver.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.bitpanda.homework.automation.selenium.utils.Utils.selectByVisibleText;

public class ProductCard extends PageObject {

    @FindBy(id = "view_full_size")
    private WebElement viewImage;

    @FindAll({
        @FindBy(xpath ="//li[starts-with(@id,'thumbnail')]")
    })
    private List<WebElement> thumbnails;

    @FindBy(xpath = "//h1[@itemprop='name']")
    private WebElement productName;

    @FindBy(id = "product_reference")
    private WebElement productReference;

    @FindBy(id = "product_condition")
    private WebElement productCondition;

    @FindBy(id = "short_description_block")
    private WebElement productShortDescription;

    @FindBy(id = "quantityAvailable")
    private WebElement quantityAvailable;

    // Typo ?
    @FindBy(id = "availability_statut")
    private WebElement availabilityStatus;

    @FindBy(id = "availability_value")
    private WebElement availabilityValue;

    @FindBy(id = "last_quantities")
    private WebElement lastQuantities;

    @FindBy(id = "availability_date")
    private WebElement availabilityDate;

    @FindBy(id = "oosHook")
    private WebElement outOfStock;

    @FindBy(xpath = "//button[contains(@class, 'btn-twitter']")
    private WebElement socialSharingTwitter;

    @FindBy(xpath = "//button[contains(@class, 'btn-facebook']")
    private WebElement socialSharingFacebook;

    @FindBy(xpath = "//button[contains(@class, 'btn-google-plus']")
    private WebElement socialSharingGooglePlus;

    @FindBy(xpath = "//button[contains(@class, 'btn-pinterest']")
    private WebElement socialSharingPinterest;

    @FindBy(id = "our_price_display")
    private WebElement displayPrice;

    @FindBy(id = "reduction_percent_display")
    private WebElement displayReductionPercent;

    @FindBy(id = "reduction_amount_display")
    private WebElement displayReductionAmount;

    @FindBy(id = "old_price_display")
    private WebElement displayOldPrice;

    @FindBy(id = "quantity_wanted")
    private WebElement wantedQuantity;

    @FindBy(xpath = "//a[contains(@class, 'product_quantity_down')]")
    private WebElement decreaseProductQuantity;

    @FindBy(xpath = "//a[contains(@class, 'product_quantity_up')]")
    private WebElement increaseProductQuantity;

    @FindBy(id = "minimal_quantity_wanted_p")
    private WebElement wantedMinimalQuantity;

    @FindBy(id = "minimal_quantity_label")
    private WebElement labelMinimalQuantity;

    @FindBy(id = "uniform-group_1")
    private WebElement uniformGroup1;
    @FindBy(id = "group_1")
    private WebElement group;

    @FindAll({
        @FindBy(xpath = "//ul[starts-with(@id,'color')]/li/a")
    })
    private List<WebElement> colorToPick;

    @FindBy(xpath = "//p[@id='add_to_cart']/button")
    private WebElement addToCart;

    @FindBy(id = "wishlist_button")
    private WebElement wishlist;

    public ProductCard(DriverManager driverManager) {
        super(driverManager);
    }


    @Override
    public void synchronize() {
    }

    public ProductCard typeWantedQuantity(Integer quantity) {
        try {
            wantedQuantity.clear();
            wantedQuantity.sendKeys(String.valueOf(quantity));
        } catch ( NoSuchElementException | StaleElementReferenceException se) {
            driverManager.getDriver().findElement(By.xpath("//input[@id='quantity_wanted']")).clear();
            driverManager.getDriver().findElement(By.xpath("//input[@id='quantity_wanted']")).sendKeys(String.valueOf(quantity));
        }
        return this;
    }

    public ProductCard increaseQuantity() {
        increaseProductQuantity.click();
        return this;
    }

    public ProductCard decreaseQuantity() {
        decreaseProductQuantity.click();
        return this;
    }

    public ProductCard selectSize(String size) {
        uniformGroup1.click();
        try {
            selectByVisibleText(group, size);
        } catch ( NoSuchElementException | StaleElementReferenceException sere) {
            RemoteWebDriver driver = driverManager.getDriver();
            Actions builder = new Actions(driver);
            builder.moveToElement(uniformGroup1)
                    .click()
                    .moveToElement(driverManager.getDriver().findElement(By.xpath(".//option[@title='"+size+"']")))
                    .click()
                    .build()
                    .perform();
        }

        return this;
    }

    public ProductCard chooseColor(String color) {
        WebElement pickedColor = colorToPick.stream().filter(item -> item.getAttribute("title").contentEquals(color))
                .findAny()
                .orElse(null);
        try {
            if (pickedColor == null)
                throw new RuntimeException("Chosen color doesn't exist");

            pickedColor.click();
        } catch ( StaleElementReferenceException sere) {
            RemoteWebDriver driver = driverManager.getDriver();
            Actions builder = new Actions(driver);
            builder.moveToElement(pickedColor)
                    .click()
                    .build()
                    .perform();
        }
        return this;
    }

    public ConfirmAddedItemsToCart addToCart() {
        addToCart.click();
        return new ConfirmAddedItemsToCart(driverManager);
    }

    public class ConfirmAddedItemsToCart extends PageObject {

        final private String XPATH_CONTINUE = ".//span[@title='Continue shopping']";
        final private String XPATH_PROCESS_TO_CHECKOUT = ".//a[@title='Proceed to checkout']";

        @FindBy(id = "layer_cart")
        WebElement confirmLayer;

        public ConfirmAddedItemsToCart(DriverManager driverManager) {
            super(driverManager);
        }

        @Override
        public void synchronize() {
            waitForElement(confirmLayer);
        }

        public void continueShopping() {
            WebElement weContinue= confirmLayer.findElement(By.xpath(XPATH_CONTINUE));
            waitForElement(confirmLayer);
            weContinue.click();
        }

        public void processCheckout() {
            WebElement weProceedCheckout = confirmLayer.findElement(By.xpath(XPATH_PROCESS_TO_CHECKOUT));
            waitForElement(weProceedCheckout);
            weProceedCheckout.click();
        }
    }
}
