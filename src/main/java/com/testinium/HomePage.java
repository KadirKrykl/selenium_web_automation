package com.testinium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

public class HomePage {
    protected WebDriver driver;

    //For user
    private By userNameBy = By.xpath("//*[@id='main-header']/div[3]/div/div/div/div[3]/div/div[1]/div/div[2]/span");
    private By loginHover = By.className("gekhq4-5");
    private By loginButton = By.cssSelector(".sc-1bydi5r-0[data-cy='header-login-button']");
    private By searchText = By.name("k");
    private By searchButton = By.xpath("//*[@id='main-header']/div[3]/div/div/div/div[2]/form/div/div[2]/button");
    

    
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public String getTitleText() {
        return driver.getTitle();
    }

    public String getUserText() {
        return driver.findElement(userNameBy).getText();
    }

    public LoginPage goToLogin() throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement buttonSgn = driver.findElement(loginHover);
        action.moveToElement(buttonSgn).build().perform();
        Thread.sleep(1000);
        WebElement buttonSgnIn = driver.findElement(loginButton);
        buttonSgnIn.click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        return new LoginPage(driver);
    }

    public SearchPage goToSearch(String text) {
        driver.findElement(searchText).sendKeys(text);
        driver.findElement(searchButton).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        return new SearchPage(driver);
    }
}
