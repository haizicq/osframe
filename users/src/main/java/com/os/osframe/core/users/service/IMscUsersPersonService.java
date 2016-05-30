package com.os.osframe.core.users.service;

import com.os.osframe.frame.interfaces.IUserModel;
import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.core.users.domain.MscUsersPerson;

/**
 * 人员信息
 * Created by wangdc on 2016-03-01 23:39.
 */
public interface IMscUsersPersonService extends IBaseService<MscUsersPerson> {

    /**
     * 重置密码
     * @param user  用户对象
     * @param newPwd 新密码
     * @param rePwd 重复密码
     */
    public void updateChangePwd(MscUsersPerson user, String newPwd, String rePwd) throws Exception;

    /**
     * 根据用户名来获取
     * @param userName
     * @return
     */
    public IUserModel findByUserName(String userName);
}
