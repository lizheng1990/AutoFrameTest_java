package com.lizheng.POmethod;

import com.lizheng.keyWord.WebKeyWord;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class shopJoinCartPage {

    public WebKeyWord wkw;
    public String url="http://8.142.92.43/";

    @FindBy(xpath = "/html/body/div[1]/div[3]/div/div[1]/a")
            public WebElement hoverGoods;

    @FindBy(xpath = "//*[@id=\"cata-nav\"]/div[1]/div[1]/h3/div/a")
            public WebElement hovermobile;

    @FindBy(xpath = "//*[@id=\"cata-nav\"]/div[1]/div[2]/div[1]/div[2]/dl[1]/dd/a[1]")
            public WebElement clickMobile;

    @FindBy(xpath = "/html/body/div[4]/div/div[2]/div[2]/ul/li[5]/div/div[4]/a")
            public WebElement scrollToPostion1;

    @FindBy(xpath = "/html/body/div[4]/div/div[2]/div[2]/ul/li[5]/div/div[5]/div[2]/a")
            public WebElement clickAddGoods;

    @FindBy(xpath = "//*[@id=\"goods_spec_a_10\"]")
            public WebElement goods_spec_a_10;

    @FindBy(xpath = "//*[@id=\"goods_spec_a_17\"]")
            public WebElement goods_spec_a_17;

    @FindBy(xpath = "//*[@id=\"join_cart\"]")
            public WebElement join_cart;

    @FindBy(xpath = "//a[@class='layui-layer-ico layui-layer-close layui-layer-close1']")
            public WebElement closedIframe;

    @FindBy(xpath = "//*[@id=\"hd-my-cart\"]/a/div/span")
            public WebElement goCarts;

    public void load(){
        wkw.visitURL(url);
    }

    public shopJoinCartPage(WebKeyWord keyword){
        wkw = keyword;
        PageFactory.initElements(wkw.driver,this);
    }

    public void shopJoinCart(){
        Actions act1 =new Actions(wkw.driver);
        act1.moveToElement(hoverGoods).perform();
        Actions act2 =new Actions(wkw.driver);
        act2.moveToElement(hovermobile).perform();
        clickMobile.click();
        String oldWindow = wkw.driver.getWindowHandle();
        Set<String> handles = wkw.driver.getWindowHandles();
        for(String handle : handles){
            if(handle.equals(oldWindow)==false){
                wkw.driver.switchTo().window(handle);
            }
        }
        ((JavascriptExecutor) wkw.driver).executeScript("arguments[0].scrollIntoView();",scrollToPostion1);
        clickAddGoods.click();
        goods_spec_a_10.click();
        goods_spec_a_17.click();
        join_cart.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        closedIframe.click();
        goCarts.click();
    }
}
