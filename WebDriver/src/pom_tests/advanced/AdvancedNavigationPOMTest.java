package pom_tests.advanced;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import pom_pages.advanced.*;
import simplejunittests.baseclasses.TestBaseStaticBeforeAfterClass;

public class AdvancedNavigationPOMTest extends TestBaseStaticBeforeAfterClass {
	// Chrome set up and teardown performed in base class
	@Before 
	public void prepareData() {
		driver.get(baseUrl + "/Edgesite2/");
	}

	@Test
	public void clearFormTest() throws InterruptedException {
		System.out.println("Starting: Clear Form Test");
		// Instantiate Home Page
		HomePagePOMAdvanced home = new HomePagePOMAdvanced(driver);
		// Click Login on Home. Intellisense suggests setUsername from Login  POM!
		home.clickLogin().setUsername("Edgewords")
							.setPassword("Edgewords123")
							.submitLogin();
		// Go back home to do it again
		driver.get(baseUrl + "/Edgesite2/");
		// Alternatively clickLogin() and store the returned LoginPOMAdvanced
		// object in a new reference
		LoginPOMAdvanced loginAlt = home.clickLogin(); //More verbose, but more readable?
		loginAlt.clearUsername().setUsername("Also fluent");
		Thread.sleep(2000);
		driver.get(baseUrl + "/Edgesite2/");
		home.clickLogin(); //Not stale - no @CacheLookup
		loginAlt.clearUsername().setUsername("stale"); //Stale
		//Username field on Login was cached. We navigated away from page, came back
		//and tried to use same reference. Because it was Cached PageFactory was not
		//allowed to rescan for the username.
	}

}
