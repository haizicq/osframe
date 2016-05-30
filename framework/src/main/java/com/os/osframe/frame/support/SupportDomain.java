package com.os.osframe.frame.support;

/**
 * 支撑模型
 *  用于标注通用的功能而无需业务模块单独实现
 * Created by wangchun on 16/5/16.
 */
public class SupportDomain {
    /**
     * 类型
     *  如：附件、权限这些类型，具体由解析处定义
     */
    protected String type;

    /**
     * 关键字
     *  用于同一中类型可以出现多次
     */
    protected String keyword;
    /**
     * 数据
     *  用于保存数据，格式为json类型，有具体type去定义
     */
    protected String data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
