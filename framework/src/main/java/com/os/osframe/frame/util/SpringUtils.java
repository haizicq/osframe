
package com.os.osframe.frame.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
* Spring 工具类
 *  通过实现ApplicationContextAware实现初始化
*Created by wangdc on 14-4-16.
*/

@Service("springUtils")
public class SpringUtils implements ApplicationContextAware  {
	
	
	private static ApplicationContext context;
    public static ApplicationContext getApplicationContext() {
        return context;
    }
    /**
	 * 
	 */
	public SpringUtils() {
	}
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context=context;
	}
	/**
	 * 根据beanName 获取 spring bean
	 * @param beanName
	 * @return Object
	 */
	public static  Object getBean(String beanName){
		if(beanName==null)return null;
	    return 	context.getBean(beanName);
	}

    /**
     * 获取资源文件信息
     * @param message
     * @param local
     * @return
     */
    public static  String getMessage(String message,Locale local){
        String str=context.getMessage(message,null,local);//"website.title"
        return str;
    }

    /**
     * 获取资源文件信息
     * @param message
     * @return
     */
    public static  String getMessage(String message){
        return getMessage(message,null);
    }

    /**
     * 是否存在bean
     * @param bean
     * @return
     * @throws Exception
     */
    public static Boolean isExist(String bean) {

        if (!context.containsBean(bean)) {
            return false;
        }
        return true;
    }

}
