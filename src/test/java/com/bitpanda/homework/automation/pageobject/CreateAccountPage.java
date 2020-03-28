package com.bitpanda.homework.automation.pageobject;

import com.bitpanda.homework.automation.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.bitpanda.homework.automation.selenium.utils.Utils.check;
import static com.bitpanda.homework.automation.selenium.utils.Utils.selectByValue;
import static com.bitpanda.homework.automation.selenium.utils.Utils.selectByVisibleText;
import static com.bitpanda.homework.automation.selenium.utils.Utils.uncheck;

public class CreateAccountPage extends AutomationPracticeObject {

    @FindBy(xpath = "//a[@class='home']/following-sibling::span[@class='navigation_page' and contains(text(),'Authentication')]")
    private WebElement navigationFlag;

    @FindBy(xpath = "//h1[@class='page-heading'][text()='Create an account']")
    private WebElement title;

    @FindBy(id = "id_gender1")
    private WebElement genderM;
    @FindBy(id = "id_gender2")
    private WebElement genderF;
    @FindBy(id = "customer_firstname")
    private WebElement firstname;
    @FindBy(id = "customer_lastname")
    private WebElement lastname;
    @FindBy(id = "email")
    private WebElement email;
    @FindBy(id = "passwd")
    private WebElement passwd;
    @FindBy(id = "uniform-days")
    private WebElement uniformDays;
    @FindBy(name = "days")
    private WebElement dob_days;
    @FindBy(id = "uniform-months")
    private WebElement uniformMonths;
    @FindBy(id = "months")
    private WebElement dob_months;
    @FindBy(id = "uniform-years")
    private WebElement uniformYears;
    @FindBy(id = "years")
    private WebElement dob_years;
    @FindBy(id = "uniform-newsletter")
    private WebElement signForNews;
    @FindBy(id = "uniform-optin")
    private WebElement receiveOffers;

    @FindBy(id = "firstname")
    private WebElement addressFirstname;
    @FindBy(id = "lastname")
    private WebElement addressLastname;
    @FindBy(id = "company")
    private WebElement addressCompany;
    @FindBy(id = "address1")
    private WebElement addressAddress1;
    @FindBy(id = "address2")
    private WebElement addressAddress2;
    @FindBy(id = "city")
    private WebElement addressCity;
    @FindBy(id = "uniform-id_state")
    private WebElement uniformIdState;
    @FindBy(id = "id_state")
    private WebElement addressState;
    @FindBy(id = "postcode")
    private WebElement addressPostcode;
    @FindBy(id = "uniform-id_country")
    private WebElement uniformIdCountry;
    @FindBy(id = "id_country")
    private WebElement addressCountry;
    @FindBy(id = "other")
    private WebElement addressOther;
    @FindBy(id = "phone")
    private WebElement addressHomePhone;
    @FindBy(id = "phone_mobile")
    private WebElement addressMobilePhone;
    @FindBy(id = "alias")
    private WebElement addressAlias;

    @FindBy(id = "submitAccount")
    private WebElement submitAccount;

    public CreateAccountPage(DriverManager driverManager) {
        super(driverManager);
    }

    @Override
    public void synchronize() {
        super.synchronize();
        waitForElement(navigationFlag);
        waitForElement(title);
        waitForElement(firstname);
        waitForElement(lastname);
        waitForElement(email);
        waitForElement(passwd);
        waitForElement(signForNews);
        waitForElement(receiveOffers);

        waitForElement(addressFirstname);
        waitForElement(addressLastname);
        waitForElement(addressCompany);
        waitForElement(addressAddress1);
        waitForElement(addressAddress2);
        waitForElement(addressCity);
        waitForElement(addressPostcode);
        waitForElement(addressOther);
        waitForElement(addressHomePhone);
        waitForElement(addressMobilePhone);
        waitForElement(addressAlias);

        waitForElement(submitAccount);
    }

    public CreateAccountPage clickGender(String gender) {
        if (gender.equals("M")) {
            genderM.click();
        }
        else if (gender.equals("F")) {
            genderF.click();
        }

        return this;

    }

    public CreateAccountPage typeFirstname(String firstname) {
        this.firstname.sendKeys(firstname);

        return this;
    }

    public CreateAccountPage typeLastname(String lastname) {
        this.lastname.sendKeys(lastname);

        return this;
    }

    public CreateAccountPage typeEmail(String email) {
        this.email.sendKeys(email);

        return this;
    }

    public CreateAccountPage typePassword(String passwd) {
        this.passwd.sendKeys(passwd);

        return this;
    }

    public CreateAccountPage selectDayOfDOB(String dob_days) {
        uniformDays.click();
        selectByValue(this.dob_days, dob_days);

        return this;
    }
    public CreateAccountPage selectMonthOfDOB(String dob_months) {
        uniformMonths.click();
        selectByValue(this.dob_months, dob_months);

        return this;
    }

    public CreateAccountPage selectYearOfDOB(String dob_years) {
        uniformYears.click();
        selectByValue(this.dob_years, dob_years);

        return this;
    }

    public CreateAccountPage checkSignForNews() {
        check(this.signForNews);

        return this;
    }

    public CreateAccountPage uncheckSignForNews() {
        uncheck(signForNews);

        return this;
    }

    public CreateAccountPage checkReceiveOffers() {
        check(receiveOffers);

        return this;
    }

    public CreateAccountPage uncheckReceiveOffers() {
        uncheck(receiveOffers);

        return this;
    }

    public CreateAccountPage typeAddrFirstname(String addressFirstname) {
        this.addressFirstname.sendKeys(addressFirstname);

        return this;
    }
    public CreateAccountPage typeAddrLastname(String addressLastname) {
        this.addressLastname.sendKeys(addressLastname);

        return this;
    }
    public CreateAccountPage typeAddrCompany(String addressCompany) {
        this.addressCompany.sendKeys(addressCompany);

        return this;
    }

    public CreateAccountPage typeAddrAddress1(String addressAddress1) {
        this.addressAddress1.sendKeys(addressAddress1);

        return this;
    }

    public CreateAccountPage typeAddrAddress2(String addressAddress2) {
        this.addressAddress2.sendKeys(addressAddress2);

        return this;
    }
    public CreateAccountPage typeAddrCity(String addressCity) {
        this.addressCity.sendKeys(addressCity);

        return this;
    }

    public CreateAccountPage selectAddrState(String addressState) {
        uniformIdState.click();
        selectByVisibleText(this.addressState, addressState);

        return this;
    }

    public CreateAccountPage typeAddrPostcode(String addressPostcode) {
        this.addressPostcode.sendKeys(addressPostcode);

        return this;
    }

    public CreateAccountPage selectAddrCountry(String addressCountry) {
        uniformIdCountry.click();
        selectByVisibleText(this.addressCountry, addressCountry);

        return this;
    }

    public CreateAccountPage typeAddrOther(String addressOther) {
        this.addressOther.sendKeys(addressOther);

        return this;
    }

    public CreateAccountPage typeAddrHomePhone(String addressHomePhone){
        this.addressHomePhone.sendKeys(addressHomePhone);

        return this;
    }

    public CreateAccountPage typeAddrMobilePhone(String addressMobilePhone) {
        this.addressMobilePhone.sendKeys(addressMobilePhone);

        return this;
    }

    public CreateAccountPage typeAddrAlias(String addressAlias) {
        this.addressAlias.clear();
        this.addressAlias.sendKeys(addressAlias);

        return this;
    }

    public CustomerAccountPage clickSubmitAccount() {
        this.submitAccount.click();

        return new CustomerAccountPage(driverManager);
    }
}
