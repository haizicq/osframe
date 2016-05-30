package com.os.osframe.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用的String转换方法。
 *
 */
public class StringUtil {

	/**
	 * 转换字符串中HTML/XML敏感的字符。
	 * 
	 * @param src
	 *            源字符串
	 * @return 转换后的字符串
	 */
	public static String XMLEscape(String src) {
		if (src == null)
			return null;
		String rtnVal = src.replaceAll("&", "&amp;");
		rtnVal = rtnVal.replaceAll("\"", "&quot;");
		rtnVal = rtnVal.replaceAll("<", "&lt;");
		rtnVal = rtnVal.replaceAll(">", "&gt;");
		rtnVal = rtnVal.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
		return rtnVal;
	}

	/**
	 * 获取query中的参数值
	 * 
	 * @param query 样例：a=1&b=2，注意：不带?符号
	 * @param param 参数名，如：a
	 * @return 参数值
	 */
	public static String getParameter(String query, String param) {
		Pattern p = Pattern.compile("&" + param + "=([^&]*)");
		Matcher m = p.matcher("&" + query);
		if (m.find())
			return m.group(1);
		return null;
	}

	/**
	 * 替换字符串中的指定字符，类似String.replaceAll的方法，但去除了正则表达式的应用
	 * 
	 * @param srcText  源字符串
	 * @param fromStr  需要替换的字符串
	 * @param toStr 替换为的字符串
	 * @return 替换后的字符串
	 */
	public static String replace(String srcText, String fromStr, String toStr) {
		if (srcText == null)
			return null;
		StringBuffer rtnVal = new StringBuffer();
		String rightText = srcText;
		for (int i = rightText.indexOf(fromStr); i > -1; i = rightText
				.indexOf(fromStr)) {
			rtnVal.append(rightText.substring(0, i));
			rtnVal.append(toStr);
			rightText = rightText.substring(i + fromStr.length());
		}
		rtnVal.append(rightText);
		return rtnVal.toString();
	}

	/**
	 * 连接字符串，常用与HQL语句的拼装，当左边值为空时返回右边值，当右边值为空时返回左边值，左右的值都不为空时返回左边值+连接串+右边值
	 * 
	 * @param leftStr 左边的值
	 * @param linkStr 连接字符串
	 * @param rightStr 右边的值
	 * @return 连接后的字符串
	 */
	public static String linkString(String leftStr, String linkStr, String rightStr) {
		if (isNull(leftStr)){
			return rightStr;
        }
		if (isNull(rightStr)){
			return leftStr;
        }
		return leftStr + linkStr + rightStr;
	}

	/**
	 * 判断一个字符串是否为null或空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 判断一个字符串是否为null或空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	/**
	 * 检查给定的字符串既不是空，也不是长度为0。
	 * @param str
	 *            需检查的字符串(可以为<code>null</code>)
	 * @return 若字符串不为null或长度不为0，则返回<code>true</code>。
	 */
	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * 过滤字符串null
	 * 
	 * @param s  要过滤的字符串
	 * @return 过滤后的字符串
	 */
	public static String getString(String s) {
		return s == null ? "" : (s.equals("null") ? "" : s);
	}

	/**
	 * 将多个path字符串拼接起来，过滤多余的路径分隔符
	 * 
	 * @param paths 多个路径
	 * @return 拼接后的路径，最后是否带带路径分隔符由最后一个路径决定
	 */
	public static String linkPathString(String... paths) {
		if (null == paths || paths.length == 0)
			return "";

		StringBuilder sb = new StringBuilder();

		sb.append(paths[0]);

		for (int i = 1; i < paths.length; i++) {
			if (paths[i - 1].endsWith("\\") || paths[i - 1].endsWith("/")) {
				if (paths[i].startsWith("\\") || paths[i].startsWith("/")) {
					sb.append(paths[i].substring(1));
				} else {
					sb.append(paths[i]);
				}
			} else {
				if (paths[i].startsWith("\\") || paths[i].startsWith("/")) {
					sb.append(paths[i]);
				} else {
					sb.append("/").append(paths[i]);
				}
			}

		}
		return sb.toString();
	}

	/**
	 * 将字符串转换为数字，如果有错，采用缺省值
	 * @param value 字符串
	 * @param defaultValue 缺省值
	 * @return
	 */
	public static int getIntFromString(String value, int defaultValue) {
		int ret = defaultValue;
		if (StringUtil.isNotNull(value)) {
			try {
				ret = Integer.parseInt(value);
			} catch (NumberFormatException e) {
				ret = defaultValue;
			}
		}
		return ret;
	}

	/**
	 * 合并2个string[],并去掉重复项
	 * @param ary1
	 * @param ary2
	 * @return
	 */
	public static String[] mergeStringArray(String[] ary1, String[] ary2) {
		if (null == ary1)
			return ary2;
		if (null == ary2)
			return ary1;

		List<String> l1 = new ArrayList<String>(Arrays.asList(ary1));
		List<String> l2 = Arrays.asList(ary2);
		for (String s : l2) {
			if (!l1.contains(s)) {
				l1.add(s);
			}
		}
		String[] strings = new String[l1.size()];
		l1.toArray(strings);
		return strings;
	}

	// 空的数组返回null
	public static String[] emptyArray2Null(String[] ary1) {
		if (null == ary1 || ary1.length == 0) {
			return null;
		} else {
			return ary1;
		}
	}

    /**
     * 过滤脚本标签
     * @param html
     * @return
     */
	public static String clearScriptTag(String html) {
		// 过滤掉script标签
		Pattern scriptTag = Pattern
				.compile("<script[^>]*>.*(?=<\\/script>)<\\/script>");
		Matcher mTag = scriptTag.matcher(html);
		html = mTag.replaceAll("");

		// 过滤掉Dom节点事件
		String regx = "(<[^<]*)(on\\w*\\x20*=|javascript:)";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE
				+ Pattern.MULTILINE);// 不区分大小写
		Matcher matcher;
		String ts = html;
		// 此处需要循环匹配，防止恶意构造的字符串如 onclick=onclick=
		while ((matcher = pattern.matcher(ts)).find()) {
			ts = matcher.replaceAll("$1" + "_disibledevent=");
		}
		return ts;
	}
    /**
     * 根据多个字符条件来确认
     * @param str 字符串
     * @param exp 表达式 用“|”分隔
     * @return
     */
    public static int indexOf(String str,String exp){
        String[] exps=exp.split("\\|");
        int reValue=-1;
        for(String sign:exps){
            reValue=str.indexOf(sign);
            if(reValue>-1){
                return reValue;
            }
        }
        return reValue;
    }
}
