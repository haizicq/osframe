package com.os.osframe.core.users.web;

import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.common.RequiresClassAuth;
import com.os.osframe.frame.domain.IBaseDomain;
import com.os.osframe.frame.log.OutException;
import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.frame.util.CurrentUserUtil;
import com.os.osframe.frame.web.BaseController;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.core.users.domain.MscUsersJob;
import com.os.osframe.core.users.service.IMscUsersInfoService;
import com.os.osframe.core.users.service.IMscUsersJobService;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 岗位信息
 * Created by wangdc on 2016-03-01 23:39.
 */
@Controller
@RequestMapping(value = "/core/users/mscUsersJob")
@RequiresClassAuth(common= RequiresClassAuth.CommonType.CONTROL)
public class MscUsersJobController extends BaseController<MscUsersJob> {

    @Resource
    IMscUsersJobService mscUsersJobService;
    @Override
    protected IBaseService getServiceImp() {
        return mscUsersJobService;
    }

    @Resource
    IMscUsersInfoService mscUsersInfoService;
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
                hqlObject.setWhereExtract(" mscUsersJob.lbUsersInfo.pkId=:bid");
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
            hqlObject.setWhereExtract(" mscUsersJob.lbUsersInfo.pkId=:bid");
            hqlObject.setParameter("bid",bid);
            List<IBaseDomain> list=this.getServiceImp().find(hqlObject);
            if(list!=null && !list.isEmpty()){
                IBaseDomain baseDomain = list.get(0);
                model.addAttribute(this.getDomainInfo().getSimpleName(), baseDomain);
            }
        }

        return this.getDomainInfo().getPathPix() + "edit";
    }

    @Override
    protected IBaseDomain initDomain(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MscUsersJob mscUsersJob=(MscUsersJob)super.initDomain(request, response);
        String pid=request.getParameter("pid");
        if(StringUtil.isNotNull(pid)){
            MscUsersInfo parentDomain = (MscUsersInfo)mscUsersInfoService.findById(pid);
            if(parentDomain!=null){

                MscUsersInfo baseDomain=new MscUsersInfo();
                baseDomain.setLbParent(parentDomain);
                mscUsersJob.setLbUsersInfo(baseDomain);

            }
        }
        return mscUsersJob;
    }

    @Override
    protected void changeSearchHqlObject(HttpServletRequest request, HqlObject hqlObject) throws Exception {
        super.changeSearchHqlObject(request, hqlObject);
        String where="";
        String searchKey=request.getParameter("searchKey");
        if(StringUtil.isNotNull(searchKey)){
            where="mscUsersInfo.lbName like :searchKey";
            hqlObject.setParameter("searchKey","%"+searchKey+"%");
        }
        hqlObject.setWhereExtract(StringUtil.linkString(hqlObject.getWhereExtract()," and ",where));
    }
}
