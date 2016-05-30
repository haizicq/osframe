package com.os.osframe.frame.loader;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * 在spring中配置的属性文件，便于后面使用时获取
 * Created by wangdc on 2015-5-22.
 */
public class PropertyConfigurerLoader extends PropertyPlaceholderConfigurer implements IPropertyConfig {
    private Properties properties=null;
    protected void processProperties( ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        // cache the properties
        properties=props;
        super.processProperties(beanFactoryToProcess, props);
    }

    /**
     * 获取整个配置
     * @return
     */
    public Properties getProperties() {
        return properties;
    }
    /**
     * 根据key获取属性
     * @param key
     * @return
     */
    public Object getProperty(String key){
        return properties.get(key);
    }
}
