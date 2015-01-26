<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../taglib.jsp"%>
<html>
<head>
<!-- 009-1.7综合绩效报表-成本利润-部门预算 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${reportName}</title>
<link href="${path}/css/report-style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script type="text/javascript">
	$(document).ready( function(e) {
		sui.parse();
		var grid = sui.get("datagrid1");
		grid.load();
		 var obj = sui.get("Formyearid");
         var date = obj.data[0].yearId;
         obj.setValue(date);
         var obj1 = sui.get("Formperid");
         var date1 = obj1.data[0].monthCode;
         obj1.setValue(date1);
         var obj2 = sui.get("Formstyle");
         var date2 = obj2.data[0].attreibute;
         obj2.setValue(date2);
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
	action="${path}/exportExcel.do?method=export&sql=sql6" method="post"
	target="excelIFrame">
	<input type="hidden" name="unit" id="unit" />
	<input type="hidden" name="columns"id="excelData" /> 
	<input type="hidden" name="yearId" id="yearId" />
     <input type="hidden" name="monthId" id="monthId" />
      <input type="hidden" name="styletext" id="styletext" />
	</form>
<iframe id="excelIFrame" name="excelIFrame" style="display: none;"></iframe>
<div class="condition">
<div class="condition">
	    年份:&nbsp;<input id="Formyearid" name="Formyearid" class="sui-combobox" textField="yearDsc" valueField="yearId" onvaluechanged="onFormperChange" url="${path}/getForm.do?method=yearchoose"/> </font> <font>  
	   	&nbsp;&nbsp;&nbsp;&nbsp; 月份:&nbsp;<input id="Formperid" name="Formperid" class="sui-combobox" textField="monthDsc" valueField="monthCode" onvaluechanged="onFormperChange" url="${path}/getForm.do?method=datachoose"/>
	   	&nbsp;&nbsp;&nbsp;&nbsp; 类别:&nbsp;<input id="Formstyle" name="Formstyle" class="sui-combobox" textField="styletext" valueField="attreibute" onvaluechanged="onFormperChange" url="${path}/getForm.do?method=getstyle"/>
	  <input type="button" value="导出Excel" onclick="ExportExcel()" style="margin-left: 50px;" />	
	   <div style="float: right;width:180px;"><span style="float: left;">单位：<span><div id="rbl" class="sui-radiobuttonlist" style="float: right;" repeatItems="2" repeatLayout="table" repeatDirection="horizontal"
    textField="text" valueField="id" value="1" data="[{'id':'1','text':'元'},{'id':'2','text':'万元'}]" ></div> 
    </div>
    </div> 
</div> 
<div id="datagrid1" class="sui-datagrid"
	style="width:100%;height:385px" allowResize="true" sortMode="client"
	url="${path}/getForm.do?method=homePage&sql=sql6" idField="id"
	onpreload="onpreload()" showPager="false" frozenStartColumn="0"
	frozenEndColumn="2" allowAlternating="true"  allowAlternating="true" ondrawcell="onDrawCell">
<div property="columns"><!--<div type="indexcolumn">序号</div>    -->
<!--<div field="perIod" width="0" align="center" 
	name="perIod" dateFormat="yyyy-MM" headeralign="center">期间（月份）</div>
--><div field="department" width="120" autoEscape="true"  headeralign="center">部门</div>
<div field="showName" width="120" autoEscape="true"  headeralign="center">项目</div>
<div header="实际金额累计" headerAlign="center">
<div property="columns">
<div field="yearAxtualAccumAmount" width="120" headeralign="center" align="right" dataType="currency" decimalPlaces="2">金额</div>
<div field="yearAxtualAccumRate" width="120" headeralign="center" align="right" dataType="currency" decimalPlaces="2">占收比</div>
</div>
</div>
<div header="预算" headerAlign="center">
<div property="columns">
<div field="budgetAmount" width="120" headeralign="center" align="right" dataType="currency" decimalPlaces="2">金额</div>
<div field="budgetRate" width="120" headeralign="center" align="right" dataType="currency" decimalPlaces="2">占收比</div>
</div>
</div>
<div field="adjustment" width="120" align="center"  headeralign="center" dataType="currency">调整</div>
<div header="节（-）超（+）" headerAlign="center">
<div property="columns">
<div field="amountOver" width="120" headeralign="center" align="right" dataType="currency" decimalPlaces="2">金额</div>
<div field="amountRate" width="120" headeralign="center" align="right" dataType="currency" decimalPlaces="2">占收比</div>
</div>
</div>
<div field="completeRate" width="120" align="center"  headeralign="center" dataType="currency" decimalPlaces="2">完成率</div>
</div>
</div>
<script>
	sui.parse();
	var grid = sui.get("datagrid1");
	grid.load();
	function onpreload() {
		fixColumns();
	}
	function onFormperChange(e) {
		loadData();
	}
	function loadData(getdatedata) {
		var _path = "${path}";
		var monthId = sui.get("Formperid").value;//获取当前选中的月份值
		var yearId = sui.get("Formyearid").value;//获取当先选中的年份值
		var styletext = sui.get("Formstyle").text;
		//获得数据
		sui.mask();
		var grid = sui.get("datagrid1");
		grid.load({"yearId" : yearId,
					"monthId" : monthId,
					"styletext":styletext
					});
		sui.unmask();
	}
	function fixColumns() {
		grid.frozenColumns(0, 2); //  锁定列
	}
	//	单位选择 前台处理
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
	           if(str=="department"){
	        	   active=false;
	               }
	           if(str=="showName"){
	        	   active=false;
	               }
	           if(str=="yearAxtualAccumRate"){
	        	   active=false;
	               }
	           if(str=="budgetRate"){
	        	   active=false;
	               }
	           if(str=="amountRate"){
	        	   active=false;
	               }
	           if(str=="completeRate"){
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
	          document.getElementById("yearId").value= sui.get("Formyearid").value;//获取当前选中的年份值
	          document.getElementById("monthId").value= sui.get("Formperid").value;
	          document.getElementById("styletext").value=  sui.get("Formstyle").text;
	          var excelForm = document.getElementById("excelForm");
	          excelForm.submit();  

	          }
</script>
</body>
</html>