package com.os.osframe.core.taglib.util;

import com.os.osframe.util.StringUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagUtil {
	/**
	 * 添加属性
	 * @param handlers 需要添加流
	 * @param name 属性名
	 * @param value 属性值
	 */
	public static void addAttribute(StringBuffer handlers, String name,Object value) {
		if (value != null) {
			handlers.append(" ");
			handlers.append(name);
			handlers.append("=\"");
			handlers.append(value);
			handlers.append("\"");
		}
	}
	/**
	 * 结束属性
	 * @param handlers 
	 */
	public static void endAttribute(StringBuffer handlers) {
		endAttribute(handlers, false);
	}
	/**
	 * 结束属性
	 * @param handlers 
	 * @param tagEnd 是否结束标签
	 */
	public static void endAttribute(StringBuffer handlers, Boolean tagEnd) {
		if (tagEnd!=null && tagEnd==true) {
			handlers.append(" />");
		}else{
			handlers.append(" >");
		}
	}

	/**
	 * 结束属性
	 * @param handlers 
	 */
	public static void endAttribute(StringBuffer handlers, String name,Object value) {
		endAttribute(handlers, name, value,false);
	}
	/**
	 * 结束属性
	 * @param handlers 
	 */
	public static void endAttribute(StringBuffer handlers, String name,Object value, Boolean tagEnd) {
		addAttribute(handlers, name, value);
		endAttribute(handlers, tagEnd);
	}
	public static void appendTag(StringBuffer handlers,String tag){
		handlers.append(addTag(tag, false, false));
	}
	public static void appendTag(StringBuffer handlers,String tag, Boolean attEnd){
		handlers.append(addTag(tag, attEnd, false));
	}
	public static void appendTag(StringBuffer handlers,String tag, Boolean attEnd, Boolean tagEnd){
		handlers.append(addTag(tag, attEnd, tagEnd));
	}
	/**
	 * 添加标签
	 * @param tag 标签名
	 * @return
	 */
	public static StringBuffer addTag(String tag){
		return addTag(tag, false, false);
	}
	/**
	 * 添加标签
	 * @param tag 标签名
	 * @param attEnd 是否结束属性
	 * @return
	 */
	public static StringBuffer addTag(String tag, Boolean attEnd) {
		return addTag(tag, attEnd, false);
	}
	/**
	 * 添加标签
	 * @param tag 标签名
	 * @param attEnd 是否结束属性
	 * @param tagEnd 是否结束标签
	 * @return
	 */
	public static StringBuffer addTag(String tag, Boolean attEnd, Boolean tagEnd) {
		if(StringUtil.isNotNull(tag)){
			StringBuffer handlers=new StringBuffer();
			handlers.append("<").append(tag);
			if(tagEnd!=null && tagEnd==true){
				handlers.append("/>");
			} else if(attEnd!=null && attEnd==true){
				handlers.append(">");
			}else{
				handlers.append(" ");
			}
			return handlers;
		}
		
		return null;
	}
	/**
	 * 结束标签
	 * @param handlers
	 * @param tag 标签
	 */
	public static void endTag(StringBuffer handlers,String tag) {
		if(StringUtil.isNotNull(tag)){
			handlers.append("</").append(tag).append(">");
		}
	}
	/**
	 * 输出标签内容
	 * @param pageContext 上下文
	 * @param Sbu 输出内容
	 * @return
	 */
	public static void printStringBuffer(PageContext pageContext,StringBuffer Sbu) throws JspException{
		JspWriter writer = pageContext.getOut();
		try {
			writer.print(Sbu.toString());
		} catch (Exception e) {
			throw new JspException(e);
		}
	}
	/**
	 * 转换选项列表
	 * @param option 选项文本
	 * 格式：value|text;value1|text1
	 * @return
	 * 	list<map<value,text>>
	 */
	public static List getOption(String option){
		if(StringUtil.isNull(option)){
			return null;
		}
		List reList=new ArrayList();
		String[] options=option.split(";");
		for(int i=0;i<options.length;i++){
			if(StringUtil.isNull(options[i])){
				continue;
			}
			String[] vt=options[i].split("\\|");
			
			Map map=new HashMap();
			map.put(TagConstant.TAG_OPTION_VALUE, vt[0]);
			map.put(TagConstant.TAG_OPTION_TEXT, vt[1]);
			reList.add(map);
		}
		return reList;
	}

}
