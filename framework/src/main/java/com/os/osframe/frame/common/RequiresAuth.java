package com.os.osframe.frame.common;

import java.lang.annotation.*;

/**
 * 权限注解
 * Created by wangchun on 16/4/15.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface RequiresAuth {
    public enum SeatType{
        DEFAULT,	//默认类型，直接获取role值解析
        COMMON     //公共的类型，该类型默认处理方式为PACKAGE，当在类上配置了前缀时，使用前缀
    }
    public enum RoleType{
        NONE,	//默认类型，不做类型控制
        EDIT,     //编辑权限控制
        READER,	//阅读权限控制
        DOWN		//下载权限控制
    }
    public enum ReturnType{
        DEFAULT,         //不设置默认
        ModelAndView,	//
        JSON,     // 返回JSON提示
        String	// 返回字符串当有ResponseBody注解的方法会返回json
    }
    public String role() default "";//权限角色
    public SeatType seat() default SeatType.DEFAULT;//权限位置，主要用于父类中，因为父类的公用方法不好直接写死角色
    public RoleType type() default RoleType.NONE;//处理的权限类型
    public ReturnType returnType() default ReturnType.DEFAULT;//处理的权限类型
    public String id() default "";//默认在url中获取id参数
}
