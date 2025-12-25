package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailPage extends BasePage {

    @FindBy(className = "inventory_details_name")
    private WebElement productName;

    @FindBy(className = "inventory_details_desc")
    private WebElement productDescription;

    @FindBy(className = "inventory_details_price")
    private WebElement productPrice;

    @FindBy(className = "inventory_details_img")
    private WebElement productImage;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;

    @FindBy(css = "button[id^='add-to-cart']")
    private WebElement addToCartButton;

    @FindBy(css = "button[id^='remove']")
    private WebElement removeButton;

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOnProductDetailPage() {
        return getCurrentUrl().contains("inventory-item.html");
    }

    public String getProductName() {
        return getText(productName);
    }

    public String getProductDescription() {
        return getText(productDescription);
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public boolean isProductImageDisplayed() {
        return isDisplayed(productImage);
    }

    public void addToCart() {
        click(addToCartButton);
    }

    public void removeFromCart() {
        click(removeButton);
    }

    public boolean isAddToCartButtonDisplayed() {
        return isDisplayed(addToCartButton);
    }

    public boolean isRemoveButtonDisplayed() {
        return isDisplayed(removeButton);
    }

    public InventoryPage goBackToProducts() {
        click(backToProductsButton);
        return new InventoryPage(driver);
    }
}