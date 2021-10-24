package com.lizheng.testApp;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class testAppiumOfqq {
    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability( "platformVersion", "6.0.1");
        cap.setCapability("platformName", "Android");
        cap.setCapability("deviceName", "127.0.0.1:7555");
        cap.setCapability("appPackage", "com.tencent.qqlite");
        cap.setCapability("appActivity", "com.tencent.mobileqq.activity.RegisterGuideActivity");

        URL serverURL = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(serverURL,cap);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("同意");
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElementById("com.tencent.qqlite:id/btn_login");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("请输入QQ号码或手机或邮箱");
        el3.click();
        el3.sendKeys("304594568");
        MobileElement el4 = (MobileElement) driver.findElementByAccessibilityId("请输入密码");
        el4.click();
        el4.sendKeys("Liz48225248");
        MobileElement el5 = (MobileElement) driver.findElementByAccessibilityId("登录QQ");
        el5.click();
        MobileElement el6 = (MobileElement) driver.findElementByAccessibilityId("拒绝按钮");
        el6.click();
        MobileElement el7 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[4]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ImageView");
        el7.click();
        MobileElement el8 = (MobileElement) driver.findElementById("com.tencent.qqlite:id/qqSettingSetting");
        el8.click();
        MobileElement el9 = (MobileElement) driver.findElementByAccessibilityId("帐号管理");
        el9.click();
        MobileElement el10 = (MobileElement) driver.findElementByAccessibilityId("退出当前帐号按钮");
        el10.click();
        MobileElement el11 = (MobileElement) driver.findElementById("com.tencent.qqlite:id/dialogRightBtn");
        el11.click();

    }
}
