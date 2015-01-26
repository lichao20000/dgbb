<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../taglib.jsp"%>
<html>
<head>
<!-- 009-1.4综合绩效报表-收入-集客收入 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${reportName}</title>
<link href="${path}/css/report-style.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
<style type="text/css">
body .sui-grid-headerCell-nowrap{
white-space:normal;
word-break: break-all;
word-wrap: break-word;
line-height:15px;
}
</style>
<script type="text/javascript">
		$(document).ready(function(e) {
			sui.parse();
			var grid = sui.get("datagrid1");
			grid.load();
			grid.on("load", function(e) {
				sui.mask();
				grid.unmask();
				sui.unmask();
				
				var rows = e.data;
				var l = rows.length
				//添加合计
				if (l!=0){
					var row_new = new Object();
					row_new.perIod="合计";
					for (var j=1;j<27;j++) {
						var total = 0;
						var kpi = "kpi600";
						if (j<10){
							kpi = kpi+ "0"+j;
						}else{
							kpi = kpi+j;	
						}
						for (var i = 0; i < l; i++) {
							var row = rows[i];
							var kpi_value = parseFloat(row[kpi]);
							if (isNaN(kpi_value)) continue;
							total += kpi_value;
						}
						row_new[kpi] = total;
					}
					//var row = {perIod:"合计",kpi60001:kpi60001_total,kpi60002:8880,kpi60003:7770};
					row_new.kpi60027=(row_new.kpi60016/row_new.kpi60023).toFixed(2);
					row_new.kpi60028=(row_new.kpi60019/row_new.kpi60024).toFixed(2);
					row_new.kpi60029=(row_new.kpi60018/row_new.kpi60025).toFixed(2);
					row_new.kpi60030=((row_new.kpi60016+row_new.kpi60018+row_new.kpi60019)/row_new.kpi60026).toFixed(2);
					grid.addRow(row_new);
				}
				//alert("---------load success!-----");
			});
			
			
		grid.on("drawcell",function(n) {
				var record = n.record, 
					column = n.column, 
					field = n.field, 
					rows = n.rowIndex,
					value = n.value;
				 //给比例增加"%"号
		        if (field == "kpi60027") {
		            n.cellHtml = value*100 + "%";
		        }
		        if (field == "kpi60028") {
		            n.cellHtml = value*100 + "%";
		        }
		        if (field == "kpi60029") {
		            n.cellHtml = value*100 + "%";
		        }
		        if (field == "kpi60030") {
		            n.cellHtml = value*100 + "%";
		        }
		        if(field=="perIod"){
			        if(rows%2 == 0){
			        	n.cellStyle = "background:#ECECEC;text-align: center;";
				        }else{
				        n.cellStyle = "background:#F7F7F7;text-align: center;";    
					        }
					
				}
			});
	});
</script>
<h1 align="center">${reportName}</h1>
<!-- 期间选择的通用组件 -->
 <form id="excelForm"
	action="${path}/exportExcel.do?method=export&sql=sql3" method="post"
	target="excelIFrame">
	<input type="hidden" name="unit" id="unit" />
	<input type="hidden" name="columns"id="excelData" /> 
	<input type="hidden" name="yearId" id="yearId" />
	</form>
<iframe id="excelIFrame" name="excelIFrame" style="display: none;"></iframe>
<div class="condition">年份： <input id="Formyearid"
	name="Formyearid" class="sui-combobox" textField="yearDsc"
	valueField="yearId" onvaluechanged="onFormperChange"
	url="${path}/getForm.do?method=yearchoose" /> <!-- showPger boolean 是否顯示報表分頁 -->
	<input type="button"
	value="导出Excel" onclick="ExportExcel()" style="margin-left: 50px;" />	
	<div style="float: right;width:180px;"><span style="float: left;">单位：<span><div id="rbl" class="sui-radiobuttonlist" style="float: right;" repeatItems="2" repeatLayout="table" repeatDirection="horizontal"
    textField="text" valueField="id" value="1" data="[{'id':'1','text':'元'},{'id':'2','text':'万元'}]" ></div> 
    </div>
</div>
<div id="datagrid1" class="sui-datagrid"
	style="width: 100%; height: 385px" allowResize="true" sortMode="client"
	url="${path}/getForm.do?method=homePage&sql=sql3" idField="id" 
	showPager="false" allowAlternating="true" frozenStartColumn="0" 
	frozenEndColumn="0" onperload="fixColumns()"  ondrawcell="onDrawCell" >
<div property="columns"><!--<div type="indexcolumn">序号</div>    -->
<div field="perIod" width="80" headeralign="center" align="canter"  renderer="onPriodRenderer" dateFormat="yyyy-MM">期间</div>
<div header="集团移动收入明细" headerAlign="center">
<div property="columns">
<div field="kpi60001" width="100"  headeralign="center"
	dataType="currency" align="right">固网出账收入（不含ICT)</div>
<div field="kpi60002" width="100"  headeralign="center"
	dataType="currency" align="right">接入码业务出账收入</div>
<div field="kpi60003" width="120"  headeralign="center"
	dataType="currency" align="right">原网通电路摊分（含国内/国际/省内）</div>
<div field="kpi60004" width="100"  headeralign="center"
	dataType="currency" align="right">原联通国际/省内电路结算分摊</div>
