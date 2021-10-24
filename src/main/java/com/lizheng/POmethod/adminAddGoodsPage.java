package com.lizheng.POmethod;

import com.lizheng.keyWord.WebKeyWord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class adminAddGoodsPage {

    public WebKeyWord wkw;
    public String url="http://www.testingedu.com.cn:8000/index.php/Admin/Index/index";

    @FindBy(xpath = "//li[@data-param='shop']/a")
    public WebElement shopPage;

    @FindBy(name = "workspace")
    public WebElement inworkspaceIframe;

    @FindBy(xpath = "//div[@title=\"添加商品\"]")
    public WebElement addGoodSubmit;

    @FindBy(xpath = "//input[@name='goods_name']")
    public WebElement goods_name;

    @FindBy(xpath = "//textarea[@name='goods_remark']")
    public WebElement goods_remark;

    @FindBy(xpath = "//input[@name='goods_sn']")
    public WebElement goods_sn;

    @FindBy(xpath = "//select[@name='cat_id']")
    public WebElement cat_id;

    @FindBy(xpath = "//select[@name='cat_id_2']")
    public WebElement cat_id_2;

    @FindBy(xpath = "//select[@name='cat_id_3']")
    public WebElement cat_id_3;

    @FindBy(xpath = "//input[@name='shop_price']")
    public WebElement shop_price;

    @FindBy(xpath = "//input[@name='market_price']")
    public WebElement market_price;

    @FindBy(xpath = "//*[@id=\"addEditGoodsForm\"]/div[1]/dl[13]/dd/div")
    public WebElement openpictureframe;

    @FindBy(name = "layui-layer-iframe1")
    public WebElement inpictureIframe;

    @FindBy(xpath = "//div[@id='filePicker']/div/input[@class=\"webuploader-element-invisible\"]")
    public WebElement uploadPicture;

    @FindBy(xpath = "//a[@class='layui-layer-ico layui-layer-close layui-layer-close1']")
    public WebElement closedpictureframe;

    public adminAddGoodsPage(WebKeyWord keyword){
        wkw = keyword;
        PageFactory.initElements(wkw.driver,this);
    }

    public void load(){
        wkw.visitURL(url);
    }

    public void addGoods() {
        shopPage.click();
        wkw.driver.switchTo().frame(inworkspaceIframe);
        addGoodSubmit.click();
        goods_name.sendKeys("testGOOD");
        goods_remark.sendKeys("测试商品简介");
        goods_sn.sendKeys("TP_TEST_0000001");
        Select sel1 = new Select(cat_id);
        sel1.selectByIndex(2);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Select sel2 = new Select(cat_id_2);
        sel2.selectByVisibleText("生活日品");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Select sel3 = new Select(cat_id_3);
        sel3.selectByValue("287");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shop_price.sendKeys("299.98");
        market_price.sendKeys("698.00");
        openpictureframe.click();
        wkw.driver.switchTo().frame(inpictureIframe);
        uploadPicture.sendKeys("C:\\Users\\86158\\Pictures\\Camera Roll\\乐高400.jpg");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wkw.driver.switchTo().parentFrame();
        closedpictureframe.click();
    }
}
