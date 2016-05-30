package com.os.osframe.frame.support;

import java.util.List;

/**
 * 支撑类处理接口
 *  定义的通过用的接口，包含保存/更新、读取
 * Created by wangchun on 16/5/16.
 */
public interface ISupportService {
    /**
     * 保存对象
     * @param supportDomain 提供支撑对象
     * @param id 主键
     * @param clazz 支撑类对象
     */
    public void saveSupport(SupportDomain supportDomain, String id, String clazz) throws Exception;

    /**
     * 读取支撑对象
     * @param id 实体id
     * @param type 类型
     * @param keyword 关键字
     * @return
     */
    public List<SupportDomain> readSupport(String id, String type, String keyword) throws Exception;

}
