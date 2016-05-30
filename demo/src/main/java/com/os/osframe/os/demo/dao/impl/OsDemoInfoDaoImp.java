package com.os.osframe.os.demo.dao.impl;


import com.os.osframe.frame.dao.BaseDaoImpl;
import com.os.osframe.os.demo.dao.IOsDemoInfoDao;
import com.os.osframe.os.demo.domain.OsDemoInfo;
import org.springframework.stereotype.Repository;

/**
 * 权限信息
 * Created by wangdc on 2016-06-06 23:06.
 */
@Repository("osDemoInfoDao")
public class OsDemoInfoDaoImp extends BaseDaoImpl<OsDemoInfo> implements IOsDemoInfoDao{
}
