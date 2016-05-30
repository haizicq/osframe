package com.os.osframe.core.auth.web;

import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.common.RequiresClassAuth;
import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.frame.web.BaseController;
import com.os.osframe.core.auth.domain.MscAuthData;
import com.os.osframe.core.auth.service.IMscAuthDataService;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 数据权限
 * Created by wangdc on 2016-04-12 16:42.
 */
@Controller
@RequestMapping(value = "/core/auth/mscAuthData")
@RequiresClassAuth(common= RequiresClassAuth.CommonType.CONTROL)
public class MscAuthDataController extends BaseController<MscAuthData> {
    @Resource
    IMscAuthDataService mscAuthDataService;
    @Override
    protected IBaseService getServiceImp() {
        return mscAuthDataService;
    }

    @Override
    protected void changeSearchHqlObject(HttpServletRequest request, HqlObject hqlObject) throws Exception {
        super.changeSearchHqlObject(request, hqlObject);
        String where="";
        String searchKey=request.getParameter("searchKey");
        if(StringUtil.isNotNull(searchKey)){
            where="mscAuthData.lbName like :searchKey";
            hqlObject.setParameter("searchKey","%"+searchKey+"%");
        }
        hqlObject.setWhereExtract(StringUtil.linkString(hqlObject.getWhereExtract()," and ",where));
    }

}
