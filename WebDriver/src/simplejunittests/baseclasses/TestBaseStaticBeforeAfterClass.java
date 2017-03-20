package simplejunittests.baseclasses;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBaseStaticBeforeAfterClass {

	protected static WebDriver driver;
	protected static String baseUrl = "http://127.0.0.1";
	protected static StringBuffer verificationErrors = new StringBuffer(); // To gather errors

	@BeforeClass
	public static void setUp() throws Exception {
		// Instantiate Chrome (assumes ChromeDriver is on our path)
		driver = new ChromeDriver();
		System.out.println("Chrome Instantiated");
	}

	@AfterClass
	public static void tearDown() throws Exception {
		// Quit Chrome
		System.out.println("Testing ended. Closing Chrome.");
		driver.quit();
		// Check if we logged any errors. If so fail with reasons.
		String verificationErrorsString = verificationErrors.toString();
		if (!"".equals(verificationErrorsString)) {
			String failReasons = "Checks Failed: " + verificationErrorsString;
			fail(failReasons);
		}
	}

}
