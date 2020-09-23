package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.LoginPage;
import utilities.ExcelUtils;

public class TC_LoginDDT_001 extends BaseClass {
	
	@Test(dataProvider="loginData")
	public void loginDDT(String uname, String pwd) throws InterruptedException {
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(uname);
		logger.info("User name entered: "+uname);
		lp.setPassword(pwd);
		logger.info("Password entered: "+pwd);
		Thread.sleep(1000);
		logger.info("Password after input is: "+lp.pwdContent());
		lp.clickLogin();
		logger.info("Clicked on login button");
		
		if(findByXpath("//*[@id=\"menu_button_container\"]/div/div[3]/div/button")) {
			driver.findElement(By.xpath("//*[@id=\"menu_button_container\"]/div/div[3]/div/button")).click();
			driver.findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]")).click();
			driver.switchTo().defaultContent();
			logger.info("Login success");
			Assert.assertTrue(true);
		} else {
			lp.clickErrorButton();
			lp.clickUserName();
			lp.resetUserName();
			lp.clickPassword();
			lp.resetPassword();
			driver.switchTo().defaultContent();
			logger.info("Login failed");
			Assert.assertTrue(false);
		}
	}
	
	@DataProvider(name = "loginData")
	String[][] getLoginData() throws IOException {
		String filePath = "logindata.xlsx";
		int rowCount = ExcelUtils.getRowCount(filePath, "Sheet1");
		int colCount = ExcelUtils.getCellCount(filePath, "Sheet1", 1);
		
		String loginDataArr[][] = new String[rowCount][colCount];
		
		for(int i=0;i<rowCount;i++) {
			for(int j = 0;j<colCount;j++) {
				loginDataArr[i][j] = ExcelUtils.getCellData(filePath, "Sheet1", i+1,j);
				//System.out.println(loginDataArr[i][j]);
			}
		}
		
		return loginDataArr;
	}
	

}
