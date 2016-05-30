package com.os.osframe.frame.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 根目录下获取文件路径
 * Created by wangdc on 2014-11-8.
 */

@Controller
@RequestMapping(value = "/jsp")
public class JspController {
    /**
     * 直接访问jsp文件
     * @param request
     * @param redirect
     * @return
     * @throws Exception
     */
    @RequestMapping("/{redirect}")
    public String redirect(HttpServletRequest request,@PathVariable String redirect)throws Exception {
       return "/"+redirect;//路径获取为通用
    }
}
