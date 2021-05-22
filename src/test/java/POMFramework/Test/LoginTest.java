package POMFramework.Test;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import POMFramework.Base.TestBase;
import POMFramework.Pages.LoginPage;
import POMFramework.Pages.MyInfoPage;
import POMFramework.Pages.PerformancePage;

public class LoginTest extends TestBase {
	
	LoginPage loginPage;
	MyInfoPage myInfoPage;
	PerformancePage performPage;
	
	//Extent Report instances
	ExtentReports extentReport;
	ExtentSparkReporter sparkReporter;
	ExtentTest test;
	
	public LoginTest() {
		super();
	}
	
	@BeforeSuite
	public void SetUp() {
		String url = "https://opensource-demo.orangehrmlive.com/";
		Initialize(url);
		
		extentReport = new ExtentReports();
		sparkReporter = new ExtentSparkReporter("extent-reports/POM/POMreport.html");
		extentReport.attachReporter(sparkReporter);
	}
	
	@Test(priority = 1)
	public void LoginSuccess() {
		//Extent test report
		test = extentReport.createTest("TC1_LoginSuccess");
		
		loginPage = new LoginPage();

		test.info("Writing the user name");
		loginPage.EnterUserName("Admin");

		test.info("Writing the password");
		loginPage.EnterPassword("admin123");

		test.info("Clicking the login button");
		loginPage.ClickLoginButton();
		
		test.pass("Login was completed successfully");
	}
	
	@Test(priority = 2)
	public void EditMyInfo() {
		//Extent test report
		test = extentReport.createTest("TC2_EditMyInfo");
		
		myInfoPage = new MyInfoPage();
		
		test.info("Clicking My Info tab");
		myInfoPage.clickMyInfoTab();
		
		test.info("Clicking the edit type of blood button");
		myInfoPage.clickEditButtonBloodType();
		
		test.info("Waiting implicitly for the select of types of blood");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		test.info("Selecting the type of blood by the value");
		myInfoPage.selectbyValueBloodType("AB+");
		
		test.info("Saving the changes of the type of blood");
		myInfoPage.clickEditButtonBloodType();
		
		test.pass("The type of blood has been edited successfully");
	}
	
	@Test(priority = 3)
	public void SeeManageReviewsMyReviews() {
		//Extent test report
		test = extentReport.createTest("TC3_SeeManageReviewsMyReviews");
		
		performPage = new PerformancePage();
		
		test.info("Performing the mouse over tab and selecting my reviews");
		performPage.performanceManageReviewsMyreviews();
		
		test.pass("The performance tab have been clicked successfully");
	}
	
	@AfterSuite
	public void Quit() {
		driver.quit();
		extentReport.flush();
	}
}
