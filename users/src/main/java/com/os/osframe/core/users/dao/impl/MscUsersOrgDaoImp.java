package com.os.osframe.core.users.dao.impl;


import com.os.osframe.frame.dao.BaseDaoImpl;
import com.os.osframe.core.users.dao.IMscUsersOrgDao;
import com.os.osframe.core.users.domain.MscUsersOrg;
import org.springframework.stereotype.Repository;

/**
 * 部门组织
 * Created by wangdc on 2016-03-01 23:39.
 */
@Repository("mscUsersOrgDao")
public class MscUsersOrgDaoImp extends BaseDaoImpl<MscUsersOrg> implements IMscUsersOrgDao{
}
