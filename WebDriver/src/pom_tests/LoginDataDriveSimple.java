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

@RunWith(value = Parameterized.class) //The DataDriven Junit Runner Class
public class LoginDataDriveSimple extends TestBaseStaticBeforeAfterClass {
	//Fields to hold the test data for use in the test
	private String strUsername;
	private String strPassword;
	//Accepts a set of data for each test run from the paramiterized runner
	public LoginDataDriveSimple(String strUsername, String strPassword){
		this.strUsername = strUsername;
		this.strPassword = strPassword;
	}
	//Passes a collection of objects to the JUnit runner to hand back to the constructor
	@Parameters(name = "Test{index}: Username({0})=Password({1})")
	public static Collection<String[]> testData(){
		return Arrays.asList(new String[][] {     
            { "Edgewords", "Edgewords123" },
            { "NotAValidUser", "InvalidPassword"}
      });
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
	public void loginWithInlineData()  {
		System.out.println("Starting: Login Test");
		LoginPOM login = new LoginPOM(driver);
		System.out.println("Username is:" + strUsername + " \t Password is:" + strPassword);
		try {
			assertTrue("Expected to be able to log in but did not",
					login.loginExpectSuccess(strUsername, strPassword));
		} catch (Exception e) {
			throw e;
		} finally {
			System.out.println("Finished: Login Test");
		}
	}
	
	
	
	
	/*
	@Test
	public void loginInvalid() {
		System.out.println("Starting: Clear Form Test");
		LoginPOM login = new LoginPOM(driver);
		assertTrue("Expected to not log in but did",
				login.loginExpectFail("Invalid", "Edgewords123"));
	}
*/
	//(name = "Test{index}: Username({0})=Password({1})")
}
