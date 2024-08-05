package MavenCucumber.Cucumber;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {
	
		 static WebDriver driver;
		
		
		public static WebDriver browser(String Browser) {
//OPen Firefox Browser
			if(Browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "F:\\Drivers\\geckodriver.exe");
				driver= new FirefoxDriver();
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
//Open Chrome Browser
			else if (Browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "F:\\Drivers\\chromedriver.exe");
				driver=new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
			
			else if(Browser.equalsIgnoreCase("Edge")) {
				System.setProperty("webdriver.edge.driver", "C:\\New_folder\\MicrosoftWebDriver.exe");
				driver=new EdgeDriver();
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
			

			return driver;
			

		}
		}
