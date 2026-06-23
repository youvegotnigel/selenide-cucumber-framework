@inventory
Feature: Inventory
  As a signed-in shopper
  I want to view and add products
  So that I can fill my cart

  Background:
    Given a standard user is logged in

  @smoke @regression
  Scenario: Inventory lists all products
    Then the product count is 6

  @regression
  Scenario: Adding multiple products updates the cart badge
    When the user adds the following products to the cart:
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |
    Then the cart badge shows 3

  @regression
  Scenario: Shared header steps work across inventory and cart pages
    Then the header title is "Products"
    When the user adds the following products to the cart:
      | Sauce Labs Backpack |
    Then the header cart badge shows 1
    When the user opens the cart from the header
    Then the header title is "Your Cart"

