package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(className = "title")
    private WebElement pageTitle;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnCartPage() {
        return getCurrentUrl().contains("cart.html");
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public List<String> getCartItemNames() {
        return cartItems.stream()
                .map(item -> item.findElement(By.className("inventory_item_name")).getText())
                .toList();
    }

    public boolean isProductInCart(String productName) {
        return getCartItemNames().stream()
                .anyMatch(name -> name.equalsIgnoreCase(productName));
    }

    public void removeItem(String productName) {
        for (WebElement item : cartItems) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equalsIgnoreCase(productName)) {
                WebElement removeBtn = item.findElement(By.cssSelector("button[id^='remove']"));
                click(removeBtn);
                return;
            }
        }
        throw new RuntimeException("Product not in cart: " + productName);
    }

    public CheckoutPage clickCheckout() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }

    public InventoryPage continueShopping() {
        click(continueShoppingButton);
        return new InventoryPage(driver);
    }

    public String getItemPrice(String productName) {
        for (WebElement item : cartItems) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equalsIgnoreCase(productName)) {
                return item.findElement(By.className("inventory_item_price")).getText();
            }
        }
        throw new RuntimeException("Product not found: " + productName);
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }
}