package com.os.osframe.frame.util;

import com.os.osframe.frame.interfaces.IAuthService;
import com.os.osframe.frame.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 通过不强制方式让子模块包去实现，目标提升通用性，
 * 框架中提供了基础的接口，去使用相对通用的功能，
 * 但有可能这些功能项目不需要，也可能会是新的不同模块来实现，
 * 为避免其他需要这些通用功能的模块与这些基础模块强关联而设计
 * Created by wangchun on 16/4/25.
 */

@Service("moduleBeanUtil")
public class ModuleBeanUtil {
    protected static ModuleBeanUtil moduleBean;
    public static ModuleBeanUtil getInstance(){
        if(moduleBean==null){
            moduleBean=(ModuleBeanUtil) SpringUtils.getBean("moduleBeanUtil");
        }
        return moduleBean;
    }
    @Autowired(required=false)
    protected IUserService userService;

    @Autowired(required=false)
    protected IAuthService authService;

    /**
     * 获取注入的人员服务
     * @return
     */
    public IUserService getUserService(){
        return userService;
    }

    /**
     * 获取注入的权限服务
     * @return
     */
    public IAuthService getAuthService() {
        return authService;
    }
}
