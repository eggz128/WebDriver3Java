package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class LoginLogoutAutomator {

	public static void main(String[] args) {
		// Instantiate Chrome (assumes ChromeDriver is on our path)
		WebDriver driver = new ChromeDriver();
		System.out.println("Chrome Instantiated");
/*		// Implicit wait
		// TODO: Remove in favour of explicit waits
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
*/
		// Load the application home page
		System.out.println("Load home page");
		driver.get("http://127.0.0.1/Edgesite2/");
		System.out.println("Home page loaded");

		// Click the Login link
		System.out.println("Navigate to Log In page");
		driver.findElement(By.linkText("Login To Restricted Area")).click();

		// Enter a username and password then submit the form
		System.out.println("Enter Username and Password");
		driver.findElement(By.id("username")).sendKeys("Edgewords");
		driver.findElement(By.id("password")).sendKeys("Edgewords123");
		System.out.println("Submit Form");
		driver.findElement(By.linkText("Submit")).click();
/*		
		// Explicit Unconditional Wait
		// Replace with conditional wait to improve performance
		// Needs throws InterruptedException to be added to method header.
		Thread.sleep(3000);
*/		
		// Explicit Wait for Log Out link to become available
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable((By.linkText("Log Out"))));

		// Now logout
		System.out.println("Logged in - now log out");
		driver.findElement(By.linkText("Log Out")).click();
		System.out.println("Log Out clicked");

		// Quit Chrome
		System.out.println("Automation ended. Closing Chrome.");
		driver.quit();
	}

}
