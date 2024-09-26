package Scratchproject.pageobjects;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Scratchproject.AbstractComponents.AbstractComponents;

public class cartpage extends AbstractComponents{
	
	WebDriver driver;
	
	public cartpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	
	}
	
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productitles;
	
	
	@FindBy(css=".totalRow button")
   WebElement checkoutele;
	

	
	public boolean verifyProductInCart(String productName) {
	    // Ensure the productTitles list is not null or empty
	    if (productitles == null || productitles.isEmpty()) {
	        System.out.println("Product titles list is empty or not loaded.");
	        return false;
	    }

	    // Iterate through the list of products and check for a match
	    for (WebElement cartProduct : productitles) {
	        try {
	            if (cartProduct.getText().equalsIgnoreCase(productName)) {
	                return true;  // Product found in the cart
	            }
	        } catch (Exception e) {
	            System.out.println("Error accessing product title: " + e.getMessage());
	        }
	    }

	    // Product not found in the cart
	    return false;
	}

	
	public checkoutpage checkout() {
		checkoutele.click();
		return new checkoutpage(driver);
		
	}
	}
	
	
	
