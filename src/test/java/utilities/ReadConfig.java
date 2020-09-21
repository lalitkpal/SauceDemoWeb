package utilities;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class ReadConfig{
	
	Properties prop;
	
	public ReadConfig() {
		File configFile = new File("config.properties");
		
		try {
			FileInputStream fs = new FileInputStream(configFile);
			prop =  new Properties();
			prop.load(fs);
		} catch (IOException ie) {
			System.out.println("Unable to read config file. Exception: " + ie);
		}
	}
	
	public String readBaseURL() {
		String url = prop.getProperty("baseURL");
		return url;
	}
	
	public String readUsername() {
		return prop.getProperty("username");
	}
	
	public String readPassword() {
		return prop.getProperty("password");
	}
	
	public String readChromePath() {
		return prop.getProperty("chromepath");
	}

	public String readFirefoxPath() {
		return prop.getProperty("firefoxpath");
	}
	

}
