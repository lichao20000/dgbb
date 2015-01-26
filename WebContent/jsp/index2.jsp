<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

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
<%
String path = request.getContextPath();
String menuId = (String)request.getAttribute("menuId");
String userId = (String)request.getAttribute("userId");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>仪表盘首页</title>
		<link href="${path}/css/dash-style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}/js/FusionCharts.js"></script>
		<script type="text/javascript" src="${path}/scripts/utilTool/tool.js"></script>
	</head>
<body onload="onInit()" >
	<div id="body_div">
	<!-- 
		<div id="dhl">
		    当前位置：仪表盘管理系统 > 仪表盘首页展示
		</div>
	  <div id="dbt">
	    <div id="xbt">
	  			<div class="tip_title">仪表盘首页展示</div>
	  	</div>
	  </div>
	 -->
	  <div class="condition">
	  	<font class="padr">报表期间:&nbsp;
	  		<input id="Formperid" name="Formperid" onvaluechanged="valueChanged" class="sui-combobox"   url="${path}/dashBoard.do?method=month" textField="text" valueField="id"/>
	  	</font>
	      <font class="padr">专业:&nbsp;
	      		<input onvaluechanged="valueChanged" id="Specialty" name="Specialty" value="" class="sui-combobox" showNullItem="true" nullItemText="全部（专业）"
	      			url="${path}/dashBoard.do?method=specialty" textField="localCode" valueField="spCode" /></font>
	      <font class="padr">客户:&nbsp;
		      <input onvaluechanged="valueChanged" id="Client" name="Client" class="sui-combobox" showNullItem="true" nullItemText="全部（客户）" 
		     	 url="${path}/dashBoard.do?method=client" textField="clientName" valueField="clientCode" />
	      </font>
	      <font class="padr">产品类型:&nbsp;
		      <input onvaluechanged="valueChanged" width="150" id="Product" name="Product" class="sui-combobox" showNullItem="true" nullItemText="全部（产品）"  value=""
		      	 url="${path}/dashBoard.do?method=product" textField="productName" valueField="productCode" />
		  </font>
	  </div>
		<div id="redlg">&nbsp;&nbsp;</div>
    <div id="div_table">
      <table class="ctable" >
      	<tr>
					<td>
						<div class="zblh">
						<div style="text-decoration:underline;color: #A7A7A7;"><h3 class="chartsTitle"></h3></div>
							<input class="bates"  type="hidden" value=""/>
							<input class="amountcurr"  type="hidden" value=""/>
							<div style="width:100%;">
								<div class="div_left">
								<p class="p_value"></p>
								<div id="chartContainer1" class="chart_angularGauge" ></div>
								<div  class="sdbj">
									<input type="image" class="alertButton" src="${path}/images/alertImage.png" onclick="alertFunction(1)"></input>
								</div>
							</div>
							<div class="div_right">
								<div id="chartContainer2" class="chart_column"></div>
									<div class="fbpz">
										<input  type="image" class="label" src="${path}/images/u407_original.png" onclick="setLabelTarget(1)"></input>
										<input type="image" class="kpid" src="${path}/images/u399_original.png" onclick="setRenderTarget(1)"></input>
									</div>
								</div>
							</div>
						</div>
					</td>
      		<td>
						<div class="zblh">
							<div style="text-decoration:underline;color: #A7A7A7;"><h3 class="chartsTitle"></h3></div>
							<input class="bates"  type="hidden" value=""/>
							<input class="amountcurr"  type="hidden" value=""/>
							<div style="width:100%;">
								<div class="div_left">
								<p class="p_value" ></p>
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
      		<td>
						<div class="zblh">
							<div style="text-decoration:underline;color: #A7A7A7;"><h3 class="chartsTitle"></h3></div>
							<input class="bates"  type="hidden" value=""/>
							<input class="amountcurr"  type="hidden" value=""/>
							<div style="width:100%;">
								<div class="div_left">
								<p class="p_value"></p>
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
      		<td>
						<div class="zblh">
							<div style="text-decoration:underline;color: #A7A7A7;"><h3 class="chartsTitle"></h3></div>
							<input class="bates"  type="hidden" value=""/>
							<input class="amountcurr"  type="hidden" value=""/>
							<div style="width:100%;">
								<div class="div_left">
								<p class="p_value"></p>
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
	</div>
