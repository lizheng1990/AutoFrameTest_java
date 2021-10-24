package com.lizheng.keyWord;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lizheng.common.AutoLogger;
import com.lizheng.common.ExcelWriter;
import com.lizheng.createDrivers.InterDriverOfHttp;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DDTOfInterTestNG {

    //存储接口返回信息
    public String responseResult;
    //声明一个InterDriverOfHttp对象
    public InterDriverOfHttp interDriver;
    //用于存储获取到的参数
    public Map<String,String> paramMap;

    //excelwriter对象，用于每个方法执行的时候，完成结果的写入。
    public ExcelWriter results;
    //用于记录当前操作的行号
    public int writeLine;
    //执行结果写入的列，固定为10
    public static final int STA_COL = 10;
    //实际返回写入的列，固定为11
    public static final int RES_COL = 11;
    public static final String PASS = "pass";
    public static final String FAIL = "fail";

    //传参完成excelwriter对象的赋值。
    public DDTOfInterTestNG(ExcelWriter result) {
        results = result;
        interDriver = new InterDriverOfHttp();
        paramMap = new HashMap<String,String>();
    }

    //设置当前操作的行号
    public void setLine(int line) {
        writeLine = line;
    }

    /**
     * 封装的get方法
     * @param url
     * @param param
     */
    public String doGet(String url,String param){
        try{
            url =toParam(url);
            param = toParam(param);
            responseResult = interDriver.doGet(url,param);
            AutoLogger.log.info("执行get请求成功！！！");
            results.writeCell(writeLine,STA_COL,PASS);
            results.writeCell(writeLine,RES_COL,responseResult);
            return PASS;
        }catch (Exception e){
            results.writeFailCell(writeLine, STA_COL, FAIL);
            results.writeFailCell(writeLine, RES_COL, String.valueOf(e.fillInStackTrace()));
            AutoLogger.log.error("执行get请求失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     * 封装实现指定类型的post请求
     * @param url
     * @param param key=value&key=value/key=value&key=value/{key:value, key:value}
     * @param type text/url/json
     */
    public String doPost(String url,String param,String type){
        try{
            url =toParam(url);
            if(param == null||param.length()<1){
            }else {
                param = toParam(param);
            }
            responseResult = interDriver.doPost(url,param,type);
            AutoLogger.log.info("执行指定类型的post请求成功！！！");
            results.writeCell(writeLine,STA_COL,PASS);
            results.writeCell(writeLine,RES_COL,responseResult);
            return PASS;
        }catch (Exception e){
            results.writeFailCell(writeLine, STA_COL, FAIL);
            results.writeFailCell(writeLine, RES_COL, String.valueOf(e.fillInStackTrace()));
            AutoLogger.log.error("执行执行类型的post请求失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     * 封装无请求体的post请求
     * @param url
     */
    public String doPostWithoutParam(String url){
        try{
            url =toParam(url);
            responseResult = interDriver.doPostWithoutParam(url);
            AutoLogger.log.info("执行无请求体的post请求成功！！！");
            results.writeCell(writeLine,STA_COL,PASS);
            results.writeCell(writeLine,RES_COL,responseResult);
            return PASS;
        }catch (Exception e){
            results.writeFailCell(writeLine, STA_COL, FAIL);
            results.writeFailCell(writeLine, RES_COL, String.valueOf(e.fillInStackTrace()));
            AutoLogger.log.error("执行无请求体的post请求失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     * 封装x-www-form-urlencoded格式的post请求
     * @param url
     * @param param 参数格式为 key=value&key=value
     */
    public String doPostByUrl(String url,String param){
        try{
            url =toParam(url);
            if(param == null||param.length()<1){
            }else {
                param = toParam(param);
            }
//            System.out.println(param);
            responseResult = interDriver.doPostByUrl(url,param);
            AutoLogger.log.info("执行x-www-form-urlencoded类型的post请求成功！！！");
            results.writeCell(writeLine,STA_COL,PASS);
            results.writeCell(writeLine,RES_COL,responseResult);
            return PASS;
        }catch (Exception e){
            results.writeFailCell(writeLine, STA_COL, FAIL);
            results.writeFailCell(writeLine, RES_COL, String.valueOf(e.fillInStackTrace()));
            AutoLogger.log.error("执行x-www-form-urlencoded类型的post请求失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     * 封装json格式传参的post方法
     * @param url
     * @param paramJson 参数格式为 {key:value, key:value}
     */
    public String doPostByJson(String url,String paramJson){
        try{
            url =toParam(url);
            if(paramJson == null||paramJson.length()<1){
            }else {
                paramJson = toParam(paramJson);
            }
            responseResult = interDriver.doPostByJson(url,paramJson);
            AutoLogger.log.info("执行json格式post请求成功！！！");
            results.writeCell(writeLine,STA_COL,PASS);
            results.writeCell(writeLine,RES_COL,responseResult);
            return PASS;
        }catch (Exception e){
            results.writeFailCell(writeLine, STA_COL, FAIL);
            results.writeFailCell(writeLine, RES_COL, String.valueOf(e.fillInStackTrace()));
            AutoLogger.log.error("执行json格式post请求失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     *通过post上传文件
     * @param url
     * @param paramOfBinaryBody 文件部分参数，格式为{"upload_file":"C:\\Users\\86158\\Pictures\\Camera Roll\\安全座椅750_1.jpg"}
     * @param paramOfTextBody 本部分参数，格式为{"submit":"提交"}
     */
    public String upload(String url,String paramOfBinaryBody,String paramOfTextBody){
        try{
            url =toParam(url);
            paramOfBinaryBody = toParam(paramOfBinaryBody);
            if(paramOfTextBody == null||paramOfTextBody.length()<1){
            }else {
                paramOfTextBody = toParam(paramOfTextBody);
            }
            responseResult = interDriver.doPostFile(url,paramOfBinaryBody,paramOfTextBody);
            AutoLogger.log.info("上传文件成功！！！");
            results.writeCell(writeLine,STA_COL,PASS);
            results.writeCell(writeLine,RES_COL,responseResult);
            return PASS;
        }catch (Exception e){
            results.writeFailCell(writeLine, STA_COL, FAIL);
            results.writeFailCell(writeLine, RES_COL, String.valueOf(e.fillInStackTrace()));
            AutoLogger.log.error("上传文件失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     * 根据url下载文件，并使用指定的路径和名称作为存储文件的路径和名称
     * @param url
     * @param filepath 绝对路径，如C:\Users\86158\Desktop\test1.png
     */
    public String downloadWithName(String url, String filepath){
        try{
            url =toParam(url);
            filepath = toParam(filepath);
            responseResult = interDriver.downloadWithName(url, filepath);
            AutoLogger.log.info("下载文件到指定目录成功！！！");
            results.writeCell(writeLine,STA_COL,PASS);
            results.writeCell(writeLine,RES_COL,responseResult);
            return PASS;
        }catch (Exception e){
            results.writeFailCell(writeLine, STA_COL, FAIL);
            results.writeFailCell(writeLine, RES_COL, String.valueOf(e.fillInStackTrace()));
            AutoLogger.log.error("下载文件到指定目录失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     * 根据url下载文件，文件名从response header头中获取,路径默认为D盘根目录
     * @param url
     */
    public String download(String url){
        try{
            url =toParam(url);
            responseResult = interDriver.download(url);
            AutoLogger.log.info("下载文件成功！！！");
            results.writeCell(writeLine,STA_COL,PASS);
            results.writeCell(writeLine,RES_COL,responseResult);
            return PASS;
        }catch (Exception e){
            results.writeFailCell(writeLine, STA_COL, FAIL);
            results.writeFailCell(writeLine, RES_COL, String.valueOf(e.fillInStackTrace()));
            AutoLogger.log.error("下载文件失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     * 获取13位时间戳
     * @return
     */
    public static String getRandomFileName() {
        String name = "1";
        try {
            name =  String.valueOf(System.currentTimeMillis());
            AutoLogger.log.info("获取13位时间戳成功！！！");
        }catch (Exception e) {
            AutoLogger.log.error("获取13位时间戳失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
        }
        return name;
    }

    /**
     * 断言解析出的json结果中等于预期内容
     * @param jsonPath jsonpath表达式
     * @param expectResult 预期内容
     */
    public String assertJsonEqual(String jsonPath, String expectResult){
        try {
            String actualResult = JSONPath.read(responseResult, jsonPath).toString();
            AutoLogger.log.info("解析的实际结果是："+actualResult);
            if(actualResult!=null){
                if(actualResult.equals(expectResult)){
                    AutoLogger.log.info("校验成功!!!");
                    results.writeCell(writeLine,STA_COL,PASS);
                    return PASS;
                }
                else{
                    results.writeFailCell(writeLine, STA_COL, FAIL);
                    AutoLogger.log.error("校验失败!!!");
                    return FAIL;
                }
            }
            else{
                AutoLogger.log.error("解析出的结果是空!!!");
                results.writeFailCell(writeLine, STA_COL, FAIL);
                return FAIL;
            }
        } catch (Exception e) {
            results.writeFailCell(writeLine, STA_COL, FAIL);
            AutoLogger.log.error("解析json并断言失败!!!");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     * 断言解析出的json结果中包含预期内容
     * @param jsonPath jsonpath表达式
     * @param expectResult  预期内容
     */
    public String assertJsonContains(String jsonPath, String expectResult){
        try {
            String actualResult = JSONPath.read(responseResult, jsonPath).toString();
            AutoLogger.log.info("解析的实际结果是："+actualResult);
            if(actualResult!=null){
                if(actualResult.contains(expectResult)){
                    AutoLogger.log.info("校验成功!!!");
                    results.writeCell(writeLine,STA_COL,PASS);
                    return PASS;
                }
                else{
                    results.writeFailCell(writeLine, STA_COL, FAIL);
                    AutoLogger.log.error("校验失败!!!");
                    return FAIL;
                }
            }
            else{
                results.writeFailCell(writeLine, STA_COL, FAIL);
                AutoLogger.log.error("解析出的结果是空!!!");
                return FAIL;
            }
        } catch (Exception e) {
            results.writeFailCell(writeLine, STA_COL, FAIL);
            AutoLogger.log.error("解析json并断言失败!!!");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     * 使用响应文本包含判断
     * @param expect
     */
    public String assertTextContains(String expect){
        try {
            if(responseResult.contains(expect)){
                AutoLogger.log.info("校验成功!!!");
                results.writeCell(writeLine,STA_COL,PASS);
                return PASS;
            }
            else{
                results.writeFailCell(writeLine, STA_COL, FAIL);
                AutoLogger.log.error("校验失败!!!");
                return FAIL;
            }
        } catch (Exception e) {
            results.writeFailCell(writeLine, STA_COL, FAIL);
            AutoLogger.log.error("返回为空，请检查执行逻辑顺序!!!");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     * 使用cookie
     */
    public String useCookie(){
        try{
            interDriver.useCookie();
            AutoLogger.log.info("使用cookie成功!!!");
            results.writeCell(writeLine,STA_COL,PASS);
            return PASS;
        }catch (Exception e){
            results.writeFailCell(writeLine, STA_COL, FAIL);
            AutoLogger.log.error("使用cookie失败!!!");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     * 不使用cookie
     */
    public String notUseCookie(){
        try{
            interDriver.notUseCookie();
            AutoLogger.log.info("不使用cookie成功!!!");
            results.writeCell(writeLine,STA_COL,PASS);
            return PASS;
        }catch (Exception e){
            results.writeFailCell(writeLine, STA_COL, FAIL);
            AutoLogger.log.error("不使用cookie失败!!!");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     *清除cookie中的数据
     */
    public String clearCookie(){
        try{
            interDriver.clearCookie();
            AutoLogger.log.info("清除cookie成功!!!");
            results.writeCell(writeLine,STA_COL,PASS);
            return PASS;
        }catch (Exception e){
            results.writeFailCell(writeLine, STA_COL, FAIL);
            AutoLogger.log.error("清除cookie失败!!!");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }


    /**
     * 将header数据添加到头域中
     * @param headerJson json格式的header数据
     */
    public String addHeader(String headerJson){
        try{
            headerJson = toParam(headerJson);
            JSONObject headers = JSON.parseObject(headerJson);
            for(String key:headers.keySet()){
                interDriver.addHeader(key,headers.get(key).toString());
            }
            interDriver.useHeader();
            AutoLogger.log.info("添加header成功!!!");
            results.writeCell(writeLine,STA_COL,PASS);
            return PASS;
        }catch (Exception e){
            results.writeFailCell(writeLine, STA_COL, FAIL);
            AutoLogger.log.error("添加header失败!!!");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     * 不使用头域进行存储数据
     */
    public String notUseHeader(){
        try{
            interDriver.notUseHeader();
            AutoLogger.log.info("不使用header成功!!!");
            results.writeCell(writeLine,STA_COL,PASS);
            return PASS;
        }catch (Exception e){
            results.writeFailCell(writeLine, STA_COL, FAIL);
            AutoLogger.log.error("不使用header失败!!!");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     * 清除头域中存储的header数据
     */
    public String clearHeader(){
        try{
            interDriver.clearHeader();
            AutoLogger.log.info("清除header成功!!!");
            results.writeCell(writeLine,STA_COL,PASS);
            return PASS;
        }catch (Exception e){
            results.writeFailCell(writeLine, STA_COL, FAIL);
            AutoLogger.log.error("清除header失败!!!");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     * 对SOAP的xml格式响应数据进行正则匹配，取出对应结果，并把结果存入responseResult
     * @param pattern
     */
    public String parasSOAP(String pattern){
        try{
            if(responseResult!=null&&responseResult.startsWith("<")) {
                Pattern resultp = Pattern.compile(pattern);
                Matcher resultm = resultp.matcher(responseResult);
                if (resultm.find()) {
                    responseResult=resultm.group(1);
                    AutoLogger.log.info("解析SOAP的结果是：" + responseResult);
                    results.writeCell(writeLine,STA_COL,PASS);
                    return PASS;
                }
                else{
                    results.writeFailCell(writeLine, STA_COL, FAIL);
                    AutoLogger.log.error("解析SOAP使用的正则表达式有问题，请检查！");
                    return FAIL;
                }
            }
            else {
                results.writeFailCell(writeLine, STA_COL, FAIL);
                AutoLogger.log.error("返回不是xml格式，请检查请求过程。");
                return FAIL;
            }
        }catch (Exception e){
            results.writeFailCell(writeLine, STA_COL, FAIL);
            AutoLogger.log.error("存储参数时解析json失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     * 保存参数到到paramMap中，如果参数以$开头，则对响应数据进行jsonpath提取后存储；如果不是，则直接存储参数值
     * @param key
     * @param jsonPath
     */
    public String saveParam(String key,String jsonPath){
        try {
            String paramValue = jsonPath;
            if(jsonPath.startsWith("$")) {
                paramValue = JSONPath.read(responseResult, jsonPath).toString();
            }
            paramMap.put(key,paramValue);
            AutoLogger.log.info("存储参数" + paramValue + "为" + key + "的值成功!!!");
            results.writeCell(writeLine,STA_COL,PASS);
            return PASS;
        }catch (Exception e){
            results.writeFailCell(writeLine, STA_COL, FAIL);
            AutoLogger.log.error("存储参数时解析json失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
            return FAIL;
        }
    }

    /**
     * 从paramMap中替换输入字符串中所有符合{{参数名}}格式的参数
     * @param origin
     */
    public String toParam(String origin){
        for(String key:paramMap.keySet()){
            origin = origin.replaceAll("\\{\\{" + key + "\\}\\}",paramMap.get(key));
        }
        return origin;
    }
}

