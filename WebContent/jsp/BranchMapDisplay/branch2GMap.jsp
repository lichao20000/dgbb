<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="com.dglt.bb.pojo.WKipConfigV"%>
<%@include file="../../taglib.jsp"  %>
<script type="text/javascript" src="${path}/scripts/utilTool/tool.js"></script>
<script src="<%=request.getContextPath()%>/scripts/utilTool/kpiBranchTool.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/kpi_branch_page.css"></link>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dglt.comm.util.gson.JsonUtil"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setHeader("progma", "no-cache"); //屏蔽页面缓存
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
      <%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    	//指标
		String	kpid  = (String)request.getAttribute("kpid");
    	//期间
		String	period = (String)request.getAttribute("period");
		//专业
		String	profess  = (String)request.getAttribute("profess");
		if("20008".equals(kpid)){
			profess="11";
		}else if("20009".equals(kpid)){
			profess="12";
		}
		//客户
		String	client   = (String)request.getAttribute("client");
		//产品
		String	productCode  = (String)request.getAttribute("productCode");
		
		//跳转类型
		String	myType  = (String)request.getAttribute("myType");
		//跳转类型
		String	mapName  = (String)request.getAttribute("mapName");
		//页面地图配置
		String	branch  = (String)request.getAttribute("branch");
		String	flexName  = (String)request.getAttribute("flexName");
		WKipConfigV wKipConfigV =(WKipConfigV)request.getAttribute("wKipConfigV");
		String showStyle = (String)request.getAttribute("showStyle");
        %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Title</title>
<script type="text/javascript" src="<%=basePath%>js/FusionCharts.js"></script>

