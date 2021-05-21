package KEYWORDSFramework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFromFile {
	
	public XSSFSheet ReadData(String FileName, String SheetName) throws IOException {
		
		FileInputStream file = new FileInputStream(FileName);
		
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		XSSFSheet sheet = workbook.getSheet(SheetName);
		
		return sheet;
	}
	
}
