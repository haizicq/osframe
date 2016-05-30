package com.os.osframe.frame.support;

/**
 * 注册对象
 * Created by wangchun on 16/5/16.
 */
public class RegisterObj {
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
}
