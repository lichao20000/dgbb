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
		<title>报表管理</title>
		<link href="${path}/css/dash-style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}/js/FusionCharts.js"></script>
	</head>
<body onload="onInit()" >
<div style="background-image:url(${path}/images/u11_original.jpg);widh:100%;font-size:14px;padding-bottom:5px;">
    当前位置：仪表盘管理系统 > 仪表盘首页展示
    </div>
    <div style="background-image:url(${path}/images/u11_original.jpg);widh:100%">
      	<div style="background-image:url(${path}/images/u5_original.png);height:29px;widh:100%; background-repeat: no-repeat;">
    		<div class="tip_title">仪表盘首页展示</div>
    	</div>
    </div>
    <div class="condition">
    	<font style="padding-right:30px;">报表期间:&nbsp;
    		<input id="Formperid" name="Formperid" onvaluechanged="valueChanged" class="sui-combobox"   url="${path}/dashBoard.do?method=month" textField="text" valueField="id"/>
    	</font>
        <font style="padding-right:30px;">专业:&nbsp;<input onvaluechanged="valueChanged" id="Specialty" name="Specialty" value="" class="sui-combobox" url="${path}/dashBoard.do?method=specialty" textField="localCode" valueField="spCode" /></font>
        <font style="padding-right:30px;">客户:&nbsp;<input onvaluechanged="valueChanged" id="Client" name="Client" class="sui-combobox" url="${path}/dashBoard.do?method=client" textField="clientName" valueField="clientCode" /></font>
        <font style="padding-right:30px;">产品类型:&nbsp;<input onvaluechanged="valueChanged" width="150" id="Product" name="Product" class="sui-combobox" url="${path}/dashBoard.do?method=product" textField="productName" valueField="productCode" /></font>
    </div>
	<div style="background-color:#C11403; background-repeat: no-repeat;width:100%;height:3px;margin-top:0.5%;margin-bottom:5px;"></div>
	
    <div style="width:100%;">
      <table style="width:100%;">
      	<tr style="width:100%;">
					<td style="width:50%;">
					  <table style="width:100%;">
					   <tr style="width:100%;">
					   		<td style="width:100%;" colspan="2">
					   		<div style="width:100%;">
					   			<h3 class="chartsTitle"></h3>
								<input class="bates"  type="hidden" value=""/>
								<input class="amountcurr"  type="hidden" value=""/>
							</div>
					   		</td>
					   </tr>
					   <tr style="width:100%;">
					   		<td style="width:100%;" colspan="2">
					   		<div style="width:100%;">
					   		<div style="width:46%;height:200px;background-color:#FAFBFE;float:left;">
					   			<p class="_title" style="width:100%;text-align: center;">2013年八月</p>
								<div id="chartContainer1" style="height: 100%;vertical-align: middle;padding-bottom: 5px;float: center; background:url(${path}/images/loaderc.gif) no-repeat center;" class="chart_angularGauge"></div>
							</div>
							<div id="chartContainer2" style="width:46%;height:200px;background-color:#FAFBFE;float:right;background:url(${path}/images/loaderc.gif) no-repeat center;" class="chart_column"></div>
							</div>
					   		</td>
					   </tr>
					   <tr style="width:100%;">
					   		<td style="width:50%;">
					   			<div  class="sdbj">
									<input  type="image" class="alertButton" src="${path}/images/alertImage.png" onclick="alertFunction(2)"></input>
								</div>
					   		</td>
					   		<td style="width:50%;">
					   			<div class="fbpz">
									<input  type="image" class="label" src="${path}/images/u407_original.png" onclick="setLabelTarget(2)"></input>
									<input type="image" class="kpid" src="${path}/images/u399_original.png" onclick="setRenderTarget(2)"></input>
								</div> 
					   		</td>
					   </tr>
						</table>
					</td>
      		<td  class="tdcss">
						<div style="width:100%;height:251px;">
							<h3 class="chartsTitle"></h3>
							<input class="bates"  type="hidden" value=""/>
							<input class="amountcurr"  type="hidden" value=""/>
							<div style="width:100%;">
								<div class="div_left">
								<p class="_title">2013年八月</p>
								<div id="chartContainer3" class="chart_angularGauge"></div>
								<div  class="sdbj">
									<input  type="image" class="alertButton" src="${path}/images/alertImage.png" onclick="alertFunction(2)"></input>
								</div>
							</div>
							<div class="div_right">
								<div id="chartContainer4" class="chart_column"></div>
								<div class="fbpz">
									<input  type="image" class="label" src="${path}/images/u407_original.png" onclick="setLabelTarget(2)"></input>
									<input type="image" class="kpid" src="${path}/images/u399_original.png" onclick="setRenderTarget(2)"></input>
								</div>      
								</div>
							</div>
						</div>
      		</td>
      	</tr>
      	<tr>
      		<td class="tdcss">
						<div style="width:100%;height:251px;">
							<h3 class="chartsTitle"></h3>
							<input class="bates"  type="hidden" value=""/>
							<input class="amountcurr"  type="hidden" value=""/>
							<div style="width:100%;">
								<div class="div_left">
								<p class="_title">2013年八月</p>
								<div id="chartContainer5" class="chart_angularGauge"></div>
								<div  class="sdbj" >
									<input  type="image" class="alertButton" src="${path}/images/alertImage.png" onclick="alertFunction(3)"></input>
								</div>
							</div>
							<div class="div_right">
								<div id="chartContainer6" class="chart_column"></div>
									<div class="fbpz">
										<input  type="image" class="label" src="${path}/images/u407_original.png" onclick="setLabelTarget(3)"></input>
										<input type="image" class="kpid" src="${path}/images/u399_original.png" onclick="setRenderTarget(3)"></input>
									</div>
								</div>
							</div>
						</div>
					</td>
      		<td  class="tdcss">
						<div style="width:100%;height:251px;">
							<h3 class="chartsTitle"></h3>
							<input class="bates"  type="hidden" value=""/>
							<input class="amountcurr"  type="hidden" value=""/>
							<div style="width:100%;">
								<div class="div_left">
								<p class="_title">2013年八月</p>
								<div id="chartContainer7" class="chart_angularGauge"></div>
								<div  class="sdbj">
									<input  type="image" class="alertButton" src="${path}/images/alertImage.png" onclick="alertFunction(4)"></input>
								</div>
							</div>
							<div class="div_right">
								<div id="chartContainer8" name="chartContainer8" class="chart_column"></div>
								<div class="fbpz">
									<input  type="image" class="label" src="${path}/images/u407_original.png" onclick="setLabelTarget(4)"></input>
									<input type="image" class="kpid" src="${path}/images/u399_original.png" onclick="setRenderTarget(4)"></input>
								</div>      
								</div>
							</div>
						</div>
      		</td>
      	</tr>
      </table>
    </div>
			
