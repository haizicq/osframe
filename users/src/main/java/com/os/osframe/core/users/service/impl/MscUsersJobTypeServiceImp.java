package com.os.osframe.core.users.service.impl;

import com.os.osframe.frame.dao.IBaseDao;
import com.os.osframe.frame.service.BaseServiceImpl;
import com.os.osframe.core.users.domain.MscUsersJobType;
import com.os.osframe.core.users.service.IMscUsersJobTypeService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 岗位类型
 * Created by wangdc on 2016-03-01 23:39.
 */
@Repository("mscUsersJobTypeService")
public class MscUsersJobTypeServiceImp extends BaseServiceImpl<MscUsersJobType> implements IMscUsersJobTypeService{
    @Resource
    IBaseDao mscUsersJobTypeDao;
    public IBaseDao getBaseDao(){
        return mscUsersJobTypeDao;
    }
}
