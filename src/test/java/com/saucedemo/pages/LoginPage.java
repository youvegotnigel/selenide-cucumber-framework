package com.saucedemo.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * The saucedemo login screen.
 * <p>
 * Notice what is NOT here: no WebDriver, no waits, no click/type helpers. Selenide
 * provides those on every {@link SelenideElement}. The page is just locators plus
 * intent-revealing methods. Locators are evaluated lazily, so declaring them as
 * fields is safe even before the page is open.
 * <p>
 * Unlike the post-login pages, this one does not extend {@link BasePage}: the
 * login screen is shown before authentication and has no header title or cart,
 * so there is nothing for it to inherit. Inheritance is used only where there
 * is genuinely shared structure.
 */
public class LoginPage extends BasePage {

    private final SelenideElement username = $("#user-name");
    private final SelenideElement password = $("#password");
    private final SelenideElement loginButton = $("#login-button");
    private final SelenideElement error = $("[data-test='error']");

    public LoginPage open() {
        Selenide.open("/");
        return this;
    }

    public InventoryPage loginAs(String user, String pass) {
        submitCredentials(user, pass);
        return new InventoryPage();
    }

    public LoginPage submitCredentials(String user, String pass) {
        username.setValue(user);
        password.setValue(pass);
        loginButton.click();
        return this;
    }

    /** Selenide waits for the element and retries the text condition automatically. */
    public void errorShouldContain(String expected) {
        error.shouldHave(text(expected));
    }

    public void errorShouldBeVisible() {
        error.shouldBe(visible);
    }
}
