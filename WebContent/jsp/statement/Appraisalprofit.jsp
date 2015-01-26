<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../taglib.jsp"%>
<html>
<head>
<!-- 009-1.8综合绩效报表-成本利润-考核利润 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${reportName}</title>
<link href="${path}/css/report-style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script type="text/javascript">
	$(document).ready( function(e) {
		sui.parse();
		var grid = sui.get("datagrid1");
		var grid2 = sui.get("datagrid2");
		grid.load();
		grid2.load();
		var obj = sui.get("Formyearid");
		var date = obj.data[0].yearId;
		obj.setValue(date);
		var obj2 = sui.get("Formperid");
		var date2 = obj2.data[0].id;
		obj2.setValue(date2);
		grid.on("load", function(e) {
			sui.mask();
			var a = e.sender;
			sui.unmask();
		});
		grid2.on("load", function(e) {
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
 <form id="excelForm"
	action="${path}/exportExcel.do?method=export&sql=sql10" method="post"
	target="excelIFrame">
	<input type="hidden" name="columns"id="excelData" /> 
	<input type="hidden" name="monthId" id="monthId" />
	<input type="hidden" name="unit" id="unit" />
	<input type="hidden" name="reportid" id="reportid" />
	</form>
<iframe id="excelIFrame" name="excelIFrame" style="display: none;"></iframe>
<div class="condition">年份:&nbsp; <input
	id="Formyearid" name="Formyearid" class="sui-combobox"
	textField="yearDsc" valueField="yearId"
	onvaluechanged="onFormperChange"
	url="${path}/getForm.do?method=yearchoose" showPager="false" />
	 <input type="button" value="导出Excel" onclick="ExportExcel()" style="margin-left: 50px;" />	
<div style="float: right;width:180px;"><span style="float: left;">单位：<span><div id="rbl" class="sui-radiobuttonlist" style="float: right;" repeatItems="2" repeatLayout="table" repeatDirection="horizontal"
   textField="text" valueField="id" value="1" data="[{'id':'1','text':'元'},{'id':'2','text':'万元'}]" ></div> 
   </div>
</div>

<div id="datagrid1" class="sui-datagrid"
	style="width: 100%; height: 385px" allowResize="true" sortMode="client"
	url="${path}/getForm.do?method=homePage&sql=sql10" idField="id"
	onpreload="onpreload" showPager="false" frozenStartColumn="0"
	frozenEndColumn="1"  allowAlternating="true" ondrawcell="onDrawCell">
<div property="columns"><!--<div type="indexcolumn">序号</div>    -->
<div field="kpiName" width="260" headeralign="center" autoEscape="true">项目</div>
<!--<div field="year" width="0" align="right" headeralign="center">年份</div>
--><div field="month1" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">一月</div>
<div field="month2" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">二月</div>
<div field="month3" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">三月</div>
<div field="month4" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">四月</div>
<div field="month5" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">五月</div>
<div field="month6" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">六月</div>
<div field="month7" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">七月</div>
<div field="month8" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">八月</div>
<div field="month9" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">九月</div>
<div field="month10" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">十月</div>
<div field="month11" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">十一月</div>
<div field="month12" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">十二月</div>
</div>
</div>


<div class="condition" style="margin-top: 30px">报表:&nbsp;
<input id="Formperid" name="Formperid" class="sui-combobox"
	textField="text" valueField="id" onvaluechanged="onFormperChange"
	url="${path}/getForm.do?method=reportchoose" />
</div>
<div id="datagrid2" class="sui-datagrid"
	style="width: 100%; height: 500px" allowResize="true" sortMode="client"
	url="${path}/getForm.do?method=homePage&sql=sql9" idField="id"
	onpreload="onpreload" showPager="false" frozenStartColumn="0"
	frozenEndColumn="1"  allowAlternating="true" ondrawcell="onDrawCell">
<div property="columns"><!--<div type="indexcolumn">序号</div>    -->
<div field="kpiName" width="260" headeralign="center" autoEscape="true">项目</div>
<!--<div field="year" width="0" align="right" headeralign="center">年份</div>
--><div field="month1" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">一月</div>
<div field="month2" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">二月</div>
<div field="month3" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">三月</div>
<div field="month4" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">四月</div>
<div field="month5" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">五月</div>
<div field="month6" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">六月</div>
<div field="month7" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">七月</div>
<div field="month8" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">八月</div>
<div field="month9" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">九月</div>
<div field="month10" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">十月</div>
<div field="month11" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">十一月</div>
<div field="month12" width="120" align="right" headeralign="center"
	decimalPlaces="2" dataType="currency">十二月</div>
</div>
</div>
<script>
	sui.parse();
	var grid = sui.get("datagrid1");
	var grid2 = sui.get("datagrid2");
	function onpreload(e) {
		fixColumns();
	}
	function onFormperChange(e) {
		loadData();
	}
	function loadData(getdatedata) {
		var _path = "${path}";
		var reportid = sui.get("Formperid").text;//获取当前选中的报表 
		var monthId = sui.get("Formyearid").value;//获取当前选中的年份的数据

		//获得数据
		sui.mask();
		var grid = sui.get("datagrid1");
		var grid2 = sui.get("datagrid2");
		grid.load( {
			"reportid" :reportid,
			"monthId" :monthId
		});
		grid2.load( {
			"reportid" :reportid,
			"monthId" :monthId
		});
		sui.unmask();
	}
	
	function fixColumns() {
		grid.frozenColumns(0, 1); //  锁定列
		grid2.frozenColumns(0, 1);
	}
	//单位选择的主体
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
	   //对出了第一列kpiname的其他列的值做除法，当点击单位选择万元时整体列除10000
	   function onDrawCell(e) {
	       if(flag){
	          var str = e.field.toString();
	          var key ="";
	          var active=true;
	          //判断列状态 当active为false的时候对判断列不执行下面的初10000方法
	          if(str=="kpiName"){
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
       document.getElementById("monthId").value= sui.get("Formyearid").value;//获取当前选中的年份值
       document.getElementById("reportid").value= sui.get("Formperid").text;//获取当前选中的baobiao
       var excelForm = document.getElementById("excelForm");
       excelForm.submit();  

       }     
</script>
</body>
</html>