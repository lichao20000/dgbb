<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../../taglib.jsp"  %>
<%@page import="com.dglt.comm.util.gson.JsonUtil"%>
<%@page import="com.dglt.bb.pojo.WKipConfigV"%>
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
		
		//页面地图配置
		WKipConfigV wKipConfigV =(WKipConfigV)request.getAttribute("wKipConfigV");
		String showStyle = (String)request.getAttribute("showStyle");
        %>
<html>
  <head>
    <title>分公司曲线图</title>
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
	.mapLeft{width:49.4%;height:320px;background-color:#FFFFFF;float:left; background:url(${path}/images/loaderc.gif) no-repeat center;}
	.mapRight{width:50%;height:320px;background-color:#FFFFFF;float:right; background:url(${path}/images/loaderc.gif) no-repeat center;}
</style>
   


     <script type="text/javascript">
     $(document).ready(
 			function(e){
 				sui.parse();
 				//sui.get("clients").load();
 				//标签页转换方法设置
 				var nowDate = "<%=period%>";
 				var nowPro = "<%=profess%>";
 				var nowCli = "<%=client%>";
 				setValue("period",nowDate);
 				setValue("profess",nowPro);
 				setValue("Client",nowCli);
 				//sui.get("Product").disable();
 				var mapName = "<%=mapName%>";
 				if(mapName=="长期欠费率"){
 					sui.get("tabs").activeTab(parseInt(1));
 	 				} 
 				if(mapName=="总体欠费率"){
 					sui.get("tabs").activeTab(parseInt(2));
 	 				} 
 				if(mapName=="短期欠费率"){
 					sui.get("tabs").activeTab(parseInt(0));
 	 				}
 				var flag = false;
 				if(mapName!="短期欠费率"){
					flag = true;
	 				}
 				var showStyle = "<%=showStyle%>";
				var showStyleArr = showStyle.split(",");
				if(showStyleArr[0]=="N"){
					sui.get("Client").disable();
					sui.get("clients0").setColumnWidth("client_name",0);
					sui.get("clients1").setColumnWidth("client_name",0);
					sui.get("clients2").setColumnWidth("client_name",0);
				}
				if(showStyleArr[1]=="N"){
					sui.get("period").disable();
				}
				if(showStyleArr[2]=="N"){
					sui.get("Product").disable();
					sui.get("clients0").setColumnWidth("product_name",0);
					sui.get("clients1").setColumnWidth("product_name",0);
					sui.get("clients2").setColumnWidth("product_name",0);
				}
				if(showStyleArr[3]=="N"){
					sui.get("profess").disable();
				}
				var grid0 = sui.get("clients0");
			      grid0.on("load", function () {
			          grid0.mergeColumns(["qufen_name", "quyu_name","sp_name","client_name"]);
			     });
			      var grid1 = sui.get("clients1");
			      grid1.on("load", function () {
			          grid1.mergeColumns(["qufen_name", "quyu_name","sp_name","client_name"]);
			     });
			      var grid2 = sui.get("clients2");
			      grid2.on("load", function () {
			          grid2.mergeColumns(["qufen_name", "quyu_name","sp_name","client_name"]);
			     });
 				sui.get("tabs").on("activechanged",function(e){
    				if(e.tab.title=="长期欠费率"){
        				//重新加载长期欠费率饼图
        				var url="companyMap.do?method=companyPageAreaC";
        				url=createUrlWithParam(url);
        		        showAnchor3(url,"changeFunsionC","branchMap3DC","myChartId3","长期","${path}/swf/FCMap_GD_Dongguan.swf",1);
        		        flag=false;	
    				}else if(e.tab.title=="总体欠费率"){
    					//重新加载总欠费率饼图
    					var url="companyMap.do?method=companyPageAreaZ";
    					url=createUrlWithParam(url);
    						showAnchor3(url,"changeFunsionZ","branchMap3DZ","myChartId4","总体","${path}/swf/FCMap_GD_Dongguan.swf",2);
    						flag=false;	
    				}else{
    					if(!flag){
    					var url="companyMap.do?method=companyPageArea";
    					url=createUrlWithParam(url);
    						showAnchor3(url,"changeFunsion","branchMap3D","myChartId2","短期","${path}/swf/FCMap_GD_Dongguan.swf",0);
    						flag=false;	
    					  }
        				}
    			});
 			}
 		);
	//拼接字符串for循环用到的局部变量
     var i;
     var j=-1;
     var obja;
     var key = false;
    
   //加入条件筛选加载图表数据  
        function createUrlWithParam(url){
        	 var period = sui.get("period").value==""||sui.get("period").value==null?"":sui.get("period").value; 
             var profess = sui.get("profess").value==""||sui.get("profess").value==null?"":sui.get("profess").value; 
             var client = sui.get("Client").value==""||sui.get("Client").value==null?"":sui.get("Client").value; 
				if(period!=null&&period!=""){
					url+="&period="+period;
					}
				if(profess!=null&&profess!=""){
					url+="&profess="+profess;
					}
				if(client!=null&&client!=""){
					url+="&client="+client;
					}
				
				return url;
            }
    //传入指定条件查询相关数据
        function onChange(){
            var url = "";
            var urlt = "";
        	if(sui.get("tabs").activeIndex==1){
				url="companyMap.do?method=companyPageAreaC";
				urlt = createUrlWithParam(url);	
				showAnchor3(urlt,"changeFunsionC","branchMap3DC","myChartId3","长期","${path}/swf/FCMap_GD_Dongguan.swf",1 );
				
				}
			if(sui.get("tabs").activeIndex==2){
				url="companyMap.do?method=companyPageAreaZ";
				urlt = createUrlWithParam(url);
				showAnchor3(urlt,"changeFunsionZ","branchMap3DZ","myChartId4","总体","${path}/swf/FCMap_GD_Dongguan.swf",2);
			}
			if(sui.get("tabs").activeIndex==0){
				url="companyMap.do?method=companyPageArea";
				urlt = createUrlWithParam(url);
				showAnchor3(urlt,"changeFunsion","branchMap3D","myChartId2","短期","${path}/swf/FCMap_GD_Dongguan.swf",0);
			}
       
            }
  	
	//标签变换地图显示
	function showAnchor3(url,func,divId,chartId,str,flexPath,type){
		var xml="";
		var jsonxml="";
		var _path = "${path}";
		var _url = _path+"/"+url;
		clearDiv(divId,chartId);
		$.ajax({
			type:"post",
			dataType:"json",
			url: _url,
			success:function(datas){
				obja = datas;
				var mapName = "分公司"+str+"欠费率分布图";
				//var xml = getMapDateT(obja,mapName,5,8,func);
				var xml = getMapDate(obja,mapName,"<%=wKipConfigV.getAttrib1()%>",func);
				showAnchor(xml,divId,flexPath,chartId);	
					if(type==0){
						changeFunsion(0);
						}
					if(type==1){
						changeFunsionC(0);
						}
					if(type==2){
						changeFunsionZ(0);
						}	
			}
		});
		}
	//通过<set元素>的link得到值传入后台去解析分公司名称
	//ajax传给后台用于联动折线图
	function changeFunsion(label){
		//将label解析成label中的值传递给后台
		var url = "/companyMap.do?method=getOweRateCompanyBight";
	    setParam(label,url,0,"clients0","busiScPage","line","branchMapzhe","${path}/swf/MSLine.swf","myChartId1");
	}

	//ajax传给后台用于联动折线图
	function changeFunsionC(label){
		var url = "/companyMap.do?method=getOweRateCompanyBightC";
	    setParam(label,url,1,"clients1","busiScPage","line","branchMapzheC","${path}/swf/MSLine.swf","myChartId5");
		}
	//ajax传给后台用于联动折线图
	function changeFunsionZ(label){
	    var url = "/companyMap.do?method=getOweRateCompanyBightZ";
	    setParam(label,url,2,"clients2","busiScPage","line","branchMapzheZ","${path}/swf/MSLine.swf","myChartId6");
		}
	//传递参数生成与地图关联的相关图表
	function setParam(label,url,index,formId,method,chartType,divId,flexPath,chartId){
		var districtBranchCode="" ;
		var kpid = "";
		if(obja!=null&&obja!=""){
			districtBranchCode = obja[label].districtBranchCode;
			 kpid = obja[obja.length-1].kpiId;
			}
		clearDiv(divId,chartId);
		var period = sui.get("period").value==""||sui.get("period").value==null?"":sui.get("period").value; 
        var profess = sui.get("profess").value=="99"||sui.get("profess").value==""||sui.get("profess").value==null?"":sui.get("profess").value; 
        var client = sui.get("Client").value==""||sui.get("Client").value==null?"":sui.get("Client").value;
        var nextType="<%=wKipConfigV.getAttrib3()%>";
   		var haveNext="<%=wKipConfigV.getAttrib2()%>";
   	if(haveNext=='Y'){
        forwordNextPage(label);
    		if(key){
    			nextPage(districtBranchCode,"${path}/busiSc.do?method="+method,index,kpid,nextType);
    			}
    		setTimeout(
    				function(){
    					   if(j==-1){
    						   getLineDate(label,districtBranchCode,url,chartType,divId,flexPath,chartId);
    						   chargeParaTable(period, profess, districtBranchCode, "","","","branch",kpid,formId,client);
    						   }
    			        },300);
   		}else{
 	   		getLineDate(label,code,busiScCode,url,chartType,divId,flexPath,chartId);
 	   		chargeParaTable(period, profess, districtBranchCode, "","","","branch",kpid,formId,client);
     		}
		}
	//ajax取后台折线数据
	function getLineDate(label,districtBranchCode,url,line,divId,flexPath,chartId){
		
		var lineName= "";
		if(obja!=null&&obja!=""){
			lineName = obja[label].districtBranchName;
			}
		var _path = "${path}";
		var _url = _path+url+"&districtBranchCode="+districtBranchCode;
			_url = createUrlWithParam(_url);
		$.ajax({
			type:"post",
			dataType:"json",
			url: _url,
			success:function(datas){
				showFlex(line,datas,lineName,divId,flexPath,chartId);
			}
		});
		}
	//转入下一层页面
	function forwordNextPage(label){ 
		if(label==j){
			  key=true;
          }else {
		   j=label;
		   setTimeout(
				   function(){j=-1;},250);  
	}
}
	//跳转页面
   function nextPage(districtBranchCode,url,typeCode,kpid,nextType){
		  var url = url+"&districtBranchCode="+districtBranchCode+"&typeCode="+typeCode+"&kpid="+kpid+"&myType="+nextType;
		  url=createUrlWithParam(url);
	  	  window.location.href=url;
		}
	//读取表格数据
	function chargeParaTable(period, profess, branch, bizcs,managerNo,productCode,myType,kpid,tableId,client){

		    var grid = sui.get(tableId);
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
		var tabs = sui.get("tabs").getActiveTab();
		var grid;
		if(tabs.title=="短期欠费率"){
			grid = sui.get("clients0"); 
			document.getElementById("kpiId").value="20003";
		}else if(tabs.title=="长期欠费率"){
			grid = sui.get("clients1"); 
			document.getElementById("kpiId").value="20004";
		}else if(tabs.title=="总体欠费率"){
			grid = sui.get("clients2");
			document.getElementById("kpiId").value="20005"; 
		}
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
  
  <body >
 <!--    <div style="background-image:url(${path}/images/u11_original.jpg);widh:100%;font-Family:'微软雅黑',Serif;font-size:14px;padding-bottom:5px;">
    当前位置：仪表盘管理系统 > 分公司地图展示 
    </div>    
    <div style="background-image:url(${path}/images/u11_original.jpg);width:100%">
      	<div style="background-image:url(${path}/images/u5_original.png);height:29px;width:100%; background-repeat: no-repeat;">
    		<div class="tip_title">分公司地图展示</div>
    	</div>
    </div>--> 
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
    		<input id="period" name="period" url="${path}/dashBoard.do?method=month"  class="sui-combobox" value="<%=period%>" textField="text" valueField="id" onvaluechanged="onChange()"/>
    	</font>
        <font style="padding-right:30px;">专业:&nbsp;<input id="profess" name="Specialty" showNullItem="true" nullItemText="全部（专业）"
         class="sui-combobox" url="${path}/dashBoard.do?method=specialty" textField="localCode" valueField="spCode" onvaluechanged="onChange()" /></font>
        <font style="padding-right:30px;">客户:&nbsp;<input id="Client" name="Client" showNullItem="true" nullItemText="全部（客户）"
         class="sui-combobox" url="${path}/dashBoard.do?method=client" textField="clientName" valueField="clientCode" onvaluechanged="onChange()" /></font>
        <font style="padding-right:30px;">产品类型:&nbsp;<input id="Product" showNullItem="true" 
         name="Product" class="sui-combobox"  textField="productName" valueField="productCode"  onvaluechanged="onChange()" /></font>
         <input type="button" value="导出Excel" onclick="ExportExcel()" style="margin-left: 50px;" />	
    </div>
     <div style=" background-color:#C11403; background-repeat: no-repeat;width:100%;height:3px;margin-top:0.5%;margin-bottom:5px;"></div>
     <div id="tabs" class="sui-tabs" activeIndex="0" style="background-image:url(${path}/images/u11_original.jpg);width:100%;height:678px; background-repeat:repeat-x;padding-left:1%;font-Family:'微软雅黑',Serif;" >
        <div id = "tab1" title="短期欠费率" headerStyle="font-Family:'微软雅黑',Serif;font-size:12px;">
        
    	<div id="shortOweRate" style="padding-top:2px;width:100%;background-repeat: no-repeat;height:49%;margin-bottom:10px;" >
            <!-- <span style="padding-left:5px;" id="branchMap3D"></span> -->
            <!-- <span style="margin-left:47px;margin-bottom:50px;" id="branchMapzhe"></span>-->
       	  <div id="branchMap3D" style="width:50%;height:320px;background-color:#FAFBFE;float:left;background:url(${path}/images/loaderc.gif) no-repeat center;"></div>
          <div id="branchMapzhe" style="width:49%;height:320px;background-color:#FAFBFE;float:right;background:url(${path}/images/loaderc.gif) no-repeat center;"></div>
        </div>
     	  <div style="background-image:url(${path}/images/u11_original.jpg);background-repeat: repeat-x;height:49%;width:100%,">
            <div id="clients0" class="sui-datagrid" style="width:100%;height:90%;" allowAlternating="true"
                 idField="itemId" dataField="items" allowCellSelect="true"
                url="${path}/shuntJumpAction.do?method=getTableData" showPager="fasle" 
                >
            <div property="columns" headerStyle="font-Family:'微软雅黑',Serif;"> 
            	<div field="qufen_name" id="qufen_name" name="qufen_name" width="60" align="center" headerAlign="center" >区域分公司</div>
            	<div field="quyu_name" id="quyu_name" name="quyu_name" width="60" align="center" headerAlign="center" >营服中心</div> 
				<div header="" field="sp_name" id="sp_id" name="sp_name" width="40" align="center" headerAlign="center" >专业</div>     
				<div field="client_name" name="client_name" id="client_code" width="40" align="center" headerAlign="center" >客户</div> 
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
    </div>
   
        </div>
        <div id = "tab2" title="长期欠费率" headerStyle="font-Family:'微软雅黑',Serif;font-size:12px;" >
          
    	<div id="shortOweRate" style="padding-top:2px;width:100%;background-repeat: no-repeat;height:49%;margin-bottom:10px;" >
            <!-- <span style="padding-left:5px;" id="branchMap3D"></span> -->
            <!-- <span style="margin-left:47px;margin-bottom:50px;" id="branchMapzhe"></span>-->
       	  <div id="branchMap3DC" style="width:50%;height:320px;background-color:#FAFBFE;float:left;background:url(${path}/images/loaderc.gif) no-repeat center;"></div>
          <div id="branchMapzheC" style="width:49%;height:320px;background-color:#FAFBFE;float:right;background:url(${path}/images/loaderc.gif) no-repeat center;"></div>
        </div>
         
       <div style="background-image:url(${path}/images/u11_original.jpg);background-repeat: repeat-x;height:49%;width:100%">
           <div id="clients1" class="sui-datagrid" style="width:100%;height:90%;" allowAlternating="true"
                 idField="itemId" dataField="items" allowCellSelect="true"
                url="${path}/shuntJumpAction.do?method=getTableData" showPager="fasle" 
            >

            <div property="columns"  headerStyle="font-Family:'微软雅黑',Serif;"> 
                <div field="qufen_name" id="qufen_name" name="qufen_name" width="60" align="center" headerAlign="center" >区域分公司</div>
            	<div field="quyu_name" id="quyu_name" name="quyu_name" width="60" align="center" headerAlign="center" >营服中心</div> 
				<div header="" field="sp_name" id="sp_id" name="sp_name" width="40" align="center" headerAlign="center" >专业</div>     
				<div field="client_name" name="client_name" id="client_code" width="40" align="center" headerAlign="center" >客户</div> 
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
    </div>
        
        </div>
        <div id = "tab3"  title="总体欠费率" headerStyle="font-Family:'微软雅黑',Serif;font-size:12px;">
        
    	<div id="shortOweRate" style="padding-top:2px;width:100%;background-repeat: no-repeat;height:49%;margin-bottom:10px;" >
            <!-- <span style="padding-left:5px;" id="branchMap3D"></span> -->
            <!-- <span style="margin-left:47px;margin-bottom:50px;" id="branchMapzhe"></span>-->
       	  <div id="branchMap3DZ" style="width:50%;height:320px;background-color:#FAFBFE;float:left;background:url(${path}/images/loaderc.gif) no-repeat center;"></div>
          <div id="branchMapzheZ" style="width:49%;height:320px;background-color:#FAFBFE;float:right;background:url(${path}/images/loaderc.gif) no-repeat center;"></div>
        </div>
       <div style="background-image:url(${path}/images/u11_original.jpg);background-repeat: repeat-x;height:49%;width:100%">
             <div id="clients2" class="sui-datagrid" style="width:100%;height:90%;" allowAlternating="true"
                 idField="itemId" dataField="items" allowCellSelect="true"
                url="${path}/shuntJumpAction.do?method=getTableData" showPager="fasle" 
            >

            <div property="columns"  headerStyle="font-Family:'微软雅黑',Serif;" > 
               	<div field="qufen_name" id="qufen_name" name="qufen_name" width="60" align="center" headerAlign="center" >区域分公司</div>
            	<div field="quyu_name" id="quyu_name" name="quyu_name" width="60" align="center" headerAlign="center" >营服中心</div> 
				<div header="" field="sp_name" id="sp_id" name="sp_name" width="40" align="center" headerAlign="center" >专业</div>     
				<div field="client_name" name="client_name" id="client_code" width="40" align="center" headerAlign="center" >客户</div> 
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
    </div>
        </div>
    </div>
<script type="text/javascript">
    
	    function onpreload(e){
	    		var a = $("#clients").find(".sui-grid-headerCell-inner");
		    		a.each(function(i){
		    			this.firstChild.data=i;
	    		});
	    }
	    function onload(e){
    		var a = $("#clients").find(".sui-grid-headerCell-inner");
    		a.each(function(i){
    			this.firstChild.data=i;
		});
}
    </script>
</body>
</html>
