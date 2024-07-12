package Step_def;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.model.Sheet;
import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import MavenCucumber.Cucumber.Browser;
import Runner.Login_runner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;

public class write_step extends Login_runner {
	WebDriver driver;
//INitialize browser and open myntra website	
	@Given("^user is on home page$")
	public void user_is_on_home_page() throws InterruptedException  
	{
		driver=Browser.browser("chrome");
	    driver.get("https://www.myntra.com");
	    Thread.sleep(5000);
	}
//Navigation for men watches
	@When("^user navigating to product info page$")
	public void user_navigating_to_product_info_page()  
	{
		driver.findElement(By.xpath("//input[@class=\"desktop-searchBar\"]")).sendKeys("Watches Men");
		driver.findElement(By.xpath("//span[@class=\"myntraweb-sprite desktop-iconSearch sprites-search\"]")).click();
	}
//Write in Excel
	@Then("^Details write into excel$")
	public void Details_write_into_excel() throws Throwable 
	{
		XSSFWorkbook work = new XSSFWorkbook();
		XSSFSheet sht = work.createSheet("Watches Men");
		//product details
		String pageXpath[]= {"pdp-title","pdp-price"};
		Thread.sleep(2000);		
		List<WebElement> prod_count= driver.findElements(By.xpath("//div[@class='product-productMetaInfo']"));
		String parentWinHandle= driver.getWindowHandle();
		Actions builder = new Actions(driver);		
		int total=prod_count.size();
		System.out.println("total:"+total);
		System.out.println(driver.getTitle());
		//create Header
				Row newRow = sht.createRow(0);
				newRow.createCell(0).setCellValue("Product Name");
				newRow.createCell(1).setCellValue("Product Price");
	int x=1;
				for(int i=1; i<5;i++)
		{
			String static1 = "(//*[@class=\"img-responsive\"])[";   
			String dyna = static1 +x+"]";
			x=x+2;
			builder.moveToElement(driver.findElement(By.xpath(dyna))).click().build().perform();
			//window handling			
			for(String handle : driver.getWindowHandles()) 
			{
				if(!handle.equals(parentWinHandle)) 
				{
					driver.switchTo().window(handle);
					Row newRow1 = sht.createRow(i);
					for(int j = 0;j<pageXpath.length;j++) 
					 {
						Cell cell = newRow1.createCell(j);
						String forPass = "//*[@class = '"+pageXpath[j]+"']"; 
						try {
							cell.setCellValue(driver.findElement(By.xpath(forPass)).getText().toString());
						}
						catch(NoSuchElementException e) {
							cell.setCellValue("Nil");
						}
					 }
				}
			}
			driver.close();//Close Child window
			driver.switchTo().window(parentWinHandle);
			//if(i==20) break;
//Close driver
//			driver.quit();
		}
		FileOutputStream out = new FileOutputStream(new File("F:\\Output\\mynthra.xlsx"));
		work.write(out);
		out.close();
	}
}	
