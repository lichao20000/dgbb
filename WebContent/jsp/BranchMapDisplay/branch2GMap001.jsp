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
%>

        <%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		//期间
		String	period = (String)request.getAttribute("period");
		 System.out.print(period);
		//专业
		String	profess  = (String)request.getAttribute("profess");
		//客户
		String	client   = (String)request.getAttribute("client");
		//产品
		String	productCode  = (String)request.getAttribute("productCode");
		//指标
		String	kpid  = (String)request.getAttribute("kpid");
		//跳转类型
		String	myType  = (String)request.getAttribute("myType");
		//跳转类型
		String	mapName  = (String)request.getAttribute("mapName");
		//页面地图配置
		String	branch  = (String)request.getAttribute("branch");
		String	flexName  = (String)request.getAttribute("flexName");
		WKipConfigV wKipConfigV =(WKipConfigV)request.getAttribute("wKipConfigV");
        %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Title</title>
<script type="text/javascript" src="<%=basePath%>js/FusionCharts.js"></script>
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
				sui.get("Client").disable();
			}
		);
   </script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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

    //通过传入的month设置联动的x轴，传入的address查询收入比传入y轴
	//拼接字符串for循环用到的局部变量
    //通过传入的month设置联动的x轴，传入的address查询收入比传入y轴
	 function showAnchor(datas,lineName){
			var xml="";
			var obj = eval(datas);
			xml = getData(obj,lineName);	
			var myChart = getChartFromId("myChartId1");
			if(myChart==null){
				var myChart = new FusionCharts("${path}/swf/MSLine.swf","myChartId1", "100%","100%","0","1");
				myChart.setDataXML(xml);
				myChart.render("2GLine");
				}else{
				myChart.setDataXML(xml);
				}	
    	 }

    //	折线图拼数据。 
     function getData(obj,lineName){
          if(obj==null){
              return ;
              }
          if(lineName){
              }else {
            	  lineName='总和';
                  }
    	var xml="";
		var jsonxml_CategoryName="";
		var jsonxml_Value="";
		for(i=0;i<obj.length;i++){	
			if(i%2){
				jsonxml_CategoryName+="<category name='' />";
				jsonxml_Value+="<set value='"+obj[i].amountCurr+"' />";
			}else{
				jsonxml_CategoryName+="<category name='"+obj[i].monthId+"' />";
				jsonxml_Value+="<set value='"+obj[i].amountCurr+"' />";
				}	
		}
		
		xml = "<graph caption='"+lineName+"'  baseFont='微软雅黑' baseFontSize='12' outCnvBaseFont='微软雅黑' showPlotBorder='1'   showAlternateHGridColor='0' divLineColor='5E5750' divLineIsDashed='0' paletteColors='FFFFFF' subcaption='' hovercapbg='FFECAA' hovercapborder='F47E00'  formatNumberScale='0' decimalPrecision='2' showvalues='0' numdivlines='5'  numVdivlines='0' yaxisminvalue='0' yaxismaxvalue='1' canvasPadding='10' showBorder='1' rotateNames='0'  slantLabels='1' numberSuffix='%' bgColor='FFFFFF' borderColor='FFFFFF' canvasBorderColor='FFFFFF' ><categories>"+
			jsonxml_CategoryName+"</categories><dataset seriesName='' color='1D8BD1' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'>"+
			jsonxml_Value+"</dataset></graph>";
		return xml;
    }
	    
	//3D饼图的显示	
	
	
	function showAnchor2(datas ){
		var xml="";
		var jsonxml="";
		var data = datas;
		var obj = eval(data); 
		 if(obj==null){
             return xml;
             }
		//接受json并拼接成xml的<set/>
		for(i=0;i<obj.length;i++){
			jsonxml+="<set label='"+obj[i].districtBranchName+"' value='"+obj[i].amountCurr+"' isSliced='' link='javascript:changeFunsion("+i+")'/>"
		 }
		//拼接xml
		xml="<chart caption='2G流失率' palette='2' pieRadius='100' animation='1' formatNumberScale='0' numbersuffix='%' pieSliceDepth='30' startingAngle='125'  unescapeLinks='0'>"+
		jsonxml+"</chart>";
		var myChartMap = getChartFromId("myChartId2");
		if(myChartMap==null){
		    var myChartMap = new FusionCharts("${path}/swf/Pie3D.swf","myChartId2", "100%","100%","0","1");
		    myChartMap.setDataXML(xml);
		    myChartMap.render("2GMap");
			}else{
				myChartMap.setDataXML(xml);
			}	
		}

  //sh
   function showAnchor3(datas,mapName,minValue,value1,Value2,maxValue,sortType,isAlert){
        var flexPath ="${path}/swf/FCMap_GD_Dongguan.swf" ;
        var date1=getMapDate(datas,mapName,minValue,value1,Value2,maxValue,sortType,isAlert) ;
        var divId="2GMap"
        var charName="myChartId2"  
        	clearDiv(divId,charName);
        insertFlexToDivs(flexPath,divId ,date1 ,charName) ;
	 }
	
    //地图XML组装 
    //datas 数据  mapName 地图名 minValue最小值    value1 最分界值 1  Value2 分界值 2 max value  最大值   sortType 警示方向   isAlert是否有提示值
      function getMapDate (datas,mapName,minValue ,value1,Value2,maxValue ,sortType,isAlert){ 
        //  alert(maxValue);
    	  var xml="";
  		var jsonxml="";
  		//提醒值
  		var jjxml=""
  		var data = datas;
  		//alert(data);
  		var obj = eval(data); 
  		 if(obj==null){
               return xml;
          }
  		  //数据区
  		for(i=0;i<obj.length;i++){
			jsonxml+="<entity  id='"+obj[i].mapCode+"' value='"+obj[i].amountCurr+"'  link='javascript:changeFunsion("+i+")'/>" 
		}  

     if(isAlert=='N'){
    	 if (sortType=="POSITIVE"){
             jjxml=  "   <colorRange> "+	             
	             "  <color minValue='"+minValue+"' maxValue='"+value2+"' displayValue='正常' color='92D300' /> "+
	             "  <color minValue='"+Value2+"' maxValue='"+maxValue+"' displayValue='预警' color='EA0000' /> "+
	             " </colorRange>"
		   }else {
             jjxml=  "   <colorRange> "+	             
	             "  <color minValue='"+minValue+"' maxValue='"+value2+"' displayValue='预警' color='EA0000' /> "+
	             "  <color minValue='"+Value2+"' maxValue='"+maxValue+"' displayValue='正常' color='92D300' /> "+
	             " </colorRange>"
			   }	
         }else {
	   if (sortType=="POSITIVE"){
              jjxml=  "   <colorRange> "+	             
	             "  <color minValue='"+minValue+"' maxValue='"+Value2+"' displayValue='正常' color='92D300' /> "+
	             "  <color minValue='"+Value2+"' maxValue='"+value1+"' displayValue='提醒' color='FFC000' /> "+
	             "  <color minValue='"+value1+"' maxValue='"+maxValue+"' displayValue='预警' color='EA0000' /> "+
	             " </colorRange>"
		   
		   }else {
              jjxml=  "   <colorRange> "+	             
	             "  <color minValue='"+minValue+"' maxValue='"+Value2+"' displayValue='预警' color='EA0000' /> "+
	             "  <color minValue='"+Value2+"' maxValue='"+value1+"' displayValue='提醒' color='FFC000' /> "+
	             "  <color minValue='"+value1+"' maxValue='"+maxValue+"' displayValue='正常' color='92D300' /> "+
	             " </colorRange>"
		    }	

     } 
        //xml头
          xml="<map caption ='"+mapName+"' baseFont='微软雅黑' baseFontSize='12' outCnvBaseFont='微软雅黑'  showAboutMenuItem='1' aboutMenuItemLabel='' aboutMenuItemLink=''  animation='1' "+
        		 " bgColor='FFFFFF'  bgAlpha='300,300'  bgRatio='0,100' bgAngle='0'  showShadow='1'  showBevel='1'  includeValueInLabels='1'  labelSepChar='{br}' "+
        		 "  numberSuffix='%' "+
        		 "  fillColor='F0FAFF' "+
        		 "  borderColor='330000'"+
        		 " baseFontSize='12'"+
        		 "  legendPosition='right'"+
        		 "  legendBorderColor='FFFFFF' "+
        		 "  legendShadow='0' "+
        		 " useHoverColor='1'"+
        		 " borderColor='FFFFFF'"+
        		 " showMarkerLabels='1'"+
        		 " showMarkerToolTip='1'"+
        		 " markerBorderColor='FFFFFF'"+
        		 " markerBgColor='FFFFFF'"+
        		 " markerRadius='6'"+
        		 " showCanvasBorder='1'"+
        		 " canvasBorderColor='FFFFFF'"+
        		 " canvasBorderThickness='2'>"+
        		 jjxml +
	        	 " <data>" + jsonxml+
	        	 " </data>"+
	             " </map>" 	
	           // alert(jjxml); 	 
            return xml ;
      		}
		
         function  insertFlexToDivs(flexPath,divId ,data ,charName){
        	   var myChartMap = getChartFromId("myChartId2");
        	   if(myChartMap==null){
	             var myChartMap =new FusionCharts( flexPath , charName, "100%", "100%", "0" ) ;
	                   myChartMap.setDataXML(data) ;
	                   myChartMap.render(divId);
	        	   }else{
	        		   myChartMap.setDataXML(data);
	            	 }
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
        	  window.location.href="${path}/shuntJumpAction.do?method=getNextPage&branch="+branch+"&myType="+nextType+"&kpid="+kpid+"&period="+period +"&profess="+profess +"&bizcs="+bizcs+"&client="+client+"&productCode="+productCode;
                }
               }else {
		   j=label ;
		 var bizcs ='';
		 chargeParaLine(period, profess, branch, bizcs,productCode,myType,lineName,kpid,client) ;
		 chargeParaTable(period, profess, branch, bizcs,productCode,myType,kpid,client) ;	
		 setTimeout(function(){j=-1;},300)
		 }
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
    //设定页面条件值
  	function setValue(id,value){
  		if(value!=null&&value!=""&&value!="null"){
  			sui.get(id).setValue(value);
  			}
  		}

  	function clearDiv(name,chartId){
  		var myChart = getChartFromId(chartId);
  		if(myChart==null){
  			return;
  		}else{
  		FusionCharts(chartId).dispose();
  		document.getElementById(name).innerHTML = '';
  		}
  	}

 //通过期间I找到前12个月
  	function monthArry(period){
  		var year = 0;//
  		var month = 0;// 
  		var monthId = 0;
  			month = period % 100;
  			year = (period - month) / 100;
  		var arry= new Array(); 
  		for (var i = 0; i < 12; i++) {	
  			if (month == 0) {
  				year--;
  				month = 12;
  			}
  			monthId = year * 100 + month;
  			arry[i] = monthId;
  			month--;
  		}
  		return arry;
  	}
	   
    </script>
