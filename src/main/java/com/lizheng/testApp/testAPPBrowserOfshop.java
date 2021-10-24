package com.lizheng.testApp;

import com.lizheng.keyWord.AppKeyWord;

public class testAPPBrowserOfshop {
    public static void main(String[] args) {
        AppKeyWord akw = new AppKeyWord();
        akw.runCmd("adb connect 127.0.0.1:7555");
        akw.startAppium("4730","10");
        akw.runBrowser("127.0.0.1:7555","6.0.1","4730","10");
        akw.visitURL("http://8.142.92.43/Mobile/Index/index.html");
        akw.mustWait("3");
        akw.printcontexts();
        akw.switchContext("CHROMIUM");
//        akw.click("//android.view.View[@text='\uE618 我的']");
        akw.click("//p[text()='我的1']");
        akw.refresh();
        akw.input("//input[@id='username']","13900000000");
        akw.input("//input[@id='password']","123456");
        akw.input("//input[@id='verify_code']","1");
        akw.click("//input[@value='登 录']");
        akw.refresh();
        akw.click("//p[text()='首页']");
        akw.click("//span[text()='全部分类']");
        akw.click("//a[text()='手机数码']");
        akw.click("//p[text()='手机/运营商/数码'][1]");
        akw.mustWait("10");
        akw.quitApp();
        akw.killAppium();

    }
}
