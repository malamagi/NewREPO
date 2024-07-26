package goldRateExcel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
public class ExcelUtils {

	public static void writeTablesToExcel(List<String> headings, List<List<List<String>>> allTableData, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
			for (int i = 0; i < headings.size(); i++) {
			    String heading = headings.get(i);
			    List<List<String>> tableData = allTableData.get(i);
			    Sheet sheet = workbook.createSheet(heading);

			    for (int rowIndex = 0; rowIndex < tableData.size(); rowIndex++) {
			        Row row = sheet.createRow(rowIndex);
			        List<String> rowData = tableData.get(rowIndex);
			        for (int colIndex = 0; colIndex < rowData.size(); colIndex++) {
			            row.createCell(colIndex).setCellValue(rowData.get(colIndex));
			        }
			    }
			}

			try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
			    workbook.write(fileOut);
			} catch (IOException e) {
			    e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
