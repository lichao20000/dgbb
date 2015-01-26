<%@page contentType="text/html; charset=UTF-8"%>
<html>
<%@include file="/head.jsp"%>
<%
String dgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<body>

  <h1>
  综合绩效报表导航
  </h1>
    <br>

    <a href="<%=dgPath %>/dgbb/showReport.do?reportId=3002&&fromFlag=fromDtt"  target="_blank"><h1><strong>1.售前支撑评分报表展示</strong> </h1></a>
	<a href="<%=dgPath %>/dgbb/showReport.do?reportId=90013&&fromFlag=fromDtt" target="_blank"><h1><strong>2.调度基本台帐报表</strong></h1></a>
    <a href="<%=dgPath %>/dgbb/showReport.do?reportId=90014&&fromFlag=fromDtt"  target="_blank"><h1><strong>3.集客拆机报表</strong></h1></a>
    <a href="<%=dgPath %>/dgbb/showReport.do?reportId=90015&&fromFlag=fromDtt"  target="_blank"><h1><strong>4.日报</strong> </h1></a>
    <a href="<%=dgPath %>/dgbb/showReport.do?reportId=90016&&fromFlag=fromDtt"  target="_blank"><h1><strong>5.调度工单在途状态</strong></h1></a>
    <a href="<%=dgPath %>/dgbb/showReport.do?reportId=90017&&fromFlag=fromDtt"  target="_blank"><h1><strong>6.工程项目概况-下钻明细表</strong></h1></a>


</body>        
            
</html>
