package com.testinium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;

public class BasketPage {
    protected WebDriver driver;
    private By price = By.cssSelector(".total-price>strong");
    private By amount = By.cssSelector("div.gg-input.gg-input-select>select");
    private By delete = By.cssSelector("a.btn-delete.btn-update-item>i");

    public BasketPage(WebDriver driver){
        this.driver = driver;
    }

    public String getProductPrice() throws InterruptedException {
        Thread.sleep(1000);
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

    public int getCountOfProduct() {
        try {
            return driver.findElements(By.className("product-group-box")).size();
        } catch (Exception e) {
            return 0;
        }
    }
}
