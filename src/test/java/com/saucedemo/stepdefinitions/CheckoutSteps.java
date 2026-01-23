package com.saucedemo.stepdefinitions;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.utils.DriverManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutSteps {
    private final WebDriver driver = DriverManager.getDriver();
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Given("I have added {string} to the cart")
    public void iHaveAddedToTheCart(String productName) {
        inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart(productName);
    }

    @When("I navigate to the cart")
    public void iNavigateToTheCart() {
        inventoryPage = new InventoryPage(driver);
        cartPage = inventoryPage.goToCart();
    }

    @When("I click checkout")
    public void iClickCheckout() {
        checkoutPage = cartPage.clickCheckout();
    }

    @When("I enter checkout information:")
    public void iEnterCheckoutInformation(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        Map<String, String> info = data.get(0);
        checkoutPage.fillCheckoutInfo(
            info.get("firstName"),
            info.get("lastName"),
            info.get("postalCode")
        );
    }

    @When("I click continue")
    public void iClickContinue() {
        checkoutPage.clickContinue();
    }

    @When("I click finish")
    public void iClickFinish() {
        checkoutPage.clickFinish();
    }

    @When("I click cancel on checkout page")
    public void iClickCancelOnCheckoutPage() {
        cartPage = checkoutPage.clickCancel();
    }

    @Then("I should see the order summary")
    public void iShouldSeeTheOrderSummary() {
        assertThat(checkoutPage.isOnOverviewPage())
            .as("Should be on order overview page")
            .isTrue();
    }

    @Then("I should see the order confirmation message {string}")
    public void iShouldSeeTheOrderConfirmationMessage(String expectedMessage) {
        assertThat(checkoutPage.isOrderComplete())
            .as("Order should be complete")
            .isTrue();
        assertThat(checkoutPage.getConfirmationMessage())
            .as("Confirmation message should match")
            .isEqualTo(expectedMessage);
    }

    @Then("I should see checkout error {string}")
    public void iShouldSeeCheckoutError(String expectedError) {
        assertThat(checkoutPage.isErrorDisplayed())
            .as("Error should be displayed")
            .isTrue();
        assertThat(checkoutPage.getErrorMessage())
            .as("Error message should match")
            .isEqualTo(expectedError);
    }

    @Then("I should be on the cart page")
    public void iShouldBeOnTheCartPage() {
        assertThat(cartPage.isOnCartPage())
            .as("Should be on cart page")
            .isTrue();
    }

    @Then("the item total should be {string}")
    public void theItemTotalShouldBe(String expectedTotal) {
        assertThat(checkoutPage.getItemTotal())
            .as("Item total should match")
            .isEqualTo(expectedTotal);
    }

    @Then("the tax should be calculated correctly")
    public void theTaxShouldBeCalculatedCorrectly() {
        String tax = checkoutPage.getTax();
        assertThat(tax)
            .as("Tax should be present")
            .isNotEmpty();
    }
}
