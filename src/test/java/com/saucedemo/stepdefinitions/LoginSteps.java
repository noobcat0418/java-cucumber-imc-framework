package com.saucedemo.stepdefinitions;

import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.ConfigReader;
import com.saucedemo.utils.DriverManager;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {
    private final WebDriver driver = DriverManager.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private InventoryPage inventoryPage;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        loginPage.navigateToLoginPage();
        assertThat(loginPage.isLoginButtonDisplayed())
            .as("Login page should be displayed")
            .isTrue();
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @When("I login with valid credentials")
    public void iLoginWithValidCredentials() {
        inventoryPage = loginPage.loginWithValidCredentials();
    }

    @When("I click the menu button")
    public void iClickTheMenuButton() {
        inventoryPage.clickMenuButton();
    }

    @When("I click logout")
    public void iClickLogout() {
        // Menu is already open from previous step, just click the logout link
        loginPage = inventoryPage.clickLogoutLink();
    }

    @Then("I should be redirected to the inventory page")
    public void iShouldBeRedirectedToTheInventoryPage() {
        inventoryPage = new InventoryPage(driver);
        assertThat(inventoryPage.isOnInventoryPage())
            .as("Should be on inventory page")
            .isTrue();
    }

    @Then("I should see products displayed")
    public void iShouldSeeProductsDisplayed() {
        assertThat(inventoryPage.areProductsDisplayed())
            .as("Products should be displayed")
            .isTrue();
        assertThat(inventoryPage.getProductCount())
            .as("Should have products")
            .isGreaterThan(0);
    }

    @Then("I should see error message {string}")
    public void iShouldSeeErrorMessage(String expectedMessage) {
        assertThat(loginPage.isErrorDisplayed())
            .as("Error message should be displayed")
            .isTrue();
        assertThat(loginPage.getErrorMessage())
            .as("Error message text should match")
            .isEqualTo(expectedMessage);
    }

    @Then("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        assertThat(loginPage.isOnLoginPage())
            .as("Should remain on login page")
            .isTrue();
    }

    @Then("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
        assertThat(loginPage.isOnLoginPage())
            .as("Should be redirected to login page")
            .isTrue();
    }

    @Given("I am logged in as {string}")
    public void iAmLoggedInAs(String username) {
        loginPage.navigateToLoginPage();
        inventoryPage = loginPage.login(username, ConfigReader.getDefaultPassword());
        assertThat(inventoryPage.isOnInventoryPage())
            .as("Should be logged in and on inventory page")
            .isTrue();
    }
}