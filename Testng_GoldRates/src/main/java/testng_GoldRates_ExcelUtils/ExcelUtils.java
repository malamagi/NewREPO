package testng_GoldRates_ExcelUtils;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtils {
	
	public static void writeTablesToExcel(List<String> tableHeadings, List<List<List<String>>> allTableData, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Gold Rates");

			  int rowNum = 0;

		        for (int i = 0; i < tableHeadings.size(); i++) {
		            String heading = tableHeadings.get(i);
		            Row headingRow = sheet.createRow(rowNum++);
		            Cell headingCell = headingRow.createCell(0);
		            headingCell.setCellValue(heading);


		            List<List<String>> tableData = allTableData.get(i);
		            for (List<String> rowData : tableData) {
		            Row row = sheet.createRow(rowNum++);
		            int colNum = 0;
		            for (String cellData : rowData) {
		                Cell cell = row.createCell(colNum++);
		                cell.setCellValue(cellData);
		            }
		        }
		            rowNum++;
		        }
			try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
			    workbook.write(outputStream);
			} catch (IOException e) {
			    e.printStackTrace();
			}
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

}
