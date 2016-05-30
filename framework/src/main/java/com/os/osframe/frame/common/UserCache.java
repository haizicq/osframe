package com.os.osframe.frame.common;

import com.os.osframe.util.CacheUtil;
import com.os.osframe.util.StringUtil;

import java.util.Date;

/**
 * Created by wangchun on 16/5/9.
 */
public class UserCache {

    private static CacheUtil cache=new CacheUtil();//创建缓存对象 第一级：key-用户名，value-用户缓存对象，第二级：用户缓存
    private static String timeOutKey="TIME_OUT_KEY";
    /**
     * 获取用户缓存对象
     * @param userName 用户名
     * @return
     */
    public CacheUtil getUserCache(String userName){
        if(StringUtil.isNull(userName)){
            return null;
        }
        CacheUtil userCache=(CacheUtil)cache.getValue(userName);
        return userCache;
    }

    /**
     * 获取用户缓存值
     * @param userName 用户名
     * @param key 关键字
     * @return
     */
    public Object getUserValue(String userName,String key){
        if(StringUtil.isNull(userName) || StringUtil.isNull(key)){//当用户名为空时，不做任何处理
            return null;
        }
        CacheUtil userCache=(CacheUtil)getUserCache(userName);
        if(userCache==null){
            return null;
        }
        //TODO 判断时间超时
        return userCache.getValue(key);
    }
    /**
     * 向用户缓存对象中存入数据
     * @param userName 用户名
     * @param key 关键字
     * @param value 缓存值
     */
    public void setUserCache(String userName,String key,Object value){
        if(StringUtil.isNull(userName)){//当用户名为空时，不做任何处理
            return ;
        }
        CacheUtil userCache=(CacheUtil)getUserCache(userName);
        if(userCache==null){//当用户对象为空时，新创建缓存
            userCache=new CacheUtil();
            cache.put(userName,userCache);
            //设置有效期
            userCache.put(timeOutKey,new Date());
        }
        userCache.put(key,value);
    }

    /**
     * 清理用户缓存
     * @param userName 用户名
     */
    public void clearUserCache(String userName){
        CacheUtil userCache=(CacheUtil)getUserCache(userName);
        if(userCache!=null){
            userCache=new CacheUtil();
            cache.put(userName,userCache);
        }
    }

    /**
     * 清理所有用户的用户缓存信息
     */
    public void clearAllCache(){
        cache=new CacheUtil();
    }

}