</body>
 <script type="text/javascript">
 sui.parse();
 var requestArray;
 function getArray(){
	 if(requestArray==null){
		 return;
	 }else{
		 return requestArray["kpiId1"]+","+requestArray["kpiId2"]+","+requestArray["kpiId3"]+","+requestArray["kpiId4"];
		 //var o = new Object();
		 //o={kpiId1:requestArray["kpiId1"],kpiId2:requestArray["kpiId2"],kpiId3:requestArray["kpiId3"],
		  //  kpiId4:requestArray["kpiId4"],kpiName1:requestArray["kpiName1"],kpiName2:requestArray["kpiName2"],
		    //  kpiName3:requestArray["kpiName3"],kpiName4:requestArray["kpiName4"],kpi1ChartType:requestArray["kpi1ChartType"],
		      //  kpi2ChartType:requestArray["kpi2ChartType"],kpi3ChartType:requestArray["kpi3ChartType"],kpi4ChartType:requestArray["kpi4ChartType"]}
	 }
}
var kpidArrayString="20006,20007,20008,20009";
var labelTarget = 0;
//var num = 0;//生成FusionCharts次数
var renderTarget=0;//选择指标时用来区别改变哪个部分指标被改变
var noDataTitle="";//选择没有数据的指标之后使用该字段更新指标标题
function valueChanged(e){
	$(".chart_column").html("");
	$(".chart_angularGauge").html("");
	if(e.sender.id=="Specialty"){//产品和专业联动
		sui.get("Product").load("${path}/dashBoard.do?method=product&specialty="+e.value);
	}
	sui.get("Product").value="";
	loadData(kpidArrayString);
}
//设置选择指标后改变哪部分的图表
function setRenderTarget(number){
	renderTarget=number;
}
function setLabelTarget(number){
	labelTarget = number;
}
function alertFunction(number){
	var result = window.confirm('你确定要发送邮件吗？');
	if(!result){
		return;
	}
	var monthDesc = ""+sui.get("Formperid").text;//获取当前选中的月份text
	var spName = ""+sui.get("Specialty").text;//获取当前选中的专业text
	var clientName = ""+sui.get("Client").text;//获取当前选中的客户text
	var productName  = ""+sui.get("Product").text;//获取当前选中的产品text
	var kpiId = $(".bates")[number-1].value;
	var amountcurr = $(".amountcurr")[number-1].value;
	var kpiName = $("h3.chartsTitle")[number-1].innerHTML;
	if(!kpiId || !amountcurr || !kpiName){
		alert("获取指标数据出错");
		return;
	}
	var _url="${path}/dashBoard.do?method=sendMail";
	$.ajax({
		type: "post",
		url: _url,
		data:{monthDesc:monthDesc,spName:spName,clientName:clientName,productName:productName,kpiId:kpiId,kpiName:kpiName,amountcurr:amountcurr},
		dataType:'json',
		success: function(result)
		{
			if(result.states!="SUCCESS"){
				alert(result.states);
			}
		}
	});
}
function upDateKpiId(index,kpiId,kpiName){
	var _url="${path}/dashBoard.do?method=upDateIndiv";
	$.ajax({
		type: "post",
		url: _url,
		data:{index:index,kpiId:kpiId,kpiName:kpiName},
		dataType:'json',
		success: function(result)
		{
			if(result!="success"){
				alert(result);
			}
		}
	});
}
function loadFun(){
	
}
//选择指标页面返回方法
function desFun(e){
	var data = sui.clone(e);
	if(data.kpiId==""){
		return;
	}else if(data.kpiId==undefined){
		return;
	}
	var arr = kpidArrayString.split(",");
	if(renderTarget-1>=0){
		arr[renderTarget-1] = data.kpiId ;
		$(".chart_column")[renderTarget-1].innerHTML = "";
		$(".chart_angularGauge")[renderTarget-1].innerHTML = "";
	}
	noDataTitle = data.kpiName;
	kpidArrayString = arr.toString();
	upDateKpiId(renderTarget-1,data.kpiId,data.kpiName);//更新个性化指标
	loadData(data.kpiId);
}
function onInit(){
	for(var i=1;i<9;i++){
		//sui.get("chartContainer8").loading("加载中....");
	}
	var obj = sui.get("Formperid");
	var nowDate = obj.data[0].id;
	obj.setValue(nowDate);
	var _title = $("p._title");
	_title.each(function(i){
		this.innerHTML=obj.getText();//设置仪表盘上面的期间
	});
	//绑定选择指标按钮点击事件
	$(".kpid").bind("click",function(){
		sui.open({
		    url: "${path}/jsp/kpiId.jsp",
		    title: "选择性指标", width: 600,height: 400,
		    showCloseButton:true,
		    onload: loadFun,
		    ondestroy: desFun
			});
	});
	
	function labelOnLoad(){
		
		//userId可以直接使用，全局变量有定义
	}
	//绑定打开标注页面
	$(".label").bind("click",function(){
		var monthId = ""+sui.get("Formperid").value;//获取当前选中的月份value
		var monthName = ""+sui.get("Formperid").text;//获取当前选中的月份text
		var spcode  = ""+sui.get("Specialty").value;//获取当前选中的专业value
		var spname = ""+sui.get("Specialty").text;//获取当前选中的专业text
		var clientCode = ""+sui.get("Client").value;//获取当前选中的客户value
		var clientName = ""+sui.get("Client").text;//获取当前选中的客户text
		var productCode  = ""+sui.get("Product").value;//获取当前选中的产品value
		var productName  = ""+sui.get("Product").text;//获取当前选中的产品text
		var kpiId = $(".bates")[labelTarget-1].value;
		var kpiName = $("h3.chartsTitle")[labelTarget-1].innerHTML;
		 var _url="${path}/jsp/tagging/tagIndex.jsp?monthId="+monthId+
		       "&monthName="+monthName+ 
		       "&spcode="+spcode+ 
		       "&spname="+spname+ 
		       "&clientCode="+clientCode+ 
		       "&clientName="+clientName+ 
		       "&productCode="+productCode+ 
		       "&productName="+productName+ 
		       "&kpiId="+kpiId+ 
		       "&kpiName="+kpiName		       
		       ;
		 _url= encodeURI(encodeURI(_url)); 
	      // alert(_url);
		sui.open({
		    url: _url,
		    title: "编辑", width: 750,height: 400,
		    showCloseButton:true,
		    onload: labelOnLoad
			});
	});
	loadData(kpidArrayString);
}
//没有数据时加载的图表
function getNoDataChart(target){
	if(target%2==1){
		setFlexT0posi("chartContainer"+target,"AngularGauge","<xml></xml>");
	}else{
		setFlexT0posi("chartContainer"+target,"MSColumn2D","<xml></xml>");
	}
}
//渲染仪表盘
function renderDashBoard(data,t,renderSize){
	var flag;
	var bates = $(".bates");
	var minValue;
	var maxValue;
	var value;
	var alertValue;
	var warningValue;
	flag=true;
	//if(bates[t].value==data[0].rowId){
  		flag=false;
  		alertValue = Number(data[0].alertValue);
  		warningValue = Number(data[0].warningValue);
  		value = Number(data[0].chartLabelValue);
		if(alertValue>warningValue  && alertValue!=0 && warningValue!=0){
   			minValue = warningValue-Math.abs(alertValue-warningValue);
   			maxValue = alertValue+Math.abs(alertValue-warningValue);
   			if(value<minValue){
   				minValue = value*0.8;
   			}else if(value>maxValue){
   				maxValue = value*1.2;
   			}
			value = getAngularGaugeData(data[0].rowId,(value*100).toFixed(2),(alertValue*100).toFixed(2),(warningValue*100).toFixed(2),(minValue*100).toFixed(2),(maxValue*100).toFixed(2));
       	} else if(alertValue<warningValue  && alertValue!=0 && warningValue!=0){
       		minValue = alertValue-Math.abs(alertValue-warningValue);
   			maxValue = warningValue+Math.abs(alertValue-warningValue);
   			if(value<minValue){
   				minValue = value*0.8;
   			}else if(value>maxValue){
   				maxValue = value*1.2;
   			}
			value = getAngularGaugeData(data[0].rowId,(value*100).toFixed(2),(alertValue*100).toFixed(2),(warningValue*100).toFixed(2),(minValue*100).toFixed(2),(maxValue*100).toFixed(2));
       	}else{
       		minValue = warningValue*0.5;
   			maxValue = warningValue*1.5;
   			if(value<minValue){
   				minValue = value*0.8;
   			}else if(value>maxValue){
   				maxValue = value*1.2;
   			}
			value = getAngularGaugeData1_1(data[0].rowId,(value*100).toFixed(2),(warningValue*100).toFixed(2),(minValue*100).toFixed(2),(maxValue*100).toFixed(2));
       	}
  		if(renderSize==1){
			setFlexT0posi("chartContainer"+(renderTarget*2-1),"AngularGauge",value);
		}else{
			setFlexT0posi("chartContainer"+((t+1)*2-1),"AngularGauge",value);
		}
      		
    //}
}
/*根据KPID加载图表数据
 * 	kpidArrayString：一个或多个指标的字符串，用逗号分隔
 */
