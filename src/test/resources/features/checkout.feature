@checkout
Feature: Checkout Functionality
  As a customer
  I want to complete the checkout process
  So that I can purchase my selected products

  Background:
    Given I am logged in as "standard_user"
    And I have added "Sauce Labs Backpack" to the cart

  @smoke @e2e
  Scenario: Complete checkout with valid information
    When I navigate to the cart
    And I click checkout
    And I enter checkout information:
      | firstName | lastName | postalCode |
      | John      | Doe      | 12345      |
    And I click continue
    Then I should see the order summary
    When I click finish
    Then I should see the order confirmation message "Thank you for your order!"

  @negative
  Scenario: Checkout with missing first name
    When I navigate to the cart
    And I click checkout
    And I enter checkout information:
      | firstName | lastName | postalCode |
      |           | Doe      | 12345      |
    And I click continue
    Then I should see checkout error "Error: First Name is required"

  @negative
  Scenario: Checkout with missing last name
    When I navigate to the cart
    And I click checkout
    And I enter checkout information:
      | firstName | lastName | postalCode |
      | John      |          | 12345      |
    And I click continue
    Then I should see checkout error "Error: Last Name is required"

  @negative
  Scenario: Checkout with missing postal code
    When I navigate to the cart
    And I click checkout
    And I enter checkout information:
      | firstName | lastName | postalCode |
      | John      | Doe      |            |
    And I click continue
    Then I should see checkout error "Error: Postal Code is required"

  @positive
  Scenario: Cancel checkout and return to cart
    When I navigate to the cart
    And I click checkout
    And I click cancel on checkout page
    Then I should be on the cart page

  @positive
  Scenario: Verify order total calculation
    And I have added "Sauce Labs Bike Light" to the cart
    When I navigate to the cart
    And I click checkout
    And I enter checkout information:
      | firstName | lastName | postalCode |
      | John      | Doe      | 12345      |
    And I click continue
    Then the item total should be "$39.98"
    And the tax should be calculated correctly