package com.bitpanda.homework.automation.pageobject;

import com.bitpanda.homework.automation.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

// Class covering all the checkout phases
// it would be nicer to split down
public class ShoppingCartSummaryPage extends AutomationPracticeObject {

    @FindAll({
        @FindBy(xpath = "//tr[starts-with(@id, 'product')]")
    })
    private List<WebElement> products;

    @FindBy(xpath = "//p[contains(@class,'cart_navigation')]//a[@title='Proceed to checkout']")
    private WebElement checkoutSummary;

    @FindBy(xpath = "//button[@name='processAddress']")
    private WebElement checkoutAddress;

    @FindBy(id = "cgv")
    private WebElement termsOfService;

    @FindBy(xpath = "//button[@name='processCarrier']")
    private WebElement checkoutShipping;

    @FindBy(xpath = "//p[contains(@class,'cart_navigation')]//button")
    private WebElement confirmOrder;

    @FindBy(xpath = "//h1[@class='page-heading']")
    private WebElement orderConfirmation;

    public ShoppingCartSummaryPage(DriverManager driverManager) {
        super(driverManager);
    }

    @Override
    public void synchronize() {
        super.synchronize();
    }

    public void displayProductDescription() {
        products.stream().forEach(p -> System.out.println(p.findElement(By.xpath("./td[@class='cart_description']")).getText()));
    }

    public ShoppingCartSummaryPage decreaseQuantity(Integer rowProduct) {
        if (products.size() == 0 )
            throw new RuntimeException("Bug ? No items added to the cart");
        if (rowProduct == 0 || rowProduct > products.size())
            throw new RuntimeException("We can't do that");

        WebElement weRowProduct = products.get(rowProduct-1);
        weRowProduct.findElement(By.xpath(".//a[starts-with(@id, cart_quantity_down)]")).click();

        return this;
    }

    public ShoppingCartSummaryPage increaseQuantity(Integer rowProduct) {
        if (products.size() == 0 )
            throw new RuntimeException("Bug ? No items added to the cart");
        if (rowProduct == 0 || rowProduct > products.size())
            throw new RuntimeException("We can't do that");

        WebElement weRowProduct = products.get(rowProduct-1);
        weRowProduct.findElement(By.xpath(".//a[starts-with(@id, cart_quantity_up)]")).click();

        return this;
    }

    public ShoppingCartSummaryPage deleteProduct(Integer rowProduct) {
        if (products.size() == 0 )
            throw new RuntimeException("Bug ? No items added to the cart");
        if (rowProduct == 0 || rowProduct > products.size())
            throw new RuntimeException("We can't do that");

        WebElement weRowProduct = products.get(rowProduct-1);
        weRowProduct.findElement(By.xpath(".//a[@class='cart_quantity_delete']")).click();

        return this;
    }

    public ShoppingCartSummaryPage processedToCheckoutSummary() {
        waitForElement(checkoutSummary);
        checkoutSummary.click();
        return this;
    }

    public ShoppingCartSummaryPage proceedToCheckoutAddress() {
        checkoutAddress.click();
        return this;
    }

    public ShoppingCartSummaryPage proceedToCheckoutShipping() {
        if ( !termsOfService.isSelected())
            termsOfService.click();

        checkoutShipping.click();
        return this;
    }

    // Todo: handle with Enum ?
    public ShoppingCartSummaryPage payment(String payment) {
        driverManager.getDriver().findElement(By.xpath("//a[@class='" + payment + "']")).click();
        return this;
    }

    public void confirmOrder() {
        confirmOrder.click();
    }

    public String getOrderCompletionDescription() {
        return orderConfirmation.getText();
    }
}
