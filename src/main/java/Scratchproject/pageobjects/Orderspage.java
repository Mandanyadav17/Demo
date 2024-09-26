package Scratchproject.pageobjects;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Scratchproject.AbstractComponents.AbstractComponents;

public class Orderspage extends AbstractComponents{
	
	WebDriver driver;
	
	public Orderspage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	
	}
	
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productnames;
	
	
	@FindBy(css=".totalRow button")
   WebElement checkoutele;
	
	
	
	
	
	public boolean verifyProductInOrdersPage(String productname) {
	    for (WebElement product : productnames) {
	        String productText = product.getText();
	        if (productText.equalsIgnoreCase(productname)) {
	            return true; // Product found, return true immediately
	        }
	    }
	    return false; // Product not found after checking all elements
	}
		
	}
	
	
	
	
	
