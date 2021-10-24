package com.lizheng.testWeb;


import com.lizheng.keyWord.WebKeyWord;

public class testkeyWord {
    public static void main(String[] args) {
        WebKeyWord wkw = new WebKeyWord();
        wkw.openBrowser("chrome");
        wkw.visitURL("https://www.baidu.com");
        wkw.input("//*[@id=\"kw\"]","java");
        wkw.click("//*[@id=\"su\"]");
        wkw.getTitle();
        wkw.mustWait("5");
        wkw.assertByTitle("java");
        wkw.closeBrowser();
    }
}
