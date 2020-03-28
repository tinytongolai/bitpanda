package com.bitpanda.homework.automation.pageobject;

import com.bitpanda.homework.automation.driver.DriverManager;
import com.bitpanda.homework.automation.pageobject.ProductCard.ConfirmAddedItemsToCart;

public class ProductView extends AutomationPracticeObject {

    protected ProductCard productCard;
    protected ConfirmAddedItemsToCart confirmAddedItemsToCart;

    public ProductView(DriverManager driverManager) {
        super(driverManager);
        productCard = new ProductCard(driverManager);
    }

    @Override
    public void synchronize() {
    }

    public ProductView typeWantedQuantity(Integer quantity) {
        productCard.typeWantedQuantity(quantity);
        return this;
    }

    public ProductView increaseQuantity() {
        productCard.increaseQuantity();
        return this;
    }

    public ProductView decreaseQuantity() {
        productCard.decreaseQuantity();
        return this;
    }

    public ProductView selectSize(String size) {
        productCard.selectSize(size);
        return this;
    }

    public ProductView chooseColor(String color) {
        productCard.chooseColor(color);
        return this;
    }

    public ConfirmAddedItemsToCart addToCart() {
        confirmAddedItemsToCart = productCard.addToCart();
        return confirmAddedItemsToCart;
    }

    public ProductView continueShopping() {
        confirmAddedItemsToCart.continueShopping();
        return new ProductView(driverManager);
    }

    public ShoppingCartSummaryPage proceedCheckout() {
        confirmAddedItemsToCart.processCheckout();
        return new ShoppingCartSummaryPage(driverManager);
    }

    public SearchResultPage searchProduct(String product) {
        this.headerPage.searchCardContainer.searchProduct(product);
        return new SearchResultPage(driverManager);
    }

    public Boolean validateProductNumberInCard(Integer number) {
        return this.headerPage.searchCardContainer.validateProductNumberInCard(number);
    }
}
