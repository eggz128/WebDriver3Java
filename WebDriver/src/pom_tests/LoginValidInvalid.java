package pom_tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import pom_pages.HomePagePOM;
import pom_pages.LoginPOM;
import simplejunittests.baseclasses.TestBaseStaticBeforeAfterClass;

public class LoginValidInvalid extends TestBaseStaticBeforeAfterClass {
	// Chrome set up and teardown performed in base class
	@Before // Sets up required test data and AUT state
	public void prepareData() {
		// Load the application home page
		driver.get(baseUrl + "/Edgesite2/");
		HomePagePOM home = new HomePagePOM(driver);
		home.clickLogin();
	}

	@Test
	public void loginValid()  {
		System.out.println("Starting: Attempt Login with Valid Data");
		LoginPOM login = new LoginPOM(driver);
		assertTrue("Expected to be able to log in but did not",
					login.loginExpectSuccess("Edgewords", "Edgewords123"));
	}
	
	@Test
	public void loginInvalid() {
		System.out.println("Starting: Attempt Login with Invalid Data");
		LoginPOM login = new LoginPOM(driver);
		assertTrue("Expected to not log in but did",
				login.loginExpectFail("Invalid", "Edgewords123"));
	}

}
