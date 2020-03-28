Feature: AutomationPractice Homework - account
  As a candidate for BitPanda
  I am implement Login scenario(s)
  So that I can show my skills

  Background:
    Given End User opens automationpractice page

  Scenario: Create AutomationPractice Account
    Given End User clicks on Sign In
    And End User types his email to create his account
    And End User checks Mr.
    And End User types John as first name
    And End User types Doe as last name
    And End User types 12345 as password
    And End User selects 1 as Day of birth
    And End User selects 1 as Months of birth
    And End User selects 2020 as Year of birth
    And End User types underthebridge as address
    And End User types Brige as city
    And End User selects Texas as state
    And End User types 14000 as postcode
    And End User types 123456789 as mobilephone
    And End User types underthebridge as address alias
    When End User submit the registration form
    Then End User access to his customer account
