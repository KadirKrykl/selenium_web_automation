package com.testinium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.netty.handler.timeout.TimeoutException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Random;

class AppTest {
    final static Logger logger = LogManager.getLogger(AppTest.class);

    @Test
    void testApp() throws InterruptedException, TimeoutException{
        logger.info("Test Başladı...");
        String email = "";
        String pass = "";

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        WebDriver driver =  new ChromeDriver(options); 

        driver.get("https://www.gittigidiyor.com/");

        HomePage home = new HomePage(driver);
        assertEquals("GittiGidiyor - Türkiye'nin Öncü Alışveriş Sitesi", home.getTitleText());
        logger.info("GittiGidiyor sayfası açıldı.");

        LoginPage login = home.goToLogin();
        home = login.loginValidUser(email, pass);
        assertEquals("berker341451", home.getUserText());
        logger.info("Giriş Yapıldı.");

        SearchPage search = home.goToSearch("Bilgisayar");
        search.goNextPage();
        assertEquals("2", search.getCurrentPage());
        logger.info("Bilgisayar kelimesi arandı ve ikinci sayfaya geçildi.");

        Random rnd = new Random();
        int itemIdx = rnd.nextInt(search.getItemCount())+1;
        ProductPage product = search.getItem(itemIdx);
        String price = product.getProductPrice();
        BasketPage basket = product.addToBasket();
        assertEquals(price, basket.getProductPrice());
        logger.info("Ürün seçildi ve sepete eklendi.");

        basket.setProductCount("2");
        logger.info("Ürün adedi 2 ye çıkartıldı.");
        basket.removeProduct();
        int proSize = basket.getCountOfProduct();
        assertEquals(0, proSize);
        logger.info("Ürün silindi.");

        driver.manage().deleteAllCookies();
        driver.close();

        logger.info("Test Bitti...");
    }
}
