package hamburgerMenu;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import ecom.SwagLabs.baseClass.BaseClass;
import ecom.SwagLabs.objRepo.HamburgerMenuPage;
import ecom.SwagLabs.objRepo.LoginPage;

public class HamburgerMenuTest extends BaseClass {

    @Test(groups = "Smoke")
    public void hamburgerMenuSmokeTest() {

        // Login to SauceDemo
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Open hamburger menu
        HamburgerMenuPage menu =
                new HamburgerMenuPage(driver);

        menu.openMenu();

        // Verify all menu options are displayed
        Assert.assertTrue(
                menu.isAllItemsDisplayed(),
                "All Items option is not displayed");

        Assert.assertTrue(
                menu.isAboutDisplayed(),
                "About option is not displayed");

        Assert.assertTrue(
                menu.isLogoutDisplayed(),
                "Logout option is not displayed");

        Assert.assertTrue(
                menu.isResetAppStateDisplayed(),
                "Reset App State option is not displayed");

        System.out.println(
                "Hamburger Menu Smoke Test Passed");
    }

    @Test(groups = "functional")
    public void hamburgerMenuAllItemsTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        HamburgerMenuPage menu =
                new HamburgerMenuPage(driver);

        menu.openMenu();
        menu.clickAllItems();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "All Items did not navigate to the products page");

        System.out.println(
                "Hamburger Menu All Items Test Passed");
    }

    @Test(groups = "functional")
    public void hamburgerMenuLogoutTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        HamburgerMenuPage menu =
                new HamburgerMenuPage(driver);

        menu.openMenu();
        menu.clickLogout();

        Assert.assertTrue(
                driver.findElements(
                        By.id("login-button")).size() > 0,
                "Logout did not return to the login page");

        System.out.println(
                "Hamburger Menu Logout Test Passed");
    }
}
