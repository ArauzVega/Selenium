package POMFramework.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import POMFramework.Base.TestBase;

public class PerformancePage extends TestBase {
	
	Actions actions;
	
	@FindBy(xpath = "//*[@id=\"menu__Performance\"]/b")
	private WebElement perfomanceTab;
	
	@FindBy(xpath = "//*[@id=\"menu_performance_ManageReviews\"]")
	private WebElement manageReviewsOption;
	
	@FindBy(xpath = "//*[@id=\"menu_performance_myPerformanceReview\"]")
	private WebElement myReviewsOption;
	
	public PerformancePage() {
		PageFactory.initElements(driver, this);
	}
	
	public void performanceManageReviewsMyreviews() {
		actions = new Actions(driver);
		actions.moveToElement(perfomanceTab).moveToElement(manageReviewsOption)
			.moveToElement(myReviewsOption).click().build().perform();
	}
}
