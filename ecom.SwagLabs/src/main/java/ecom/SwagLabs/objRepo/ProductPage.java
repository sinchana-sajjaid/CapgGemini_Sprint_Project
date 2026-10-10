package ecom.SwagLabs.objRepo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Sorting dropdown
    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    // All product names
    @FindBy(css = "[data-test='inventory-item-name']")
    private List<WebElement> productNames;

    // All product prices
    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    // Add to cart button on product details page
    @FindBy(css = "button[data-test^='add-to-cart']")
    private WebElement addToCartButton;

    // Menu button
    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    // Logout link
    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    // Step 1: Sort products
    public void sortProduct(String filter) {

        Select select = new Select(sortDropdown);

        switch (filter.toLowerCase()) {

            case "az":
                select.selectByValue("az");
                break;

            case "za":
                select.selectByValue("za");
                break;

            case "lowhigh":
                select.selectByValue("lohi");
                break;

            case "highlow":
                select.selectByValue("hilo");
                break;

            default:
                throw new IllegalArgumentException(
                        "Invalid filter: " + filter);
        }
    }

    // Step 2: Get selected sort value
    public String getSelectedSortOption() {
        return new Select(sortDropdown)
                .getFirstSelectedOption()
                .getAttribute("value");
    }

    // Step 3: Get all product names
    public List<String> getAllProductNames() {

        List<String> names = new ArrayList<>();

        for (WebElement product : productNames) {
            names.add(product.getText());
        }

        return names;
    }

    // Step 4: Get all product prices
    public List<Double> getAllProductPrices() {

        List<Double> prices = new ArrayList<>();

        for (WebElement price : productPrices) {

            String value = price.getText()
                    .replace("$", "")
                    .trim();

            prices.add(Double.parseDouble(value));
        }

        return prices;
    }

    // Step 5: Click the first product
    public void selectFirstProduct() {
        driver.findElement(
                By.cssSelector("[data-test='inventory-item-name']"))
                .click();
    }

    // Step 6: Add product to cart on details page
    public void addFirstProductToCart() {
        addToCartButton.click();
    }

    // Step 7: Return to the products page
    public void waitForProductsPage() {

       
        // Click the Back to products button if on the details page.
        driver.findElement(
                By.id("back-to-products")).click();
    }

    // Step 8: Logout
    public void logout() {

        menuButton.click();
        logoutButton.click();
    }
}