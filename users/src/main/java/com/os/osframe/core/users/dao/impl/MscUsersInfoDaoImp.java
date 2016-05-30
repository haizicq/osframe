package com.os.osframe.core.users.dao.impl;


import com.os.osframe.frame.dao.BaseDaoImpl;
import com.os.osframe.core.users.dao.IMscUsersInfoDao;
import com.os.osframe.core.users.domain.MscUsersInfo;
import org.springframework.stereotype.Repository;

/**
 * 组织基础信息
 * Created by wangdc on 2016-03-01 23:39.
 */
@Repository("mscUsersInfoDao")
public class MscUsersInfoDaoImp extends BaseDaoImpl<MscUsersInfo> implements IMscUsersInfoDao{
}
