package com.bitpanda.homework.automation.pageobject;

import com.bitpanda.homework.automation.driver.DriverManager;
import com.bitpanda.homework.automation.driver.PageObject;

public class FooterPage extends PageObject
{
    protected NewsContainer newsContainer;
    protected BlocksContainer blocksContainer;

    public FooterPage(DriverManager driverManager) {
        super(driverManager);
        newsContainer = new NewsContainer(driverManager);
        blocksContainer = new BlocksContainer(driverManager);
    }

    @Override
    public void synchronize() {

    }
}
