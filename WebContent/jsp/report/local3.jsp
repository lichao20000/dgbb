<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../taglib.jsp"%>
<%@ page import="java.util.Calendar,java.util.Map,java.util.HashMap" %>
<%
	String dgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";

	int year = Calendar.getInstance().get(Calendar.YEAR);
	int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
	int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//小时
    int minute=Calendar.getInstance().get(Calendar.MINUTE);//分           
    int second=Calendar.getInstance().get(Calendar.SECOND);//秒 
	String today = year + "-" + month + "-" + (day+1);
    String time=hour+":"+minute+":"+second;
 
%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${reportName}</title>
<link href="${path}/css/report-style.css" rel="stylesheet" type="text/css" />
</head>
<body scroll=no>
<!-- scroll属性设置 成NO 禁用浏览器滚动条 -->
<script type="text/javascript">
	$(document).ready( function(e) {
		sui.parse();
		var grid = sui.get("datagrid1");
		grid.load();

		grid.on("load", function(e) {
			sui.mask();
			var a = e.sender;
			a.mergeColumns( [ "form_no" ]);
			grid.unmask();
			sui.unmask();
			
	   	  	document.getElementById('one').innerHTML="共<font>"+grid.getTotalCount()+"</font>条记录";

			
		});
		grid.on("drawcell", function(e) {
			var record = e.record,
				rows = e.rowIndex, 
				field = e.field;
			if (record.isColor == "Y") {
				e.rowStyle = "background: #DAE3F4;font-weight : bold;";
			}
			if(field=="form_no"){
				e.cellStyle = "background:#FFFFFF";
				}
		});
	});
</script>

<style type="text/css">
body .sui-grid-headerCell-nowrap{
	white-space:normal;
	word-break: break-all;
	word-wrap: break-word;
	line-height:15px;
}
.add_table .tdLable{
	padding-top: 3px;
	font-weight: bold;
	border-right: none;
	text-align: right;
}
.add_table .tdCondition{
	border-left: none;

}
</style>
<h1 align="center">${reportName}</h1>
<br>
<!-- 期间选择的通用组件 -->
<form id="excelForm"
	action="${path}/exportExcel.do?method=export&sql=sql90015" method="post"
	target="excelIFrame">
	<input type="hidden" name="unit" id="unit" />
	<input type="hidden" name="columns"id="excelData" /> 
	<input type="hidden" name="yearId" id="yearId" />
     <input type="hidden" name="monthId" id="monthId" />
