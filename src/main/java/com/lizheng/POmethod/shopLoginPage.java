package com.lizheng.POmethod;

import com.lizheng.common.AutoLogger;
import com.lizheng.keyWord.WebKeyWord;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class shopLoginPage {
    public WebKeyWord wkw;
    public String url="http://8.142.92.43/Home/user/login.html";

    @FindBy(name="username")
    public WebElement user;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(name = "verify_code")
    public WebElement vertify;

    @FindBy(name = "sbtbutton")
    public WebElement submitlg;

    @FindBy(xpath = "//a[@class='logo']/img")
    public WebElement goHomePage;

    public void load(){
        wkw.visitURL(url);
    }

    public shopLoginPage(WebKeyWord keyword){
        wkw = keyword;
        PageFactory.initElements(wkw.driver,this);
    }

    public void shopLogin(){
        user.sendKeys("13900000000");
        password.sendKeys("123456");
        vertify.sendKeys("1");
        submitlg.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wkw.driver.navigate().refresh();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        goHomePage.click();
    }

    public void shopLogin(String un,String pw,String ver){
        user.sendKeys(un);
        password.sendKeys(pw);
        vertify.sendKeys(ver);
        submitlg.click();
        wkw.driver.navigate().refresh();
        goHomePage.click();
    }
}
