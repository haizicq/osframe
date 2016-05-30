package com.os.osframe.core.taglib.bs;

import com.os.osframe.frame.common.EnumsCache;
import com.os.osframe.frame.util.ResourceUtil;
import com.os.osframe.core.taglib.util.TagUtil;
import com.os.osframe.util.StringUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.List;
import java.util.Map;

/**
 * 枚举标签
 * Created by wangchun on 16/4/5.
 */
public class EnumsTag  extends BodyTagSupport {
    /**
     * 显示方式
     *  input(输入)、label(文本)、readOnly(只读)
     */
    protected String display=null;
    /**
     * 控件类型
     * select/radio/checkbox
     */
    protected String type=null;
    /**
     * 名称
     */
    protected String name = null;
    /**
     * 枚举关键字
     */
    protected String key = null;
    /**
     * 样式class
     */
    protected String className = null;
    /**
     * 是否必填
     */
    protected Boolean required = Boolean.valueOf(false);
    /**
     * 样式style
     */
    protected String style = null;
    /**
     * 追加的其他dom属性
     */
    protected String html = null;
    /**
     * 值
     */
    protected String value = null;
    public void release()
    {
        super.release();
        this.display = null;
        this.type = null;
        this.name = null;
        this.key = null;
        this.className = null;
        this.required = Boolean.valueOf(false);
        this.style = null;
        this.html = null;
        this.value = null;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int doStartTag() throws JspException {
        return 1;
    }

    @Override
    public int doEndTag() throws JspException {
        this.outEndTag();
        return super.doEndTag();
    }


    /**
     * 输出结束标签
     *
     * @throws javax.servlet.jsp.JspException
     */
    public void outEndTag()
            throws JspException {
        JspWriter writer = pageContext.getOut();
        try {
            writer.print(joinHtml());

        } catch (Exception ex) {
            throw new JspException(ex);
        }
    }

    /**
     * 拼装实际输出的html
     *
     * @return
     * @throws Exception
     */
    protected StringBuffer joinHtml()
            throws Exception {
        StringBuffer results = new StringBuffer();
        if(StringUtil.isNull(this.key)){
            results.append(this.value);
            return results;
        }
        if("label".equals(display)){//当为显示文本时
            if(value!=null && !"".equals(value)){
                String valueKey=(String)EnumsCache.getInstance().get(this.key, this.value);//  获取值对应的key
                if(StringUtil.isNotNull(valueKey)){
                    results.append(ResourceUtil.getResourceKey(pageContext, valueKey));
                }
            }
            return results;
        }else{
            List list=(List)EnumsCache.getInstance().get(this.key);

            if("select".equals(this.type)){//下拉框
                TagUtil.appendTag(results, "select");;
                TagUtil.addAttribute(results, "name", this.name);
                TagUtil.addAttribute(results, "id", this.name);
                TagUtil.addAttribute(results, "ng-model", this.name);
                TagUtil.addAttribute(results, "ng-init", this.name + "='" + this.value + "'");
                TagUtil.addAttribute(results, "class", this.className);
                TagUtil.addAttribute(results, "style", this.style);
                if("readonly".equals(display)){
                    TagUtil.addAttribute(results, "disabled", "disabled");
                }
                TagUtil.endAttribute(results);
                if(list!=null && !list.isEmpty()){
                    int enumsLen=list.size();
                    results.append(createOption("", "=请选择="));
                    for(int i=0;i<enumsLen;i++){
                        Map map=(Map)list.get(i);
                        // 输出获取的中文名称
                        String messageKey=(String)map.get("key");
                        String message=null;
                        if(StringUtil.isNotNull(messageKey)){
                             message=ResourceUtil.getResourceKey(pageContext, messageKey);
                        }
                        if(StringUtil.isNotNull(message)){
                            results.append(createOption((String)map.get("value"), message));
                        }else{
                            results.append(createOption((String)map.get("value"), ""));
                        }

                    }
                }
                TagUtil.endTag(results, "select");

            }else if("checkbox".equals(this.type)){//复选框
                if(list!=null && !list.isEmpty()){
                    //复选框，通过生成 anglarjs的指令来控制，ng-checkbox ,ng-checkbox-name(赋值到的对象 this.name) 取当前对象的值，无需ng－model
                    results.append("<input type=\"hidden\" ng-model=\""+this.name+"\"  ng-init=\""+this.name+"='"+this.value+"'\"/>");
                    int enumsLen=list.size();
                    String[] valueArr=null;
                    if(StringUtil.isNotNull(this.value)){
                        valueArr=this.value.split(",");
                    }
                    for(int i=0;i<enumsLen;i++){
                        Map map=(Map)list.get(i);
                        TagUtil.appendTag(results, "label");
                        TagUtil.addAttribute(results, "class", "checkbox-inline i-checks");
                        TagUtil.endAttribute(results);
                        TagUtil.appendTag(results, "input");
                        TagUtil.addAttribute(results, "type", "checkbox");
                        TagUtil.addAttribute(results, "name", this.name);
                        TagUtil.addAttribute(results, "id", this.name + "_" + i);
                        results.append(" ng-checkbox ");//添加指令
                        TagUtil.addAttribute(results, "style", this.style);
                        if("readonly".equals(display)){
                            TagUtil.addAttribute(results, "readOnly", true);
                        }
                        if(StringUtil.isNull(this.className)){
                            TagUtil.addAttribute(results, "class", "form-control");
                        }else{
                            TagUtil.addAttribute(results, "class", this.className);
                        }
                        String enumsValue=(String)map.get("value");
                        TagUtil.addAttribute(results, "value", enumsValue);
                        //初始化选中状态需要根据逗号拆分

                        if(StringUtil.isNotNull(this.value) ){
                            for(int j=0;j<valueArr.length;j++){
                                if(valueArr[j].equals(enumsValue)){//如果这2个值相等则说明未选中状态
                                    TagUtil.addAttribute(results, "checked", true);
                                    break;
                                }
                            }

                        }
                        TagUtil.endAttribute(results, true);
                        results.append("<i></i>");
                        //results.append("<fmt:message key=\""+(String)map.get("key")+"\"/>");
                        // 输出获取的中文名称
                        String messageKey=(String)map.get("key");
                        if(StringUtil.isNotNull(messageKey)){
                            String message=ResourceUtil.getResourceKey(pageContext, messageKey);
                            if(StringUtil.isNotNull(message)){
                                results.append(message);
                            }

                        }
                        TagUtil.endTag(results, "label");

                    }
                }
            }else{//默认为radio
                if(list!=null && !list.isEmpty()){
                    int enumsLen=list.size();

                    for(int i=0;i<enumsLen;i++){
                        Map map=(Map)list.get(i);
                        TagUtil.appendTag(results, "label");
                        TagUtil.addAttribute(results, "class", "checkbox-inline i-checks");
                        TagUtil.endAttribute(results);

                        TagUtil.appendTag(results, "input");
                        TagUtil.addAttribute(results, "type", "radio");
                        TagUtil.addAttribute(results, "name", this.name);
                        TagUtil.addAttribute(results, "id", this.name + "_" + i);
                        TagUtil.addAttribute(results, "ng-model", this.name);
                        if(i==0){
                            TagUtil.addAttribute(results, "ng-init", this.name + "='" + this.value + "'");
                        }
                        TagUtil.addAttribute(results, "style", this.style);
                        if(StringUtil.isNull(this.className)){
                            TagUtil.addAttribute(results, "class", "form-control");
                        }else{
                            TagUtil.addAttribute(results, "class", this.className);
                        }
                        TagUtil.addAttribute(results, "value", (String) map.get("value"));
                        if(StringUtil.isNotNull(this.value) && this.value.equals((String)map.get("value"))){
                            TagUtil.addAttribute(results, "ng-checked", true);
                        }
                        TagUtil.endAttribute(results, true);
                        results.append("<i></i>");
                        //results.append("<fmt:message key=\""+(String)map.get("key")+"\"/>");
                        // 输出获取的中文名称
                        String messageKey=(String)map.get("key");
                        if(StringUtil.isNotNull(messageKey)){
                            String message=ResourceUtil.getResourceKey(pageContext, messageKey);
                            if(StringUtil.isNotNull(message)){
                                results.append(message);
                            }

                        }
                        TagUtil.endTag(results, "label");
                    }
                }
             }
        }
        return results;
    }

    /**
     * 创建option
     * @param optionValue
     * @param text
     * @return
     */
    protected StringBuffer createOption(String optionValue,String text){
        StringBuffer results= TagUtil.addTag("option");
        TagUtil.addAttribute(results, "value", optionValue);
        if(StringUtil.isNotNull(optionValue) && StringUtil.isNotNull(this.value) && optionValue.equals(this.value)){
            TagUtil.addAttribute(results, "selected", "true");
        }
        TagUtil.endAttribute(results);
        results.append(text);
        TagUtil.endTag(results, "option");
        return results;
    }

}
