package pageObjects;


import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {



	WebDriver driver;
	WebDriverWait wait;

	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath="//div[@class='uiU-ZX']")
	private WebElement loginText;

	@FindBy(xpath="//input[@class='r4vIwl BV+Dqf']")
	private WebElement email;

	////button[@class='_1wGnMD rX1XT4 _2nD2xJ']
	@FindBy(xpath="//button[@class='QqFHMw twnTnD _7Pd1Fp']")
	private WebElement requestOTP;

	@FindBy(xpath = "(//a[@class='_1TOQfO'])[1]")
	private WebElement userName;


	public void clickLoginText()
	{

		JavascriptExecutor executor1=(JavascriptExecutor)driver;
		executor1.executeScript("arguments[0].click()",loginText);
	}


	public void enterMailID(String MailID)
	{
		//emailOrMobile.sendKeys(mobileNo);
		wait.until(ExpectedConditions.elementToBeClickable(email)).sendKeys(MailID);
		System.out.println("\n");
		System.out.println("E_Mail ID: " + MailID);
	}

	public void clickRequestOTP()
	{
		try {
			retryingFindClick(requestOTP);
		} catch (TimeoutException e) {
			System.out.println("Element not clickable: " + e.getMessage());
		}
		System.out.println("Given OTP Manually successfully");

	}

	private void retryingFindClick(WebElement element) {
		int attempts = 0;
		while (attempts < 20) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(element)).click();
				break;
			} catch (StaleElementReferenceException e) {
				System.out.println("StaleElementReferenceException caught. Retrying...");
				element = driver.findElement(By.xpath("//button[@class='QqFHMw twnTnD _7Pd1Fp']"));
			}
			attempts++;
		}
	}

	public String getUserName() {
		return wait.until(ExpectedConditions.visibilityOf(userName)).getText();

	}

}
