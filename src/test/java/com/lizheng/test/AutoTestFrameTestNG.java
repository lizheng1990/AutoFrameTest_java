package com.lizheng.test;

import com.lizheng.common.ExcelReader;
import com.lizheng.common.ExcelResult;
import com.lizheng.common.ExcelWriter;
import com.lizheng.keyWord.DDTOfInter;
import com.lizheng.keyWord.DDTOfInterTestNG;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoTestFrameTestNG {
    public ExcelReader er;
    public ExcelWriter results;
    public DDTOfInterTestNG inter;

    @Test(dataProvider = "caseData")
    public void autoTest(String No,String casename,String keyName,String keyWord,String param1,String param2,String param3,String assertName,String assertParam1,String assertParam2,String result,String response){
        System.out.println(No + "\t" + casename + "\t" + keyName + "\t" + keyWord + "\t" + param1 + "\t" + param2 + "\t" + param3 + "\t" + assertName + "\t" + assertParam1 + "\t" + assertParam2);
        int no = Integer.parseInt(No);
        inter.setLine(no);
        String returnValue = invokeInter(keyWord,param1,param2,param3);
        Assert.assertEquals(returnValue,"pass");
    }

    @DataProvider(name = "caseData")
    public Object[][] proveideDbconfid(){
        System.out.println(er.readAsMatrix());
        return er.readAsMatrix();
    }

    @BeforeTest
    public void beforeTest(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String nowtime = sdf.format(date);
        er = new ExcelReader("cases/TestNGInterCases.xlsx");
        String resultFileName = "casesResult/TestNGInterCasesResult" + nowtime + ".xlsx";
        //构造方法复制一份用例文件，生成结果文件。
        results = new ExcelWriter("cases/TestNGInterCases.xlsx", resultFileName);
        inter = new DDTOfInterTestNG(results);
    }

    @AfterTest
    public void afterTest(){
        er.close();
        results.save();
    }

    public String invokeInter(String keyword,String param1,String param2,String param3){
        String result = "fail";
        //查找关键字，且该关键字没有参数
        try {
            Method target = inter.getClass().getDeclaredMethod(keyword);
            result = target.invoke(inter).toString();
            return result;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有一个参数
        try {
            Method target = inter.getClass().getDeclaredMethod(keyword,String.class);
            result = target.invoke(inter,param1).toString();
            return result;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有两个参数
        try {
            Method target = inter.getClass().getDeclaredMethod(keyword,String.class,String.class);
            result = target.invoke(inter,param1,param2).toString();
            return result;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有三个参数
        try {
            Method target = inter.getClass().getDeclaredMethod(keyword,String.class,String.class,String.class);
            result = target.invoke(inter,param1,param2,param3).toString();
            return result;
        } catch (Exception e) {
        }
        return result;
    }

}
