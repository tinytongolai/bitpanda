package com.bitpanda.homework.automation.selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class Utils {

    final static private long WAIT_TIME = 10;
    final static private long WITH_TIMEOUT = 30;
    final static private long POLLING_EVERY = 5;

    static public void waitForPageToLoad(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = driverLambda -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIME);
        wait.until(input -> expectation.apply(driver));
    }

    static public void waitForElement(WebDriver driver, WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(driver, WAIT_TIME);
        ExpectedCondition<Boolean> expectation = driverLambda -> {
            try {
                return element.isDisplayed();
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                return false;
            }
        };
        try {
            wait.until(input -> expectation.apply(driver));
        } catch (TimeoutException e) {
            throw new TimeoutException("Timeout exception: Element is not visible after " + WAIT_TIME + " seconds.", e);
        }
    }

    static public WebElement findElementByXpath(WebDriver driver, String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    static public WebElement findElementById(WebDriver driver, String id) {
        return driver.findElement(By.id(id));
    }

    static public WebElement findElementByName(WebDriver driver, String name) {
        return driver.findElement(By.name(name));
    }

    static public List<WebElement> findElementsByXpath(WebDriver driver, String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    static public boolean waitUntil(WebDriver driver, WaitAction waitAction, WebElement element) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(WITH_TIMEOUT));
        fluentWait.pollingEvery(Duration.ofMillis(POLLING_EVERY));
        fluentWait.ignoring(NoSuchElementException.class);

        Function<WebDriver, Boolean> func = driverLambda -> {
            switch (waitAction) {
                case ELEMENT_LOADER_DISABLE:
                    if (!(element.getAttribute("class").contains("loading") || element.getAttribute("class").contains("focus"))) {
                        return true;
                    }
                    break;
                case ELEMENT_LOADED:
                    if (element != null) {
                        return true;
                    }
                    break;
                case ELEMENT_VISIBLE:
                    if (element.getAttribute("class").contains("visible")) {
                        return true;
                    }
                    break;
                case ELEMENT_CLICKABLE:
                    if (element.isDisplayed() && element.isEnabled()) {
                        return true;
                    }
                    break;
                case COND_ELEMENT_CLICKABLE:
                    if ( ExpectedConditions.elementToBeClickable(element) != null ) {
                        return true;
                    }
                    break;
            }
            return false;
        };

        return fluentWait.until(func);
    }

    static public void selectByVisibleText(WebElement element, String text) {
        Select currentSelect = new Select(element);
        currentSelect.selectByVisibleText(text);
    }


    static public void selectByValue(WebElement element, String value) {
        Select currentSelect = new Select(element);
        currentSelect.selectByValue(value);
    }

    static public void check(WebElement element) {
        if ( ! element.isSelected() ) {
            element.click();
        }
    }

    static public void uncheck(WebElement element) {
        if ( element.isSelected() ) {
            element.click();
        }
    }
}
