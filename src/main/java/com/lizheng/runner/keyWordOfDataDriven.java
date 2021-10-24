package com.lizheng.runner;

import com.lizheng.common.*;
import com.lizheng.keyWord.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class keyWordOfDataDriven {
    public static DDTOfApp app;
    public static DDTOfInter inter;
    public static DDTOfWeb web;
    public static ExcelReader cases;
    public static ExcelWriter results;
    public static ExcelResult er;
    public static InvokeKeyWord invokeKeyWord;
    public static SwitchKeyWord switchKeyWord;

    /**
     * 集成inter/app/web的执行类，通过传入参数执行指定类型的用例文件
     * @param args
     */
    public static void main(String[] args) {
        //首先指定用例类型inter/app/web
        String type = args[0];
        //生成时间格式字符串，用于结果文件命名
        String nowtime = createDate("yyyy-MM-dd HH-mm-ss");
        String resultFileName = "";
        AutoLogger.log.info("你选择运行的自动化测试类型为：" + type);
        try{
            AutoLogger.log.info("---------------------------自动化测试运行开始---------------------------");
            switch (type){
                case "inter":
                    cases = new ExcelReader("cases/InterCases.xlsx");
                    resultFileName = "casesResult/InterResult" + nowtime + ".xlsx";
                    //构造方法复制一份用例文件，生成结果文件。
                    results = new ExcelWriter("cases/InterCases.xlsx", resultFileName);
                    inter = new DDTOfInter(results);
                    break;
                case "web":
                    cases = new ExcelReader("cases/WebCases.xlsx");
                    resultFileName = "casesResult/WebResult" + nowtime + ".xlsx";
                    //构造方法复制一份用例文件，生成结果文件。
                    results = new ExcelWriter("cases/WebCases.xlsx", resultFileName);
                    web = new DDTOfWeb(results);
                    break;
                case "app":
                    cases = new ExcelReader("cases/AppCases.xlsx");
                    resultFileName = "casesResult/AppResult" + nowtime + ".xlsx";
                    //构造方法复制一份用例文件，生成结果文件。
                    results = new ExcelWriter("cases/AppCases.xlsx", resultFileName);
                    app = new DDTOfApp(results);
                    break;
                default:
                    AutoLogger.log.error("自动化执行类型错误！请输入web、app、inter");
                    break;
            }
            //使用反射方法运行用例
//            execaseByInvoke(type);
            //使用switch方法运行用例
            execaseBySwitch(type);
        }catch(Exception e) {
            AutoLogger.log.error("获取文件失败，请检查！！！");
            AutoLogger.log.error(e.fillInStackTrace());
        }
        AutoLogger.log.info("---------------------------自动化测试运行结束---------------------------");
        AutoLogger.log.info("---------------------------测试结果统计运行开始---------------------------");
//        er = new ExcelResult();
//        List<Map<String,String>> list = er.Sumarry(resultFileName,nowtime);
//        System.out.println(list);
        AutoLogger.log.info("---------------------------测试结果统计运行结束---------------------------");
        Report report = new Report();
        report.sendReport(resultFileName,nowtime,resultFileName);
        AutoLogger.log.info("---------------------------邮件发送完毕---------------------------");
    }

    /**
     * 生成指定时间格式字符串
     * @param dateFormat
     * @return
     */
    private static String createDate(String dateFormat) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    /**
     * 调用invoke反射方法执行用例
     * @param type 用例类型 inter/app/web
     */
    private static void execaseByInvoke(String type){
        for (int sheetNo = 0; sheetNo < cases.getTotalSheetNo(); sheetNo++) {
            //使用当前sheet页
            cases.useSheetByIndex(sheetNo);
            results.useSheetByIndex(sheetNo);
            //遍历sheet页每一行
            for (int rowNo = 0; rowNo < cases.getRowNo(); rowNo++) {
                //读取当前行内容为list
                List<String> rowContent = cases.readLine(rowNo);
//                System.out.println(rowContent);
                //基于一行内容执行用例
                //第一列和第二列都为空，是用例行，要执行
                if ((rowContent.get(0).equals("") || rowContent.get(0).trim().length() < 1) &&
                        (rowContent.get(1).equals("") || rowContent.get(1).trim().length() < 1)) {
                    switch (type){
                        case "inter":
                            inter.setLine(rowNo);
                            invokeKeyWord.invokeInter(inter, rowContent);
                            invokeKeyWord.invokeAssert(inter, rowContent);
                            break;
                        case "web":
                            web.setLine(rowNo);
                            invokeKeyWord.invokeWeb(web,rowContent);
                            break;
                        case "app":
                            app.setLine(rowNo);
                            invokeKeyWord.invokeApp(app,rowContent);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        cases.close();
        results.save();
    }

    /**
     * 调用switch方法执行用例
     * @param type inter/app/web
     */
    private static void execaseBySwitch(String type){
        for (int sheetNo = 0; sheetNo < cases.getTotalSheetNo(); sheetNo++) {
            //使用当前sheet页
            cases.useSheetByIndex(sheetNo);
            results.useSheetByIndex(sheetNo);
            //遍历sheet页每一行
            for (int rowNo = 0; rowNo < cases.getRowNo(); rowNo++) {
                //读取当前行内容为list
                List<String> rowContent = cases.readLine(rowNo);
                System.out.println(rowContent);
                //基于一行内容执行用例
                //第一列和第二列都为空，是用例行，要执行
                if ((rowContent.get(0).equals("") || rowContent.get(0).trim().length() < 1) &&
                        (rowContent.get(1).equals("") || rowContent.get(1).trim().length() < 1)) {
                    switch (type){
                        case "inter":
                            inter.setLine(rowNo);
                            switchKeyWord.switchInter(inter, rowContent);
                            break;
                        case "web":
                            web.setLine(rowNo);
                            switchKeyWord.switchWeb(web, rowContent);
                            break;
                        case "app":
                            app.setLine(rowNo);
                            switchKeyWord.switchApp(app, rowContent);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        cases.close();
        results.save();
    }
}