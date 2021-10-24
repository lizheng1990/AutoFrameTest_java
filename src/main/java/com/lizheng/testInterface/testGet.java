package com.lizheng.testInterface;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class testGet {
    public static void main(String[] args){
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=8.142.92.43&co=&resource_id=5809&t=1627828675683&ie=utf8&oe=gbk&cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery1102007905650838739997_1627828644129&_=1627828644134");
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            System.out.println("响应数据长度： " + entity.getContentLength());
            System.out.println("响应数据内容： " + EntityUtils.toString(entity,"UTF-8"));
            response.close();
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
