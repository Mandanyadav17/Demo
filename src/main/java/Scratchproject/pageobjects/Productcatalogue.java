package Scratchproject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Scratchproject.AbstractComponents.AbstractComponents;

public class Productcatalogue extends AbstractComponents {

	WebDriver driver;

	public Productcatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	By animation = By.cssSelector(".ng-animating");

	public List<WebElement> getProductList() {
		System.out.println("Waiting for products to appear...");
		try {
			waitforelementtoapperar(productsBy);
			System.out.println("Products are visible.");
		} catch (TimeoutException e) {
			System.err.println("Timed out waiting for products to appear: " + e.getMessage());
		}
		return products;
	}

	public WebElement getProductByName(String productName) {
		for (WebElement product : getProductList()) {
			try {
				String productText = product.findElement(By.cssSelector("b")).getText();
				if (productText.equalsIgnoreCase(productName)) {
					return product;
				}
			} catch (NoSuchElementException e) {
				System.out.println("Element not found in product: " + e.getMessage());
			}
		}
		return null; // Return null if no match is found
	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		if (prod != null) {
			prod.findElement(addToCart).click();
			waitforelementtoapperar(toastMessage);
			waitforelementtoapperar(animation);
		} else {
			System.out.println("Product not found: " + productName);
		}
	}
}
