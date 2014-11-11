package com.besttone.http;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletUtil {
	/**
	 * return chinese character to jsp page
	 * @param l
	 * @return
	 */
	public static void returnCharacter(HttpServletRequest request,HttpServletResponse response) {
		try {
			//防止数据中文乱码
			response.setHeader("content-type","text/html;charset=UTF-8");
			//通过PrintWriter向浏览器返回字符串
			response.getWriter().print("chinese character");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//clear sessioin
	public void logout(HttpServletRequest request,HttpServletResponse response){
		try{
			//清空session
			request.getSession().invalidate();
			//返回登陆页面
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//jsp页面跳转方法,forward
	public void forward(HttpServletRequest request,HttpServletResponse response){   
		try {
			response.setContentType("text/html; charset=gb2312");   
			ServletContext sc = request.getServletContext();   
			RequestDispatcher rd = null;   
			rd = sc.getRequestDispatcher("/index.jsp"); //定向的页面   
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	//redirect
	public void redirect(HttpServletRequest request,HttpServletResponse response){   
		try {
			response.setContentType("text/html; charset=gb2312");
			response.sendRedirect("/index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}   
	}
	
	public static void main(String[] args) {
		
	}
}
