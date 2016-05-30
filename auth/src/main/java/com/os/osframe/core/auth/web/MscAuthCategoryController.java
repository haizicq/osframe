package com.os.osframe.core.auth.web;

import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.common.RequiresClassAuth;
import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.frame.web.BaseController;
import com.os.osframe.core.auth.domain.MscAuthCategory;
import com.os.osframe.core.auth.service.IMscAuthCategoryService;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 角色分组
 * Created by wangdc on 2016-04-12 16:42.
 */
@Controller
@RequestMapping(value = "/core/auth/mscAuthCategory")
@RequiresClassAuth(common= RequiresClassAuth.CommonType.CONTROL)
public class MscAuthCategoryController extends BaseController<MscAuthCategory> {
    @Resource
    IMscAuthCategoryService mscAuthCategoryService;
    @Override
    protected IBaseService getServiceImp() {
        return mscAuthCategoryService;
    }

    @Override
    protected void changeSearchHqlObject(HttpServletRequest request, HqlObject hqlObject) throws Exception {
        super.changeSearchHqlObject(request, hqlObject);
        String where="";
        String searchKey=request.getParameter("searchKey");
        if(StringUtil.isNotNull(searchKey)){
            where="mscAuthCategory.lbName like :searchKey";
            hqlObject.setParameter("searchKey","%"+searchKey+"%");
        }
        hqlObject.setWhereExtract(StringUtil.linkString(hqlObject.getWhereExtract()," and ",where));
    }

}
