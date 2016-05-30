package com.os.osframe.core.users.web;

import com.os.osframe.frame.common.DomainInfo;
import com.os.osframe.frame.common.JsonDataFormat;
import com.os.osframe.frame.common.RequiresClassAuth;
import com.os.osframe.frame.log.OutException;
import com.os.osframe.frame.util.CurrentUserUtil;
import com.os.osframe.frame.web.MyController;
import com.os.osframe.core.users.domain.MscUsersPerson;
import com.os.osframe.core.users.service.IMscUsersPersonService;
import com.os.osframe.util.DateUtil;
import com.os.osframe.util.PasswordUtil;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by wangchun on 16/5/29.
 */
@Controller
@RequiresClassAuth
public class MscUsersMyController extends MyController {
    @Resource
    IMscUsersPersonService mscUsersPersonService;
    @Override
    public String myInfo(HttpServletRequest request,Model model) throws Exception {
        //查询人员的相关信息
        MscUsersPerson person= (MscUsersPerson)mscUsersPersonService.findByUserName(CurrentUserUtil.getUserName());
        model.addAttribute("userInfo",person);
        return  super.myInfo(request,model);
    }

    @Override
    public String myEditInfo(HttpServletRequest request, Model model) throws Exception {
        MscUsersPerson person= (MscUsersPerson)mscUsersPersonService.findByUserName(CurrentUserUtil.getUserName());
        model.addAttribute("userInfo",person);
        return super.myEditInfo(request, model);
    }

    @ResponseBody
    @RequestMapping(value="/my/savePwd" ,method = RequestMethod.POST)
    public String saveMyPwd(HttpServletRequest request) throws Exception {
        OutException outException=new OutException("savePwd");
        JsonDataFormat jsonDataFormat=new JsonDataFormat();
        try{
            String oldPwd=request.getParameter("oldPwd");
            String newPwd=request.getParameter("newPwd");
            String confirmPwd=request.getParameter("newConfirmPwd");
            if(StringUtil.isNull(newPwd) || StringUtil.isNull(confirmPwd) || !newPwd.trim().equals(confirmPwd.trim())){
                jsonDataFormat.setErrorMessage("1","新密码与确认密码不同");
            }
            MscUsersPerson user= (MscUsersPerson)CurrentUserUtil.getUser();
            //旧密码是否正确
            if(StringUtil.isNotNull(oldPwd)){
                String oldPwdEn= PasswordUtil.encrypt(user.getLbLoginName(), oldPwd.trim(), PasswordUtil.getStaticSalt());
                if(!oldPwdEn.equals(user.getLbPwd())){
                    jsonDataFormat.setErrorMessage("2","原密码错误");
                }
            }
                if(!jsonDataFormat.isError()){
                    mscUsersPersonService.updateChangePwd(user, newPwd, confirmPwd);
                }

        }catch (Exception e){
            outException.setError(e);
            jsonDataFormat.setCommonCode(JsonDataFormat.JSON_ERROR_CODE_SERVICE, e.getMessage());
        }
        logger.executeTime(outException);
        if(outException.hasError()){
            logger.error(outException);
            if(CurrentUserUtil.isDeveloper()){//校验是否开发者，非开发者则不返回错误信息了,仅有
                jsonDataFormat.setErrorMessage(JsonDataFormat.JSON_ERROR_CODE_OTHER, outException.toString());
            }
        }
        return jsonDataFormat.toString();
    }
    @ResponseBody
    @RequestMapping(value="/my/saveInfo" ,method = RequestMethod.POST)
    public String saveInfo(HttpServletRequest request) throws Exception {
        OutException outException=new OutException("saveInfo");
        JsonDataFormat jsonDataFormat=new JsonDataFormat();
        try{
            String birthday=request.getParameter("birthday");
            String sex=request.getParameter("sex");
            String phone=request.getParameter("phone");
            String mobilePhone=request.getParameter("mobilePhone");
            String email=request.getParameter("email");
            String other=request.getParameter("other");
            String language=request.getParameter("language");

            MscUsersPerson user= (MscUsersPerson)CurrentUserUtil.getUser();
            if(StringUtil.isNotNull(birthday)){
                user.setLbBirthday(DateUtil.convertStringToDate(DateUtil.DEFAILT_DATE_PATTERN,birthday));
            }
            user.setLbSex(sex);
            user.setLbPhone(phone);
            user.setLbMobilePhone(mobilePhone);
            user.setLbMail(email);
            user.setLbOthers(other);
            user.setLbLanguage(language);
            mscUsersPersonService.update(user);

        }catch (Exception e){
            outException.setError(e);
            jsonDataFormat.setCommonCode(JsonDataFormat.JSON_ERROR_CODE_SERVICE, e.getMessage());
        }
        logger.executeTime(outException);
        if(outException.hasError()){
            logger.error(outException);
            if(CurrentUserUtil.isDeveloper()){//校验是否开发者，非开发者则不返回错误信息了,仅有
                jsonDataFormat.setErrorMessage(JsonDataFormat.JSON_ERROR_CODE_OTHER, outException.toString());
            }
        }
        return jsonDataFormat.toString();
    }
    public static void main(String[] args) throws Exception{
        Date date=DateUtil.convertStringToDate(DateUtil.DEFAILT_DATE_PATTERN,"2015-09-08");
    }
    /**
     * 获取默认的模型的路劲信息
     * @return
     */
    protected DomainInfo getDomainInfo(Class domainClass) {
        DomainInfo domainInfo= new DomainInfo(domainClass);
        return domainInfo;
    }
}
