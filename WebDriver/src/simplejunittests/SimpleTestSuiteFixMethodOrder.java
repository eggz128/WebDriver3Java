package simplejunittests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import simplejunittests.baseclasses.TestBaseStaticBeforeAfterClass;
import simplejunittests.helpers.Utilities;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SimpleTestSuiteFixMethodOrder extends TestBaseStaticBeforeAfterClass {

	private String bodyText;

	@Test
	public void logInAndLogOut() {
		logIn();
		logOut();
	}

	@Test
	public void logInAddRecordDeleteRecordLogout() {
		logIn();
		addRecord();
		deleteRecord();
		logOut();
	}
	
	@Test
	public void logInAddRecordEditDeleteRecordLogout() {
		logIn();
		addRecord();
		editRecord();
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
			if (!alert.contains("Record Added")) {
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
		System.out.println("Test: Starting Delete Record");
		driver.get(baseUrl + "/Edgesite2/sdocs/remove_record.php");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("DoeJohn12");
		driver.findElement(By.linkText("Submit")).click();
		if (help.isAlertPresent()) {
			String alert = help.closeAlertAndGetItsText();
			if (!alert.contains("Do You Really want to Remove this record")) {
				fail("Didn't remove record. Confirmation Alert text was: " + alert);
			}
		} else {
			verificationErrors.append("Expected alert upon removing record");
			fail("Expected Confirmation Alert Not Found");
		}

		String alert = help.closeAlertAndGetItsText(); // Closes second info
														// alert
		assertTrue("Didn't remove record. Alert text: " + alert, alert.contains("Record Removed"));
		System.out.println("Test: Delete Record Completed");
	}

	private void editRecord() {
		Utilities help = new Utilities(driver);
		System.out.println("Test: Starting Edit Record");
		driver.get(baseUrl + "/Edgesite2/sdocs/edit_record.php");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("DoeJohn12");
		driver.findElement(By.linkText("Submit")).click();

		if (help.isAlertPresent()) {
			String alert = help.closeAlertAndGetItsText();
			System.out.println("Unexpected alert when editing record");
			fail(alert);
		}

		//Set new mandatory fields
		driver.findElement(By.id("house")).clear();
		driver.findElement(By.id("adl1")).clear();
		driver.findElement(By.id("town")).clear();
		driver.findElement(By.id("county")).clear();
		driver.findElement(By.id("postcode")).clear();
		driver.findElement(By.id("house")).sendKeys("Placeholder House");
		driver.findElement(By.id("adl1")).sendKeys("Example Street");
		driver.findElement(By.id("town")).sendKeys("Madeupston");
		driver.findElement(By.id("county")).sendKeys("Wyvern");
		driver.findElement(By.id("postcode")).sendKeys("ZA1 1YB");
		
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("4321password");
		driver.findElement(By.linkText("Submit")).click();
		
		//There is no Alert upon success
		if (help.isAlertPresent()) {
			String alert = help.closeAlertAndGetItsText();
			System.out.println("Unexpected alert after editing record");
			fail(alert);
		}
		
		// Assert record edited
		System.out.println("Test: Edit Record Completed");
	}

}
