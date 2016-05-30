package com.os.osframe.core.auth.dao.impl;


import com.os.osframe.frame.dao.BaseDaoImpl;
import com.os.osframe.core.auth.dao.IMscAuthDataDao;
import com.os.osframe.core.auth.domain.MscAuthData;
import org.springframework.stereotype.Repository;

/**
 * 数据权限
 * Created by wangdc on 2016-04-12 16:42.
 */
@Repository("mscAuthDataDao")
public class MscAuthDataDaoImp extends BaseDaoImpl<MscAuthData> implements IMscAuthDataDao{
}
