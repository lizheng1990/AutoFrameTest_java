package com.lizheng.testWeb;

import com.lizheng.common.ExcelReader;
import com.lizheng.common.ExcelWriter;
import com.lizheng.keyWord.DDTOfWeb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class testDDTOfWeb {
    public static void main(String[] args) {
        ExcelReader cases = new ExcelReader("Cases/WebCases.xlsx");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String nowtime = sdf.format(date);
        //构造方法复制一份用例文件，生成结果文件。
        ExcelWriter results = new ExcelWriter("cases/WebCases.xlsx", "casesResult/WebResult" + nowtime + ".xlsx");
        DDTOfWeb web = new DDTOfWeb(results);
        //先遍历sheet页
        for (int sheetNo = 0; sheetNo < cases.getTotalSheetNo(); sheetNo++) {
            //使用当前sheet页
            cases.useSheetByIndex(sheetNo);
            results.useSheetByIndex(sheetNo);
            //遍历sheet页每一行
            for (int rowNo = 0; rowNo < cases.getRowNo(); rowNo++) {
                web.setLine(rowNo);
                //读取当前行内容为list
                List<String> rowContent = cases.readLine(rowNo);
//                System.out.println(rowContent);
                //基于一行内容执行用例
                //第一列和第二列都为空，是用例行，要执行
                if ((rowContent.get(0).equals("") || rowContent.get(0).trim().length() < 1) &&
                        (rowContent.get(1).equals("") || rowContent.get(1).trim().length() < 1)) {
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
            }
        }
        cases.close();
        results.save();
    }
}
