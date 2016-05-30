package com.os.osframe.core.auth.service.impl;

import com.os.osframe.frame.dao.IBaseDao;
import com.os.osframe.frame.service.BaseServiceImpl;
import com.os.osframe.core.auth.domain.MscAuthCategory;
import com.os.osframe.core.auth.service.IMscAuthCategoryService;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 角色分组
 * Created by wangdc on 2016-04-12 16:42.
 */
@Repository("mscAuthCategoryService")
public class MscAuthCategoryServiceImp extends BaseServiceImpl<MscAuthCategory> implements IMscAuthCategoryService{
    @Resource
    IBaseDao mscAuthCategoryDao;
    public IBaseDao getBaseDao(){
        return mscAuthCategoryDao;
    }
    @Override
    public  void saveOrUpdate(MscAuthCategory domain, HttpServletRequest request, Model model) throws Exception {
        MscAuthCategory cate=(MscAuthCategory)domain;
        cate.setLbUpdateTime(new Date());//设置更新时间
        super.saveOrUpdate(cate, request, model);

    }
}
