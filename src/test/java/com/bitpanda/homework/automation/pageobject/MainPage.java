package com.bitpanda.homework.automation.pageobject;

import com.bitpanda.homework.automation.driver.DriverManager;

public class MainPage extends AutomationPracticeObject {

    protected EntryPage entryPage;

    public MainPage(DriverManager driverManager) {
        super(driverManager);
    }

    @Override
    public void synchronize() {
        super.synchronize();
        entryPage = new EntryPage(driverManager);
    }

    public AuthenticationPage clickSignIn() {
        return headerPage.navContainer.signIn();
    }

    public ProductView chooseProductIndex(Integer index) {
        this.entryPage.chooseProduct(index);
        return new ProductView(driverManager);
    }
}
