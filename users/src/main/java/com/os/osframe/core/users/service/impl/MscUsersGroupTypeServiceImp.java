package com.os.osframe.core.users.service.impl;

import com.os.osframe.frame.dao.IBaseDao;
import com.os.osframe.frame.service.BaseServiceImpl;
import com.os.osframe.core.users.domain.MscUsersGroupType;
import com.os.osframe.core.users.service.IMscUsersGroupTypeService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 群组类型
 * Created by wangdc on 2016-03-01 23:39.
 */
@Repository("mscUsersGroupTypeService")
public class MscUsersGroupTypeServiceImp extends BaseServiceImpl<MscUsersGroupType> implements IMscUsersGroupTypeService{
    @Resource
    IBaseDao mscUsersGroupTypeDao;
    public IBaseDao getBaseDao(){
        return mscUsersGroupTypeDao;
    }
}
