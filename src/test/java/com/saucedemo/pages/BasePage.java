package com.saucedemo.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

/**
 * Shared parent for every page that renders the standard saucedemo header.
 * <p>
 * After login, the inventory, cart, and checkout screens all show the same
 * header: a page title on the left and a shopping-cart link with an item-count
 * badge on the right. Rather than redeclare those elements on each page, they
 * live here once and the concrete pages inherit them by extending this class.
 * Each subclass then adds only the elements and actions unique to its screen.
 * <p>
 * The class is {@code abstract} because a "base page" is never opened on its
 * own; it only exists to be extended. {@link LoginPage} deliberately does not
 * extend it: the login screen is shown before authentication and has neither a
 * title bar nor a cart, so it is not a "header page" and forcing the
 * relationship would inherit elements that do not exist on it.
 */
public class BasePage {

    /** The page heading in the header, e.g. "Products" or "Your Cart". */
    protected final SelenideElement title = $(".title");

    /** The shopping-cart link and its item-count badge, shared by every header page. */
    protected final SelenideElement cartLink = $(".shopping_cart_link");
    protected final SelenideElement cartBadge = $(".shopping_cart_badge");

    /** Selenide waits for the heading and retries the text condition automatically. */
    public void shouldHaveTitle(String expected) {
        title.shouldHave(text(expected));
    }

    /** Opens the cart from the header. Available on every page that has one. */
    public CartPage openCart() {
        cartLink.click();
        return new CartPage();
    }

    public void shouldHaveCartBadgeCount(int expectedCount) {
        cartBadge.shouldHave(text(String.valueOf(expectedCount)));
    }
}