</head>
<!-- 2G业务流失率分公司展示 -->
  <body onload="initPage()">
   <div style="widh:100% ;padding-left:1px;padding-right:1px;" >
<div style="background-image:url(${path}/images/u11_original.jpg);widh:100%;font-size:14px;padding-bottom:5px;">
    当前位置：仪表盘管理系统 > 分公司展示
    </div>
    <div style="background-image:url(${path}/images/u11_original.jpg);widh:100%">
      	<div style="background-image:url(${path}/images/u5_original.png);height:29px;widh:100%; background-repeat: no-repeat;">
    		<div class="tip_title">分公司展示展示</div>
    	</div>
    </div>
    
    <div  class="condition"  >
        <font style="padding-right:30px;">报表期间:&nbsp;
    		<input id="period" name="period" url="${path}/dashBoard.do?method=month" class="sui-combobox"   value="<%=period%>"
    		 textField="text" valueField="id" onvaluechanged="selectChange"/>
    	</font>
        <font style="padding-right:30px;">专业:&nbsp;<input id="profess" name="profess" value="<%=profess%>"
         class="sui-combobox" url="${path}/dashBoard.do?method=specialty" textField="localCode" valueField="spCode" onvaluechanged="selectChange" /></font>
        <font style="padding-right:30px;">客户:&nbsp;<input id="Client" name="Client" showNullItem="true" nullItemText="请选择..."  value="<%=client%>"
         class="sui-combobox" url="${path}/dashBoard.do?method=client" textField="clientName" valueField="clientCode" onvaluechanged="selectChange" /></font>
        <font style="padding-right:30px;">产品类型:&nbsp;<input width="150" id="Product" showNullItem="true" nullItemText="请选择..."  value="<%=productCode%>"
         name="Product" class="sui-combobox" url="${path}/dashBoard.do?method=product" textField="productName" valueField="productCode"  onvaluechanged="selectChange"/></font>
    </div>
    
	<div style=" background-color:#C11403; background-repeat: no-repeat;width:100%;height:2px;margin-top:0.5%;margin-bottom:1px;"></div>
  
   <div style="widh:100% " >
    	<div style="padding-top:2px; widh:100% ;background-repeat: no-repeat;height:320px;" >
          <div  class="mapLeft"  id="2GMap" ></div>
          <div  class="mapRight"  id="2GLine" ></div>
        </div>
      
    	<div style=" background-color:#FFFFFF; background-repeat: no-repeat;width:100%;height:1px;margin-top:0.5%;"></div>
    	
            <div id="clients" class="sui-datagrid" style="width:100%;height:200px"
                 idField="itemId" dataField="items" allowCellSelect="true"
                url="${path}/shuntJumpAction.do?method=getTableData" showPager="fasle" 
             >
                   <div property="columns"> 
