package goldRateBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

	 protected static WebDriver driver;
	    private Properties properties;

	    public void initialize() {
	        properties = new Properties();
	        try (FileInputStream file = new FileInputStream("src/test/resources/config.properties")) {
	            properties.load(file);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	    }

	    public String getProperty(String key) {
	        return properties.getProperty(key);
	    }

	    public void quit() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}
