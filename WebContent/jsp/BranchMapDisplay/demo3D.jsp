<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../../taglib.jsp"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>FusionChart3D</title>
	<script type="text/javascript" src="<%=basePath%>js/FusionCharts.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
var aaa;
	function showAnchor(){
		var myChart = new FusionCharts("../../swf/Pie3D.swf","myChartId", "470","240","0","1");
		 myChart.setDataURL("../../datas/pie3D.xml");
		 myChart.render("Income3D");
		}
	function changeFunsion(3Dpie,value){
		aaa=3Dpie;
		$.ajax({
			type:"post",
			dataType:"josn",
			data:{3Dpie,laber},
			success:function(data){
		    if(data.result=="0"{
		    	alert("获取失败");
			}
			else{
				alert("获取成功");    
			}
			},
			error:function(data){
				alert("获取错误");
			}
		})
		$("#fosiontext").text(a);
		
</script>
  </head>
  
  <body onload="showAnchor();">
    <div  id=Income3D></div>
   
     
</body>
</html>
