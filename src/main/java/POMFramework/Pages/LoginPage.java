package POMFramework.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import POMFramework.Base.TestBase;

public class LoginPage extends TestBase {
	
	@FindBy(xpath = "//*[@id=\"txtUsername\"]")
	private WebElement loginUserName;

	@FindBy(xpath = "//*[@id=\"txtPassword\"]")
	private WebElement loginPassword;

	@FindBy(xpath = "//*[@id=\"btnLogin\"]")
	private WebElement loginButton;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void EnterUserName(String userName) {
		loginUserName.sendKeys(userName);
	}
	
	public void EnterPassword(String password) {
		loginPassword.sendKeys(password);
	}
	
	public void ClickLoginButton() {
		loginButton.click();
	}
}
