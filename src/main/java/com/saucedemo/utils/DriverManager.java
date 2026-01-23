package com.saucedemo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    private DriverManager() {}

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initializeDriver();
        }
        return driver.get();
    }

    public static void initializeDriver() {
        String browser = ConfigReader.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless", "false"));
        
        WebDriver webDriver;
        
        switch (browser.toLowerCase()) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless=new");
                }
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-extensions");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");

                // Disable password manager and breach detection popups
                options.setExperimentalOption("prefs", java.util.Map.of(
                    "credentials_enable_service", false,
                    "profile.password_manager_enabled", false,
                    "profile.password_manager_leak_detection", false
                ));

                webDriver = new ChromeDriver(options);
            }
        }
        
        int timeout = Integer.parseInt(ConfigReader.getProperty("implicit.wait", "10"));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        webDriver.manage().window().maximize();
        
        driver.set(webDriver);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}