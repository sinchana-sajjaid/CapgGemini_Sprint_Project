package ecom.SwagLabs.genericUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility {
    public String readDatafromPropertyFile(String key) throws Exception {
        Properties prop = new Properties();

        try (FileInputStream fis =
                new FileInputStream("./src/main/resources/CommonData.properties")) {
            prop.load(fis);
        }

        return prop.getProperty(key);
    }
}
