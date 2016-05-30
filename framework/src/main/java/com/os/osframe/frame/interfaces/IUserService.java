package com.os.osframe.frame.interfaces;

/**
 * Created by wangchun on 16/4/16.
 */
public interface IUserService {
    /**
     * 根据用户名查找对象
     * @param userName
     * @return
     */
    public IUserModel findByUserName(String userName);
    /**
     * 根据ID查找对象
     * @param id
     * @return
     */
    public IUserModel findByUserId(String id);
}
