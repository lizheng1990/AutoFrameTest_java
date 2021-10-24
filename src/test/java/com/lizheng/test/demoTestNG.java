package com.lizheng.test;

import org.testng.annotations.*;

public class demoTestNG {
    @BeforeTest
    public void beforeTest(){
        System.out.println("这是类demoTestNG的方法：BeforeTest");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("这是类demoTestNG的方法：AfterTest");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("这是类demoTestNG的方法：BeforeClass");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("这是类demoTestNG的方法：AfterClass");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("这是类demoTestNG的方法：BeforeSuite");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("这是类demoTestNG的方法：AfterSuite");
    }

    @Test
    public void test1(){
        System.out.println("这是类demoTestNG的方法：test1");
    }

    @Test
    public void test2(){
        System.out.println("这是类demoTestNG的方法：test2");
    }
}
