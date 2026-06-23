package com.saucedemo.steps;

import com.saucedemo.pages.BasePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Condition.text;

/**
 * Shared step definitions for screens that render the saucedemo header.
 *
 * This keeps common "header" checks/actions in one place instead of duplicating
 * them across feature-specific step classes.
 */
public class BasePageSteps {

    private final BasePage basePage = new BasePage();

    @Then("the header title is {string}")
    public void theHeaderTitleIs(String expectedTitle) {
        basePage.shouldHaveTitle(expectedTitle);
    }

    @When("the user opens the cart from the header")
    public void theUserOpensTheCartFromTheHeader() {
        basePage.openCart();
    }

    @Then("the header cart badge shows {int}")
    public void theHeaderCartBadgeShows(int expectedCount) {
        basePage.shouldHaveCartBadgeCount(expectedCount);
    }
}

