package com.thinklab.platform.util;

public class StringUtil {
	
	/**
	 * Long ת��Ϊ String
	 * @param l
	 * @return
	 */
	public static String longToString(Long l){
		String res = "";
		if(l!=null){
			res = l.toString();
		}
		return res;
	}
}
