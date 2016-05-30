package com.os.osframe.frame.support;

import org.springframework.beans.factory.InitializingBean;

/**
 * 支撑类初始化工厂
 * Created by wangchun on 16/5/16.
 */
public class RegisterFactory implements InitializingBean {
    protected String type;
    protected String name;
    protected String bean;
    protected Integer order;
    protected String info;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void afterPropertiesSet() throws Exception {//初始化时直接注册
        RegisterCenter.getInstance().register(this.type,this.name,this.bean,this.order,this.info);
    }
}
