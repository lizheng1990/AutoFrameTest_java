package com.lizheng.keyWord;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 封装反射方法执行excel
 */
public class InvokeKeyWord {
    /**
     * 反射方法，适用于inter用例中的D列
     * @param api 关键字类
     * @param rowContent excel中的行数据
     */
    public static void invokeInter(Object api, List<String> rowContent){
        //查找关键字，且该关键字没有参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3));
            target.invoke(api);
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有一个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class);
            target.invoke(api,rowContent.get(4));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有两个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有三个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5),rowContent.get(6));
            return;
        } catch (Exception e) {
        }
    }

    /**
     * 反射方法，适用于inter用例中的H列
     * @param api 关键字类
     * @param rowContent excel中的行数据
     */
    public static void invokeAssert(Object api,List<String> rowContent) {
        //查找关键字，且该关键字没有参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(7));
            target.invoke(api);
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有一个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(7), String.class);
            target.invoke(api, rowContent.get(8));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有两个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(7), String.class, String.class);
            target.invoke(api, rowContent.get(8), rowContent.get(9));
            return;
        } catch (Exception e) {
        }
    }

    /**
     * 反射方法，适用于APP用例中的D列
     * @param api 关键字类
     * @param rowContent excel中的行数据
     */
    public static void invokeApp(Object api,List<String> rowContent){
        //查找关键字，且该关键字没有参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3));
            target.invoke(api);
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有一个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class);
            target.invoke(api,rowContent.get(4));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有两个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有三个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5),rowContent.get(6));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有四个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class,String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5),rowContent.get(6),rowContent.get(7));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有五个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class,String.class,String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5),rowContent.get(6),rowContent.get(7),rowContent.get(8));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有六个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class,String.class,String.class,String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5),rowContent.get(6),rowContent.get(7),rowContent.get(8),rowContent.get(9));
            return;
        } catch (Exception e) {
        }
    }

    /**
     * 反射方法，适用于web用例中的D列
     * @param api 关键字类
     * @param rowContent excel中的行数据
     */
    public static void invokeWeb(Object api,List<String> rowContent){
        //查找关键字，且该关键字没有参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3));
            target.invoke(api);
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有一个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class);
            target.invoke(api,rowContent.get(4));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有两个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有三个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5),rowContent.get(6));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有四个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class,String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5),rowContent.get(6),rowContent.get(7));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有五个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class,String.class,String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5),rowContent.get(6),rowContent.get(7),rowContent.get(8));
            return;
        } catch (Exception e) {
        }
        //查找关键字，且该关键字只有六个参数
        try {
            Method target = api.getClass().getDeclaredMethod(rowContent.get(3),String.class,String.class,String.class,String.class,String.class,String.class);
            target.invoke(api,rowContent.get(4),rowContent.get(5),rowContent.get(6),rowContent.get(7),rowContent.get(8),rowContent.get(9));
            return;
        } catch (Exception e) {
        }
    }
}
