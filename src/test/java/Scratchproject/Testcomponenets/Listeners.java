package Scratchproject.Testcomponenets;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Scratchproject.resources.ExtendReport; // Import your ExtendReport class

public class Listeners extends BaseTest implements ITestListener {

	// Create an instance of ExtentReports to generate reports
	ExtentReports extent = new ExtendReport().getReportObject(); 
	ExtentTest test;  // This will hold the current test's status and logs
	//cuncurrency issue due to parallel execution we need to use threadlocal

	// Method executed before each test starts
	@Override
	public void onTestStart(ITestResult result) {
		// Create a new test entry in the report with the test method's name
		test = extent.createTest(result.getMethod().getMethodName());
	}

	// Method executed when a test is successful
	@Override
	public void onTestSuccess(ITestResult result) {
		// Log the success status in the report
		test.log(Status.PASS, "Test Passed");
	}

	// Method executed when a test fails
	@Override
	public void onTestFailure(ITestResult result) {
		// Log the exception that caused the test failure in the report
		test.fail(result.getThrowable());

		WebDriver driver = null;  // Initialize WebDriver to null
		try {
			// Reflectively get the WebDriver instance from the test class
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

			// Capture the screenshot and add it to the report
			String filePath = getScreenshot(result.getMethod().getMethodName(), driver);
			test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

		} catch (Exception e) {
			// Print any exception that occurs during the screenshot capture
			e.printStackTrace();
		}
	}

	// Method executed when a test is skipped
	@Override
	public void onTestSkipped(ITestResult result) {
		// Log the skipped status in the report
		test.log(Status.SKIP, "Test Skipped");
	}

	// Method executed after all the tests are completed
	@Override
	public void onFinish(ITestContext context) {
		// Flush all the logs and write them to the report
		extent.flush();
	}
}
