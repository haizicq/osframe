package com.os.osframe.core.auth.service.impl;

import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.core.users.domain.MscUsersPerson;
import com.os.osframe.core.users.service.IMscUsersPersonService;
import com.os.osframe.core.users.util.MscUsersTypeConstant;
import com.os.osframe.frame.dao.IBaseDao;
import com.os.osframe.frame.service.BaseServiceImpl;
import com.os.osframe.core.auth.domain.MscAuthRole;
import com.os.osframe.core.auth.service.IMscAuthRoleService;
import com.os.osframe.frame.util.AuthConstant;
import com.os.osframe.util.PasswordUtil;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色信息
 * Created by wangdc on 2016-04-12 16:42.
 */
@Repository("mscAuthRoleService")
public class MscAuthRoleServiceImp extends BaseServiceImpl<MscAuthRole> implements IMscAuthRoleService {
    @Resource
    IBaseDao mscAuthRoleDao;
    public IBaseDao getBaseDao(){
        return mscAuthRoleDao;
    }

    @Resource
    IMscUsersPersonService mscUsersPersonService;

    @Override
    public  void saveOrUpdate(MscAuthRole domain, HttpServletRequest request, Model model) throws Exception {
        MscAuthRole role=(MscAuthRole)domain;
        role.setLbUpdateTime(new Date());//设置更新时间
        super.saveOrUpdate(role, request, model);

    }

    /**
     * 初始化管理员账号
     * @return 是否初始化成功
     * @throws Exception
     */
    public int initAccount() throws  Exception{
        //1、检查数据库中是否存在管理员账号
        MscUsersPerson mscUsersPerson=(MscUsersPerson)mscUsersPersonService.findByUserName("admin");
        if(mscUsersPerson==null){
            //2、当不存在管理员账号时，插入账号
            mscUsersPerson=new MscUsersPerson();
            mscUsersPerson.init();
            mscUsersPerson.setLbLoginName("admin");
            String pwd= PasswordUtil.encrypt(mscUsersPerson.getLbLoginName(), "1", PasswordUtil.getSalt());
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
            mscUsersPersonService.save(mscUsersPerson);

            //3、同时插入赋权限
            MscAuthRole mscAuthRole=new MscAuthRole();
            mscAuthRole.init();
            mscAuthRole.setLbName("系统管理员");
            mscAuthRole.setLbType(AuthConstant.ROLE_ADMIN);
            mscAuthRole.setLbUpdateTime(new Date());
            List userList=new ArrayList();
            userList.add(mscUsersPerson.getLbUsersInfo());
            mscAuthRole.setLbPersonList(userList);
            this.save(mscAuthRole);

            return 1;
        }
        return 0;
    }

}
