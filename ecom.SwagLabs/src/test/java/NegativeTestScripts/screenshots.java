package NegativeTestScripts;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class screenshots {

    public static void TakeScreenShot(
            WebDriver driver, String screenshotName)
            throws IOException {

        // Create screenshot folder
        File folder = new File("./ss");

        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Capture screenshot
        TakesScreenshot tks = (TakesScreenshot) driver;
        File scr = tks.getScreenshotAs(OutputType.FILE);

        // Save screenshot
        File dest = new File(
                folder, screenshotName + ".png");

        FileHandler.copy(scr, dest);

        System.out.println(
                "Screenshot saved at: " + dest.getAbsolutePath());
    }
}