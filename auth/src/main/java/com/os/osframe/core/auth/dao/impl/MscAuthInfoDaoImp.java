package com.os.osframe.core.auth.dao.impl;


import com.os.osframe.frame.dao.BaseDaoImpl;
import com.os.osframe.core.auth.dao.IMscAuthInfoDao;
import com.os.osframe.core.auth.domain.MscAuthInfo;
import org.springframework.stereotype.Repository;

/**
 * 权限信息
 * Created by wangdc on 2016-04-12 16:42.
 */
@Repository("mscAuthInfoDao")
public class MscAuthInfoDaoImp extends BaseDaoImpl<MscAuthInfo> implements IMscAuthInfoDao{
}
