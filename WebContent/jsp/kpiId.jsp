<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.dglt.comm.util.gson.JsonUtil"%>
<%@include file="../taglib.jsp"  %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setHeader("progma", "no-cache"); //屏蔽页面缓存
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<style type="text/css">
			body{margin-top:5px;margin-left:10px;width:100%;height:80%;overflow-x:hidden;overflow-y:hidden;}
			#kpiGrid{margin-top:10px;float: left;}
			.select_kpi{height:90%;width:40%;float: right;}
		</style>
		<title>指标选择</title>
	</head>
<body>
	
		<input onvaluechanged="valueChanged" id="kpiType" name="kpiType" value="" class="sui-combobox" style="width:200px;" emptyText="请选择指标类型" showNullItem="true" nullItemText="请选择指标" 
				url="${path}/dashBoard.do?method=kpiType" textField="type" valueField="type" />
		<input id="kpiName" name="kpiName" class="sui-textbox" emptyText="指标名称" style="width:150px;" onenter="onKeyEnter"/> 
		<input type="hidden" id="area" value=""/> 
		<input type="hidden" id="kpiId" value=""/> 
		<input type="hidden" id="menuId" value=""/>  
		<a class="sui-button" onclick="search()">查询</a>
		
		<div id="kpiGrid" class="sui-datagrid" url="${path}/dashBoard.do?method=kpiName" showPager="false"
				style="width:60%;height:90%">
	       <div property="columns">
	            <div type="checkcolumn"></div>         
	            <div field="kpiId" width="0" align="left" allowSort="true" headeralign="center">指标ID</div>  
	            <div field="kpiName" width="100%" align="left"  headeralign="center">指标名称</div>  
	        </div>	
	    </div> 
	    
	    <div align="center" class="select_kpi">
	    	<div  style="float:left;width:3px; background: url(../images/u700_line.png) repeat-y left;height:100%;margin-left:10px;margin-top:10px;"></div>
		    <div align="center" style="padding-top:100px;margin-left: 50px;">
				<h3 style="text-align: left;margin-bottom: 0;">展现方式:</h3>
				<p style="text-align: left;margin-bottom: 0;margin-top: 0;"><input type="radio" name="RadioGroup1"  title="四龙对比" value="silong" id="RadioGroup01" />四龙对比</p>
				<p style="text-align: left;margin-bottom: 0;margin-top: 0;"><input type="radio" name="RadioGroup1"  title="TOP5区分分析" value="top5" id="RadioGroup02" />TOP5区分分析</p>
				<p style="text-align: left;margin-bottom: 0;margin-top: 0;"><input type="radio" name="RadioGroup1"  title="趋势分析" value="line" id="RadioGroup03" />趋势分析</p>
				<p style="text-align: left;margin-bottom: 0;margin-top: 0;"><input type="radio" name="RadioGroup1"  title="占比分析" value="pie" id="RadioGroup04" />占比分析</p>
			</div>
		</div>
		<a align="center" class="sui-button" style="position: absolute;bottom: 0;right:20%;margin-bottom: 5px;" onclick="CloseWindow('ok')">确认</a>
	
	<script type="text/javascript">
	sui.parse();
	var grid = sui.get("kpiGrid");
	//根据指标设置指标选择展现方式的radioButton哪一个禁用
	function setRadioData(kpiId,chartTpye){
		var id = "#RadioGroup0"+chartTpye;
		if(kpiId==20003 || kpiId==20004 || kpiId==20005){//指标为短期、长期、总体欠费率的时候把四龙禁用
			$("#RadioGroup01").attr("disabled","true");
		}else{//其他指标把top5禁用
			$("#RadioGroup02").attr("disabled","true");
		}
		$(id).click();
	}
	function setChartType(kpiId){
		$.post("${path}/dashBoard.do?method=chartType",
				{kpiId:kpiId},
				function(e){
					var types = e.split(",");
					var id="";
					var v = document.getElementsByName("RadioGroup1");
					for(var i=0;i<types.length;i++){
						switch(i){
							case 0:
								id="#RadioGroup03";
								break;
							case 1:
								id="#RadioGroup04";
								break;
							case 2:
								id="#RadioGroup01";
								break;
							case 3:
								id="#RadioGroup02";
								break;
						}
						if(types[i]=="Y"){
							$(id).removeAttr("disabled");
						}else{
							$(id).attr("disabled","true");
						}
					}
					if(v[0].checked && $("#RadioGroup01").attr("disabled")=="disabled"){
						$("#RadioGroup02").click();
					}
					if(v[1].checked && $("#RadioGroup02").attr("disabled")=="disabled"){
						$("#RadioGroup01").click();
					}
		});
	}
	//表格加载完毕时设置默认指标
	grid.on("load",function(e){
		var kpiId=$("#kpiId").val();
		for(var i=0;e.data && i<e.data.length;i++){
			if(kpiId==e.data[i].kpiId){
				grid.select(e.data[i]);
			}
		}
	});
	grid.on("rowclick",function(e){
		var kpiId = e.row.kpiId;
		setChartType(kpiId);
	})
	function SetData(e,kpiId,chartTpye,menuId){
		$("#area").val(sui.clone(e));
		$("#kpiId").val(sui.clone(kpiId));
		$("#menuId").val(sui.clone(menuId));
		setRadioData(sui.clone(kpiId),sui.clone(chartTpye));
		search();//表格查询数据
		setChartType(kpiId);
	}
	//表格查询数据方法
	function search(){
		var kpiType = sui.get("kpiType").value;
		var kpiName = sui.get("kpiName").value;
		var area =$("#area").val();
		var menuId =$("#menuId").val();
		grid.load({kpiType:kpiType,kpiName:kpiName,area:area,menuId:menuId});
	}
		
	function valueChanged(e){
		search();
	}
	//关闭指标选择页面的时候调用的方法
	function CloseWindow(action) {
		if(action!="ok"){//点击确认按钮
			return;
		}
		var data = grid.getSelected();//获取选择行
		if(data==null){//没有选择指标时提示
			alert("请选择指标");
			return;
		}
		var v = document.getElementsByName("RadioGroup1");
		var chartNum;
		var chartType;
		for(var i=0;i<v.length;i++){
			if(v[i].checked){
				
				chartType=v[i].value;
			}
		}
		//获取图表类型，用于返回到主页后传到后台更新个性化指标
		if("silong"==chartType){
			chartNum=1;
		}else if("top5"==chartType){
			chartNum=2;
		}else if("line"==chartType){
			chartNum=3;
		}else if("pie"==chartType){
			chartNum=4;
		}
		var kpiId = data.kpiId;
		var kpiName= data.kpiName;
		var data = {kpiId:kpiId,kpiName:kpiName,type:chartType,chartNum:chartNum};
	    if (window.CloseOwnerWindow) return window.CloseOwnerWindow(data);
	    else window.close();            
	}
	</script>
</body>
</html>