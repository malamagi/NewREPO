package stepdefinitions;

import java.io.IOException;
import java.util.List;

import baseClasses.BaseClass;
import baseClasses.ExcelUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ElectronicsPage;

public class ElectronicSteps extends BaseClass {

	ElectronicsPage electronicsPage = new ElectronicsPage(driver);


	@When("User navigates to Electronics category")
	public void user_navigates_to_electronics_category() {

		driver.get(BaseClass.prop.getProperty("url"));

		electronicsPage.navigateToElectronics();
		electronicsPage.selectSubCategory();

	}

	@Then("User selects a product and writes its details to excel")
	public void user_selects_a_product_and_writes_its_details_to_excel() throws IOException {
		String outputExcelPath = BaseClass.prop.getProperty("outputExcelPath");
		String[] headers = {"Product Name and Price", "Product Details"};
		ExcelUtils.setHeaders(outputExcelPath, "Electronics", headers);
		ExcelUtils.setExcelFile(outputExcelPath, "Electronics");

		// Extract details of multiple products (in this case, assuming the requirement is 3 products)
		List<String[]>productDetailsList = electronicsPage.getMultipleProductDetails(3);

		// Write product details to Excel
		for (int i = 0; i<productDetailsList.size(); i++) {
			String[] productDetails = productDetailsList.get(i);
			
			int rowCount = ExcelUtils.getRowCount();
			if (productDetails.length>= 2) {
				ExcelUtils.setCellData(outputExcelPath, "Electronics", i+1, 0, productDetails[0]);
				ExcelUtils.setCellData(outputExcelPath, "Electronics", i+1, 1, productDetails[1]);
				
			}else {
				// Handle cases where product details are missing (optional: log a message)
				System.out.println("Product " + (i + 1) + " details are incomplete.");
			}
		}

	}

}

