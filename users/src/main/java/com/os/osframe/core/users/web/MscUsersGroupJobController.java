package com.os.osframe.core.users.web;

import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.common.RequiresClassAuth;
import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.frame.web.BaseController;
import com.os.osframe.core.users.domain.MscUsersGroupJob;
import com.os.osframe.core.users.service.IMscUsersGroupJobService;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 群组岗位类型
 * Created by wangdc on 2016-03-01 23:39.
 */
@Controller
@RequestMapping(value = "/core/users/mscUsersGroupJob")
@RequiresClassAuth(common= RequiresClassAuth.CommonType.CONTROL)
public class MscUsersGroupJobController extends BaseController<MscUsersGroupJob> {
    @Resource
    IMscUsersGroupJobService mscUsersGroupJobService;
    @Override
    protected IBaseService getServiceImp() {
        return mscUsersGroupJobService;
    }

    @Override
    protected void changeSearchHqlObject(HttpServletRequest request, HqlObject hqlObject) throws Exception {
        super.changeSearchHqlObject(request, hqlObject);
        String where="";
        String searchKey=request.getParameter("searchKey");
        if(StringUtil.isNotNull(searchKey)){
            where="mscUsersGroupJob.lbName like :searchKey";
            hqlObject.setParameter("searchKey","%"+searchKey+"%");
        }
        hqlObject.setWhereExtract(StringUtil.linkString(hqlObject.getWhereExtract()," and ",where));
    }
}
