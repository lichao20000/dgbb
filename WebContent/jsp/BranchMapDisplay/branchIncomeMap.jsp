    <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        <html xmlns="http://www.w3.org/1999/xhtml">
        <%@include file="../../taglib.jsp"  %>
        <script type="text/javascript" src="FusionCharts.js">
            </script> 
<!-- 
  - Author(s): Administrator
  - Date: 2013-12-24 08:15:07
  - Description:
-->
<head>
<title>Title</title>
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
</head>
<!-- 收入预算完成率分公司展示 -->
<body>
<!-- 
    <div style="background-image:url(../../images/u11_original.jpg);widh:100%;font-size:10px;padding-bottom:5px;">
    当前位置：仪表盘管理系统 > 分公司地图展示 
    </div>    
    <div style="background-image:url(../../images/u11_original.jpg);widh:100%">
      	<div style="background-image:url(../../images/u5_original.png);height:29px;widh:100%; background-repeat: no-repeat;">
    		<div class="tip_title">分公司地图展示</div>
    	</div>
    </div>
 -->
    <div class="condition">
    	<font style="padding-right:30px;">报表期间:&nbsp;<input class="sui-combobox"/></font>
        <font style="padding-right:30px;">专业:&nbsp;<input class="sui-combobox"/></font>
        <font style="padding-right:30px;">分公司:&nbsp;<input class="sui-combobox"/></font>
        <font style="padding-right:30px;">营销中心:&nbsp;<input class="sui-combobox"/></font>
    </div> 
	<div style="background-image:url(../../images/u290_original.png); background-repeat: no-repeat;width:100%;height:9px;pargin-top:0;margin-top:-5px;"></div>
    
    <div style="margin-left:1%;" id="Incomezhe">
    	<div style="padding-top:1%;background-image:url(../../images/u72_table.png); background-repeat: no-repeat;height:268px;" >
            <div style="padding-left:5px;">
            <!-- 
            <img alt="收入进度完成率块状图" src="../../images/u96_original.png" width="490px" height="230"px;> 
            -->
            	
            </div>
            <span style="margin-left:12px;" >
            <!--
            <img alt="收入进度完成率柱状图" src="../../images/u337_original.png" width="470px" height="230"px;> 
            -->
         
            
            </span>
        </div>
        <script type="text/javascript">
         	var myChart = new FusionCharts("Charts/Column3D.swf","myIncomezhe","600","500");
        	myChart.setDataURL('branchIncomeMapData.xml');
         	myChart.render("Incomezhe");
     	</script>
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
    </div>
<script type="text/javascript">
var _path = '${path}';
     var data="\
		<chart lowerLimit='0' upperLimit='100'  gaugeStartAngle='180' gaugeEndAngle='1' palette='1' numberSuffix='%' tickValueDistance='20' showValue='1' clickURL='campMapDisplay/campMapDisplay.jsp'>\
		  <colorRange>\
		    <color minValue='0' maxValue='4' code='FF0000'/>\
		    <color minValue='4' maxValue='10' code='00FF00'/>\
		  </colorRange>\
		  <dials>\
		    <dial value='5' rearExtension='1'/>\
		  </dials>\
		</chart>\
		";
	 var _swf = _path+"/swf/AngularGauge.swf";
		//new FusionCharts(swfUrl,id,width,height,debugMode(0/1),registerWithJS,bgColor,scaleMode);
	    var chart_FG_AvgQuan = new FusionCharts(_swf, "FG_AvgQuan", "400", "215", "0", "1");
	    // 按参数顺序：仪表盘swf、显示的区域div、显示宽度、显示高度、
	    //chart_FG_AvgQuan.setDataURL("?a=1");
	    chart_FG_AvgQuan.setDataXML(data);
	    chart_FG_AvgQuan.render("FG_AvgQuanDiv");
     </script>
</body>
</html>