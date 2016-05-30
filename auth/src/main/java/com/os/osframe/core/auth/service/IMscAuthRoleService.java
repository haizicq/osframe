package com.os.osframe.core.auth.service;

import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.core.auth.domain.MscAuthRole;

/**
 * 角色信息
 * Created by wangdc on 2016-04-12 16:42.
 */
public interface IMscAuthRoleService extends IBaseService<MscAuthRole> {

    /**
     * 初始化管理员账号
     * @return 是否初始化成功
     * @throws Exception
     */
    public int initAccount() throws  Exception;
}
