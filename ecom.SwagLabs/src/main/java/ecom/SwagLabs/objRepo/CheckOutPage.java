package ecom.SwagLabs.objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {

    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    private WebElement fn;

    @FindBy(id = "last-name")
    private WebElement ln;

    @FindBy(id = "postal-code")
    private WebElement pcode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    public void enterFirstName(String firstname) {
        fn.sendKeys(firstname);
    }

    public void enterLastName(String lastname) {
        ln.sendKeys(lastname);
    }

    public void enterPostalCode(String postalcode) {
        pcode.sendKeys(postalcode);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public void clickCancel() {
        cancelButton.click();
    }

    public void checkoutDetails(
            String firstname,
            String lastname,
            String postalcode) {

        enterFirstName(firstname);
        enterLastName(lastname);
        enterPostalCode(postalcode);
        clickContinue();
    }
}