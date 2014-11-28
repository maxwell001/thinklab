package com.thinklab.platform.util;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
	
	public static String getUrlParameter(HttpServletRequest request,String key){
		String res = "";
		try{
			res = request.getParameter(key);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public static Object getSessionAttribute(HttpServletRequest request,String key){
		Object obj = "";
		try{
			obj = request.getSession().getAttribute(key);
		}catch(Exception e){
			e.printStackTrace();
		}
		return obj;
	}
	
	public static void setSessionAttribute(HttpServletRequest request,String key,Object obj){
		try{
			request.getSession().setAttribute(key, obj);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
