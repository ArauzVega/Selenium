package KEYWORDSFramework.Test;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
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
		
		//Instances of the classes
		KeywordsOperations perform = new KeywordsOperations();
		ReadFromFile readFile = new ReadFromFile();
		
		//Getting the data from the file
		XSSFSheet sheetData = readFile.ReadData("TestData.xlsx", "Sheet1");
		
		//Getting the data times for waits
		XSSFSheet sheetDataTimes = readFile.ReadData("TestData.xlsx", "Sheet2");
		
		ArrayList<ArrayList<String>> OUT = new ArrayList<ArrayList<String>>();
		// Get iterator to all the rows in current sheet 
        Iterator<Row> rowIterator = sheetDataTimes.iterator(); 

        while (rowIterator.hasNext()) { 
            Row row = rowIterator.next();
            ArrayList<String> InnerArray = new ArrayList<String>() ;
            // For each row, iterate through each columns 
            Iterator<Cell> cellIterator = row.cellIterator(); 

            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next(); 

                switch (cell.getCellType().toString()) { 
                	case "STRING": 
                		String c = cell.getStringCellValue();
                		InnerArray.add(c);
                		break; 
                	case "NUMERIC": 
                		int n = (int) cell.getNumericCellValue();
                		InnerArray.add(String.valueOf(n));
                		break; 
                	default :
                		break;
                 } 
             }
            	OUT.add(InnerArray); 
            }
		
		
		//Counting the rows with the important data for the test
		int rowCountData = sheetData.getLastRowNum() - sheetData.getFirstRowNum();

		
		for(int i = 1; i < rowCountData + 1; i++) {
			Row row = sheetData.getRow(i);
			
			if(row != null) {
				if(row.getCell(0) == null || row.getCell(0).toString().length() == 0) {
					System.out.println("Step: " + row.getRowNum());
					System.out.println(row.getCell(1)  + " ------ " +
							row.getCell(2) + " ------ " +
							row.getCell(3)  + " ------ " +
							row.getCell(4)  + " ------ " +
							row.getCell(5)  + " ------ " +
							row.getCell(6)  + " ------ " +
							row.getCell(7)  + " ------ " +
							row.getCell(8)  + " ------ " +
							row.getCell(9)  + " ------ " +
							row.getCell(10)  + " ------ " +
							row.getCell(11));
					
					//Sending the cells with the data that the program will need
					perform.Perform(row.getCell(3), row.getCell(4), row.getCell(5), 
							row.getCell(6), row.getCell(7), row.getCell(8), row.getCell(9), 
							row.getCell(10), row.getCell(11), OUT);
				} else {
					System.out.println("Step: " + row.getRowNum());
					System.out.println("New test case: " + row.getCell(0) + " ------ " +
							row.getCell(1)  + " ------ " +
							row.getCell(2)  + " ------ " +
							row.getCell(3)  + " ------ " +
							row.getCell(4)  + " ------ " +
							row.getCell(5)  + " ------ " +
							row.getCell(6)  + " ------ " +
							row.getCell(7)  + " ------ " +
							row.getCell(8)  + " ------ " +
							row.getCell(9)  + " ------ " +
							row.getCell(10)  + " ------ " +
							row.getCell(11));
					
					//Sending the cells with the data that the program will need
					perform.Perform(row.getCell(3), row.getCell(4), row.getCell(5), 
							row.getCell(6), row.getCell(7), row.getCell(8), row.getCell(9), 
							row.getCell(10), row.getCell(11), OUT);
				}
			}
		}
	}
}
