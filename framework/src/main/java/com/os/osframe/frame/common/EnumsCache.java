package com.os.osframe.frame.common;

import com.os.osframe.frame.util.SpringUtils;
import com.os.osframe.util.CacheUtil;
import com.os.osframe.util.StringUtil;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by wangchun on 16/4/3.
 */
public class EnumsCache {
    private static EnumsCache enumsCache = new EnumsCache();
    private static CacheUtil cache=new CacheUtil();//创建缓存对象
    protected static IEnumsConfig propertyConfigurer;

    /**
     * 获取配置的对象
     * @return
     */
    protected IEnumsConfig getEnumsConfigurer() {
        if(propertyConfigurer==null){
            propertyConfigurer=(IEnumsConfig) SpringUtils.getBean("enumsConfigurer");
        }
        return propertyConfigurer;
    }
    /**
     * 使用单例模式
     * @return
     */
    public static EnumsCache getInstance() {
        return enumsCache;
    }


    /**
     * 获取枚举对象列表
     * @param key
     */
    public Object get(String key){
       return getEnumsConfigurer().getEnums(key);
    }

    /**
     * 获取枚举对象列表
     * @param key
     * @param value
     */
    public Object get(String key,String value){
        if(StringUtil.isNull(value)){
            return null;
        }
        List enumsList=getEnumsConfigurer().getEnums(key);
        if(enumsList!=null && !enumsList.isEmpty()){
            for(int i=0;i<enumsList.size();i++){
                Map map=(Map)enumsList.get(i);
                if(value.trim().equals(map.get("value"))){
                    return map.get("key");
                }
            }
        }
        return null;
    }

}
