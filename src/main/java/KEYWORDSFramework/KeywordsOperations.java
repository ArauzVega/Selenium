package KEYWORDSFramework;

import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KeywordsOperations {

	WebDriver driver;
	
	public void Perform(Cell Action, Cell ObjectType, Cell Object, Cell Locator, Cell Url,
			Cell TestData) throws Exception{
		switch (Action.toString().toUpperCase()) {
		
		case "OPEN-CHROME":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		
		case "GO-TO":
			driver.get(Url.toString());
			break;
			
		case "MAXIMIZE-WINDOW":
			driver.manage().window().maximize();
			break;
			
		case "SEND-KEYS":
			driver.findElement(FindObject(ObjectType.toString(), Locator.toString())).sendKeys(TestData.toString());
			break;
		
		case "CLICK":
			driver.findElement(FindObject(ObjectType.toString(), Locator.toString())).click();
			break;
		
		case "IMPLICIT-WAIT":
			
			break;
			
		case "EXPLICIT-WAIT":
				
			break;
		
		case "FLUENT-WAIT":
			
			break;
		
		case "SLEEP":
			
			break;
		
		case "SELECT-BY-INDEX":
			
			break;
			
		case "SELECT-BY-VALUE":
			
			break;
			
		case "HOVER-OVER":
			
			break;
			
		case "JSCLICK":
			
			break;
			
		case "SWITCHWINDOW":
			
			break;
		
		case "DRAG-AND-DROP":
			
			break;
			
		case "CLOSE-BROWSER":
			driver.quit();
			break;
			
		default:
			System.out.println("Action: " + Action.toString() + ", " +
					"no registered on Performing Actions");
			break;
		}
	}
	
	public By FindObject(String ObjectType, String Locator) throws Exception {
		
		if(ObjectType.equalsIgnoreCase("XPATH")) {
			return By.xpath(Locator);
		} 
		
		else if(ObjectType.equalsIgnoreCase("NAME")) {
			return By.name(Locator);
		} 
		
		else if(ObjectType.equalsIgnoreCase("ID")) {
			return By.id(Locator);
		} 
		
		else if(ObjectType.equalsIgnoreCase("CSS")) {
			return By.cssSelector(Locator);
		} 
		
		else if(ObjectType.equalsIgnoreCase("CLASS-NAME")) {
			return By.className(Locator);
		}
		
		throw new Exception("Missig the object type");
	}
	
}
