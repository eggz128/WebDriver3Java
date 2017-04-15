package pom_tests.extentreports;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pom_pages.HomePagePOM;
import pom_pages.LoginPOM;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;

public class LoginValidInvalidExtent {
	static WebDriver driver;
	static String baseUrl = "http://127.0.0.1";
	static ExtentReports reports; //Setup Extent Reports and path to reports
	static String reportpath = 	"C:\\Users\\edgewords\\documents\\extent\\";
	@BeforeClass
	public static void setUp(){
		driver = new ChromeDriver();
		
		//Create Extent Reports object. Set path to report.
		reports = new ExtentReports(reportpath + "login_report_edgewords.html",
				true, NetworkMode.OFFLINE); //Overwrite if present. Keep resources Offline
		//Add custom info to report all at once
		Map<String, String> sysInfo = new HashMap<String, String>();
		sysInfo.put("Selenium Version", "3.0.1");
		sysInfo.put("Environment", "Production System");
		sysInfo.put("Title", "Login Test Using ExtentReports");
		//Add custom info one line at a time
		reports.addSystemInfo(sysInfo);
		reports.addSystemInfo("Name", "John Doe");
	}
	
	@AfterClass
	public static void tearDoen(){
		driver.quit();
		reports.close(); //Close report at end of test suite execution
	}
	// Chrome set up and teardown performed in base class
	@Before // Sets up required test data and AUT state
	public void prepareDataAndReport() {
		// Load the application home page
		driver.get(baseUrl + "/Edgesite2/");
		HomePagePOM home = new HomePagePOM(driver);
		home.clickLogin();
	}

	@Test //Starts a JUnit test
	public void loginValid()  {
		//Starts an ExtentReports Test
		ExtentTest test = reports.startTest("Login Valid Test"); 
		test.assignAuthor("JD");
		//Different logging levels are available
		test.log(LogStatus.INFO,"Attempt Login with Valid Data"); 
		System.out.println("Starting: Attempt Login with Valid Data");
		LoginPOM login = new LoginPOM(driver);
		try{
			assertTrue("Expected to be able to log in but did not",
					login.loginExpectSuccess("Edgewords", "Edgewords1234"));
			test.log(LogStatus.PASS,"Login successful");
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL,"Login failed");
			test.log(LogStatus.INFO,"Screenshot below"
					+ test.addScreenCapture(takeScreenshot("LoginWithValid")));
		}
		reports.endTest(test); //End this Extent Reports test
	}
	
	@Test //Starts a JUnit test
	public void loginInvalid() {
		//Starts an ExtentReports Test
		ExtentTest test = reports.startTest("Login Invalid Test"); 
		test.assignAuthor("JD");
		//Different logging levels are available
		test.log(LogStatus.INFO,"Attempt Login with Invalid Data");
		System.out.println("Starting: Attempt Login with Valid Data");
		LoginPOM login = new LoginPOM(driver);
		try{
			assertTrue("Expected to not log in but did",
					login.loginExpectFail("Invalid", "Edgewords123"));
			test.log(LogStatus.PASS,"Didn't Login successfully");
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL,"Unexpected login");
			test.log(LogStatus.INFO,"Screenshot below" //Logs can contain screenshots
					+ test.addScreenCapture(takeScreenshot("LoginWithValid")));
		}
		reports.endTest(test); //End this Extent Reports test
	}
	
	@After
	public void cleanUp(){
		//After a test flush the data to disc so we don't lose it
		reports.flush(); 
	}
	/*
	 * Helper to take screenshot
	 */
	private String takeScreenshot(String filename) {
		// Timestamp the screenshot
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String timestamp = dateFormat.format(date);
		String screenshotPath = reportpath + timestamp + filename + ".png";
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot,
					new File(screenshotPath));
		} catch (Exception ex) {
			// Do nothing. Don't crash the test if the screenshot fails.
		}
		return screenshotPath;

	}
}
