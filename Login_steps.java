package Step_def;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import Runner.Login_runner;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Login_steps extends Login_runner {

	
			public static WebDriver driver;
		@Given("^User is on Home Page$")
		public void user_is_on_Home_Page() throws Throwable
		{
		System.setProperty("webdriver.chrome.driver","F:\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		   driver.manage().deleteAllCookies();
		    driver.get("https://www.myntra.com");
		}

		@When("^User Navigate to LogIn Page$")
		public void user_Navigate_to_LogIn_Page() throws Throwable 
		{
			//driver.findElement(By.xpath(".//*[@id='account']/a")).click();
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//span[text()=\"Profile\"]"))).build().perform();
			WebElement SignUp = driver.findElement(By.xpath("//a[text()=\"log in\"]"));
			SignUp.click();
		}

		@And("^User enters UserName and Password$")
		public void user_enters_UserName_and_Password() throws Throwable {
			driver.findElement(By.name("email")).sendKeys("karthksinghfb@gmail.com"); 
		    driver.findElement(By.name("password")).sendKeys("9876543210");
		    driver.findElement(By.xpath("//button[text()='Log in']")).click();
		}

		@Then("^Message displayed Login Successfully$")
		public void message_displayed_Login_Successfully() throws Throwable {
			System.out.println("Login Successfully");
		}

}
