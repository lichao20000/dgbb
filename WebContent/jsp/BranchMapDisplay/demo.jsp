<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../../taglib.jsp"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>FusionChart曲线图</title>
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
			$(#Income).dblclick(funtion(){
				//取得突出的饼块的分公司名称
				
				//判断
				if()
				});
		);
   </script>
   
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
//通过传入的month设置联动的x轴，传入的address查询收入比传入y轴
		function showAnchor(month,address){
			var xml="<graph caption='' subcaption='' hovercapbg='FFECAA' hovercapborder='F47E00' formatNumberScale='0' decimalPrecision='0' showvalues='0' numdivlines='3' numVdivlines='0' yaxisminvalue='0' yaxismaxvalue='10'  rotateNames='0' labelPadding='0' chartRightMargin='30'   >"+
 			"<categories >"+
 			"<category name='Jan'  />"+
     		"<category name='Feb' />"+
 			"<category name='Mar' />"+
 			"<category name='Apr' />"+
 			"<category name='May' />"+
 			"<category name='Jun' />"+
 			"<category name='Jul' />"+
 			"<category name='Aug' />"+
 			"<category name='Seq' />"+
 			"<category name='Oct' />"+
 			"<category name='Nov' />"+
 			"<category name='Dec11111' />"+

 			"</categories>"+
 			"<dataset seriesName='' color='1D8BD1' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'>"+
 			"<set value='2' />"+
 			"<set value='3' />"+
 			"<set value='4' />"+
 			"<set value='5' />"+
 			"<set value='6' />"+
 			"<set value='7' />"+
 			"<set value='8' />"+
 			"<set value='9' />"+
 			"<set value='2' />"+
 			"<set value='5' />"+
 			"<set value='7' />"+
 			"<set value='5' />"+
 			"</dataset>"+

 			"</graph>" ;
 			//
    
    var myChart1 = new FusionCharts("../../swf/MSLine.swf","myChartId1", "600","240","0","1");
//加载数据
     myChart1.setDataXML(xml);
     //折线图在哪里显示
    myChart1.render("Incomezhe");
    }
		function showAnchor2(){
			var myChart = new FusionCharts("../../swf/Pie3D.swf","myChartId2", "470","240","0","1");
			 myChart.setDataURL("../../datas/pie3D.xml");
			 myChart.render("Income3D");
			}
	</script>
	
  </head>
  
  <body onload="showAnchor(1,'');showAnchor2()">
    <div style="background-image:url(../../images/u11_original.jpg);widh:100%;font-size:10px;padding-bottom:5px;">
    当前位置：仪表盘管理系统 > 分公司地图展示 
    </div>    
    <div style="background-image:url(../../images/u11_original.jpg);widh:100%">
      	<div style="background-image:url(../../images/u5_original.png);height:29px;widh:100%; background-repeat: no-repeat;">
    		<div class="tip_title">分公司地图展示</div>
    	</div>
    </div>
    <div class="condition">
    	<font style="padding-right:30px;">报表期间:&nbsp;<input class="sui-combobox"/></font>
        <font style="padding-right:30px;">专业:&nbsp;<input class="sui-combobox"/></font>
        <font style="padding-right:30px;">分公司:&nbsp;<input class="sui-combobox"/></font>
        <font style="padding-right:30px;">营销中心:&nbsp;<input class="sui-combobox"/></font>
    </div>
	<div style="background-image:url(../../images/u290_original.png); background-repeat: no-repeat;width:100%;height:9px;pargin-top:0;margin-top:-5px;"></div>
    
    <div style="margin-left:1%;" >
    	<div style="margin-left:1%;" >
    	<div style="padding-top:1%;background-image:url(../../images/u72_table.png); background-repeat: no-repeat;height:268px;" >
            <span style="padding-left:5px;" id="Income3D"></span>
            <span style="margin-left:10px;" id="Incomezhe"></span>
       		 
        </div>
        </div>
    	<div style="background-image:url(../../images/u11_original.jpg);background-repeat: repeat-x;height:29px;widh:100%">
      	<div style="background-image:url(../../images/u5_original.png);widh:100%; background-repeat: no-repeat;">
    		<div class="clien_title">
            	客户群<a style="float:right;color:#000;font-size:9px;padding-bottom:0;" onclick="javascript:alert('')">隐藏<img src="../../images/hidden_button.png" style="margin-left:8px;margin-bottom:-3px;"></a>
            </div>
    	</div>
       
         <div id="clients" class="sui-datagrid" style="width:79%"
                 idField="itemId" dataField="items" allowCellSelect="true"
                url="" showPager="fasle" 
            >
            <div property="columns"> 
                <div field="client_name" id="client_name" width="160" align="center" headerAlign="center" allowSort="true">客户群</div>      
                <div field="month_1" id="month_1" width="80" align="center" headerAlign="center" allowSort="true">6月</div>
                <div field="month_2" id="month_2" width="80" align="center" headerAlign="center"  allowSort="true">7月</div> 
                <div field="month_3" id="month_3" width="80" align="center" headerAlign="center"  allowSort="true">8月</div>
                <div field="month_4" id="month_4" width="80" align="center" headerAlign="center" allowSort="true" >9月</div>
                <div field="month_5" id="month_5" width="80" align="center" headerAlign="center"  allowSort="true">10月</div>
                <div field="month_6" id="month_6" width="80" align="center" headerAlign="center"  allowSort="true">11月</div> 
                <div field="month_7" id="month_7" width="80" align="center" headerAlign="center"  allowSort="true">12月</div>  
            </div>
         </div>
         
    </div>
</body>
</html>
