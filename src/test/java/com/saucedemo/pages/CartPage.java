package com.saucedemo.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * The shopping cart review page.
 * <p>
 * Extends {@link BasePage} for the shared header (title and cart link/badge);
 * only the cart's own list of items and the checkout button are declared here.
 */
public class CartPage extends BasePage {

    private final ElementsCollection cartItems = $$(".cart_item");
    private final SelenideElement checkoutButton = $("#checkout");

    public CartPage shouldHaveItemCount(int expected) {
        cartItems.shouldHave(size(expected));
        return this;
    }

    public CheckoutPage proceedToCheckout() {
        checkoutButton.click();
        return new CheckoutPage();
    }
}
