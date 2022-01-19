package com.kingmeter.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author CrazyAndy
 */
public class StringUtil extends StringUtils{
	/**
	 * replace value from  {}
	 * @param str
	 * @param params
	 * @return
	 */
	public static String bracketReplace(String str,Map<String,Object> params){
		for(String key:params.keySet())
			str = str.replace(new StringBuilder("{").append(key).append("}").toString(), params.get(key).toString());
		return str;
	}
	public static String createTreeCode(int srcNum, int digits){
		return createTreeCode(srcNum, digits, '0');
	}
	
	public static String createTreeCode(int srcNum, int digits, char fillChar){
		String src = String.valueOf(srcNum);
		if(src.length() > digits)
			return src.substring(src.length() - digits, src.length());
		
		while(src.length() < digits){
			src = fillChar + src;
		}
		return src;
	}
	
	public static String getResultByQueryCondition(String condition){
		
		String result = condition.replace("%", "\\%").replace("_", "\\_");
		
		return result;
	}
	public static String camelCaseToUnderscore(String name) {
		if (isBlank(name)) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		result.append(name.substring(0, 1).toLowerCase());
		for (int i = 1; i < name.length(); i++) {
			String s = name.substring(i, i + 1);
			String slc = s.toLowerCase();
			if (!s.equals(slc)) {
				result.append("_").append(slc);
			}
			else {
				result.append(s);
			}
		}
		return result.toString();
	}
	public static boolean isInteger(String str) {
		if(StringUtil.isEmpty(str)){
			return false;
		}
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		boolean flag = pattern.matcher(str).matches();
		return flag;
	}



}