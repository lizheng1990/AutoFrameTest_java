package com.lizheng.testWeb;

import com.lizheng.common.AutoLogger;
import com.lizheng.keyWord.WebKeyWord;

public class testByPO {
    public static void main(String[] args) {
        WebKeyWord wkw = new WebKeyWord();
        AutoLogger.log.info("打开浏览器");
        wkw.openBrowser("chrome");
        AutoLogger.log.info("后台登录");
        adminLogin(wkw);
        AutoLogger.log.info("新增商品");
        adminAddGoods(wkw);
        wkw.mustWait("10");
        shopLogin(wkw);
        shopJoinCart(wkw);
        pay(wkw);
        wkw.switchWindowByTitle("首页");
//        点击进入我的订单页面
        wkw.click("/html/body/div[1]/div[1]/div/ul/li[1]/a");
        wkw.switchWindowByTitle("我的订单");
        wkw.click("/html/body/div[1]/div/div/div/div[2]/a[2]");
        wkw.closeBrowser();
    }

    public static void pay(WebKeyWord wkw) {
        //        点击去结算按钮
        wkw.mustWait("3");
        wkw.click("/html/body/div[4]/div/div/div/div[2]/div[2]/div[1]/a");
//        滑动到提交订单按钮处
        wkw.scrollToXpath("/html/body/div[14]/div/button");
//        点击提交订单按钮
        wkw.click("/html/body/div[14]/div/button");
//        选择货到付款方式
        wkw.click("//*[@id=\"input-ALIPAY-1\"]");
//        点击确认支付方式
        wkw.click("//*[@id=\"cart4_form\"]/div/div/div/a");
//        关闭当前页面
        wkw.closeWebPage();
    }

    public static void shopJoinCart(WebKeyWord wkw) {
        //        鼠标悬停到商品分类上
        wkw.hover("/html/body/div[1]/div[3]/div/div[1]/a");
//        鼠标悬停到手机上
        wkw.hover("//*[@id=\"cata-nav\"]/div[1]/div[1]/h3/div/a");
//        点击手机按钮
        wkw.click("//*[@id=\"cata-nav\"]/div[1]/div[2]/div[1]/div[2]/dl[1]/dd/a[1]");
//        切换到新窗口
        wkw.switchWindow();
//        滑动页面到指定xpath
        wkw.scrollToXpath("/html/body/div[4]/div/div[2]/div[2]/ul/li[5]/div/div[4]/a");
//        点击添加到购物车按钮
        wkw.click("/html/body/div[4]/div/div[2]/div[2]/ul/li[5]/div/div[5]/div[2]/a");
//        选择“全网通4G+64G”
        wkw.click("//*[@id=\"goods_spec_a_10\"]");
//        选择套餐二
        wkw.click("//*[@id=\"goods_spec_a_17\"]");
//        点击加入购物车
        wkw.click("//*[@id=\"join_cart\"]");
//        切换到弹出iframe
//        wkw.switchIframeByXpath("//*[@id=\"layui-layer-iframe1\"]");
        wkw.mustWait("3");
//        点击关闭弹出框
        wkw.click("//a[@class='layui-layer-ico layui-layer-close layui-layer-close1']");
//        点击进入购物车页面
        wkw.click("//*[@id=\"hd-my-cart\"]/a/div/span");
    }

    public static void shopLogin(WebKeyWord wkw) {
        //        访问商城首页
        wkw.visitURL("http://8.142.92.43/");
//        点击进入登录页面
        wkw.click("/html/body/div[1]/div[1]/div/div/div[2]/a[1]");
//        输入手机号
        wkw.input("//*[@id=\"username\"]","13900000000");
//        输入密码
        wkw.input("//*[@id=\"password\"]","123456");
//        输入验证码
        wkw.input("//*[@id=\"verify_code\"]","1");
//        点击登录按钮
        wkw.click("//*[@id=\"loginform\"]/div/div[6]/a");
//        刷新页面
        wkw.refresh();
//        点击返回商城首页
        wkw.click("//a[@class='logo']/img");
    }

    public static void adminAddGoods(WebKeyWord wkw) {
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
        wkw.upload("C:\\Users\\86158\\Pictures\\Camera Roll\\乐高400.jpg");
//        wkw.input("//div[@id='filePicker']/div/input[@class=\"webuploader-element-invisible\"]","C:\\Users\\86158\\Pictures\\Camera Roll\\乐高400.jpg");
//        等待3秒
        wkw.mustWait("3");
//        返回新增商品iframe
        wkw.switchUpIfarme();
//        点击关闭弹出框
        wkw.click("//a[@class='layui-layer-ico layui-layer-close layui-layer-close1']");
    }

    public static void adminLogin(WebKeyWord wkw) {
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
    }

}