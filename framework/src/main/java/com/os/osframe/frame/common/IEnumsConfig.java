package com.os.osframe.frame.common;

import java.util.List;

/**
 * Created by wangdc on 2015-5-22.
 */
public interface IEnumsConfig {
    /**
     * 根据key获取枚举列表
     * @param key
     * @return
     */
    public List getEnums(String key);
    /**
     * 根据key获取具体值
     * @param key
     * @return
     */
    public String get(String key);

}