</body>
 <script type="text/javascript">
 var menuId ="<%=menuId%>" ;
 var userId="<%=userId%>";
 sui.parse();
 //后台传过来的个性化指标数据
 var requestArray;
 //从个性化指标对象requestArray获取当前显示的指标ID的字符串（用逗号分隔）
 function getArray(){
	 if(requestArray==null){
		 return;
	 }else{
		 return requestArray["kpiId1"]+","+requestArray["kpiId2"]+","+requestArray["kpiId3"]+","+requestArray["kpiId4"];
	 }
}
var alertSuccessMessage;//预警成功提示信息
var alertNum=0;//预警种类数目
var alertRequestNum=0;//预警成功的数目
var kpidArrayString="";//获取要显示的子表字符串;
var labelTarget = 0;
var renderTarget=0;//选择指标时用来区别改变哪个部分指标被改变
var noDataTitle="";//选择没有数据的指标之后使用该字段更新指标标题
var dataObject;
//个个维度的下拉列表改变时触发改方法
function valueChanged(e){
	var p_value = $(".p_value");
	for(var t=0;t<p_value.length;t++){
		p_value[t].innerHTML ="";
	}
	$(".chart_column").html("");
	$(".chart_angularGauge").html("");
	if(e.sender.id=="Specialty"){//产品和专业联动，当选择的下拉列表是产品类型时，联动加载专业
		sui.get("Product").load("${path}/dashBoard.do?method=product&specialty="+e.value);
	}
	loadData(kpidArrayString);
}

//设置选择指标后改变哪部分的图表
function setRenderTarget(number){
	renderTarget=number;
}
//设置点击了那部分的批注
function setLabelTarget(number){
	labelTarget = number;
}

