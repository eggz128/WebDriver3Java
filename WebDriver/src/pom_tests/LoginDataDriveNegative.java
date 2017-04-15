package pom_tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import pom_pages.HomePagePOM;
import pom_pages.LoginPOM;
import simplejunittests.baseclasses.TestBaseStaticBeforeAfterClass;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class) // The DataDriven JUnit Runner Class
public class LoginDataDriveNegative extends TestBaseStaticBeforeAfterClass {
	// Fields to hold the test data for use in the test
	private String strUsername;
	private String strPassword;
	private String expResult;

	// Accepts a set of data for each test run from the parameterised runner
	public LoginDataDriveNegative(String strUsername, String strPassword, String expResult) {
		this.strUsername = strUsername;
		this.strPassword = strPassword;
		this.expResult = expResult;
	}

	// Passes a collection of objects to the JUnit runner to hand back to the
	// constructor
	@Parameters(name = "Test{index}, Expect To:{2}: Username({0})=Password({1}) ")
	public static Collection<String[]> testData() {
		return Arrays.asList(new String[][] { 
			{ "EwordsSrAX", "Bert1234!", "fail" },
			{ "EwordspOBB", "Millichamp1", "pass" } });
	}

	// Chrome set up and teardown performed in base class
	@Before // Sets up required AUT state
	public void prepareData() {
		// Load the application home page
		driver.get(baseUrl + "/Edgesite2/");
		HomePagePOM home = new HomePagePOM(driver);
		home.clickLogin();
	}

	@Test
	public void loginWithInlineData() {
		try {
			System.out.println("Starting: Login Test");
			LoginPOM login = new LoginPOM(driver);
			System.out.println("Username is:" + strUsername + " \t Password is:" + strPassword);
			if (expResult.equalsIgnoreCase("pass")) {
				assertTrue("Expected to be able to log in but did not",
						login.loginExpectSuccess(strUsername, strPassword));
			} else if (expResult.equalsIgnoreCase("fail")) {
				assertTrue("Expected to not log in but did", 
						login.loginExpectFail(strUsername, strPassword));
			}
		} finally {
			System.out.println("Finished: Login Test");
		}
	}

	/*
	 * @Test public void loginInvalid() {
	 * System.out.println("Starting: Clear Form Test"); LoginPOM login = new
	 * LoginPOM(driver);
	 * 
	 * }
	 */
	// (name = "Test{index}: Username({0})=Password({1})")
}
