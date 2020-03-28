package com.bitpanda.homework.automation.test;

import com.bitpanda.homework.automation.driver.DriverManager;
import com.bitpanda.homework.automation.driver.TestProperties;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@CucumberOptions(
    features = "src/test/resources/features/io/cucumber/testng/automationpractice-account.feature",
    glue = {"com.bitpanda.homework.automation.cucumber"},
    plugin = {
            "pretty",
            "html:target/cucumber-reports/cucumber-pretty",
            "json:target/cucumber-reports/json-reports/CucumberTestReport.json",
            "rerun:target/cucumber-reports/rerun-reports/rerun.txt"
    }
    //, tags = {"~@Ignore"}
)
public class RunCucumberTestAccount extends AbstractTestNGCucumberTests {

    // Hook not to override methods of each PageObject class
    private static DriverManager driverManager;
    public static DriverManager getDriverManager() {
        if ( driverManager == null) {
            throw new RuntimeException();
        }
        return driverManager;
    }

    // preparation for parallel tests
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @Parameters({"browser", "platform", "run", "webUrl", "remoteUrl"})
    @BeforeClass
    public void init(@Optional("CHROME") String browser,
                     @Optional("SIERRA") String platform,
                     @Optional("LOCAL") String run,
                     @Optional("http://automationpractice.com/index.php") String webUrl,
                     @Optional("http://localhost:4444/wd/hub") String remoteUrl) {
        TestProperties properties = new TestProperties();
        properties.setRemoteUrl(remoteUrl);
        properties.setWebUrl(webUrl);
        properties.setRun(run);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setPlatform(Platform.valueOf(platform));
        properties.setCapabilities(capabilities);
        driverManager = new DriverManager(properties);
    }

    @AfterClass
    public void reset() {
        driverManager.quit();
    }

}
