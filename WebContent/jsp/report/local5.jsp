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
	action="${path}/exportExcel.do?method=export&sql=sql90017" method="post"
	target="excelIFrame">
	<input type="hidden" name="unit" id="unit" />
	<input type="hidden" name="columns"id="excelData" /> 
	<input type="hidden" name="yearId" id="yearId" />
     <input type="hidden" name="monthId" id="monthId" />
<iframe id="excelIFrame" name="excelIFrame" style="display: none;"></iframe>
<div class="condition">
	<table class="add_table" width="100%" style="border: 1px solid  #BBBBBB;" cellspacing="0" cellpadding="0">
	<tr >
		<td width="5%" class="tdLable"><span style="color: red;">工单编号：</span></td>
		<td width="8%" class="tdCondition">
			<input id="Form_seqid" name="Form_seq" style="border:0px;border-bottom:#000000 1px solid;"
			textField="form_seq" valueField="form_seqid"/>
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
		<td width="40%" class="field_table_td" style="">
			<input style="margin-top: 2px;" type="button" class="redbutton" value="查询" onclick="searchs();"/>
		</td>
	</tr>
	<tr>
		<td width="5%" class="tdLable"><span style="color: red;">*</span>业务类型：</td>
		<td width="8%" class="tdCondition">
			<dgcu:XMLDictTagLocal name="bizTypeCode" id="bizType" value="" dictId="bizType" 
					fileName="code" showFlag="DRAFT" field="" />
		</td>

		  <td width="5%" class="tdLable"><span style="color: red;">*</span>按分公司：</td>
		   <td width="8%" class="tdCondition">
			<dgcu:XMLDictTagLocal name="lanchAreaCode" id="lanchArea" value="" dictId="lanchArea" 
					fileName="code" showFlag="DRAFT" field="" />
		</td>
				<td width="5%" class="tdLable"><span style="color: red;">*</span>按网格：</td>
		<td width="10%" class="tdCondition">
			<dgcu:XMLDictTagLocal name="blongTeamCode" id="blongTeam" value="" dictId="blongTeam" 
					fileName="code" showFlag="DRAFT" field="" />
		  </td>
		<td>
		<input style="margin-top: 2px;" type="button" class="redbutton" value="导出" onclick="javascript:exportExcel();"/>
		</td>
		
	  </tr>
	  
	  <tr>
		<td width="6%" class="tdLable"><span style="color: red;">*</span>审批工单状态：</td>
		<td width="10%" class="tdCondition">
			<dgcu:XMLDictTagLocal name="applyFormStatusCode" id="applyFormStatus" value="" dictId="reportFormStatus" 
					fileName="code" showFlag="DRAFT" field="" />
		  </td>
		<td width="6%" class="tdLable"><span style="color: red;">*</span>施工工单状态：</td>
		<td width="10%" class="tdCondition">
			<dgcu:XMLDictTagLocal name="cstFormStatusCode" id="cstFormStatus" value="" dictId="reportFormStatus" 
					fileName="code" showFlag="DRAFT" field="" />
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


	</tr>
</table>