<script type="text/javascript" >
   		$(document).ready(
			function(e){
				sui.parse();
				setValue("period","<%=period%>");
				setValue("profess","<%=profess%>");
				if(<%=kpid%>==20008 || <%=kpid%>==20009){
					sui.get("Product").load("${path}/dashBoard.do?method=product&specialty="+"<%=profess%>");
				}
				setValue("Product","<%=productCode%>");
				setValue("Client","<%=client%>");
				//sui.get("Client").disable();
				var showStyle = "<%=showStyle%>";
				var showStyleArr = showStyle.split(",");
				if(showStyleArr[0]=="N"){
					sui.get("Client").disable();
					sui.get("clients").setColumnWidth("client_name",0);
				}
				if(showStyleArr[1]=="N"){
					sui.get("period").disable();
				}
				if(showStyleArr[2]=="N"){
					sui.get("Product").disable();
					sui.get("clients").setColumnWidth("product_name",0);
				}
				if(showStyleArr[3]=="N"){
					sui.get("profess").disable();
				}
				var grid = sui.get("clients");
			      grid.on("load", function () {
			    	  grid.mergeColumns(["qufen_name", "quyu_name","sp_name","client_name","product_name"]);
			     });
			     initPage();
			}
		);

   		function initPage(){
   		    //得到查询参数
   		    var period = sui.get("period").value ;
   		    var profess = sui.get("profess").value ;
   		    var productCode = sui.get("Product").value ;
   		    var client = sui.get("Client").value ;  
   		    var branch ='';
   		    var bizcs ='';
   		    var myType="<%=myType%>";
   		    var kpid="<%=kpid%>";
   		    var mapName ="<%=mapName%>" ;
   		    var  lineName="总计" ;
   		    //查询数据 处理数据
   		     chargeParaMap(period, profess, branch, bizcs,productCode,myType,kpid,mapName,client) ;
   		    // chargeParaLine(period, profess, branch, bizcs,productCode,myType,lineName,kpid,client) ;
   		    // chargeParaTable(period, profess, branch, bizcs,productCode,myType,kpid,client) ;
   		 }

   		function selectChange(e){
   			  var period = sui.get("period").value ;
   			    var profess = sui.get("profess").value ;
   			    var productCode = sui.get("Product").value ;
   			    var client = sui.get("Client").value ;
   			    var branch ='';
   			    var bizcs ='';
   			    var myType="<%=myType%>";
   			    var kpid="<%=kpid%>";
   			    var mapName ="<%=mapName%>" ;
   			    var flexName ="<%=flexName%>" ;
   			    var  lineName="总计" ;
   			    if(e.sender.id=="profess"){//产品和专业联动
   					sui.get("Product").load("${path}/dashBoard.do?method=product&specialty="+e.value);
   				}
   			    //查询数据 处理数据
   			     chargeParaMap(period, profess, branch, bizcs,productCode,myType,kpid,mapName,client) ;
   				// var branch =  mapDate[1].districtBranchCode;;
   			
   		}

   		//通过<set元素>的link得到值传入后台去解析分公司名称
   		//ajax传给后台用于联动折线图  折线图加载数据
   		function changeFunsion(label){
   			//alert(j+'----'+label);
   			//将label解析成label中的值传递给后台
   		     var period = sui.get("period").value ;
   	         var profess = sui.get("profess").value ;
   	         var productCode = sui.get("Product").value ;
   	         var client = sui.get("Client").value ;
   	         var lineName ='' ;
   	         var branch =''
   			 var obj = eval(mapDate); 
   			 if(obj==null||obj==''){ 
   				    clearDiv("2GLine","myChartId1");
   				 }else {
   					  lineName=obj[label].districtBranchName;	
   				       branch =  obj[label].districtBranchCode;
   					 }
   			
   		     var kpid="<%=kpid%>";
   		     var bizcs='' ;
   		     var myType="<%=myType%>";
   		     var nextType="<%=wKipConfigV.getAttrib3()%>" ;
   			   //  alert(label);
   			 var haveNext="<%=wKipConfigV.getAttrib2()%>";
   		//双击下一层		 
   	         if(label==j){
   	             if(haveNext=='Y'){
                      var _url= "${path}/shuntJumpAction.do?method=getNextPage&branch="+branch+"&myType="+nextType+"&kpid="+kpid+"&period="+period +"&profess="+profess +"&bizcs="+bizcs+"&client="+client+"&productCode="+productCode;   	             
   	        	  window.location.href=_url ;
   	                }
   	               }else {
   			   j=label ;
   			 var bizcs ='';
   			 chargeParaLine(period, profess, branch, bizcs,productCode,myType,lineName,kpid,client) ;
   			 chargeParaTable(period, profess, branch, bizcs,productCode,myType,kpid,client) ;	
   			 setTimeout(function(){j=-1;},300)
   			 }
   		}

   	 //地图加载数据
   	 function chargeParaMap(period, profess, branch, bizcs,productCode,myType,kpid ,mapName,client){
   		 var _path = "${path}";
    		 var _url = _path+"/shuntJumpAction.do?method=turnover2GMap&branch="+branch+"&period="+period +"&profess="+profess +"&bizcs="+bizcs+"&productCode="+productCode+"&myType="+myType+"&kpid="+kpid+"&client="+client  ;
     		$.ajax({
    			type:"post",
    			dataType:"json",
    			url: _url,
    			success:function(datas){
    				showAnchor3(datas,mapName,"<%=wKipConfigV.getMinVale()%>","<%=wKipConfigV.getAlertVale()%>","<%=wKipConfigV.getWarningValue()%>","<%=wKipConfigV.getMaxValue()%>","<%=wKipConfigV.getAttrib1()%>","<%=wKipConfigV.getIsAlert()%>");
    				mapDate=datas ;
    			   changeFunsion(0);
    			}
    		  });
   	   }  

   	 function showAnchor(datas,lineName){
			var xml="";
			var obj = eval(datas);
			xml = getLineData(obj,lineName,"1");	
			var myChart = getChartFromId("myChartId1");
			if(myChart==null){
				var myChart = new FusionCharts("${path}/swf/MSLine.swf","myChartId1", "100%","100%","0","1");
				myChart.setDataXML(xml);
				myChart.render("2GLine");
				}else{
				myChart.setDataXML(xml);
				}	
 	 }

   	function showAnchor3(datas,mapName,minValue,value1,Value2,maxValue,sortType,isAlert){
        var flexPath ="${path}/swf/FCMap_GD_Dongguan.swf" ;
        var date1=getMapDate_1(datas,mapName,minValue,value1,Value2,maxValue,sortType,isAlert) ;
        var divId="2GMap"
        var charName="myChartId2"  
        	clearDiv(divId,charName);
        insertFlexToDivs(flexPath,divId ,date1 ,charName) ;
	 }


	//曲线加载数据
     function  chargeParaLine(period, profess, branch, bizcs,productCode,myType,lineName,kpid,client){	
    		var _path = "${path}";
    		clearDiv("2GLine","myChartId1");
    		var _url = _path+"/shuntJumpAction.do?method=turnover2GLine&branch="+branch+"&period="+period +"&profess="+profess +"&bizcs="+bizcs+"&productCode="+productCode+"&myType="+myType+"&kpid="+kpid+"&client="+client ;
    		$.ajax({
    			type:"post",
    			dataType:"json",
    			url: _url,
    			success:function(datas){
    		     showAnchor(datas,lineName);
    			}
    		  });   
       }  
	
	 //表格加载数据
      function chargeParaTable(period, profess, branch, bizcs,productCode,myType,kpid,client){
 		    var _path = "${path}";
 		    var grid = sui.get("clients");
 		    var arry = monthArry(period);
		    for(var i=0;i<12;i++){
			    var j = i+1;
		    	grid.updateColumn("month0"+j, {header:arry[i]});
			    }
 		    grid.load({ "period": period ,"profess": profess  ,"branch": branch ,"bizcs": bizcs,"productCode":productCode ,"myType":myType,"kpid":kpid,"client":client});
	   }
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
	function ExportExcel() {
		var grid = sui.get("clients"); 
		if(sui.encode(grid.data)=="[]"){
			alert("没有可导出的数据");
			return ;
		}
		var columns = grid.getBottomColumns();
		var c=[];
		for(var i=0;i<columns.length;i++){
			if(columns[i].width!="" && columns[i].width!="0px"){
				c[c.length] = columns[i] ;
			}
		}
		columns = getColumns(c);
		var json = sui.encode(columns);                        
		document.getElementById("excelData").value = json;
		// alert(document.getElementById("excelData").value);
		
		document.getElementById("datas").value= sui.encode(grid.data);
		document.getElementById("mergedCells").value= sui.encode(grid._mergedCells);
		document.getElementById("excelProfess").value= sui.get("profess").value;
		document.getElementById("excelClient").value= sui.get("Client").value;
		document.getElementById("excelProduct").value= sui.get("Product").value;
		var excelForm = document.getElementById("excelForm");
		excelForm.submit();  
	}
 </script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<!-- 2G业务流失率分公司展示 -->
  <body onload="initPage()">
  <!-- 
   <div class="div1">
     <div class="div2">
                   当前位置：仪表盘管理系统 > 分公司展示
    </div>
    <div  class="div3" >
      	<div  class="div4" >
    	 <div class="tip_title">分公司展示展示</div>
    	</div>
    </div>
     -->
     <form id="excelForm"
	action="${path}/exportExcel.do?method=tableexport&kpiId=<%=kpid%>" method="post"
	target="excelIFrame">
	<input type="hidden" name="columns" id="excelData" /> 
	<input type="hidden" name="excelProfess" id="excelProfess" />
	<input type="hidden" name="datas" id="datas" />
	<input type="hidden" name="mergedCells" id="mergedCells" />
	<input type="hidden" name="excelClient" id="excelClient" />
	<input type="hidden" name="excelPeriod" id="excelPeriod" />
	<input type="hidden" name="excelProduct" id="excelProduct" />
	<input type="hidden" name="myType" id="myType" value="<%=myType%>"/>
	<input type="hidden" name="kpiId" id="kpiId" value="<%=kpid%>"/>
	</form>
	<iframe id="excelIFrame" name="excelIFrame" style="display: none;"></iframe>
    <div  class="condition"  >
        <font class="font1">报表期间:&nbsp;
    		<input id="period" name="period" url="${path}/dashBoard.do?method=month" class="sui-combobox"   value="<%=period%>"
    		 textField="text" valueField="id" onvaluechanged="selectChange"/>
    	</font>
        <font class="font1">专业:&nbsp;<input id="profess" name="profess" value="<%=profess%>" showNullItem="true" nullItemText="全部（专业）" 
         class="sui-combobox" url="${path}/dashBoard.do?method=specialty" textField="localCode" valueField="spCode" onvaluechanged="selectChange" /></font>
        <font class="font1">客户:&nbsp;<input id="Client" name="Client" showNullItem="true" nullItemText="全部（客户）"  value="<%=client%>"
         class="sui-combobox" url="${path}/dashBoard.do?method=client" textField="clientName" valueField="clientCode" onvaluechanged="selectChange" /></font>
        <font class="font1">产品类型:&nbsp;<input width="150" id="Product" showNullItem="true" nullItemText="全部（产品）"  value="<%=productCode%>"
         name="Product" class="sui-combobox" url="${path}/dashBoard.do?method=product" textField="productName" valueField="productCode"  onvaluechanged="selectChange"/></font>
    	<input type="button" value="导出Excel" onclick="ExportExcel()" style="margin-left: 50px;" />	
    </div>
    
	<div class="div5"></div>
    <div style="widh:100% ">
    	<div class="div7" >
          <div  class="mapLeft"  id="2GMap" ></div>
          <div  class="mapRight"  id="2GLine" ></div>
        </div>
    	<div class="div6"></div>
            <div id="clients" class="sui-datagrid" style="width:100%;height:200px"
                 idField="itemId" dataField="items" allowCellSelect="true" allowAlternating="true"
                url="${path}/shuntJumpAction.do?method=getTableData" showPager="fasle" 
             >
               <div property="columns"> 
               	<div field="qufen_name" id="qufen_name" name="qufen_name" width="60" align="center" headerAlign="center" >区域分公司</div>
            	<div field="quyu_name" id="quyu_name" name="quyu_name" width="60" align="center" headerAlign="center" >营服中心</div> 
				<div header="" field="sp_name" id="sp_id" name="sp_id" width="40" align="center" headerAlign="center" >专业</div>     
				<div field="client_name" name="client_name" id="client_code" width="40" align="center" headerAlign="center" >客户</div> 
				<div field="product_name" id="product_name" width="80" align="center" headerAlign="center" >产品名称</div> 
                 
                <div name="month01" field="month01" id="month01" width="40" align="center" headerAlign="center" ></div>      
                <div name="month02" field="month02" id="month02" width="40" align="center" headerAlign="center" ></div>      
                <div name="month03" field="month03" id="month03" width="40" align="center" headerAlign="center" ></div>         
                <div name="month04" field="month04" id="month04" width="40" align="center" headerAlign="center" ></div>
                <div name="month05" field="month05" id="month05" width="40" align="center" headerAlign="center" ></div>     
                <div name="month06" field="month06" id="month06" width="40" align="center" headerAlign="center" ></div>      
                <div name="month07" field="month07" id="month07" width="40" align="center" headerAlign="center" ></div>      
                <div name="month08" field="month08" id="month08" width="40" align="center" headerAlign="center" ></div>         
                <div name="month09" field="month09" id="month09" width="40" align="center" headerAlign="center" ></div>
                <div name="month010" field="month010" id="month010" width="40" align="center" headerAlign="center" ></div>      
                <div name="month011" field="month011" id="month011" width="40" align="center" headerAlign="center" ></div>         
                <div name="month012" field="month012" id="month012" width="40" align="center" headerAlign="center" ></div>
            </div>
           </div>
     </div>
</body>
</html>