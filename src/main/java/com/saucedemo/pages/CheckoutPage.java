package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    // Checkout Step One - Information
    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    // Checkout Step Two - Overview
    @FindBy(className = "summary_subtotal_label")
    private WebElement itemTotal;

    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    @FindBy(id = "finish")
    private WebElement finishButton;

    // Checkout Complete
    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    @FindBy(className = "complete-text")
    private WebElement completeText;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        type(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameInput, lastName);
    }

    public void enterPostalCode(String postalCode) {
        type(postalCodeInput, postalCode);
    }

    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        if (firstName != null && !firstName.isEmpty()) enterFirstName(firstName);
        if (lastName != null && !lastName.isEmpty()) enterLastName(lastName);
        if (postalCode != null && !postalCode.isEmpty()) enterPostalCode(postalCode);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public CartPage clickCancel() {
        click(cancelButton);
        return new CartPage(driver);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    // Overview Page Methods
    public boolean isOnOverviewPage() {
        return getCurrentUrl().contains("checkout-step-two");
    }

    public String getItemTotal() {
        return getText(itemTotal).replace("Item total: ", "");
    }

    public String getTax() {
        return getText(taxLabel).replace("Tax: ", "");
    }

    public String getTotal() {
        return getText(totalLabel).replace("Total: ", "");
    }

    public void clickFinish() {
        click(finishButton);
    }

    // Complete Page Methods
    public boolean isOrderComplete() {
        return getCurrentUrl().contains("checkout-complete");
    }

    public String getConfirmationMessage() {
        return getText(completeHeader);
    }

    public String getConfirmationText() {
        return getText(completeText);
    }

    public InventoryPage clickBackHome() {
        click(backHomeButton);
        return new InventoryPage(driver);
    }
}