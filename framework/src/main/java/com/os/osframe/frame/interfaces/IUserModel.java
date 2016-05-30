package com.os.osframe.frame.interfaces;

/**
 * Created by wangchun on 16/4/16.
 */
public interface IUserModel {
    public String getUserId();
    public String getUserName();
    public String getName();
    public String getPwd();
    public String getUserLevelIds();
    public String getUserOrgId();//一人如果归属多机构怎么处理？通过切换岗位来切换

}
