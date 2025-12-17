package com.automation.tests;

import com.framework.core.BaseTest;
import com.framework.pages.LoginPage;
import com.framework.utils.TestDataProvider;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider; // 1. Import this
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {

    // 2. Define the Data Provider
    

    // 3. Link Test to Data & Add Arguments
	@Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
    public void testLogin(String username, String password) {
        
		
		// 1. Initialize the Page
	    LoginPage loginPage = new LoginPage(driver);

	    // 2. Perform Login (Use the business method we created!)
	    loginPage.login(username, password);
	    
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
        boolean isSecure = driver.getCurrentUrl().contains("secure");
        
        // Logic: Only "tomsmith" should pass, others might fail/stay on page
        // (For this demo, we are just printing the result)
        System.out.println("User: " + username + " | Login Success: " + isSecure);
    }
}