package com.lizheng.testWeb;

import com.lizheng.common.ExcelReader;
import com.lizheng.common.ExcelWriter;
import com.lizheng.keyWord.DDTOfWeb;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 使用反射的方式进行数据驱动
 */
public class testDDTOfWebByInvoke {
    public static void main(String[] args) {
        ExcelReader cases = new ExcelReader("cases/WebCases.xlsx");
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
                    invokeWeb(web,rowContent);
                }
            }
        }
        cases.close();
        results.save();
    }

    /**
     * 反射方法，适用于web用例中的D列
     * @param api 关键字类
     * @param rowContent excel中的行数据
     */
    public static void invokeWeb(Object api,List<String> rowContent){
        //查找关键字，且该关键字没有参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3));
            target.invoke(api);
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有一个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class);
            target.invoke(api,rowContent.get(4));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有两个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有三个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5),rowContent.get(6));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有四个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class,String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5),rowContent.get(6),rowContent.get(7));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有五个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class,String.class,String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5),rowContent.get(6),rowContent.get(7),rowContent.get(8));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有六个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class,String.class,String.class,String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5),rowContent.get(6),rowContent.get(7),rowContent.get(8),rowContent.get(9));
            return;
        } catch (Exception e) {
        }
    }
}
