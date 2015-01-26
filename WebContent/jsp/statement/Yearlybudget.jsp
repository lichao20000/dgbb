<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../taglib.jsp"%>
<html>
<head>
<!-- 009-1.5综合绩效报表-成本利润-年度预算 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${reportName}</title>
<link href="${path}/css/report-style.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
<script type="text/javascript">
	$(document).ready( function(e) {
		sui.parse();
		var grid = sui.get("datagrid1");
		grid.load();
		//获取数组第一个为下拉控件赋值
		var obj = sui.get("Formyearid");
		var date = obj.data[0].yearId;
		obj.setValue(date);
		var obj1 = sui.get("Formperid");
		var date1 = obj1.data[0].monthCode;
		obj1.setValue(date1);
		grid.on("load", function(e) {
			sui.mask();
			var a = e.sender;
			sui.unmask();
		});
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
<h1 align="center">${reportName}</h1>
<!-- 期间选择的通用组件 -->
 <form id="excelForm"
	action="${path}/exportExcel.do?method=export&sql=sql7" method="post"
	target="excelIFrame">
	<input type="hidden" name="unit" id="unit" />
	<input type="hidden" name="columns"id="excelData" /> 
	<input type="hidden" name="yearId" id="yearId" />
     <input type="hidden" name="monthId" id="monthId" />
	
	</form>
<iframe id="excelIFrame" name="excelIFrame" style="display: none;"></iframe>

<div class="condition">年份:&nbsp;<input id="Formyearid"
	name="Formyearid" class="sui-combobox" textField="yearDsc"
	valueField="yearId" onvaluechanged="onFormperChange"
	url="${path}/getForm.do?method=yearchoose"
	onvaluechanged="onDateChange" />&nbsp;&nbsp;&nbsp;&nbsp;月份:&nbsp;<input
	id="Formperid" name="Formperid" class="sui-combobox"
	textField="monthDsc" valueField="monthCode"
	onvaluechanged="onFormperChange"
	url="${path}/getForm.do?method=datachoose"
	onvaluechanged="onDateChange" />
	
<input type="button"
	value="导出Excel" onclick="ExportExcel()" style="margin-left: 50px;" />	
<div style="float: right; width: 180px;"><span
	style="float: left;">单位：<span>
<div id="rbl" class="sui-radiobuttonlist" style="float: right;"
	repeatItems="2" repeatLayout="table" repeatDirection="horizontal"
	textField="text" valueField="id" value="1"
	data="[{'id':'1','text':'元'},{'id':'2','text':'万元'}]"></div></div>
</div>
<div id="datagrid1" class="sui-datagrid"
	style="width: 100%; height: 385px" allowResize="true" sortMode="client"
	url="${path}/getForm.do?method=homePage&sql=sql7" idField="id"
	onpreload="onpreload" showPager="false" frozenStartColumn="0"
	frozenEndColumn="1" allowAlternating="true"  ondrawcell="onDrawCell">
<div property="columns"><!--<div type="indexcolumn">序号</div>    -->
<!--<div field="perIod" width="0" align="center" name="perIod"
	dateFormat="yyyy-MM" headeralign="center">期间（月份）</div>
--><div field="showName" width="200" autoEscape="true" headeralign="center">项目</div>
<div header="本地" headerAlign="center">
<div property="columns">
<div field="localCurrentForcast" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">本月预测</div>
<div field="localCurrentActual" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">本月实际</div>
<div field="localYearAccumulation" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">本年累计</div>
</div>
</div>
<div header="分入" headerAlign="center">
<div property="columns">
<div field="inCurrentForcast" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">本月预测</div>
<div field="localCurrentActual" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">本月实际</div>
<div field="inYeatAccumulation" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">本年累计</div>
</div>
</div>
<div field="adjustment" width="120" align="center" headeralign="center">调整</div>
<div header="全成本" headerAlign="center">
<div property="columns">
<div field="fullCostCurrentForcast" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">本月预测</div>
<div field="fullCostCurrentActual" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">本月实际</div>
<div field="fullCostYearAccumulation" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">本年累计</div>
</div>
</div>
<div header="年度预算完成情况" headerAlign="center">
<div property="columns">
<div field="yearBudgetNum" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">年度预算值</div>
<div field="scheduleBudgetNum" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">进度预算值</div>
<div field="yearBudgetCompNum" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">完成值</div>
<div field="yearBudgetCompRate" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">完成率</div>
<div field="yearBudgetAccuOver" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">累计节超</div>
</div>
</div>
<div header="当月预测与实际对比完成情况" headerAlign="center">
<div property="columns">
<div field="locCurtFcstCprActu" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">本地</div>
<div field="inCurtFcstCprAct" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">分入</div>
<div field="fullCostCurtFcstCprActu" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">全成本</div>
</div>
</div>
<div header="同比" headerAlign="center">
<div property="columns">
<div field="yearOnYearAccumulation" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">上年同期累计</div>
<div field="yearOnYearVariety" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">同比增减</div>
<div field="yearOnYearVarietyRate" width="120" headeralign="center"
	align="right" dataType="currency" decimalPlaces="2">增减率</div>
</div>
</div>
</div>
</div>
<script>
	sui.parse();
	var grid = sui.get("datagrid1");
	grid.load();

	function onpreload(e) {
		fixColumns();
	}
	function onFormperChange(e) {
		loadData();
	}
	function loadData(getdatedata) {
		var _path = "${path}";
		var monthId = sui.get("Formperid").value;//获取当前选中的月份值
		var yearId = sui.get("Formyearid").value;//获取当先选中的年份值
		//获得数据
		sui.mask();
		var grid = sui.get("datagrid1");
		grid.load( {
			"yearId" :yearId,
			"monthId" :monthId
		});
		sui.unmask();
	}
	function fixColumns() {
		grid.frozenColumns(0, 0); //  锁定列
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
	           if(str =="showName"){
	        	   active = false;
		           }
	           if(str=="yearBudgetCompRate"){
	        	   active=false;
               }
	           if(str=="yearOnYearVarietyRate"){
	        	   active=false;
               }
	           if(str=="perIod"){
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
			//导出到excel绑定的ExportExcel事件
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
	          document.getElementById("yearId").value= sui.get("Formyearid").value;//获取当前选中的年份值
	          document.getElementById("monthId").value= sui.get("Formperid").value;
	          var excelForm = document.getElementById("excelForm");
	          excelForm.submit();  

	          }

	    
</script>
</body>
</html>