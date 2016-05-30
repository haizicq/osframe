package com.os.osframe.frame.support;

import com.os.osframe.frame.log.LogInfo;
import com.os.osframe.frame.util.SpringUtils;
import com.os.osframe.util.CacheUtil;
import com.os.osframe.util.StringUtil;

/**
 * 支撑类注册中心
 * Created by wangchun on 16/5/16.
 */
public class RegisterCenter {
    protected LogInfo logInfo=new LogInfo(RegisterCenter.class);
    protected static RegisterCenter instance=new RegisterCenter();
    public static RegisterCenter getInstance(){
        return instance;
    }
    protected CacheUtil cacheUtil=new CacheUtil();

    /**
     * 注册支撑类型
     * @param type 支撑类型
     * @param name 支撑名称
     * @param bean 支撑解析的spring bean
     * @param order 排序号
     * @param info 备注
     */
    public void register(String type,String name,String bean,Integer order,String info){
        RegisterObj registerObj=(RegisterObj)cacheUtil.getValue(type);
        if(registerObj==null){//当不存在对象时才能注册
            registerObj=new RegisterObj();
            registerObj.setBean(bean);
            registerObj.setType(type);
            registerObj.setName(name);
            registerObj.setOrder(order);
            registerObj.setInfo(info);
            cacheUtil.put(type,registerObj);
        }else{
            logInfo.debug("对象类型"+type+"重复注册,被自动忽略！");
        }
    }

    /**
     * 获取支撑类型
     * @param type 支撑类型
     */
    public RegisterObj get(String type){
        RegisterObj registerObj=(RegisterObj)cacheUtil.getValue(type);
        return registerObj;
    }

    /**
     * 获取支撑解析bean
     * @param type 支撑类型
     */
    public ISupportService getBean(String type){
        if(StringUtil.isNull(type)){
            return null;
        }
        RegisterObj registerObj=(RegisterObj)cacheUtil.getValue(type);
        if(registerObj!=null && StringUtil.isNotNull(registerObj.getBean())){
            return (ISupportService)SpringUtils.getBean(registerObj.getBean());
        }
        return null;
    }
}
