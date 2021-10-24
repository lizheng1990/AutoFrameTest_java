package com.lizheng.testInterface;

import com.lizheng.common.AutoLogger;
import com.lizheng.keyWord.InterKeyWord;

public class testSOAP{
        public static void main(String[] args) {
            InterKeyWord ikw = new InterKeyWord();
//            http://testingedu.com.cn:8081/inter/SOAP?wsdl
            ikw.doPost("http://8.142.92.43:8080/Inter/SOAP?wsdl","<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:auth></soap:auth></soapenv:Body></soapenv:Envelope>","text");
            ikw.parasSOAP("<return>(.*?)</return>");
            System.out.println(ikw.responseResult);
//
//            ikw.saveParam("token","$.token");
//            ikw.addHeader("{\"token\":\"{{token}}\"}");
//            ikw.useCookie();
//            ikw.saveParam("registername","test" + ikw.getRandomFileName().substring(9,13));
//
//            ikw.doPost("http://8.142.92.43:8080/Inter/SOAP?wsdl","<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:register><arg0>{{registername}}</arg0><arg1>123456</arg1><arg2>{{registername}}</arg2><arg3>测试号</arg3></soap:register></soapenv:Body></soapenv:Envelope>","text");
//            ikw.parasSOAP("<return>(.*?)</return>");
//            System.out.println(ikw.responseResult);
//
//            ikw.doPost("http://8.142.92.43:8080/Inter/SOAP?wsdl","<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:login><arg0>{{registername}}</arg0><arg1>123456</arg1></soap:login></soapenv:Body></soapenv:Envelope>","text");
//            ikw.parasSOAP("<return>(.*?)</return>");
//            System.out.println(ikw.responseResult);
//            ikw.saveParam("userid","$.userid");
//
//            ikw.doPost("http://8.142.92.43:8080/Inter/SOAP?wsdl","<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:getUserInfo><arg0>{{userid}}</arg0></soap:getUserInfo></soapenv:Body></soapenv:Envelope>","text");
//            ikw.parasSOAP("<return>(.*?)</return>");
//            System.out.println(ikw.responseResult);
//
//            ikw.doPost("http://testingedu.com.cn:8081/inter/SOAP?wsdl","<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:logout></soap:logout></soapenv:Body></soapenv:Envelope>","text");
//            ikw.parasSOAP("<return>(.*?)</return>");
//            System.out.println(ikw.responseResult);
        }
}