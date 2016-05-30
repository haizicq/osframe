package com.os.osframe.frame.loader;

import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by wangdc on 14-4-16.
 */
public class ServletLoader extends DispatcherServlet {
    /**
     * 初始化直接注入
     */
    public ServletLoader(){
        setContextConfigLocation("classpath*:com/os/osframe/**/*-servlet.xml");
    }
}