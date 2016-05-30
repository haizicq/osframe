package com.os.osframe.core.taglib.bs;

import com.os.osframe.frame.util.CurrentUserUtil;
import com.os.osframe.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 权限标签
 * 可传入参数：角色、url、control、方法名、数据项
 * 保存时需要将校验的url保存下来，控制器包路径、方法名（还是不需要了，这样要保存太多重复的角色）
 * 为了提升权限校验的速度，标签获取权限采用缓存模式，去读取缓存中的
 * Created by wangchun on 16/5/6.
 */
public class RoleTag  extends BodyTagSupport {
    protected static final String basePath="com.os.osframe";

    /**
     * 权限角色
     */
    protected String role = null;
    /**
     * 所属模块
     */
    protected String module = null;

    /**
     * 模型
     */
    protected String controller = null;
    /**
     * 方法
     */
    protected String function = null;

    /**
     * 参数值
     */
    protected String value = null;

    public void release()
    {
        super.release();
        this.role = null;
        this.module = null;
        this.controller = null;
        this.function = null;
        this.value = null;
    }

    @Override
    public int doStartTag() throws JspException {
        //返回0-SKIP_BODY不显示内部的内容，返回1-EVAL_BODY_INCLUDE返回内部内容
        if(StringUtil.isNull(role) &&  StringUtil.isNull(controller) && StringUtil.isNull(function)){//当没有传递任何参数时直接返回
            return 1;
        }
        if(StringUtil.isNull(this.module)){
            this.module=this.getModuleByUrl();
        }
        if(CurrentUserUtil.isAdmin() || CurrentUserUtil.isDeveloper() || CurrentUserUtil.isModuleAdmin(module)){//当为任何一项管理员时，不进行权限校验
            return 1;
        }
        if(StringUtil.isNotNull(role)){//当有权限时直接返回权限对象
            if(CurrentUserUtil.checkModuleRole(this.module,role)){
                return 1;
            }

        }else if(StringUtil.isNotNull(controller) && StringUtil.isNotNull(function)){//当域模型和方法不为空时，进行权限校验
            String _controller;
            //根据url获取模块路径 基础路径+模块路径+contorl+domain 同模块内部的调用没问题，但外部模块调用，必须走全部路径
            if(controller.indexOf(basePath)>-1){
                _controller=controller;
            }else{
                _controller=basePath+"."+this.module+".web."+controller;
            }
            if(CurrentUserUtil.checkAuth(_controller, function, value)){
                return 1;
            }
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    /**
     * 获取模块信息
     * @return 如base.taglib
     */
    protected String getModuleByUrl(){
        HttpServletRequest httpRequest=(HttpServletRequest)pageContext.getRequest();
        String servletPath=httpRequest.getServletPath();
        if(servletPath.indexOf("WEB-INF/jsp")>-1){//当存在webinf时
            servletPath=servletPath.substring(servletPath.indexOf("WEB-INF/jsp")+12,servletPath.lastIndexOf("/"));
            //除了index\nav\fullpage是一级
            if(!(servletPath.indexOf("index.jsp")>-1 || servletPath.indexOf("nav.jsp")>-1 || servletPath.indexOf("full_page.jsp")>-1) ){
                servletPath=servletPath.substring(0,servletPath.lastIndexOf("/"));
            }
        }
        //servletPath=servletPath.substring(1,servletPath.lastIndexOf("/"));
        return servletPath.replaceAll("/",".");
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
