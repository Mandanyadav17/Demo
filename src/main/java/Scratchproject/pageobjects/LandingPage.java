package Scratchproject.pageobjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Scratchproject.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement Passwordele ;
	
	@FindBy(id="login")
	WebElement submitlogin;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;
	//By.cssSelector("[class*='flyInOut']")
	
	//document.querySelector(".ng-tns-c4-4.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error")
	
public Productcatalogue loginapplication(String email,String password ) {
	
	userEmail.sendKeys(email);
	Passwordele.sendKeys(password);
	submitlogin.click();
    Productcatalogue Productcatalogue=new Productcatalogue(driver);
    return Productcatalogue;

	
}

public String erroremessageerify() {
	waitforwebelementtoapperar(errormessage);
	return errormessage.getText();
	
}

public void gotourl() {
	driver.get("https://rahulshettyacademy.com/client");
}
}
