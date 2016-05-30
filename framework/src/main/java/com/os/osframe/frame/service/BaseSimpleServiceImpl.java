package com.os.osframe.frame.service;

import com.os.osframe.frame.dao.IBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wangdc on 14-4-17.
 */
@Service("baseSimpleService")
public class BaseSimpleServiceImpl extends BaseServiceImpl implements IBaseSimpleService {
    @Resource
    IBaseDao baseSimpleDao;
    public IBaseDao getBaseDao(){
        return baseSimpleDao;
    }
}
