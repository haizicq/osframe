package com.os.osframe.core.auth.dao.impl;


import com.os.osframe.frame.dao.BaseDaoImpl;
import com.os.osframe.core.auth.dao.IMscAuthRoleDao;
import com.os.osframe.core.auth.domain.MscAuthRole;
import org.springframework.stereotype.Repository;

/**
 * 角色信息
 * Created by wangdc on 2016-04-12 16:42.
 */
@Repository("mscAuthRoleDao")
public class MscAuthRoleDaoImp extends BaseDaoImpl<MscAuthRole> implements IMscAuthRoleDao{
}
