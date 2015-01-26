<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../../taglib.jsp"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>销售经理曲线图</title>
	<script type="text/javascript" src="<%=basePath%>js/FusionCharts.js"></script>
	<style type="text/css"> 
	.tip_title{ color:#FFF;padding-left:1%;height:20px;padding-top:5px;vertical-align:middle;font:bold;font-size:16px;}
	.condition{margin-left:3%;margin-top:2%;}
	.image_title_selected{background-image:url(${path}/images/u97_original.png); background-repeat:no-repeat; width:121px; height:26px; vertical-align:middle; color:#FFF; text-align:center; padding-top:5px;font-size:18px;}
	.image_title{background-image:url(${path}/images/u93_original.png);background-repeat:no-repeat; width:121px; height:26px; vertical-align:middle; color:#000; text-align:center; padding-top:5px;font-size:18px;}
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
     
//通过传入的month设置联动的x轴，传入的address查询收入比传入y轴
 var myChart = new FusionCharts("${path}/swf/MSLine.swf","myChartId1", "500","260","0","1");
		function showAnchor(datas,month){
			var xml="";
			var data=<%=request.getAttribute("line")%>;
			var obj = eval(data);
			if(datas==null||datas==""){
				 xml = getData(obj);
				}else{
				xml = getData(datas);
					}
		   
	 //加载数据
    	 	myChart.setDataXML(xml);
     //折线图在哪里显示
    		myChart.render("saleManagerzhe");
    	}
    function getData(obj){
    	var xml="";
		var jsonxml_CategoryName="";
		var jsonxml_Value="";
		for(i=0;i<obj.length;i++){
			jsonxml_CategoryName+="<category name='"+obj[i].monthId+"' />";
			}
		for(i=0;i<obj.length;i++){
			jsonxml_Value+="<set value='"+obj[i].amountCurr+"' />";
			}
		xml = "<graph caption='' subcaption='' hovercapbg='FFECAA' hovercapborder='F47E00'  formatNumberScale='0' decimalPrecision='2' showvalues='0' numdivlines='5'  numVdivlines='0' yaxisminvalue='0' yaxismaxvalue='1'  rotateNames='0'><categories>"+
			jsonxml_CategoryName+"</categories><dataset seriesName='' color='1D8BD1' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'>"+
			jsonxml_Value+"</dataset></graph>";
		return xml;
    }
	    
	//3D饼图的显示	
	
	
	function showAnchor2(){
		var xml="";
		var jsonxml="";
		var data = <%=request.getAttribute("data") %>;
		var obj = eval(data); 
		
		//接受json并拼接成xml的<set/>
		for(i=0;i<obj.length;i++){
			jsonxml+="<set label='"+obj[i].districtBranchName+"' value='"+obj[i].amountCurr+"' isSliced='' link='javascript:changeFunsion("+i+")'/>"
		}
		//拼接xml
		//2d饼图拼接的格式："<chart caption='Pie Chart' numberPrefix='$'>"+
		//"<set value='25' label='Item B' link='javascript:changeFusion(123)'/>" +
	    //"<set value='17' label='Item A' />"+
	    //"<set value='44' label='Item c' />"+
		//"</chart>"
		xml="<chart caption='' palette='2' animation='1' formatNumberScale='0' numberPrefix='$' pieSliceDepth='30' startingAngle='125'>"+
		jsonxml+"</chart>";
		var myChart = new FusionCharts("${path}/swf/Pie3D.swf","myChartId2", "480","240","0","1");
			myChart.setDataXML(xml);
			myChart.render("saleManager2D");
		}
	//通过<set元素>的link得到值传入后台去解析分公司名称
	//ajax传给后台用于联动折线图
	function changeFunsion(label){
		
		//将label解析成label中的值传递给后台
		var data = <%=request.getAttribute("data")  %>
		var obj = eval(data); 
		var districtBranchCode= obj[label].districtBranchCode;
		var _path = "${path}";
		var _url = _path+"/companyMap.do?method=getOweRateCompanyBight&label="+districtBranchCode+"";
		$.ajax({
			type:"post",
			dataType:"json",
			url: _url,
			success:function(datas){
				showAnchor(datas);
			}
		});
		
		
		if(a == districtBranchCode){
			sum=1;
		}else{
			sum=0;
		}
		if(sum==1){
			window.location.href("http://www.baidu.com");
		}
		a=districtBranchCode;
	}
	 </script>
  </head>
  
  <body onload="showAnchor();showAnchor2()">
    <div style="background-image:url(${path}/images/u11_original.jpg);widh:100%;font-size:10px;padding-bottom:5px;">
    当前位置：仪表盘管理系统 > 销售经理地图展示 
    </div>    
    <div style="background-image:url(${path}/images/u11_original.jpg);widh:100%">
      	<div style="background-image:url(${path}/images/u5_original.png);height:29px;widh:100%; background-repeat: no-repeat;">
    		<div class="tip_title">销售经理地图展示</div>
    	</div>
    </div>
    <div class="condition">
    	<font style="padding-right:30px;">报表期间:&nbsp;<input class="sui-combobox"/></font>
        <font style="padding-right:30px;">专业:&nbsp;<input class="sui-combobox"/></font>
        <font style="padding-right:30px;">分公司:&nbsp;<input class="sui-combobox"/></font>
        <font style="padding-right:30px;">营销中心:&nbsp;<input class="sui-combobox"/></font>
    </div>
    
     <div style="background-image:url(../../images/u284_original.png); background-repeat: no-repeat;widh:100%;margin-top:1%;padding-left:1%;padding-top:10px;">
        <div class="image_title_selected" style="margin-bottom:-31px;">短期欠费率</div>
        <div class="image_title" style="margin-left:121px;margin-top:-28px;">长期欠费率</div>
        <div class="image_title" style="margin-left:242px;margin-top:-30px;">总体欠费率</div>
    </div>
	<div style="background-image:url(${path}/images/u290_original.png); background-repeat: no-repeat;width:100%;height:9px;pargin-top:0;margin-top:-5px;"></div>
    
    <div style="margin-left:1%;" >
    	<div style="margin-left:1%;" >
    	<div style="padding-top:1%;background-image:url(${path}/images/u72_table.png); background-repeat: no-repeat;height:268px;" >
            <span style="padding-left:5px;" id="saleManager2D"></span>
            <span style="margin-left:23px;margin-bottom:50px;" id="saleManagerzhe"></span>
       		 
        </div>
        </div>
       
    	<div style="background-image:url(${path}/images/u11_original.jpg);background-repeat: repeat-x;height:29px;widh:100%">
      	<div style="background-image:url(${path}/images/u5_original.png);widh:100%; background-repeat: no-repeat;">
    		<div class="clien_title">
            	客户群<a style="float:right;color:#000;font-size:9px;padding-bottom:0;" onclick="javascript:alert('')">隐藏<img src="${path}/images/hidden_button.png" style="margin-left:8px;margin-bottom:-3px;"></a>
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
