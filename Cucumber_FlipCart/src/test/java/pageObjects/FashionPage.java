package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FashionPage  {
	WebDriver driver;
	WebDriverWait wait;

	public FashionPage(WebDriver driver)
	{
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@class='_1ch8e_'][1]")
	private WebElement FashionCategory;

	@FindBy(xpath="//a[@class='_3490ry'][2]")
	private WebElement FashionSubCategory;

	@FindBy(xpath="//div[@class='_1sdMkc LFEi7Z']")
	private List<WebElement>productList;

	@FindBy(xpath="(//div[@class='cPHDOP col-12-12'])[5]")
	private List<WebElement>productName;

	@FindBy(xpath="//div[@class='DOjaWF gdgoEp col-8-12']//li")
	private List<WebElement>productDetails;


	public void navigateToFashion()
	{

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", FashionCategory);
		wait.until(ExpectedConditions.elementToBeClickable(FashionCategory)).click();
		System.out.println("Navigated to Fashion category");
	}

	public void selectSubCategory()
	{
		wait.until(ExpectedConditions.elementToBeClickable(FashionSubCategory)).click();
		System.out.println("Navigated to Fashion sub-category");
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

			System.out.println("Fashion product is clicked successfully");
			System.out.println("Selected product at index: " + index );


		}
	}

	public String getProductName()
	{

		WebElement productDetailsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='container']"))); // Replace with your container locator


		WebElement productNameElement = productDetailsContainer.findElement(By.xpath("(//div[@class='cPHDOP col-12-12'])[5]"));  // Replace with your product name XPath


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

				//switches to the new window
				for (String handle : driver.getWindowHandles()) {
					if (!handle.equals(originalWindowHandle)) {
						driver.switchTo().window(handle);
						break;
					}
				}

				//product description page
				WebElement productDetailsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='container']")));
				
				//product name and price
				WebElement productNameElement = productDetailsContainer.findElement(By.xpath("(//div[@class='cPHDOP col-12-12'])[5]"));

				//product details
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='DOjaWF gdgoEp col-8-12']//li")));


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
