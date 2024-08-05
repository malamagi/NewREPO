package Step_def;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import MavenCucumber.Cucumber.Browser;
import Runner.Login_runner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Payment_steps extends Login_runner {
	WebDriver driver;
	@Given("^User is on Home Page1$")
	public void user_is_on_Home_Page() throws Throwable
	{
// OPen Chrome browser
		driver=Browser.browser("chrome");
//Open Mytra Website
		driver.get("https://www.myntra.com");
	    Thread.sleep(5000);
	}

	@When("^user selects a product$")
	public void user_selects_a_product()
	
	{
//Search Mens watches
		driver.findElement(By.xpath("//input[@class=\"desktop-searchBar\"]")).sendKeys("Watches Men");
		driver.findElement(By.xpath("//span[@class=\"myntraweb-sprite desktop-iconSearch sprites-search\"]")).click();
	}

	@Then("^user bag the product$")
	public void user_bag_the_product() throws Throwable 
	{
	   Actions act =  new Actions(driver);
	  // act.moveToElement(driver.findElement(By.xpath("\"//*[@id=\\\"desktopSearchResults\\\"]/div[2]/section/ul/li[3]/a/div[1]/div\""))).build().perform().click-
	   
	   String parentWinHandle = driver.getWindowHandle();
       System.out.println("Parent window handle: " + parentWinHandle);
       // Locate 'Click to open a new browser window!' button using id
       WebElement newWindowBtn = driver.findElement(By.xpath("//div[@class='product-productMetaInfo'][1]"));
       // Click the button to open a new window
       newWindowBtn.click();
       // Get the window handles of all open windows
       Set<String> winHandles = driver.getWindowHandles();
       // Loop through all handles
       for(String handle: winHandles){
           if(!handle.equals(parentWinHandle))
           {
        	   driver.switchTo().window(handle);

        	   WebElement Add_To_Bag = driver.findElement(By.xpath("//*[@class=\"pdp-add-to-bag pdp-button pdp-flex pdp-center\" and text()=\"ADD TO BAG\"]"));
               Add_To_Bag.click();
               
               driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
               
               //to reach cart to buy the product in the cart
               WebElement Go_To_Cart = driver.findElement(By.xpath("//*[text()='GO TO BAG']"));
               Go_To_Cart.click();
             
               WebElement Place_Order = driver.findElement(By.xpath("//*[text()=\"Place Order\"]"));
               Place_Order.click();
                
           Thread.sleep(1000);
           System.out.println("Title of the new window: " +
        	driver.getTitle());
           System.out.println("Closing the new window...");
	   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
}
	}
	}
