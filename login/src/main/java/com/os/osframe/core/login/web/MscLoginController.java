package com.os.osframe.core.login.web;

import com.alibaba.fastjson.JSONObject;
import com.os.osframe.frame.util.CurrentUserUtil;
import com.os.osframe.core.login.domain.UserModel;
import com.os.osframe.util.PasswordUtil;
import com.os.osframe.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;


@Controller
public class MscLoginController {
    /**
     * 跳转到登陆页面
     * @param user
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(UserModel user,HttpSession session, HttpServletRequest request){
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("login");//默认直接跳转到登陆界面
        return modelView;
    }
    /**
     * 提交登陆入口
     * @param user
     * @param session
     * @param request
     * @return
     */
    @ResponseBody
	@RequestMapping(value = "/submitLogin")
	public String submitLogin(UserModel user,HttpSession session, HttpServletRequest request,HttpServletResponse response) {

        JSONObject jsonObject =new JSONObject();
		Subject currentUser = SecurityUtils.getSubject();

        if(StringUtil.isNull(user.getUserName()) || StringUtil.isNull(user.getPwd())){//当用户名或密码为空时直接跳转到登陆页面
            jsonObject.put("error","0");
            jsonObject.put("code","0");
            jsonObject.put("msg","请输入用户名和密码");
            return jsonObject.toJSONString();
        }
        if(currentUser.isAuthenticated()){//当已经登陆
            jsonObject.put("error","1");
            jsonObject.put("code","2");
            jsonObject.put("msg","用户已经登录");
            jsonObject.put("url",redirectUrl(request));
            return jsonObject.toJSONString();
        }
        String encryptPwd= PasswordUtil.encrypt(user.getUserName(), user.getPwd(), PasswordUtil.getStaticSalt());
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),encryptPwd);
		token.setRememberMe(true);
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		if(currentUser.isAuthenticated()){
            jsonObject.put("error","1");
            jsonObject.put("code","3");
            jsonObject.put("msg","登录成功");
            jsonObject.put("url",redirectUrl(request));
            return jsonObject.toJSONString();
		}else{
            jsonObject.put("error","0");
            jsonObject.put("code","1");
            jsonObject.put("msg","用户名或密码错误");
            return jsonObject.toJSONString();
		}
	}

    /**
     * 返回重定向的页面地址
     * @param request
     */
    protected String redirectUrl(HttpServletRequest request){
        String targetUrl=request.getParameter("targetUrl");//获取目标url
        if(StringUtil.isNull(targetUrl)){
            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);//获取拦截的url
            if(savedRequest!=null){
                targetUrl = savedRequest.getRequestUrl();
            }
            try{
                if(StringUtil.isNotNull(targetUrl)){
                    return URLDecoder.decode(targetUrl,"UTF-8");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return request.getContextPath()+"/index.html";
        }
        return targetUrl;
    }
    /**
     * 重定向到目标页面
     * @param request
     * @param response
     */
    protected void redirectTarget(HttpServletRequest request,HttpServletResponse response){
        String targetUrl=null;
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);//获取拦截的url
        if(savedRequest!=null){
            targetUrl = savedRequest.getRequestUrl();
        }
        try{
            if(StringUtil.isNotNull(targetUrl)){
                response.sendRedirect(URLDecoder.decode(targetUrl,"UTF-8"));
            }else{
                response.sendRedirect(request.getContextPath()+"/index.html");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView logout() {
		Subject currentUser = SecurityUtils.getSubject();
		try {
            CurrentUserUtil.clearUserCache();//清楚用户缓存，避免重新授权之类的功能不能直接使用
			currentUser.logout();
		} catch (AuthenticationException e) {
			e.printStackTrace();

		}
        ModelAndView modelView = new ModelAndView();
        modelView.setViewName("redirect:login.html");//默认直接跳转到登陆界面
        return modelView;
	}

}
