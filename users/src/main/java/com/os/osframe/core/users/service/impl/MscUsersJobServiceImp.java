package com.os.osframe.core.users.service.impl;

import com.os.osframe.frame.common.DomainIDStrategy;
import com.os.osframe.frame.dao.IBaseDao;
import com.os.osframe.frame.service.BaseServiceImpl;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.core.users.domain.MscUsersJob;
import com.os.osframe.core.users.service.IMscUsersInfoService;
import com.os.osframe.core.users.service.IMscUsersJobService;
import com.os.osframe.core.users.util.MscUsersTypeConstant;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 岗位信息
 * Created by wangdc on 2016-03-01 23:39.
 */
@Repository("mscUsersJobService")
public class MscUsersJobServiceImp extends BaseServiceImpl<MscUsersJob> implements IMscUsersJobService{
    @Resource
    IBaseDao mscUsersJobDao;
    public IBaseDao getBaseDao(){
        return mscUsersJobDao;
    }

    @Resource
    IMscUsersInfoService mscUsersInfoService;

    @Override
    public  void saveOrUpdate(MscUsersJob domain) throws Exception {
        //先保存组织基础信息
        MscUsersJob job=(MscUsersJob)domain;
        MscUsersInfo info= job.getLbUsersInfo();
        if(info!=null){
            if(StringUtil.isNull(info.getPkId())){
                info.setPkId(DomainIDStrategy.strategyID());

            }
            if(StringUtil.isNull(info.getLbType())){
                info.setLbType(MscUsersTypeConstant.USERS_TYPE_JOB);//默认设置类型为部门类型
            }
            mscUsersInfoService.saveOrUpdate(info);
        }
        super.saveOrUpdate(domain);
    }
}