<iframe id="excelIFrame" name="excelIFrame" style="display: none;"></iframe>
<div class="condition">
	<table class="add_table" width="100%" style="border: 1px solid  #BBBBBB;" cellspacing="0" cellpadding="0">
	<tr >
		<td width="5%" class="tdLable"><span style="color: red;">调单号：</span></td>
		<td width="8%" class="tdCondition">
			<input id="Dispatchformid" name="Dispatchform" style="border:0px;border-bottom:#000000 1px solid;"
			textField="dispatchform" valueField="dispatchformid"/>
		</td>
		
		<td width="5%" class="tdLable"><span style="color: red;">发单人：</span></td>
		<td width="8%" class="tdCondition">
			<input id="Formsenderid" name="Formsender"" style="border:0px;border-bottom:#000000 1px solid;"
			textField="formsender" valueField="formsenderid"/>
		</td>	
		
		<td width="5%" class="tdLable"><span style="color: red;">工单主题：</span></td>
		<td width="8%" class="tdCondition">
			<input id="Formtitleid" name="Formtitle" style="border:0px;border-bottom:#000000 1px solid;"
			textField="formtitle" valueField="formtitleid"/>
		</td>
	</tr>
	<tr>
		<td width="5%" class="tdLable"><span style="color: red;">*</span>业务类型：</td>
		<td width="8%" class="tdCondition">
			<dgcu:XMLDictTagLocal name="businessTypeCode" id="businessType" value="" dictId="businessType" 
					fileName="code" showFlag="DRAFT" field="" />
		</td>
		<td width="5%" class="tdLable"><span style="color: red;">*</span>工单状态：</td>
		<td width="10%" class="tdCondition">
			<dgcu:XMLDictTagLocal name="reportFormStatusCode" id="reportFormStatus" value="" dictId="reportFormStatus" 
					fileName="code" showFlag="DRAFT" field="" />
		  </td>
		  
		  <td width="5%" class="tdLable"><span style="color: red;">*</span>电路性质：</td>
		   <td width="8%" class="tdCondition">
			<dgcu:XMLDictTagLocal name="customerTypeCode" id="prdCategoryBss" value="" dictId="prdCategoryBss" 
					fileName="code" showFlag="DRAFT" field="" />
		</td>
	  </tr>
	  
	  <tr>
		<td width="5%" class="tdLable"><span style="color: red;">产品号码：</span></td>
		<td width="8%" class="tdCondition">
			<input id="Productid" name="Product" style="border:0px;border-bottom:#000000 1px solid;"
			textField="product" valueField="productid"/>
		</td>
	
		<td width="5%" class="tdLable"><span style="color: red;">*</span>起止时间：</td>
		<td width="10%" class="tdCondition" style="border-right: none;">
			<span style="float: left;padding-top: 4px; width: 30px;">起</span>
			<div class="calInputdiv" style="width: 100px; border: 0px solid #bbbbbb;">
				<input type="text" value="${paramVo.fromDate }" name="fromDate" id="fromDate" onblur="" class="calInput" style="margin-top: 2px;" />
				<i onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss','2000-01-01 00:00:00','<%=today %>')"></i>
			</div>
		</td>
		<td width="10%" class="tdCondition">
			<span style="float: left;padding-top: 4px; width: 30px;">止</span>
			<div class="calInputdiv" style="width: 100px; border: 0px solid #bbbbbb;">
				<input type="text" value="${paramVo.toDate }" name="toDate" id="toDate" onblur="" class="calInput" style="margin-top: 2px;" />
				<i onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss','2000-01-01 00:00:00','<%=today %>')"></i>
			</div>
		</td>
		<td width="40%" class="field_table_td" style="">
			<input style="margin-top: 2px;" type="button" class="redbutton" value="查询" onclick="searchs();"/>
			<input style="margin-top: 2px;" type="button" class="redbutton" value="导出" onclick="javascript:exportExcel();"/>
		</td>
	</tr>
</table>

</div>
<div id="datagrid1" class="sui-datagrid" 	style="width: 100%; height: 385px" allowResize="true" sortMode="client"
	pageSize="100" sizeList="[100,200,500]" 	url="${path}/loadReportData.do?method=loadData&sqlNo=90015" idField="id" onupdate="update"
	showPager="false" ondrawcell="onDrawCell">
	<div property="columns">
	
	<!--
		<div field="perIod" width="0" align="center"  name="perIod" dateFormat="yyyy-MM" headeralign="center">期间</div>
	-->
		<div field="form_seq" width="80" align="center" name="localCode"  allowSort="true"	headeralign="center">工单编号</div>
		<div field="endtime" width="80" align="center" allowSort="true" headeralign="center">收单日期</div>
		<div field="form_title" width="80" headeralign="center" autoEscape="true">工单标题</div>
		<div field="customertype" width="50" align="right" headeralign="center">客户类别</div>
		<div field="bizclassification" width="50" align="right" headeralign="center">业务类别</div>
		<div field="biztype" width="50" align="right" headeralign="center" ">业务类型</div>
		<div field="product_no" width="50" align="right" headeralign="center">产品号码</div>
		<div field="user_name" width="50" align="right" headeralign="center">申请人</div>
		<div field="dept_name" width="50" align="right" headeralign="center">发起部门</div>
		<div field="form_status" width="50" headeralign="center" autoEscape="true">工单状态</div>
		<div field="send_time" width="50" align="right" headeralign="center">申请人发单时间</div>
		<div field="retcount" width="50" align="right" headeralign="center" ">退单次数</div>
		<div field="reason" width="50" align="right" headeralign="center">退单原因</div>
		
		<!--<div field="isColor" width="0" align="right" headeralign="center" dataType="currency">标识列不显示</div>
	--></div>
</div>

