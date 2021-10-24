package com.lizheng.POmethod;

import com.lizheng.keyWord.WebKeyWord;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class shopPayPage {
    public WebKeyWord wkw;
    public String url="http://8.142.92.43/Home/Cart/index.html";

    @FindBy(xpath = "/html/body/div[4]/div/div/div/div[2]/div[2]/div[1]/a")
            public WebElement gosettlement;

    @FindBy(xpath = "/html/body/div[14]/div/button")
            public WebElement scrollToOrder;

    @FindBy(xpath = "/html/body/div[14]/div/button")
            public WebElement order;

    @FindBy(xpath = "//*[@id=\"input-ALIPAY-1\"]")
            public WebElement input_ALIPAY_1;

    @FindBy(xpath = "//*[@id=\"cart4_form\"]/div/div/div/a")
            public WebElement cart4_form;

    public void load(){
        wkw.visitURL(url);
    }

    public shopPayPage(WebKeyWord keyword){
        wkw = keyword;
        PageFactory.initElements(wkw.driver,this);
    }

    public void shopPay(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gosettlement.click();
        ((JavascriptExecutor) wkw.driver).executeScript("arguments[0].scrollIntoView();",scrollToOrder);
        order.click();
        input_ALIPAY_1.click();
        cart4_form.click();
        wkw.driver.close();
    }
}
