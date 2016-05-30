package com.os.osframe.core.users.dao.impl;


import com.os.osframe.frame.dao.BaseDaoImpl;
import com.os.osframe.core.users.dao.IMscUsersGroupJobDao;
import com.os.osframe.core.users.domain.MscUsersGroupJob;
import org.springframework.stereotype.Repository;

/**
 * 群组岗位类型
 * Created by wangdc on 2016-03-01 23:39.
 */
@Repository("mscUsersGroupJobDao")
public class MscUsersGroupJobDaoImp extends BaseDaoImpl<MscUsersGroupJob> implements IMscUsersGroupJobDao{
}
