package pom_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePagePOM {
	
	WebDriver driver;
	
	//Constructor to accept driver from calling Test Classes
	public HomePagePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (linkText="Login To Restricted Area") WebElement login;
	@FindBy (partialLinkText="Basic Examples") WebElement basicExample;
	
	//Are these good service method names?
	//Remember we're supposed to be providing services with the underlying
	//details hidden. Maybe in future we access the two areas in a different way.
	//Perhaps from a drop down menu. Would navigateToLogin() and 
	//navigateToBasicExamples() be better?
	public void clickLogin(){
		login.click();
	}
	
	public void clickBasicExamples(){
		basicExample.click();
	}
	
	
}
