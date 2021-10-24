package com.lizheng.keyWord;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lizheng.common.AutoLogger;
import com.lizheng.createDrivers.InterDriverOfHttp;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterKeyWord {
    //存储接口返回信息
    public String responseResult;
    //声明一个InterDriverOfHttp对象
    public InterDriverOfHttp interDriver;
    //用于存储获取到的参数
    public Map<String,String> paramMap;

    public InterKeyWord(){
        interDriver = new InterDriverOfHttp();
        paramMap = new HashMap<String,String>();
    }

    /**
     * 封装的get方法
     * @param url
     * @param param
     */
    public void doGet(String url,String param){
        try{
            url =toParam(url);
            param = toParam(param);
            responseResult = interDriver.doGet(url,param);
            AutoLogger.log.info("执行get请求成功！！！");
        }catch (Exception e){
            AutoLogger.log.error("执行get请求失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }

    /**
     * 封装实现指定类型的post请求
     * @param url
     * @param param key=value&key=value/key=value&key=value/{key:value, key:value}
     * @param type text/url/json
     */
    public void doPost(String url,String param,String type){
        try{
            url =toParam(url);
            if(param == null||param.length()<1){
            }else {
                param = toParam(param);
            }
            responseResult = interDriver.doPost(url,param,type);
            AutoLogger.log.info("执行指定类型的post请求成功！！！");
        }catch (Exception e){
            AutoLogger.log.error("执行执行类型的post请求失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }

    /**
     * 封装无请求体的post请求
     * @param url
     */
    public void doPostWithoutParam(String url){
        try{
            url =toParam(url);
            responseResult = interDriver.doPostWithoutParam(url);
            AutoLogger.log.info("执行无请求体的post请求成功！！！");
        }catch (Exception e){
            AutoLogger.log.error("执行无请求体的post请求失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }

    /**
     * 封装x-www-form-urlencoded格式的post请求
     * @param url
     * @param param 参数格式为 key=value&key=value
     */
    public void doPostByUrl(String url,String param){
        try{
            url =toParam(url);
            if(param == null||param.length()<1){
            }else {
                param = toParam(param);
            }
//            System.out.println(param);
            responseResult = interDriver.doPostByUrl(url,param);
            AutoLogger.log.info("执行x-www-form-urlencoded类型的post请求成功！！！");
        }catch (Exception e){
            AutoLogger.log.error("执行x-www-form-urlencoded类型的post请求失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }

    /**
     * 封装json格式传参的post方法
     * @param url
     * @param paramJson 参数格式为 {key:value, key:value}
     */
    public void doPostByJson(String url,String paramJson){
        try{
            url =toParam(url);
            if(paramJson == null||paramJson.length()<1){
            }else {
                paramJson = toParam(paramJson);
            }
            responseResult = interDriver.doPostByJson(url,paramJson);
            AutoLogger.log.info("执行json格式post请求成功！！！");
        }catch (Exception e){
            AutoLogger.log.error("执行json格式post请求失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }

    /**
     *通过post上传文件
     * @param url
     * @param paramOfBinaryBody 文件部分参数，格式为{"upload_file":"C:\\Users\\86158\\Pictures\\Camera Roll\\安全座椅750_1.jpg"}
     * @param paramOfTextBody 本部分参数，格式为{"submit":"提交"}
     */
    public void upload(String url,String paramOfBinaryBody,String paramOfTextBody){
        try{
            url =toParam(url);
            paramOfBinaryBody = toParam(paramOfBinaryBody);
            if(paramOfTextBody == null||paramOfTextBody.length()<1){
            }else {
                paramOfTextBody = toParam(paramOfTextBody);
            }
            responseResult = interDriver.doPostFile(url,paramOfBinaryBody,paramOfTextBody);
            AutoLogger.log.info("上传文件成功！！！");
        }catch (Exception e){
            AutoLogger.log.error("上传文件失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }

    /**
     * 根据url下载文件，并使用指定的路径和名称作为存储文件的路径和名称
     * @param url
     * @param filepath 绝对路径，如C:\Users\86158\Desktop\test1.png
     */
    public void downloadWithName(String url, String filepath){
        try{
            url =toParam(url);
            filepath = toParam(filepath);
            responseResult = interDriver.downloadWithName(url, filepath);
            AutoLogger.log.info("下载文件到指定目录成功！！！");
        }catch (Exception e){
            AutoLogger.log.error("下载文件到指定目录失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }

    /**
     * 根据url下载文件，文件名从response header头中获取,路径默认为D盘根目录
     * @param url
     */
    public void download(String url){
        try{
            url =toParam(url);
            responseResult = interDriver.download(url);
            AutoLogger.log.info("下载文件成功！！！");
        }catch (Exception e){
            AutoLogger.log.error("下载文件失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
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
    public void assertJsonEqual(String jsonPath, String expectResult){
        try {
            String actualResult = JSONPath.read(responseResult, jsonPath).toString();
            AutoLogger.log.info("解析的实际结果是："+actualResult);
            if(actualResult!=null){
                if(actualResult.equals(expectResult)){
                    AutoLogger.log.info("校验成功!!!");
                }
                else{
                    AutoLogger.log.error("校验失败!!!");
                }
            }
            else{
                AutoLogger.log.error("解析出的结果是空!!!");
            }
        } catch (Exception e) {
            AutoLogger.log.error("解析json并断言失败!!!");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }

    /**
     * 断言解析出的json结果中包含预期内容
     * @param jsonPath jsonpath表达式
     * @param expectResult  预期内容
     */
    public void assertJsonContains(String jsonPath, String expectResult){
        try {
            String actualResult = JSONPath.read(responseResult, jsonPath).toString();
            AutoLogger.log.info("解析的实际结果是："+actualResult);
            if(actualResult!=null){
                if(actualResult.contains(expectResult)){
                    AutoLogger.log.info("校验成功!!!");
                }
                else{
                    AutoLogger.log.error("校验失败!!!");
                }
            }
            else{
                AutoLogger.log.error("解析出的结果是空!!!");
            }
        } catch (Exception e) {
            AutoLogger.log.error("解析json并断言失败!!!");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }

    /**
     * 使用响应文本包含判断
     * @param expect
     */
    public void assertTextContains(String expect){
        try {
            if(responseResult.contains(expect)){
                AutoLogger.log.info("校验成功!!!");
            }
            else{
                AutoLogger.log.error("校验失败!!!");
            }
        } catch (Exception e) {
            AutoLogger.log.error("返回为空，请检查执行逻辑顺序!!!");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }

    /**
     * 使用cookie
     */
    public void useCookie(){
        try{
            interDriver.useCookie();
            AutoLogger.log.info("使用cookie成功!!!");
        }catch (Exception e){
            AutoLogger.log.error("使用cookie失败!!!");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }

    /**
     * 不使用cookie
     */
    public void notUseCookie(){
        try{
            interDriver.notUseCookie();
            AutoLogger.log.info("不使用cookie成功!!!");
        }catch (Exception e){
            AutoLogger.log.error("不使用cookie失败!!!");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }

    /**
     *清除cookie中的数据
     */
    public void clearCookie(){
        try{
            interDriver.clearCookie();
            AutoLogger.log.info("清除cookie成功!!!");
        }catch (Exception e){
            AutoLogger.log.error("清除cookie失败!!!");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }


    /**
     * 将header数据添加到头域中
     * @param headerJson json格式的header数据
     */
    public void addHeader(String headerJson){
        try{
            headerJson = toParam(headerJson);
            JSONObject headers = JSON.parseObject(headerJson);
            for(String key:headers.keySet()){
                interDriver.addHeader(key,headers.get(key).toString());
            }
            interDriver.useHeader();
            AutoLogger.log.info("添加header成功!!!");
        }catch (Exception e){
            AutoLogger.log.error("添加header失败!!!");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }

    /**
     * 不使用头域进行存储数据
     */
    public void  notUseHeader(){
        try{
            interDriver.notUseHeader();
            AutoLogger.log.info("不使用header成功!!!");
        }catch (Exception e){
            AutoLogger.log.error("不使用header失败!!!");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }

    /**
     * 清除头域中存储的header数据
     */
    public void clearHeader(){
        try{
            interDriver.clearHeader();
            AutoLogger.log.info("清除header成功!!!");
        }catch (Exception e){
            AutoLogger.log.error("清除header失败!!!");
            AutoLogger.log.error(e.fillInStackTrace());
        }
    }

    /**
     * 对SOAP的xml格式响应数据进行正则匹配，取出对应结果，并把结果存入responseResult
     * @param pattern
     */
    public void parasSOAP(String pattern){
        if(responseResult!=null&&responseResult.startsWith("<")) {
            Pattern resultp = Pattern.compile(pattern);
            Matcher resultm = resultp.matcher(responseResult);
            if (resultm.find()) {
                responseResult=resultm.group(1);
                AutoLogger.log.info("解析SOAP的结果是：" + responseResult);
            }
            else{
                AutoLogger.log.error("解析SOAP使用的正则表达式有问题，请检查！");
            }
        }
        else {
            AutoLogger.log.error("返回不是xml格式，请检查请求过程。");
        }

    }

    /**
     * 保存参数到到paramMap中，如果参数以$开头，则对响应数据进行jsonpath提取后存储；如果不是，则直接存储参数值
     * @param key
     * @param jsonPath
     */
    public void saveParam(String key,String jsonPath){
        try {
            String paramValue = jsonPath;
            if(jsonPath.startsWith("$")) {
                paramValue = JSONPath.read(responseResult, jsonPath).toString();
            }
            paramMap.put(key,paramValue);
            AutoLogger.log.info("存储参数" + paramValue + "为" + key + "的值成功!!!");
        }catch (Exception e){
            AutoLogger.log.error("存储参数时解析json失败！！！");
            AutoLogger.log.error(e.fillInStackTrace());
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
