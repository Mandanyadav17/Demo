package Scratchproject.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReport {

    private ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public ExtentReports getReportObject() {

        // Defining the path where the report will be saved
        String path = System.getProperty("user.dir") + "\\reports\\index.html";

        // Creating an instance of ExtentSparkReporter with the defined path
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);

        // Setting the name of the report
        reporter.config().setReportName("Web Automation Results");

        // Setting the title of the report document
        reporter.config().setDocumentTitle("Test Results");

        // Creating an instance of ExtentReports
        extent = new ExtentReports();

        // Attaching the ExtentSparkReporter to ExtentReports
        extent.attachReporter(reporter);

        // Adding system information (Tester name) to the report
        extent.setSystemInfo("Tester", "Mandan yadav");

        return extent;
    }

    // Method to get the current thread's ExtentTest instance
    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }

    // Method to set the current thread's ExtentTest instance
    public static synchronized void setTest(ExtentTest test) {
        extentTest.set(test);
    }
}
