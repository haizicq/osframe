package com.os.osframe.frame.convert;

/**
 * 多对多的列表转换
 * Created by wangdc on 2015-4-3.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.os.osframe.frame.domain.IBaseDomain;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ManyToManyListEditor extends PropertyEditorSupport {

    protected Class domainClass=null;
    protected String showName=null;
    public ManyToManyListEditor() {
    }
    public ManyToManyListEditor(Class domainClass) {
        this.domainClass =domainClass;
    }
    public ManyToManyListEditor(Class domainClass,String name) {
        this.domainClass =domainClass;
        this.showName=name;
    }
    /**
     * Parse the Date from the given text, using the specified DateFormat.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        if ( !StringUtils.hasText(text)) {
            setValue(null);
        } else if(domainClass==null){
            setValue(null);
        } else {
            List objList=new ArrayList();
            try {
                //当表单上传入的直接为json串时,需要先处理json
                if(text.indexOf("{")>-1){
                    JSONObject jsonObject=JSONObject.parseObject(text);
                    int len=0;
                    Iterator it= jsonObject.keySet().iterator();
                    while (it.hasNext()){
                        String key=(String)it.next();
                        JSONArray values=jsonObject.getJSONArray(key);
                        if(values==null){
                            continue;
                        }

                        len=values.size();
                        if(objList==null || objList.isEmpty()){//将值转入到对象中
                            objList=new ArrayList();
                            for(int i=0;i<len;i++){
                                IBaseDomain baseDomain = (IBaseDomain) domainClass.newInstance();
                                PropertyUtils.setProperty(baseDomain, key, values.getString(i));
                                objList.add(baseDomain);
                            }

                        }else{
                            for(int i=0;i<len;i++){
                                IBaseDomain baseDomain;
                                if(objList.size()<=i){//当前面对象字段较少时再新增
                                    baseDomain = (IBaseDomain) domainClass.newInstance();
                                    objList.add(baseDomain);
                                }else{
                                    baseDomain =(IBaseDomain)objList.get(i);
                                }
                                PropertyUtils.setProperty(baseDomain, key, values.getString(i));
                            }
                        }
                    }
                }else{
                    String[] objArr = text.split(",");//XXX 如何实现基础包中直接初始化子类，然后添加到LIST中
                    for (int i = 0; i < objArr.length; i++) {
                        IBaseDomain baseDomain = (IBaseDomain) domainClass.newInstance();
                        baseDomain.setPkId(objArr[i]);
                        objList.add(baseDomain);
                    }
                }
            }catch (Exception e){
                    e.printStackTrace();
            }
            if(objList!=null && !objList.isEmpty()){
                    setValue(objList);
            }else{
                    setValue(null);
            }
        }
    }

    /**
     * Format the Date as String, using the specified DateFormat.
     */
    @Override
    public String getAsText() {
//        List list=(List)this.getValue();
//        JSONArray jsonArray=new JSONArray();
//        String showName=this.showName;
//        if(StringUtil.isNull(showName)){
//            showName="pkId";
//        }
//        if(list!=null && !list.isEmpty()){
//            for(int i=0;i<list.size();i++){
//                IBaseDomain baseDomain=(IBaseDomain)list.get(i);
//                JSONObject jsonObject=new JSONObject();
//                jsonObject.put("id",baseDomain.getPkId());
//                try{
//                    jsonObject.put("name", PropertyUtils.getProperty(baseDomain, this.showName));
//                }catch (Exception e){
//                    jsonObject.put("name","");
//                    e.printStackTrace();
//                }
//
//            }
//        }
        JSONArray jsonArray=(JSONArray)this.getValue();;
        return jsonArray.toJSONString();
    }

}

