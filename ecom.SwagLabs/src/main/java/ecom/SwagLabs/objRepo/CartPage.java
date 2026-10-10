package ecom.SwagLabs.objRepo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver, this);
    }

    // Cart title
    @FindBy(className = "title")
    private WebElement cartTitle;

    // Product name
    @FindBy(className = "inventory_item_name")
    private WebElement productName;

    // Product quantity
    @FindBy(className = "cart_quantity")
    private WebElement quantity;

    // Product price
    @FindBy(className = "inventory_item_price")
    private WebElement productPrice;

    // Remove first product in cart
    @FindBy(xpath = "(//button[contains(@id,'remove-')])[1]")
    private WebElement removeButton;

    // Continue shopping button
    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    // Checkout button
    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    // Get cart title
    public String getCartTitle() {
        return wait.until(
                ExpectedConditions.visibilityOf(cartTitle)).getText();
    }

    // Get product name
    public String getProductName() {
        return wait.until(
                ExpectedConditions.visibilityOf(productName)
        ).getText();
    }

    // Get quantity
    public String getQuantity() {
        return wait.until(
                ExpectedConditions.visibilityOf(quantity)
        ).getText();
    }

    // Get product price
    public String getProductPrice() {
        return wait.until(
                ExpectedConditions.visibilityOf(productPrice)
        ).getText();
    }

    // Remove first product
    public void clickRemove() {
        wait.until(
                ExpectedConditions.elementToBeClickable(removeButton)).click();
    }

    // Continue shopping
    public void clickContinueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton)).click();
    }

    // Proceed to checkout
    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}