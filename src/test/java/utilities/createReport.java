//Listener class to generate report

package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class createReport extends TestListenerAdapter {
	
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extentReport;
	public ExtentTest extentTest;
	
	public void onStart(ITestContext testContext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportName = "Test-Report-"+timeStamp+".html";
		
		htmlReporter = new ExtentSparkReporter("test-output/"+reportName);
		try {
			htmlReporter.loadXMLConfig("extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to load extent-config file."+e);
		}
		
		extentReport =  new ExtentReports();
		extentReport.attachReporter(htmlReporter);
		extentReport.setSystemInfo("Host Name", "localhost");
		extentReport.setSystemInfo("Environment", "QA");
		extentReport.setSystemInfo("User", "Lalit Kumar");
		
		htmlReporter.config().setDocumentTitle("SauceDemo Wed automation test");
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
	}
	
	public void onTestSuccess(ITestResult tr) {
		extentTest = extentReport.createTest(tr.getName());
		extentTest.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult tr) {
		extentTest = extentReport.createTest(tr.getName());
		extentTest.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		String screenShotPath = "ScreenShots/"+tr.getName()+".png";
		
		File f = new File(screenShotPath);
		
		if(f.exists()) {
				extentTest.fail("Screenshot at: "+extentTest.addScreenCaptureFromPath(screenShotPath));
			}
	}
	
	public void onTestSkipped(ITestResult tr) {
		extentTest = extentReport.createTest(tr.getName());
		extentTest.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.YELLOW));
	}
	
	public void onFinish(ITestContext testContext) {
		extentReport.flush();
	}

}
