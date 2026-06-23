package com.saucedemo.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

/**
 * The checkout flow: customer information, then overview, then confirmation.
 * Kept as one page object for brevity; a larger app would split the info and
 * overview screens into separate pages.
 * <p>
 * Extends {@link BasePage} so it shares the saucedemo header; the
 * checkout-specific form fields and confirmation banner are declared here.
 */
public class CheckoutPage extends BasePage {

    private final SelenideElement firstName = $("#first-name");
    private final SelenideElement lastName = $("#last-name");
    private final SelenideElement postalCode = $("#postal-code");
    private final SelenideElement continueButton = $("#continue");
    private final SelenideElement finishButton = $("#finish");
    private final SelenideElement confirmation = $(".complete-header");

    public CheckoutPage enterCustomerInformation(String first, String last, String postal) {
        firstName.setValue(first);
        lastName.setValue(last);
        postalCode.setValue(postal);
        continueButton.click();
        return this;
    }

    public CheckoutPage finishOrder() {
        finishButton.click();
        return this;
    }

    public void shouldShowConfirmation(String expected) {
        confirmation.shouldHave(exactText(expected));
    }
}
