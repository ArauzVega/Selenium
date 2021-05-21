package KEYWORDSFramework;

import java.time.Duration;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KeywordsOperations {

	//Instance of driver
	WebDriver driver;
	
	//Method with the keywords to use in the workflow of test
	public void Perform(Cell Action, Cell ObjectType, Cell Object, Cell Locator, Cell Url,
			Cell TestData, Cell Condition, Cell Element, Cell LocatorOfElement, 
			ArrayList<ArrayList<String>> DataTimes) throws Exception{
		
		switch (Action.toString().toUpperCase()) {
		//To instance the chrome driver to run the tests
		case "OPEN-CHROME":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
			
		//To instance the firefox driver to run the tests	
		case "OPEN-FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
			
		//To open the url given
		case "GO-TO":
			driver.get(Url.toString());
			break;
			
		//To maximize the window opened
		case "MAXIMIZE-WINDOW":
			driver.manage().window().maximize();
			break;
			
		//To write the given data from the file into the element
		case "SEND-KEYS":
			driver.findElement(FindObject(ObjectType.toString(), Locator.toString())).sendKeys(TestData.toString());
			break;
			
		//To perform a click on the element
		case "CLICK":
			driver.findElement(FindObject(ObjectType.toString(), Locator.toString())).click();
			break;
			
		//To use the implicit wait
		case "IMPLICIT-WAIT":
			
			int implicitTime = GetTimeToWait(DataTimes, ObjectType.toString());
			
			driver.manage().timeouts().implicitlyWait(implicitTime, TimeUnit.SECONDS);
			
			break;
			
		//To use the explicit wait
		case "EXPLICIT-WAIT":
			
			int explicitTime = GetTimeToWait(DataTimes, ObjectType.toString());
			
			ExplicitWaitWithCondition(Condition.toString(), Element.toString(), 
					LocatorOfElement.toString(), explicitTime);
			
			break;
			
		//To use the fluent wait
		case "FLUENT-WAIT":
			
			int timeToWait = 0;
			int frecuencyTime = 0;
			
			for(ArrayList<String> data: DataTimes) {
				String[] myArray = new String[data.size()];
				data.toArray(myArray);

			      for(int i=0; i<myArray.length; i++){
			         if(myArray[i].equals(ObjectType.toString())) {
			        	 timeToWait = Integer.parseInt(myArray[2]);
			        	 frecuencyTime = Integer.parseInt(myArray[3]);
			         }
			      }
			}
			
			MyFluentWaitFunction(Condition.toString(), Element.toString(), LocatorOfElement.toString(), 
					timeToWait, frecuencyTime);
			
			break;
			
		//To use the sleep method
		case "SLEEP":
			String timeToSleep = TestData.toString();
			int timeS = Integer.parseInt(timeToSleep);
			Thread.sleep(timeS);
			break;
			
		//To select an option from the select item by index
		case "SELECT-BY-INDEX":
			Select drpSelectIndex = new Select(driver.findElement(FindObject(
					ObjectType.toString(), Object.toString())));
			int index = Integer.parseInt(TestData.toString());
			drpSelectIndex.selectByIndex(index);
			break;
			
		//To select an option from the select item by value
		case "SELECT-BY-VALUE":
			Select drpSelectValue = new Select(driver.findElement(FindObject(
					ObjectType.toString(), Object.toString())));
			drpSelectValue.selectByValue(TestData.toString());
			break;
			
		//To perform the mouse over an element
		case "HOVER-OVER":
			Actions hoverOver = new Actions(driver);
			hoverOver.moveToElement(driver.findElement(FindObject(
					ObjectType.toString(), Object.toString()))).click().build().perform();
			break;
			
		//To perform a click on an element using javaScript	
		case "JSCLICK":
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click", driver.findElement(FindObject(
					ObjectType.toString(), Locator.toString())));
			break;
			
		//To switch to an active window
		case "SWITCH-ACTIVE-WINDOW":
			for(String handle1 : driver.getWindowHandles()) {
				driver.switchTo().window(handle1);
			}
			break;
			
		//To drag an element and drop it into another element
		case "DRAG-AND-DROP":
			
			break;
			
		//To set the window size
		case "SET-WINDOW-SIZE":
			driver.manage().window().setSize(new Dimension(1024, 768));
			break;
			
		//To close the browser after running the test
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
		//To Find an element by xpath
		if(ObjectType.equalsIgnoreCase("XPATH")) {
			return By.xpath(Locator);
		} 
		//To Find an element by name
		else if(ObjectType.equalsIgnoreCase("NAME")) {
			return By.name(Locator);
		} 
		//To Find an element by id
		else if(ObjectType.equalsIgnoreCase("ID")) {
			return By.id(Locator);
		} 
		//To Find an element by css
		else if(ObjectType.equalsIgnoreCase("CSS")) {
			return By.cssSelector(Locator);
		} 
		//To Find an element by class name
		else if(ObjectType.equalsIgnoreCase("CLASS-NAME")) {
			return By.className(Locator);
		}
		//To Find an element by link text
		else if(ObjectType.equalsIgnoreCase("LINK-TEXT")) {
			return By.linkText(Locator);
		}
		//To Find an element by partial link
		else if(ObjectType.equalsIgnoreCase("PARTIAL-LINK")) {
			return By.partialLinkText(Locator);
		}
		
		throw new Exception("Missig the object type");
	}
	
	public int GetTimeToWait(ArrayList<ArrayList<String>> dataTimes, String ObjectType) {
		int time = 0;
		
		for(ArrayList<String> data: dataTimes) {
			String[] myArray = new String[data.size()];
			data.toArray(myArray);

		      for(int i=0; i<myArray.length; i++){
		         if(myArray[i].equals(ObjectType)) {
		        	 time = Integer.parseInt(myArray[2]);
		         }
		      }
		}
		
		return time;
	}
	
	public void ExplicitWaitWithCondition(String Condition, String Element, String Locator, int Time) 
			throws Exception {
		switch (Condition) {
		case "ELEMENTTOBECLICKABLE":
			WebElement elementWanted = new WebDriverWait(driver, Time)
				.until(ExpectedConditions.elementToBeClickable(FindObject(Element, Locator)));
			
			elementWanted.click();
			break;

		default:
			break;
		}
	}
	
	public void MyFluentWaitFunction(String Condition, String Element, String Locator, 
			int timeToWait, int frecuencyTime) throws Exception {
		
		
		Wait<WebDriver> waitObject = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeToWait))
				.pollingEvery(Duration.ofSeconds(frecuencyTime))
				.ignoring(NoSuchElementException.class);

		switch (Condition) {
		case "ELEMENTTOBECLICKABLE":
			WebElement elementToWait = waitObject.until(ExpectedConditions.
					elementToBeClickable(FindObject(Element, Locator)));
			elementToWait.click();
			break;

		default:
			break;
		}
		
	}
	
}
