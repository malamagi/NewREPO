package goldRatePOM;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
public class GoldRatesPage {
	 private WebDriver driver;

	    @FindBy(tagName = "table")
	    private List<WebElement> tables;

	    @FindBy(xpath = "//h2")
	    private List<WebElement> headings;

	    public GoldRatesPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    public void navigateTo() {
	        driver.get("https://www.goodreturns.in/gold-rates/");
	    }

	    public List<WebElement> getAllTables() {
	        return tables;
	    }

	    public List<WebElement> getAllHeadings() {
	        return headings;
	    }

}
