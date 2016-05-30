package com.os.osframe.core.login.domain;

/**
 * 用户模型
 * Created by wangchun on 16/4/17.
 */
public class UserModel {
    protected String userId;
    protected String userName;
    protected String pwd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
