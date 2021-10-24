package com.lizheng.testWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class testFirefoxDriver {
    public static void main(String[] args) {
        FirefoxOptions options = new FirefoxOptions();
        //火狐安装位置
        System.setProperty("webdriver.firefox.bin","C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        //获取浏览器驱动
        System.setProperty("webdriver.gecko.driver","webDrivers/geckodriver.exe");
        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://www.baidu.com");
        System.out.println("页面标题： " + driver.getTitle());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement ele = driver.findElement(By.id("kw"));
        ele.clear();
        ele.sendKeys("java");
        WebElement ele1 = driver.findElement(By.id("su"));
        ele1.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("页面标题： " + driver.getTitle());
        driver.quit();
    }
}