function sentalert(number,_url,type){
	var monthDesc = ""+sui.get("Formperid").text;//获取当前选中的月份text
	var spName = ""+sui.get("Specialty").text;//获取当前选中的专业text
	var clientName = ""+sui.get("Client").text;//获取当前选中的客户text
	var productName  = ""+sui.get("Product").text;//获取当前选中的产品text
	var kpiId = requestArray["kpiId"+number];//$(".bates")[number-1].value;
	var warnValue = dataObject["dashDoard"+kpiId][0].warningValue;
	warnValue = Number(warnValue)*100+"%";
	var amountcurr = $(".amountcurr")[number-1].value;
	var kpiName = $("h3.chartsTitle")[number-1].innerHTML;
	if(!kpiId || !amountcurr || !kpiName){
		alert("获取指标数据出错");
		return;
	}
	$.ajax({
		type: "post",
		url: _url,
		data:{monthDesc:monthDesc,spName:spName,clientName:clientName,productName:productName,kpiId:kpiId,kpiName:kpiName,amountcurr:amountcurr,warnValue:warnValue},
		dataType:'json',
		success: function(result)
		{
			if(result.states!="SUCCESS"){
				alert(result.states);
				alertRequestNum=0;
				alertNum=0;
			}else{
				alertRequestNum++;
				if(alertNum==1 && alertRequestNum==1){
					alert(type+"预警成功");
					alertRequestNum=0;
					alertNum=0;
					return ;
				}else if(alertRequestNum==2){
					alert("短信预警成功，邮件预警成功！");
					alertRequestNum=0;
					alertNum=0;
				}
			}
		}
	});
}
//邮件预警方法
function alertFunction(number){
	sui.open({url: "${path}/jsp/alertWindow.html",
		    title: "发动预警", width: 270,height: 170,
		    iconCls:"sui--messagebox-info",
		    overflow:false,
		    allowResize:false,
		    showCloseButton:true,
		    onload: function(){},
		    ondestroy: function(e){
				if(e){
					if(e.SMS){
						alertNum++;
						sentalert(number,"${path}/dashBoard.do?method=sendSMS","短信");
					}
					if(e.email){
						alertNum++;
						sentalert(number,"${path}/dashBoard.do?method=sendMail","邮件");
					}
				}
			}
	});
	var _url="${path}/dashBoard.do?method=sendSMS";
	//var _url="${path}/dashBoard.do?method=sendMail";
	
}
//异步更新个性化指标
function upDateKpiId(index,kpiId,kpiName,type){
	var _url="${path}/dashBoard.do?method=upDateIndiv";
	$.ajax({
		type: "post",
		url: _url,
		data:{index:index,kpiId:kpiId,kpiName:kpiName,kpiType:type,userId:userId,menuId:menuId},
		dataType:'json',
		success: function(result)
		{
			if(result!="success"){
				alert(result);
			}
		}
	});
}
//选择指标时加载的方法
function loadFun(){
	var iframe = this.getIFrameEl();
	var area;
	var kpiId = requestArray["kpiId"+renderTarget];//$(".bates")[renderTarget-1].value;
	var chartTpye = requestArray["kpi"+renderTarget+"ChartType"];
	switch (renderTarget) {
	case 1:
		area="A"
		break;
	case 2:
		area="B"
		break;
	case 3:
		area="C"
		break;
	case 4:
		area="D"
		break;
	}
	//在打开的指标选择页面前调用里面的方法
    iframe.contentWindow.SetData(area,kpiId,chartTpye,menuId);
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
	upDateKpiId(renderTarget-1,data.kpiId,data.kpiName,data.chartNum);//更新个性化指标
	requestArray["kpi"+renderTarget+"ChartType"] = data.chartNum;
	$(".p_value")[renderTarget-1].innerHTML = "";
	if(data.type=="line"){
		loadData(data.kpiId,"3");
	}else if(data.type=="silong"){
		loadData(data.kpiId,"1");
	}else if(data.type=="top5"){
		loadData(data.kpiId,"2");
	}else if(data.type=="pie"){
		loadData(data.kpiId,"4");
	}else{
		loadData(data.kpiId);
	}
}
function onInit(){
	//设置期间默认时间为数据第一个
	var obj = sui.get("Formperid");
	var nowDate = obj.data[0].id;
	obj.setValue(nowDate);
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
		    title: "编辑", width: 600,height: 400,
		    showCloseButton:true,
		    onload: labelOnLoad
			});
	});
	loadData(kpidArrayString);
}
//没有数据时加载的图表，target为加载那一区域的图表
function getNoDataChart(target){
	if(target%2==1){
		var kpiId = requestArray[("kpiId"+target)];
		setFlexT0posi("chartContainer"+target,"AngularGauge","<chart clickURL='javascript:getBranchUrl("+kpiId+")'></chart>");
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
	var isPercent = data[0].isPercent;
	//if(bates[t].value==data[0].rowId){
  		alertValue = Number(data[0].alertValue);
  		warningValue = Number(data[0].warningValue);
  		value = Number(data[0].chartLabelValue);
  		//根据alertValue、warningValue来判断仪表盘数据越大越好还是越小越好
		if(alertValue>warningValue  && alertValue!=0 && warningValue!=0){
   			minValue = warningValue-Math.abs(alertValue-warningValue);
   			maxValue = alertValue+Math.abs(alertValue-warningValue);
   			if(value<minValue){
   				minValue = value*0.8;
   			}else if(value>maxValue){
   				if(maxValue>1){
   					maxValue = value*1.05;
   				}else if(maxValue<0.05){
   					maxValue = value*1.5;
   				}
   			}
   			if(isPercent=="Y"){
   				minValue = (minValue*100).toFixed(0)
   				maxValue = (maxValue*100).toFixed(0)
				value = getAngularGaugeData(data[0].rowId,(value*100).toFixed(2),(alertValue*100).toFixed(0),(warningValue*100).toFixed(0),minValue,maxValue,isPercent);
       		}else{
       			minValue = (minValue).toFixed(0)
   				maxValue = (maxValue).toFixed(0)
				value = getAngularGaugeData(data[0].rowId,(value).toFixed(0),(alertValue).toFixed(0),(warningValue).toFixed(0),minValue,maxValue,isPercent);
       		}
       	} else if(alertValue<warningValue  && alertValue!=0 && warningValue!=0){
       		minValue = alertValue-Math.abs(alertValue-warningValue);
   			maxValue = warningValue+Math.abs(alertValue-warningValue);
   			if(value<minValue){
   				minValue = value*0.8;
   			}else if(value>maxValue){
   				if(maxValue>1){
   					maxValue = value*1.05;
   				}else if(maxValue<0.05){
   					maxValue = value*1.5;
   				}
   			}
   			if(isPercent=="Y"){
   				minValue = (minValue*100).toFixed(0)
   				maxValue = (maxValue*100).toFixed(0)
				value = getAngularGaugeData(data[0].rowId,(value*100).toFixed(2),(alertValue*100).toFixed(0),(warningValue*100).toFixed(0),minValue,maxValue,isPercent);
       		}else{
       			minValue = (minValue).toFixed(0)
   				maxValue = (maxValue).toFixed(0)
				value = getAngularGaugeData(data[0].rowId,(value).toFixed(0),(alertValue).toFixed(0),(warningValue).toFixed(0),minValue,maxValue,isPercent);
       		}
       	}else{
       		minValue = warningValue*0.5;
   			maxValue = warningValue*1.5;
   			if(value<minValue){
   				minValue = value*0.8;
   			}else if(value>maxValue){
   				if(maxValue>1){
   					maxValue = value*1.05;
   				}else if(maxValue<0.05){
   					maxValue = value*1.5;
   				}
   			}
   			if(isPercent=="Y"){
   				minValue = (minValue*100).toFixed(0)
   				maxValue = (maxValue*100).toFixed(0)
				value = getAngularGaugeData1_1(data[0].rowId,(value*100).toFixed(2),(warningValue*100).toFixed(0),minValue,maxValue,isPercent);
       		}else{
       			minValue = (minValue).toFixed(0)
   				maxValue = (maxValue).toFixed(0)
				value = getAngularGaugeData1_1(data[0].rowId,(value).toFixed(0),(warningValue).toFixed(0),minValue,maxValue,isPercent);
       		}
       	}
       	//渲染一个仪表盘的时候
  		if(renderSize==1){
  	  		if((Number(data[0].chartLabelValue)*100)!=0){
  	  	  		//把值设置到仪表盘上面
  	  	  		if(isPercent=="Y"){
  	  				$(".p_value")[renderTarget-1].innerHTML = (Number(data[0].chartLabelValue)*100).toFixed(2)+"%";
  	  	  		}else{
  	  	  			//$(".p_value")[renderTarget-1].innerHTML = (Number(data[0].chartLabelValue)*100).toFixed(2);
  	  	  			$(".p_value")[renderTarget-1].innerHTML = (Number(data[0].chartLabelValue)).toFixed(0);
  	  	  		}
  	  		}else{
  	  			$(".p_value")[renderTarget-1].innerHTML ="";
  	  		}
			setFlexT0posi("chartContainer"+(renderTarget*2-1),"AngularGauge",value);
		}else{
			if((Number(data[0].chartLabelValue)*100)!=0){
				if(isPercent=="Y"){
					$(".p_value")[t].innerHTML = (Number(data[0].chartLabelValue)*100).toFixed(2)+"%";
				}else{
					//$(".p_value")[t].innerHTML = (Number(data[0].chartLabelValue)*100).toFixed(2);
					$(".p_value")[t].innerHTML = (Number(data[0].chartLabelValue)).toFixed(0);
				}
			}else{
				$(".p_value")[t].innerHTML ="";
			}
			setFlexT0posi("chartContainer"+((t+1)*2-1),"AngularGauge",value);
		}
      		
    //}
}
/*根据KPID加载图表数据
 * 	kpidArrayString：一个或多个指标的字符串，用逗号分隔
 */
function loadData(kpidString,type){
	var cookie = document.cookie;
	var _path = "${path}";
	var monthId = sui.get("Formperid").value;//获取当前选中的月份值
	var specialty = sui.get("Specialty").value;//获取当前选中的专业
	var client = sui.get("Client").value;//获取当前选中的客户
	var product = sui.get("Product").value;//获取当前选中的产品
	var _url = _path+"/dashBoard.do?method=index";
	var data;
	if(kpidString==""){
		data = {monthId:monthId,specialty:specialty,client:client,product:product,userId:userId,menuId:menuId};
	}else{
		data = {kpidString:kpidString,monthId:monthId,specialty:specialty,client:client,product:product,userId:userId,menuId:menuId};
	}
	if(type){
		data.chartType = type;
	}
	//获取数据
	$.ajax({
		type: "post",
		url: _url,
		data:data,
		dataType:"json",
		success: function(data)
		{
			dataObject=data;
			requestArray = data["kapidArray"][0];//从后台获取个性化指标
			kpidArrayString = getArray();//获取要展示指标ID，用逗号拼接
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
			//var _title = $(".p_title");
			if(kpidArray.length==0) return;//没有查到数据时直接返回
			var draw_num=0;
			for(var i=0;i<kpidArray.length;i++){
				if(null==data[kpidArray[i]] || 0==data[kpidArray[i]].length){//查询出来没有数据调用
					if(kpidArray.length>1){
						//_title[i].innerHTML = sui.get("Formperid").text;
						if(renderTarget==0){//没点击+下拉 或者 页面刷新的情况使用后台传过来个性化的指标名称
							chartsTitles[i].innerHTML = requestArray[("kpiName"+(i+1))];
						}else if(i+1==renderTarget){//点击+下拉 使用子页面返回的指标名称(只是用选择过一个指标)
							chartsTitles[i].innerHTML =noDataTitle;
						}else{//点击+下拉 （没有没点击的页面）(只是用选择过一个指标)
							chartsTitles[i].innerHTML = requestArray[("kpiName"+(i+1))];
						}
						getNoDataChart((i+1)*2);
					}else if(kpidArray.length==1 && renderTarget!=0){//
						//_title[renderTarget-1].innerHTML = sui.get("Formperid").text;
						chartsTitles[renderTarget-1].innerHTML = noDataTitle;
						getNoDataChart(renderTarget*2);
					}
					//var a = renderTarget==0?(i+1):renderTarget;
					//getNoDataChart(a*2);
					//$(".chart_column")[i].innerHTML = "<h3>没有找到相关数据</h3>";
					continue;
				}
				var d = data[kpidArray[i]];
				var _data;
				var type;
				
				var t;
				var isPercent;
				if(data[("dashDoard"+kpidArray[i])][0]){
					t = data[("dashDoard"+kpidArray[i])][0].isPercent;
					isPercent = data[("dashDoard"+kpidArray[i])][0].isPercent;
				}     
				
				if(kpidArray.length==1){//获取要显示指标类型
					type = requestArray["kpi"+(renderTarget)+"ChartType"];
				}else{
					type = requestArray["kpi"+(i+1)+"ChartType"];
				}
				if(!type){//类型为空是默认为柱状图
					type = "MSColumn2D"
				}
				var AxisName;
				if(type=="1"){
					AxisName="四龙对比";
				}else if(type=="2"){
					AxisName="区域分公司对比图";
				}else if(type=="3"){
					AxisName="趋势分析";
				}else if(type=="4"){
					AxisName="占比分析";
				}
				//alert(isPercent);
				if(isPercent=="Y"){
					for(var j=0;null!=d[j];j++){
						chartLabelName = d[j].chartLabelName;
						var flag = chartLabelName.indexOf("分公司");
						
						if(flag!="-1"){
							chartLabelName = chartLabelName.substring(0,flag);
						}
						//显示的数值为小数位后2位
						d[j].chartLabelValue = (d[j].chartLabelValue*100).toFixed(2);
						d[j].chartLabelName = chartLabelName;
					}
				}else{
					for(var j=0;null!=d[j];j++){
						chartLabelName = d[j].chartLabelName;
						var flag = chartLabelName.indexOf("分公司");
						
						if(flag!="-1"){
							chartLabelName = chartLabelName.substring(0,flag);
						}
						//显示的数值为小数位后2位
						d[j].chartLabelValue = (d[j].chartLabelValue*1).toFixed(0);
						d[j].chartLabelName = chartLabelName;
					}
				}
				if(type==1){
					chartType="MSBar2D";
				}else if(type==2){
					chartType="MSColumn2D";
				}else if(type==3){
					chartType="MSLine";
				}else if(type==4){
					chartType="Pie2D";
				}
				_data = getChartDateByType(chartType,d,AxisName,t);
				
				if(kpidArray.length>1){//查询的数据不为空
					//改变指标标题、月份等信息
					chartsTitles[i].innerHTML = requestArray[("kpiName"+(i+1))];
					setFlexT0posi("chartContainer"+(i+1)*2,chartType,_data);
				}else if(kpidArray.length==1){
					chartsTitles[renderTarget-1].innerHTML = requestArray[("kpiName"+renderTarget)];
					setFlexT0posi("chartContainer"+renderTarget*2,chartType,_data);
				}
				
				draw_num++;
			}
			for(var i=0;i<kpidArray.length;i++){//加载仪表盘
				
				//判断仪表盘对应记录是否为空
				if(null==data[kpidArray[i]] || data[("dashDoard"+kpidArray[i])].length==0) {
					//判断是选择指标跟新单个或者是整体更新
					if(kpidArray.length>1){
						getNoDataChart((i+1)*2-1);
						continue;
					}else{
						var a = renderTarget==0?(i+1):renderTarget;
						getNoDataChart(a*2-1);
						continue;
					}
				}
				//仪表盘对应记录不为空，并且只有根据选择指标单个更新
				if(data[kpidArray[i]] && kpidArray.length==1){
					bates[i].value = data[("dashDoard"+kpidArray[i])][0].rowId;
					//chartsTitles[i+1].innerHTML = data[kpidArray[i]][0].kpiName;
					amountcurr[i].value = data[("dashDoard"+kpidArray[i])][0].chartLabelValue;
					renderDashBoard(data[("dashDoard"+kpidArray[i])],i,kpidArray.length);
					continue;
				}
				//仪表盘对应记录不为空，并且整体更新
				bates[i].value = data[("dashDoard"+kpidArray[i])][0].rowId;
				amountcurr[i].value = data[("dashDoard"+kpidArray[i])][0].chartLabelValue;
				renderDashBoard(data[("dashDoard"+kpidArray[i])],i,kpidArray.length);
			}
		}		
	
	});
	
}
//获取仪表盘下钻页面URL
function getBranchUrl(kpiId){
	var url = "${path}/shuntJumpAction.do?method=getNextPage";
	var monthId = ""+sui.get("Formperid").value;//获取当前选中的月份值
	var specialty = ""+sui.get("Specialty").value;//获取当前选中的专业
	var client = ""+sui.get("Client").value;//获取当前选中的客户
	var product = ""+sui.get("Product").value;//获取当前选中的产品
	var q_url = url+"&kpid="+kpiId+"&period="+monthId+"&profess="+specialty+"&client="+client+"&productCode="+product+"&myType=branch"
	window.location.href = q_url;
	//return q_url;
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
   		}else if(chartsType=="MSLine") {
	        width='100%';	 
	        hgiht='100%' ; 
	        name="chart_"+divId;
	        flexPath1 = "${path}/swf/MSLine.swf"; 
   		}else if(chartsType=="Pie2D") {
	        width='100%';	 
	        hgiht='100%' ; 
	        name="chart_"+divId;
	        flexPath1 = "${path}/swf/Pie2D.swf"; 
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