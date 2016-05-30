package com.os.osframe.frame.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangchun on 16/6/7.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView defaultIndex() throws Exception {
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("redirect:/admin/index.html");//默认直接跳转到首页
        return modelView;
    }
    @RequestMapping("/index")
    public String index(HttpServletRequest request)throws Exception {
        return "admin/index";//路径获取为通用
    }

    /**
     * 系统初始化，无需登录，无需权限验证
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/install/index")
    public String inti(HttpServletRequest request)throws Exception {
        return "admin/init";//路径获取为通用
    }
}
