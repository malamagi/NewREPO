package Step_def;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import MavenCucumber.Cucumber.Browser;
import MavenCucumber.Cucumber.ExcelReadMynthra;
import Runner.Login_runner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Signup_step extends Login_runner {

		//driver is a WebDriver Instance
		WebDriver driver;

		ExcelReadMynthra obj=new ExcelReadMynthra("F:\\Inputs\\Myntra_data.xlsx");
		
		//This Given method is to invoke a browser
		@Given("^User is on the application Web Site$")
		public void user_is_on_the_application_Web_Site() throws InterruptedException  
		{
			driver=Browser.browser("chrome");
		    driver.get("https://www.myntra.com");
		    Thread.sleep(5000);
		}

		//To reach sign up Link
		@When("^User Opens the SignUp Link$")
		public void user_Opens_the_SignUp_Link()  
		{

			
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//span[text()=\"Profile\"]"))).build().perform();

			WebElement SignUp = driver.findElement(By.xpath("//a[text()=\"Sign up\"]"));
			SignUp.click();

//			driver.findElement(By.xpath("//a[@class='login-create-account-link login-link']")).click();
			
		}

		@Then("^User Enters Email Id and Name and Mobile Number, Selects the Gender Category and Clicks the \"([^\"]*)\" Button$")
		public void user_Enters_Email_Id_and_Name_and_Mobile_Number_Selects_the_Gender_Category_and_Clicks_the_Button(String arg1) throws IOException 
		{
	      //Feed data from excel to delivery Address details
		    driver.findElement(By.name("email")).sendKeys(obj.getdata(0, 0, 0));
		    driver.findElement(By.name("password")).sendKeys(obj.getdata(0, 1, 0));
		    driver.findElement(By.name("mobile")).sendKeys(obj.getdata2(0, 2, 0));
		  //Click Male in check box
		    driver.findElement(By.xpath("//input[@id=\"male\"]")).click();
		    
		    //validate the "REGISTER" button is present or not
		    boolean RegistrationButton = driver.findElement(By.xpath("//button[text()=\"REGISTER\"]")).isEnabled();
		    
		    Assert.assertEquals(true, RegistrationButton);  
		}
	}


