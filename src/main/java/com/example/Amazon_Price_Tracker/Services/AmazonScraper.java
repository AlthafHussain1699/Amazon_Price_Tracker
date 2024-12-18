package com.example.Amazon_Price_Tracker.Services;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

public class AmazonScraper {
    private WebDriver driver;

    public AmazonScraper() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        this.driver = new ChromeDriver();
    }

    public Map<String, String> scrapeProductDetails(String productUrl) {
        Map<String, String> productDetails = new HashMap<>();

        try {
            driver.get(productUrl);

            // Scrape product name
            WebElement nameElement = driver.findElement(By.id("productTitle"));
            String productName = nameElement.getText();

            // Scrape product price
            WebElement priceElement = driver.findElement(By.id("priceblock_ourprice"));
            String productPrice = priceElement.getText();

            productDetails.put("name", productName);
            productDetails.put("price", productPrice);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
        return productDetails;
    }
}

