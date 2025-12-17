package com.automation.listeners;
import com.framework.utils.ExtentReportManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.framework.core.BaseTest;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    // These must be static so they persist across all tests
  //  private static ExtentReports extent = ExtentReportManager.createInstance("extent-report.html");
   // private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentReportManager.createInstance("extent-report.html");

    
    @Override
    public void onTestStart(ITestResult result) {
        // Create a new entry in the report for this specific test method
       // ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        startTest(result.getMethod().getMethodName());
       // test.set(extentTest);
        
    }
    

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }
    
    public static synchronized ExtentTest startTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	  Object currentClass = result.getInstance();
    	 WebDriver driver = ((BaseTest) currentClass).getDriver();
    	String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.PASS, "Test Passed",MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        getTest().fail(result.getThrowable()); // Log the error message

        // SENIOR LEVEL: Retrieve the driver from the test class to take a screenshot
        try {
            // 1. Get the current test class instance
            Object currentClass = result.getInstance();
            // 2. Cast it to BaseTest to access getDriver()
            WebDriver driver = ((BaseTest) currentClass).getDriver();

            // 3. Take Screenshot
            if (driver != null) {
                String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
                getTest().addScreenCaptureFromBase64String(base64Screenshot, "Failure Screenshot");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static synchronized void flush() {
        extent.flush();
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
           // extent.flush(); // CRITICAL: Writes the file to disk!
            flush();
        }
    }
}