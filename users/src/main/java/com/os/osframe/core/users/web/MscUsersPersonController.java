package com.os.osframe.core.users.web;

import com.os.osframe.frame.common.*;
import com.os.osframe.frame.domain.IBaseDomain;
import com.os.osframe.frame.log.OutException;
import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.frame.util.CurrentUserUtil;
import com.os.osframe.frame.web.BaseController;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.core.users.domain.MscUsersPerson;
import com.os.osframe.core.users.service.IMscUsersInfoService;
import com.os.osframe.core.users.service.IMscUsersPersonService;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 人员信息
 * Created by wangdc on 2016-03-01 23:39.
 */
@Controller
@RequestMapping(value = "/core/users/mscUsersPerson")
@RequiresClassAuth
public class MscUsersPersonController extends BaseController<MscUsersPerson> {
    @Resource
    IMscUsersPersonService mscUsersPersonService;
    @Override
    protected IBaseService getServiceImp() {
        return mscUsersPersonService;
    }

    @Resource
    IMscUsersInfoService mscUsersInfoService;
    @Override
    @RequestMapping({"/look"})
    public String look(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        OutException outException=new OutException("look");
        try {
            String e = request.getParameter("id");
            String bid = request.getParameter("bid");
            if(StringUtil.isNotNull(e)) {
                IBaseDomain baseDomain = (IBaseDomain)this.getServiceImp().findById(e);
                model.addAttribute(this.getDomainInfo().getSimpleName(), baseDomain);
            }else if(StringUtil.isNotNull(bid)){
                HqlObject hqlObject=new HqlObject();
                hqlObject.setWhereExtract(" mscUsersPerson.lbUsersInfo.pkId=:bid");
                hqlObject.setParameter("bid",bid);
                List<IBaseDomain> list=this.getServiceImp().find(hqlObject);
                if(list!=null && !list.isEmpty()){
                    IBaseDomain baseDomain = list.get(0);
                    model.addAttribute(this.getDomainInfo().getSimpleName(), baseDomain);
                }

            }
        } catch (Exception e) {
            outException.setError(e);
        }
        logger.executeTime(outException);
        if(outException.hasError()){
            logger.error(outException);
            if(CurrentUserUtil.isDeveloper()){//校验是否开发者，非开发者则不返回错误信息了,仅有
                model.addAttribute("errorInfo",outException.toString());
            }
            return forwardError();//错误文件存在资源文件位置
        }
            return this.getDomainInfo().getPathPix() + "look";
    }
    @RequestMapping({"/edit"})
    public String edit(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String bid = request.getParameter("bid");
        if(StringUtil.isNotNull(id)) {
            IBaseDomain baseDomain = (IBaseDomain)this.getServiceImp().findById(id);
            model.addAttribute(this.getDomainInfo().getSimpleName(), baseDomain);
        }else if(StringUtil.isNotNull(bid)){
            HqlObject hqlObject=new HqlObject();
            hqlObject.setWhereExtract(" mscUsersPerson.lbUsersInfo.pkId=:bid");
            hqlObject.setParameter("bid",bid);
            List<IBaseDomain> list=this.getServiceImp().find(hqlObject);
            if(list!=null && !list.isEmpty()){
                IBaseDomain baseDomain = list.get(0);
                model.addAttribute(this.getDomainInfo().getSimpleName(), baseDomain);
            }
        }

        return this.getDomainInfo().getPathPix() + "edit";
    }

    /**
     * 重置密码
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    @RequiresAuth
    @RequestMapping({"/rePwd"})
    public String rePwd(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //当方法为get时，重定向到修改密码页面
        if (!request.getMethod().equals("POST")){//跳转到修改密码页面

            return this.getDomainInfo().getPathPix() + "rePwd";
        }else{
            //当页面代码为post时
            OutException outException=new OutException("rePwd");
            JsonDataFormat jsonDataFormat=new JsonDataFormat();
            try{
                //XXX 先获取设置的用户名、密码、重复密码，确认正确后修改密码保存
                String usersId=request.getParameter("usersId");

                String newPwd=request.getParameter("newPassword");
                String rePwd=request.getParameter("newRepeatPassword");
                if(StringUtil.isNull(newPwd) || StringUtil.isNull(rePwd) || !newPwd.trim().equals(rePwd.trim())){
                    jsonDataFormat.setErrorMessage("1","新密码与确认密码不同");
                }
                if(StringUtil.isNull(usersId)){
                    jsonDataFormat.setErrorMessage("0","未知变更用户信息");
                }else{
                    MscUsersPerson user= mscUsersPersonService.findById(usersId);
                    if(user==null){
                        jsonDataFormat.setErrorMessage("0","未知变更用户信息");
                    }
                    if(!jsonDataFormat.isError()){
                        mscUsersPersonService.updateChangePwd(user,newPwd,rePwd);
                    }
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
            //根据response输出json
            jsonDataFormat.printJson(response);
        }
        return null;
    }

    /**
     * 修改我的密码
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping({"/myPwd"})
    public String myPwd(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //当方法为get时，重定向到修改密码页面
        if (!request.getMethod().equals("POST")){//跳转到修改密码页面
            return this.getDomainInfo().getPathPix() + "myPwd";
        }else{
            //当页面代码为post时

            OutException outException=new OutException("myPwd");
            JsonDataFormat jsonDataFormat=new JsonDataFormat();
            try{
                //XXX 先获取原密码、新密码、重复密码，确认正确后修改密码保存
                //获取当前登录人，根据登录人校验密码，有效则重新保存新密码
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
            //根据response输出json
            jsonDataFormat.printJson(response);
        }
        return null;
    }
    @Override
    protected IBaseDomain initDomain(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MscUsersPerson mscUsersPerson=(MscUsersPerson)super.initDomain(request, response);
        String pid=request.getParameter("pid");
        if(StringUtil.isNotNull(pid)){
            MscUsersInfo parentDomain = (MscUsersInfo)mscUsersInfoService.findById(pid);
            if(parentDomain!=null){
                MscUsersInfo baseDomain=new MscUsersInfo();
                baseDomain.setLbParent(parentDomain);
                mscUsersPerson.setLbUsersInfo(baseDomain);

            }
        }
        return mscUsersPerson;
    }
    //XXX 人员需要提供可以查看自己的个人信息的权限

    @Override
    protected void changeSearchHqlObject(HttpServletRequest request, HqlObject hqlObject) throws Exception {
        super.changeSearchHqlObject(request, hqlObject);
        String where="";
        String searchKey=request.getParameter("searchKey");
        if(StringUtil.isNotNull(searchKey)){
            where=" mscUsersPerson.lbName like :searchKey";
            hqlObject.setParameter("searchKey","%"+searchKey+"%");
        }
        hqlObject.setWhereExtract(StringUtil.linkString(hqlObject.getWhereExtract()," and ",where));
    }
}
