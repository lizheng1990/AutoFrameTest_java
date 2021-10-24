package com.lizheng.testApp;

import com.lizheng.keyWord.AppKeyWord;

public class testAPPBrowserOfShopByApp {
    public static void main(String[] args) {
        AppKeyWord akw = new AppKeyWord();
        akw.runCmd("adb connect 127.0.0.1:7555");
        akw.startAppium("4730","10");
        akw.runBrowser("127.0.0.1:7555","6.0.1","4730","10");
        akw.visitURL("http://8.142.92.43/Mobile/Index/index.html");
        akw.mustWait("3");
        akw.printcontexts();
        akw.switchContext("NATIVE_APP");
        akw.click("//android.view.View[@text='\uE618 我的']");
//        刷新页面
        akw.clickById("sogou.mobile.explorer:id/aqe");
//        akw.mustWait("3");
        akw.waitForXpath("//android.widget.EditText[@resource-id='username']","10");
        akw.input("//android.widget.EditText[@resource-id='username']","13900000000");
        akw.input("//android.widget.EditText[@resource-id='password']","123456");
        akw.input("//android.widget.EditText[@resource-id='verify_code']","1");
        akw.click("//android.widget.Button[@text='登 录']");
//        刷新页面
        akw.clickById("sogou.mobile.explorer:id/aqe");
//        akw.mustWait("5");
        akw.waitForXpath("//android.view.View[@resource-id='logout']","5");
        akw.appiumSwipe("467","2144","467","732");
        akw.click("//android.view.View[@resource-id='logout']");
        akw.printcontexts();
        akw.mustWait("10");
        akw.quitApp();
        akw.killAppium();
    }
}
