package testcases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.commons.io.FileUtils;


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
		//Implicit wait added to handle synchronization during website flow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
	
	public void screenCapture(WebDriver driver, String testName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File("ScreenShots/" +testName+ ".png");
		//FileHandler.copy(source,target);
		FileUtils.copyFile(source, target);
		logger.info("Screen shot taken");
		
	}
			
	public boolean findByXpath(String xPath) {
		
		
		try {
			driver.findElement(By.xpath(xPath));
			return true;
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Element "+xPath+" not found.");
			return false;
		}
		
	}
	
	

}
