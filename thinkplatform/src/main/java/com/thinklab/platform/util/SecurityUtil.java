package com.thinklab.platform.util;

import java.security.MessageDigest;

public class SecurityUtil {

	/**
	 * string convert to md5 
	 * @param str
	 * @return
	 */
	public static String stringToMd5(String str){
		String res = "";
		try{
			if(str!=null && !"".equals(str)){
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(str.getBytes());
				res = md.digest().toString();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return res;
	}
	
	
}
