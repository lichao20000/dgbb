<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../taglib.jsp"%>
<html>
<head>
<!-- 009-1.3综合绩效报表-收入-网内网间结算 -->
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
		grid.sortBy("perIod", "desc");
		var obj1 = sui.get("Formyearid");
		var date1 = obj1.data[1].yearId;
		obj1.setValue(date1);
		var obj = sui.get("Formperid");
		var date = obj.data[0].monthCode;
		obj.setValue(date);
		grid.on("load", function(e) {
			sui.mask();
			var a = e.sender;
			//			a.mergeColumns( [ "cateGory" ]);
				var marges = [ {
					rowIndex :0,
					columnIndex :0,
					rowSpan :16,
					colSpan :1
				}, {
					rowIndex :16,
					columnIndex :0,
					rowSpan :16,
					colSpan :1
				}, {
					rowIndex :32,
					columnIndex :0,
					rowSpan :8,
					colSpan :1
				}, {
					rowIndex :40,
					columnIndex :0,
					rowSpan :40,
					colSpan :1
				}, {
					rowIndex :0,
					columnIndex :1,
					rowSpan :4,
					colSpan :1
				}, {
					rowIndex :4,
					columnIndex :1,
					rowSpan :4,
					colSpan :1
				}, {
					rowIndex :8,
					columnIndex :1,
					rowSpan :4,
					colSpan :1
				}, {
					rowIndex :12,
					columnIndex :1,
					rowSpan :4,
					colSpan :1
				}, {
					rowIndex :16,
					columnIndex :1,
					rowSpan :4,
					colSpan :1
				}, {
					rowIndex :20,
					columnIndex :1,
					rowSpan :4,
					colSpan :1
				}, {
					rowIndex :24,
					columnIndex :1,
					rowSpan :4,
					colSpan :1
				}, {
					rowIndex :28,
					columnIndex :1,
					rowSpan :4,
					colSpan :1
				}, {
					rowIndex :40,
					columnIndex :1,
					rowSpan :10,
					colSpan :1
				}, {
					rowIndex :50,
					columnIndex :1,
					rowSpan :10,
					colSpan :1
				}, {
					rowIndex :60,
					columnIndex :1,
					rowSpan :10,
					colSpan :1
				}, {
					rowIndex :70,
					columnIndex :1,
					rowSpan :10,
					colSpan :1
				}, {
					rowIndex :80,
					columnIndex :0,
					rowSpan :1,
					colSpan :3
				}, {
					rowIndex :81,
					columnIndex :0,
					rowSpan :1,
					colSpan :3
				}, {
					rowIndex :82,
					columnIndex :0,
					rowSpan :1,
					colSpan :3
				}, {
					rowIndex :32,
					columnIndex :1,
					rowSpan :1,
					colSpan :2
				}, {
					rowIndex :33,
					columnIndex :1,
					rowSpan :1,
					colSpan :2
				}, {
					rowIndex :34,
					columnIndex :1,
					rowSpan :1,
					colSpan :2
				}, {
					rowIndex :35,
					columnIndex :1,
					rowSpan :1,
					colSpan :2
				}, {
					rowIndex :36,
					columnIndex :1,
					rowSpan :1,
					colSpan :2
				}, {
					rowIndex :37,
					columnIndex :1,
					rowSpan :1,
					colSpan :2
				}, {
					rowIndex :38,
					columnIndex :1,
					rowSpan :1,
					colSpan :2
				}, {
					rowIndex :39,
					columnIndex :1,
					rowSpan :1,
					colSpan :2
				} ];
				a.mergeCells(marges);
				sui.unmask();
			});
			
		grid.on("drawcell", function(e) {
			var record = e.record,
			rows = e.rowIndex, 
			field = e.field;
			if (record.attribute == "Y") {
				e.rowStyle = "background: #DAE3F4;font-weight : bold;";
			}
			//if(record.showName == "0"){
			//	 e.cellHtml = "合计";
			//	}
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
	action="${path}/exportExcel.do?method=export&sql=sql2" method="post"
	target="excelIFrame">
	<input type="hidden" name="columns"id="excelData" /> 
	<input type="hidden" name="yearId" id="yearId" />
	<input type="hidden" name="unit" id="unit" />
     <input type="hidden" name="monthId" id="monthId" />
	
	</form>
<iframe id="excelIFrame" name="excelIFrame" style="display: none;"></iframe>

<div class="condition">年份：<input id="Formyearid" name="Formyearid"
	class="sui-combobox" textField="yearDsc" valueField="yearId"
	onvaluechanged="onFormperChange"
	url="${path}/getForm.do?method=yearchoose"
	onvaluechanged="onDateChange" /> &nbsp;&nbsp;&nbsp;&nbsp;月份：<input
	id="Formperid" name="Formperid" class="sui-combobox"
	textField="monthDsc" valueField="monthCode"
	onvaluechanged="onFormperChange"
	url="${path}/getForm.do?method=datachoose"
	onvaluechanged="onDateChange" />
	<input type="button" value="导出Excel" onclick="ExportExcel()" style="margin-left: 50px;" />	
	<div style="float: right;width:180px;"><span style="float: left;">单位：<span><div id="rbl" class="sui-radiobuttonlist" style="float: right;" repeatItems="2" repeatLayout="table" repeatDirection="horizontal"
    textField="text" valueField="id" value="1" data="[{'id':'1','text':'元'},{'id':'2','text':'万元'}]" ></div> 
    </div>
	</div>
<div id="datagrid1" class="sui-datagrid"
	style="width: 100%; height: 385px" allowResize="true" sortMode="client"
	url="${path}/getForm.do?method=homePage&sql=sql2" idField="id"
	onpreload="onpreload" showPager="false" ondrawcell="onDrawCell">
<div property="columns"><!--<div type="indexcolumn">序号</div>    -->
<!--<div field="perIod" width="0" align="center" allowSort="true"
	name="perIod" dateFormat="yyyy-MM" headeralign="center">期间（月份）</div>
--><div field="catagory" width="120" autoEscape="true" allowSort="true"
	name="cateGory" headeralign="center" align="center">类别</div>
<div field="localTeleType" width="120" autoEscape="true"
	allowSort="true" headeralign="center" align="center">专业</div>
<div field="showName" width="120" autoEscape="true" allowSort="true"
	headeralign="center" align="center">项目</div>
<div field="currentNum" width="120" align="right" allowSort="true"
	headeralign="center" dataType="currency" decimalPlaces="2">当期值</div>
<div field="monthOnMonthLimit" width="120" align="right"
	allowSort="true" headeralign="center" dataType="currency"
	decimalPlaces="2">环比额度</div>
<div field="totalAmount" width="120" align="right" allowSort="true"
	headeralign="center" dataType="currency" decimalPlaces="2">本年累计</div>
<div field="schedualAverage" width="120" align="right" allowSort="true"
	headeralign="center" dataType="currency" decimalPlaces="2">进度平均值</div>
<!-- 手动隐藏一列 -->
<!--<div field="attribute" width="0" align="right" allowSort="true"
	headeralign="center">区别灰色色背景条</div>
--></div>
</div>
<script>
	sui.parse();
	var date = "";
	function onpreload(x) {
	if (x.data.length > 0) {
			for ( var i = 0; i < x.data.length; i++) {
				var a = x.data[i].localTeleType;
				var b = x.data[i].catagory;
				if (b == "0" && a == "0") {
					x.data[i].catagory = x.data[i].showName;
					//x.data[i].showName = b;
				} else if (a == "0" && b != "0") {
					x.data[i].localTeleType = x.data[i].showName;
					//x.data[i].showName = a;
				}
			}
		}
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
           if(str=="cateGory"){
        	   active=false;
               }
           if(str=="localTeleType"){
        	   active=false;
               }
           if(str=="showName"){
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
       var excelForm = document.getElementById("excelForm");
       excelForm.submit();  

       }
</script>

</body>
</html>