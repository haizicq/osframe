package com.os.osframe.frame.loader;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdc on 14-4-18.
 */
public class I18nMessageLoader extends ResourceBundleMessageSource {

    public static String PROPERTY_POSTFIX = ".properties";
    //加载属性资源文件
    // private ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
    //private ReloadableResourceBundleMessageSource resourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
    //支持通配符匹配　
    private PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
    public void setExpression(String[] baseNames) {
        List<String> baseNameList = new ArrayList<String>();
        try {
            System.out.println("国际化：baseNames="+baseNames[0]);
            for(String baseName : baseNames) {
                Resource[] resources = patternResolver.getResources(baseName);   //通过通配符取得到所有对应的source
                System.out.println("国际化：resources.length="+resources.length);
                for(Resource resource : resources) {
                    String url=resource.getURL().getPath();
                    int index=url.indexOf("com/os/osframe");//截取前缀路径
                    url=url.substring(index,url.indexOf(PROPERTY_POSTFIX));//截取后缀
                    if(url.indexOf("_")>-1){
                        url=url.substring(0,url.indexOf("_"));
                    }
                    if(url.indexOf(".jar!")>-1){//当为jar包中的文件时 需要去除jar部分
                        url=url.substring(url.indexOf(".jar!")+6);
                    }
                    url=url.replaceAll("/",".");//去除.jar 部分文件名
                    System.out.println("国际化：url="+url);
                    if(!baseNameList.contains(url)){
                        baseNameList.add(url);
                    }
                    //除掉后的.properties后缀，因为直接用ResourceBundleMessageSource，是不用加后缀的
                    if(logger.isInfoEnabled()) {
                        logger.info("Add properties file: [" + resource.getDescription() + "]");
                    }
                }
            }
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        this.setBasenames(baseNameList.toArray(new String[baseNameList.size()]));
    }

}
