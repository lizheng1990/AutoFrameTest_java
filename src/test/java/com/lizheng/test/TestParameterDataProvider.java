package com.lizheng.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestParameterDataProvider {
    @Test(dataProvider = "provideNumbers")
    public void test(int number,int expected){
        Assert.assertEquals(number+10,expected);
    }

    @DataProvider(name = "provideNumbers")
    public Object[][] provideData(){
        return new Object[][] {{10,20},{100,120},{200,210}};
    }
}
