package testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import utilities.ReadConfig;


public class BaseClass {
	
	public static WebDriver driver;
	public static Logger logger;
	
	ReadConfig readConfig = new ReadConfig();
	
	/*
	 * public String baseURL = "https://www.saucedemo.com/"; 
	 * public String userName = "standard_user"; 
	 * public String password = "secret_sauce";
	 */
	
	public String baseURL  = readConfig.readBaseURL();
	public String userName = readConfig.readUsername();
	public String password = readConfig.readPassword();
	
	
	
	
	@BeforeClass
	public void setup() {
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver");
		System.setProperty("webdriver.chrome.driver", readConfig.readChromePath());
		driver = new ChromeDriver();
		
		logger  = Logger.getLogger("SauceWeb");
		PropertyConfigurator.configure("log4j.properties");
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
			
	

}