</div>
<div id="datagrid1" class="sui-datagrid" 	style="width: 100%; height: 385px" allowResize="true" sortMode="client"
	pageSize="100" sizeList="[100,200,500]" 	url="${path}/loadReportData.do?method=loadData&sqlNo=90017" idField="id" onupdate="update"
	showPager="false" ondrawcell="onDrawCell">
	<div property="columns">
	
	<!--
		<div field="perIod" width="0" align="center"  name="perIod" dateFormat="yyyy-MM" headeralign="center">期间</div>
	-->
	
		<div field="apply_form_title" width="80" headeralign="center" autoEscape="true">工单标题</div>
		<div field="dept_name" width="80" align="right" headeralign="center">发起部门</div>
		<div field="apply_form_is_cross_domain" width="80" align="right" headeralign="center" ">是否跨域</div>
		<div field="cust_level" width="80" align="right" allowSort="true" headeralign="center">客户星级</div>
		<div field="apply_form_lanch_area_name" width="80" align="right" headeralign="center">落地分公司</div>
		<div field="apply_form_blongTeam" width="80" align="right" headeralign="center">落地网格</div>
		<div field="bss_pdc_no" width="80" headeralign="center" autoEscape="true">产品号</div>
		<div field="estimated_income" width="80" align="right" headeralign="center">效益评估的收入</div>
		<div field="prj_act_invt_amount" width="80" align="right" allowSort="true" headeralign="center" ">投资金额</div>
		<div field="additional_income" width="80" align="right" headeralign="center">新增月租</div>
		<div field="contract_term" width="80" align="right" headeralign="center">合同期</div>
		<div field="start_charge_time" width="80" align="right" headeralign="center" ">具体计费时间</div>
		<div field="send_user_id" width="80" align="right"  headeralign="center">发单工号</div>
		<div field="apply_form_seq" width="80" align="right" allowSort="true" headeralign="center">审批工单号</div>
		<div field="apply_form_status" width="80" align="right" headeralign="center">审批状态</div>
		<div field="apply_form_sendtime" width="80" align="right" headeralign="center">发单时间</div>
		<div field="apply_form_endtime" width="80" headeralign="center" autoEscape="true">结束时间</div>
		<div field="apply_form_dealtime" width="80" align="right" headeralign="center">审批时长</div>
		<div field="cst_form_seq" width="80" align="right" allowSort="true" headeralign="center" ">施工单号</div>
		<div field="cst_form_sendtime" width="80" align="right" allowSort="true" headeralign="center">发单时间</div>
		<div field="prj_impl_period" width="80" align="right" allowSort="true" headeralign="center">施工时限</div>
		<div field="limit_time" width="80" align="right" headeralign="center">计划完工时间</div>
		<div field="cst_form_endtime" width="80" align="right" headeralign="center">实际完成时间</div>
		<div field="cst_form_dealtime" width="80" headeralign="center" autoEscape="true">施工时长</div>
		<div field="cst_form_overtime" width="80" align="right" headeralign="center">超时时间</div>
		<div field="cst_form_isovertime" width="80" align="right" headeralign="center" ">是否超时</div>
		<div field="cst_form_status" width="80" align="right" headeralign="center">施工单状态</div>
		
		
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
	
	
	var form_seq = $("#Form_seqid").val();//获取调单号
	var formsender = $("#Formsenderid").val();//发单人
	var formtitle = $("#Formtitleid").val();//工单主题
	var applyFormStatusCode = $("#applyFormStatus").val();//请选择工单状态
	var cstFormStatusCode = $("#cstFormStatus").val();//请选择工单状态
	
	var bizTypeCode = $("#bizType").val();//获取业务类型
	var blongTeamCode = $("#blongTeam").val();//请选择工单状态
	var lanchAreaCode = $("#lanchArea").val();//获取工单类型
	
	
	var fromDate = $("#fromDate").val();
	
	
	var toDate = $("#toDate").val();
	
	var grid = sui.get("datagrid1");
	grid.load( {
		"sqlNo" : "90017",
		"form_seq" : form_seq,
		"formsender" : formsender,
		"formtitle" : formtitle,
		"bizTypeCode" : bizTypeCode,
		"blongTeamCode" : blongTeamCode,
		"lanchAreaCode" : lanchAreaCode,
		"applyFormStatusCode" : applyFormStatusCode,
		"cstFormStatusCode" : cstFormStatusCode,
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
	
	var form_seq = $("#Form_seqid").val();//获取调单号
	var formsender = $("#Formsenderid").val();//发单人
	var formtitle = $("#Formtitleid").val();//工单主题
	var product = $("#Productid").val();//工单主题
	var bizTypeCode = $("#bizType").val();//获取业务类型
	var blongTeamCode = $("#blongTeam").val();//请选择工单状态
	var lanchAreaCode = $("#lanchAreaCode").val();//获取工单类型
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();

		//win_alert("","请选择业务类型1。"+blongTeamCode);

		//win_alert("","请选择工单类型。");
	
		//win_alert("","请选择起始日期。"+toDate);

		loadData();
		
 	  	document.getElementById('one').innerHTML="共<font>"+grid.getTotalCount()+"</font>条记录";

		
	   // var grid = sui.get("datagrid1");
	   // document.getElementById('one').innerHTML="共<font>"+grid.getTotalCount()+"</font>条记录";	
	
}

function onDrawCell(e) {

       var str = e.field.toString();
 
       if(str == 'apply_form_seq'){
    	   var url = '<%=dgPath%>dgcuvm_web/'+e.record.apply_form_req_type+'/getDetail?1=1&prcsInstId='+e.record.apply_form_prcs_inst_id+'&activityDefID=DraftActivity&formMainPkId='+
    	   e.record.apply_form_pk_id+'&activityInstId='+e.record.apply_form_activityinstid+'&workitemId='+e.record.apply_form_workitemid+'&doFlag=1';
    	   
    	   e.cellHtml = '<a target="_blank" href="'+url+'">' + e.value+'</a>';
 
       }
       
       if(str == 'cst_form_seq'){
    	   var url = '<%=dgPath%>dgcuvm_web/'+e.record.cst_form_req_type+'/getDetail?1=1&prcsInstId='+e.record.cst_form_prcs_inst_id+'&activityDefID=DraftActivity&formMainPkId='+
    	   e.record.cst_form_pk_id+'&activityInstId='+e.record.cst_form_activityinstid+'&workitemId='+e.record.cst_form_workitemid+'&doFlag=1';
    	   
    	   e.cellHtml = '<a target="_blank" href="'+url+'">' + e.value+'</a>';
 
       }

 
}
</script>
<span style="color: red;"><strong>说明</strong></span><strong>:</strong></p>
<p> <strong>仅支持固网查询。	
</strong></p>
<br>
<h1><strong>注：推荐使用火狐浏览器，360浏览器极速模式，高版本的IE浏览器。</strong></h1>
</body>
</html>