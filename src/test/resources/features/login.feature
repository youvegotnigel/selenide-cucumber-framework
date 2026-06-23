@login
Feature: Login
  As a shopper
  I want to sign in to saucedemo
  So that I can browse and buy products

  Background:
    Given the login page is open

  @smoke @regression
  Scenario: Standard user logs in successfully
    When the user logs in as "standard_user" with password "secret_sauce"
    Then the products page is displayed

  @regression
  Scenario: Locked out user is rejected
    When the user logs in as "locked_out_user" with password "secret_sauce"
    Then an error message containing "locked out" is shown

  @regression
  Scenario Outline: Invalid credentials are rejected
    When the user logs in as "<username>" with password "<password>"
    Then an error message is shown

    Examples:
      | username      | password       |
      | standard_user | wrong_password |
      | no_such_user  | secret_sauce   |
      |               |                |
