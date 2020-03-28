package com.bitpanda.homework.automation.test;

import com.bitpanda.homework.automation.driver.DriversUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.util.concurrent.TimeUnit;

public class SimpleTest {

    protected WebDriver instanceWebDriver;

    public SimpleTest() {
        this.instanceWebDriver = null;
    }

    @Parameters({"platform"})
    @BeforeSuite
    public void processDriverSettings(@Optional("SIERRA") String platform) {
        DriversUtils.initDrivers(Platform.valueOf(platform));
    }

    @Parameters({"browser"})
    @BeforeClass
    public void instanceDriver(@Optional("CHROME") String browser) {
        instanceWebDriver = createWebDriver(browser);
        instanceWebDriver.manage().window().maximize();
        instanceWebDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private WebDriver createWebDriver(String browser) {
        WebDriver webDriver = null;
        switch (browser) {
            case "CHROME":
                webDriver = new ChromeDriver();
                break;
            case "FIREFOX":
                webDriver = new FirefoxDriver();
                break;
            case "IE":
                webDriver = new InternetExplorerDriver();
                break;
        }
        return webDriver;
    }

    @AfterClass
    public void quitDriver() {
        if (instanceWebDriver == null)
            return;

        instanceWebDriver.quit();
    }

}
