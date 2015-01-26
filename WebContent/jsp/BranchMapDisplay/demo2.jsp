<%@ page language="java" import="java.util.*" 
    pageEncoding="utf-8"%>
<%@include file="../../taglib.jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
     $(document).ready(
 			function(e){
 				sui.parse();
 				//标签页转换方法设置
 				sui.get("tabs").on("activechanged",function(e){
 					sui.get("tabs").activeTab (2);
    				if(e.tab.title=="Tab2"){
        				//重新加载长期欠费率饼图
    					alert(sui.get("tabs").activeIndex);
    				}else if(e.tab.title=="Tab3"){
    					alert(sui.get("tabs").activeIndex);
    				}
    			});
 			}
 		);
</script>
</head>
<body>
  <div id="tabs" class="sui-tabs" activeIndex="0"  style="width:350px;height:200px;">
    <div title="Tab1">
        1
    </div>
    <div title="Tab2" iconCls="icon-cut" headerStyle="font-Family:'微软雅黑',Serif;font-size:14px;">
        2
    </div>
    <div title="Tab3" showCloseButton="true" >
        3
    </div>
    <div title="Tab4" showCloseButton="true" enabled="false">
        4
    </div>
</div>
</body>
</html>