package com.os.osframe.frame.dao;

import com.os.osframe.frame.domain.BaseDomain;
import org.springframework.stereotype.Repository;

/**
 * 通用的保存
 * Created by wangdc on 14-4-19.
 */
@Repository("baseSimpleDao")
public class BaseSimpleDaoImpl extends BaseDaoImpl<BaseDomain> implements IBaseSimpleDao {
}
