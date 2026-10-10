package cart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ecom.SwagLabs.baseClass.BaseClass;
import ecom.SwagLabs.objRepo.LoginPage;
import ecom.SwagLabs.objRepo.CartPage;
import ecom.SwagLabs.objRepo.CheckOutPage;

public class cartTest1 extends BaseClass {

    // Common wait
    public WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Login and add a product to cart
    public void loginAndAddProduct() {

        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        WebDriverWait wait = getWait();

        // Wait for Products page
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("inventory_list")));

        // Add Sauce Labs Backpack
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-sauce-labs-backpack"))).click();

        // Open cart
        wait.until(ExpectedConditions.elementToBeClickable(
                By.className("shopping_cart_link"))).click();

        // Verify Cart page
        wait.until(ExpectedConditions.urlContains("cart.html"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("title")));
    }

    // SMOKE TESTING
    @Test(groups = "Smoke")
    public void cartSmokeTest() {

        loginAndAddProduct();

        CartPage cart = new CartPage(driver);

        Assert.assertEquals(
                cart.getCartTitle(),
                "Your Cart",
                "Cart title is incorrect");

        Assert.assertEquals(
                cart.getProductName(),
                "Sauce Labs Backpack",
                "Product is missing from cart");

        System.out.println("Cart Smoke Test Passed");
    }

    // FUNCTIONAL TESTING
    @Test(groups = "functional")
    public void cartFunctionalTest() {

        loginAndAddProduct();

        CartPage cart = new CartPage(driver);

        Assert.assertEquals(
                cart.getProductName(),
                "Sauce Labs Backpack");

        Assert.assertEquals(
                cart.getQuantity(),
                "1",
                "Product quantity is incorrect");

        Assert.assertEquals(
                cart.getProductPrice(),
                "$29.99",
                "Product price is incorrect");

        // Remove product
        cart.clickRemove();

        // Verify cart is empty
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("cart_item")));

        Assert.assertTrue(
                driver.findElements(By.className("cart_item")).isEmpty(),
                "Product was not removed from cart");

        System.out.println("Cart Functional Test Passed");
    }

    // INTEGRATION TESTING
    @Test(groups = "Integration")
    public void cartIntegrationTest() {

        loginAndAddProduct();

        CartPage cart = new CartPage(driver);

        Assert.assertEquals(
                cart.getProductName(),
                "Sauce Labs Backpack",
                "Incorrect product displayed in cart");

        // Navigate from Cart to Checkout
        cart.clickCheckout();

        getWait().until(ExpectedConditions.urlContains(
                "checkout-step-one.html"));

        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-one.html"),
                "Checkout page did not open");

        // Verify checkout form is displayed
        Assert.assertTrue(
                getWait().until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("first-name"))).isDisplayed(),
                "Checkout form is not displayed");

        System.out.println("Cart Integration Test Passed");
    }

    // SYSTEM TESTING
    @Test(groups = "System")
    public void cartSystemTest() {

        loginAndAddProduct();

        CartPage cart = new CartPage(driver);

        Assert.assertEquals(
                cart.getProductName(),
                "Sauce Labs Backpack",
                "Product is missing from cart");

        // Cart to Checkout
        cart.clickCheckout();

        getWait().until(ExpectedConditions.urlContains(
                "checkout-step-one.html"));

        // Enter checkout details
        CheckOutPage checkout = new CheckOutPage(driver);
        checkout.checkoutDetails("Sinchana", "S", "583101");

        // Wait for Overview page
        getWait().until(ExpectedConditions.urlContains(
                "checkout-step-two.html"));

        // Finish order
        getWait().until(ExpectedConditions.elementToBeClickable(
                By.id("finish"))).click();

        // Wait for order completion page
        getWait().until(ExpectedConditions.urlContains(
                "checkout-complete.html"));

        // Verify success message
        String actualMessage = getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("complete-header"))).getText();

        Assert.assertEquals(
                actualMessage,
                "Thank you for your order!",
                "Order success message not displayed");

        System.out.println("Cart System Test Passed");
    }
}
