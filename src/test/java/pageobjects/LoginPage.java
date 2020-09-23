package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;;

public class LoginPage {
	
	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(id = "user-name")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(id = "password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(id = "login-button")
	@CacheLookup
	WebElement loginButton;
	
	@FindBy(xpath = "//*[@id=\"login_button_container\"]/div/form/h3/button")
	@CacheLookup
	WebElement errorButton;
	
	public void clickUserName() {
		txtUserName.click();
	}
	
	public void setUserName(String userName) {
		txtUserName.sendKeys(userName);
	}
	
	public void resetUserName() {
		txtUserName.clear();
	}
	
	public String userNameContent() {
		return txtUserName.getText();
	}
	
	public void clickPassword() {
		txtPassword.click();
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void resetPassword() {
		txtPassword.clear();
	}
	
	public String pwdContent() {
		return txtPassword.getText();
	}

	public void clickLogin() {
		loginButton.click();;
	}
	
	public void clickErrorButton() {
		errorButton.click();
	}
}
