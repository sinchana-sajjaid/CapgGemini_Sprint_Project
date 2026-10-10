package login1;

import org.testng.annotations.Test;

import ecom.SwagLabs.baseClass.BaseClass;
import ecom.SwagLabs.objRepo.LoginPage;

public class loginTest extends BaseClass {

    @Test(groups = "smoke")
    public void loginPositiveTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(username, password);

        System.out.println("Positive Login Test Passed");
    }
}
