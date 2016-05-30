package com.os.osframe.core.users.service.impl;

import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.dao.IBaseDao;
import com.os.osframe.frame.service.BaseServiceImpl;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.core.users.service.IMscUsersInfoService;
import com.os.osframe.core.users.util.MscUsersTypeConstant;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 组织基础信息
 * Created by wangdc on 2016-03-01 23:39.
 */
@Repository("mscUsersInfoService")
public class MscUsersInfoServiceImp extends BaseServiceImpl<MscUsersInfo> implements IMscUsersInfoService{
    @Resource
    IBaseDao mscUsersInfoDao;
    public IBaseDao getBaseDao(){
        return mscUsersInfoDao;
    }

    public  void saveOrUpdate(MscUsersInfo domain) throws Exception {
        MscUsersInfo mscUsersInfo=(MscUsersInfo)domain;
        Date now=new Date();
        if(mscUsersInfo.getLbCreateTime()==null){//设置创建时间,当已经赋值则无需赋值
            mscUsersInfo.setLbCreateTime(now);
        }
        mscUsersInfo.setLbUpdateTime(now);//每次重新设置
        super.saveOrUpdate(mscUsersInfo);
    }

    /**
     * 根据id获取子集部门列表
     * @param id
     * @return
     * @throws Exception
     */
    public List<MscUsersInfo> getChildDeptList(String id)throws Exception {
        //查找当前层级的所有部门或机构
        HqlObject hqlObject=new HqlObject();
        String where=" (mscUsersInfo.lbType =:lbType1 or mscUsersInfo.lbType =:lbType2)";
        if(StringUtil.isNull(id)){
            where+=" and mscUsersInfo.lbParent.pkId is null";
        }else{
            where+=" and mscUsersInfo.lbParent.pkId =:pid";
            hqlObject.setParameter("pid",id);
        }
        hqlObject.setParameter("lbType1", MscUsersTypeConstant.USERS_TYPE_ORG);
        hqlObject.setParameter("lbType2", MscUsersTypeConstant.USERS_TYPE_DEPT);
        hqlObject.setOrderByExtract(" mscUsersInfo.lbType asc,mscUsersInfo.lbOrder asc,mscUsersInfo.lbCreateTime desc");
        hqlObject.setWhereExtract(where);
        List<MscUsersInfo> list=this.find(hqlObject);
        return list;
    }

    /**
     * 根据id获取子集人员和岗位
     * @param id 上级部门id
     * @param type 组织类型
     * @return
     * @throws Exception
     */
    public List<MscUsersInfo> getChildUsers(String id,String type)throws Exception {
        //查找当前层级的所有部门或机构
        HqlObject hqlObject=new HqlObject();
        String where=" (mscUsersInfo.lbType =:lbType1 or mscUsersInfo.lbType =:lbType2)";
        if(StringUtil.isNull(id)){
            where+=" and mscUsersInfo.lbParent.pkId is null";
        }else{
            where+=" and mscUsersInfo.lbParent.pkId =:pid";
            hqlObject.setParameter("pid",id);
        }
        if(StringUtil.isNull(type)){
            hqlObject.setParameter("lbType1", MscUsersTypeConstant.USERS_TYPE_PERSON);
            hqlObject.setParameter("lbType2", MscUsersTypeConstant.USERS_TYPE_JOB);
        }else{
            hqlObject.setParameter("lbType1", type);
        }

        hqlObject.setOrderByExtract("  mscUsersInfo.lbType asc,mscUsersInfo.lbOrder asc,mscUsersInfo.lbCreateTime desc");
        hqlObject.setWhereExtract(where);
        List<MscUsersInfo> list=this.find(hqlObject);
        //this.findPageInfo(hqlObject);
        return list;
    }
}
