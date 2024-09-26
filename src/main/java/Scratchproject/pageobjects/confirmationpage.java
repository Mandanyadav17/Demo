package Scratchproject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Scratchproject.AbstractComponents.AbstractComponents;

public class confirmationpage extends AbstractComponents {
	
	WebDriver driver;
	public confirmationpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

    //String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

	@FindBy(css=".hero-primary")
	WebElement confirmationmeessage;
	
	
	public String verifyconfirmationmessage(){
		
		return confirmationmeessage.getText();
		
		}
}
