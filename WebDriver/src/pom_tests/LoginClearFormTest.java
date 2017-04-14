package pom_tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import pom_pages.HomePagePOM;
import pom_pages.LoginPOM;
import simplejunittests.baseclasses.TestBaseStaticBeforeAfterClass;

public class LoginClearFormTest extends TestBaseStaticBeforeAfterClass {
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
		// login.setUsername("NotCleared"); //Break the test

		String username = login.getUsername();
		String password = login.getPassword();

		boolean formEmpty = (username.isEmpty() && password.isEmpty());
		try {
			assertTrue("Login Form not empty", formEmpty);
			System.out.println("Test Passed: Clear Form Test");
		} catch (AssertionError e) {
			verificationErrors.append(e.toString());
			System.out.println(e.toString());
			System.out.println("Username is: " + username + " Password is : " + password);
			System.out.println("Test Failed: Clear Form Test");
		}
		System.out.println("End of: Clear Form Test");
		//assertTrue("Login Form not empty", formEmpty); 
		//is it desirable to pull down the test with a failure trace on fail
	}

}
