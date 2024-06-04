package testngpackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Tradeviv_Testng {

	WebDriver driver;
	XSSFWorkbook wbook;
	XSSFSheet sheet;

	@BeforeClass
	public void setup() throws IOException
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();

		String fileLocation = "./Test_Data/Login_TestData.xlsx";//Locate the file
		wbook=new XSSFWorkbook(fileLocation); //Locate the workbook
		sheet=wbook.getSheetAt(2); //get the first sheet & sheet is an object
	}

	@Test
	public void testLoginAndLogout() throws InterruptedException {
		int loginCount = 0;
		for (int i = 1; i <=5; i++) {
			XSSFRow row = sheet.getRow(i);
			//		for (int j = 0; j <2; j++) {
			//XSSFCell cell = row.getCell(j);
			XSSFCell usernameCell = row.getCell(0);
			XSSFCell passwordCell = row.getCell(1);

			String username = usernameCell.getStringCellValue();
			String password = passwordCell.getStringCellValue();

			System.out.println("Attempting login " + (i) + " with:");

			System.out.println("Username: " + username);
			System.out.println("Password: " + password);

			driver.get("https://tradeviv.com");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

			//User Icon
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement usericon=wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='row sign-in my-account-dropdown position-relative'])[1]")));
			usericon.click();

			//User Login
			WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement userlogin=wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[.='User Login'])[1]")));
			userlogin.click();

			WebElement usernamefield=driver.findElement(By.id("username"));
			WebElement passwordfield=driver.findElement(By.id("password"));

			usernamefield.clear();
			usernamefield.sendKeys(username);
			passwordfield.clear();
			passwordfield.sendKeys(password);

			// login_btn.click();  Use Javascript
			WebElement login_btn=driver.findElement(By.xpath("//*[@value='Log in']"));
			JavascriptExecutor executor=(JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click()", login_btn);

			//After Login Click for user icon for logout
			WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement logout_icon=wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("(//img[@class='img-fluid user lazy'])[1]")));
			logout_icon.click();

			//Logout Link
			driver.findElement(By.xpath("(//span[.='Logout'])[1]")).click();
			// Increment login count
			loginCount++;
			System.out.println("Logged in and out successfully " + loginCount + " time(s).");
		}

	}
	
	@AfterClass
	public void close() throws IOException {
		wbook.close();
		driver.quit();
	}
}
