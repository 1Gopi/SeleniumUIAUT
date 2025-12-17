package com.framework.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        return new Object[][] {
            { "tomsmith", "SuperSecretPassword!" },
            { "admin",    "admin123" },
            { "guest",    "guest" }
        };
    }
}