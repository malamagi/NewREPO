package testng_GoldRates_Tests;

import testng_GoldRates_Base.BaseClass;
import testng_GoldRates_ExcelUtils.ExcelUtils;
import testng_GoldRates_Page.PageClass;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestClass extends BaseClass {



	@Test
	public void extractGoldRatesToExcel() throws IOException {
		String baseUrl = getProperty("baseUrl");
		System.out.println("Base URL: " + baseUrl);
		
		PageClass goldRatesPage = new PageClass(driver, baseUrl);
		goldRatesPage.navigateTo();

		 List<WebElement> headings = goldRatesPage.getAllHeadings();
		List<WebElement> tables = goldRatesPage.getAllTables();
		
		System.out.println("Tables found: " + (tables != null ? tables.size() : 0));
		if (tables == null || tables.isEmpty()) {
			System.out.println("No tables found on the page.");
			return;
		}
		
	     List<String> tableHeadings = new ArrayList<>();
	     List<List<List<String>>>  allTableData  = new ArrayList<>();
		int headingIndex = 0;

		for (WebElement table : tables) {
			  // Extract main heading
            if (headingIndex < headings.size()) {
            	  String headingText = headings.get(headingIndex).getText();
                  tableHeadings.add(headingText);
                  // Print the heading to the console
                  System.out.println("Table Heading: " + headingText);
            }

            
			//extract table data
            List<List<String>> tableData = new ArrayList<>();
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			for (WebElement row : rows) {
				List<WebElement> cells = row.findElements(By.tagName("td"));
				List<String> cellData = new ArrayList<>();
				for (WebElement cell : cells) {
					cellData.add(cell.getText());
				}
				tableData.add(cellData);
            }
            allTableData.add(tableData);
            headingIndex++;
        }

		String filePath = properties.getProperty("outputFilePath");
		System.out.println("Output file path: " + filePath);
		ExcelUtils.writeTablesToExcel(tableHeadings,allTableData, filePath);
	}
}
