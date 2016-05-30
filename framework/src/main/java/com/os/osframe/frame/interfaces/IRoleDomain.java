package com.os.osframe.frame.interfaces;

import java.util.List;

/**
 * 角色信息接口
 * Created by wangchun on 16/6/6.
 */
public interface IRoleDomain {
    /**
     *类型
     */
    public String getType();

    /**
     * 权限列表
     * @return
     */
    public List getAuthList();
}
