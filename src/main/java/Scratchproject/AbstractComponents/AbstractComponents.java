package Scratchproject.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Scratchproject.pageobjects.Orderspage;
import Scratchproject.pageobjects.cartpage;
import java.time.Duration;

public class AbstractComponents {
	

	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
		
	}
	
	public WebElement waitforelementtoapperar(By FindBy) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    return wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	
	}
	
	public WebElement waitforwebelementtoapperar(WebElement FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    return wait.until(ExpectedConditions.visibilityOf(FindBy));
		
		}
	
	public void waitforelementtodisaapear(By FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		  wait.until(ExpectedConditions.invisibilityOfElementLocated(FindBy));

	}
	
	public void waitForOverlayToDisappear(By overlayLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayLocator));
    }
	@FindBy(css="button[routerlink='/dashboard/cart']")
	
	WebElement clickoncartbuttn;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement Orderspage;

	@FindBy(css = ".ngx-spinner-overlay")
    WebElement overlay;
    
    public cartpage clickelementtocart() {
        try {
            waitForOverlayToDisappear(By.cssSelector(".ngx-spinner-overlay"));
            clickoncartbuttn.click();
        } catch (Exception e) {
            // Log and handle exceptions as needed
            e.printStackTrace();
        }
        return new cartpage(driver);
    }

	

public Orderspage clickOnMyOrders() {
    try {
        waitForOverlayToDisappear(By.cssSelector(".ngx-spinner-overlay"));
        Orderspage.click();
    } catch (Exception e) {
        // Log and handle exceptions as needed
        e.printStackTrace();
    }
    return new Orderspage(driver);
}

}


