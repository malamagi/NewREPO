package stepdefinitions;


import java.io.IOException;
import java.util.List;

import baseClasses.BaseClass;
import baseClasses.ExcelUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ElectronicsPage;
import pageObjects.FashionPage;


public class FashionSteps extends BaseClass{

	FashionPage hpPage = new FashionPage(driver);


	@When("User navigates to Fashion category")
	public void user_navigates_to_Fashion_category() {
		//driver.navigate().back();
		driver.get(BaseClass.prop.getProperty("url"));
		hpPage.navigateToFashion();
		hpPage.selectSubCategory();

	}

	@Then("User selects products and writes its details to excel")
	public void user_selects_products_and_writes_its_details_to_excel() throws IOException {

		String outputExcelPath = BaseClass.prop.getProperty("outputExcelPath");
		String[] headers = {"Product Name and Price", "Product Details"};
		ExcelUtils.setHeaders(outputExcelPath, "Fashion", headers);
		ExcelUtils.setExcelFile(outputExcelPath, "Fashion");

		// Extract details of multiple products (in this case, assuming the requirement is 3 products)
		List<String[]>productDetailsList = hpPage.getMultipleProductDetails(3);

		// Write product details to Excel
		for (int i = 0; i<productDetailsList.size(); i++) {
			String[] productDetails = productDetailsList.get(i);
			

			int rowCount = ExcelUtils.getRowCount();
			if (productDetails.length>= 2) {
				ExcelUtils.setCellData(outputExcelPath, "Fashion", i+1, 0, productDetails[0]);
				ExcelUtils.setCellData(outputExcelPath, "Fashion", i+1, 1, productDetails[1]);
				//ExcelUtils.setCellData(outputExcelPath, "Electronics", i, 2, productDetails[2]);
			}else {
				// Handle cases where product details are missing (optional: log a message)
				System.out.println("Product " + (i + 1) + " details are incomplete.");
			}
		}

	}



}
