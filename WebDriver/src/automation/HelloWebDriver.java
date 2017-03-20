package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HelloWebDriver {

	public static void main(String[] args) {
		// Instantiate Chrome (assumes ChromeDriver is on our path)
		WebDriver driver = new ChromeDriver();
		
		//Load the application home page
		driver.get("http://127.0.0.1/Edgesite2/");
		
		//Click the Login link
		driver.findElement(By.linkText("Login To Restricted Area")).click();
		
		//Quit Chrome
		driver.quit();
	}

}
