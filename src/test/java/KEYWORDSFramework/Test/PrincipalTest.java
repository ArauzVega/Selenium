package KEYWORDSFramework.Test;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.Test;

import KEYWORDSFramework.KeywordsOperations;
import KEYWORDSFramework.ReadFromFile;
import io.qameta.allure.Description;

public class PrincipalTest {
	
	@Test
	@Description("Principal test to run the workflow")
	public void Principal_testcase() throws Exception {
		
		KeywordsOperations perform = new KeywordsOperations();
		
		ReadFromFile readFile = new ReadFromFile();
		
		XSSFSheet sheet = readFile.ReadData("TestData.xlsx", "Sheet1");
		
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		
		for(int i = 1; i < rowCount + 1; i++) {
			Row row = sheet.getRow(i);
			
			if(row != null) {
				if(row.getCell(0) != null) {
					System.out.println("Row: " + row.getRowNum());
					System.out.println(row.getCell(0) + " ------ " +
							row.getCell(1)  + " ------ " +
							row.getCell(2) + " ------ " +
							row.getCell(3)  + " ------ " +
							row.getCell(4)  + " ------ " +
							row.getCell(5)  + " ------ " +
							row.getCell(6)  + " ------ " +
							row.getCell(7)  + " ------ " +
							row.getCell(8));
					
					perform.Perform(row.getCell(3), row.getCell(4), row.getCell(5), 
							row.getCell(6), row.getCell(7), row.getCell(8));
				} else {
					System.out.println("Row: " + row.getRowNum());
					System.out.println(row.getCell(1)  + " ------ " +
							row.getCell(2)  + " ------ " +
							row.getCell(3)  + " ------ " +
							row.getCell(4)  + " ------ " +
							row.getCell(5)  + " ------ " +
							row.getCell(6)  + " ------ " +
							row.getCell(7)  + " ------ " +
							row.getCell(8));
					
					perform.Perform(row.getCell(3), row.getCell(4), row.getCell(5), 
							row.getCell(6), row.getCell(7), row.getCell(8));
				}
			}
		}
	}
}
