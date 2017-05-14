package commandline;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import simplejunittests.baseclasses.TestBase;
import simplejunittests.helpers.Utilities;

public class LoginLogoutTest extends TestBase { //Extend TestBaseCommand for command line options

	@Test
	public void test() {
		//Utility classes need instantiation
		Utilities help = new Utilities(driver);

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
		help.waitForElementToBeClickable(By.id("username"), 10);

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
		help.waitForElementToBeClickable(By.linkText("Log Out"), 5);

		// Now logout
		System.out.println("Logged in - now log out");
		driver.findElement(By.linkText("Log Out")).click();
		System.out.println("Log Out clicked");
		/*
		//To handle an alert by dismissing it call the setter first
		help.setAcceptNextAlert(false);
		*/
		
		// Handle the alert by accepting OK
		if (help.isAlertPresent()) {
			help.closeAlertAndGetItsText();
		} else {
			verificationErrors
				.append("Expected JS Logout alert not found.");
		}

		// Explicit Wait for the Username input to return.
		help.waitForElementToBeClickable(By.id("username"), 10);

		// Capture the body text
		bodyText = driver.findElement(By.tagName("body")).getText();
		assertTrue("Body didnt report logged out", bodyText.contains("User is not Logged in"));
		System.out.println("Test: Logout Completed");
	}

}
