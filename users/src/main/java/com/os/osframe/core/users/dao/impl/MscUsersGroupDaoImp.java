package com.os.osframe.core.users.dao.impl;


import com.os.osframe.frame.dao.BaseDaoImpl;
import com.os.osframe.core.users.dao.IMscUsersGroupDao;
import com.os.osframe.core.users.domain.MscUsersGroup;
import org.springframework.stereotype.Repository;

/**
 * 群组组织
 * Created by wangdc on 2016-03-01 23:39.
 */
@Repository("mscUsersGroupDao")
public class MscUsersGroupDaoImp extends BaseDaoImpl<MscUsersGroup> implements IMscUsersGroupDao{
}
