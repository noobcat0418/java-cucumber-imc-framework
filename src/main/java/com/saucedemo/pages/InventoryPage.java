package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(css = "[data-test='product-sort-container']")
    private WebElement sortDropdown;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItemsLink;

    @FindBy(className = "title")
    private WebElement pageTitle;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnInventoryPage() {
        return getCurrentUrl().contains("inventory.html");
    }

    public boolean areProductsDisplayed() {
        return !inventoryItems.isEmpty();
    }

    public int getProductCount() {
        return inventoryItems.size();
    }

    public void addProductToCart(String productName) {
        for (WebElement item : inventoryItems) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equalsIgnoreCase(productName)) {
                WebElement addButton = item.findElement(By.cssSelector("button[id^='add-to-cart']"));
                click(addButton);
                return;
            }
        }
        throw new RuntimeException("Product not found: " + productName);
    }

    public void removeProductFromCart(String productName) {
        for (WebElement item : inventoryItems) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equalsIgnoreCase(productName)) {
                WebElement removeButton = item.findElement(By.cssSelector("button[id^='remove']"));
                click(removeButton);
                return;
            }
        }
        throw new RuntimeException("Product not found: " + productName);
    }

    public void sortProducts(String sortOption) {
        selectByVisibleText(sortDropdown, sortOption);
    }

    public List<String> getProductNames() {
        return productNames.stream().map(WebElement::getText).toList();
    }

    public CartPage goToCart() {
        click(cartIcon);
        return new CartPage(driver);
    }

    public int getCartItemCount() {
        try {
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public void clickMenuButton() {
        click(menuButton);
    }

    public LoginPage logout() {
        clickMenuButton();
        waitForClickable(logoutLink);
        click(logoutLink);
        return new LoginPage(driver);
    }

    public LoginPage clickLogoutLink() {
        waitForClickable(logoutLink);
        click(logoutLink);
        return new LoginPage(driver);
    }

    public ProductDetailPage clickOnProduct(String productName) {
        for (WebElement name : productNames) {
            if (name.getText().equalsIgnoreCase(productName)) {
                click(name);
                return new ProductDetailPage(driver);
            }
        }
        throw new RuntimeException("Product not found: " + productName);
    }

    public String getPageTitleText() {
        return getText(pageTitle);
    }
}