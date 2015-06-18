<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*,java.text.*;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
	<!-- jspé¡µé¢æ¾ç¤ºæ¥æ ææ  -->
	<%
	  Date date = new Date();
	  Calendar cal=Calendar.getInstance();
	  String dayOfWeekTime="";
	  int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
	  switch(dayOfWeek){
	   case 1:dayOfWeekTime="ææå¤©";break;
	   case 2:dayOfWeekTime="ææä¸";break;
	   case 3:dayOfWeekTime="ææäº";break;
	   case 4:dayOfWeekTime="ææä¸";break;
	   case 5:dayOfWeekTime="ææå";break;
	   case 6:dayOfWeekTime="ææäº";break;
	   case 7:dayOfWeekTime="ææå­";break;
	  }
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyå¹´MMæddæ¥");
	  String timeString = sdf.format(date);
	%>
	<%=timeString%> <%=dayOfWeekTime%> 
	<!-- jspéå®å -->
	ï¼jsp:forward page="" /ï¼
	<!-- ä¿®æ¹HTTP headerçLocationå±æ§æ¥éå®å -->
	ï¼%   
	ãresponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);   
	ãString newLocn = "/newpath/jsa.jsp";   
	ãresponse.setHeader("Location",newLocn);   
	%ï¼
	<!-- refresh a target jsp every 300 ms -->
	ï¼meta http-equiv="refresh" content="300; url=target.jsp"ï¼ 
	
	<!-- import a jsp page in three ways -->
	<span style="background-color: rgb(255, 255, 255);"><!-- jstl import --></span>  
	<div style="color:red;">  
		<c:import url="inlayingJsp.jsp"></c:import>  
	</div>  
	<span style="background-color: rgb(255, 255, 255);"><!-- jsp include --></span>  
	<div>  
		<%@ include file="/welcome.jsp" %>  
	</div>  
	<span style="background-color: rgb(255, 255, 255);"><!-- jsp include,can with paramters --></span>  
	<div>  
		<jsp:include page="/welcome.jsp" flush="true"/>  
		<!--  <jsp:param name="parameterName" value="{parameterValue |  }" /> -->  
	</div> 
	
	
</body>
<script type="text/javascript">
	function getTime() {
		var now= new Date(),
		h=now.getHours(),
		m=now.getMinutes(),
		s=now.getSeconds(),
		ms=now.getMilliseconds();
		return (h+":"+m+":"+s+ " " +ms);
	}
	function openWindow(){
		/**
		* js open modal dialog
		* showModalDialog:children page will be front of father page,father page can't do if children page is not closed.
		* showModalLessDialog:can do father page when children page is opened
		**/
		//window.open(url,title,features);  window.showModalDialog(url,augrements,features); 
		//augrements
		/**
			below are the features
			1.dialogHeight : height  px|percent
			2.dialogWidth: width px|percent
			3.dialogLeft: 
			4.dialogTop: 
			5.center: {yes | no | 1 | 0 }
			6.help: {yes | no | 1 | 0 }
			7.resizable: {yes | no | 1 | 0 } 
			8.status: {yes | no | 1 | 0 } 
			9.scroll:{ yes | no | 1 | 0 | on | off }
			10.dialogHide:{ yes | no | 1 | 0 | on | off }
			11.edge:{ sunken | raised }
			12.unadorned:{ yes | no | 1 | 0 | on | off }
		**/
		var obj = window.showModalDialog(url,augrements,features);
		//get augrements value
		var obj=window.dialogArguments; 
		//get return value
		window.returnValue = value;
		window.close();
	}
	
</script>
</html>