package simplejunittests.helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilitiesStatic {

	static private boolean acceptNextAlert = true; //field for alert handling
	
	//Setter for acceptNextAlert to alter behaviour of closeAlertAndGetItsText
	static public void setAcceptNextAlert(boolean acceptNextAlert) {
		UtilitiesStatic.acceptNextAlert = acceptNextAlert;
	}

	/*
	 * Utility Methods
	 */
	static public boolean waitForElementToBeClickable(WebDriver driver, By locator, int timeout) {
		try { // Instantiate WebDriver wait, supply a timeout and locator
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			return true;
		} catch (NoSuchElementException e) {
			// The element did not appear, or did not appear in the given timeout
			return false;
		}
	}

	static public boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	static public String closeAlertAndGetItsText(WebDriver driver) {
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
