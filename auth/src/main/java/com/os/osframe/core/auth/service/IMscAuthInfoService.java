package com.os.osframe.core.auth.service;

import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.core.auth.domain.MscAuthInfo;

/**
 * 权限信息
 * Created by wangdc on 2016-04-12 16:42.
 */
public interface IMscAuthInfoService extends IBaseService<MscAuthInfo> {

    /**
     * 根据key查找对象
     * @param authKey
     * @return
     * @throws Exception
     */
    public MscAuthInfo findByKey(String authKey) throws Exception;
    /**
     * 扫描包更新权限信息
     */
    public void updateAuthInfo() throws Exception;


}
