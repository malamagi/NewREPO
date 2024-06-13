package testng_GoldRates_Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;


public class PageClass {
	

    private WebDriver driver;
    private String baseUrl;
    

    @FindBy(tagName = "h2")
    private List<WebElement> headings;

    @FindBy(tagName = "table")
    private List<WebElement> tables;

    public PageClass(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
        PageFactory.initElements(driver, this);
    }

    public void navigateTo() {
        driver.get(baseUrl);
    }

    public List<WebElement> getAllHeadings() {
        return headings;
    }
    public List<WebElement> getAllTables() {
        return tables;
    }


}
