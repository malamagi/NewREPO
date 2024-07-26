package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ElectronicsPage {

	WebDriver driver;
	WebDriverWait wait;

	public ElectronicsPage(WebDriver driver)
	{
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="(//img[@class='_2puWtW _3a3qyb'])[4]")
	private WebElement electronicsCategory;


	@FindBy(xpath="(//a[@class='_3490ry'])[2]")
	private WebElement electronicsSubCategory;

	
	@FindBy(xpath="//div[@class='slAVV4']")
	private List<WebElement>productList;


	// from listing page
	@FindBy(xpath="(//div[@class='cPHDOP col-12-12'])[6]")
	private List<WebElement>productName;


	@FindBy(xpath="//div[@class='U+9u4y']")
	private List<WebElement>productDetails;


	public void navigateToElectronics()
	{
		//wait.until(ExpectedConditions.elementToBeClickable(electronicsCategory)).click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(electronicsCategory)).click();
		}catch(org.openqa.selenium.StaleElementReferenceException ex) {
			wait.until(ExpectedConditions.elementToBeClickable(electronicsCategory)).click();
		}
		System.out.println("Navigated to Electronics category");
	}

	public void selectSubCategory()
	{
		wait.until(ExpectedConditions.elementToBeClickable(electronicsSubCategory)).click();
		System.out.println("Navigated to Electronics sub-category");
	}

	public void selectProduct(int index)
	{
		
		wait.until(ExpectedConditions.visibilityOfAllElements(productList));
		int productListSize = productList.size();
		if (index >= 0 && index <productListSize) {

			WebElement product = productList.get(index);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
			wait.until(ExpectedConditions.elementToBeClickable(product));
			try
			{
				product.click();
			}
			catch (org.openqa.selenium.ElementClickInterceptedException e) {
				System.out.println("Click intercepted, trying again...");
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", product);
			}
		
			System.out.println("product is clicked successfully");
			System.out.println("Selected product at index: " + index );


		}
	
	}

	public String getProductName()
	{
		
		WebElement productDetailsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='container']"))); // Replace with your container locator

		// Find the product name element within the container using your verified XPath
		WebElement productNameElement = productDetailsContainer.findElement(By.xpath("(//div[@class='cPHDOP col-12-12'])[6]"));  // Replace with your product name XPath
	

		// Extract and return the product name text
		String productNameText= productNameElement.getText();
		System.out.println("Product Name: " + productNameText);
		return productNameText;

	}


	public String getProductDetails() {
		StringBuilder details = new StringBuilder();
		for (WebElement detail : productDetails) {
			
			details.append(detail.getText()).append("\n");
		}
		String detailsText = details.toString();
		System.out.println("Product Details: " + detailsText);
		return detailsText;
	}

	public List<String[]>getMultipleProductDetails(int numberOfProducts) {
		List<String[]>productDetailsList = new ArrayList<>();
		String originalWindowHandle = driver.getWindowHandle();

		for (int i = 0; i<numberOfProducts; i++) {
					// Print productList size for debugging
			System.out.println("Product list size: " + productList.size());
			System.out.println("Getting details for product index: " + i);

			// Check if the product list contains elements
			if (productList.size() >i) {

				// Click on product from the productList
				selectProduct(i);

				for (String handle : driver.getWindowHandles()) {
					if (!handle.equals(originalWindowHandle)) {
						driver.switchTo().window(handle);
						break;
					}
				}

				WebElement productDetailsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='container']")));
				WebElement productNameElement = productDetailsContainer.findElement(By.xpath("(//div[@class='cPHDOP col-12-12'])[6]"));

				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='UOCQB1']")));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='DOjaWF gdgoEp col-8-12']//li")));

				// PageFactory.initElements(driver, this);
				// Get details
				String name = getProductName();
				//String price = getProductPrice();
				String details = getProductDetails();

				// Store details in list
				if (name.isEmpty() || details.isEmpty()) {
					System.out.println("Product " + (i + 1) + " details are incomplete.");
				} else {
					productDetailsList.add(new String[]{name, details});
				}

				driver.close();
				driver.switchTo().window(originalWindowHandle);
				System.out.println("Navigated back to product list");

				wait.until(ExpectedConditions.visibilityOfAllElements(productList));
			}else {
				System.out.println("Product list does not contain enough elements.");
			}

			
		}
		return productDetailsList;
	}

}
