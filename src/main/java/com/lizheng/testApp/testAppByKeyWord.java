package com.lizheng.testApp;

import com.lizheng.keyWord.AppKeyWord;

public class testAppByKeyWord {
    public static void main(String[] args) {
        AppKeyWord akw = new AppKeyWord();
        akw.runCmd("adb connect 127.0.0.1:7555");
        akw.startAppium("4729","30");
        akw.runAPP("127.0.0.1:7555","6.0.1","com.tencent.qqlite",
                "com.tencent.mobileqq.activity.RegisterGuideActivity","4729","30");
        akw.clickByAccId("同意");
        akw.clickById("com.tencent.qqlite:id/btn_login");
        akw.inputByAccId("请输入QQ号码或手机或邮箱","304594568");
        akw.inputByAccId("请输入密码","Liz48225248");
        akw.clickByAccId("登录QQ");
        akw.clickByAccId("拒绝按钮");
        akw.click("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout[4]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ImageView");
        akw.clickById("com.tencent.qqlite:id/qqSettingSetting");
        akw.clickByAccId("帐号管理");
        akw.clickByAccId("退出当前帐号按钮");
        akw.clickById("com.tencent.qqlite:id/dialogRightBtn");
        akw.quitApp();
        akw.killAppium();
    }
}
