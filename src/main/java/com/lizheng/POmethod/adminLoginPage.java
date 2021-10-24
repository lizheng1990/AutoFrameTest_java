package com.lizheng.POmethod;

import com.lizheng.keyWord.WebKeyWord;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class adminLoginPage {
    public WebKeyWord wkw;
    public String url="http://8.142.92.43/index.php/Admin/Admin/login";

    @FindBy(name="username")
    public WebElement user;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(name = "vertify")
    public WebElement vertify;

    @FindBy(name = "submit")
    public WebElement submitlg;

    public adminLoginPage(WebKeyWord keyword){
        wkw = keyword;
        PageFactory.initElements(wkw.driver,this);
    }

    public void load(){
        wkw.visitURL(url);
    }

    public void adminLogin(){
        user.sendKeys("admin");
        password.sendKeys("123456");
        vertify.sendKeys("1");
        submitlg.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void adminLogin(String un,String pw,String ve){
        user.sendKeys(un);
        password.sendKeys(pw);
        vertify.sendKeys(ve);
        submitlg.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
