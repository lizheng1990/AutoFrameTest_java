package com.lizheng.testInterface;

import com.lizheng.common.ExcelReader;
import com.lizheng.common.ExcelWriter;
import com.lizheng.keyWord.DDTOfInter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class testDDTOfInter {
    public static void main(String[] args) {
        ExcelReader cases = new ExcelReader("Cases/InterCases.xlsx");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String nowtime = sdf.format(date);
        //构造方法复制一份用例文件，生成结果文件。
        ExcelWriter results = new ExcelWriter("cases/InterCases.xlsx", "casesResult/InterResult" + nowtime + ".xlsx");
        DDTOfInter inter = new DDTOfInter(results);
        //先遍历sheet页
        for (int sheetNo = 0; sheetNo < cases.getTotalSheetNo(); sheetNo++) {
            //使用当前sheet页
            cases.useSheetByIndex(sheetNo);
            results.useSheetByIndex(sheetNo);
            //遍历sheet页每一行
            for (int rowNo = 0; rowNo < cases.getRowNo(); rowNo++) {
                inter.setLine(rowNo);
                //读取当前行内容为list
                List<String> rowContent = cases.readLine(rowNo);
                System.out.println(rowContent);
                //基于一行内容执行用例
                //第一列和第二列都为空，是用例行，要执行
                if ((rowContent.get(0).equals("") || rowContent.get(0).trim().length() < 1) &&
                        (rowContent.get(1).equals("") || rowContent.get(1).trim().length() < 1)) {
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
        }
        cases.close();
        results.save();
    }
}
