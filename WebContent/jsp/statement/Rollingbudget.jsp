<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../taglib.jsp"%>
<html>
<head>
<!-- 009-1.6综合绩效报表-成本利润-滚动预算 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${reportName}</title>
<link href="${path}/css/report-style.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">
	$(document).ready( function(e) {
		sui.parse();
		var grid = sui.get("datagrid1");
		grid.load();
		var obj = sui.get("Formperid");
		var date = obj.data[0].rowId;
		obj.setValue(date);
		grid.on("load", function(e) {
			sui.mask();
			var a = e.sender;
			grid.unmask();
			sui.unmask();
		});

		var monthId = sui.get("Formperid").text;
		var grid = sui.get("datagrid1");
			monthId = monthId.substring(4,6);
		if(monthId=="Q3"){
			grid.updateColumn("fourthQuarterBudget", {header:"四季度预测"});
			grid.updateColumn("budget", {header:"预算值（三季度）"});
			} 
		
		if(monthId=="Q2"){
			grid.updateColumn("fourthQuarterBudget", {header:"四季度预测"});
			grid.updateColumn("fourthQuarterBudget", {header:"四季度预测"});
			grid.updateColumn("budget", {header:"预算值（三季度）"});
			} 

		if(monthId=="Q1"){
			grid.updateColumn("fourthQuarterBudget", {header:"四季度预测"});
			grid.updateColumn("fourthQuarterBudget", {header:"四季度预测"});
			grid.updateColumn("budget", {header:"预算值（三季度）"});
			} 
	});
</script>
<script type="text/css">
body .sui-grid-headerCell-nowrap{
	white-space:normal;
	word-break: break-all;
	word-wrap: break-word;
	line-height:15px;
}
</script>
<body>
<h1 align="center">${reportName}</h1>
<!-- 期间选择的通用组件 -->
 <form id="excelForm"
	action="${path}/exportExcel.do?method=export&sql=sql5" method="post"
	target="excelIFrame">
	<input type="hidden" name="unit" id="unit" />
	<input type="hidden" name="columns"id="excelData" /> 
	<input type="hidden" name="rowId" id="rowId" />
	</form>
<iframe id="excelIFrame" name="excelIFrame" style="display: none;"></iframe>
<div class="condition">季度:&nbsp;
<input id="Formperid" name="Formperid" class="sui-combobox"
	textField="perIod" valueField="rowId" onvaluechanged="onFormperChange"
	url="${path}/getForm.do?method=getquarter"
	onvaluechanged="onDateChange" />
	&nbsp;&nbsp;&nbsp;&nbsp;<input type="button"
	value="导出Excel" onclick="ExportExcel()" style="margin-left: 50px;" />	
	<div style="float: right;width:180px;"><span style="float: left;">单位：<span><div id="rbl" class="sui-radiobuttonlist" style="float: right;" repeatItems="2" repeatLayout="table" repeatDirection="horizontal"
    textField="text" valueField="id" value="1" data="[{'id':'1','text':'元'},{'id':'2','text':'万元'}]" ></div> 
    </div>
</div>
<div id="datagrid1" class="sui-datagrid"
	style="width: 100%; height: 385px" allowResize="true" sortMode="client"
	url="${path}/getForm.do?method=homePage&sql=sql5" idField="id"
	onpreload="onpreload" showPager="false" allowAlternating="true" ondrawcell="onDrawCell">
<div property="columns"><!--<div type="indexcolumn">序号</div>    -->
<!--<div field="perIod" width="0" align="center" 
	name="perIod" dateFormat="yyyy-MM" headeralign="center">期间（月份）</div>
--><div field="reportKpiName" width="250"  autoEscape="true" 
	headeralign="center">项目</div>
<div field="quarterFullCostBudget" width="120" align="right"
	 headeralign="center" decimalPlaces="2"
	dataType="currency">全成本年度预算</div>
<div name="budget" header="预算值" headerAlign="center">
<div property="columns">
<div name="firstQuarterBudget" header="一季度达到" field="firstQuarterBudget" width="120" headeralign="center"
	align="right" decimalPlaces="2" dataType="currency"></div>
<div name="secondQuarterBudget" header="二季度达到" field="secondQuarterBudget" width="120" headeralign="center"
	align="right" decimalPlaces="2" dataType="currency"></div>
<div name="thirdQuarterBudget"  header="三季度达到" field="thirdQuarterBudget" width="120" headeralign="center"
	align="right" decimalPlaces="2" dataType="currency"></div>
<div name="fourthQuarterBudget" header="四季度达到"  field="fourthQuarterBudget" width="120" headeralign="center"
	align="right" decimalPlaces="2" dataType="currency"></div>
<div field="yearAccumulation" width="120" headeralign="center"
	align="right" decimalPlaces="2" dataType="currency">年度累计</div>
</div>
</div>
<div field="overBudget" width="120" align="right" 
	headeralign="center" decimalPlaces="2" dataType="currency">超预算</div>
</div>
</div>
</body>
<script>
	sui.parse();
	function onpreload(e) {
		var a = 0;
	}
	function onbeforeload(e) {
		var a = 0;
	}
	function onFormperChange(e) {
		loadData();
	}
	function loadData(getdatedata) {
		var _path = "${path}";
		var monthId = sui.get("Formperid").text;//获取当前选中的季度值
		//获得数据
		sui.mask();
		var grid = sui.get("datagrid1");
		grid.load( {
			"monthId" :monthId
		});
		
		var monthId = sui.get("Formperid").text;
		var grid = sui.get("datagrid1");
		if(monthId=="1000001"){
			grid.updateColumn("thirdQuarterBudget", {header:"三季度预测"});
			grid.updateColumn("fourthQuarterBudget", {header:"四季度预测"});
			grid.updateColumn("budget", {header:"预算值（三季度）"});
			} 	
		
		sui.unmask();
	}
	 var rbl = sui.get("rbl");
	 var flag = false;
    rbl.on("valuechanged", function (e) {
    	if(e.value == "2"){
	    	
    		flag=true;
        	}else{
				flag=false;
            	}
    	loadData();
    });
	    function onDrawCell(e) {
	        if(flag){
	           var str = e.field.toString();
	           var key ="";
	           var active=true;
	           if(str=="reportKpiName"){
	        	   active=false;
	               }
	           if(active){
	        	   var rs = parseFloat(((e.value=="undefined"||e.value==null||e.value=="")?0:e.value).toString())/10000;
	        	   var showValue = rs.toFixed(2).toString();
	        	   var index = showValue.indexOf(".");
	        	   if(index>3){
	            	   var t=0;
	            	   var value="";
	            	    var ceValue=showValue.substring(0,(index%3==0?3:index%3));
						for(var i=0;i<index/3-1;i++){
								value = showValue.substring((index%3==0?3:index%3)+t*i,(index%3==0?3:index%3)+t*i+3);
								ceValue=ceValue+","+value;
							}
							e.cellHtml=ceValue+showValue.substring(index);
	            	   }else{
	            		   	e.cellHtml=showValue;
	                	   }
	               }  
	            }
	    }
	    function ExportExcel() {
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
	          if(sui.get("rbl").value=="1"){
		       		document.getElementById("unit").value = "(单位\:元)";
		       }else if(sui.get("rbl").value=="2"){
		    	   document.getElementById("unit").value = "(单位\:万元)";
		       }
	         // alert(document.getElementById("excelData").value);
	          document.getElementById("rowId").value= sui.get("Formperid").value;//获取当前选中的年份值
	          var excelForm = document.getElementById("excelForm");
	          excelForm.submit();  

	          }
</script>
</html>