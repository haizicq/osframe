package com.os.osframe.frame.loader;

import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * spring加载器
 * Created by wangdc on 14-4-16.
 */
public class SpringLoader  extends XmlWebApplicationContext {
    protected String[] getDefaultConfigLocations() {
        String[] beansConfig={"classpath*:com/os/osframe/**/*-beans.xml"};//设置正则表达式
        return beansConfig;
    }

}
