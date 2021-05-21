package com.testinium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;

public class BasketPage {
    protected WebDriver driver;
    private By price = By.cssSelector(".total-price>strong");
    private By amount = By.className("amount");
    private By delete = By.xpath("//*[contains(@class,'cart-group-item')]/div[1]/div[3]/div/div[2]/div/a[1]/i");

    public BasketPage(WebDriver driver){
        this.driver = driver;
    }

    public String getProductPrice() {
        return driver.findElement(price).getText();
    }

    public void setProductCount(String many) throws InterruptedException {
        Select dropdown = new Select(driver.findElement(amount));
        dropdown.selectByValue(many);
        Thread.sleep(1000);
    }

    public void removeProduct() throws InterruptedException {
        driver.findElement(delete).click();
        Thread.sleep(1000);
    }
}
