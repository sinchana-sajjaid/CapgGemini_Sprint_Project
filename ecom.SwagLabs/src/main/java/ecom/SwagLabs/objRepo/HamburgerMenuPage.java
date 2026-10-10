package ecom.SwagLabs.objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HamburgerMenuPage {

    WebDriver driver;

    public HamburgerMenuPage(WebDriver driver) {
        this.driver = driver;
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
        menuButton.click();
    }

    public void closeMenu() {
        closeMenuButton.click();
    }

    public void clickAllItems() {
        allItems.click();
    }

    public void clickAbout() {
        about.click();
    }

    public void clickLogout() {
        logout.click();
    }

    public void clickResetAppState() {
        resetAppState.click();
    }

    public boolean isAllItemsDisplayed() {
        return allItems.isDisplayed();
    }

    public boolean isAboutDisplayed() {
        return about.isDisplayed();
    }

    public boolean isLogoutDisplayed() {
        return logout.isDisplayed();
    }

    public boolean isResetAppStateDisplayed() {
        return resetAppState.isDisplayed();
    }
}