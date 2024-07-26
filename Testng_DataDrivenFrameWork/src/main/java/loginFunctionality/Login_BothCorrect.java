package loginFunctionality;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Login_BothCorrect {
	
	String [][] data={
		{"Admin","admin123"}
	};
	
	@DataProvider(name="logindata")
	public String [][] datadriven_login()
	{
		return data;
	}
	
	@Test(dataProvider = "logindata")
	//@Parameters({"userN","passW"})
	public void BothCorrect(String uname,String pword)
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		try {
			// Wait for the username field to be visible
			WebElement userN = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
			System.out.println("Username field found");
			userN.sendKeys("uname");

			// Wait for the password field to be visible
			WebElement passW = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
			System.out.println("Password field found");
			passW.sendKeys("pword");

			// Wait for the login button to be clickable
			WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'oxd-button')]")));
			System.out.println("Login button found");
			login.click();

			// Additional steps to verify login success can be added here

		} catch (Exception e) {
			System.out.println("Exception occurred: " + e.getMessage());
		} finally {

			driver.quit();
		}}
}
