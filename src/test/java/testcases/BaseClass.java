package testcases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

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
	
	
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		
		logger  = Logger.getLogger("SauceWeb");
		PropertyConfigurator.configure("log4j.properties");
		
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver");
		if(br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readConfig.readChromePath());
			driver = new ChromeDriver();
		} else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readConfig.readFirefoxPath());
			driver = new FirefoxDriver();
		}
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
			
	

}
