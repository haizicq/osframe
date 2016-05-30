package com.os.osframe.core.auth.web;

import com.os.osframe.core.auth.domain.MscAuthCategory;
import com.os.osframe.core.auth.service.IMscAuthCategoryService;
import com.os.osframe.core.auth.service.IMscAuthInfoService;
import com.os.osframe.core.auth.service.IMscAuthRoleService;
import com.os.osframe.core.users.service.IMscUsersInfoService;
import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.common.RequiresAuth;
import com.os.osframe.frame.common.RequiresClassAuth;
import com.os.osframe.frame.log.LogInfo;
import com.os.osframe.frame.log.OutException;
import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.frame.util.CurrentUserUtil;
import com.os.osframe.frame.web.BaseController;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 角色分组
 * Created by wangdc on 2016-04-12 16:42.
 */
@Controller
@RequestMapping(value = "/admin/install")
public class MscAuthInstallController {
    LogInfo log=new LogInfo(MscAuthInstallController.class);
    @Resource
    IMscAuthInfoService mscAuthInfoService;
    @Resource
    IMscAuthRoleService mscAuthRoleService;
    /**
     * 跳转到错误页面
     * @return
     */
    protected String forwardError(){
        return "forward:/common/jsp/error.jsp";
    }
    /**
     * 跳转到成功页面
     * @return
     */
    protected String forwardSuccess(){
        return "forward:/common/jsp/success.jsp";
    }
    /**
     * 初始化权限
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping({"/auth"})
    public String export(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        OutException outException=new OutException("auth");
        try{
            mscAuthInfoService.updateAuthInfo();
        }catch (Exception e){
            outException.setError(e);
        }
        log.executeTime(outException);
        if(outException.hasError()){
            log.error(outException);
            if(CurrentUserUtil.isDeveloper()){//校验是否开发者，非开发者则不返回错误信息了,仅有
                model.addAttribute("errorInfo",outException.toString());
            }
            return forwardError();//错误文件存在资源文件位置
        }
        return forwardSuccess();
    }

    /**
     * 初始化管理员账号
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping({"/account"})
    public String account(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        OutException outException=new OutException("auth");
        try{
            int i=mscAuthRoleService.initAccount();
            if(i==0){
                //outException.setError();
            }
        }catch (Exception e){
            outException.setError(e);
        }
        log.executeTime(outException);
        if(outException.hasError()){
            log.error(outException);
            if(CurrentUserUtil.isDeveloper()){//校验是否开发者，非开发者则不返回错误信息了,仅有
                model.addAttribute("errorInfo",outException.toString());
            }
            return forwardError();//错误文件存在资源文件位置
        }
        return forwardSuccess();
    }
}
