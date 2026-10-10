package NegativeTestScripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import ecom.SwagLabs.baseClass.BaseClass;
import ecom.SwagLabs.genericUtility.ExcelUtility;
import ecom.SwagLabs.objRepo.LoginPage;

public class LoginTestScripts extends BaseClass {

    ExcelUtility excel = new ExcelUtility();

    // Read test ID from Excel Sheet3
    public String getTestID(int row) throws IOException {
        return excel.readDataFromExcel("Sheet3", row, 0);
    }

    // LOGIN-FUN-002: Invalid username
    @Test(groups = {"functional"})
    public void invalidUsernameTest() throws IOException {

        String testId = getTestID(1);

        login.getun("invalid_user");
        login.getpwd("secret_sauce");
        login.getlogin();

        String errorMessage = driver.findElement(
                By.cssSelector("[data-test='error']"))
                .getText();

        Assert.assertTrue(
                errorMessage.contains(
                        "Username and password do not match"),
                "Error message was not displayed for invalid username.");

        System.out.println(testId + " - PASSED");
    }

    // LOGIN-INT-003: Invalid login must not open Inventory
    @Test(groups = {"Integration"})
    public void invalidLoginCannotAccessInventory()
            throws IOException {

        String testId = getTestID(2);

        login.getun("invalid_user");
        login.getpwd("invalid_password");
        login.getlogin();

        Assert.assertFalse(
                driver.getCurrentUrl().contains("inventory.html"),
                "Invalid login should not allow Inventory access.");

        Assert.assertTrue(
                driver.getCurrentUrl().contains("saucedemo.com"),
                "User should remain on the SauceDemo website.");

        Assert.assertTrue(
                driver.findElement(
                        By.cssSelector("[data-test='error']"))
                        .isDisplayed(),
                "Login error was not displayed.");

        System.out.println(testId + " - PASSED");
    }

    // LOGIN-SM-003: Invalid credentials are rejected
    @Test(groups = {"Smoke"})
    public void invalidCredentialsSmokeTest()
            throws IOException {

        String testId = getTestID(3);

        login.getun("wrong_user");
        login.getpwd("wrong_password");
        login.getlogin();

        Assert.assertTrue(
                driver.findElement(
                        By.cssSelector("[data-test='error']"))
                        .isDisplayed(),
                "Login error was not displayed.");

        Assert.assertFalse(
                driver.getCurrentUrl().contains("inventory.html"),
                "Invalid credentials should not open Inventory.");

        System.out.println(testId + " - PASSED");
    }

    // LOGIN-SYS-002: Blank username and password
    @Test(groups = {"System"})
    public void blankCredentialsTest()
            throws IOException {

        String testId = getTestID(4);

        login.getun("");
        login.getpwd("");
        login.getlogin();

        String errorMessage = driver.findElement(
                By.cssSelector("[data-test='error']"))
                .getText();

        Assert.assertTrue(
                errorMessage.contains("Username is required"),
                "Required-credentials error was not displayed.");

        Assert.assertFalse(
                driver.getCurrentUrl().contains("inventory.html"),
                "Blank credentials should not open Inventory.");

        System.out.println(testId + " - PASSED");
    }
}