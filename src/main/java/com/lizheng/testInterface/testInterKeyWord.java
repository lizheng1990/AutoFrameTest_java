package com.lizheng.testInterface;

import com.alibaba.fastjson.JSONPath;
import com.lizheng.common.AutoLogger;
import com.lizheng.keyWord.InterKeyWord;

public class testInterKeyWord {
    public static void main(String[] args) {
        InterKeyWord ikw = new InterKeyWord();
//        ikw.doGet("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php","query=1.1.1.1&co=&resource_id=5809&t=1628779751504&ie=utf8&oe=gbk");
//        System.out.println(ikw.responseResult);
//        ikw.assertJsonEqual("$.Result.DisplayData.resultData.tplData.location[0]","泛播 Cloudflare");
//        ikw.assertJsonContains("$.Result.DisplayData.resultData.tplData.location[0]","泛播");
//        ikw.assertTextContains("泛播");
//
//        ikw.doPost("http://8.142.92.43:8080/LoginSimple/Login","username=lizheng&password=123456");
//        System.out.println(ikw.responseResult);
////        ikw.notUseCookie();
//        ikw.doGet("http://8.142.92.43:8080/LoginSimple/UserInfo",null);
//        System.out.println(ikw.responseResult);

//        ikw.doPost("http://8.142.92.43:8080/Inter/HTTP//auth",null);
//        System.out.println(ikw.responseResult);
//        String token = JSONPath.read(ikw.responseResult,"$.token").toString();
//        System.out.println("token=" + token);
//        ikw.useHeader();
//        ikw.addHeader("token",token);
//        ikw.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
//        ikw.useCookie();
//        ikw.doPost("http://8.142.92.43:8080/Inter/HTTP//login","username=Will&password=123456");
//        System.out.println(ikw.responseResult);
//        String uid = JSONPath.read(ikw.responseResult,"$.userid").toString();
//        System.out.println("userid=" + uid);
//        ikw.doPost("http://8.142.92.43:8080/Inter/HTTP//getUserInfo","id="+uid);
//        System.out.println(ikw.responseResult);
//        ikw.doPost("http://8.142.92.43:8080/Inter/HTTP//logout",null);

//        ikw.doPost("http://8.142.92.43:8080/Inter/HTTP//auth",null);
//        System.out.println(ikw.responseResult);
//        ikw.saveParam("token","$.token");
//        ikw.addHeader("{\"token\":\"{{token}}\"}");
//        ikw.useCookie();
//        ikw.doPost("http://8.142.92.43:8080/Inter/HTTP//login","username=lizheng&password=123456");
//        System.out.println(ikw.responseResult);
//        ikw.saveParam("userid","$.userid");
////        ikw.addHeader("{\"userid\":\"{{userid}}\"}");
//        ikw.doPost("http://8.142.92.43:8080/Inter/HTTP//getUserInfo","id={{userid}}");
//        System.out.println(ikw.responseResult);
//        ikw.doPost("http://8.142.92.43:8080/Inter/HTTP//logout",null);
//        System.out.println(ikw.responseResult);

    }
}
