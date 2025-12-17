package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    // 1. Locators (Private: Encapsulation)
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton   = By.cssSelector("button[type='submit']");
    private By flashMessage  = By.id("flash");

    // 2. Constructor (The Bridge)
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // 3. Page Actions (Public: The Interface)
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
    
    // Business Logic Method (Combining actions)
    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }
    
    public String getFlashMessageText() {
        return driver.findElement(flashMessage).getText();
    }
}