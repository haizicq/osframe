package com.os.osframe.frame.web;

import com.os.osframe.frame.log.LogInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局设定的抽象个人相关信息的控制器，需要模块中子类来实现详细的方法
 *  包含我的主页、个人信息、修改密码
 * Created by wangchun on 16/5/29.
 */
@Controller
public abstract class MyController {
    protected LogInfo logger=new LogInfo(this.getClass());

    /**
     * 我的主页
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/my/index")
    public String myIndex(HttpServletRequest request) throws Exception{
        return "/my/index";
    }
    /**
     * 我的个人信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/my/info")
    public String myInfo(HttpServletRequest request,Model model) throws Exception{
        return "/my/info";
    }

    /**
     * 编辑我的信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/my/info/edit")
    public String myEditInfo(HttpServletRequest request,Model model) throws Exception{
        return "/my/edit";
    }
    /**
     * 修改我的密码
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/my/pwd")
    public String changeMyPwd(HttpServletRequest request) throws Exception{
        return "/my/pwd";
    }

}
