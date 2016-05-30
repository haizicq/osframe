package com.os.osframe.os.demo.service.impl;

import com.os.osframe.frame.dao.IBaseDao;
import com.os.osframe.frame.service.BaseServiceImpl;
import com.os.osframe.os.demo.domain.OsDemoInfo;
import com.os.osframe.os.demo.service.IOsDemoInfoService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 权限信息
 * Created by wangdc on 2016-06-06 23:06.
 */
@Repository("osDemoInfoService")
public class OsDemoInfoServiceImp extends BaseServiceImpl<OsDemoInfo> implements IOsDemoInfoService{
    @Resource
    IBaseDao osDemoInfoDao;
    public IBaseDao getBaseDao(){
        return osDemoInfoDao;
    }
}
