<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="com.dglt.bb.pojo.WKipConfigV"%>
<%@include file="../../taglib.jsp"  %>
<%@page import="java.util.Date"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	request.setCharacterEncoding("UTF-8");
	response.setHeader("progma", "no-cache"); //屏蔽页面缓存
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//期间
String	monthId = (String)request.getParameter("monthId")==null?"":request.getParameter("monthId");
String	monthName = (String)request.getParameter("monthName")==null?"":request.getParameter("monthName");
monthName=java.net.URLDecoder.decode(monthName,"utf-8");
//专业
String	spname  = (String)request.getParameter("spname")==null?"":request.getParameter("spname");
String	spcode  = (String)request.getParameter("spcode")==null?"":request.getParameter("spcode");
spname=java.net.URLDecoder.decode(spname,"utf-8");
//客户
String	clientName   = (String)request.getParameter("clientName")==null?"":request.getParameter("clientName");
String	clientCode   = (String)request.getParameter("clientCode")==null?"":request.getParameter("clientCode");
clientName=java.net.URLDecoder.decode(clientName,"utf-8");
//产品
String	productCode  = (String)request.getParameter("productCode")==null?"":request.getParameter("productCode");
String	productName  = (String)request.getParameter("productName")==null?"":request.getParameter("productName");
productName=java.net.URLDecoder.decode(productName,"utf-8");
//指标
String	kpiId  = (String)request.getParameter("kpiId")==null?"":request.getParameter("kpiId");
String	kpiName  = (String)request.getParameter("kpiName")==null?"":request.getParameter("kpiName");
kpiName=java.net.URLDecoder.decode(kpiName,"utf-8");
//
String	userName  = (String)request.getParameter("userName")==null?"系统管理员":request.getParameter("userName");
userName=java.net.URLDecoder.decode(userName,"utf-8");
Date date=new Date(); 
%>
<style type="text/css">
  .font_css{font-family:"微软雅黑";font-size:10pt}
  .div_css{font-family:"微软雅黑";font-size:20pt}
</style>
</head>
<body>
    <div>
        <div class="sui-toolbar">
                     　　　　      <font class="font_css" >报表期间: <input id="monthName" name="monthName" class="sui-textbox" value="<%= monthName%>" readonly="readonly"/></font>   
                  <fontclass="font_css" >标注人     :  <input id="userName" name="userName" class="sui-textbox" value="<%= userName%>"/>      </font>   
                  <a class="sui-button"  style="width:60px;" onclick="search()"> <font class="font_css" >查询</font></a>      
        </div>
     <div id="datagrid1" class="sui-datagrid" style="height:260px;"  showPager="fasle" 
        url="${path}/shuntJumpAction.do?method=taggingQuery"  idField="id" multiSelect="true" >
        <div property="columns">
            <!--<div type="indexcolumn"></div>        -->     
            <div type="indexcolumn" width="4%" headerAlign="center" class="div_css" >   <font class="font_css" >序号</font></div> 
            <div field="title" width="20%" headerAlign="center" class="div_css" ><font class="font_css" >主题</font></div>
            <div field="content" width="30%" headerAlign="center" class="div_css" ><font class="font_css" >内容</font></div>       
            <div field="userName" width="10%" headerAlign="center" class="div_css" ><font class="font_css" >标注人</font></div> 
            <div field="creationDate" width="10%"" headerAlign="center" dateFormat="yyyy-MM-dd"  class="div_css"><font class="font_css" >发布时间</font></div>   
           <div name="action" width="15%"" headerAlign="center" align="center" renderer="onActionRenderer" cellStyle="padding:0;" class="div_css"><font class="font_css" >操作</font></div>
         </div>                     
     </div>
       <div style="text-align:center;padding:4px;width:100%; border-color: black">               
              <a class="sui-button" onclick="add()" style="width:60px;margin-right:20px;"> <font class="font_css" >发布</font></a>    
              <a class="sui-button" onclick="onCancel" style="width:60px;"> <font class="font_css" >关闭</font></a>      
          </div> 
    </div> 
