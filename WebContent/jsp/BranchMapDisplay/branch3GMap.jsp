    <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <%@include file="../../taglib.jsp"  %>
    <%@page import="java.util.Calendar"%>
<%@page import="com.dglt.comm.util.gson.JsonUtil"%>
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        <html xmlns="http://www.w3.org/1999/xhtml">
        <%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>
<%
	Calendar ca = Calendar.getInstance();
	int year = ca.get(Calendar.YEAR);//获取年份
	int month=ca.get(Calendar.MONTH)+1;//获取月份
	System.out.println(month);
    int monthId = 0;
	StringBuffer s = new StringBuffer();
	s.append("[");
	String d="";
	for(int i=0;i<12;i++){
		month--;
  	  if(month==0){
  		  year --;
  		  month=12;
  	  }
  	  monthId= year*100+month;
		d=(monthId+"").substring(0,4)+"年"+(monthId+"").substring(4)+"月";
		if(i==0){
			s.append("{id:'");
			s.append(monthId);
			s.append("',text:'");
			s.append(d);
			s.append("'}");
		}else{
			s.append(",{id:'");
			s.append(monthId);
			s.append("',text:'");
			s.append(d);
			s.append("'}");
		}
		monthId--;
	}
	s.append("]");
	request.setAttribute("date",s.toString());
%>
<!-- 
  - Author(s): Administrator
  - Date: 2013-12-24 08:15:07
  - Description:
-->
<head>


