@login
Feature: Login Functionality
  As a user of Sauce Demo
  I want to be able to login to the application
  So that I can access the product inventory

  Background:
    Given I am on the login page

  @smoke @positive
  Scenario: Successful login with valid credentials
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should be redirected to the inventory page
    And I should see products displayed

  @negative
  Scenario: Login with invalid password
    When I enter username "standard_user"
    And I enter password "wrong_password"
    And I click the login button
    Then I should see error message "Epic sadface: Username and password do not match any user in this service"
    And I should remain on the login page

  @negative
  Scenario: Login with locked out user
    When I enter username "locked_out_user"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should see error message "Epic sadface: Sorry, this user has been locked out."

  @negative
  Scenario: Login with empty credentials
    When I click the login button
    Then I should see error message "Epic sadface: Username is required"

  @negative
  Scenario: Login with empty password
    When I enter username "standard_user"
    And I click the login button
    Then I should see error message "Epic sadface: Password is required"

  @smoke
  Scenario: Logout functionality
    When I login with valid credentials
    And I click the menu button
    And I click logout
    Then I should be redirected to the login page

  @positive
  Scenario Outline: Login with different valid users
    When I enter username "<username>"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should be redirected to the inventory page

    Examples:
      | username                |
      | standard_user           |
      | problem_user            |
      | performance_glitch_user |