package com.testinium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class SearchPage {
    protected WebDriver driver;
    private By nextButton = By.xpath("//*[@id='best-match-right']/div[5]/ul/li[7]/a");
    private By currentPage = By.className("current");
    private By items = By.className("catalog-seem-cell");
    private By cookiesClose = By.cssSelector("div.policy-alert > div > div > a > span");
    private By popupClose = By.xpath("/html/body/div[6]/div/img[1]");
    private By noProductText = By.cssSelector("#empty-cart-container > div.gg-d-24 > div:nth-child(1) > div > div.gg-w-22.gg-d-22.gg-t-21.gg-m-18 > h2");


    public SearchPage(WebDriver driver){
        this.driver = driver;
    }

    public String getCurrentPage() {
        return driver.findElement(currentPage).getText();
    }

    public void goNextPage(){
        try {
            Thread.sleep(1000);
            driver.findElement(popupClose).click();
            Thread.sleep(1000);
            driver.findElement(cookiesClose).click();
        } catch (Exception e) {
            
        } finally{
            driver.findElement(nextButton).click();
        }
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    public int getItemCount() {
        return driver.findElements(items).size();
    }

    public ProductPage getItem(int idx) {
        driver.findElement(By.cssSelector("li[product-index='"+idx+"']")).findElement(By.tagName("a")).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        return new ProductPage(driver);
    }

    
}
