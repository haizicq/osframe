package com.os.osframe.core.users.dao.impl;


import com.os.osframe.frame.dao.BaseDaoImpl;
import com.os.osframe.core.users.dao.IMscUsersJobDao;
import com.os.osframe.core.users.domain.MscUsersJob;
import org.springframework.stereotype.Repository;

/**
 * 岗位信息
 * Created by wangdc on 2016-03-01 23:39.
 */
@Repository("mscUsersJobDao")
public class MscUsersJobDaoImp extends BaseDaoImpl<MscUsersJob> implements IMscUsersJobDao{
}
