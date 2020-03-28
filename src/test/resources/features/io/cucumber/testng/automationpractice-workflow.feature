Feature: AutomationPractice Homework - workflow
  As a candidate for BitPanda
  I am implement End-2-End Workflow scenario(s)
  So that I can show my skills

  Background:
    Given E2E User opens automationpractice page

  Scenario: AutomationPractice Workflow - cheque End to End
    Given E2E User chooses 5th product
    And E2E User orders 2 quantities
    And E2E User chooses M size
    And E2E User chooses Blue color
    And E2E User adds to cart
    And E2E User wants to continue shopping
    And E2E User validates that his cart has 2 Products
    And E2E User searches "Evening Dresses > Printed Dress"
    And E2E User chooses 1st result product
    And E2E User orders 2 quantities
    And E2E User adds to cart
    And E2E User processes to checkout
    And E2E User processes to checkout - Summary
    And E2E User logs in
    And E2E User processes to checkout - Address
    And E2E User processes to checkout - Shipping
    And E2E User pays by "cheque"
    And E2E User confirms order