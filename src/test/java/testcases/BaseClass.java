package testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class BaseClass {
	
	public static WebDriver driver;
	public static Logger logger;
	
	public String baseURL = "https://www.saucedemo.com/";
	public String userName = "standard_user";
	public String password = "secret_sauce";
	
	
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver");
		driver = new ChromeDriver();
		
		logger  = Logger.getLogger("SauceWeb");
		PropertyConfigurator.configure("log4j.properties");
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
			
	

}