function loadData(kpidString){
	var _path = "${path}";
	var monthId = sui.get("Formperid").value;//获取当前选中的月份值
	var specialty = sui.get("Specialty").value;//获取当前选中的专业
	var client = sui.get("Client").value;//获取当前选中的客户
	var product = sui.get("Product").value;//获取当前选中的产品
	var _url = _path+"/dashBoard.do?method=index";
	var data;
	if(kpidString==""){
		data = {monthId:monthId,specialty:specialty,client:client,product:product};
	}else{
		data = {kpidString:kpidString,monthId:monthId,specialty:specialty,client:client,product:product};
	}
	//获取数据
	$.ajax({
		type: "post",
		url: _url,
		data:data,
		dataType:"json",
		success: function(data)
		{
			requestArray = data["kapidArray"][0];//从后台获取个性化指标
			kpidArrayString = getArray();
			var kpidArray;
			if(kpidString==""){
				kpidArray = kpidArrayString.split(",");
			}else{
				kpidArray = kpidString.split(",");
			}
			var chartType="";
			var chartsTitles = $("h3.chartsTitle");
			var bates = $(".bates");
			var amountcurr = $(".amountcurr");
			var _title = $("._title");
			if(kpidArray.length==0) return;//没有查到数据时直接返回
			var draw_num=0;
			for(var i=0;i<kpidArray.length;i++){
				if(null==data[kpidArray[i]] || 0==data[kpidArray[i]].length){//查询出来没有数据调用
					if(kpidArray.length>1){
						_title[i].innerHTML = sui.get("Formperid").text;
						if(renderTarget==0){//没点击+下拉 或者 页面刷新的情况使用后台传过来个性化的指标名称
							chartsTitles[i].innerHTML = requestArray[("kpiName"+(i+1))];
						}else if(i+1==renderTarget){//点击+下拉 使用子页面返回的指标名称(只是用选择过一个指标)
							chartsTitles[i].innerHTML =noDataTitle;
						}else{//点击+下拉 （没有没点击的页面）(只是用选择过一个指标)
							chartsTitles[i].innerHTML = requestArray[("kpiName"+(i+1))];
						}
						getNoDataChart((i+1)*2);
					}else if(kpidArray.length==1 && renderTarget!=0){//
						_title[renderTarget-1].innerHTML = sui.get("Formperid").text;
						chartsTitles[renderTarget-1].innerHTML = noDataTitle;
						getNoDataChart(renderTarget*2);
					}
					//var a = renderTarget==0?(i+1):renderTarget;
					//getNoDataChart(a*2);
					//$(".chart_column")[i].innerHTML = "<h3>没有找到相关数据</h3>";
					continue;
				}
				var d = data[kpidArray[i]];
				var value = new Object();
				var AxisName;
				if(i%2==1){
					chartType="MSBar2D";
					AxisName ="caption=";
				}else{
					chartType="MSColumn2D";
					AxisName = "caption="
				}
				var _data = getMSColumnData(d,AxisName);
				
				if(kpidArray.length>1){//查询的数据不为空
					//改变指标标题、月份等信息
					_title[i].innerHTML = sui.get("Formperid").text;
					chartsTitles[i].innerHTML = data[kpidArray[i]][0].kpiName;
					//bates[i].value=kpidArray[i];
					setFlexT0posi("chartContainer"+(i+1)*2,chartType,_data);
				}else if(kpidArray.length==1){
					//bates[renderTarget-1].value=kpidArray[i];
					_title[renderTarget-1].innerHTML = sui.get("Formperid").text;
					chartsTitles[renderTarget-1].innerHTML = data[kpidArray[i]][0].kpiName;
					setFlexT0posi("chartContainer"+renderTarget*2,chartType,_data);
				}
				
				draw_num++;
			}
			for(var i=0;i<kpidArray.length;i++){//加载仪表盘
				if(data[kpidArray[i]] && kpidArray.length==1){
					bates[i].value = data[("dashDoard"+kpidArray[i])][0].rowId;
					//chartsTitles[i+1].innerHTML = data[kpidArray[i]][0].kpiName;
					amountcurr[i].value = data[("dashDoard"+kpidArray[i])][0].chartLabelValue;
					renderDashBoard(data[("dashDoard"+kpidArray[i])],i,kpidArray.length);
					continue;
				}
				
				if(null==data[kpidArray[i]] || data[("dashDoard"+kpidArray[i])].length==0) {
					var a = renderTarget==0?(i+1):renderTarget;
					getNoDataChart(a*2-1);
					//$(".chart_angularGauge")[i].innerHTML = "<h3 >没有找到相关数据</h3>";
					continue;
				}
				bates[i].value = data[("dashDoard"+kpidArray[i])][0].rowId;
				amountcurr[i].value = data[("dashDoard"+kpidArray[i])][0].chartLabelValue;
				renderDashBoard(data[("dashDoard"+kpidArray[i])],i,kpidArray.length);
			}
		}		
	
	});
	
}
//获取仪表盘下钻页面URL
function getBranchUrl(kpid){
	var url = "${path}/shuntJumpAction.do?method=getNextPage";
	var monthId = ""+sui.get("Formperid").value;//获取当前选中的月份值
	var specialty = ""+sui.get("Specialty").value;//获取当前选中的专业
	var client = ""+sui.get("Client").value;//获取当前选中的客户
	var product = ""+sui.get("Product").value;//获取当前选中的产品
	var q_url = url+"&kpid="+kpid+"&period="+monthId+"&profess="+specialty+"&client="+client+"&productCode="+product+"&myType=branch"
	window.location.href = q_url;
	//return q_url;
}
//获取2色仪表盘数据
function getAngularGaugeData1_1(kpid,value,warningValue,minValue,maxValue){
	var color1="8BBA00";
	var color2="FF654F";
	//var url = getBranchUrl(kpid);
	var data="<chart showTickValues='1' lowerLimit='"+minValue+"' upperLimit='"+maxValue+"' gaugeStartAngle='180' gaugeEndAngle='0' gaugeFillMix='' "
			 	+"pivotFillMix='{F0EFEA}, {BEBCB0}' pivotBorderColor='BEBCB0'  showBorder='0' bgColor='FFFFFF' manageResize='1'  gaugeInnerRadius='70%'"
			 		+" chartLeftMargin='3' chartRightMargin='3' chartBottomMargin='40' placeValuesInside='1'  palette='1' numberSuffix='%' tickValueDistance='13'  showValue='0' clickURL='javascript:getBranchUrl("+kpid+")'>"
		   +"<colorRange><color minValue='"+minValue+"' maxValue='"+warningValue+"' code='"+color1+"'/><color minValue='"+warningValue+"' maxValue='"+maxValue+"' code='"+color2+"'/>"
		   +"</colorRange><dials><dial value='"+value+"' baseWidth='8' radius='75' borderThickness='1' borderAlpha='0' /></dials><trendpoints><point radius='105' innerRadius='80' valueInside='0' startValue='"+value+"' displayValue='"+parseFloat(value)+"%' color='FFFFFF' thickness='1' alpha='100'/><point valueInside='1' startValue='"+warningValue+"' displayValue='"+parseFloat(warningValue)+"%' color='"+color1+"' thickness='1' alpha='100'/></trendpoints></chart>"; 
	return data;
}
//获取三色仪表盘数据
 function getAngularGaugeData(kpid,value,alertValue,warningValue,minValue,maxValue){
	 var value1="";
	 var value2="";
	 var value3="";
	 var tickValue;
	 var color1="";//红
	 var color2="";//黄
	 var color3="";//绿
	 if(Number(alertValue)<Number(warningValue)){
		 value1=alertValue;
		 value2=warningValue;
		 tickValue=warningValue-alertValue;
		 color1="8BBA00";//绿
		 color2="F6BD0F";//黄
		 color3="FF654F";//红
	 }else{
		 value1=warningValue;
		 value2=alertValue;
		 tickValue=alertValue-warningValue;
		 color1="FF654F";//红
		 color2="F6BD0F";//黄
		 color3="8BBA00";//绿
	 }
	 var data="<chart showTickValues='1' lowerLimit='"+minValue+"' upperLimit='"+maxValue+"'  gaugeStartAngle='180' gaugeEndAngle='0' gaugeFillMix='' "
			 	+"pivotFillMix='{F0EFEA}, {BEBCB0}' pivotBorderColor='BEBCB0'  showBorder='0' bgColor='FFFFFF' manageResize='1'  gaugeInnerRadius='70%'"
			 		+" chartLeftMargin='3' chartRightMargin='3' chartBottomMargin='40' placeValuesInside='1'  palette='1' numberSuffix='%' tickValueDistance='13'  showValue='0' clickURL='javascript:getBranchUrl("+kpid+")'>"
		   +"<colorRange><color minValue='"+minValue+"' maxValue='"+value1+"' code='"+color1+"'/><color minValue='"+value1+"' maxValue='"+value2+"' code='"+color2+"'/>"
		      +"<color minValue='"+value2+"' maxValue='"+maxValue+"' code='"+color3+"'/></colorRange><dials><dial value='"+value+"' baseWidth='8' radius='75' borderThickness='1' borderAlpha='0'/>"
		   +"</dials><trendpoints><point radius='105' innerRadius='80' valueInside='0' startValue='"+value+"' displayValue='"+parseFloat(value)+"%' color='FFFFFF'  thickness='1' alpha='100'/><point valueInside='1' startValue='"+value1+"' displayValue='"+parseFloat(value1)+"%' color='"+color1+"' thickness='1' alpha='100'/><point valueInside='1' startValue='"+value2+"' displayValue='"+parseFloat(value2)+"%' color='"+color2+"' thickness='1' alpha='100'/></trendpoints></chart>"; 
	return data;
}


