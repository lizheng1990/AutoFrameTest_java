package com.lizheng.testInterface;

import com.lizheng.common.AutoLogger;
import com.lizheng.keyWord.InterKeyWord;

public class testHTTP {
    public static void main(String[] args) {
        InterKeyWord ikw = new InterKeyWord();
//        ikw.doPost("http://8.142.92.43:8080/Inter/HTTP//auth",null,"text");
//        ikw.doPostWithoutParam("http://8.142.92.43:8080/Inter/HTTP//auth");
        ikw.doPostByUrl("http://8.142.92.43:8080/Inter/HTTP//auth","");
        AutoLogger.log.info(ikw.responseResult);
        ikw.saveParam("token","$.token");
        ikw.addHeader("{\"token\":\"{{token}}\"}");
        ikw.useCookie();
        ikw.saveParam("registername","test" + ikw.getRandomFileName().substring(9,13));
        ikw.doPostByUrl("http://8.142.92.43:8080/Inter/HTTP//register","username={{registername}}&pwd=123456&nickname=test32312&describe=测试号码");
        AutoLogger.log.info(ikw.responseResult);
        ikw.doPostByUrl("http://8.142.92.43:8080/Inter/HTTP//login","username={{registername}}&password=123456");
        AutoLogger.log.info(ikw.responseResult);
        ikw.saveParam("userid","$.userid");
//        ikw.addHeader("{\"userid\":\"{{userid}}\"}");
        ikw.doPostByUrl("http://8.142.92.43:8080/Inter/HTTP//getUserInfo","id={{userid}}");
        AutoLogger.log.info(ikw.responseResult);
        ikw.doPostByUrl("http://8.142.92.43:8080/Inter/HTTP//logout",null);
        AutoLogger.log.info(ikw.responseResult);
    }
}
