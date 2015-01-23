<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.util.*,java.text.*;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
	<!-- jsp页面显示日期 星期  -->
	<%
	  Date date = new Date();
	  Calendar cal=Calendar.getInstance();
	  String dayOfWeekTime="";
	  int dayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
	  switch(dayOfWeek){
	   case 1:dayOfWeekTime="星期天";break;
	   case 2:dayOfWeekTime="星期一";break;
	   case 3:dayOfWeekTime="星期二";break;
	   case 4:dayOfWeekTime="星期三";break;
	   case 5:dayOfWeekTime="星期四";break;
	   case 6:dayOfWeekTime="星期五";break;
	   case 7:dayOfWeekTime="星期六";break;
	  }
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
	  String timeString = sdf.format(date);
	%>
	<%=timeString%> <%=dayOfWeekTime%> 
	<!-- jsp重定向 -->
	＜jsp:forward page="" /＞
	<!-- 修改HTTP header的Location属性来重定向 -->
	＜%   
	　response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);   
	　String newLocn = "/newpath/jsa.jsp";   
	　response.setHeader("Location",newLocn);   
	%＞
	<!-- JSP中实现在某页面停留若干秒后,自动重定向到另一页面,300的单位是秒 -->
	＜meta http-equiv="refresh" content="300; url=target.jsp"＞ 
	
	<!-- jsp中引入其他jsp页面，有三种方法 -->
	<span style="background-color: rgb(255, 255, 255);"><!-- 第一种：jstl import --></span>  
	<div style="color:red;">  
		<c:import url="inlayingJsp.jsp"></c:import>  
	</div>  
	<span style="background-color: rgb(255, 255, 255);"><!-- 第二种：jsp include指令 --></span>  
	<div>  
		<%@ include file="" %>  
	</div>  
	<span style="background-color: rgb(255, 255, 255);"><!-- 第三种：jsp include动作 --></span>  
	<div>  
		<jsp:include page="" flush="true"/>  
		<!-- 传入参数时用 <jsp:param name="parameterName" value="{parameterValue | EL表达式 }" /> -->  
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
		* js 弹窗
		**/
		//有两种方式：1，window.open(url,title,features);  2，window.showModalDialog(url,augrements,features); 
		//其中1父子窗口之间不能传值，2父子窗口可以传值。  1比较简单，下面是2的例子
		//augrements可以是父页面的任何dom对象，features有：
		/**
			1.dialogHeight :对话框高度，不小于100px，IE4中dialogHeight 和 dialogWidth         默认的单位是em，而IE5以上是px，为方便其见，在定义modal方式的对话框时，用px做单位。
			2.dialogWidth: 对话框宽度。
			3.dialogLeft: 离屏幕左的距离。
			4.dialogTop: 离屏幕上的距离。
			5.center: {yes | no | 1 | 0 }：窗口是否居中，默认yes，但仍可以指定高度和宽度。
			6.help: {yes | no | 1 | 0 }：是否显示帮助按钮，默认yes。
			7.resizable: {yes | no | 1 | 0 } 〔IE5＋〕：是否可被改变大小。默认no。
			8.status: {yes | no | 1 | 0 } 〔IE5+〕：是否显示状态栏。默认为yes[ Modeless]或no[Modal]。
			9.scroll:{ yes | no | 1 | 0 | on | off }：指明对话框是否显示滚动条。默认为yes。
			下面几个属性是用在HTA中的，在一般的网页中一般不使用。
			10.dialogHide:{ yes | no | 1 | 0 | on | off }：在打印或者打印预览时对话框是否隐藏。默认为no。
			11.edge:{ sunken | raised }：指明对话框的边框样式。默认为raised。
			12.unadorned:{ yes | no | 1 | 0 | on | off }：默认为no。 
		**/
		var obj = window.showModalDialog(url,augrements,features);
		//在弹窗页面，获取传递的对象
		var obj=window.dialogArguments; 
		//设置返回值
		window.returnValue = value;
		window.close();
	}
	
</script>
</html>