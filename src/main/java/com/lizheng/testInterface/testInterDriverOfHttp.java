package com.lizheng.testInterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lizheng.common.AutoLogger;
import com.lizheng.createDrivers.InterDriverOfHttp;
import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.activation.MimetypesFileTypeMap;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class testInterDriverOfHttp {

    public static void main(String[] args) throws IOException {
        InterDriverOfHttp idoh = new InterDriverOfHttp();

//        idoh.downloadWithName("http://8.142.92.43/pikachu/vul/unsafedownload/execdownload.php?filename=oldfish.png","C:\\Users\\86158\\Desktop\\test1.png");
//        idoh.downloadWithName("http://8.142.92.43/pikachu/vul/unsafedownload/execdownload.php?filename=oldfish.png");

//        String re = idoh.doGet("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php","query=1.1.1.1&co=&resource_id=5809&t=1628779751504&ie=utf8&oe=gbk");
//        System.out.println(re);

//        String re = idoh.doPost("http://8.142.92.43/pikachu/vul/sqli/sqli_id.php","id=1&submit=查询");
//        System.out.println(re);

//        String re = idoh.doPostByJson("https://b.zhulogic.com/designer_api/account/login_quick","{\"phone\":\"15825902288\",\"code\":\"3321\",\"messageType\":3,\"key\":\"a0b18e0d-50d3-45c9-961a-981d573a446f\",\"registration_type\":1,\"channel\":\"zhulogic\",\"unionid\":\"\"}");
//        System.out.println(re);


//        idoh.doPostFile("http://8.142.92.43/upload-labs-master/Pass-02/index.php","{\"upload_file\":\"C:\\\\Users\\\\86158\\\\Pictures\\\\Camera Roll\\\\安全座椅750_1.jpg\"}","{\"submit\":\"提交\"}");

//        idoh.doPostFile("http://8.142.92.43/pikachu/vul/unsafeupload/clientcheck.php","{\"uploadfile\":\"C:\\\\Users\\\\86158\\\\Pictures\\\\Camera Roll\\\\安全座椅750_1.jpg\"}","{\"submit\":\"提交\"}");

//        String re = idoh.doPost("http://8.142.92.43:8080/LoginSimple/Login","username=lizheng&password=123456");
//        System.out.println(re);
////        idoh.notUseCookie();
//        String re1 = idoh.doGet("http://8.142.92.43:8080/LoginSimple/UserInfo",null);
//        System.out.println(re1);

    }
}