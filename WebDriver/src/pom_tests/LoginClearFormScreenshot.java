package pom_tests;

import static org.junit.Assert.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import simplejunittests.baseclasses.TestBaseStaticBeforeAfterClass;

import pom_pages.HomePagePOM;
import pom_pages.LoginPOM;

public class LoginClearFormScreenshot extends TestBaseStaticBeforeAfterClass {
	// Chrome set up and teardown performed in base class
	@Before // Sets up required test data and AUT state
	public void prepareData() {
		// Load the application home page
		driver.get(baseUrl + "/Edgesite2/");
		HomePagePOM home = new HomePagePOM(driver);
		home.clickLogin();
		LoginPOM login = new LoginPOM(driver);
		login.setUsername("Edgewords");
		login.setPassword("Edgewords123");
	}

	@Test
	public void clearFormTest() { // a test that tests one thing
		System.out.println("Starting: Clear Form Test");
		LoginPOM login = new LoginPOM(driver);

		login.clearForm();
		login.setUsername("NotCleared"); //Break the test

		String username = login.getUsername();
		String password = login.getPassword();

		boolean formEmpty = (username.isEmpty() && password.isEmpty());
		try {
			assertTrue("Login Form not empty", formEmpty);
			System.out.println("Test Passed: Clear Form Test");
		} catch (AssertionError e) {
			verificationErrors.append(e.toString());
			takeScreenshot("myscreenshot");
			System.out.println(e.toString());
			System.out.println("Username is: " + username + " Password is : " + password);
			System.out.println("Test Failed: Clear Form Test");
		}
		System.out.println("End of: Clear Form Test");
		assertTrue("Login Form not empty", formEmpty); 
	}

	private void takeScreenshot(String filename) {
		// Timestamp the screenshot
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String timestamp = dateFormat.format(date);
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot,
					new File("c:\\users\\edgewords\\documents\\" + timestamp + filename + ".png"));
		} catch (Exception ex) {
			// Do nothing. Don't crash the test if the screenshot fails.
		}

	}

}
