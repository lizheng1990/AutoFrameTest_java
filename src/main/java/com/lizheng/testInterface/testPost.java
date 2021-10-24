package com.lizheng.testInterface;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class testPost {
    public static void main(String[] args) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost("http://8.142.92.43/index.php?m=Home&c=User&a=do_login&t=0.8791672612082269");

            // 创建参数队列
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//            formparams.add(new BasicNameValuePair("type", "house"));
            formparams.add(new BasicNameValuePair("username", "13900000000"));
            formparams.add(new BasicNameValuePair("password", "123456"));
            formparams.add(new BasicNameValuePair("verify_code", "1"));
            UrlEncodedFormEntity uefEntity;
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            uefEntity.setContentEncoding("UTF-8");
            uefEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            httppost.setEntity(uefEntity);
            System.out.println("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);

            HttpEntity entity = response.getEntity();
            System.out.println("响应数据内容：" + EntityUtils.toString(entity, "UTF-8"));
            response.close();
            httpclient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
