package simplejunittests.helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {

	// Field to hold the passed driver object
	WebDriver driver;

	// Constructor
	public Utilities(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * Utility Methods
	 */
	public boolean waitForElementToBeClickable(By locator, int timeout) {
		try { // Instantiate WebDriver wait, supply a timeout and locator
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			return true;
		} catch (NoSuchElementException e) {
			// The element did not appear, or did not appear in the given
			// timeout
			return false;
		}

	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
	
	//Field for acceptNextAlert
	private boolean acceptNextAlert = true;
	//Setter for acceptNextAlert to alter behaviour of closeAlertAndGetItsText()
	public void setAcceptNextAlert(boolean acceptNextAlert) {
		acceptNextAlert = this.acceptNextAlert;
	}
	
	public String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true; //acceptNextAlert is always reset to true
		}
	}

}
