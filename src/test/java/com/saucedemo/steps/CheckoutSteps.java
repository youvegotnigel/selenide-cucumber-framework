package com.saucedemo.steps;

import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.InventoryPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

public class CheckoutSteps {

    /**
     * A two-column Gherkin data table is delivered as a {@code Map<String, String>}:
     * the first column becomes the key, the second the value.
     */
    @When("the user checks out with the details:")
    public void theUserChecksOutWith(Map<String, String> details) {
        new InventoryPage().openCart()
                .proceedToCheckout()
                .enterCustomerInformation(
                        details.get("firstName"),
                        details.get("lastName"),
                        details.get("postalCode"))
                .finishOrder();
    }

    @Then("the order confirmation {string} is shown")
    public void theOrderConfirmationIsShown(String expected) {
        new CheckoutPage().shouldShowConfirmation(expected);
    }
}
