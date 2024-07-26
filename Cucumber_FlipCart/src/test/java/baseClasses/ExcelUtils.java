package baseClasses;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class ExcelUtils {
	private static Workbook workbook;
	private static Sheet sheet;

	public static void setExcelFile(String path, String sheetName) throws IOException {
		FileInputStream file = new FileInputStream(path);
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheet(sheetName);
	}

	public static String getCellData(int rowNum, int colNum) {
		Cell cell = sheet.getRow(rowNum).getCell(colNum);
		//return String.valueOf((int) cell.getNumericCellValue());
		//return cell.getStringCellValue();

		CellType cellType = cell.getCellType();
		switch (cellType)
		{
		case STRING :System.out.print(cell.getStringCellValue()); 
		return cell.getStringCellValue(); 
		case NUMERIC :System.out.print(cell.getNumericCellValue()); 
		return String.valueOf((long)cell.getNumericCellValue());

		}
		return"";

	}

	public static void setCellData(String path, String sheetName, int rowNum, int colNum, String value) throws IOException {
		//sheet.getRow(rowNum).createCell(colNum).setCellValue(value);

		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum);
		}
		Cell cell = row.createCell(colNum, CellType.STRING);
		cell.setCellValue(value);

		FileOutputStream fileOut = new FileOutputStream(path);
		workbook.write(fileOut);
		fileOut.close();
	}

	public static int getRowCount() {
		return sheet.getPhysicalNumberOfRows();
	}


	public static void setHeaders(String path, String sheetName, String[] headers) throws IOException {
		setExcelFile(path, sheetName);
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i<headers.length; i++) {
			Cell cell = headerRow.createCell(i, CellType.STRING);
			cell.setCellValue(headers[i]);
		}
		FileOutputStream fileOut = new FileOutputStream(path);
		workbook.write(fileOut);
		fileOut.close();
	}
}
