package Scratchproject.Testcomponenets;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Scratchproject.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;


public class BaseTest {
    public WebDriver driver;
    public LandingPage LandingPage;

    // Method to initialize the WebDriver based on the browser specified in the properties file
    public WebDriver driverinitialize() throws IOException {

        // Load properties from the GlobalData.properties file
        Properties prop = new Properties();
        System.out.println(System.getProperty("user.dir"));

        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "\\src\\main\\java\\Scratchproject\\resources\\GlobalData.properties");
        prop.load(fis);
        

        String browsername = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
        System.out.println("Browser name: " + browsername);

        
     // Check if the browser name contains "headless"
        boolean isHeadless = browsername.toLowerCase().contains("headless");
        
        // Retrieve headless mode flag from properties (optional)
    //    boolean isHeadless = Boolean.parseBoolean(prop.getProperty("headless", "false"));

        // Initialize the WebDriver based on the specified browser
        if (browsername.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            System.out.println("Headless mode: " + isHeadless);
            System.out.println("Browser name: " + browsername);

            
            if (isHeadless) {
                options.addArguments("--headless=new");  // Enables headless mode in Chrome
                options.addArguments("--window-size=1920,1080");  // Set window size for proper rendering in headless mode
            }
            

            driver = new ChromeDriver(options);

        } else if (browsername.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();

            
            if (isHeadless) {
                options.addArguments("--headless");  // Enables headless mode in Firefox
                options.addArguments("--window-size=1920,1080");  // Set window size for proper rendering in headless mode
            }
            

            driver = new FirefoxDriver(options);

        } else if (browsername.equalsIgnoreCase("edge")) {
            // Add code for Edge browser initialization if needed
        }

        // Set default timeout and maximize the browser window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    // Method to read data from a JSON file and convert it to a list of HashMaps
    public List<HashMap<String, String>> getJsondatatoMpa(String filepath) throws IOException {
        // Read the content of the JSON file as a string
        String jsoncontent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

        // Convert the JSON string into a list of HashMapss
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsoncontent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
        return data;
    }

    // Method to capture a screenshot and save it to a file
    public String getScreenshot(String testName, WebDriver driver) {

        // Generate a timestamp for unique screenshot file names
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Capture the screenshot as a file
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the destination file path for the screenshot
        String filePath = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + timestamp + ".png";

        try {
            // Copy the screenshot file to the destination path
            FileUtils.copyFile(src, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath; // Return the file path for further use
    }

    
    // Method that runs before each test method to launch the application
    @BeforeMethod(alwaysRun = true)
    public LandingPage launchappilication() throws IOException {
        // Initialize the WebDriver and launch the application
        driver = driverinitialize();
        LandingPage = new LandingPage(driver);
        LandingPage.gotourl();
        return LandingPage;
    }
    
    

    // Method that runs after each test method to close the browser
    @AfterMethod(alwaysRun = true)
    public void teardown() {
        // Close the browser
        driver.close();
    
    if (driver == null) {
        throw new RuntimeException("WebDriver failed to initialize");
    }
}
}
