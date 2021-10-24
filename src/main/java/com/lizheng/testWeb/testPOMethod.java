package com.lizheng.testWeb;

import com.lizheng.POmethod.*;
import com.lizheng.keyWord.WebKeyWord;

public class testPOMethod {
    public static void main(String[] args) {
        WebKeyWord wkw = new WebKeyWord();
        wkw.openBrowser("chrome");

        adminLoginPage adminlogin = new adminLoginPage(wkw);
        adminlogin.load();
        adminlogin.adminLogin();

        adminAddGoodsPage adminaddgoods = new adminAddGoodsPage(wkw);
//        adminaddgoods.load();
        adminaddgoods.addGoods();

        shopLoginPage shoplogin = new shopLoginPage(wkw);
        shoplogin.load();
        shoplogin.shopLogin();

        shopJoinCartPage shopjoincart = new shopJoinCartPage(wkw);
        shopjoincart.load();
        shopjoincart.shopJoinCart();

        shopPayPage shoppay = new shopPayPage(wkw);
        shoppay.load();
        shoppay.shopPay();

    }
}
