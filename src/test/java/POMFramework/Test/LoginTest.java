package POMFramework.Test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import POMFramework.Base.TestBase;
import POMFramework.Pages.LoginPage;

public class LoginTest extends TestBase {
	
	LoginPage loginPage;
	
	public LoginTest() {
		super();
	}
	
	@BeforeMethod
	public void SetUp() {
		String url = "https://opensource-demo.orangehrmlive.com/";
		Initialize(url);
	}
	
	@Test(priority = 1)
	public void LoginSuccess_testcase() {
		loginPage = new LoginPage();
		
		loginPage.EnterUserName("Admin");
		loginPage.EnterPassword("admin123");
		
		loginPage.ClickLoginButton();
	}
	
	@AfterMethod
	public void Quit() {
		driver.quit();
	}
}
