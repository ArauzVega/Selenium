package POMFramework.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import POMFramework.Base.TestBase;

public class MyInfoPage extends TestBase {
	
	@FindBy(xpath = "//*[@id=\"menu_pim_viewMyDetails\"]/b")
	private WebElement myInfoTab;
	
	@FindBy(xpath = "//*[@id=\"btnEditCustom\"]")
	private WebElement btnEditBloodType;
	
	@FindBy(xpath = "//*[@id=\"frmEmpCustomFields\"]/ol/li/select")
	private WebElement bloodTypeSelect;
	
	public MyInfoPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickMyInfoTab() {
		myInfoTab.click();
	}
	
	public void clickEditButtonBloodType() {
		btnEditBloodType.click();
	}
	
	public void selectbyValueBloodType(String value) {
		Select bloodSelect = new Select(bloodTypeSelect);
		bloodSelect.selectByValue(value);
	}
}