<div id="one" style='height:40px'></div>
<script>
sui.parse();
//加载数据
function loadData() {
	//获得数据
	//sui.mask();
	
	
	var dispatchform = $("#Dispatchformid").val();//获取调单号
	var formsender = $("#Formsenderid").val();//发单人
	var formtitle = $("#Formtitleid").val();//工单主题
	var product = $("#Productid").val();//工单主题
	
	var businessTypeCode = $("#businessType").val();//获取业务类型
	var reportFormStatusCode = $("#reportFormStatus").val();//请选择工单状态
	var customerTypeCode = $("#prdCategoryBss").val();//获取工单类型
	
	
	var fromDate = $("#fromDate").val();
	
	
	var toDate = $("#toDate").val();
	
	/*
	if(toDate.length<=11)
	{
	  
		toDate+=" <%=time %>";
		//alert(toDate);
	}
	*/
	var grid = sui.get("datagrid1");
	grid.load( {
		"sqlNo" : "90015",
		"dispatchform" : dispatchform,
		"formsender" : formsender,
		"formtitle" : formtitle,
		"product" : product,
		"businessTypeCode" : businessTypeCode,
		"reportFormStatusCode" : reportFormStatusCode,
		"customerTypeCode" : customerTypeCode,
		"fromDate" : fromDate,
		"toDate" : toDate,
	});
	//sui.unmask();
	 document.getElementById('one').innerHTML="共<font>"+grid.getTotalCount()+"</font>条记录";

	
}
//导出excel
function exportExcel() {
	   var grid = sui.get("datagrid1"); 
       var columns = grid.getBottomColumns();
       
	  	document.getElementById('one').innerHTML="共<font>"+grid.getTotalCount()+"</font>条记录";

       
      var row=grid.getTotalCount();
      
       
       function getColumns(columns) {
           columns = columns.clone();
           for (var i = columns.length - 1; i >= 0; i--) {
               var column = columns[i];
               if (!column.field) {
                   columns.removeAt(i);
               } else {
                   var c = { header: column.header, field: column.field };
                   columns[i] = c;
               }
           }
           
           
           
           return columns;
       }
       
       var columns = getColumns(columns);
       var json = sui.encode(columns);                        
       document.getElementById("excelData").value = json;
       var excelForm = document.getElementById("excelForm");
       excelForm.submit();  

}
//查询
function searchs(){
	
	var dispatchform = $("#Dispatchformid").val();//获取调单号
	var formsender = $("#Formsenderid").val();//发单人
	var formtitle = $("#Formtitleid").val();//工单主题
	var product = $("#Productid").val();//工单主题
	var businessTypeCode = $("#businessType").val();//获取业务类型
	var reportFormStatusCode = $("#reportFormStatus").val();//请选择工单状态
	var customerTypeCode = $("#customerType").val();//获取工单类型

	var reportFormStatusCode = $("#reportFormStatus").val();//请选择工单状态
	var customerTypeCode = $("#customerType").val();//获取工单类型
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();

		//win_alert("","请选择业务类型1。"+reportFormStatusCode);

		//win_alert("","请选择工单类型。");
	
		//win_alert("","请选择起始日期。"+toDate);

		loadData();
		
 	  	document.getElementById('one').innerHTML="共<font>"+grid.getTotalCount()+"</font>条记录";

		
	   // var grid = sui.get("datagrid1");
	   // document.getElementById('one').innerHTML="共<font>"+grid.getTotalCount()+"</font>条记录";	
	
}

function onDrawCell(e) {
	   var url = '<%=dgPath%>dgcuvm_web/'+e.record.req_type+'/getDetail?1=1&prcsInstId='+e.record.prcs_Inst_Id+'&activityDefID=DraftActivity&formMainPkId='+
	   e.record.form_main_id+'&activityInstId='+e.record.activityinstid+'&workitemId='+e.record.workitemid+'&doFlag=1';
       var str = e.field.toString();
       
      
       if(str == 'form_no'){
    	   e.cellHtml = '<a target="_blank" href="'+url+'">' + e.value+'</a>';
 
       }
       

 
}

</script>
<span style="color: red;"><strong>说明</strong></span><strong>:</strong></p>
<p> <strong>因固网项目施工工单有新的需求，"一站式流水号"回填字段暂未添加。一些取值为空的字段，数据分配时间、备注，省调单号均未显示。	
</strong></p>
<br>
<h1><strong>注：推荐使用火狐浏览器，360浏览器极速模式，高版本的IE浏览器。</strong></h1>
<br>
<h1><strong>"工单编号"、"发单时间"菜单，可以点击进行升序降序排列。</strong></h1>
</body>
</html>