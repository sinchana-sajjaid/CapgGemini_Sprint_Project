
package ecom.SwagLabs.objRepo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HamburgerMenuPage {

    WebDriver driver;
    WebDriverWait wait;

    public HamburgerMenuPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "react-burger-cross-btn")
    private WebElement closeMenuButton;

    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItems;

    @FindBy(id = "about_sidebar_link")
    private WebElement about;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logout;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppState;

    public void openMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();

        wait.until(ExpectedConditions.visibilityOf(allItems));
    }

    public void closeMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(closeMenuButton)).click();
    }

    public void clickAllItems() {
        wait.until(ExpectedConditions.elementToBeClickable(allItems)).click();
    }

    public void clickAbout() {
        wait.until(ExpectedConditions.elementToBeClickable(about)).click();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
    }

    public void clickResetAppState() {
        wait.until(ExpectedConditions.elementToBeClickable(resetAppState)).click();
    }

    public boolean isAllItemsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(allItems)).isDisplayed();
    }

    public boolean isAboutDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(about)).isDisplayed();
    }

    public boolean isLogoutDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(logout)).isDisplayed();
    }

    public boolean isResetAppStateDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(resetAppState)).isDisplayed();
    }
}
