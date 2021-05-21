package com.testinium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

public class ProductPage {
    protected WebDriver driver;
    private By price = By.xpath("//*[@id='sp-price-lowPrice']");
    private By addToBasket = By.xpath("//*[@id='add-to-basket']");
    private By basketDiv = By.xpath("//*[@id='header_wrapper']/div[4]/div[3]/a");
    private By basketButton = By.xpath("//*[@id='add-to-basket']");

    public ProductPage(WebDriver driver){
        this.driver = driver;
    }

    public String getProductPrice() {
        return driver.findElement(price).getText();
    }

    public BasketPage addToBasket() throws InterruptedException {
        driver.findElement(addToBasket).click();
        Actions action = new Actions(driver);
        WebElement basketBox = driver.findElement(basketDiv);
        action.moveToElement(basketBox).build().perform();
        Thread.sleep(1000);
        WebElement basketBtn = driver.findElement(basketButton);
        basketBtn.click();
        Thread.sleep(1000);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        return new BasketPage(driver);
    }
}
