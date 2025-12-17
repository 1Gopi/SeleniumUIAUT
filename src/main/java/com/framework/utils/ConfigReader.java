package com.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    // Static Block: Loads the file ONCE when the class is first used
    static {
        try {
            // 1. Where is the file?
            String path = "src/main/resources/config.properties";
            
            // 2. Open a stream to read it
            FileInputStream input = new FileInputStream(path);
            
            // 3. Initialize Properties object and load data
            properties = new Properties();
            properties.load(input);
            
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file!");
        }
    }

    // Public method to access values
//    public static String getProperty(String key) {
//        return properties.getProperty(key);
//    }
//    
    public static String getProperty(String key) {
        // 1. Check if the value was passed via Command Line (Maven)
        String systemValue = System.getProperty(key);
        
        // 2. If System Property exists, return it. Otherwise, use the file.
        if (systemValue != null) {
            return systemValue;
        } else {
            return properties.getProperty(key);
        }
    }
}