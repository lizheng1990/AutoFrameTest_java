package com.lizheng.test;

import org.testng.annotations.*;

import static org.testng.Assert.*;

public class sendMailTestNG {

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("这是SendMailTestNG类的方法：BeforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("这是SendMailTestNG类的方法：AfterMethod");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("这是SendMailTestNG类的方法：BeforeClass");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("这是SendMailTestNG类的方法：AfterClass");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("这是SendMailTestNG类的方法：BeforeTest");
    }

    @AfterTest
    public void AfterTest(){
        System.out.println("这是SendMailTestNG类的方法：AfterTest");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("这是SendMailTestNG类的方法：BeforeSuite");

    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("这是SendMailTestNG类的方法：AfterSuite");

    }

    @Test
    public void test1(){
        System.out.println("这是SendMailTestNG类的方法：Test1");
    }

    @Test
    public void test2(){
        System.out.println("这是SendMailTestNG类的方法：Test2");
    }
}