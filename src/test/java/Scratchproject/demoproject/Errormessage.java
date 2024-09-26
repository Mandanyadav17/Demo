package Scratchproject.demoproject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Scratchproject.Testcomponenets.BaseTest;
import Scratchproject.pageobjects.Productcatalogue;
import Scratchproject.pageobjects.cartpage;
import java.io.IOException;

public class Errormessage extends BaseTest {
	
	@Test(groups= {"Error handling"})
public void loginerrormessage() throws IOException {
		
		String productname = "ZARA COAT 3";
	LandingPage.loginapplication("mandan17@yopmail.com", "Mandan17");
		Assert.assertEquals("Login Successfully", LandingPage.erroremessageerify());
	
	}
	@Test
	public void productmatchvalidation() {
	String productname = "ZARA COAT 3";
	Productcatalogue Productcatalogue = LandingPage.loginapplication("mandan17@yopmail.com", "Mandan17");
	List<WebElement> products = Productcatalogue.getProductList();
	Productcatalogue.addProductToCart(productname);
	cartpage cartpage = Productcatalogue.clickelementtocart();
	Boolean match = cartpage.verifyProductInCart("ZARA COAT 3");
	Assert.assertFalse(match);
	
}
}