<div field="sp_name" id="sp_id" width="60" align="left" headerAlign="center" allowSort="true">专业</div>     
                <div field="product_name" id="product_code" width="80" align="left" headerAlign="center" allowSort="true" visible="false" >产品 </div>      
                <div field="client_name" id="client_code" width="40" align="left" headerAlign="center" allowSort="true">客户</div>      
                <div field="kpi_name" id="kpi_id" width="80" align="left" headerAlign="center" allowSort="true">指标</div>         
                <div field="qufen_name" id="qufen_id" width="80" align="left" headerAlign="center" allowSort="true">分公司</div>
                <div field="quyu_name" id="quyu_id" width="120" align="left" headerAlign="center" allowSort="true">营业厅</div>     
                <div name="month01" field="month01" id="month01" width="40" align="center" headerAlign="center" allowSort="true"></div>      
                <div name="month02" field="month02" id="month02" width="40" align="center" headerAlign="center" allowSort="true"></div>      
                <div name="month03" field="month03" id="month03" width="40" align="center" headerAlign="center" allowSort="true"></div>         
                <div name="month04" field="month04" id="month04" width="40" align="center" headerAlign="center" allowSort="true"></div>
                <div name="month05" field="month05" id="month05" width="40" align="center" headerAlign="center" allowSort="true"></div>     
                <div name="month06" field="month06" id="month06" width="40" align="center" headerAlign="center" allowSort="true"></div>      
                <div name="month07" field="month07" id="month07" width="40" align="center" headerAlign="center" allowSort="true"></div>      
                <div name="month08" field="month08" id="month08" width="40" align="center" headerAlign="center" allowSort="true"></div>         
                <div name="month09" field="month09" id="month09" width="40" align="center" headerAlign="center" allowSort="true"></div>
                <div name="month010" field="month010" id="month010" width="40" align="center" headerAlign="center" allowSort="true"></div>      
                <div name="month011" field="month011" id="month011" width="40" align="center" headerAlign="center" allowSort="true"></div>         
                <div name="month012" field="month012" id="month012" width="40" align="center" headerAlign="center" allowSort="true"></div>
            </div>
           </div>
       
     </div>
</body>
</html>