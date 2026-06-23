package com.saucedemo.steps;

import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class InventorySteps {

    @Given("a standard user is logged in")
    public void aStandardUserIsLoggedIn() {
        new LoginPage().open().loginAs("standard_user", "secret_sauce");
    }

    @Then("the product count is {int}")
    public void theProductCountIs(int count) {
        new InventoryPage().shouldHaveProductCount(count);
    }

    /**
     * A single-column Gherkin data table is delivered as a flat {@code List<String>}.
     * Each value is a product name to add to the cart.
     */
    @When("the user adds the following products to the cart:")
    public void theUserAddsProducts(List<String> products) {
        InventoryPage inventory = new InventoryPage();
        products.forEach(inventory::addItem);
    }

    @Then("the cart badge shows {int}")
    public void theCartBadgeShows(int count) {
        new InventoryPage().shouldHaveCartCount(count);
    }
}
