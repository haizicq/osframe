package com.os.osframe.frame.web;

import com.os.osframe.frame.common.DomainInfo;
import com.os.osframe.frame.common.RequiresAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 模块首页Controller的基类
 * XXX 待实现
 * </pre>
 * @see
 */
@Controller
public abstract class BaseIndexController {

    /**
     * 获取Request
     * @return
     */
    protected  HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    @RequiresAuth
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView defaultIndex() throws Exception {
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("redirect:/"+getDomainInfo().getModulePath()+"/index.html");//默认直接跳转到登陆界面
//        modelView.setViewName(getDomainInfo().getModulePath()+"/index");
        return modelView;
    }
    /**
     * 模块首页
     * @return
     * @throws Exception
     */
    @RequiresAuth
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) throws Exception {
        return getDomainInfo().getModulePath()+"/index";
    }
    @RequiresAuth
    @RequestMapping(value = "/fullPage")
    public String fullPage(HttpServletRequest request) throws Exception {
        return getDomainInfo().getModulePath()+"/full_page";
    }
    @RequiresAuth
    @RequestMapping(value = "/nav")
    public String nav(HttpServletRequest request) throws Exception {
        //nav
        return getDomainInfo().getModulePath()+"/nav";
    }
    /**
     * 获取默认的模型的类名
     * @return
     */
    protected DomainInfo getDomainInfo() {
        DomainInfo domainInfo=new DomainInfo(this.getClass());
        return domainInfo;
    }

}
