package com.os.osframe.core.users.service;

import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.core.users.domain.MscUsersInfo;

import java.util.List;

/**
 * 组织基础信息
 * Created by wangdc on 2016-03-01 23:39.
 */
public interface IMscUsersInfoService extends IBaseService<MscUsersInfo> {

    /**
     * 根据id获取子集部门列表
     * @param id
     * @return
     * @throws Exception
     */
    public List<MscUsersInfo> getChildDeptList(String id)throws Exception;

    /**
     * 根据id获取子集人员和岗位
     * @param id 上级部门id
     * @param type 组织类型
     * @return
     * @throws Exception
     */
    public List<MscUsersInfo> getChildUsers(String id, String type)throws Exception;
}
