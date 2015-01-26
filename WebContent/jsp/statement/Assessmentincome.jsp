<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../taglib.jsp"%>

<html>
<head>
<!-- 009-1.1综合绩效报表-收入-考核收入 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${reportName}</title>
<link href="${path}/css/report-style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="${path}/scripts/json/json2.js"></script>
<script type="text/css">
body .sui-grid-headerCell-nowrap{
	white-space:normal;
	word-break: break-all;
	word-wrap: break-word;
	line-height:15px;
}
</script>
</head>
<body>
<h1 align="center">${reportName}</h1>
<!-- 期间选择的通用组件 -->
<!-- 期间选择的通用组件 -->
<form id="excelForm"
	action="${path}/exportExcel.do?method=export&sql=sql8" method="post"
	target="excelIFrame"><input type="hidden" name="columns"
	id="excelData" /><input type="hidden" name="unit" id="unit" /><input type="hidden" name="yearId" id="yearId" /></form>
<iframe id="excelIFrame" name="excelIFrame" style="display: none;"></iframe>
<div class="condition">年份：<input id="FormYear" name="FormYear"
	class="sui-combobox" textField="yearDsc" valueField="yearId"
	onvaluechanged="onFormperChange"
	url="${path}/getForm.do?method=yearchoose" />
&nbsp;&nbsp;&nbsp;&nbsp;月份：<input id="FormMonth" name="FormMonth"
	class="sui-combobox" textField="monthDsc" valueField="monthCode"
	onvaluechanged="onFormperChange"
	url="${path}/getForm.do?method=datachoose"
	onvaluechanged="onFormperChange" /> <input type="button"
	value="导出Excel" onclick="ExportExcel()" style="margin-left: 50px;" />
	<div style="float: right;width:180px;"><span style="float: left;">单位：<span><div id="rbl" class="sui-radiobuttonlist" style="float: right;" repeatItems="2" repeatLayout="table" repeatDirection="horizontal"
   textField="text" valueField="id" value="1" data="[{'id':'1','text':'元'},{'id':'2','text':'万元'}]" ></div> 
    </div>
	</div>

<div id="datagrid1" class="sui-datagrid"
	style="width: 100%; height: 385px" allowResize="true" sortMode="client"
	url="${path}/getForm.do?method=homePage&sql=sql8" showPager="false"
	allowAlternating="true" onload="onload()" ondrawcell="onDrawCell">

<!--<div property="columns">
		<div field="kpiclassify" width="80" align="center" name="kpiclassify" headeralign="center">类别</div>
		<div field="project" width="150" align="center" headeralign="center" name="project">项目</div>
		<div field="kpiName" width="180" headeralign="center" autoEscape="true">指标</div>
		<div field="year" width="0" align="right" headeralign="center">年份</div>
		<div field="attribute1" width="0" align="right" headeralign="center">排序</div>
		<div field="month1" width="110" align="right" headeralign="center" dataType="currency" >一月</div>
		<div field="month2" width="110" align="right" headeralign="center" dataType="currency" >二月</div>
		<div field="month3" width="110" align="right" headeralign="center" dataType="currency" >三月</div>
		<div field="month4" width="110" align="right" headeralign="center" dataType="currency" >四月</div>
		<div field="month5" width="110" align="right" headeralign="center" dataType="currency" >五月</div>
		<div field="month6" width="110" align="right" headeralign="center" dataType="currency" >六月</div>
		<div field="month7" width="110" align="right" headeralign="center" dataType="currency" >七月</div>
		<div field="month8" width="110" align="right" headeralign="center" dataType="currency" >八月</div>
		<div field="month9" width="110" align="right" headeralign="center" dataType="currency" >九月</div>
		<div field="month10" width="110" align="right" headeralign="center" dataType="currency" >十月</div>
		<div field="month11" width="110" align="right" headeralign="center" dataType="currency" >十一月</div>
		<div field="month12" width="110" align="right" headeralign="center" dataType="currency" >十二月</div>
		<div field="month12" width="110" align="right" headeralign="center" dataType="currency" >总计</div>
	</div>
	<div type="indexcolumn">序号</div>    --></div>
