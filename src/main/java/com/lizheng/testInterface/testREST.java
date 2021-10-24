package com.lizheng.testInterface;

import com.lizheng.common.AutoLogger;
import com.lizheng.keyWord.InterKeyWord;

public class testREST {
        public static void main(String[] args) {
            InterKeyWord ikw = new InterKeyWord();
            ikw.doPostWithoutParam("http://8.142.92.43:8080/Inter/REST/auth");
            AutoLogger.log.info(ikw.responseResult);

            ikw.saveParam("token","$.token");
            ikw.addHeader("{\"token\":\"{{token}}\"}");
            ikw.useCookie();
            ikw.saveParam("registername","test" + ikw.getRandomFileName().substring(9,13));
//            ikw.doPostByUrl("http://8.142.92.43:8080/Inter/REST/user/register/","{\"name\":\"{{registername}}\",\"pwd\":\"123456\",\"nickname\":\"{{registername}}\",\"describe\":\"测试号码\"}");
            ikw.doPostWithoutParam("http://8.142.92.43:8080/Inter/REST/user/{\"name\":\"{{registername}}\",\"pwd\":\"123456\",\"nickname\":\"{{registername}}\",\"describe\":\"测试号码\"}");
            AutoLogger.log.info(ikw.responseResult);

            ikw.doPostWithoutParam("http://8.142.92.43:8080/Inter/REST/login/{{registername}}/123456");
            AutoLogger.log.info(ikw.responseResult);

            ikw.saveParam("userid","$.userid");
//        ikw.addHeader("{\"userid\":\"{{userid}}\"}");
            ikw.doPostWithoutParam("http://8.142.92.43:8080/Inter/REST/login/{{userid}}");
            AutoLogger.log.info(ikw.responseResult);

            ikw.doPostWithoutParam("http://8.142.92.43:8080/Inter/REST/login");
            AutoLogger.log.info(ikw.responseResult);
        }
}