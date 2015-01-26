<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../taglib.jsp"%>
<%@ page import="java.util.Calendar,java.util.Map,java.util.HashMap" %>
<%
String dgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";

	int year = Calendar.getInstance().get(Calendar.YEAR);
	int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
	int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	String today = year + "-" + month + "-" + day;	

%>
<!DOCTYPE HTML>
<html>
<head>
<%@include file="/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${reportName}</title>
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
<script type="text/javascript">
$(document).ready(function(e){
	    //联动方法路径
		var mulLinkagePath = '<%=mulLinkagePath %>';

		$("#bizType").change(function(){
			$("#testLenovo").val("");
			$("#testLenovo").prev().val("");
			$("#reqType_input").val("");
			$("#reqType_input").prev().val("");
			var thisValue = this.value;
			var childId = "workOrderType";
			
			$.ajax({
				  type: "POST",
				  url: mulLinkagePath,
				  data: {value:thisValue, id:childId},
				  success: function(str1){
					  var strArr = eval(str1);
					  if(strArr.length != 0){
						  initdfLenovo("testLenovo", {lenovoData:strArr});
						  if(strArr.length == 1){
							  $("#testLenovo").val(strArr[0].text).prev().val(strArr[0].value);
						  }
					  }else{
						  initdfLenovo("testLenovo", {lenovoData:[{value:"none",text:"级联结果为空"}]});
					  }
					},
				  async :false,
				  dataType: 'text'
				});
		}).change();

		$("#testLenovo").change(function(){
			$("#reqType_input").val("");
			$("#reqType_input").prev().val("");
			var thisValue = $(this).prev().val();
			var childId = "reqType";
			
			$.ajax({
				  type: "POST",
				  url: mulLinkagePath,
				  data: {value:thisValue, id:childId},
				  success: function(str1){
					  var strArr = eval(str1);
					  if(strArr.length != 0){
						  initdfLenovo("reqType_input",{lenovoData:strArr});
						  if(strArr.length == 1){
							  $("#reqType_input").val(strArr[0].text).prev().val(strArr[0].value);
						  }
					  }else{
						  initdfLenovo("reqType_input", {lenovoData:[{value:"none",text:"级联结果为空"}]});
					  }
					},
				  async :false,
				  dataType: 'text'
				});
		});
		
		sui.parse();
		var grid = sui.get("datagrid1");
		grid.on("load", function(e) {
			sui.mask();
			var a = e.sender;
			a.mergeColumns( [ "localCode" ]);
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
			if(field=="localCode"){
				e.cellStyle = "background:#FFFFFF";
				}
		});
	});
</script>
</head>
<body scroll=no>
<!-- scroll属性设置 成NO 禁用浏览器滚动条 -->
<h1 style="text-align:center;line-height:30px">${reportName}</h1>
<!-- 期间选择的通用组件 -->
<form id="excelForm"
	action="${path}/exportExcel.do?method=export&sql=sql3002" method="post"
	target="excelIFrame">
	<input type="hidden" name="unit" id="unit" />
	<input type="hidden" name="columns"id="excelData" /> 
