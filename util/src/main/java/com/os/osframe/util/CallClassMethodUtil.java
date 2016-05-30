package com.os.osframe.util;

import java.lang.reflect.Method;


/**
 * 获取调度方法的类名和方法名并调用
 * Created by wangdc on 2015-5-2.
 */
public class CallClassMethodUtil {
    /**
     * 直接获取类中无参数的方法
     *
     * @param className
     * @param methodName
     * @return
     */
    public static Object call(String className,String methodName) {
        Object value =null;
        try {
            Class clazz=Class.forName(className);
            Method m = clazz.getDeclaredMethod(methodName, String.class);
            value =m.invoke(clazz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
    /**
     * 动态调用指定类中的方法
     * @param methodName
     * @param ptypes
     * @param obj
     * @param className
     */
    public static Object call(String className,String methodName,Class<? extends Object>[] ptypes, Object[] obj) {
        Object value =null;
        try {
            Class clazz=Class.forName(className);
            Method m = clazz.getDeclaredMethod(methodName, ptypes);
            value =m.invoke(clazz.newInstance(), obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
    /**
     * 动态调用指定类中的方法
     * @param className
     * @param methodName
     * @param obj
     */
    public static Object call(String className,String methodName, String obj) {
        Object value =null;
        try {
            Class clazz=Class.forName(className);
            Method m = clazz.getDeclaredMethod(methodName, String.class);
            value =m.invoke(clazz.newInstance(), obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
    /**
     * 动态调用方法
     * @param methodName
     * @param ptype
     * @param obj
     */
    public static Object call(String className,String methodName,Class<? extends Object> ptype, Object obj) {
        Object value =null;
        try {
            Class clazz=Class.forName(className);
            Class<? extends Object>[]  ptypes=new Class[1];
            ptypes[0]=ptype;
            Method m = clazz.getDeclaredMethod(methodName, ptypes);
            value =m.invoke(clazz.newInstance(), obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
