<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="com.dglt.bb.pojo.WKipConfigV"%>
<%@include file="../../taglib.jsp"  %>
<%@page import="java.util.Calendar"%>
<%@page import="com.dglt.comm.util.gson.JsonUtil"%>

	

        <%
        request.setCharacterEncoding("UTF-8");
    	response.setHeader("progma", "no-cache"); //屏蔽页面缓存
    	response.setHeader("Cache-Control", "no-cache");
    	response.setDateHeader("Expires", 0);
    	
        String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//期间
		String	period = (String)request.getAttribute("period");
		//指标
		String	kpid  = (String)request.getAttribute("kpid");
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
		//分公司
		String branch = (String)request.getAttribute("branch");
		//营服
		String	bizcs  = (String)request.getAttribute("bizcs");
		//页面地图配置
		WKipConfigV wKipConfigV =(WKipConfigV)request.getAttribute("wKipConfigV");
		String showStyle = (String)request.getAttribute("showStyle");
        %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>销售经理曲线图</title>
<script type="text/javascript" src="<%=basePath%>js/FusionCharts.js"></script>
<script src="${path}/scripts/utilTool/tool.js" charset="UTF-8" type="text/javascript"></script>
<style type="text/css"> 
   *{margin:0;padding:0}
     body{min-width: 980px;width: expression_r( document.body.clientWidth < 980 ? "980px" : "auto" );}
	.tip_title{ color:#FFF;padding-left:1%;height:20px;padding-top:5px;vertical-align:middle;font:bold;font-size:16px;}
     .condition{margin-left:3%;margin-top:1%;}
	.image_title_selected{background-image:url(${path}/images/u97_original.png); background-repeat:no-repeat; width:121px; height:26px; vertical-align:middle; color:#FFF; text-align:center; padding-top:5px;font-size:18px;}
	.image_title{background-image:url(${path}/images/u93_original.png);background-repeat:no-repeat; width:121px; height:26px; vertical-align:middle; color:#000; text-align:center; padding-top:5px;font-size:18px;}
	.clien_title{height:20px;padding-top:5px;vertical-align:middle;color:#FFF;font:bold;padding-left:5px; font-size:18px}
	.mapLeft{width:49.4%;height:320px;background-color:#FFFFFF;float:left;border:solid 1px #D7D6E6; background:url(${path}/images/loaderc.gif) no-repeat center;}
	.mapRight{width:50%;height:320px;background-color:#FFFFFF;float:right;border:solid 1px #D7D6E6; background:url(${path}/images/loaderc.gif) no-repeat center;}
</style>
<script type="text/javascript" >
   		$(document).ready(
			function(e){
				sui.parse();
				setValue("period","<%=period%>");
				setValue("profess","<%=profess%>");
				setValue("Product","<%=productCode%>");
				setValue("Client","<%=client%>");
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
			    	  grid.mergeColumns(["qufen_name", "quyu_name","sp_name","product_name"]);
			     });
			     initPage();
			}
		);
   </script>

<script type="text/javascript">
var i;
var j=-1;
var sum=0;
var a="";
//存放加
var mapDate ;


function initPage(){
    //得到查询参数
    var period = sui.get("period").value ;
    var profess = sui.get("profess").value ;
    var productCode = sui.get("Product").value ;
    var client = sui.get("Client").value ; 
    var branch ="<%=branch%>";
    var bizcs ="<%=bizcs%>";
    var myType="<%=myType%>";
    var kpid="<%=kpid%>";
    var mapName ="<%=mapName%>" ;
    var  lineName="总计" ;
    //sui.get("Product").disable() ; 
    //查询数据 处理数据
     chargeParaMap(period, profess, branch, bizcs,productCode,myType,kpid,mapName,client) ;
   }
       

   function showAnchor3(datas,mapName,divId,charName,minValue,value1,Value2,maxValue,sortType,isAlert){
        var flexPath ="${path}/swf/Pie2D.swf" ;
        var func = "changeFunsion";
       // var date1=getMapDate(datas,mapName,minValue,value1,Value2,maxValue,sortType,isAlert) ;
        var xml = getPieDate(datas,mapName,value1,Value2,func);
        showAnchor(xml,divId,flexPath,charName);
	 }


   
	//通过<set元素>的link得到值传入后台去解析分公司名称
	//ajax传给后台用于联动折线图  折线图加载数据
	
	function changeFunsion(label){
		//alert(j+'----'+label);
		//将label解析成label中的值传递给后台
	     var period = sui.get("period").value ;
         var profess = sui.get("profess").value==null||sui.get("profess").value==""||sui.get("profess").value=="99"?"":sui.get("profess").value ;
         var productCode = sui.get("Product").value ;
         var client = sui.get("Client").value ;
		 var obj = eval(mapDate); 
		 var lineName= "";
		 var managerNo = "";
			if(obj!=null&&obj!=""){
				lineName=obj[label].chartLabelName;	
			    managerNo=obj[label].salesmanager_no;
				} 
	     var branch ="<%=branch%>";
	     var bizcs ="<%=bizcs%>";
	     var kpid="<%=kpid%>";
	     var myType="<%=myType%>";
	     var nextType="<%=wKipConfigV.getAttrib3()%>";
		 var haveNext="<%=wKipConfigV.getAttrib2()%>";
	//双击下一层		 
         if(label==j){
             if(haveNext=='Y'){
        	  window.location.href="${path}/shuntJumpAction.do?method=getNextPage&branch="+branch+"&myType="+nextType+"&kpid="+kpid+"&period="+period +"&profess="+profess +"&bizcs="+bizcs+"client"+client;
                }
               }else {
		   j=label ;
		 
		 chargeParaLine(period, profess, branch, bizcs,productCode,myType,lineName,kpid,client,managerNo) ;
		 chargeParaTable(period, profess, branch, bizcs,managerNo,productCode,myType,kpid,client) ;	
		 setTimeout(function(){j=-1;},300)
		 }
	}

	//曲线加载数据
     function  chargeParaLine(period, profess, branch, bizcs,productCode,myType,lineName,kpid,client,managerNo){	
    		var _path = "${path}";
    		clearDiv("2GLine","myChartId1");
    		var _url = _path+"/shuntJumpAction.do?method=turnover2GLine&branch="+branch+"&period="+period +"&profess="+profess +"&bizcs="+bizcs+"&productCode="+productCode+"&myType="+myType+"&kpid="+kpid+"&client="+client+"&managerNo="+managerNo;
    		$.ajax({
    			type:"post",
    			dataType:"json",
    			url: _url,
    			success:function(datas){
    		     showFlex("line",datas,lineName,"2GLine","${path}/swf/MSLine.swf","myChartId1");
    			}
    		  });   
       }  
	
    //地图加载数据
	 function chargeParaMap(period, profess, branch, bizcs,productCode,myType,kpid ,mapName,client){
		 var _path = "${path}";
		  var divId="2GMap"
		  var charName="myChartId2"  
		      clearDiv(divId,charName);
 		 var _url = _path+"/shuntJumpAction.do?method=turnover2GMap&branch="+branch+"&period="+period +"&profess="+profess +"&bizcs="+bizcs+"&productCode="+productCode+"&myType="+myType+"&kpid="+kpid+"&client="+client;
 		$.ajax({
 			type:"post",
 			dataType:"json",
 			url: _url,
 			success:function(datas){
 				showAnchor3(datas,mapName,divId,charName,"<%=wKipConfigV.getMinVale()%>","<%=wKipConfigV.getAlertVale()%>","<%=wKipConfigV.getWarningValue()%>","<%=wKipConfigV.getMaxValue()%>","<%=wKipConfigV.getAttrib1()%>","<%=wKipConfigV.getIsAlert()%>");
 				mapDate=datas ;
 				changeFunsion(0);
 			}
 		  });
	   }  
	 //表格加载数据
      function chargeParaTable(period, profess, branch, bizcs,managerNo,productCode,myType,kpid,client){
 		    var _path = "${path}";
 		    var grid = sui.get("clients");
 		   var arry = monthArry(period);
		    for(var i=0;i<12;i++){
		    	  var j = 12-i;
		    	grid.updateColumn("month0"+j, {header:arry[i]});
			    }
 		    grid.load({ "period": period ,"profess": profess  ,"branch": branch ,"bizcs": bizcs,"productCode":productCode ,"myType":myType,"kpid":kpid,"client":client,"managerNo":managerNo});
 		   
	   }
    //设定页面条件值
  	function setValue(id,value){
  		if(value!=null&&value!=""&&value!="null"){
  			sui.get(id).setValue(value);
  			}
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
</head>
<!-- 2G业务流失率分公司展示 -->
  <body onload="initPage()">
<!--   <div style="background-image:url(${path}/images/u11_original.jpg);width:100%;font-Family:'微软雅黑',Serif;font-size:14px;font-size:14px;padding-bottom:5px;">
    当前位置：仪表盘管理系统 >销售经理地图展示
    </div>    
   <div style="background-image:url(${path}/images/u11_original.jpg);width:100%">
      	<div style="background-image:url(${path}/images/u5_original.png);height:29px;width:100%;font-Family:'微软雅黑',Serif;font-size:14px; background-repeat: no-repeat;">
    		<div class="tip_title">销售经理展示</div>
    	</div>
    </div> --> 
    <form id="excelForm"
	action="${path}/exportExcel.do?method=tableexport" method="post"
	target="excelIFrame">
	<input type="hidden" name="columns" id="excelData" /> 
	<input type="hidden" name="excelProfess" id="excelProfess" />
	<input type="hidden" name="datas" id="datas" />
	<input type="hidden" name="mergedCells" id="mergedCells" />
	<input type="hidden" name="excelClient" id="excelClient" />
	<input type="hidden" name="excelPeriod" id="excelPeriod" />
	<input type="hidden" name="excelProduct" id="excelProduct" />
	<input type="hidden" name="myType" id="myType" value="<%=myType%>"/>
	<input type="hidden" name="kpiId" id="kpiId" />
	</form>
	<iframe id="excelIFrame" name="excelIFrame" style="display: none;"></iframe>
    <div class="condition"  >
        <font style="padding-right:30px;">报表期间:&nbsp;
    		<input id="period" name="period" url="${path}/dashBoard.do?method=month" class="sui-combobox"   value="<%=period%>"
    		 textField="text" valueField="id" onvaluechanged="initPage()"/>
    	</font>
        <font style="padding-right:30px;">专业:&nbsp;<input id="profess" name="Specialty"  showNullItem="true" nullItemText="全部（专业）"  
         class="sui-combobox" url="${path}/dashBoard.do?method=specialty" textField="localCode" valueField="spCode" onvaluechanged="initPage()" /></font>
        <font style="padding-right:30px;">客户:&nbsp;<input id="Client" name="Client"    showNullItem="true" nullItemText="全部（客户）"
         class="sui-combobox" url="${path}/dashBoard.do?method=client" textField="clientName" valueField="clientCode" onvaluechanged="initPage()" /></font>
        <font style="padding-right:30px;">产品类型:&nbsp;<input  id="Product" showNullItem="true"  
         name="Product" class="sui-combobox" url="${path}/dashBoard.do?method=product" textField="productName" valueField="productCode"  onvaluechanged="initPage()"  /></font>
         <input type="button" value="导出Excel" onclick="ExportExcel()" style="margin-left: 50px;" />	
    </div>
    <div style=" background-color:#C11403; background-repeat: no-repeat;width:100%;height:3px;margin-top:0.5%;margin-bottom:5px;"></div>
    	<div id="shortOweRate" style="padding-top:3px;width:100%;background-repeat: no-repeat;height:310px;margin-bottom:10px;" >
          <div id="2GMap" style="width:50%;height:320px;background-color:#FAFBFE;float:left;background:url(${path}/images/loaderc.gif) no-repeat center;"></div>
          <div   id="2GLine" style="width:49%;height:320px;background-color:#FAFBFE;float:right;background:url(${path}/images/loaderc.gif) no-repeat center;"></div>
         <!--  <span style="padding-left:5px;" id="2GMap"></span>-->
          <!--  <span style="margin-left:52px;" id="2GLine"></span> -->
        </div>
            <div id="clients" class="sui-datagrid" style="width:100%;height:315px" allowAlternating="true"
                 idField="itemId" dataField="items" allowCellSelect="true"
                url="${path}/shuntJumpAction.do?method=getTableData" showPager="fasle" 
             >
            <div property="columns" headerStyle="font-Family:'微软雅黑',Serif;"> 
            <div field="qufen_name" id="qufen_name" name="qufen_name" width="60" align="center" headerAlign="center" >区域分公司</div>
            	<div field="quyu_name" id="quyu_name" name="quyu_name" width="60" align="center" headerAlign="center" >营服中心</div> 
				<div header="" field="sp_name" id="sp_id" name="sp_name" width="40" align="center" headerAlign="center" >专业</div>     
				<div field="client_name" name="client_name" id="client_code" width="40" align="center" headerAlign="center" >客户</div> 
				<div field="salesmanager_name" name="salesmanager_name" id="quyu_id" width="40" align="left" headerAlign="center" >销售经理</div>    
				<div field="product_name" name="product_name" id="product_name" width="80" align="center" headerAlign="center" >产品类型</div> 
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
    
</body>
</html>