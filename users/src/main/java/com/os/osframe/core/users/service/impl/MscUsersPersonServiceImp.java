package com.os.osframe.core.users.service.impl;

import com.os.osframe.frame.common.DomainIDStrategy;
import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.dao.IBaseDao;
import com.os.osframe.frame.interfaces.IUserModel;
import com.os.osframe.frame.interfaces.IUserService;
import com.os.osframe.frame.service.BaseServiceImpl;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.core.users.domain.MscUsersPerson;
import com.os.osframe.core.users.service.IMscUsersInfoService;
import com.os.osframe.core.users.service.IMscUsersPersonService;
import com.os.osframe.core.users.util.MscUsersTypeConstant;
import com.os.osframe.util.PasswordUtil;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 人员信息
 * Created by wangdc on 2016-03-01 23:39.
 */
@Repository("mscUsersPersonService")
public class MscUsersPersonServiceImp extends BaseServiceImpl<MscUsersPerson> implements IMscUsersPersonService,IUserService {
    @Resource
    IBaseDao mscUsersPersonDao;
    public IBaseDao getBaseDao(){
        return mscUsersPersonDao;
    }
    @Resource
    IMscUsersInfoService mscUsersInfoService;

    @Override
    public  void saveOrUpdate(MscUsersPerson domain) throws Exception {
        //先保存组织基础信息
        MscUsersPerson person=(MscUsersPerson)domain;
        MscUsersInfo info= person.getLbUsersInfo();
        if(info!=null){
            if(StringUtil.isNull(info.getPkId())){
                info.setPkId(DomainIDStrategy.strategyID());
            }
            if(StringUtil.isNull(info.getLbType())){
                info.setLbType(MscUsersTypeConstant.USERS_TYPE_PERSON);//设置类型为人员类型
                //当他为空的时候，代表首次加入，在此时来设置加密密码
                if(StringUtil.isNotNull(person.getLbLoginName()) && StringUtil.isNotNull(person.getPwd())){//当不为空时
                    String pwd= PasswordUtil.encrypt(person.getLbLoginName(), person.getPwd(), PasswordUtil.getStaticSalt());
                    person.setLbPwd(pwd);
                }else{
                    person.setLbPwd(null);
                }

            }
            mscUsersInfoService.saveOrUpdate(info);
        }
        super.saveOrUpdate(domain);
    }

    public IUserModel findByUserName(String userName) {
        if(StringUtil.isNull(userName)){
            return null;
        }
        try{
            HqlObject hqlObject=new HqlObject();
            hqlObject.setWhereExtract(" mscUsersPerson.lbLoginName=:userName");
            hqlObject.setParameter("userName",userName);
            List persons=this.find(hqlObject);
            if(persons!=null && !persons.isEmpty()){
                MscUsersPerson person=(MscUsersPerson)persons.get(0);
                return person;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public IUserModel findByUserId(String userId) {
        try{
            HqlObject hqlObject=new HqlObject();
            hqlObject.setWhereExtract(" mscUsersPerson.lbUsersInfo.pkId=:userId");
            hqlObject.setParameter("userId",userId);
            List persons=this.find(hqlObject);
            if(persons!=null && !persons.isEmpty()){
                MscUsersPerson person=(MscUsersPerson)persons.get(0);
                return person;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 重置密码
     * @param user  用户对象
     * @param newPwd 新密码
     * @param rePwd 重复密码
     */
    public void updateChangePwd(MscUsersPerson user,String newPwd,String rePwd) throws Exception{
        if(user==null || StringUtil.isNull(newPwd) || StringUtil.isNull(rePwd) || !newPwd.trim().equals(rePwd.trim())){
            return;
        }
        String pwd= PasswordUtil.encrypt(user.getLbLoginName(), newPwd.trim(), PasswordUtil.getStaticSalt());
        user.setLbPwd(pwd);
        this.saveOrUpdate(user);
    }
}
