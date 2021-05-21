package com.testinium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class LoginPage {
    protected WebDriver driver;

    // <input name="kullanici" type="text" value="">
    private By usernameBy = By.name("kullanici");
    // <input name="sifre" type="password" value="">
    private By passwordBy = By.name("sifre");
    // <input name="pass" id="gg-login-enter" type="submit" value="Giriş Yap">
    private By loginBy = By.xpath("//*[@id='gg-login-enter']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public HomePage loginValidUser(String userName, String password) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(usernameBy).sendKeys(userName);
        driver.findElement(passwordBy).sendKeys(password);
        Thread.sleep(1000);
        driver.findElement(loginBy).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        return new HomePage(driver);
    }

}