<script type="text/javascript">


    var grid  ;
    
    $(document).ready(
			function(e){
				sui.parse();
				grid = sui.get("datagrid1");
				init();
			}
		);
         function init(){
              search() ;
             }
         
        function add() { 
             var  monthId ="<%=monthId%>"
             var  monthName ="<%=monthName%>"
             var  spname ="<%=spname%>"
             var  spcode ="<%=spcode%>"
             var  clientName ="<%=clientName%>"
             var  clientCode ="<%=clientCode%>"
             var  productCode ="<%=productCode%>"
             var  productName ="<%=productName%>"
             var  kpiId ="<%=kpiId%>"
             var  kpiName ="<%=kpiName%>"
             var  userName ="<%=userName%>"
             var  creationDate ="<%=date.toGMTString()%>"
                // alert(kpiId);
            sui.open({
                url:"${path}/jsp/tagging/tagInsert.jsp",
                title: "标注新增页面", width: 600, height: 360,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "new","monthId":monthId,"monthDsc":monthName,"spname":spname,"spcode":spcode,"clientName":clientName,"clientCode":clientCode,"productCode":productCode,"productName":productName,"kpiId":kpiId,"kpiName":kpiName,"userName":userName,"creationDate":creationDate};
                    iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    grid.reload();
                }
            });
        }

  
        function search() {
        	 sui.parse();
        	var monthId = "<%=monthId%>";
          //  alert(monthId);
        	var spcode = "<%=spcode%>";
        	var kpiId=  "<%=kpiId%>";
        	var clientCode = "<%=clientCode%>";
        	var productCode = "<%=productCode%>";
        	var userName = "<%=userName%>";
        	userName = sui.get("userName").value;
            grid.load({ "monthId": monthId , "spcode": spcode , "clientCode": clientCode ,"productCode": productCode ,"userName": userName ,"kpiId":kpiId});
        }
        /////////////////////////////////////////////////
        function onBirthdayRenderer(e) {
            var value = e.value;
            if (value) return sui.formatDate(value, 'yyyy-MM-dd');
            return "";
        }


	  function onActionRenderer(e) {
          var grid = e.sender;
          var record = e.record;
          var row =e.row ;
          var uid = record._uid;
          var rowIndex = e.rowIndex;
          var s = ' <a class="Edit_Button" href="javascript:taggingReply(\'' + rowIndex + '\')" >回复</a>'
                  + ' <a class="Delete_Button" href="javascript:taggingHistory(\'' + rowIndex + '\')">历吏</a>';
          return s;
      }
   
  function taggingReply(rowIndex){
	  var b=grid.data[rowIndex] ;
	  var row =grid.getRow(rowIndex);

	//   alert(row.taggingId);
	  sui.open({
          url: "${path}/jsp/tagging/tagReply.jsp",
          title: "标注回复页面", width: 600, height: 400,
          onload: function () {
              var iframe = this.getIFrameEl();
              var data = b;
              iframe.contentWindow.SetData(data); 
          },
          ondestroy: function (action) {
              grid.reload();
              
          }
      });

	  
      return ;
	  }
  function taggingHistory(rowIndex){
	  var b=grid.data[rowIndex] ;
	  var row =grid.getRow(rowIndex);
	  sui.open({
          url: "${path}/jsp/tagging/tagHistory.jsp",
          title: "标注回复历史查看", width: 600, height: 460,
          onload: function () {
              var iframe = this.getIFrameEl();
              var data = b;
              iframe.contentWindow.SetData(data); 
          },
          ondestroy: function (action) {
              grid.reload();    
          }
      });
      return ;
	  }

  function onCancel(e) {
      CloseWindow("cancel");
  }
  
  function CloseWindow(action) {            
      if (action == "close" && form.isChanged()) {
          if (confirm("数据被修改了，是否先保存？")) {
              return false;
          }
      }
      if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
      else window.close();            
  }      
</script>
</body>
</html>