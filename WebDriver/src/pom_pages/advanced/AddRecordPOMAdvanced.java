package pom_pages.advanced;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddRecordPOMAdvanced {
	
	WebDriver driver;
	
	//Constructor to accept driver from calling Test Classes
	public AddRecordPOMAdvanced(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy (id="name") WebElement name; 
	@FindBy (id="username") WebElement username; 
	@FindBy (id="pin") WebElement pin; 
	@FindBy (id="password") WebElement password; 
	//When a method leaves us on the same page, return the current class
	//This allows test methods to be written "fluently"
	//See test for example
	
	
}
