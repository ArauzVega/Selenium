package POMFramework.Test;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import POMFramework.Base.TestBase;
import POMFramework.Pages.LoginPage;
import POMFramework.Pages.MyInfoPage;
import POMFramework.Pages.PerformancePage;

public class LoginTest extends TestBase {
	
	LoginPage loginPage;
	MyInfoPage myInfoPage;
	PerformancePage performPage;
	
	public LoginTest() {
		super();
	}
	
	@BeforeTest
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
	
	@Test(priority = 2)
	public void editMyInfo() {
		myInfoPage = new MyInfoPage();
		
		myInfoPage.clickMyInfoTab();
		myInfoPage.clickEditButtonBloodType();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		myInfoPage.selectbyValueBloodType("AB+");
		myInfoPage.clickEditButtonBloodType();
	}
	
	@Test(priority = 3)
	public void seeManageReviewsMyReviews() {
		performPage = new PerformancePage();
		
		performPage.performanceManageReviewsMyreviews();
	}
	
	@AfterTest
	public void Quit() {
		driver.quit();
	}
}
