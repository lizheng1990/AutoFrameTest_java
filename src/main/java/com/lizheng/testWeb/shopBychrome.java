package com.lizheng.testWeb;

import com.lizheng.keyWord.WebKeyWord;

public class shopBychrome {
    public static void main(String[] args) {
        WebKeyWord wkw = new WebKeyWord();
//        选择chrome
        wkw.openBrowser("chrome");
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
        wkw.switchWindowByTitle("首页");
//        点击进入我的订单页面
        wkw.click("/html/body/div[1]/div[1]/div/ul/li[1]/a");
        wkw.switchWindowByTitle("我的订单");
        wkw.click("/html/body/div[1]/div/div/div/div[2]/a[2]");
        wkw.closeBrowser();


    }
}
