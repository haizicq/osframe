package com.os.osframe.core.users.dao.impl;


import com.os.osframe.frame.dao.BaseDaoImpl;
import com.os.osframe.core.users.dao.IMscUsersPersonDao;
import com.os.osframe.core.users.domain.MscUsersPerson;
import org.springframework.stereotype.Repository;

/**
 * 人员信息
 * Created by wangdc on 2016-03-01 23:39.
 */
@Repository("mscUsersPersonDao")
public class MscUsersPersonDaoImp extends BaseDaoImpl<MscUsersPerson> implements IMscUsersPersonDao{
}
