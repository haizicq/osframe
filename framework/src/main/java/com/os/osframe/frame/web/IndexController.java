package com.os.osframe.frame.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目首页访问地址
 * 文件默认放到jsp的根目录下
 * </pre>
 * @see
 */
@Controller
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView defaultIndex() throws Exception {
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("redirect:/index.html");//默认直接跳转到首页
        return modelView;
    }
    @RequestMapping("/index")
    public String index(HttpServletRequest request)throws Exception {
        return "index";//路径获取为通用
    }

    @RequestMapping("/portal")
    public String portal(HttpServletRequest request)throws Exception {
        return "index/portal";//路径获取为通用
    }
}
