package com.os.osframe.core.auth.web;

import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.common.RequiresClassAuth;
import com.os.osframe.frame.web.BaseIndexController;
import com.os.osframe.core.auth.service.IMscAuthCategoryService;
import com.os.osframe.core.auth.service.IMscAuthDataService;
import com.os.osframe.core.auth.service.IMscAuthInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 首页入口
 * Created by wangdc on 2016-04-12 16:42.
 */
@Controller
@RequestMapping(value = "/core/auth")
@RequiresClassAuth(common= RequiresClassAuth.CommonType.CONTROL)
public class mscAuthIndexController extends BaseIndexController {

    protected Class getSubClass(){
        return mscAuthIndexController.class;
    }
    @Resource
    IMscAuthCategoryService mscAuthCategoryService;
    @Resource
    IMscAuthInfoService mscAuthInfoService;
    @Resource
    IMscAuthDataService mscAuthDataService;

    @Override
    public String nav(HttpServletRequest request) throws Exception {
        //获取分组的角色,由于角色分组原则上不会太多，所以直接查询全部
        HqlObject hqlObject=new HqlObject();
        hqlObject.setWhereExtract("1=1");
        List list=mscAuthCategoryService.find(hqlObject);
        request.setAttribute("mscAuthCategoryList", list);
        //获取权限模块列表
        HqlObject hqlAuth=new HqlObject();
        //hqlAuth.setDistinctType(HqlObject.NOT_REPEAT);//去重
//        hqlAuth.setSelectExtract("mscAuthInfo.lbModuleKey as lbModuleKey,mscAuthInfo.lbModule as lbModule");
//        hqlAuth.setIsReMap(true);//设置返回类型为map
        hqlAuth.setSelectExtract("distinct new Map( mscAuthInfo.lbModuleKey as lbModuleKey,mscAuthInfo.lbModule as lbModule)");
        hqlAuth.setWhereExtract("1=1 ");

        List authModuleList=mscAuthInfoService.find(hqlAuth);
        request.setAttribute("authModuleList", authModuleList);
        return super.nav(request);
    }
}
