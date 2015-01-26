<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8"%>
<html>
<!--[if gte IE 7]>  
	   <!--[if IE 9]>  
         <%
         response.setHeader("X-UA-Compatible","IE=EmulateIE8");
         %>
      <![endif]-->  
<!-- [endif] -->        
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String mulLinkagePath = request.getContextPath() +"/MulLinkage/enumEration.do";
String onchange="changeChildEnum(this,"+"'"+mulLinkagePath+"')";
response.setHeader("Cache-Control", "no-cache");
%>
  	<!-- HTTP 1.1 -->
  	<meta http-equiv="Cache-Control" content="no-store" />
  	<!-- HTTP 1.0 -->
  	<meta http-equiv="Pragma" content="no-cache" />
  	<!-- Prevents caching at the Proxy Server -->
 	<meta http-equiv="Expires" content="0" />
 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/common/df.css" />  
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css" />
	<link href="<%=basePath%>js/dfCalendar/css/calendar.css" rel="stylesheet" type="text/css" />
	<link href="<%=path %>/css/common/demo.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>js/jquery.mini.1.8.3.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/df.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/dataformcheck.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/df-validate.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/dfCalendar/js/calendar.js"></script>

</head>
</html>
