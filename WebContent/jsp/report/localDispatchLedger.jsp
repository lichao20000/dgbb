<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../taglib.jsp"%>
<html>

<head>
<!-- 1004 售前报表——售前支撑工单评分 -->
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
		var obj1 = sui.get("Formyearid");
		var date1 = obj1.data[0].yearId;
		obj1.setValue(date1);
		var obj = sui.get("Formperid");
		var date = obj.data[0].monthCode;
		obj.setValue(date);
		grid.on("load", function(e) {
			sui.mask();
			var a = e.sender;
			a.mergeColumns( [ "localCode" ]);
			grid.unmask();
			sui.unmask();
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
	action="${path}/exportExcel.do?method=export&sql=sql1" method="post"
	target="excelIFrame">
	<input type="hidden" name="unit" id="unit" />
	<input type="hidden" name="columns"id="excelData" /> 
	<input type="hidden" name="yearId" id="yearId" />
     <input type="hidden" name="monthId" id="monthId" />
<iframe id="excelIFrame" name="excelIFrame" style="display: none;"></iframe>
<div class="condition">
	工单状态：<input id="Formyearid" name="Formyearid" class="sui-combobox"	textField="yearDsc" valueField="yearId"
	onvaluechanged="onFormperChange" 	url="${path}/getForm.do?method=yearchoose"
	onvaluechanged="onDateChange" />
	&nbsp;&nbsp;&nbsp;&nbsp;时间类型：<input id="Formperid" 	name="Formperid" class="sui-combobox" textField="monthDsc"
	valueField="monthCode" onvaluechanged="onFormperChange" 	url="${path}/getForm.do?method=datachoose"
	onvaluechanged="onDateChange" />
	 <input type="button"
	value="导出Excel" onclick="ExportExcel()" style="margin-left: 50px;" />

</div>
<div id="datagrid1" class="sui-datagrid" 	style="width: 100%; height: 385px" allowResize="true" sortMode="client"
	pageSize="100" sizeList="[100,200,500]" 	url="${path}/loadReportData.do?method=loadData&sqlNo=3001" idField="id" onupdate="update"
	showPager="false" ondrawcell="onDrawCell">
	<div property="columns"><!--
		<div field="perIod" width="0" align="center"  name="perIod" dateFormat="yyyy-MM" headeralign="center">期间</div>
		--><div field="form_no" width="80" align="center" name="localCode" 	headeralign="center">调单号</div>
		<div field="form_title" width="120" headeralign="center" autoEscape="true">调单名称</div>
		<div field="bss_customer_name" width="120" align="right" headeralign="center" 	dataType="currency">客户名称</div>
		<div field="send_user_id" width="120" align="right" headeralign="center" dataType="currency">申请人</div>
		<div field="biz_type" width="120" align="right" headeralign="center">业务类型</div>
		<div field="customer_type" width="120" align="right" headeralign="center" dataType="currency">电路性质</div>
		
		<!--<div field="isColor" width="0" align="right" headeralign="center" dataType="currency">标识列不显示</div>
	--></div>
</div>
<script>
	sui.parse();
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
			"monthId" :monthId,
		});
		sui.unmask();
	}
	function ExportExcel() {
		var columns = grid.getBottomColumns();
		function getColumns(columns) {
			columns = columns.clone();
			for ( var i = columns.length - 1; i >= 0; i--) {
				var column = columns[i];
				if (!column.field) {
					columns.removeAt(i);
				} else {
					var c = {
						header :column.header,
						field :column.field
					};
					columns[i] = c;
				}
			}
			return columns;
		}

		var columns = getColumns(columns);
		var json = mini.encode(columns);
		document.getElementById("excelData").value = json;

		var excelForm = document.getElementById("excelForm");
		excelForm.submit();

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
           if(str=="localCode"){
        	   active=false;
               }
           if(str=="showName"){
        	   active=false;
               }
           if(str=="growthRate"){
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