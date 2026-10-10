package NegativeTestScripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import ecom.SwagLabs.baseClass.BaseClass;
import ecom.SwagLabs.objRepo.LoginPage;
import ecom.SwagLabs.genericUtility.ExcelUtility;

public class CheckoutTestScripts extends BaseClass {

    ExcelUtility excel = new ExcelUtility();

    public String convertEmpty(String data) {
        if (data == null || data.trim().equalsIgnoreCase("empty")) {
            return "";
        }
        return data.trim();
    }

    public String readSheet2(int row, int column) throws IOException {
        return convertEmpty(excel.readDataFromExcel("Sheet2", row, column));
    }

    public void openCheckoutPage() {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        driver.findElement(
                By.id("add-to-cart-sauce-labs-backpack")).click();

        driver.findElement(
                By.className("shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();
    }

    public void enterCheckoutDetails(
            String firstName, String lastName, String postalCode) {

        driver.findElement(By.id("first-name")).clear();
        driver.findElement(By.id("first-name")).sendKeys(firstName);

        driver.findElement(By.id("last-name")).clear();
        driver.findElement(By.id("last-name")).sendKeys(lastName);

        driver.findElement(By.id("postal-code")).clear();
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);

        driver.findElement(By.id("continue")).click();
    }

    // 1. Functional: Blank first name
    @Test(groups = "functional")
    public void blankFirstNameTest() throws IOException {

        String lastName = readSheet2(1, 1);
        String postalCode = readSheet2(1, 2);

        openCheckoutPage();
        enterCheckoutDetails("", lastName, postalCode);

        String actualMessage = driver.findElement(
                By.cssSelector("[data-test='error']")).getText();

        Assert.assertEquals(
                actualMessage, "Error: First Name is required");
    }

    // 2. Integration: Blank last name
    @Test(groups = "Integration")
    public void blankLastNameTest() throws IOException {

        String firstName = readSheet2(1, 0);
        String postalCode = readSheet2(1, 2);

        openCheckoutPage();
        enterCheckoutDetails(firstName, "", postalCode);

        String actualMessage = driver.findElement(
                By.cssSelector("[data-test='error']")).getText();

        Assert.assertEquals(
                actualMessage, "Error: Last Name is required");
    }

    // 3. Smoke: Blank postal code
    @Test(groups = "Smoke")
    public void blankPostalCodeTest() throws IOException {

        String firstName = readSheet2(1, 0);
        String lastName = readSheet2(1, 1);

        openCheckoutPage();
        enterCheckoutDetails(firstName, lastName, "");

        String actualMessage = driver.findElement(
                By.cssSelector("[data-test='error']")).getText();

        Assert.assertEquals(
                actualMessage, "Error: Postal Code is required");
    }

    // 4. System: Blank first name and last name
    @Test(groups = "System")
    public void blankFirstAndLastNameTest() throws IOException {

        String firstName = readSheet2(2, 0);
        String lastName = readSheet2(2, 1);
        String postalCode = readSheet2(2, 2);

        openCheckoutPage();
        enterCheckoutDetails(firstName, lastName, postalCode);

        String actualMessage = driver.findElement(
                By.cssSelector("[data-test='error']")).getText();

        Assert.assertEquals(
                actualMessage, "Error: First Name is required");
    }
}