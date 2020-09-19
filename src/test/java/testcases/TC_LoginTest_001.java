package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void testLogin() {
		driver.get(baseURL);
		logger.info("URL is opened");
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(userName);
		logger.info("Username input success");
		lp.setPassword(password);
		logger.info("Password input success");
		lp.clickLogin();
		
		if(driver.getTitle().equals("Swag Labs")) {
			Assert.assertTrue(true);
			logger.info("Login success");
		} else {
			Assert.assertTrue(false);
			logger.info("Login failed");
		}
	}

}