//获取柱状图数据
function getMSColumnData(value,AxisName){
	var AxisName = AxisName;
	var maxValue = parseInt(value[0].chartLabelValue*120);
	maxValue = maxValue+(2-maxValue%2);
	var colorArray = ["99FFCC","99FF33","99CCFF","99CC99","99CC00"];
	var categories ="<categories>";
	var dataset="<dataset>";
	var chartLabelName;
	var col;
	for(var i=0;null!=value[i];i++){
		chartLabelName = value[i].chartLabelName;
		if(chartLabelName =="东莞"){
			col = "e44a00";
			if(i==0) AxisName+="'四龙对比图'";
		}else if(chartLabelName =="佛山"){
			col = "f8bd19";
			if(i==0) AxisName+="'四龙对比图'";
		}else if(chartLabelName =="广州"){
			col = "008ee4";
			if(i==0) AxisName+="'四龙对比图'";
		}else if(chartLabelName =="深圳"){
			col = "6baa01";
			if(i==0) AxisName+="'四龙对比图'";
		}else if(chartLabelName.length>3){
			chartLabelName = chartLabelName.substring(0,chartLabelName.length-3);
			col = colorArray[i>5?5:i];
			if(i==0) AxisName+="'区域分公司对比图'";
		}
		categories +="<category Label='"+chartLabelName+"'/>";
		dataset+="<set   value='"+Math.round(value[i].chartLabelValue*100)+"' color='"+col+"'/> ";
	}
	categories +="</categories>";
	dataset+="</dataset>";
	var data2 ="<chart baseFont='微软雅黑' alternateVGridColor='FFFFFF' numberSuffix='%' "+AxisName+" rotateLabels='0' showPlotBorder='0' divLineColor='999999' showAlternateHGridColor='0' canvasBorderColor='FFFFFF' canvasBorderThickness='1' slantLabels='1'  showBorder='1'  bgColor='FFFFFF'  yAxisMaxValue='"+maxValue+"'  showValues='1'  maxColWidth='20' plotSpacePercent='40'  > ";
		data2+=categories;
		data2+=dataset;
		    //<trendlines>\
		    //  <line startValue='"+value.value1.amountCurr*50+"' color='91C728' displayValue='Target' showOnTop='1'/>\
		    //</trendlines>\
		    
		data2+="</chart>" ;
	return data2;
}


  /*把图片插入到div中
     flexPath flex的路经
     divId  div的ID
     width　　图的的宽
     lenght　　图的长
     data　　　动态数据
     charName　　名称
  */
  function  insertFlexToDivs(flexPath,divId,width,lenght ,data ,charName){ 	  
        var my_chart =new FusionCharts( flexPath , charName, width, lenght, "0" ) ;
            my_chart.setDataXML(data) ;
            my_chart.render(divId);
         }
  function  updateFlexToDivs(flexPath,divId,width,lenght ,data ,charName){ 	
	  		var my_chart = getChartFromId(charName);
                my_chart.setDataXML(data) ;
                //my_chart.render(divId);
             }
 

  
  /* 
    指定类型到一个地方  
   */
   function  setFlexT0posi(divId,chartsType,data){
          var  width ;
          var  hgiht ;
          var  url="";
          var flexPath1;
          var name;
        if (chartsType=="AngularGauge"){           
			        width='100%';	 
			        hgiht='100%'  ;  
			        name="chart_"+divId;
			        flexPath1 = "${path}/swf/AngularGauge.swf";
          }else if(chartsType=="MSColumn2D") {
		        width='100%';	 
		        hgiht='100%' ; 
		        name="chart_"+divId;
		        flexPath1 = "${path}/swf/MSColumn2D.swf"; 
       }else if(chartsType=="MSBar2D") {
	        width='100%';	 
	        hgiht='100%' ; 
	        name="chart_"+divId;
	        flexPath1 = "${path}/swf/MSBar2D.swf"; 
   		} else{
   		 	alert("未取到对应图片");
            return  ;
        }
        var chart = getChartFromId(name);
        if(chart==null){
        	   insertFlexToDivs(flexPath1,divId,width,hgiht,data,name);
        }else{
        	FusionCharts(name).dispose();
        	insertFlexToDivs(flexPath1,divId,width,hgiht,data,name);
        	//updateFlexToDivs(flexPath1,divId,width,hgiht,data,name);
        }
   }


  


 </script>
</html>