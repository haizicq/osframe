package com.os.osframe.core.auth.service.impl;

import com.os.osframe.core.auth.domain.MscAuthRole;
import com.os.osframe.core.auth.service.IMscAuthRoleService;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.core.users.domain.MscUsersPerson;
import com.os.osframe.core.users.service.IMscUsersPersonService;
import com.os.osframe.core.users.util.MscUsersTypeConstant;
import com.os.osframe.frame.util.AuthConstant;
import com.os.osframe.util.PasswordUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangchun on 16/6/7.
 */

@Repository("mscAuthInitAccount")
public class MscAuthInitAccount implements InitializingBean {

    @Resource
    IMscUsersPersonService mscUsersPersonService;
    @Resource
    IMscAuthRoleService mscAuthRoleService;

    /**
     * 启动应用时导入管理员账号
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        //1、检查数据库中是否存在管理员账号
        MscUsersPerson mscUsersPerson=(MscUsersPerson)mscUsersPersonService.findByUserName("admin");
        if(mscUsersPerson==null){
            //2、当不存在管理员账号时，插入账号
            mscUsersPerson=new MscUsersPerson();
            mscUsersPerson.init();
            mscUsersPerson.setLbLoginName("admin");
            String pwd= PasswordUtil.encrypt(mscUsersPerson.getLbLoginName(), "1", PasswordUtil.getStaticSalt());
            mscUsersPerson.setLbPwd(pwd);
            mscUsersPerson.setLbSex("1");
            MscUsersInfo mscUsersInfo=new MscUsersInfo();
            mscUsersInfo.init();
            mscUsersInfo.setLbName("管理员");
            mscUsersInfo.setLbCreateTime(new Date());
            mscUsersInfo.setLbUpdateTime(new Date());
            mscUsersInfo.setLbIsValid("1");
            mscUsersInfo.setLbIsVirtual("2");
            mscUsersInfo.setLbType(MscUsersTypeConstant.USERS_TYPE_PERSON);
            mscUsersInfo.setLbLevelsId(mscUsersInfo.getPkId());
            mscUsersPerson.setLbUsersInfo(mscUsersInfo);
            mscUsersPersonService.saveOrUpdate(mscUsersPerson);

            //3、同时插入赋权限
            MscAuthRole mscAuthRole=new MscAuthRole();
            mscAuthRole.init();
            mscAuthRole.setLbName("系统管理员");
            mscAuthRole.setLbType(AuthConstant.getRole(AuthConstant.ROLE_ADMIN));
            mscAuthRole.setLbUpdateTime(new Date());
            List userList=new ArrayList();
            userList.add(mscUsersPerson.getLbUsersInfo());
            mscAuthRole.setLbPersonList(userList);
            mscAuthRoleService.save(mscAuthRole);

        }
    }
    public static void main(String[] args) throws  Exception{

        String pwd= PasswordUtil.encrypt("admin", "1", PasswordUtil.getStaticSalt());
        System.out.println("pwd="+pwd);
    }
}