<title>Title</title>
<script type="text/javascript" src="<%=basePath%>js/FusionCharts.js"></script>
<style type="text/css"> 
	.tip_title{ color:#FFF;padding-left:1%;height:20px;padding-top:5px;vertical-align:middle;font:bold;font-size:16px;}
	.condition{margin-left:3%;margin-top:2%;}
	.image_title_selected{background-image:url(../../images/u97_original.png); background-repeat:no-repeat; width:121px; height:26px; vertical-align:middle; color:#FFF; text-align:center; padding-top:5px;font-size:18px;}
	.image_title{background-image:url(../../images/u93_original.png);background-repeat:no-repeat; width:121px; height:26px; vertical-align:middle; color:#000; text-align:center; padding-top:5px;font-size:18px;}
	.clien_title{height:20px;padding-top:5px;vertical-align:middle;color:#FFF;font:bold;padding-left:5px; font-size:18px}
</style>
<script type="text/javascript" >
   		$(document).ready(
			function(e){
				sui.parse();
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
    var productName = sui.get("Product").value ;
    var branch ='';
    var bizcs ='';
    var myType='2g';
    //查询数据 处理数据
     chargeParaMap(period, profess, branch, bizcs,productName,myType) ;
	// var branch =  mapDate[1].districtBranchCode;;
     chargeParaLine(period, profess, branch, bizcs,productName,myType) ;
     chargeParaTable(period, profess, branch, bizcs,productName,myType) ;
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
			jsonxml_CategoryName+="<category name='"+obj[i].monthId+"' />";
			}
		for(i=0;i<obj.length;i++){
			jsonxml_Value+="<set value='"+obj[i].amountCurr+"' />";
			}
		xml = "<graph caption='"+lineName+"' subcaption='' hovercapbg='FFECAA' hovercapborder='F47E00'  formatNumberScale='0' decimalPrecision='2' showvalues='0' numdivlines='5'  numVdivlines='0' yaxisminvalue='0' yaxismaxvalue='1'  rotateNames='0'><categories>"+
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
   function showAnchor3(datas,mapName,value1,Value2){
        var flexPath ="${path}/swf/FCMap_GD_Dongguan.swf" ;
        var date1=getMapDate(datas,mapName,value1,Value2) ;
       // alert(date1) ;
        var divId="2GMap"
        var charName="myChartId2"  
        insertFlexToDivs(flexPath,divId ,date1 ,charName) ;
	 }
	
    //地图XML组装
      function getMapDate (datas,mapName,value1,Value2){
    	  var xml="";
  		var jsonxml="";
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
        //xml头
          xml="<map caption ='"+mapName+"'  showAboutMenuItem='1' aboutMenuItemLabel='' aboutMenuItemLink=''  animation='1' "+
        		 " bgColor='FFFFFF'  bgAlpha='300,300'  bgRatio='0,100' bgAngle='0'  showShadow='1'  showBevel='1'  includeValueInLabels='1'  labelSepChar='{br}' "+
        		 "  numberSuffix='%' "+
        		 "  fillColor='F0FAFF' "+
        		 "  borderColor='330000'"+
        		 " baseFontSize='12'"+
        		 "  legendPosition='bottom'"+
        		 " useHoverColor='1'"+
        		 " borderColor='00324A'"+
        		 " showMarkerLabels='1'"+
        		 " showMarkerToolTip='1'"+
        		 " markerBorderColor='000000'"+
        		 " markerBgColor='FF5904'"+
        		 " markerRadius='6'"+
        		 " showCanvasBorder='1'"+
        		 " canvasBorderColor='f1f1f1'"+
        		 " canvasBorderThickness='2'>"+
	             "   <colorRange> "+
	             "  <color minValue='0' maxValue='"+value1+"' displayValue='正常' color='14EB1F' /> "+
	             "  <color minValue='"+value1+"' maxValue='"+Value2+"' displayValue='提醒' color='E8DF17' /> "+
	             "  <color minValue='"+Value2+"' maxValue='100' displayValue='预警' color='F41F34' /> "+
	             " </colorRange>" +
	        	 " <data>" + jsonxml+
	        	 " </data>"+
	             " </map>" 		 
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
	     var period = sui.get("period").value ;
         var profess = sui.get("profess").value ;
         var productName = sui.get("Product").value ;
		 var obj = eval(mapDate); 
		 var lineName=obj[label].districtBranchName;	
	     var branch =  obj[label].districtBranchCode;
         if(label==j){
               alert("下一面");
            }else {
		   j=label ;
		//将label解析成label中的值传递给后台
		
		 //alert(lineName);
		 var bizcs ='';
		 var myType='2g';
		 chargeParaLine(period, profess, branch, bizcs,productName,myType,lineName) ;
		 chargeParaTable(period, profess, branch, bizcs,productName,myType) ;	
		 //alert('11111');
		 setTimeout(function(){j=-1;},300)
		 
		 }
	}

	//曲线加载数据
     function  chargeParaLine(period, profess, branch, bizcs,productName,myType,lineName){	
    		var _path = "${path}";
    		var _url = _path+"/Trunover2g.do?method=turnover2GLine&branch="+branch+"&period="+period +"&profess="+profess +"&bizcs="+bizcs+"&productName="+productName+"&myType="+myType ;
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
	 function chargeParaMap(period, profess, branch, bizcs,productName,myType){
		 var _path = "${path}";
 		var _url = _path+"/Trunover2g.do?method=turnover2GMap&branch="+branch+"&period="+period +"&profess="+profess +"&bizcs="+bizcs+"&productName="+productName+"&myType="+myType  ;
 		$.ajax({
 			type:"post",
 			dataType:"json",
 			url: _url,
 			success:function(datas){
 				showAnchor3(datas,"2G流失率","7","9");
 				mapDate=datas ;
 			}
 		  });
	   }  
	 //表格加载数据
      function chargeParaTable(period, profess, branch, bizcs,productName,myType){
 		    var _path = "${path}";
 		    var grid = sui.get("clients");
 		    grid.load({ "period": period ,"profess": profess  ,"branch": branch ,"bizcs": bizcs,"productName":productName ,"myType":myType});
	   }
    </script>
</head>
<!-- 2G业务流失率分公司展示 -->
  <body onload="initPage()">
  <!-- 
    <div style="background-image:url(../../images/u11_original.jpg);widh:100%;font-size:10px;padding-bottom:5px;">
         当前位置：仪表盘管理系统 > 2G流失率 
    </div>    
    <div style="background-image:url(../../images/u11_original.jpg);widh:100%">
      	<div style="background-image:url(../../images/u5_original.png);height:29px;widh:100%; background-repeat: no-repeat;">
    		<div class="tip_title">分公司地图展示</div>
    	</div>
    </div>
     -->
    <div style="background-color:#0000; background-repeat: repeat-y;widh:100%;height:25px;margin-top:6px;"  >
        <font style="padding-right:30px;">报表期间:&nbsp;
    		<input id="period" name="period" class="sui-combobox" showNullItem="true" nullItemText="请选择..."
    		 data="${date}" textField="text" valueField="id" onvaluechanged="initPage()"/>
    	</font>
        <font style="padding-right:30px;">专业:&nbsp;<input id="profess" name="Specialty" showNullItem="true" nullItemText="全部（专业）"
         class="sui-combobox" url="${path}/dashBoard.do?method=specialty" textField="localCode" valueField="spCode" onvaluechanged="initPage()" /></font>
        <font style="padding-right:30px;">客户:&nbsp;<input id="Client" name="Client" showNullItem="true" nullItemText="全部（客户）" 
         class="sui-combobox" url="${path}/dashBoard.do?method=client" textField="clientName" valueField="clientCode" onvaluechanged="initPage()" /></font>
        <font style="padding-right:30px;">产品:&nbsp;<input width="150" id="Product" showNullItem="true" nullItemText="全部（产品）" 
         name="Product" class="sui-combobox" url="${path}/dashBoard.do?method=product" textField="productName" valueField="productCode"  onvaluechanged="initPage()"/></font>
    </div>

	<div style="background-image:url(../../images/u290_original.png);background-color:C11403; background-repeat: repeat-y;widh:100%;height:9px;margin-top:4px;"></div>
    <div style="margin-left:1%;widh:100% ;" >
    	<div style="padding-top:1%; widh:100% ;background-repeat: no-repeat;height:268px;" >
          <div id="2GMap" style="width:50%;height:268px;background-color:#FAFBFE;border:1px solid #000;float:left;"></div>
          <div   id="2GLine" style="width:49%;height:268px;background-color:#FAFBFE;border:1px solid #000;float:right;"></div>
         <!--  <span style="padding-left:5px;" id="2GMap"></span>-->
          <!--  <span style="margin-left:52px;" id="2GLine"></span> -->
        </div>
        <div style="background-color:#0000; background-repeat: repeat-y;widh:100%;height:9px;margin-top:4px;"></div>
    	<div style="background-image:url(../../images/u11_original.jpg);background-repeat: repeat-x;height:29px;widh:100%">
      	<div style="background-image:url(../../images/u5_original.png);widh:100%; background-repeat: no-repeat;">
    		<div class="clien_title">
            	客户群
              </div>
    	</div>
            <div id="clients" class="sui-datagrid" style="width:100%;height:200px"
                 idField="itemId" dataField="items" allowCellSelect="true"
                url="${path}/Trunover2g.do?method=turnover2GTable" showPager="fasle" 
            >
            <div property="columns"> 
                <div field="kpiName" id="kpiName" width="160" align="center" headerAlign="center" allowSort="true">客户群1</div>     
                <div field="spName" id="spName" width="160" align="center" headerAlign="center" allowSort="true">专业</div>      
                <div field="districtbranchName" id="districtbranchName" width="160" align="center" headerAlign="center" allowSort="true">分公司</div>      
                <div field="busiScName" id="client_name" width="160" align="center" headerAlign="center" allowSort="true">营业厅</div>         
                <div field="amountCurr" id="amountCurr" width="80" align="center" headerAlign="center" allowSort="true">流失率</div>
            </div>
    </div>
    </div>
</body>
</html>