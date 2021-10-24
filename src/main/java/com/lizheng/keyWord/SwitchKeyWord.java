package com.lizheng.keyWord;

import java.util.List;

/**
 * 封装switch方法执行excel
 */
public class SwitchKeyWord {
    /**
     * switch方法，运行DDTOfApp关键字
     * @param app
     * @param rowContent
     */
    public static void switchApp(DDTOfApp app, List<String> rowContent){
        switch (rowContent.get(3)) {
            case "runCmd":
                app.runCmd(rowContent.get(4));
                break;
            case "startAppium":
                app.startAppium(rowContent.get(4),rowContent.get(5));
                break;
            case "runAPP":
                app.runAPP(rowContent.get(4), rowContent.get(5), rowContent.get(6),
                        rowContent.get(7), rowContent.get(8), rowContent.get(9));
                break;
            case "runBrowser":
                app.runBrowser(rowContent.get(4), rowContent.get(5), rowContent.get(6),rowContent.get(7));
                break;
            case "visitURL":
                app.visitURL(rowContent.get(4));
                break;
            case "mustWait":
                app.mustWait(rowContent.get(4));
                break;
            case "implicitWait":
                app.implicitWait(rowContent.get(4));
                break;
            case "waitForXpath":
                app.waitForXpath(rowContent.get(4), rowContent.get(5));
                break;
            case "waitForContent":
                app.waitForContent(rowContent.get(4), rowContent.get(5), rowContent.get(6));
                break;
            case "adbTap":
                app.adbTap(rowContent.get(4), rowContent.get(5));
                break;
            case "adbSwipe":
                app.adbSwipe(rowContent.get(4), rowContent.get(5), rowContent.get(6),
                        rowContent.get(7), rowContent.get(8));
                break;
            case "adbText":
                app.adbText(rowContent.get(4));
                break;
            case "adbPressKey":
                app.adbPressKey(rowContent.get(4));
                break;
            case "appiumSwipe":
                app.appiumSwipe(rowContent.get(4), rowContent.get(5), rowContent.get(6),rowContent.get(7));
                break;
            case "appiumTap":
                app.appiumTap(rowContent.get(4), rowContent.get(5));
                break;
            case "click":
                app.click(rowContent.get(4));
                break;
            case "clickById":
                app.clickById(rowContent.get(4));
                break;
            case "clickByAccId":
                app.clickByAccId(rowContent.get(4));
                break;
            case "appiumHoldPoint":
                app.appiumHoldPoint(rowContent.get(4), rowContent.get(5), rowContent.get(6));
                break;
            case "appiumHold":
                app.appiumHold(rowContent.get(4), rowContent.get(5));
                break;
            case "clear":
                app.clear(rowContent.get(4));
                break;
            case "clearById":
                app.clearById(rowContent.get(4));
                break;
            case "clearByAccId":
                app.clearByAccId(rowContent.get(4));
                break;
            case "input":
                app.input(rowContent.get(4), rowContent.get(5));
                break;
            case "inputById":
                app.inputById(rowContent.get(4), rowContent.get(5));
                break;
            case "inputByAccId":
                app.inputByAccId(rowContent.get(4), rowContent.get(5));
                break;
            case "refresh":
                app.refresh();
                break;
            case "printcontexts":
                app.printcontexts();
                break;
            case "switchContext":
                app.switchContext(rowContent.get(4));
                break;
            case "explicityWait":
                app.explicityWait(rowContent.get(4),rowContent.get(5));
                break;
            case "closeBrowser":
                app.closeBrowser();
                break;
            case "quitApp":
                app.quitApp();
                break;
            case "killAppium":
                app.killAppium();
                break;
        }
    }

    /**
     * switch方法，运行DDTOfWeb关键字
     * @param web
     * @param rowContent
     */
    public static void switchWeb(DDTOfWeb web, List<String> rowContent){
        switch (rowContent.get(3)) {
            case "openBrowser":
                web.openBrowser(rowContent.get(4));
                break;
            case "visitURL":
                web.visitURL(rowContent.get(4));
                break;
            case "input":
                web.input(rowContent.get(4), rowContent.get(5));
                break;
            case "click":
                web.click(rowContent.get(4));
                break;
            case "switchIframe":
                web.switchIframe(rowContent.get(4));
                break;
            case "selectByIndex":
                web.selectByIndex(rowContent.get(4),rowContent.get(5));
                break;
            case "selectByContent":
                web.selectByContent(rowContent.get(4),rowContent.get(5));
                break;
            case "selectByValue":
                web.selectByValue(rowContent.get(4), rowContent.get(5));
                break;
            case "upload":
                web.upload(rowContent.get(4));
                break;
            case "mustWait":
                web.mustWait(rowContent.get(4));
                break;
            case "switchUpIfarme":
                web.switchUpIfarme();
                break;
            case "refresh":
                web.refresh();
                break;
            case "hover":
                web.hover(rowContent.get(4));
                break;
            case "switchWindow":
                web.switchWindow();
                break;
            case "switchWindowByTitle":
                web.switchWindowByTitle(rowContent.get(4));
                break;
            case "scrollToXpath":
                web.scrollToXpath(rowContent.get(4));
                break;
            case "closeWebPage":
                web.closeWebPage();
                break;
            case "closeBrowser":
                web.closeBrowser();
                break;
        }
    }

    /**
     * switch方法，运行DDTOfInter关键字
     * @param inter
     * @param rowContent
     */
    public static void switchInter(DDTOfInter inter, List<String> rowContent){
        switch (rowContent.get(3)) {
            case "doGet":
                inter.doGet(rowContent.get(4),rowContent.get(5));
                break;
            case "doPost":
                inter.doPost(rowContent.get(4),rowContent.get(5),rowContent.get(6));
                break;
            case "doPostWithoutParam":
                inter.doPostWithoutParam(rowContent.get(4));
                break;
            case "doPostByUrl":
                inter.doPostByUrl(rowContent.get(4),rowContent.get(5));
                break;
            case "doPostByJson":
                inter.doPostByJson(rowContent.get(4),rowContent.get(5));
                break;
            case "upload":
                inter.upload(rowContent.get(4),rowContent.get(5),rowContent.get(6));
                break;
            case "downloadWithName":
                inter.downloadWithName(rowContent.get(4),rowContent.get(5));
                break;
            case "download":
                inter.download(rowContent.get(4));
                break;
            case "useCookie":
                inter.useCookie();
                break;
            case "notUseCookie":
                inter.notUseCookie();
                break;
            case "clearCookie":
                inter.clearCookie();
                break;
            case "addHeader":
                inter.addHeader(rowContent.get(4));
                break;
            case "notUseHeader":
                inter.notUseHeader();
                break;
            case "clearHeader":
                inter.clearHeader();
                break;
            case "parasSOAP":
                inter.parasSOAP(rowContent.get(4));
                break;
            case "saveParam":
                inter.saveParam(rowContent.get(4),rowContent.get(5));
                break;
        }
        switch (rowContent.get(7)) {
            case "assertJsonEqual":
                inter.assertJsonEqual(rowContent.get(8),rowContent.get(9));
                break;
            case "assertJsonContains":
                inter.assertJsonContains(rowContent.get(8),rowContent.get(9));
                break;
            case "assertTextContains":
                inter.assertTextContains(rowContent.get(9));
                break;
        }
    }
}
