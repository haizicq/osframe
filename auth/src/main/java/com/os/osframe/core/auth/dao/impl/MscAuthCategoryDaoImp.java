package com.os.osframe.core.auth.dao.impl;


import com.os.osframe.frame.dao.BaseDaoImpl;
import com.os.osframe.core.auth.dao.IMscAuthCategoryDao;
import com.os.osframe.core.auth.domain.MscAuthCategory;
import org.springframework.stereotype.Repository;

/**
 * 角色分组
 * Created by wangdc on 2016-04-12 16:42.
 */
@Repository("mscAuthCategoryDao")
public class MscAuthCategoryDaoImp extends BaseDaoImpl<MscAuthCategory> implements IMscAuthCategoryDao{
}
