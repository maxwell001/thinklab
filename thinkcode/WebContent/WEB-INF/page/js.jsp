<html>
<head>
	<!-- js代码收集 -->
	<script type="text/javascript">
		//针对IE浏览器，防止敲击backspace时页面回退
		document.onkeydown = check;
		function check(e) {
		    var code;
		    if (!e) var e = window.event;
		    code = e.keyCode||e.which;
			if (((code == 8) &&  //BackSpace 
			         ((event.srcElement.type != "text" && 
			         event.srcElement.type != "textarea" && 
			         event.srcElement.type != "password") || 
			         event.srcElement.readOnly == true)) || 
			        ((event.ctrlKey) && ((code == 78) || (code == 82)) ) ||  //CtrlN,CtrlR 
			        (code == 116) ) {  //F5 
				ev.preventDefault();
			    }
		}	
	
		//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
	    function banBackSpace(e){   
	        var ev = e || window.event;//获取event对象   
	        var obj = ev.target || ev.srcElement;//获取事件源   
	        var t = obj.type || obj.getAttribute('type');//获取事件源类型  
	        //获取作为判断条件的事件类型
	        var vReadOnly = obj.getAttribute('readonly');
	        //处理null值情况
	        vReadOnly = (vReadOnly == "") ? false : vReadOnly;
	        //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
	        //并且readonly属性为true或enabled属性为false的，则退格键失效
	        var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea") 
	                    && vReadOnly=="readonly")?true:false;
	        //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
	        var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")
	                    ?true:false;        
	        //判断
	        if(flag2){
	            ev.preventDefault();
	        }
	        if(flag1){   
	        	ev.preventDefault();
	        }   
	    }
	    //监听键盘事件，禁止后退键 作用于Firefox、Opera
	    document.onkeypress=banBackSpace;
	    //监听键盘事件，禁止后退键  作用于IE、Chrome
	    document.onkeydown=banBackSpace;
	</script>
</head>
</html>