<iframe id="excelIFrame" name="excelIFrame" style="display: none;"></iframe>
<div class="condition">
	<table class="add_table" width="100%" style="border: 1px solid  #BBBBBB;" cellspacing="0" cellpadding="0">
	<tr >
	<!--查询 -->
		<td class="main_top_bg" colspan="10" style="border:0;text-align:left;padding-left: 10px;"><b>查询条件</b></td>
	</tr>
	<tr>
		<td width="5%" class="tdLable"><span style="color: red;">*</span>业务类型：</td>
		<td width="8%" class="tdCondition">
			<dgcu:XMLDictTagLocal name="bizTypeCode" id="bizType" value="" dictId="bizType" 
					fileName="code" showFlag="DRAFT" field="" />
		</td>
		<td width="5%" class="tdLable"><span style="color: red;">*</span>需求类型：</td>
		<td width="10%" class="tdCondition">
			<div class="downInputTextDIV30"
				style="width: 100%; border: 0px solid #bbbbbb; float: left;">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td><input type="hidden" value="${paramVo.workOrderType }" class="dfLenovoHiiden" 
						id="workOrderType" name="workOrderType" />
							<input type="text" value="" class="dfLenovoInput"
							id="testLenovo"/></td>
						<td class="downInputTextImg">
							<div onclick="" class="dfLenovoI"></div>
						</td>
					</tr>
				</table>
		 	</div>
		  </td>
		  
		  <td width="5%" class="tdLable"><span style="color: red;">*</span>工单类型：</td>
		   <td width="8%" class="tdCondition">
			<div class="downInputTextDIV30"
				style="width: 100%; border: 0px solid #bbbbbb; float: left;">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td><input type="hidden" value="" class="dfLenovoHiiden"
							name="formTypeCode" id="reqType" value="${paramVo.formTypeCode }" />
						<input type="text" value=""
							class="dfLenovoInput" id="reqType_input"/>
						</td>
						<td class="downInputTextImg">
							<div onclick="" class="dfLenovoI"></div>
						</td>
					</tr>
				</table>
			</div>
		</td>
	  </tr>
	  
	  <tr>
		<td width="5%" class="tdLable"><span style="color: red;">*</span>时间类型：</td>
		<td width="8%" class="tdCondition">
		<dgcu:XMLDictTagLocal name="timeType" id="timeType" value="${paramVo.timeType}" dictId="timeType" 
					fileName="code" showFlag="DRAFT" field="" />
		</td>
	
		<td width="5%" class="tdLable"><span style="color: red;">*</span>起止时间：</td>
		<td width="10%" class="tdCondition" style="border-right: none;">
			<span style="float: left;padding-top: 4px; width: 30px;">起</span>
			<div class="calInputdiv" style="width: 100px; border: 0px solid #bbbbbb;">
				<input type="text" value="${paramVo.fromDate }" name="fromDate" id="fromDate" onblur="" class="calInput" style="margin-top: 2px;" />
				<i onclick="SelectDate(this,'yyyy-MM-dd','2000-01-01','<%=today %>')"></i>
			</div>
		</td>
		<td width="10%" class="tdCondition">
			<span style="float: left;padding-top: 4px; width: 30px;">止</span>
			<div class="calInputdiv" style="width: 100px; border: 0px solid #bbbbbb;">
				<input type="text" value="${paramVo.toDate }" name="toDate" id="toDate" onblur="" class="calInput" style="margin-top: 2px;" />
				<i onclick="SelectDate(this,'yyyy-MM-dd','2000-01-01','<%=today %>')"></i>
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
	pageSize="100" sizeList="[100,200,500]" 	url="${path}/loadReportData.do?method=loadData" idField="id" 
	showPager="false" onDrawCell="onDrawCell" >
	<div property="columns">
	    <div field="form_seq" width="80" align="center" name="localCode" headeralign="center">工单编号</div>
		<div field="send_time" width="120" align="center"  headeralign="center">发单日期</div>
		<div field="form_title" width="120" align="right" headeralign="center" >工单标题</div>
		<div field="assessment_score" width="120" align="right" headeralign="center">评分</div>
		<div field="user_name" width="120" align="right" headeralign="center">评分人员</div>
		<div field="partiname" width="120" align="right" headeralign="center">被评人员</div>
		<div field="dept_name" width="120" align="right" headeralign="center">所属部门</div>
		<div field="assessment_time" width="120" align="right" headeralign="center">评分时间</div>
		<div field="enum_value_meaning" width="120" align="right" headeralign="center">评分说明</div>
	</div>
</div>
<div id="one" style='height:40px'></div>
<script>
	sui.parse();
	//加载数据
	function loadData() {
		//获得数据
		//sui.mask();
		var bizTypeCode = $("#bizType").val();//获取业务类型
    	var formTypeCode = $("#reqType").val();//获取工单类型
    	var fromDate = $("#fromDate").val();//开始时间
    	var toDate = $("#toDate").val();//结束时间
    	var timeType = $("#timeType").val();//时间类型
		
		var grid = sui.get("datagrid1");
		grid.load( {
			"sqlNo" : "3002",
			"bizTypeCode" : bizTypeCode,
			"formTypeCode" : formTypeCode,
			"fromDate" : fromDate,
			"toDate" : toDate,
			"timeType" : timeType
		});
		//sui.unmask();
		 document.getElementById('one').innerHTML="共<font>"+grid.getTotalCount()+"</font>条记录";

	}
	//导出excel
	function exportExcel() {
		   var grid = sui.get("datagrid1"); 
	       var columns = grid.getBottomColumns();
	       
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
    	var bizTypeCode = $("#bizType").val();//获取业务类型
    	var formTypeCode = $("#reqType").val();//获取工单类型
    	var fromDate = $("#fromDate").val();
    	var toDate = $("#toDate").val();
    	if(bizTypeCode == '' || bizTypeCode == null){
    		win_alert("","请选择业务类型。");
    	}else if(formTypeCode == '' || formTypeCode == null){
    		win_alert("","请选择工单类型。");
    	}else if(fromDate == '' || fromDate == null){
    		win_alert("","请选择起始日期。");
    	}else if(toDate == '' || toDate == null){
    		win_alert("","请选择结束日期。");
    	}else{
    		//$("form:first").attr("action","<%=path %>/timeEffectiveRank/getFormHisStat");
    		//$("form:first").submit();
    		loadData();
    		document.getElementById('one').innerHTML="共<font>"+grid.getTotalCount()+"</font>条记录";

    	}
    }
   
    function onDrawCell(e) {
    	   var url = '<%=dgPath%>dgcuvm_web/'+e.record.req_type+'/getDetail?1=1&prcsInstId='+e.record.prcs_Inst_Id+'&activityDefID=DraftActivity&formMainPkId='+
    	   e.record.form_main_id+'&activityInstId='+e.record.activityinstid+'&workitemId='+e.record.workitemid+'&doFlag=1';
           var str = e.field.toString();
           if(str == 'form_seq'){
        	   e.cellHtml = '<a target="_blank" href="'+url+'">' + e.value+'</a>';
           }
    }

</script>
</body>

<span style="color: red;"><strong>说明</strong></span><strong>:</strong></p>
<p> <strong>目前因业务关系，只支持“固网”和“ICT”的售前支撑评分功能。	
</strong></p>
<br>
<h1><strong>注：推荐使用火狐浏览器，360浏览器极速模式，高版本的IE浏览器。</strong></h1>
</html>