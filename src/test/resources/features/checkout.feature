@checkout
Feature: Checkout
  As a signed-in shopper
  I want to check out the items in my cart
  So that I can complete a purchase

  @smoke @regression @slow
  Scenario: Complete a purchase end to end
    Given a standard user is logged in
    And the user adds the following products to the cart:
      | Sauce Labs Backpack |
    When the user checks out with the details:
      | firstName  | Ada      |
      | lastName   | Lovelace |
      | postalCode | EC1A     |
    Then the order confirmation "Thank you for your order!" is shown

