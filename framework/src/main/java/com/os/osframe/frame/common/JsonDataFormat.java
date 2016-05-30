package com.os.osframe.frame.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.os.osframe.frame.log.LogInfo;
import com.os.osframe.frame.util.ResourceUtil;
import com.os.osframe.util.StringUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * json数据格式规范
 * Created by wangchun on 16/4/28.
 */
public class JsonDataFormat {
    protected LogInfo logger=new LogInfo(JsonDataFormat.class);

    protected JSONObject jsonObject;
    public JsonDataFormat(){
        jsonObject=new JSONObject();
    }
    //涉及到的基础数据包括：错误类型，错误代码错误描述
    /**
     * json key
     *  成功状态
     */
    protected final static String JSON_KEY_STATUS="status";
    protected final static String JSON_KEY_CODE="code";
    protected final static String JSON_KEY_MESSAGE="message";
    protected final static String JSON_KEY_DATA="data";

    /**
     * 设置成功失败状态
     * 成功是 1 失败是 0
     */
    public void setStatus(boolean bool){
        if(!bool){
            jsonObject.put(JSON_KEY_STATUS,"0");
        }else{//默认是成功
            jsonObject.put(JSON_KEY_STATUS,"1");
        }
    }

    /**
     * 获取是否为错误
     * @return
     */
    public boolean isError(){
        String bool=(String)jsonObject.get(JSON_KEY_STATUS);
        if(!"0".equals(bool)){
            return false;
        }
        return true;
    }

    /**
     * 设置错误消息
     * @param code 错误代码
     * @param message 错误消息
     */
    public void setErrorMessage(String code,String message){
        this.setStatus(false);
        if(StringUtil.isNotNull(code)){
            jsonObject.put(JSON_KEY_CODE,code);
        }
        if(StringUtil.isNotNull(message)){
            jsonObject.put(JSON_KEY_MESSAGE,message);
        }

    }

    /**
     * 设置数据值
     * @param data
     */
    public void setData(Object data){
        jsonObject.put(JSON_KEY_DATA,data);
    }
    /**
     * 想data中增加值
     * @param data
     */
    public void addData(Object data){
        Object obj= getData();
        if(obj!=null){
                if(obj instanceof Collection) {//判断是否为集合,是集合情况，直接添加数据
                    Collection collection = (Collection) obj;
                    collection.add(data);
                }else{//不是集合情况则将原来的数据替换
                    this.setData(data);
                }
        }else{
            JSONArray  collection=new JSONArray();
            collection.add(data);
            this.setData(collection);
        }
    }
    /**
     * 获取数据
     * @return 获取数据
     */
    public Object getData(){
        if(jsonObject.containsKey(JSON_KEY_DATA)){
            return this.get(JSON_KEY_DATA);
        }
        return null;
    }

    /**
     * 获取json对象
     * @return
     */
    public JSONObject getJsonObject(){
        return jsonObject;
    }

    /**
     * 向json中继续添加属性
     * @param key
     * @param value
     */
    public void put(String key,Object value){
        jsonObject.put(key,value);
    }

    /**
     * 根据key
     * @param key
     * @return
     */
    public Object get(String key){
        if(jsonObject.containsKey(key)){
            return (Object)jsonObject.get(key);
        }
        return null;
    }



    /**
     * 将json转为字符串输出
     * @return
     */
    public String toString(){
        if(!jsonObject.containsKey(JSON_KEY_STATUS)){
            this.setStatus(true);
        }
        String bool=(String)this.get(JSON_KEY_STATUS);
        //success.message 处理成功情况增加消息提示
        if(!"0".equals(bool)){//不为失败 的情况
            this.put(JSON_KEY_MESSAGE,ResourceUtil.getResourceKey("success.message"));
        }
        return jsonObject.toJSONString();
    }

    /**
     * 错误异常代码 101 必须使用post请求
     */
    public final static String JSON_ERROR_CODE_POST="101";
    /**
     * 错误异常代码 201 业务处理捕获到异常
     */
    public final static String JSON_ERROR_CODE_SERVICE="201";
    /**
     * 错误异常代码 301 必要参数缺失异常
     */
    public final static String JSON_ERROR_CODE_PARAM="301";
    /**
     * 错误异常代码 401 其他异常
     */
    public final static String JSON_ERROR_CODE_OTHER="401";

    /**
     * 设置常用异常代码
     * @param code
     */
    public void setCommonCode(String code){
        this.setCommonCode(code,null);
    }
    /**
     * 设置常用异常代码
     * @param code
     */
    public void setCommonCode(String code,String message){
        if(JSON_ERROR_CODE_POST.equals(code)){
            this.setErrorMessage(code, ResourceUtil.getResourceKey("error.code.post"));
        }else if(JSON_ERROR_CODE_SERVICE.equals(code)){
            String msg=ResourceUtil.getResourceKey("error.code.service");
            if(StringUtil.isNotNull(message)){
                msg+="  "+message;
            }
            this.setErrorMessage(code, msg);
        }else if(JSON_ERROR_CODE_PARAM.equals(code)){
            String msg=ResourceUtil.getResourceKey("error.code.param");
            if(StringUtil.isNotNull(message)){
                msg+=":"+message;
            }
            this.setErrorMessage(code, msg);
        }else if(JSON_ERROR_CODE_OTHER.equals(code)){
            this.setErrorMessage(code, ResourceUtil.getResourceKey("error.code.other"));
        }else{
            this.setErrorMessage(code,message);
        }
    }

    /**
     * 输出json
     * @param response
     */
    public void printJson(HttpServletResponse response) {
        PrintWriter out=null;
        try {
            String json=this.toString();
            out=response.getWriter();
            response.setContentType("text/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
            response.setDateHeader("Expires",-1);
            out.print(json);
        } catch (Exception e) {
            logger.error(e);
        }finally {
            if(out!=null){
                out.close();
            }
        }

    }
}
