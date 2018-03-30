package test;

import driver.WebDriverSingleton;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestExample {

    private WebDriver driver = WebDriverSingleton.getInstance();

    @Test
    public void shouldGoogleIbaMainPage() {
        driver.get("https://www.google.by");
        WebElement searchField = driver.findElement(By.className("gsfi"));
        searchField.sendKeys("IBA Group");
        searchField.sendKeys(Keys.RETURN);
        List<WebElement> results = driver.findElements(By.xpath("//div[@class='rc']//a"));
        results.get(0).click();
        Assert.assertEquals("IBA Беларусь", driver.getTitle());
    }



    @Test
    public void loginAsRecruiter(){
        driver.get("http://testing.cld.iba.by");
        WebElement loginField = driver.findElement(By.xpath("//*[@id=\"_58_login\"]"));
        loginField.sendKeys("kabanov@tc.by");

        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"_58_password\"]"));
        passwordField.sendKeys("welcome");

        WebElement enterButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        enterButton.click();

        WebElement logname = driver.findElement(By.xpath("//a[@class='signed-in']"));
        Assert.assertEquals("Александр Евгеньевич Кабанов", logname.getText());
    }

    @Test
    public void loadRecruitingPage(){
        driver.get("http://testing.cld.iba.by");
        WebElement loginField = driver.findElement(By.xpath("//*[@id=\"_58_login\"]"));
        loginField.sendKeys("kabanov@tc.by");

        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"_58_password\"]"));
        passwordField.sendKeys("welcome");

        WebElement enterButton = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        enterButton.click();

        WebDriverWait recruitingButton = new WebDriverWait(driver, 120);
        recruitingButton.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'recruiting')]"))).click();
        Assert.assertEquals("Подбор и адаптация - Конструктор Талантов",driver.getTitle());
    }

    @After
    public void shutDown() {
        driver.close();
    }
}