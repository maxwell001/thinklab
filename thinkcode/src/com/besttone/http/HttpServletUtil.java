package src.com.besttone.http;

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
			//��ֹ�����������
			response.setHeader("content-type","text/html;charset=UTF-8");
			//ͨ��PrintWriter������������ַ�
			response.getWriter().print("chinese character");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//clear sessioin
	public void logout(HttpServletRequest request,HttpServletResponse response){
		try{
			//���session
			request.getSession().invalidate();
			//���ص�½ҳ��
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//jspҳ����ת����,forward
	public void forward(HttpServletRequest request,HttpServletResponse response){   
		try {
			response.setContentType("text/html; charset=gb2312");   
			ServletContext sc = request.getServletContext();   
			RequestDispatcher rd = null;   
			rd = sc.getRequestDispatcher("/index.jsp"); //�����ҳ��   
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
	
	public String getUrlParameter(HttpServletRequest request,String key){
		String res = "";
		try{
			res = request.getParameter(key);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public Object getSessionAttribute(HttpServletRequest request,String key){
		Object obj = "";
		try{
			obj = request.getSession().getAttribute(key);
		}catch(Exception e){
			e.printStackTrace();
		}
		return obj;
	}
	
	public static void main(String[] args) {
		
	}
}
