package Scratchproject.demoproject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Scratchproject.Testcomponenets.BaseTest;
import Scratchproject.pageobjects.Orderspage;
import Scratchproject.pageobjects.Productcatalogue;
import Scratchproject.pageobjects.cartpage;
import Scratchproject.pageobjects.checkoutpage;
import Scratchproject.pageobjects.confirmationpage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ecommerce extends BaseTest {
	String productname = "ZARA COAT 3";
	@Test(dataProvider="getdata", groups={"Purchaase"})
public void submitorder(HashMap<String, String> input) throws IOException {
		//WebDriver driver = new ChromeDriver();s
		//driver.manage().window().maximize();
		//String productname = "ZARA COAT 3";
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//LandingPage LandingPage = new LandingPage(driver);
	//	LandingPage LandingPage= launchappilication();
		Productcatalogue Productcatalogue = LandingPage.loginapplication(input.get("email"), input.get("password"));
		List<WebElement> products = Productcatalogue.getProductList();
		Productcatalogue.addProductToCart(input.get("productname"));
		cartpage cartpage = Productcatalogue.clickelementtocart();
		Boolean match = cartpage.verifyProductInCart(input.get("productname"));
		Assert.assertTrue(match);
		checkoutpage checkoutpage =cartpage.checkout();
		checkoutpage.selectCountry("india");
		confirmationpage confirmationpage = checkoutpage.submitOrder();
		String confirmMessage = confirmationpage.verifyconfirmationmessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		//driver.close();
	}
	
	public void takeScreenshot(String testName) {
        // Format the date for a unique filename
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        // Combine the test name and timestamp to create a unique filename
        String filename = testName + "_" + timestamp + ".png";
        // Define the path where the screenshot will be saved
        String filepath = System.getProperty("user.dir") + "/screenshots/" + filename;

        // Take a screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // Save the screenshot to the specified location
            FileUtils.copyFile(screenshot, new File(filepath));
            System.out.println("Screenshot saved: " + filepath);
            
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }

	
	@Test(dependsOnMethods= {"submitorder"})
	public void orderhistorytest() {
		Productcatalogue Productcatalogue = LandingPage.loginapplication("mandan17@yopmail.com", "Mandan17");
		Orderspage orderpage= Productcatalogue.clickOnMyOrders();
		Assert.assertTrue(orderpage.verifyProductInOrdersPage(productname));
		
	}
	@DataProvider
	public Object[][] getdata() throws IOException {
		//return new Object[][]   {{"mandan17@yopmail.com","Mandan17","ZARA COAT 3"}, {"nidhi17@yopmail.com","Mandan17@","ADIDAS ORIGINAL"}};
		
//		HashMap<String, String> map= new HashMap<String, String>();
//		map.put("email", "mandan17@yopmail.com");
//		map.put("password", "Mandan17");
//		map.put("productname", "ZARA COAT 3");
//		
//		HashMap<String, String> map2= new HashMap<String, String>();
//		map2.put("email", "nidhi17@yopmail.com");
//		map2.put("password", "Mandan17@");
//		map2.put("productname", "ADIDAS ORIGINAL");
//		
//		return new Object[][] {{map},{map2}};
		
        String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\Scratchproject\\jsondata\\PurchaseOrder.json";

		List<HashMap<String, String>> data= getJsondatatoMpa(filePath);
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}