package com.os.osframe.core.users.dao.impl;


import com.os.osframe.frame.dao.BaseDaoImpl;
import com.os.osframe.core.users.dao.IMscUsersGroupTypeDao;
import com.os.osframe.core.users.domain.MscUsersGroupType;
import org.springframework.stereotype.Repository;

/**
 * 群组类型
 * Created by wangdc on 2016-03-01 23:39.
 */
@Repository("mscUsersGroupTypeDao")
public class MscUsersGroupTypeDaoImp extends BaseDaoImpl<MscUsersGroupType> implements IMscUsersGroupTypeDao{
}
