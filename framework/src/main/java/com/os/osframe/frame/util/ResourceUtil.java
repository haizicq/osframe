package com.os.osframe.frame.util;

import com.os.osframe.frame.loader.IPropertyConfig;
import com.os.osframe.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 获取资源文件相关配置
 * 依赖于SpringUtil
 *
 */
@Component
public class ResourceUtil {
    @Autowired
    private static ResourceBundleMessageSource rms;
    public ResourceUtil() {
	}
	/**
	 * 根据key获得对应的value
	 * 
	 * @param key
	 *            key
	 * @return String
	 */
	public static String getString(String key) {
		try {
			return SpringUtils.getMessage(key);
		} catch (Exception e) {
			return key;
		}
	}

    /**
     * 获取资源文件并替换其中的字符串
     * @param key
     * @param args
     * @return
     */
    public static String getString( String key,Object[] args) {
        String rtnVal = getString(key);
        for (int i = 0; i < args.length; i++)
            rtnVal = StringUtil.replace(rtnVal, "{" + i + "}",
                    String.valueOf(args[i]));
        return rtnVal;
    }

    protected static IPropertyConfig propertyConfigurer;

    /**
     * 获取配置的对象
     * @return
     */
    protected static IPropertyConfig getPropertyConfigurerLoader() {
        if(propertyConfigurer==null){
            propertyConfigurer=(IPropertyConfig) SpringUtils.getBean("propertyConfigurer");
        }
        return propertyConfigurer;
    }

    /**
     * 获取整个配置文件
     * @return
     */
    public static Properties getProperties(){
        return getPropertyConfigurerLoader().getProperties();
    }
    /**
     * 根据key获取属性
     * @param key
     * @return
     */
    public static Object getProperty(String key){
        return getPropertyConfigurerLoader().getProperty(key);
    }

    /**
     * 获取CDN地址
     * @return
     */
    public static String getCdnUrl(){
        return (String)getProperty("osframe.cdn.url");
    }
    /**
     * 获取国际化资源key
     * @param pageContext 页面上下文
     * @param key 资源key
     * @return
     */
    public static String getResourceKey(PageContext pageContext,String key){
        if(StringUtil.isNotNull(key)){
            //String keyAttrValue = (String) ExpressionEvaluatorManager.evaluate( "key", messageKey, String.class, this, pageContext);
            LocalizationContext locCtxt = null;

            Object obj = Config.find(pageContext, Config.FMT_LOCALIZATION_CONTEXT);
            if (obj == null) {
                return null;
            }

            if (obj instanceof LocalizationContext) {
                locCtxt = (LocalizationContext) obj;
            } else {
                // localization context is a bundle basename
                //locCtxt = getLocalizationContext(pc, (String) obj);
            }

            ResourceBundle bundle = locCtxt.getResourceBundle();
            if (bundle != null) {
                try {
                    String message = bundle.getString(key);
                    return message;
                } catch (MissingResourceException mre) {
                    mre.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 获取资源key
     * @param request
     * @param key
     * @param args 附带参数
     * @return
     */
    public static String getResourceKey(HttpServletRequest request,String key,Object[] args){
        WebApplicationContext ac =RequestContextUtils.getWebApplicationContext(request);
        if(StringUtil.isNotNull(key)){
            return ac.getMessage(key,args, RequestContextUtils.getLocale(request));
        }
        return null;
    }

    /**
     * 获取资源key
     * @param key
     * @param args 附带参数
     * @return
     */
    public static String getResourceKey(String key,Object[] args){
        return getResourceKey(getRequest(),key,args);
    }
    /**
     * 获取资源key
     * @param request
     * @param key
     * @return
     */
    public static String getResourceKey(HttpServletRequest request,String key){
        return getResourceKey(request,key,null);
    }

    /**
     * 获取资源key
     * @param key
     * @return
     */
    public static String getResourceKey(String key){
        return getResourceKey(getRequest(),key);
    }

    /**
     * 获取Request
     * @return
     */
    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

}
