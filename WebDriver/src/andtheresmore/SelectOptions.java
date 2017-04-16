package andtheresmore;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import simplejunittests.baseclasses.TestBase;

public class SelectOptions extends TestBase {

	@Test
	public void test() throws InterruptedException {
		// Load the CSS/Xpath Basic Examples Page
		System.out.println("Drop Downs");
		driver.get(baseUrl + "/Edgesite2/docs/forms.html");
		
		WebElement dropDownElement = driver.findElement(By.id("select"));
		Select dropDown = new Select(dropDownElement);
		dropDown.selectByVisibleText("Selection Two");
		Thread.sleep(2000);
		
		List<WebElement> option = dropDown.getOptions();
		for(int i=0; i<option.size() ; i++){
			dropDown.selectByIndex(i);
			Thread.sleep(1000);
			}
	}

}
