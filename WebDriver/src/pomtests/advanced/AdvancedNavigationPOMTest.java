package pomtests.advanced;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pompages.advanced.*;
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
		// CLick Login on Home. Intellisense suggests setUsername from Login  POM!
		home.clickLogin().setUsername("Also fluid")
							.setUsername("A password")
							.submitLogin();
		

		// Go back home
		driver.get(baseUrl + "/Edgesite2/");

		// Alternatively clickLogin() and store the returned LoginPOMAdvanced
		// object in a new reference
		LoginPOMAdvanced loginAlt = home.clickLogin();
		loginAlt.clearUsername().setUsername("last try");
		Thread.sleep(2000);
	}

}
