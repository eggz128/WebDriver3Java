package andtheresmore;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.openqa.selenium.By;

import simplejunittests.baseclasses.TestBase;

public class PopUp extends TestBase {

	@Test
	public void test() throws InterruptedException {
		// Load the CSS/Xpath Basic Examples Page
		System.out.println("Pop Up");
		driver.get(baseUrl + "/Edgesite2/docs/dynamicContent.html");
		driver.findElement(By.cssSelector("#right-column > a:nth-of-type(2)")).click();
		assertTrue("Didn't handle pop up correctly",
				HandleSubWindow("Pop-up Window", "Pop-up Window Content"));
	}

	private boolean HandleSubWindow (String wintitle, String checkresponse) throws InterruptedException {
		boolean handled = false;
		// Store handle to parent window to return to later
		String parentWindowHandler = driver.getWindowHandle(); 
		
        String subWindowHandler = null;
      
		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		      subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler); // switch to popup window
		//Now controlling pop up
		Thread.sleep(2000); //Hack to wait for pop up to load
		// Check the window is the one we want and set handled accordingly
		if (driver.getTitle().contentEquals(wintitle)) {
			if (driver.findElement(By.cssSelector("h1")).getText().contains(checkresponse)) {
				driver.close();
				handled = true; //Only reached if both checks are true
			}
		}
		driver.switchTo().window(parentWindowHandler);  // switch back to parent window
		return handled;
	}
	
}
