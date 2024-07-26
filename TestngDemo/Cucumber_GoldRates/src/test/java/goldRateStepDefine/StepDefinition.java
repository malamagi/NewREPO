package goldRateStepDefine;

import goldRateBase.BaseClass;
import goldRatePOM.GoldRatesPage;
import goldRateExcel.ExcelUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class StepDefinition extends BaseClass {

	   private GoldRatesPage goldRatesPage;
	    private List<String> tableHeadings = new ArrayList<>();
	    private List<List<List<String>>> allTableData = new ArrayList<>();
	    private String excelFilePath;

	    @Given("I navigate to the gold rates page")
	    public void i_navigate_to_the_gold_rates_page() {
	        initialize();
	        goldRatesPage = new GoldRatesPage(driver);
	        goldRatesPage.navigateTo();
	        System.out.println("Navigated to the gold rates page");
	    }

	    @When("I extract the gold rates table data with configurations")
	    public void i_extract_the_gold_rates_table_data_with_configurations(DataTable dataTable) {
	        List<Map<String, String>> config = dataTable.asMaps(String.class, String.class);
	        excelFilePath = getProperty("excelFilePath");
	        
	        List<WebElement> headings = goldRatesPage.getAllHeadings();
	        List<WebElement> tables = goldRatesPage.getAllTables();
	        int headingIndex = 0;

	        for (WebElement table : tables) {
	            if (headingIndex < headings.size()) {
	                String headingText = headings.get(headingIndex).getText();
	                tableHeadings.add(headingText);
	                System.out.println("Extracting data for table heading: " + headingText);
	            }

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
	    }

	    @Then("I write the data to an Excel file at {string}")
	    public void i_write_the_data_to_an_excel_file_at(String filePath) {
	        ExcelUtils.writeTablesToExcel(tableHeadings, allTableData, filePath);
	        System.out.println("Data has been written to the Excel file at: " + filePath);
	        quit(); 
	    }

	    @Then("I print all table headings to the console")
	    public void i_print_all_table_headings_to_the_console() {
	        System.out.println("Printing all table headings:");
	        for (String heading : tableHeadings) {
	            System.out.println("Table Heading: " + heading);
	        }
	        quit();
	    }
}
