package product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ecom.SwagLabs.objRepo.LoginPage;
import ecom.SwagLabs.objRepo.ProductPage;
import ecom.SwagLabs.genericUtility.ExcelUtility;
import ecom.SwagLabs.baseClass.BaseClass;

public class productTest extends BaseClass {


    private String readExcel(int row, int column)
            throws IOException {

        ExcelUtility excel = new ExcelUtility();

        return excel.readDataFromExcel(
                "Sheet1", row, column);
    }

    // Use existing LoginPage
    private void loginAndOpenCatalog()
            throws IOException, InterruptedException {

        LoginPage login = new LoginPage(driver);

        login.login("standard_user", "secret_sauce");

        Thread.sleep(2000);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "Inventory page did not open.");

        Assert.assertTrue(
                driver.findElement(
                        By.className("inventory_list")).isDisplayed(),
                "Product catalog is not displayed.");
    }

    private List<String> getProductNames() {

        List<WebElement> elements =
                driver.findElements(
                        By.className("inventory_item_name"));

        List<String> names = new ArrayList<>();

        for (WebElement element : elements) {
            names.add(element.getText().trim());
        }

        return names;
    }

    private void verifyCatalog() {

        List<WebElement> products =
                driver.findElements(
                        By.className("inventory_item"));

        Assert.assertEquals(
                products.size(), 6,
                "Expected six products in the catalog.");

        for (WebElement item : products) {

            Assert.assertTrue(
                    item.findElement(
                            By.className("inventory_item_name"))
                            .isDisplayed(),
                    "Product name is not displayed.");

            Assert.assertTrue(
                    item.findElement(
                            By.className("inventory_item_price"))
                            .isDisplayed(),
                    "Product price is not displayed.");

            Assert.assertTrue(
                    item.findElement(
                            By.className("inventory_item_img"))
                            .isDisplayed(),
                    "Product image is not displayed.");
        }
    }

    // CAT-TC-001: Functional
    // Verify sorting by Name A-Z

    @Test(groups = "functional")
    public void verifySortingByNameAToZ()
            throws IOException, InterruptedException {

        int row = 1;

        String id = readExcel(row, 0);
        String type = readExcel(row, 1);
        String scenario = readExcel(row, 2);
        String expectedResult = readExcel(row, 3);

        System.out.println(id + " | " + type + " | " + scenario);

        loginAndOpenCatalog();
        verifyCatalog();

        ProductPage product = new ProductPage(driver);

        product.sortProduct("az");

        Thread.sleep(1000);

        Assert.assertEquals(
                product.getSelectedSortOption(), "az",
                "Name A-Z sorting was not selected.");

        List<String> actual = getProductNames();
        List<String> expected = new ArrayList<>(actual);

        Collections.sort(
                expected, String.CASE_INSENSITIVE_ORDER);

        Assert.assertEquals(
                actual, expected, expectedResult);

        System.out.println(id + " PASSED");
    }

    // CAT-TC-002: Integration
    // Verify catalog to product details navigation

    @Test(groups = "Integration")
    public void verifyCatalogToProductDetails()
            throws IOException, InterruptedException {

        int row = 2;

        String id = readExcel(row, 0);
        String type = readExcel(row, 1);
        String scenario = readExcel(row, 2);
        String expectedResult = readExcel(row, 3);

        System.out.println(id + " | " + type + " | " + scenario);

        loginAndOpenCatalog();
        verifyCatalog();

        WebElement firstProduct = driver.findElement(
                By.className("inventory_item_name"));

        String catalogName = firstProduct.getText().trim();

        WebElement catalogItem = firstProduct.findElement(
                By.xpath("./ancestor::div[@class='inventory_item']"));

        String catalogPrice = catalogItem.findElement(
                By.className("inventory_item_price"))
                .getText().trim();

        firstProduct.click();

        Thread.sleep(1000);

        String detailName = driver.findElement(
                By.className("inventory_details_name"))
                .getText().trim();

        String detailPrice = driver.findElement(
                By.className("inventory_details_price"))
                .getText().trim();

        Assert.assertEquals(
                detailName, catalogName,
                "Product name does not match the catalog.");

        Assert.assertEquals(
                detailPrice, catalogPrice, expectedResult);

        System.out.println(id + " PASSED");
    }

    // CAT-TC-003: System
    // Verify complete catalog browsing journey

    @Test(groups = "System")
    public void verifyCompleteCatalogBrowsingJourney()
            throws IOException, InterruptedException {

        int row = 3;

        String id = readExcel(row, 0);
        String type = readExcel(row, 1);
        String scenario = readExcel(row, 2);
        String expectedResult = readExcel(row, 3);

        System.out.println(id + " | " + type + " | " + scenario);

        loginAndOpenCatalog();
        verifyCatalog();

        ProductPage product = new ProductPage(driver);

        product.sortProduct("az");

        Thread.sleep(1000);

        List<String> actual = getProductNames();
        List<String> expected = new ArrayList<>(actual);

        Collections.sort(
                expected, String.CASE_INSENSITIVE_ORDER);

        Assert.assertEquals(
                actual, expected,
                "Products are not sorted A-Z.");

        String selectedName = actual.get(0);

        driver.findElement(
                By.className("inventory_item_name")).click();

        Thread.sleep(1000);

        String detailName = driver.findElement(
                By.className("inventory_details_name"))
                .getText().trim();

        Assert.assertEquals(
                detailName, selectedName,
                "Incorrect product details page.");

        driver.findElement(
                By.id("back-to-products")).click();

        Thread.sleep(1000);

        verifyCatalog();

        Assert.assertTrue(
                driver.findElement(
                        By.className("product_sort_container"))
                        .isDisplayed(),
                "Sort dropdown is not displayed.");

        product.logout();

        Thread.sleep(1000);

        Assert.assertTrue(
                driver.findElement(
                        By.id("login-button")).isDisplayed(),
                expectedResult);

        System.out.println(id + " PASSED");
    }

    // CAT-TC-004: Smoke
    // Verify inventory catalog loads after login

    @Test(groups = "Smoke")
    public void verifyInventoryCatalogLoads()
            throws IOException, InterruptedException {

        int row = 4;

        String id = readExcel(row, 0);
        String type = readExcel(row, 1);
        String scenario = readExcel(row, 2);
        String expectedResult = readExcel(row, 3);

        System.out.println(id + " | " + type + " | " + scenario);loginAndOpenCatalog();

        Assert.assertEquals(
                driver.findElement(
                        By.className("title")).getText().trim(),
                "Products",
                "Inventory page title is incorrect.");

        verifyCatalog();

        Assert.assertTrue(
                driver.findElement(By.className("product_sort_container"))
                        .isDisplayed(),
                "Sort dropdown is not displayed.");

        Assert.assertTrue(
                driver.findElement(
                        By.className("shopping_cart_link"))
                        .isDisplayed(),
                "Shopping cart link is not displayed.");

        System.out.println(id + " PASSED: " + expectedResult);
    }
}
