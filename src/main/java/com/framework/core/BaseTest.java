package com.framework.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.framework.utils.ConfigReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    // Protected so that Child tests can access it
    protected WebDriver driver;

//    @BeforeMethod
//    public void setup() {
//        // 1. Launch Browser
//    	String browser = ConfigReader.getProperty("browser");
//    	switch(browser) {
//    	case "chrome": ChromeOptions options = new ChromeOptions();
//        	// Check if we passed 'headless=true' from Maven/Jenkins
//        	if ("true".equalsIgnoreCase(System.getProperty("headless"))) {
//        		options.addArguments("--headless");
//        		options.addArguments("--disable-gpu");
//        		options.addArguments("--window-size=1920,1080");
//        		options.addArguments("--no-sandbox"); // Critical for Docker security models
//        		options.addArguments("--disable-dev-shm-usage"); // Critical for Docker memory
//        	}
//        	driver = new ChromeDriver(options);
//        	break;
//    				
//    	case "edge" : driver = new EdgeDriver();
//    				  break;
//    	case "firefox": driver = new FirefoxDriver();
//    					break;
//    	default:
//            // Good architecture: Fail fast if config is wrong
//            throw new RuntimeException("Browser not supported: " + browser);
//    	
//    	}
//    	
//        driver = new ChromeDriver();
//
//        // 2. Maximize Window
//        driver.manage().window().maximize();
//
//        // 3. Set Implicit Wait (Global sync)
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        
//        // 4. Navigate (Hardcoded for now)
//       // driver.get("https://the-internet.herokuapp.com/login");
//        driver.get(ConfigReader.getProperty("url"));
//    }
    
    
    @BeforeMethod
    public void setup() throws MalformedURLException {
        // 1. Load Config
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        String executionEnv = System.getProperty("env", "local"); // Default to 'local' if not specified

        // 2. Define Capabilities (What browser do we want?)
        // Note: In Selenium 4, we use Options objects as capabilities
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if ("remote".equalsIgnoreCase(executionEnv)) {
                    // Point to the Hub URL defined in Docker Compose
                    driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), chromeOptions);
                } else {
                    // Run Locally
                    driver = new ChromeDriver(chromeOptions);
                }
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if ("remote".equalsIgnoreCase(executionEnv)) {
                    driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), firefoxOptions);
                } else {
                    driver = new FirefoxDriver(firefoxOptions);
                }
                break;
                
            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("url"));
    }
    

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Closes the browser and kills the session
        }
    }
    
    public WebDriver getDriver() {
        return driver;
    }
}