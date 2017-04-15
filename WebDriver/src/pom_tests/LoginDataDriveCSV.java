package pom_tests;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import pom_pages.HomePagePOM;
import pom_pages.LoginPOM;
import simplejunittests.baseclasses.TestBaseStaticBeforeAfterClass;

@RunWith(value = Parameterized.class) // The DataDriven JUnit Runner Class
public class LoginDataDriveCSV extends TestBaseStaticBeforeAfterClass {
	// Fields to hold the test data for use in the test
	private String strUsername;
	private String strPassword;
	private String expResult;

	// Accepts a set of data for each test run from the parameterised runner
	public LoginDataDriveCSV(String strUsername, String strPassword, String expResult) {
		this.strUsername = strUsername;
		this.strPassword = strPassword;
		this.expResult = expResult;
	}

	// Passes a collection of objects to the JUnit runner to hand back to the
	// constructor
	@Parameters(name = "Test{index}, Expect To:{2}: Username({0})=Password({1}) ")
	public static Collection<String[]> testData() throws IOException {
		return getTestData("c:\\users\\edgewords\\documents\\data.csv");
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
	 * Simple CSV Parser
	 */
	public static Collection<String[]> getTestData(String fileName) throws IOException {
		List<String[]> records = new ArrayList<String[]>();
		String record;
		BufferedReader file = new BufferedReader(new FileReader(fileName));

		while ((record = file.readLine()) != null) {
			String fields[] = record.split(",");
			records.add(fields);
		}

		file.close();
		return records;
	}
		
}
