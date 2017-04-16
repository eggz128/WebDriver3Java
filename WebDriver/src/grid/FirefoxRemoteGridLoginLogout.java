package grid;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirefoxRemoteGridLoginLogout {

	private WebDriver driver;
	private String baseUrl = "http://127.0.0.1";
	private StringBuffer verificationErrors = new StringBuffer(); // To gather errors

	@Before
	public void setUp() throws Exception {
		//Remote via Hub
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		driver = new RemoteWebDriver(new URL("http://192.168.100.1:4444/wd/hub"),dc);
		System.out.println("Chrome Instantiated");
	}

	@After
	public void tearDown() throws Exception {
		// Quit Chrome
		System.out.println("Testing ended. Closing Firefox.");
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
		// fail("Not yet implemented");
		System.out.println("Starting Test");
		// Local variables
		String bodyText;
		WebDriverWait wait = new WebDriverWait(driver, 5);

		// Load the application home page
		System.out.println("Load home page");
		driver.get("http://www.edgewordstraining.co.uk/webdriver2/");
		System.out.println("Home page loaded");

		// Click the Login link
		System.out.println("Navigate to Log In page");
		driver.findElement(By.linkText("Login To Restricted Area")).click();
		
		//Wait for Login Page
		wait.until(ExpectedConditions.elementToBeClickable((By.id("username"))));
		
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
		
		driver.findElement(By.id("username")).sendKeys(Keys.chord(Keys.CONTROL,"a"));
		
		driver.findElement(By.id("username")).sendKeys("edgewords");
		driver.findElement(By.id("password")).sendKeys("edgewords123");
		System.out.println("Submit Form");
		driver.findElement(By.linkText("Submit")).click();

		// Explicit Wait for Log Out link to become available
		
		wait.until(ExpectedConditions.elementToBeClickable((By.linkText("Log Out"))));

		// Now logout
		System.out.println("Logged in - now log out");
		driver.findElement(By.linkText("Log Out")).click();
		System.out.println("Log Out clicked");

		// Dismiss the alert by accepting OK
		driver.switchTo().alert(); // WebDriver now sends commands to the alert
		driver.switchTo().alert().accept(); // Accept the alert
		driver.switchTo().defaultContent(); // Return WebDriver control to the
											// main page

		// Explicit Wait for the Username input to return.
		wait.until(ExpectedConditions.elementToBeClickable((By.id("username"))));

		// Capture the body text
		bodyText = driver.findElement(By.tagName("body")).getText();
		assertTrue("Body didnt report logged out", bodyText.contains("User is not Logged in"));
		System.out.println("Test: Logout Completed");
	}

}
