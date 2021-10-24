package com.lizheng.test;

import com.lizheng.common.ExcelReader;
import com.lizheng.common.ExcelResult;
import com.lizheng.common.ExcelWriter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testCount {
    public static void main(String[] args) {
        ExcelResult er = new ExcelResult();
//        er.setResultCol(10);
        List<Map<String,String>> list = er.Sumarry("casesResult/InterResult2021-10-10 16-11-22.xlsx","2021-10-10 16-11-22");
        System.out.println(list);

//        ExcelReader cases = new ExcelReader("casesResult/InterResult2021-10-10 16-11-22.xlsx");
//        Map<String,Integer> countData = new HashMap<>();
//        boolean flag = true;
//        String countName = "";
//        float totalCount = 0;
//        float passCount = 0;
//        float failCount = 0;
//        //先遍历sheet页
//        for (int sheetNo = 1; sheetNo < cases.getTotalSheetNo()-5; sheetNo++) {
//            //使用当前sheet页
//            cases.useSheetByIndex(sheetNo);
//            System.out.println("当前页为第：" + sheetNo + "页");
//            System.out.println("当前sheet名为：" + cases.getSheetName(sheetNo));
//            //遍历sheet页每一行
//            for (int rowNo = 1; rowNo < cases.getRowNo(); rowNo++) {
//                //读取当前行内容为list
//                List<String> rowContent = cases.readLine(rowNo);
////                System.out.println(rowContent);
//                if((!rowContent.get(0).equals("")||rowContent.get(0).trim().length()>0)&&((rowContent.get(1).equals(""))||rowContent.get(0).trim().length()<1)){
//                    countName = cases.getSheetName(sheetNo) + "-->" + rowContent.get(0);
//                    System.out.println("统计名字为：" + countName);
//                    flag = false;
//                }else{
//                    //基于一行内容执行用例
//                    //第一列和第二列都为空，是用例行，要执行
//                    if ((rowContent.get(0).equals("") || rowContent.get(0).trim().length() < 1) &&
//                            (rowContent.get(1).equals("") || rowContent.get(1).trim().length() < 1)) {
//                        if(rowContent.get(10).equals("pass")){
//                            passCount++;
//                        }else {
//                            failCount++;
//                        }
//                    }
//                }
//                if(flag){
//                    totalCount = passCount + failCount;
//                    float m = (float) (passCount/totalCount * 100.0);
//                    System.out.println("pass数量为：" + passCount);
//                    System.out.println("fail数量为：" + failCount);
//                    System.out.println("总数量为：" + m + "%");
//                    countName = "";
//                    totalCount = 0;
//                    passCount = 0;
//                    failCount = 0;
//                    flag=true;
//                }
//                }
//
//        }
//        cases.close();
    }
}
