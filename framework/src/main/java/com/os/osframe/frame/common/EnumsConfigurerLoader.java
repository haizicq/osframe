package com.os.osframe.frame.common;

import com.os.osframe.util.CacheUtil;
import com.os.osframe.util.StringUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.*;

/**
 * 在spring中配置的属性文件，便于后面使用时获取
 * Created by wangdc on 2015-5-22.
 */
public class EnumsConfigurerLoader extends PropertyPlaceholderConfigurer implements IEnumsConfig {
    private  CacheUtil cache=new CacheUtil();//创建缓存对象
    protected void processProperties( ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        // cache the properties
        Iterator<Map.Entry<Object, Object>> it = props.entrySet().iterator();

        //返回格式，先截取key的.前面的部分，然后看是否存在，如果存在则在其下方加入该key
        //cache 的key中存的是一个List
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = it.next();
            String key = (String)entry.getKey();
            if(StringUtil.isNotNull(key) && key.indexOf(".")>-1){
                String[] keyPre=key.split("\\.");
                List obj=(List)cache.getValue(keyPre[0]);
                if(obj==null){//当缓存中没存时，重新创建一个放到缓存中
                    obj=new ArrayList();
                    cache.put(keyPre[0],obj);
                }
                Map node =new HashMap();
                node.put("key",key);
                node.put("value", entry.getValue());
                obj.add(node);
            }
        }
        super.processProperties(beanFactoryToProcess, props);
    }

    /**
     * 根据key获取枚举中的所有值
     * @param key
     * @return
     */
    public List getEnums(String key) {
        if(StringUtil.isNotNull(key)){
            String reKey=key;
            if( key.indexOf(".")>-1){
                reKey=key.split("\\.")[0];
            }
            return (List)cache.getValue(reKey);
        }
        return null;
    }

    /**
     * 根据key获取其对应的值
     * @param key
     * @return
     */
    public String get(String key) {
        List list=getEnums(key);
        if(list!=null && !list.isEmpty()){
            if(key.indexOf(".")==-1){
                return list.toString();
            }else{
                for(int i=0;i<list.size();i++){//循环去查找是否存在key，存在则直接返回
                    Map map=(Map)list.get(i);
                    if(key.equals(map.get("key"))){
                        return (String)map.get("value");
                    }
                }
            }

        }

        return null;
    }
}
