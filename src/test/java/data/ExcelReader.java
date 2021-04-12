package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	static FileInputStream fis=null;
	
	public FileInputStream getFileInputStream()
	{
		String filePath=System.getProperty("user.dir")+"\\src\\test\\java\\data\\AddressDetails.xlsx";
		File srcFile=new File(filePath);
		try {
			fis=new FileInputStream(srcFile);
		} catch (FileNotFoundException e) {
			System.out.println("Test data file not found check file path");
		}
		return fis;
	}
	
	public Object[][] getExcelData() throws IOException
	{
		fis=getFileInputStream();
		XSSFWorkbook wB=new XSSFWorkbook(fis);
		XSSFSheet sheet=wB.getSheetAt(0);
		//int TotalNumberOfRows=sheet.getLastRowNum()+1;
		int TotalNumberOfRows=1;
		int TotalNumberOfCols=6;
		String [][]arrayExcelSheet= new String[TotalNumberOfRows][TotalNumberOfCols];
		
		for (int i = 0; i < TotalNumberOfRows; i++) {
			for (int j = 0; j < TotalNumberOfCols; j++) {
				XSSFRow row=sheet.getRow(i);
				arrayExcelSheet[i][j]=row.getCell(j).toString();
			}
		}
		wB.close();
		return arrayExcelSheet;
	}
	
}
