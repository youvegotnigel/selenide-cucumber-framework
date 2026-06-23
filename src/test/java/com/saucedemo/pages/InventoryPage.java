package com.saucedemo.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * The product listing shown after a successful login.
 * <p>
 * Extends {@link BasePage}, so the header title and shopping-cart link/badge
 * (and {@link BasePage#openCart()}) are inherited. Only the product grid and
 * its actions are declared here.
 */
public class InventoryPage extends BasePage {

    private final ElementsCollection items = $$(".inventory_item");

    public InventoryPage shouldBeLoaded() {
        shouldHaveTitle("Products"); // inherited from BasePage
        return this;
    }

    /** Selenide retries the collection size assertion until it matches or times out. */
    public InventoryPage shouldHaveProductCount(int expected) {
        items.shouldHave(size(expected));
        return this;
    }

    /**
     * Add a product by visible name. saucedemo derives the button id from a
     * slugified product name, which keeps this readable and avoids brittle XPath.
     */
    public InventoryPage addItem(String productName) {
        String slug = productName.toLowerCase().replace(" ", "-");
        $("#add-to-cart-" + slug).click();
        return this;
    }

    public InventoryPage shouldHaveCartCount(int expected) {
        cartBadge.shouldHave(text(String.valueOf(expected))); // cartBadge inherited from BasePage
        return this;
    }
}
