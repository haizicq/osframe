package com.os.osframe.util;




import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
*正则表达式工具类
*/


public class RegexUtil {
	
	
	//验证文件后缀
//	Pattern p = Pattern.compile(".+\\.js$",Pattern.CASE_INSENSITIVE);//不区分大小写的,默认只有一个参数时是区分大小写
//	System.out.println(p.matcher("dads.jS").matches());

	/**
	 * 对一字符串进行正则验证
	 * 验证一个字符串中长度必须大于6位，同时必须包含至少一个字符和一个数值 
	 * 
	 * @param str 待验证的字符串
	 * 
	 * @return 
	 * 		通过验证时返回null
	 * 		如果验证出现问题，会返回对应的失败原因
	 * @throws Exception 
	 */
	public static String validateString(String str) throws Exception{
		//验证长度
		if(validateLength(str)==null){
			//验证字符元素
			return isContainNumber(str);
		}else{
			return validateLength(str);
		}
		
	}
	
	/**
	 * 验证content中必须同时存在数字和字母
	 * @param content
	 * @return
	 * 			通过验证时，返回null  否则返回对应提示
	 * @throws Exception 
	 * 
	 */
	public static String isContainNumber(String content) throws Exception{
		String message = "必须包含数字和字母";
		String restring = "^.*(([a-zA-Z]+.*[0-9]+)|([0-9]+.*[a-zA-Z]+)).*$";  //必须六位以上,包含数字和字母
		Pattern pattern=Pattern.compile(restring);
        if (pattern.matcher(content).matches()) { 
            return null;
        } 
		return message;
	}
	
	/**
	 * 验证content的长度必须大于等六位
	 * @param content
	 * @return
	 * 			通过验证时，返回null  否则返回对应提示
	 * @throws Exception 
	 */
	public static String validateLength(String content) throws Exception{
		String message = "必须六位以上";
		String restring = "^.{6,}$";
		Pattern pattern=Pattern.compile(restring);
        if (pattern.matcher(content).matches()) { 
            return null;
        } 
		return message;
	}
	
	
	/**
	 * 如果字符串url是否以指定的数组的任一字符串结尾
	 * @param url
	 * 			指定字符串
	 * @param noFomat
	 * 			格式集[".js",".html"] <<主要是用于文件格式的验证，必须以"."开头加后缀名    如： .js >>
	 * @return
	 * 			url不是以指定的数组的任一字符串结尾  返回  true 
	 * 			否则返回false
	 */
	public static boolean validateUrl(String url,String[] noFomat){
		//正则非
		String endStr = "";
		//为空时返回true
		if(noFomat==null || noFomat.length-0 == 0 || url==null){
			return true;
		}else{
			for(int i=0; i<noFomat.length; i++ ){
				if(i-0 == 0){
					endStr = "((\\"+noFomat[i]+")";
				}else{
					endStr = endStr+"|(\\"+noFomat[i]+")";
				}
				
				if(i-noFomat.length+1 == 0){
					endStr = endStr+")$";
				}
			}
		}
		//定义正则
		String reg = "^.*";
		//验证文件后缀
		//其中参数意义：不区分大小写的,默认只有一个参数时是区分大小写
		Pattern p = Pattern.compile(reg+endStr, Pattern.CASE_INSENSITIVE);
		return !p.matcher(url).matches();
	}
	
	
	/**
	 * 验证指定的字符串是不是符点型数值
	 * 
	 * @param value
	 * 			某指定的字符串
	 * @return
	 * 			字符串符合数值格式时返回true,否则返回false
	 */
	public static boolean isFloat(String value){
		String restring = "^-?(\\d)+((.\\d+)|(\\d)*)$";
		Pattern pattern=Pattern.compile(restring);
        if (pattern.matcher(value).matches()) { 
            return true;
        }
        return false;
	}
	
	/**
	 * 获取 order by 开始位置
	 * @param orderbysql
	 * @return
	 */
	public static int getOrderByIndex(String orderbysql){
		int index=-1;
		if(StringUtil.isNull(orderbysql)){
			return index;
		}
		 String regStr="\\s+(order)\\s+(by)";
		 Pattern pattern=Pattern.compile(regStr);
		 Matcher matcher=pattern.matcher(orderbysql.toLowerCase());
		 if(matcher.find()){
			 index=matcher.start();
		 }
		 return index;
	}
	
	 
	
	
	public static void main(String[] args) {
		/*
		try {

			
			System.out.println(RegexValidateUtils.isContainNumber("dss1fasd;f"));
			System.out.println(RegexValidateUtils.isContainNumber("2dsf"));
			System.out.println(RegexValidateUtils.isContainNumber("dsf3"));
			System.out.println(RegexValidateUtils.isContainNumber("dsf"));
			System.out.println(RegexValidateUtils.isContainNumber("123"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println(RegexUtil.isFloat("-012416545.000"));
	}
}
