package com.os.osframe.core.users.service.impl;

import com.os.osframe.frame.common.DomainIDStrategy;
import com.os.osframe.frame.dao.IBaseDao;
import com.os.osframe.frame.service.BaseServiceImpl;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.core.users.domain.MscUsersOrg;
import com.os.osframe.core.users.service.IMscUsersInfoService;
import com.os.osframe.core.users.service.IMscUsersOrgService;
import com.os.osframe.core.users.util.MscUsersTypeConstant;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 部门组织
 * Created by wangdc on 2016-03-01 23:39.
 */
@Repository("mscUsersOrgService")
public class MscUsersOrgServiceImp extends BaseServiceImpl<MscUsersOrg>  implements IMscUsersOrgService{
    @Resource
    IBaseDao mscUsersOrgDao;
    public IBaseDao getBaseDao(){
        return mscUsersOrgDao;
    }
    @Resource
    IMscUsersInfoService mscUsersInfoService;

    @Override
    public  void saveOrUpdate(MscUsersOrg domain) throws Exception {
        //先保存组织基础信息
        MscUsersOrg org=(MscUsersOrg)domain;
        MscUsersInfo info= org.getLbUsersInfo();
        if(info!=null){
            if(StringUtil.isNull(info.getPkId())){
                info.setPkId(DomainIDStrategy.strategyID());

            }
            if(StringUtil.isNull(info.getLbType())){
                info.setLbType(MscUsersTypeConstant.USERS_TYPE_DEPT);//默认设置类型为部门类型
            }
            mscUsersInfoService.saveOrUpdate(info);
        }
        super.saveOrUpdate(domain);
    }
}
