package pompages.advanced;

import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import simplejunittests.helpers.Utilities;

public class LoginPOMAdvanced {
	
	WebDriver driver;
	
	Utilities help;
	
	//Constructor to accept driver from calling Test Classes
	public LoginPOMAdvanced(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		System.out.println("Wait For Login Page");
		help = new Utilities(driver);
		//Exception to "no aserts in POM class" rule
		//We may assert in the constructor to prove the correct page has loaded
		//Below waits for username id then asserts
		assertTrue("Wrong page or Page did not appear in given time",
				help.waitForElementToBeClickable(By.id("username"), 3));	
		System.out.println("Login Page Ready");
	}
	
	@CacheLookup WebElement username; //Finds an element if it has an id or name of username
	@CacheLookup @FindBy (id="password") WebElement password; //More explicit
	@FindBy (linkText="Submit") WebElement submit; //Short form
	@FindBy (how = How.LINK_TEXT, using = "Clear") WebElement clear; //Long form
	
	//When a method leaves us on the same page, return the current class
	//This allows test methods to be written "fluently"
	//See test for example
	public LoginPOMAdvanced clearUsername(){
		username.clear();
		return this;
	}
	//Which is more correct?
	public LoginPOMAdvanced setUsername(String strUsername){ 
		username.sendKeys(strUsername);
		return this;
	}
	
	public String getUsername(){
		return username.getAttribute("value");
	}
	
	public LoginPOMAdvanced clearPassword(){
		password.clear();
		return this;
	}
	
	public LoginPOMAdvanced setPassword(String strPassword){ 
		password.sendKeys(strPassword);
		return this;
	}
	
	public String getPassword(){
		return password.getAttribute("value");
	}
	
	//need an addrecord page to return
	public LoginPOMAdvanced submitLogin(){
		submit.click();
		return this;
	}
	
	public LoginPOMAdvanced clearForm(){
		clear.click();
		return this;
	}
	
	public boolean loginExpectSuccess(String strUsername, String strPassword){
		clearForm();
		setUsername(strUsername);
		setPassword(strPassword);
		submitLogin();
		//Use helper to return true if no alert present
		return !help.isAlertPresent();
	}
	
	public boolean loginExpectFail(String strUsername, String strPassword){
		clearForm();
		setUsername(strUsername);
		setPassword(strPassword);
		submitLogin();
		//Use helper to return true if alert present (error on log in)
		return help.isAlertPresent();
	}
	
}
