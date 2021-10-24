package com.lizheng.testWeb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class testChromeDriver {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
//        设置浏览器程序位置
        options.setBinary("C:\\Users\\45360\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
//        chrome浏览器缓存配置（查看：chrome://version/）
        options.addArguments("--user-data-dir=C:\\Users\\45360\\AppData\\Local\\Google\\Chrome\\User Data\\");
//        取消显示Chrome正在受到自动软件的控制--无效
        options.addArguments("disable-infobars");
//        启动时最大化窗口
        options.addArguments("--start-maximized");
//        禁用阻止弹出窗口
        options.addArguments("--disable-popup-blocking");
//        启动无沙盒模式运行
        options.addArguments("--no-sandbox");
//        禁用扩展
        options.addArguments("--disable-extensions");
//        禁用保存密码提示框
        Map<String,Object> prefs = new HashMap();
        prefs.put("credentials_enable_service",false);
        prefs.put("profile.password_manager_enabled",false);
        options.setExperimentalOption("prefs",prefs);
        options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation","load-extension"});
//        获取chrom浏览器驱动
        System.setProperty("webdriver.chrome.driver","browserDrivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);
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
        driver.close();
        driver.quit();
    }
}
