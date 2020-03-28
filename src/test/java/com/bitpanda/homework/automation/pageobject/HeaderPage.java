package com.bitpanda.homework.automation.pageobject;

import com.bitpanda.homework.automation.driver.DriverManager;
import com.bitpanda.homework.automation.driver.PageObject;

public class HeaderPage extends PageObject
{
    protected BannerContainer bannerContainer;
    protected NavContainer navContainer;
    protected SearchCartContainer searchCardContainer;
    protected CategoryContainer categoryContainer;

    public HeaderPage(DriverManager driverManager) {
        super(driverManager);
        bannerContainer = new BannerContainer(driverManager);
        navContainer = new NavContainer(driverManager);
        searchCardContainer = new SearchCartContainer(driverManager);
        categoryContainer = new CategoryContainer(driverManager);
    }

    @Override
    public void synchronize() {

    }

    public AuthenticationPage clickSignIn() {
        return navContainer.signIn();
    }
    public String getConnectedCustomer() {
        return navContainer.getConnectedCustomer();
    }
}
