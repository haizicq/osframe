package com.os.osframe.frame.util;

/**
 * Created by wangchun on 16/4/16.
 */
public class AuthConstant {
    /**
     * 管理员角色
     * ROLE_ADMIN
     */
    public final static String ROLE_ADMIN="ROLE_ADMIN";
    /**
     * 开发者角色
     * ROLE_DEVELOPER
     */
    public final static String ROLE_DEVELOPER="ROLE_DEVELOPER";
    /**
     * 匿名用户角色
     * ROLE_ANONYMOUS
     */
    public final static String ROLE_ANONYMOUS="ROLE_ANONYMOUS";
    /**
     * 登陆用户角色
     * ROLE_ANONYMOUS
     */
    public final static String ROLE_USERS="ROLE_USERS";
    /**
     * 集团管理员角色
     * ROLE_GROUP_ADMIN
     */
    public final static String ROLE_GROUP_ADMIN="ROLE_GROUP_ADMIN";
    /**
     * 自定义角色
     * ROLE_CUSTOM
     */
    public final static String ROLE_CUSTOM="ROLE_CUSTOM";
    /**
     * 数据权限类型 拥有者
     * 1
     */
    public final static String DATA_AUTH_TYPE_OWNER="1";
    /**
     * 数据权限类型 阅读者
     * 2
     */
    public final static String DATA_AUTH_TYPE_READER="2";
    /**
     * 数据权限类型 编辑者
     * 3
     */
    public final static String DATA_AUTH_TYPE_EDITOR="3";
    /**
     * 数据权限类型 下载者
     * 4
     */
    public final static String DATA_AUTH_TYPE_DOWNER="4";
    /**
     * 数据权限类型 其他阅读者
     * 5
     */
    public final static String DATA_AUTH_TYPE_OTHER_READER="5";
    /**
     * 为空处理方式 所有机构人可读
     * 0
     */
    public final static String DATA_AUTH_NULL_ORG_READER="0";
    /**
     * 为空处理方式 机构内所有人可读
     * 1
     */
    public final static String DATA_AUTH_NULL_ALL_READER="1";
    /**
     * 为空处理方式 相关人可读
     * 2
     */
    public final static String DATA_AUTH_NULL_RELEVANT_READER="2";

    /**
     * 根据用户获取权限角色
     * @param type
     * @return
     */
    public static String getRole(String type){
        String role=null;
        if("1".equals(type)){
            role=ROLE_ADMIN;
        }else if("2".equals(type)){
            role=ROLE_DEVELOPER;
        }else if("3".equals(type)){
            role=ROLE_GROUP_ADMIN;
        }else if("4".equals(type)){
            role=ROLE_ANONYMOUS;
        }else if("5".equals(type)){
            role=ROLE_USERS;
        }else{
            role=ROLE_CUSTOM;
        }
        return role;
    }
}
