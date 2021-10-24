package com.lizheng.createDrivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class WebDriverOfGoogle {
    private WebDriver driver;
    public WebDriverOfGoogle(){
        ChromeOptions options = new ChromeOptions();
//        设置浏览器程序位置
//        options.setBinary("C:\\Users\\45360\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
//        chrome浏览器缓存配置（查看：chrome://version/）
        options.addArguments("--user-data-dir=C:\\Users\\45360\\AppData\\Local\\Google\\Chrome\\User Data\\");
//        取消显示Chrome正在受到自动软件的控制
        options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation","load-extension"});
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
        //获取chrom浏览器驱动
        System.setProperty("webdriver.chrome.driver","browserDrivers/chromedriver.exe");
        //设置临时环境变量，指定chrome使用静默模式，减少日志输出量
        System.setProperty("webdriver.chrome.silentOutput", "true");
        try {
            this.driver = new ChromeDriver(options);
        } catch (Exception e) {
            System.out.println("创建ChromDriver失败！！！");
            e.printStackTrace();
        }
    }

    public WebDriver getdriver(){
        return this.driver;
    }
}