</div>
</div>
<div field="kpi60005" width="120" headeralign="center"
	dataType="currency" align="right">集团固网收入合计</div>
<div header="集团移动收入明细" headerAlign="center">
<div property="columns">
<div field="kpi60006" width="100"  headeralign="center"
	dataType="currency" align="right">3G出账收入</div>
<div field="kpi60007" width="100"  headeralign="center"
	dataType="currency" align="right">2G出账收入</div>
<div field="kpi60008" width="100"  headeralign="center"
	dataType="currency" align="right">2G信息化2G出账体现</div>
<div field="kpi60009" width="100"  headeralign="center"
	dataType="currency" align="right">3G预付费上网卡</div>
</div>
</div>
<div field="kpi60010" width="100"  headeralign="center"
	dataType="currency" align="right">集团移动收入合计</div>
<div field="kpi60011" width="100"  headeralign="center"
	dataType="currency" align="right">ICT收入</div>
<div header="考核调整事项" headerAlign="center">
<div property="columns">
<div field="kpi60012" width="100"  headeralign="center"
	dataType="currency" align="right">加：无线固话（按套餐）</div>
<div field="kpi60013" width="100"  headeralign="center"
	dataType="currency" align="right">分入分出调整（含广佛中铁通、汇丰）</div>
<div field="kpi60014" width="100"  headeralign="center"
	dataType="currency" align="right">其他（固网）</div>
<div field="kpi60015" width="100"  headeralign="center"
	dataType="currency" align="right">本月考核收入合计</div>
</div>
</div>
<div field="kpi60016" width="120" headeralign="center"
	dataType="currency" align="right">考核口径的固网</div>
<div field="kpi60017" width="120" headeralign="center"
	dataType="currency" align="right">考核口径的移网</div>
<div field="kpi60018" width="120" headeralign="center"
	dataType="currency" align="right">3G</div>
<div field="kpi60019" width="120" headeralign="center"
	dataType="currency" align="right">2G</div>
<div header="分客户群考核收入" headerAlign="center">
<div property="columns">
<div field="kpi60020" width="100"  headeralign="center"
	dataType="currency" align="right">大客</div>
<div field="kpi60021" width="100"  headeralign="center"
	dataType="currency" align="right">商企</div>
<div field="kpi60022" width="100"  headeralign="center"
	dataType="currency" align="right">校园</div>
</div>
</div>
<div header="省公司收入预算(考核口径)" headerAlign="center">
<div property="columns">
<div field="kpi60023" width="100"  headeralign="center"
	dataType="currency" align="right">固网</div>
<div field="kpi60024" width="100"  headeralign="center"
	dataType="currency" align="right">2G</div>
<div field="kpi60025" width="100"  headeralign="center"
	dataType="currency" align="right">3G</div>
<div field="kpi60026" width="100"  headeralign="center"
	dataType="currency" align="right">小计</div>
</div>
</div>
<div header="收入预算完成率" headerAlign="center">
<div property="columns">
<div field="kpi60027" width="100"  headeralign="center"
	dataType="currency" align="right">固网</div>
<div field="kpi60028" width="100"  headeralign="center"
	dataType="currency" align="right">2G</div>
<div field="kpi60029" width="100"  headeralign="center"
	dataType="currency" align="right">3G</div>
<div field="kpi60030" width="100"  headeralign="center"
	dataType="currency" align="right">小计</div>
</div>
</div>
</div>
</div>
<script>
	sui.parse();
	var grid = sui.get("datagrid1");
	
	
	//function onpreload(x) {
	//	if (x.data.length > 0) {
	//		for ( var i = 0; i < x.data.length; i++) {
	//			var a = x.data[i].showName;
	//			if (a == "0") {
	//				x.data[i].showName = x.data[i].catagory;
	//				x.data[i].catagory = a;
	//			}
	//		}
	//	}
	//}
	var obj = sui.get("Formyearid");
	var date = obj.data[0].yearId;
	obj.setValue(date);
	grid.load({
			"yearId" :date
		});
	function onFormperChange(e) {
		loadData();
	}
	function loadData(getdatedata) {
		var _path = "${path}";
		var yearId = sui.get("Formyearid").value;//获取当先选中的年份值
		//获得数据
		sui.mask();
		var grid = sui.get("datagrid1");
		grid.load( {
			"yearId" :yearId
		});
		sui.unmask();
	}
	function fixColumns() {
		grid.frozenColumns(0, 0); //  锁定列
	}
    function onDrawSummaryCell(e) {
        var result = e.result;
        var grid = e.sender;
        var rows = e.data;
        
        if (e.field == "kpi60030") {
            var total = 0;
            for (var i = 0, l = rows.length; i < l; i++) {
                var row = rows[i];
                var t = parseFloat(row.kpi60027)+ parseFloat(row.kpi60028)+ parseFloat(row.kpi60029);
                if (isNaN(t)) continue;
                total += t;
            }
						//alert("--------------0");
            e.cellHtml = "总计: " + total;
        }
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
           for(var i=27;i<31;i++){
					key = "kpi600"+i;
               if(str==key){
					active = false;
					break;
                   }
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
    
    function onPriodRenderer(e) {
    	var priodValue = e.value
        if (priodValue != "合计"){
        	 return priodValue;
        }else{
        	return priodValue;
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
       var excelForm = document.getElementById("excelForm");
       excelForm.submit();  

       }     
</script>
</body>
</html>