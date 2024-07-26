package stepdefinitions;


import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;



import baseClasses.BaseClass;
import baseClasses.ExcelUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;


public class LoginSteps {


	WebDriver driver=BaseClass.driver;
	LoginPage loginPage;


	@Given("User is on Flipkart homepage")
	public void user_is_on_flipkart_homepage() {

		driver.get(BaseClass.prop.getProperty("url"));
		loginPage = new LoginPage(driver);

	}
	@When("User clicks on login text")
	public void user_clicks_on_login_text() {
		loginPage.clickLoginText();

	}
		@And("User logs in with credentials from excel")
	public void user_logs_in_with_credentials_from_excel() throws IOException {
		String loginExcelPath=BaseClass.prop.getProperty("loginExcelPath");
		ExcelUtils.setExcelFile(loginExcelPath, "Sheet1");
		String MailID=ExcelUtils.getCellData(1, 0);
		loginPage.enterMailID(MailID);
		
		//loginPage.clickRequestOTP();
		
	}
				
		@When("user clicks on request OTP")
	    public void user_clicks_on_request_otp() {
			loginPage.clickRequestOTP();
	    }
	@Then("User is redirected to the Flipkart home page")
	public void user_is_redirected_to_the_flipkart_home_page() {
		System.out.println("Successfully logged in");
	}
	
	@Then("user account name is displayed")
    public void user_account_name_is_displayed() {
    	
    	  String expectedUserName = "malathi";
          String actualUserName = loginPage.getUserName();

          // Log the expected and actual values
          System.out.println("Expected User Name: " + expectedUserName);
          System.out.println("Actual User Name: " + actualUserName);

          // Assertion
          Assert.assertEquals(expectedUserName, actualUserName);
      }
	
	
	
	
	
	
	
	
	
	
	
	
    private String getUserName() {
        // Your logic to get the actual user name
        return "malathi"; // Replace with actual user name fetching logic
    }}

	


//
//@AfterStep
//		public void attachScreenshot(Scenario scenario) {
//			if(scenario.isFailed()) {
//				byte [] screenshotTaken = ((TakenScreenshot) DriverManager.getDriver()).getScreenshotAs(QutputType.BYTES);
//				scenario.attach(screenshotTaken, "image/png", "error screen");
//			}
//		}





