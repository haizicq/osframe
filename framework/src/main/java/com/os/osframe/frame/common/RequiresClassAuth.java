package com.os.osframe.frame.common;

import java.lang.annotation.*;

/**
 * Created by wangchun on 16/4/16.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface RequiresClassAuth {

    public enum CommonType{
        DEFAULT,	//默认方式 ，可对增删改查权限控制
        CONTROL 	//仅使用管理员权限，针对父类中的 即：TypeMode.PACKAGE不做管理员控制

    }
    public String role() default "";//权限角色,默认为默认权限
    public CommonType common() default CommonType.DEFAULT;//公共方法处理方式，主要用于父类中，因为父类的公用方法不好直接写死角色
    public String rolePref() default "";//权限前缀


}
