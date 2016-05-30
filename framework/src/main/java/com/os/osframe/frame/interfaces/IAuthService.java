package com.os.osframe.frame.interfaces;

import com.os.osframe.frame.common.DomainInfo;
import com.os.osframe.frame.common.HqlObject;

import java.util.List;

/**
 * Created by wangchun on 16/4/20.
 */
public interface IAuthService {

    /**
     * 根据用户获取权限角色
     * @param userId
     * @return
     */
    public List findByUser(String userId);

    /**
     * 修改hql语句增加权限模块相应功能
     * @param hqlObject hql语句
     * @param domainInfo 实体模型信息
     * @param clazz  控制器调用类
     * @return
     */
    public void changeAuthHql(HqlObject hqlObject, DomainInfo domainInfo, Class clazz);

    /**
     * 根据类获取控制类，并找到方法的注解
     * @param controllerClassStr
     * @param function
     * @param modelId
     * @return
     * @throws Exception
     */
    public  boolean auth(String controllerClassStr, String function, String modelId);
}
