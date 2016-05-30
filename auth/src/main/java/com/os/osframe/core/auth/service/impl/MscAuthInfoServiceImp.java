package com.os.osframe.core.auth.service.impl;

import com.os.osframe.core.users.service.IMscUsersPersonService;
import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.dao.IBaseDao;
import com.os.osframe.frame.service.BaseServiceImpl;
import com.os.osframe.core.auth.domain.MscAuthInfo;
import com.os.osframe.core.auth.service.IMscAuthDataService;
import com.os.osframe.core.auth.service.IMscAuthInfoService;
import com.os.osframe.core.auth.util.ScanAnnotationUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 权限信息
 * Created by wangdc on 2016-04-12 16:42.
 */
@Repository("mscAuthInfoService")
public class MscAuthInfoServiceImp extends BaseServiceImpl<MscAuthInfo> implements IMscAuthInfoService{
    @Resource
    IBaseDao mscAuthInfoDao;
    public IBaseDao getBaseDao(){
        return mscAuthInfoDao;
    }
    @Resource
    IMscAuthDataService mscAuthDataService;
    /**
     * 根据key查找对象
     * @param authKey
     * @return
     * @throws Exception
     */
    public MscAuthInfo findByKey(String authKey) throws Exception{
        HqlObject hqlObject=new HqlObject();
        hqlObject.setWhereExtract(" mscAuthInfo.lbAuthKey=:lbAuthKey");
        hqlObject.setParameter("lbAuthKey",authKey);
        hqlObject.setOrderByExtract("mscAuthInfo.lbUpdateTime asc");
        List list=this.find(hqlObject);
        if(list!=null && !list.isEmpty()){
            return (MscAuthInfo)list.get(0);
        }
        return null;
    }
    /**
     * 扫描包更新权限信息
     */
    public void updateAuthInfo() throws Exception{
        try {
            //扫描包路径
            List<String> roles = ScanAnnotationUtil.sanRoles("com.os.osframe.**.web");
            if (roles != null && !roles.isEmpty()) {
                for (String role : roles) {
                    String[] roleArr = role.split(":");//跟冒号拆分
                    if (roleArr.length < 2) {//单小于2，则说明不是按此规则的权限
                        continue;
                    }
                    MscAuthInfo mscAuthInfo =this.findByKey(roleArr[1]);
                    if (mscAuthInfo != null) {//当存在则不更新
                        continue;
                    }
                    mscAuthInfo = new MscAuthInfo();
                    mscAuthInfo.init();
                    mscAuthInfo.setLbName(roleArr[1] + ".name");
                    mscAuthInfo.setLbAuthKey(roleArr[1]);
                    mscAuthInfo.setLbUpdateTime(new Date());
                    mscAuthInfo.setLbInfo(roleArr[1] + ".info");
                    mscAuthInfo.setLbModuleKey(roleArr[0]);
                    //获取模块名称 module.msc_auth=权限模块
                    //mscAuthInfo.setLbModule(ResourceUtil.getResourceKey("module."+roleArr[0]));
                    mscAuthInfo.setLbModule("module." + roleArr[0]);
                    //XXX 为什么这样保存不成功，而直接新建保存时可以成功
                    this.save(mscAuthInfo);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
