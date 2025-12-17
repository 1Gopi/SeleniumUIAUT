package com.automation.tests;

import com.automation.listeners.TestListener;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.framework.core.BaseTest;
import com.framework.pages.LoginPage;
import com.framework.utils.TestDataProvider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider; // 1. Import this
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {

    // 2. Define the Data Provider
    
	// Initialize Log4j Logger
    private static final Logger logger = LogManager.getLogger(LoginTest.class);
   // private static ExtentTest test = TestListener.getTest();
    
    public void getScreenshot(String message) {
    	TakesScreenshot ts = (TakesScreenshot) driver;
    	String base64Screenshot = ts.getScreenshotAs(OutputType.BASE64);
    	TestListener.getTest().log(Status.INFO, message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
    }

    // 3. Link Test to Data & Add Arguments
	@Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
    public void testLogin(String username, String password) {
        
		
		// 1. Initialize the Page
	    LoginPage loginPage = new LoginPage(driver);

	    // 2. Perform Login (Use the business method we created!)
	    loginPage.login(username, password);
	    logger.info("Logged to Application");
	    getScreenshot("Logged to App");
	    //test.addScreenCaptureFromBase64String(username, password)
	    
	    // 3. Verify
	 // 3. Verify
	    String actualMessage = loginPage.getFlashMessageText(); // Capture it!
	    SoftAssert softAssert = new SoftAssert();

	    // We check if the message contains what we expect (e.g., "secure" or "invalid")
	    // NOTE: In a real framework, the expected text would also come from the DataProvider!
	    softAssert.assertTrue(actualMessage.contains("secure") || actualMessage.contains("invalid"), 
	        "Flash message was unexpected: " + actualMessage);

	    softAssert.assertAll();
        // Simple check for demonstration
	    getScreenshot("Did a assert");
        boolean isSecure = driver.getCurrentUrl().contains("secure");
        
        // Logic: Only "tomsmith" should pass, others might fail/stay on page
        // (For this demo, we are just printing the result)
        System.out.println("User: " + username + " | Login Success: " + isSecure);
    }
}