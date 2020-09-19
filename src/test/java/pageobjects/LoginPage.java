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
	
	public void setUserName(String userName) {
		txtUserName.sendKeys(userName);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void clickLogin() {
		loginButton.click();;
	}
}
