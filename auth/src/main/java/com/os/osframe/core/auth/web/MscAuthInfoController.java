package com.os.osframe.core.auth.web;

import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.common.RequiresAuth;
import com.os.osframe.frame.common.RequiresClassAuth;
import com.os.osframe.frame.domain.IBaseDomain;
import com.os.osframe.frame.log.OutException;
import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.frame.util.CurrentUserUtil;
import com.os.osframe.frame.web.BaseController;
import com.os.osframe.core.auth.domain.MscAuthInfo;
import com.os.osframe.core.auth.service.IMscAuthInfoService;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 权限信息
 * Created by wangdc on 2016-04-12 16:42.
 */
@Controller
@RequestMapping(value = "/core/auth/mscAuthInfo")
@RequiresClassAuth(common= RequiresClassAuth.CommonType.CONTROL)
public class MscAuthInfoController extends BaseController<MscAuthInfo> {
    @Resource
    IMscAuthInfoService mscAuthInfoService;
    @Override
    protected IBaseService getServiceImp() {
        return mscAuthInfoService;
    }

    @Override
    protected void changeSearchHqlObject(HttpServletRequest request, HqlObject hqlObject) throws Exception {
        super.changeSearchHqlObject(request, hqlObject);
        String module=request.getParameter("module");
        String where="";
        if(StringUtil.isNotNull(module)){
            where="mscAuthInfo.lbModuleKey=:module";
            hqlObject.setParameter("module",module);
        }
        String searchKey=request.getParameter("searchKey");
        if(StringUtil.isNotNull(searchKey)){
            where+=StringUtil.linkString(where," and "," mscAuthInfo.lbName like :searchKey");
            hqlObject.setParameter("searchKey","%"+searchKey+"%");
        }
        hqlObject.setWhereExtract(StringUtil.linkString(hqlObject.getWhereExtract()," and ",where));
    }

    @Override
    protected IBaseDomain initDomain(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MscAuthInfo mscAuthInfo=(MscAuthInfo) super.initDomain(request, response);
        String module=request.getParameter("module");
        if(StringUtil.isNotNull(module)){//设置角色分组
            HqlObject hqlAuth=new HqlObject();
            hqlAuth.setDistinctType(HqlObject.NOT_REPEAT);//去重
            hqlAuth.setSelectExtract(" new Map(mscAuthInfo.lbModuleKey as lbModuleKey,mscAuthInfo.lbModule as lbModule,mscAuthInfo.lbSystem as lbSystem,mscAuthInfo.lbSystemKey as lbSystemKey)");
            hqlAuth.setWhereExtract("mscAuthInfo.lbModuleKey=:moduleKey");
            hqlAuth.setParameter("moduleKey",module);

            List authModuleList=mscAuthInfoService.find(hqlAuth);
            if(authModuleList!=null && !authModuleList.isEmpty()){
                Map map =(Map)authModuleList.get(0);
                mscAuthInfo.setLbModule((String)map.get("lbModule"));
                mscAuthInfo.setLbModuleKey((String) map.get("lbModuleKey"));
                mscAuthInfo.setLbSystem((String) map.get("lbSystem"));
                mscAuthInfo.setLbSystemKey((String)map.get("lbSystemKey"));
            }
        }
        return mscAuthInfo;
    }


    /**
     * 导入权限
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    @RequestMapping({"/export"})
    @RequiresAuth(role="ROLE_MSC_AUTH_ADMIN")
    public String export(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        OutException outException=new OutException("auth");
        try{
            mscAuthInfoService.updateAuthInfo();
        }catch (Exception e){
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
        return forwardSuccess();
    }


}
