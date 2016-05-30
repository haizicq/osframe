package com.os.osframe.frame.loader;

import java.util.Properties;

/**
 * Created by wangdc on 2015-5-22.
 */
public interface IPropertyConfig {
    /**
     * 获取整个配置
     * @return
     */
    public Properties getProperties();
    /**
     * 根据key获取属性
     * @param key
     * @return
     */
    public Object getProperty(String key);
}
