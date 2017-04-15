package pom_pages;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPOM {
	
	WebDriver driver;
	
	//Constructor to accept driver from calling Test Classes
	public LoginPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	WebElement username; //Finds an element if it has an id or name of username
	@FindBy (id="password") WebElement password; //More explicit
	@FindBy (linkText="Submit") WebElement submit; //Short form
	@FindBy (how = How.LINK_TEXT, using = "Clear") WebElement clear; //Long form
	
	public void clearUsername(){
		username.clear();
	}
	
	public void setUsername(String strUsername){ 
		username.sendKeys(strUsername);
	}
	
	public String getUsername(){
		return username.getAttribute("value"); //not getText()
		//getText gets an elements inner text
		//inputs are 'empty'/'void' (no closing tag)
		//the text inside an input is held in its value attribute
	}
	
	public void clearPassword(){
		password.clear();
	}
	
	public void setPassword(String strPassword){ 
		password.sendKeys(strPassword);
	}
	
	public String getPassword(){
		return password.getAttribute("value");
	}
	
	public void submitLogin(){
		submit.click();
	}
	
	public void clearForm(){
		clear.click();
		//or do it manually?
		//clearUsername();
		//clearPassword();
	}
	
	public void login(String strUsername, String strPassword){
		clearForm();
		setUsername(strUsername);
		setPassword(strPassword);
		submitLogin();
	}
	
	public boolean loginExpectSuccess(String strUsername, String strPassword){
		clearForm();
		setUsername(strUsername);
		setPassword(strPassword);
		submitLogin();
		try {
			driver.switchTo().alert();
			driver.switchTo().alert().dismiss();
			return false;
		} catch (NoAlertPresentException e) {
			return true;
		}
	}
	
	public boolean loginExpectFail(String strUsername, String strPassword){
		clearForm();
		setUsername(strUsername);
		setPassword(strPassword);
		submitLogin();
		try {
			driver.switchTo().alert();
			driver.switchTo().alert().dismiss();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
	
}
