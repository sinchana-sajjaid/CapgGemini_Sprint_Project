package checkout;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import ecom.SwagLabs.baseClass.BaseClass;
import ecom.SwagLabs.objRepo.CartPage;
import ecom.SwagLabs.objRepo.CheckOutPage;
import ecom.SwagLabs.genericUtility.ExcelUtility;

public class CheckoutTest extends BaseClass {

    // SMOKE TESTING
    @Test(groups = "Smoke")
    public void checkoutSmokeTest() {

        login.login(username, password);

        driver.findElement(
            By.id("add-to-cart-sauce-labs-backpack")
        ).click();

        driver.findElement(
            By.className("shopping_cart_link")
        ).click();

        CartPage cart = new CartPage(driver);
        cart.clickCheckout();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("checkout-step-one.html"),
            "Checkout information page did not open"
        );

        System.out.println("Checkout Smoke Test Passed");
    }

    // FUNCTIONAL TESTING
    @Test(groups = "functional")
    public void checkoutFunctionalTest() throws IOException {

        login.login(username, password);

        driver.findElement(
            By.id("add-to-cart-sauce-labs-backpack")
        ).click();

        driver.findElement(
            By.className("shopping_cart_link")
        ).click();

        CartPage cart = new CartPage(driver);
        cart.clickCheckout();

        ExcelUtility excel = new ExcelUtility();

        String firstname =
            excel.readDataFromExcel("Sheet2", 1, 0);

        String lastname =
            excel.readDataFromExcel("Sheet2", 1, 1);

        String postalcode =
            excel.readDataFromExcel("Sheet2", 1, 2);

        CheckOutPage checkout = new CheckOutPage(driver);

        checkout.enterFirstName(firstname);
        checkout.enterLastName(lastname);
        checkout.enterPostalCode(postalcode);

        Assert.assertEquals(
            driver.findElement(By.id("first-name"))
                  .getAttribute("value"),
            firstname
        );

        Assert.assertEquals(
            driver.findElement(By.id("last-name"))
                  .getAttribute("value"),
            lastname
        );

        Assert.assertEquals(
            driver.findElement(By.id("postal-code"))
                  .getAttribute("value"),
            postalcode
        );

        checkout.clickContinue();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("checkout-step-two.html"),
            "Checkout overview page did not open"
        );

        System.out.println("Checkout Functional Test Passed");
    }

    // INTEGRATION TESTING
    @Test(groups = "Integration")
    public void checkoutIntegrationTest() throws IOException {

        login.login(username, password);

        driver.findElement(
            By.id("add-to-cart-sauce-labs-backpack")
        ).click();

        driver.findElement(
            By.className("shopping_cart_link")
        ).click();

        CartPage cart = new CartPage(driver);
        cart.clickCheckout();

        ExcelUtility excel = new ExcelUtility();

        String firstname =
            excel.readDataFromExcel("Sheet2", 1, 0);

        String lastname =
            excel.readDataFromExcel("Sheet2", 1, 1);

        String postalcode =
            excel.readDataFromExcel("Sheet2", 1, 2);

        CheckOutPage checkout = new CheckOutPage(driver);

        checkout.checkoutDetails(
            firstname,
            lastname,
            postalcode
        );

        Assert.assertTrue(
            driver.getCurrentUrl().contains("checkout-step-two.html"),
            "Excel data integration failed"
        );

        Assert.assertEquals(
            driver.findElement(
                By.className("inventory_item_name")).getText(),
            "Sauce Labs Backpack",
            "Expected product is missing from overview"
        );

        System.out.println("Checkout Integration Test Passed");
    }

    // SYSTEM TESTING
    @Test(groups = "System")
    public void checkoutSystemTest() throws IOException {

        login.login(username, password);

        driver.findElement(
            By.id("add-to-cart-sauce-labs-backpack")).click();

        driver.findElement(
            By.className("shopping_cart_link")).click();

        CartPage cart = new CartPage(driver);
        cart.clickCheckout();

        ExcelUtility excel = new ExcelUtility();

        String firstName = excel.readDataFromExcel("Sheet2", 1, 0);
        String lastName = excel.readDataFromExcel("Sheet2", 1, 1);
        String postalCode = excel.readDataFromExcel("Sheet2", 1, 2);
        CheckOutPage checkout = new CheckOutPage(driver);

        checkout.checkoutDetails(firstName,lastName,postalCode);

        // Finish order from overview page
        driver.findElement(By.id("finish")).click();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("checkout-complete.html"),
            "Order completion page did not open"
        );

        Assert.assertEquals(
            driver.findElement(
                By.className("complete-header")
            ).getText(),
            "Thank you for your order!",
            "Order success message did not appear"
        );

        System.out.println("Checkout System Test Passed");
    }
}