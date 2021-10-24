package com.lizheng.testWeb;

import com.lizheng.keyWord.WebKeyWord;

public class AdminByChrome {
    public static void main(String[] args) {
        WebKeyWord wkw = new WebKeyWord();
        wkw.openBrowser("chrome");
        //进入登录页面
        wkw.visitURL("http://8.142.92.43/index.php/Admin/Admin/login");
        //输入用户名
        wkw.input("//input[@name='username']","admin");
//        输入密码
        wkw.input("//input[@name='password']","123456");
//        输入验证码
        wkw.input("//input[@name='vertify']","1");
//        点击登录按钮
        wkw.click("//input[@name='submit']");
//        点击商城按钮
        wkw.click("//li[@data-param='shop']/a");
//        进入iframe
        wkw.switchIframe("workspace");
//        点击添加商品按钮
        wkw.click("//div[@title=\"添加商品\"]");
//        输入商品名称
        wkw.input("//input[@name='goods_name']","testGOOD");
//        输入商品简介
        wkw.input("//textarea[@name='goods_remark']","测试商品简介");
//        输入商品sn
        wkw.input("//input[@name='goods_sn']","TP_TEST_0000001");
//        选择商品分类
        wkw.selectByIndex("//select[@name='cat_id']","3");
        wkw.selectByContent("//select[@name='cat_id_2']","生活日品");
        wkw.selectByValue("//select[@name='cat_id_3']","287");
//        输入商品售价
        wkw.input("//input[@name='shop_price']","299.98");
//        输入商品市场价
        wkw.input("//input[@name='market_price']","688.00");
//        点击打开上传图片框
        wkw.click("//*[@id=\"addEditGoodsForm\"]/div[1]/dl[13]/dd/div");
//        进入iframe
        wkw.switchIframe("layui-layer-iframe1");
//        点击上传按钮
        wkw.click("//*[@id=\"filePicker\"]/div/label");
//        上传图片
        wkw.upload("C:\\Users\\45360\\Pictures\\Camera Roll\\乐高400.jpg");
//        wkw.input("//div[@id='filePicker']/div/input[@class=\"webuploader-element-invisible\"]","C:\\Users\\86158\\Pictures\\Camera Roll\\乐高400.jpg");
//        等待3秒
        wkw.mustWait("3");
//        返回新增商品iframe
        wkw.switchUpIfarme();
//        点击关闭弹出框
        wkw.click("//a[@class='layui-layer-ico layui-layer-close layui-layer-close1']");
        wkw.mustWait("10");




    }

}
