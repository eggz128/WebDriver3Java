package pom_tests;

import static org.junit.Assert.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import simplejunittests.baseclasses.TestBaseStaticBeforeAfterClass;

import pom_pages.HomePagePOM;
import pom_pages.LoginPOM;

public class LoginClearFormScreenshotJUnit extends TestBaseStaticBeforeAfterClass {

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

	@Rule
	public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule(driver);

	@Test
	public void clearFormTest() { // a test that tests one thing
		System.out.println("Starting: Clear Form Test");
		LoginPOM login = new LoginPOM(driver);

		login.clearForm();
		login.setUsername("NotCleared"); // Break the test

		String username = login.getUsername();
		String password = login.getPassword();

		boolean formEmpty = (username.isEmpty() && password.isEmpty());
		assertTrue("Login Form not empty", formEmpty);
	}
}

class ScreenshotTestRule extends TestWatcher {
	WebDriver driver;

	public ScreenshotTestRule(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	protected void failed(Throwable t, Description description) {
		try {
			System.out.println("Getting screenshot");
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			String timestamp = dateFormat.format(date);
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot,
					new File("c:\\users\\edgewords\\documents\\" 
							+ timestamp + description.getMethodName() + ".png"));
		} catch (Exception e) {
			// No need to crash the tests if the screenshot fails
		}
	}
}
