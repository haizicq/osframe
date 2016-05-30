package com.os.osframe.core.users.service.impl;

import com.os.osframe.frame.common.DomainIDStrategy;
import com.os.osframe.frame.dao.IBaseDao;
import com.os.osframe.frame.service.BaseServiceImpl;
import com.os.osframe.core.users.domain.MscUsersGroupJob;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.core.users.service.IMscUsersGroupJobService;
import com.os.osframe.core.users.service.IMscUsersInfoService;
import com.os.osframe.core.users.util.MscUsersTypeConstant;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 群组岗位类型
 * Created by wangdc on 2016-03-01 23:39.
 */
@Repository("mscUsersGroupJobService")
public class MscUsersGroupJobServiceImp extends BaseServiceImpl<MscUsersGroupJob>  implements IMscUsersGroupJobService{
    @Resource
    IBaseDao mscUsersGroupJobDao;
    public IBaseDao getBaseDao(){
        return mscUsersGroupJobDao;
    }

    @Resource
    IMscUsersInfoService mscUsersInfoService;

    @Override
    public  void saveOrUpdate(MscUsersGroupJob domain) throws Exception {
        //先保存组织基础信息
        MscUsersGroupJob group=(MscUsersGroupJob)domain;
        MscUsersInfo info= group.getLbUsersInfo();
        if(info!=null){
            if(StringUtil.isNull(info.getPkId())){
                info.setPkId(DomainIDStrategy.strategyID());

            }
            if(StringUtil.isNull(info.getLbType())){
                info.setLbType(MscUsersTypeConstant.USERS_TYPE_GROUP_JOB);//默认设置类型为部门类型
            }
            mscUsersInfoService.saveOrUpdate(info);
        }
        super.saveOrUpdate(domain);
    }
}
