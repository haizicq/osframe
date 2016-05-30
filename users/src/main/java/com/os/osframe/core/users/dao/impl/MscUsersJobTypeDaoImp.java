package com.os.osframe.core.users.dao.impl;


import com.os.osframe.frame.dao.BaseDaoImpl;
import com.os.osframe.core.users.dao.IMscUsersJobTypeDao;
import com.os.osframe.core.users.domain.MscUsersJobType;
import org.springframework.stereotype.Repository;

/**
 * 岗位类型
 * Created by wangdc on 2016-03-01 23:39.
 */
@Repository("mscUsersJobTypeDao")
public class MscUsersJobTypeDaoImp extends BaseDaoImpl<MscUsersJobType> implements IMscUsersJobTypeDao{
}
