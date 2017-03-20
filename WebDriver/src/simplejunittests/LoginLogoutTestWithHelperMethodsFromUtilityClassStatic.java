package simplejunittests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
// We need to import all the members of the class we wish to use
// It is no longer sufficient to simply import the class.
// While all members of the class can be imported in this way:
//import static simplejunittests.helpers.UtilitiesStatic.*;
// It is discouraged for anything other than well known classes (e.g. org.junit.Assert)
// Instead for readability members should be imported one at a time
import static simplejunittests.helpers.UtilitiesStatic.waitForElementToBeClickable;
import static simplejunittests.helpers.UtilitiesStatic.isAlertPresent;
import static simplejunittests.helpers.UtilitiesStatic.closeAlertAndGetItsText;

public class LoginLogoutTestWithHelperMethodsFromUtilityClassStatic {

	private WebDriver driver;
	private String baseUrl = "http://127.0.0.1";
	private StringBuffer verificationErrors = new StringBuffer(); // To gather errors

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

	@Test
	public void test() {
		System.out.println("Starting Test");

		// Local variables
		String bodyText;

		// Load the application home page
		System.out.println("Load home page");
		driver.get(baseUrl + "/Edgesite2/");
		System.out.println("Home page loaded");

		// Click the Login link
		System.out.println("Navigate to Log In page");
		driver.findElement(By.linkText("Login To Restricted Area")).click();

		// Wait for Login page
		waitForElementToBeClickable(driver, By.id("username"), 10);

		// Check that we are in the right state to log in.
		bodyText = driver.findElement(By.tagName("body")).getText();
		try {
			assertTrue(bodyText.contains("User is not Logged in"));
			System.out.println("User is not logged in");
		} catch (AssertionError e) {
			String failReason = "User was logged in. Continuing anyway. ";
			verificationErrors.append(failReason + e.toString()); // Log error
		}

		// Enter a username and password then submit the form
		System.out.println("Enter Username and Password");
		driver.findElement(By.id("username")).sendKeys("Edgewords");
		driver.findElement(By.id("password")).sendKeys("Edgewords123");
		System.out.println("Submit Form");
		driver.findElement(By.linkText("Submit")).click();

		// Explicit Wait for Log Out link to become available
		waitForElementToBeClickable(driver, By.linkText("Log Out"), 5);

		// Now logout
		System.out.println("Logged in - now log out");
		driver.findElement(By.linkText("Log Out")).click();
		System.out.println("Log Out clicked");
		
		//To handle an alert we may need to set the Utilities Field acceptNextAlert to false
		//This can either be done using the setter
		//setAcceptNextAlert(false);
		//Or if the Field is public by assigning false to the variable
		//acceptNextAlert = false;
		//Note doing either causes the test to fail as dismissing the alert means we don't logout
		
		// Handle the alert by accepting OK
		if (isAlertPresent(driver)) {
			closeAlertAndGetItsText(driver);
		} else {
			verificationErrors
				.append("Expected JS Logout alert not found.");
		}

		// Explicit Wait for the Username input to return.
		waitForElementToBeClickable(driver,By.id("username"), 10);

		// Capture the body text
		bodyText = driver.findElement(By.tagName("body")).getText();
		assertTrue("Body didnt report logged out", bodyText.contains("User is not Logged in"));
		System.out.println("Test: Logout Completed");
	}

}
