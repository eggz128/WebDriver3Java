package simplejunittests.baseclasses;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBaseCommand {

	protected WebDriver driver;
	protected String baseUrl = "http://localhost";
	protected StringBuffer verificationErrors = new StringBuffer(); // To gather errors

	@Before
	public void setUp() throws Exception {
		// Get the passed command line parameter, or set a default
		String strBrowser = System.getProperty("mybrowser", "chrome");
		System.out.println("browser: " + strBrowser);

		switch (strBrowser.toLowerCase()) {
		case "chrome":
			// Instantiate Chrome (assumes ChromeDriver is on our path)
			driver = new ChromeDriver();
			System.out.println("Chrome Instantiated");
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "c:\\users\\edgewords\\documents\\geckodriver.exe");
			driver = new FirefoxDriver();
			System.out.println("Firefox Instantiated");
			break;
		case "ie":	case "internetexplorer":
			fail("IE Not yet implimented");
			break;
		default:
			fail("Unknown Browser");
		}
	}

	@After
	public void tearDown() throws Exception {
		// Quit Chrome
		System.out.println("Testing ended. Closing Browser.");
		driver.quit();
		// Check if we logged any errors. If so fail with reasons.
		String verificationErrorsString = verificationErrors.toString();
		if (!"".equals(verificationErrorsString)) {
			String failReasons = "Checks Failed: " + verificationErrorsString;
			fail(failReasons);
		}
	}

}
