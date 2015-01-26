<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
 <meta http-equiv="pragma" content="no-cache">
 <meta http-equiv="cache-control" content="no-cache">
 <meta http-equiv="expires" content="0">    
 <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
 <meta http-equiv="description" content="This is my page">
 <!--
 <link rel="stylesheet" type="text/css" href="styles.css">
 -->
  </head>
  
  <body>
    <form name="form1" action="CoreServlet" method=get>
 <table width="333" border="1">
 <tr>
 <td>N1</td><td>
 <input name="N1" type="text" id=>
 </td>
 </tr>
 <tr>
 <td>N2</td><td>
 <input name="N2" type="text">
 </td>
 </tr>
 
 <tr>
 <td>N4</td><td>
 <input name="N3" type="text">
 </td>
 </tr>
 
 <tr>
 <td>N3</td><td>
 <input name="N4" type="text">
 </td>
 </tr>
 <tr>
 <td valign="top"><br></td><td> </td><td>
 <input name="submit" type="Submit" value="Ìá½»">
 </td></tr>
 </table>
</form>
  </body>
</html>
