package com.lizheng.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestParameterDataProvider2 {
    @Test(dataProvider = "dbconfig")
    public void testConnection(Map<String,String> map){
        for (Map.Entry<String,String> entry:map.entrySet()){
            System.out.println("[key]: " + entry.getKey() + "[value]: " + entry.getValue());
        }
    }

    @DataProvider(name = "dbconfig")
    public Object[][] proveideDbconfid(){
        Map<String,String> map = readDbconfig();
        return new Object[][] {{map}};
    }

    public Map<String,String> readDbconfig(){
        Properties prop = new Properties();
        InputStream input = null;
        Map<String,String> map = new HashMap<String,String>();
        try {
//            input = getClass().getClassLoader().getResourceAsStream("src\\main\\resources\\db.properties");
//            prop.load(input);
            String path = System.getProperty("user.dir")+"src\\main\\resources\\db.properties";

            System.out.println("path => "+path);
            //input = getClass().getClassLoader().getResourceAsStream(path);

            //prop.load(input);
            prop.load(new FileInputStream("src\\main\\resources\\db.properties"));
            map.put("jdbc.driver",prop.getProperty("jdbc.driver"));
            map.put("jdbc.url",prop.getProperty("jdbc.url"));
            map.put("jdbc.username",prop.getProperty("jdbc.username"));
            map.put("jdbc.password",prop.getProperty("jdbc.password"));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(input != null){
                try{
                    input.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
}
