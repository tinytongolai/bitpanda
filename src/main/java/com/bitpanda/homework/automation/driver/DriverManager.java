package com.bitpanda.homework.automation.driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class DriverManager {

    private static Logger log = Logger.getLogger(DriverManager.class.getName());

    private RemoteWebDriver driver;
    private TestProperties properties;

    public DriverManager(TestProperties testProperties) {
        properties = testProperties;
        if (driver == null) {
            initDrivers();
            driver = createDriver();
        }
        openLocationFromProperties();
        enterFullScreen();
    }

    public DriverManager() {
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public RemoteWebDriver createDriver() {
        if (properties.isRemote()) {
            try {
                URL url = new URL(properties.getRemoteUrl());
                driver = new RemoteWebDriver(url, properties.getCapabilities());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            String browserName = properties.getBrowserName();
            switch (browserName) {
                case "CHROME":
                    driver = new ChromeDriver();
                    break;
                case "FIREFOX":
                    driver = new FirefoxDriver();
                    break;
                case "IE":
                    driver = new InternetExplorerDriver();
                    break;
            }
        }
        return driver;
    }

    private void initDrivers() {
        DriversUtils.initDrivers(properties.getPlatform());
    }

    public void openLocationFromProperties() {
        driver.get(properties.getWebUrl());
    }

    public void enterFullScreen() {
        driver.manage().window().maximize();
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