<script>
	sui.parse();
	$(document).ready( function(e) {
		sui.parse();
	
		//初始化条件
		var year = sui.get("FormYear");
		var yearValue = year.data[0].yearId;
		year.setValue(yearValue);
		
		var month = sui.get("FormMonth");
		var monthValue = "<%=com.dglt.base.util.ClassUtil.getLastMonth()%>"; //系统当前时间前一个月
		var headerNum = monthValue;//显示几个月
		if (monthValue < 10){
			monthValue = "0"+monthValue;
		}
		month.setValue(monthValue);
		
		//表格渲染
		var grid = sui.get("datagrid1");
		//动态设置表头
		grid.load();	
	
		//
	
		grid.on("load", function(e) {
			sui.mask();
			var a = e.sender;
			a.mergeColumns( [ "kpiclassify", "project" ]);
			sui.unmask();
		});
	
		//渲染
		grid.on("drawcell", function(e) {
			var record = e.record,
			rows = e.rowIndex, 
			field = e.field;
			if(field=="kpiclassify"){
				e.cellStyle = "background:#FFFFFF";
			}
			if(field=="project"){
				e.cellStyle = "background:#FFFFFF";
			}
		});
		
		//动态设置表头
		grid.on("preload", function(e) {
			var monthId = sui.get("FormMonth").value; 
			var header = getGridHeadArr(parseInt(monthId,10));
			grid.set(header);
		  //grid.frozenColumns(0,2);
		});
	
		
	});
	
	//提交
	function onFormperChange(e) {
		loadData("");
		
	}
	//刷新
	function loadData(getdatedata) {
		var _path = "${path}";
		var yearId = sui.get("FormYear").value;//获取当前选中的年份值
		//获得数据
		sui.mask();
		var grid = sui.get("datagrid1");
		
		grid.load( {
			"yearId" :yearId
		});
		grid.frozenColumns(0,2);
		sui.unmask();

	}
	
	//根据月份动态设置表头
	function getGridHeadArr(monthValue) {
		var end =monthValue;
		if ( end==undefined || end==null || isNaN(end) ){
			end = 12;
		}else if (end<0 || end>12){
			end = 12;
		}else{}
		var jsonStr ='{'
			+'columns: ['
			+'{ field: "kpiclassify", name:"kpiclassify",width: "80", headerAlign: "center", align: "center",header: "类别" },'
			+'{ field: "project", name:"project",width: "200", headerAlign: "center", align: "center", allowCellWrap:"true" ,header: "项目" },'
			+'{ field: "kpiName", name:"kpiName",width: "180", headerAlign: "center",autoEscape:"true", header: "指标" },';
		
		for(var i=1;i<=end;i++){
			jsonStr+='{ field: "month'+i+'", name:"month'+i+'",width: "110", headerAlign: "center", align: "right", dataType:"currency", header: "'+i+'月" },';
		}
		jsonStr = jsonStr +'{ field: "totalcount", name:"totalcount",width: "110", headerAlign: "center", align: "right",dataType:"currency", header: "总计" }'
						+']'
						+'}';
		//alert(jsonStr);
		return  eval('(' + jsonStr + ')'); 
	}	
	function onload(){
		var grid = sui.get("datagrid1"); 
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
          document.getElementById("yearId").value= sui.get("FormYear").value;//获取当前选中的年份值
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
        var record = e.record;
        if(flag){
            var str = e.field.toString();
            var key ="";
            var active=true;
            
            if(str=="kpiclassify"){
         	   active=false;
                }
            if(str=="project"){
         	   active=false;
                }
            if(str=="kpiName"){
         	   active=false;
                }
            if(str=="totalcount"){
          	   active=false;
                 }
            if(active){
         	   var rs = parseFloat(((e.value=="undefined"||e.value==null||e.value=="")?0:e.value).toString())/10000;
         	   var showValue = rs.toFixed(2).toString();
         	   	e.cellHtml=oneToWan(showValue);
                }  
             }
        if (e.field == "totalcount") {
            var month1 = record.month1;
            var month2 = record.month2;
            var month3 = record.month3;
            var month4 = record.month4;
            var month5 = record.month5;
            var month6 = record.month6;
            var month7 = record.month7;
            var month8 = record.month8;
            var month9 = record.month9;
            var month10 = record.month10;
            var month11 = record.month11;
            var month12 = record.month12;
            var monthId = sui.get("FormMonth").value;//获取当前选中的月份值
            if(monthId =="02"){
            	var result = parseFloat(month1.toString()) + parseFloat(month2.toString());
            	result = result.toFixed(2);
                e.cellHtml = fx(result);
            }
            if(monthId =="03"){
                 var result= parseFloat(month1.toString()) + parseFloat(month2.toString())+ parseFloat(month3.toString());
                 result = result.toFixed(2);
             	 e.cellHtml = fx(result);
                }
            if(monthId =="04"){
            	var result = parseFloat(month1.toString()) + parseFloat(month2.toString())+ parseFloat(month3.toString())+ parseFloat(month4.toString());
            	result = result.toFixed(2); 
            	 e.cellHtml = fx(result);
                }
            if(monthId =="05"){
            	var result = parseFloat(month1.toString()) + parseFloat(month2.toString())+ parseFloat(month3.toString())+ parseFloat(month4.toString())+ parseFloat(month5.toString());
            	result = result.toFixed(2);
            	 e.cellHtml = fx(result);
                }
            if(monthId =="06"){
            	var result = parseFloat(month1.toString()) + parseFloat(month2.toString())+ parseFloat(month3.toString())+ parseFloat(month4.toString())+ parseFloat(month5.toString())+ parseFloat(month6.toString());
            	result = result.toFixed(2); 
            	 e.cellHtml = fx(result);
                }
            if(monthId =="07"){
            	var result = parseFloat(month1.toString()) + parseFloat(month2.toString())+ parseFloat(month3.toString())+ parseFloat(month4.toString())+ parseFloat(month5.toString())+ parseFloat(month6.toString())+ parseFloat(month7.toString());
            	result = result.toFixed(2); 
            	 e.cellHtml = fx(result);
                }
            if(monthId =="08"){
            	var result = parseFloat(month1.toString()) + parseFloat(month2.toString())+ parseFloat(month3.toString())+ parseFloat(month4.toString())+ parseFloat(month5.toString())+ parseFloat(month6.toString())+ parseFloat(month7.toString())+ parseFloat(month8.toString());
            	result = result.toFixed(2);
            	 e.cellHtml = fx(result);
                }
            if(monthId =="09"){
            	var result = parseFloat(month1.toString()) + parseFloat(month2.toString())+ parseFloat(month3.toString())+ parseFloat(month4.toString())+ parseFloat(month5.toString())+ parseFloat(month6.toString())+ parseFloat(month7.toString())+ parseFloat(month8.toString())+ parseFloat(month9.toString());
            	result = result.toFixed(2); 
            	 e.cellHtml = fx(result);
                }
            if(monthId =="10"){
            	var result = parseFloat(month1.toString()) + parseFloat(month2.toString())+ parseFloat(month3.toString())+ parseFloat(month4.toString())+ parseFloat(month5.toString())+ parseFloat(month6.toString())+ parseFloat(month7.toString())+ parseFloat(month8.toString())+ parseFloat(month9.toString())+ parseFloat(month10.toString());
            	result = result.toFixed(2);  
            	 e.cellHtml = fx(result);
                }
            if(monthId =="11"){
            	var result = parseFloat(month1.toString()) + parseFloat(month2.toString())+ parseFloat(month3.toString())+ parseFloat(month4.toString())+ parseFloat(month5.toString())+ parseFloat(month6.toString())+ parseFloat(month7.toString())+ parseFloat(month8.toString())+ parseFloat(month9.toString())+ parseFloat(month10.toString())+ parseFloat(month11.toString());
            	result = result.toFixed(2);
            	 e.cellHtml = fx(result);
                }
            if(monthId =="12"){
            	var result = parseFloat(month1.toString()) + parseFloat(month2.toString())+ parseFloat(month3.toString())+ parseFloat(month4.toString())+ parseFloat(month5.toString())+ parseFloat(month6.toString())+ parseFloat(month7.toString())+ parseFloat(month8.toString())+ parseFloat(month9.toString())+ parseFloat(month10.toString())+ parseFloat(month11.toString())+ parseFloat(month12.toString());
            	result = result.toFixed(2);
            	 e.cellHtml = fx(result);
                }
            if(monthId =="01"){
            	var result = parseFloat(month1.toString());
            	e.cellHtml =fx(result.toFixed(2));
                }
        }
        
    }
    function oneToWan(showValue){
     var cellHtml="";
     var index = showValue.indexOf(".");
  	   if(index>3){
      	   var t=0;
      	   var value="";
      	    var ceValue=showValue.substring(0,(index%3==0?3:index%3));
				for(var i=0;i<index/3-1;i++){
						value = showValue.substring((index%3==0?3:index%3)+t*i,(index%3==0?3:index%3)+t*i+3);
						ceValue=ceValue+","+value;
					}
					cellHtml=ceValue+showValue.substring(index);
      	   }else{
      		   	cellHtml=showValue;
          	   }
  	   	return cellHtml;
        }
    function fx(value){
        var a = value;
			if(flag){
				var rs = parseFloat(value.toString())/10000;
	         	var showValue = rs.toFixed(2).toString();
	         	a =	oneToWan(showValue);
				}else{
				a = oneToWan(value);	
					}
			
			return a;
        }




</script>
</body>
</html>