package com.os.osframe.util;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * Created by wangchun on 16/3/3.
 */
public class EntityUtil {

    /**
     * 得到实体类
     * @param objClass 实体类包含包名
     * @return
     */
    public static  Class getEntityClass(String objClass){
        Class entityClass = null;
        try {
            entityClass = Class.forName(objClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entityClass;
    }
    /**
     * 根据实体得到实体的所有属性
     * @param objClass
     * @return
     * @throws ClassNotFoundException
     */
    public static String[] getColumnNames(String objClass) throws ClassNotFoundException {
        String[] wageStrArray = null;
        if (objClass != null) {
            Class class1 = Class.forName(objClass);
            Field[] field = class1.getDeclaredFields();// 这里便是获得实体Bean中所有属性的方法
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < field.length; i++) {// 这里不多说了

                sb.append(field[i].getName());

                // 这是分割符 是为了去掉最后那个逗号

                // 比如 如果不去最后那个逗号 最后打印出来的结果是 "id,name,"

                // 去了以后打印出来的是 "id,name"
                if (i < field.length - 1) {
                    sb.append(",");

                }
            }

            // split(",");这是根据逗号来切割字符串使字符串变成一个数组

            wageStrArray = sb.toString().split(",");
            return wageStrArray;
        } else {
            return wageStrArray;
        }

    }
    /**
     *获取实体中的指定字段值
     * @param obj 对象
     * @param field 字段
     * @return
     */
    public static Object getEntityFieldValue(Object obj,String field){
        Object val=null;
        if(obj==null || StringUtil.isNull(field)){
            return val;
        }
        try{
            val=PropertyUtils.getProperty(obj, field);
        }catch (Exception e){
            e.printStackTrace();
        }
        return val;
    }

    /**
     * 将属性列表转为逗号分隔
     * @param objList
     * @param field
     * @return
     */
    public static String getEntityFieldList(Object objList,String field){
        String val="";
        if(objList==null || StringUtil.isNull(field)){
            return val;
        }
        try{
            if(objList instanceof List){
                for(int i=0;i<((List) objList).size();i++){
                    Object obj=((List) objList).get(i);
                    if(StringUtil.isNotNull(val)){
                        val+=",";
                    }
                    Object entityFieldValue=getEntityFieldValue(obj,field);
                    if(entityFieldValue==null){
                        continue;
                    }
                    //查询结果数据类型可能为：int str date
                    String entityValue=null;
                    if(entityFieldValue instanceof Date){
                        entityValue=DateUtil.formatDateTime((Date)entityFieldValue);
                    }else{
                        entityValue=String.valueOf(entityFieldValue);
                    }
                    if(StringUtil.isNull(entityValue)){
                        continue;
                    }
                    if (StringUtil.isNull(val)){
                        val=entityValue;
                    }else{
                        val+=entityValue;
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return val;
    }
}
