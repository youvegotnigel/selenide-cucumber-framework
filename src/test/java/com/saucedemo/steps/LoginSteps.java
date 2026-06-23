package com.saucedemo.steps;

import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Steps for the login feature.
 * <p>
 * Page objects are created on the spot. Because Selenide keeps a single browser
 * for the scenario, the live session holds the state, so there is no need to
 * share a driver or page instances between step classes (no DI container needed).
 */
public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();

    @Given("the login page is open")
    public void theLoginPageIsOpen() {
        loginPage.open();
    }

    @When("the user logs in as {string} with password {string}")
    public void theUserLogsIn(String username, String password) {
        loginPage.submitCredentials(username, password);
    }

    @Then("the products page is displayed")
    public void theProductsPageIsDisplayed() {
        new InventoryPage().shouldBeLoaded();
    }

    @Then("an error message containing {string} is shown")
    public void anErrorContaining(String expected) {
        loginPage.errorShouldContain(expected);
    }

    @Then("an error message is shown")
    public void anErrorIsShown() {
        loginPage.errorShouldBeVisible();
    }
}
