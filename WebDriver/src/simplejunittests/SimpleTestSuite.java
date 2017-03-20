package simplejunittests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import simplejunittests.baseclasses.TestBase;
import simplejunittests.helpers.Utilities;

public class SimpleTestSuite extends TestBase {

	private String bodyText;

	@Test
	public void test() {
		logIn();
		addRecord();
		deleteRecord();
		logOut();
	}

	/*
	 * Test helper methods
	 */
	private void logIn() {
		// Instantiate utilities for use in test
		Utilities help = new Utilities(driver);
		System.out.println("Test: Starting Login");

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

		// Confirm we are logged in
		bodyText = driver.findElement(By.tagName("body")).getText();
		assertTrue("Body didnt report logged in", bodyText.contains("User is Logged in"));
		System.out.println("Test: Login Completed");

	}

	private void logOut() {
		// Test needs utilities
		Utilities help = new Utilities(driver);
		System.out.println("Test: Starting Log Out");
		// Now logout
		System.out.println("Logged in - now log out");
		driver.findElement(By.linkText("Log Out")).click();
		System.out.println("Log Out clicked");
		/*
		 * //To handle an alert by dismissing it call the setter first
		 * help.setAcceptNextAlert(false);
		 */

		// Handle the alert by accepting OK
		if (help.isAlertPresent()) {
			help.closeAlertAndGetItsText();
		} else {
			verificationErrors.append("Expected JS Logout alert not found.");
		}

		// Explicit Wait for the Username input to return.
		help.waitForElementToBeClickable(By.id("username"), 10);

		// Capture the body text
		bodyText = driver.findElement(By.tagName("body")).getText();
		assertTrue("Body didnt report logged out", bodyText.contains("User is not Logged in"));
		System.out.println("Test: Logout Completed");
	}

	private void addRecord() {
		Utilities help = new Utilities(driver);
		System.out.println("Test: Starting Add Record");
		driver.get(baseUrl + "/Edgesite2/sdocs/add_record.php");
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("John Doe");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("DoeJohn12");
		driver.findElement(By.id("pin")).clear();
		driver.findElement(By.id("pin")).sendKeys("1234");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("12password34");
		driver.findElement(By.linkText("Submit")).click();
		if (help.isAlertPresent()) {
			String alert = help.closeAlertAndGetItsText();
			if(!alert.contains("Record Added")){
				fail("Didn't add record. Alert text was: " + alert);
			}
		} else {
			verificationErrors.append("Expected alert upon adding record");
			fail("Expected Alert Not Found");
		}
		System.out.println("Test: Add Record Completed");
	}

	private void deleteRecord() {

		Utilities help = new Utilities(driver);
		System.out.println("Test: Delete Record procedure");
		driver.get(baseUrl + "/Edgesite2/sdocs/remove_record.php");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("DoeJohn12");
		driver.findElement(By.linkText("Submit")).click();
		if (help.isAlertPresent()) {
			String alert = help.closeAlertAndGetItsText();
			if(!alert.contains("Record Removed")){
				fail("Didn't remove record. Alert text was: " + alert);
			}
		} else {
			verificationErrors.append("Expected alert upon removing record");
			fail("Expected Alert Not Found");
		}
		System.out.println("Test: End Delete Record procedure");
	}
}
