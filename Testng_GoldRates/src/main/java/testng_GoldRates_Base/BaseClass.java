package testng_GoldRates_Base;




import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {
	

    protected WebDriver driver;
    protected Properties properties;

   
	@BeforeClass
    public void setUp() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/testng_GoldRates_Resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(properties.getProperty("implicitWait"))));
        driver.manage().window().maximize();
    }

	 public String getProperty(String key) {
	        return properties.getProperty(key);
	    } 
	 
	@AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
