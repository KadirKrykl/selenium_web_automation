package com.testinium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.netty.handler.timeout.TimeoutException;

// import org.apache.logging.log4j.Logger;
// import org.apache.logging.log4j.LogManager;

import java.util.Random;

import java.util.concurrent.TimeUnit;

class AppTest {
    @Test
    void testApp() throws InterruptedException, TimeoutException{
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        WebDriver driver =  new ChromeDriver(options); 
        driver.get("https://www.gittigidiyor.com/");
        HomePage home = new HomePage(driver);

        assertEquals("GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi", home.getTitleText());
        LoginPage login = home.goToLogin();

        home = login.loginValidUser("test58@gmail.com", "123456789a");

        assertEquals("berker341451", home.getUserText());
        SearchPage search = home.goToSearch("Bilgisayar");

        search.goNextPage();

        assertEquals("2", search.getCurrentPage());
        Random rnd = new Random();
        int itemIdx = rnd.nextInt(search.getItemCount()+1);
        ProductPage product = search.getItem(itemIdx);

        String price = product.getProductPrice();
        BasketPage basket = product.addToBasket();

        assertEquals(price, basket.getProductPrice());

        basket.setProductCount("2");
        basket.removeProduct();

    }
}
