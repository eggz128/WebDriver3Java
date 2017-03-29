package pompages.advanced;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasicExamplesHomePOMAdvanced {
	
	WebDriver driver;
	
	//Constructor to accept driver from calling Test Classes
	public BasicExamplesHomePOMAdvanced(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (linkText="Home") WebElement menuBasicHome;
	@FindBy (linkText="Basic HTML") WebElement menuBasicHTML;
	@FindBy (linkText="Forms") WebElement menuForms;
	@FindBy (linkText="Dynamic Content") WebElement menuDynamic;
	@FindBy (linkText="CSS/XPath") WebElement menuCSS;
	
	//Methods that cause navigation return the class of the page to navigate to
	public void clickHome(){
		menuBasicHome.click();
	}
	
	
}
