package com.lizheng.createDrivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverOfFF {
    private WebDriver driver;

    public WebDriverOfFF(){
        FirefoxOptions options = new FirefoxOptions();
        //火狐安装位置
        System.setProperty("webdriver.firefox.bin","C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        //获取浏览器驱动
        System.setProperty("webdriver.gecko.driver","browserDrivers/geckodriver.exe");
        try {
            this.driver = new FirefoxDriver(options);
        } catch (Exception e) {
            System.out.println("创建FirefoxDriver失败！！！");
            e.printStackTrace();
        }
    }

    public WebDriver getdriver(){
        return this.driver;
    }
}
