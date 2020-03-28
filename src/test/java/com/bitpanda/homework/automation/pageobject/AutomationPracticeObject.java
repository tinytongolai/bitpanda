package com.bitpanda.homework.automation.pageobject;

import com.bitpanda.homework.automation.driver.DriverManager;
import com.bitpanda.homework.automation.driver.PageObject;

public class AutomationPracticeObject extends PageObject {

    protected HeaderPage headerPage;
    protected FooterPage footerPage;

    public AutomationPracticeObject(DriverManager driverManager) {
        super(driverManager);
        headerPage = new HeaderPage(driverManager);
        footerPage = new FooterPage(driverManager);
    }

    @Override
    public void synchronize() {

    }
}
