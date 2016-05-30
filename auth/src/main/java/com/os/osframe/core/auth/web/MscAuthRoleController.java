package com.os.osframe.core.auth.web;

import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.common.RequiresClassAuth;
import com.os.osframe.frame.domain.IBaseDomain;
import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.frame.web.BaseController;
import com.os.osframe.core.auth.domain.MscAuthCategory;
import com.os.osframe.core.auth.domain.MscAuthRole;
import com.os.osframe.core.auth.service.IMscAuthCategoryService;
import com.os.osframe.core.auth.service.IMscAuthRoleService;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 角色信息
 * Created by wangdc on 2016-04-12 16:42.
 */
@Controller
@RequestMapping(value = "/core/auth/mscAuthRole")
@RequiresClassAuth(common= RequiresClassAuth.CommonType.CONTROL)
public class MscAuthRoleController extends BaseController<MscAuthRole> {
    @Resource
    IMscAuthRoleService mscAuthRoleService;
    @Resource
    IMscAuthCategoryService mscAuthCategoryService;
    @Override
    protected IBaseService getServiceImp() {
        return mscAuthRoleService;
    }

    @Override
    protected void changeSearchHqlObject(HttpServletRequest request, HqlObject hqlObject) throws Exception {
        super.changeSearchHqlObject(request, hqlObject);
        String categoryId=request.getParameter("cid");
        String where="1=1";
        if(StringUtil.isNotNull(categoryId)){
            where="mscAuthRole.lbRoleCategory.pkId=:cid";
            hqlObject.setParameter("cid",categoryId);
        }
        String searchKey=request.getParameter("searchKey");
        if(StringUtil.isNotNull(searchKey)){
            where+=" and mscAuthRole.lbName like :searchKey";
            hqlObject.setParameter("searchKey","%"+searchKey+"%");
        }
        hqlObject.setWhereExtract(StringUtil.linkString(hqlObject.getWhereExtract()," and ",where));

    }

    @Override
    protected IBaseDomain initDomain(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MscAuthRole mscAuthRole=(MscAuthRole)super.initDomain(request, response);
        String categoryId=request.getParameter("cid");
        if(StringUtil.isNotNull(categoryId)){//设置角色分组
            MscAuthCategory mscAuthCategory=mscAuthCategoryService.findById(categoryId);
            mscAuthRole.setLbRoleCategory(mscAuthCategory);
        }
        return mscAuthRole;

    }
}
