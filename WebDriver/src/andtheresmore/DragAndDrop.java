package andtheresmore;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import simplejunittests.baseclasses.TestBase;

public class DragAndDrop extends TestBase {

	@Test
	public void test() throws InterruptedException {
		// Load the CSS/Xpath Basic Examples Page
		System.out.println("Drag and Drop Slider");
		driver.get(baseUrl + "/Edgesite2/docs/cssXPath.html");
		// Find the slider, store a reference
		WebElement slider = driver.findElement(By.cssSelector("#slider > a"));
		// Use JS Executor to scroll slider in to view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", slider);
		Thread.sleep(500);
		Actions builder = new Actions(driver);
		// Build complex user interaction
		Action dragAndDropSlider = builder.clickAndHold(slider).
											moveByOffset(50, 0).
											release().
											build();
		dragAndDropSlider.perform(); // perform user interaction
		Thread.sleep(5000); // Wait so we can see the effect
	}

}
