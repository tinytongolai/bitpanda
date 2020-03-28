package com.bitpanda.homework.automation.cucumber;

import com.bitpanda.homework.automation.driver.DriverManager;
import com.bitpanda.homework.automation.pageobject.AuthenticationPage;
import com.bitpanda.homework.automation.pageobject.CreateAccountPage;
import com.bitpanda.homework.automation.pageobject.CustomerAccountPage;
import com.bitpanda.homework.automation.pageobject.MainPage;
import com.bitpanda.homework.automation.test.RunCucumberTest;
import io.cucumber.java8.En;
import org.testng.Assert;

public class BitPandaStepdefs implements En {

    private final DriverManager driverManager = RunCucumberTest.getDriverManager();

    private MainPage mainPage;
    private AuthenticationPage authenticationPage;
    private CreateAccountPage createAccountPage;
    private CustomerAccountPage customerAccountPage;

    private String currentEmailLogin;
    private String firstname;
    private String lastname;

    public BitPandaStepdefs() {

        Given("^End User opens automationpractice page$", () -> {
            mainPage = new MainPage(driverManager);
        });


        Given("^End User clicks on Sign In$", () -> {
            authenticationPage = mainPage.clickSignIn();
        });


        And("^End User types his email to create his account$", () -> {
            long epoch = System.currentTimeMillis()/1000;
            currentEmailLogin = String.valueOf(epoch) + "@underthebridge.com";
            createAccountPage = authenticationPage.typeCreateEmail(currentEmailLogin).submitCreateEmail();
        });

        And("^End User checks Mr\\.$", () -> {
            createAccountPage.clickGender("M");
        });

        And("^End User types ([^\"]*) as first name$", (String firstname) -> {
            createAccountPage.typeFirstname(firstname);
            this.firstname = firstname;
        });
        And("^End User types ([^\"]*) as last name$", (String lastname) -> {
            createAccountPage.typeLastname(lastname);
            this.lastname = lastname;
        });
        And("^End User types ([^\"]*) as password$", (String password) -> {
            createAccountPage.typePassword(password);
        });
        And("^End User selects (\\d+) as Day of birth$", (Integer day) -> {
            createAccountPage.selectDayOfDOB(String.valueOf(day));
        });
        And("^End User selects (\\d+) as Months of birth$", (Integer month) -> {
            createAccountPage.selectMonthOfDOB(String.valueOf(month));
        });
        And("^End User selects (\\d+) as Year of birth$", (Integer year) -> {
            createAccountPage.selectYearOfDOB(String.valueOf(year));
        });
        And("^End User types ([^\"]*) as address$", (String address) -> {
            createAccountPage.typeAddrAddress1(address);
        });
        And("^End User types ([^\"]*) as city$", (String city) -> {
            createAccountPage.typeAddrCity(city);
        });
        And("^End User selects ([^\"]*) as state$", (String state) -> {
            createAccountPage.selectAddrState(state);
        });
        And("^End User types ([^\"]*) as postcode$", (String postcode) -> {
            createAccountPage.typeAddrPostcode(postcode);
        });
        And("^End User types (\\d+) as mobilephone$", (Integer mobilephone) -> {
            createAccountPage.typeAddrMobilePhone(String.valueOf(mobilephone));
        });
        And("^End User types ([^\"]*) as address alias$", (String alias) -> {
            createAccountPage.typeAddrAlias(alias);
        });

        When("^End User submit the registration form$", () -> {
            customerAccountPage = createAccountPage.clickSubmitAccount();
        });

        Then("^End User access to his customer account$", () -> {
            Assert.assertEquals(customerAccountPage.getConnectedCustomer(), firstname + " " + lastname);
        });

    }

}
