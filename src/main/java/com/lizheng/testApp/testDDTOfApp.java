package com.lizheng.testApp;

import com.lizheng.common.ExcelReader;
import com.lizheng.common.ExcelWriter;
import com.lizheng.keyWord.DDTOfApp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class testDDTOfApp {
    public static void main(String[] args) {

        ExcelReader cases = new ExcelReader("Cases/AppCases.xlsx");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String nowtime = sdf.format(date);
        //构造方法复制一份用例文件，生成结果文件。
        ExcelWriter results = new ExcelWriter("cases/AppCases.xlsx", "casesResult/AppResult" + nowtime + ".xlsx");
        DDTOfApp app = new DDTOfApp(results);
        //先遍历sheet页
        for (int sheetNo = 0; sheetNo < cases.getTotalSheetNo(); sheetNo++) {
            //使用当前sheet页
            cases.useSheetByIndex(sheetNo);
            results.useSheetByIndex(sheetNo);
            //遍历sheet页每一行
            for (int rowNo = 0; rowNo < cases.getRowNo(); rowNo++) {
                app.setLine(rowNo);
                //读取当前行内容为list
                List<String> rowContent = cases.readLine(rowNo);
//                System.out.println(rowContent);
                //基于一行内容执行用例
                //第一列和第二列都为空，是用例行，要执行
                if ((rowContent.get(0).equals("") || rowContent.get(0).trim().length() < 1) &&
                        (rowContent.get(1).equals("") || rowContent.get(1).trim().length() < 1)) {
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
            }
        }
        cases.close();
        results.save();
    }
}
