package com.bitpanda.homework.automation.cucumber;

import com.bitpanda.homework.automation.driver.DriverManager;
import com.bitpanda.homework.automation.pageobject.MainPage;
import com.bitpanda.homework.automation.pageobject.AuthenticationPage;
import com.bitpanda.homework.automation.pageobject.ProductView;
import com.bitpanda.homework.automation.pageobject.SearchResultPage;
import com.bitpanda.homework.automation.pageobject.ShoppingCartSummaryPage;
import com.bitpanda.homework.automation.test.RunCucumberTest;
import io.cucumber.java8.En;
import org.testng.Assert;

public class BitPandaWorkflowStepdefs implements En {

    private final DriverManager driverManager = RunCucumberTest.getDriverManager();
    private MainPage mainPage;
    private AuthenticationPage authenticationPage;
    private ProductView productView;
    private SearchResultPage searchResultPage;
    private ShoppingCartSummaryPage shoppingCartSummaryPage;

    public BitPandaWorkflowStepdefs() {

        Given("^E2E User opens automationpractice page$", () -> {
            mainPage = new MainPage(driverManager);
        });

        Given("^E2E User chooses (\\d+)(th|st|nd|rd) product$", (Integer index, String rank) -> {
            productView = mainPage.chooseProductIndex(index-1);
        });

        And("^E2E User orders (\\d+) quantities$", (Integer quantity) -> {
            productView.typeWantedQuantity(quantity);
        });

        And("^E2E User chooses ([^\"]*) size$", (String size) -> {
            productView.selectSize("M");
        });

        And("^E2E User chooses ([^\"]*) color$", (String color) -> {
            productView.chooseColor(color);
        });

        And("^E2E User adds to cart$", () -> {
            productView.addToCart();
        });

        And("^E2E User wants to continue shopping$", () -> {
            productView.continueShopping();
        });

        And("^E2E User validates that his cart has (\\d+) Products$", (Integer number) -> {
            Assert.assertTrue(productView.validateProductNumberInCard(number));
        });

        And("^E2E User searches \"([^\"]*)\"$", (String searchProduct) -> {
            searchResultPage = productView.searchProduct(searchProduct);
        });

        And("^E2E User chooses (\\d+)(th|st|nd|rd) result product$", (Integer index, String rank) -> {
            productView = searchResultPage.chooseProductIndex(index-1);
        });

        And("^E2E User processes to checkout$", () -> {
            shoppingCartSummaryPage = productView.proceedCheckout();
            shoppingCartSummaryPage.displayProductDescription();
        });

        And("^E2E User processes to checkout - Summary$", () -> {
            shoppingCartSummaryPage.processedToCheckoutSummary();
        });

        // Todo: create parametrized Gerhking
        And("^E2E User logs in$", () -> {
            authenticationPage = new AuthenticationPage(driverManager);
            shoppingCartSummaryPage = authenticationPage.typeRegisteredAccount("johndoe@underthebridge.com", "12345");
        });

        And("^E2E User processes to checkout - Address", () -> {
            shoppingCartSummaryPage.proceedToCheckoutAddress();
        });

        And("^E2E User processes to checkout - Shipping", () -> {
            shoppingCartSummaryPage.proceedToCheckoutShipping();
        });

        And("^E2E User pays by \"([^\"]*)\"$", (String paymentType) -> {
            shoppingCartSummaryPage.payment(paymentType);
        });

        And("^E2E User confirms order", () -> {
            shoppingCartSummaryPage.confirmOrder();
            Assert.assertEquals(shoppingCartSummaryPage.getOrderCompletionDescription(), "ORDER CONFIRMATION");
        });
    }
}
