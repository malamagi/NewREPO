package MavenCucumber.Cucumber;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelReadMynthra {
//Objects creation
	XSSFWorkbook wb;
	XSSFSheet sh;
	
	public ExcelReadMynthra (String Path){ 
//Set stream for excel for data input
		try {
			File src =new File(Path);
			FileInputStream fis=new FileInputStream(src);
			wb=new XSSFWorkbook(fis);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	//For Raw value
	public String getdata2(int sheetno, int row, int cell)
	{
		sh=wb.getSheetAt(sheetno);
		String data = sh.getRow(row).getCell(cell).getRawValue();
		return data;
	}
//FOr string Value	
	public String getdata(int sheetno, int row, int cell)
	{
		sh=wb.getSheetAt(sheetno);
		String data = sh.getRow(row).getCell(cell).getStringCellValue();
		return data;
	}
	
}
