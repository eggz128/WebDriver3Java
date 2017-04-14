package pom_pages.advanced;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePagePOMAdvanced {
	
	WebDriver driver;
	
	//Constructor to accept driver from calling Test Classes
	public HomePagePOMAdvanced(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (linkText="Login To Restricted Area") WebElement login;
	@FindBy (partialLinkText="Basic Examples") WebElement basicExample;
	
	//Methods that cause navigation return the class of the page to navigate to
	public LoginPOMAdvanced clickLogin(){
		login.click();
		return new LoginPOMAdvanced(driver);
	}
	
	
	public BasicExamplesHomePOMAdvanced clickBasicExamples(){
		basicExample.click();
		return new BasicExamplesHomePOMAdvanced(driver);
	}
	
	public boolean isBasicExamplesLinkDisplayed(){
		try{
			basicExample.isDisplayed();
			return true;
		} catch (NoSuchElementException e){
			return false;
		}
	}
	
}
