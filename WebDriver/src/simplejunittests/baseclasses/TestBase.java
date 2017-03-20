package simplejunittests.baseclasses;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

	protected WebDriver driver;
	protected String baseUrl = "http://127.0.0.1";
	protected StringBuffer verificationErrors = new StringBuffer(); // To gather errors

	@Before
	public void setUp() throws Exception {
		// Instantiate Chrome (assumes ChromeDriver is on our path)
		driver = new ChromeDriver();
		System.out.println("Chrome Instantiated");
	}

	@After
	public void tearDown() throws Exception {
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
