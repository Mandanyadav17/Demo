package Scratchproject.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Scratchproject.AbstractComponents.AbstractComponents;

public class checkoutpage extends AbstractComponents {

    WebDriver driver;

    public checkoutpage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;

    @FindBy(css = ".action__submit")
    WebElement submitbutton;

    @FindBy(xpath = "//button[contains(@class, 'ta-item')][2]")
    WebElement selectcountry;

    By results = By.cssSelector(".ta-results");

    public void selectCountry(String countryName) {
        Actions actions = new Actions(driver);
        actions.sendKeys(country, countryName).build().perform();
        waitforelementtoapperar(results);
        selectcountry.click();
    }

    public confirmationpage submitOrder() {
        try {
            // Scroll into view and attempt to click the submit button using JavaScript
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", submitbutton);
            js.executeScript("arguments[0].click();", submitbutton);
        } catch (StaleElementReferenceException e) {
            // Handle StaleElementReferenceException by re-locating the element and clicking again
            System.out.println("Encountered StaleElementReferenceException, attempting to re-locate and click the submit button again.");
            WebElement reLocatedButton = driver.findElement(By.cssSelector(".action__submit"));
            reLocatedButton.click();
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            e.printStackTrace();
        }
        return new confirmationpage(driver);
    }
}
