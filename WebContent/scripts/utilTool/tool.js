/**
 * 组装地图数据xml
 * @param datas 地图信息数据集
 * @param mapName 地图标题
 * @param value1  提醒界限值
 * @param Value2  预警界限值
 * @param func 
 * @return
 */
function getMapDateT (datas,mapName,value1,Value2,func){
    	 var xml="";
  		var jsonxml="";
  		var data = datas;
  		var obj = eval(data); 
  		 if(obj==null){
               return xml;
          }
  		  //
  		for(i=0;i<obj.length;i++){
			jsonxml+="<entity  id='"+obj[i].mapCode+"' value='"+obj[i].amountCurr+"'  link='javascript:"+func+"("+i+")'/>"
		}  
        //xml
          xml="<map caption ='"+mapName+"'  showAboutMenuItem='1' aboutMenuItemLabel='About China Unicom' aboutMenuItemLink='http://www.10010.com'  animation='1' "+
        		" bgColor='FFFFFF'  bgAlpha='300,300'  bgRatio='0,100' bgAngle='0'  showShadow='1'  showBevel='1'  includeValueInLabels='1'  labelSepChar='{br}' "+
        		"  numberSuffix='%' "+
        		"  fillColor='F0FAFF' "+
        		"  borderColor='330000'"+
        		" baseFontSize='12'"+
        		"  legendPosition='right'"+
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
	        	 "	</data>"+
	             "</map>" 		 
            return xml ;
      		}
/**
 * 组装饼图xml
 * @param datas 饼图信息数据集
 * @param mapName 饼图标题
 * @param value1
 * @param Value2
 * @param func 链接方法名
 * @return
 */
function getPieDate(datas,mapName,value1,Value2,func,ridius,t){
	var xml="";
	var jsonxml="";
	var obj = eval(datas); 	
	for(i=0;i<obj.length;i++){
		if(func){
			jsonxml+="<set label='"+obj[i].chartLabelName+"' value='"+obj[i].chartLabelValue+"' isSliced=''  link='javascript:"+func+"("+i+")'/>"
		}else{
			jsonxml+="<set label='"+obj[i].chartLabelName+"' value='"+obj[i].chartLabelValue+"' isSliced='' />"
		}
	}
	if(ridius){
		if(t=="Y"){
			xml="<chart caption='"+mapName+"' baseFont='微软雅黑' showValues='0' showLabels='0' palette='2' pieRadius='"+ridius+"' animation='1' formatNumberScale='0' numbersuffix='%' pieSliceDepth='30' startingAngle='125' bgColor='#F4F4F4' borderColor='#F4F4F4' >"+jsonxml+"</chart>";
		}else{
			xml="<chart caption='"+mapName+"' baseFont='微软雅黑' showValues='0' showLabels='0' palette='2' pieRadius='"+ridius+"' animation='1' formatNumberScale='0' pieSliceDepth='30' startingAngle='125' bgColor='#F4F4F4' borderColor='#F4F4F4' >"+jsonxml+"</chart>";
		}
	}else{
		if(t=="Y"){
			xml="<chart caption='"+mapName+"' baseFont='微软雅黑' showValues='0' showLabels='0'  palette='2' pieRadius='100' animation='1' formatNumberScale='0' numbersuffix='%' pieSliceDepth='30' startingAngle='125' bgColor='FFFFFF' borderColor='FFFFFF' >"+jsonxml+"</chart>";
		}else{
			xml="<chart caption='"+mapName+"' baseFont='微软雅黑' showValues='0' showLabels='0'  palette='2' pieRadius='100' animation='1' formatNumberScale='0' pieSliceDepth='30' startingAngle='125' bgColor='FFFFFF' borderColor='FFFFFF' >"+jsonxml+"</chart>";
		}
	}
	
	return xml;
}

/**
 * 组装折线图xml
 * @param obj 折线图信息数据集
 * @param lineName 折线图标题
 * @return
 */
function getLineData(obj,lineName,ifShow,t){
    if(obj==null || obj.length==0){
        return ;
        }
	var xml="";
	var jsonxml_CategoryName="";
	var jsonxml_Value="";
	var maxValue =0;
	var minValue =obj[0].chartLabelValue;
	for(i=0;i<obj.length;i++){
		if(maxValue<parseFloat(obj[i].chartLabelValue)){
			maxValue=parseFloat(obj[i].chartLabelValue);
		}
		if(minValue>parseFloat(obj[i].chartLabelValue)){
			minValue=parseFloat(obj[i].chartLabelValue);
		}
		var label = obj[i].chartLabelName;
		if(t=="Y"){
			if(i%2){
				jsonxml_CategoryName+="<category name='' />";
				jsonxml_Value+="<set value='"+obj[i].chartLabelValue+"' toolText='"+obj[i].chartLabelValue+"%'/>";
			}else{
				jsonxml_CategoryName+="<category name='"+label+"' />";
				jsonxml_Value+="<set value='"+obj[i].chartLabelValue+"' toolText='"+obj[i].chartLabelValue+"%'/>";
			}
		}else{
			if(i%2){
				jsonxml_CategoryName+="<category name='' />";
				jsonxml_Value+="<set value='"+obj[i].chartLabelValue+"' toolText='"+obj[i].chartLabelValue+"'/>";
			}else{
				jsonxml_CategoryName+="<category name='"+label+"' />";
				jsonxml_Value+="<set value='"+obj[i].chartLabelValue+"' toolText='"+obj[i].chartLabelValue+"'/>";
			}
		}
	}
	var maxYS = maxValue%10;
	var minYS = minValue%10;
	if(maxValue-minValue>5){
		var maxYS = maxValue%10;
		var minYS = minValue%10;
		if(maxValue>=50){
			maxValue=Math.ceil((maxValue)/10)*10;
			minValue=Math.floor((minValue)/10)*10;
			if(minValue>=1){
				minValue-=1;
			}else{
				minValue=0;
			}
		}else if(10<=maxValue<50){
			if(maxYS>=5){
				maxValue=Math.ceil((maxValue)/10)*10;
			}else{
				maxValue=Math.round((maxValue)/10)*10+5;
			}
			minValue=Math.floor((minValue)/10)*10;
			if(minValue>=1){
				minValue-=1;
			}else{
				minValue=0;
			}
		}else if(1<maxValue<10){
			if(maxYS>=5){
				maxValue=10;
			}else{
				maxValue=5;
			}
			minValue=0;
		}else{
			if(maxValue>0.5){
				maxValue=1;
			}else{
				maxValue=0.5;
			}
			minValue=0;
		}
	}else{
		if(maxValue>=10){
			if(maxYS>=5){
				maxValue=Math.ceil((maxValue)/10)*10;
				minValue=maxValue-5;
			}else{
				maxValue=Math.round((maxValue)/10)*10+5;
				minValue=maxValue-5;
			}
		}else if(1<maxValue<10){
				maxValue=Math.ceil((maxValue)/1);
				minValue=Math.floor(minValue/1);
				if(minValue>=1){
					minValue-=1;
				}else{
					minValue=0;
				}
		}else{
			if(maxValue>0.5){
				maxValue=1;
				if(minValue>0.5){
					minValue=0.5;
				}
			}else{
				maxValue=0.5;
				minValue=0;
			}
		}
	}
	if(t=="Y"){
		xml = "<graph caption='"+lineName+"' showPlotBorder='1' showBorder='0' baseFont='微软雅黑' showAlternateHGridColor='0' divLineColor='5E5750'"
			+"divLineIsDashed='0' paletteColors='FFFFFF' subcaption='' hovercapbg='FFECAA' hovercapborder='F47E00' formatNumberScale='0' decimalPrecision='2'"
			+"showvalues='0' numdivlines='10'  numVdivlines='0' yaxisminvalue='"+minValue+"' yaxismaxvalue='"+maxValue+"'  showBorder='0' rotateNames='0'  "
			+"slantLabels='1' numberSuffix='%' bgColor='#F0F0F0' borderColor='F8F8F8' canvasBorderColor='F8F8F8' canvasBgColor='#F8F8F8' ><categories>"+
		jsonxml_CategoryName+"</categories><dataset seriesName='' color='1D8BD1' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'>"+
		jsonxml_Value+"</dataset></graph>";
	}else{
		xml = "<graph caption='"+lineName+"' showPlotBorder='1' showBorder='0' baseFont='微软雅黑' showAlternateHGridColor='0' divLineColor='5E5750'"
			+"divLineIsDashed='0' paletteColors='FFFFFF' subcaption='' hovercapbg='FFECAA' hovercapborder='F47E00' formatNumberScale='0' decimalPrecision='2'"
			+"showvalues='0' numdivlines='10'  numVdivlines='0' yaxisminvalue='"+minValue+"' yaxismaxvalue='"+maxValue+"'  showBorder='0' rotateNames='0'  "
			+"slantLabels='1' bgColor='#F0F0F0' borderColor='F8F8F8' canvasBorderColor='F8F8F8' canvasBgColor='#F8F8F8' ><categories>"+
		jsonxml_CategoryName+"</categories><dataset seriesName='' color='1D8BD1' anchorBorderColor='1D8BD1' anchorBgColor='1D8BD1'>"+
		jsonxml_Value+"</dataset></graph>";
	}
	return xml;
}

function getChartDateByType(type,data,name,t){
	var chartData;
	if(type=="MSBar2D" || type=="MSColumn2D"){
		chartData = getMSColumnData(data,name,t);
	}else if(type=="MSLine"){
		chartData = getLineData(data,name,"",t);
	}else if(type=="Pie2D"){
		chartData = getPieDate(data,name,"","","",70,t);
	}
	return chartData;
}

/**
 * 组装柱状图数据xml
 * @param value 柱状图信息数据集
 * @param columnName 柱状图标题
 * @return
 */
function getMSColumnData(value,name,t){
	var maxValue = parseInt(value[0].chartLabelValue*1.2);
	maxValue = maxValue+(2-maxValue%2);
	var colorArray = ["e44a00","f8bd19","008ee4","6baa01","99CC00"];
	var categories ="<categories>";
	var dataset="<dataset>";
	var chartLabelName;
	var col;
	var categorieDG="";
	var categorieFS="";
	var categorieGZ="";
	var categorieSZ="";
	var datasetDG="";
	var datasetFS="";
	var datasetGZ="";
	var datasetSZ="";
	for(var i=0;null!=value[i];i++){
		chartLabelName = value[i].chartLabelName;
		if(chartLabelName =="东莞"){
			col = "e44a00";
			categorieDG="<category Label='"+chartLabelName+"'/>";
			datasetDG="<set value='"+value[i].chartLabelValue+"' color='"+col+"'/> ";
		}else if(chartLabelName =="佛山"){
			col = "f8bd19";
			categorieFS="<category Label='"+chartLabelName+"'/>";
			datasetFS="<set value='"+value[i].chartLabelValue+"' color='"+col+"'/> ";
		}else if(chartLabelName =="广州"){
			col = "008ee4";
			categorieGZ="<category Label='"+chartLabelName+"'/>";
			datasetGZ="<set value='"+value[i].chartLabelValue+"' color='"+col+"'/> ";
		}else if(chartLabelName =="深圳"){
			col = "6baa01";
			categorieSZ="<category Label='"+chartLabelName+"'/>";
			datasetSZ="<set value='"+value[i].chartLabelValue+"' color='"+col+"'/> ";
		}else{
			col = colorArray[i>5?5:i];
			categories +="<category Label='"+chartLabelName+"'/>";
			dataset+="<set value='"+value[i].chartLabelValue+"' color='"+col+"'/> ";
		}
		
	}
	categories+=(categorieDG+categorieFS+categorieGZ+categorieSZ);
	dataset+=(datasetDG+datasetFS+datasetGZ+datasetSZ);
	categories +="</categories>";
	dataset+="</dataset>";
	if(t=="Y"){
		
		var data2 ="<chart baseFont='微软雅黑' alternateVGridColor='FFFFFF'  numberSuffix='%' caption='"+name+"' rotateLabels='0' showPlotBorder='0' "
	  		+" divLineColor='999999' showAlternateHGridColor='0' canvasBorderColor='F8F8F8' canvasBaseColor='F8F8F8' canvasBorderThickness='1'" 
	  		+" slantLabels='1'  showBorder='0' "
		   	+"  bgColor='F0F0F0' canvasBgColor='#F8F8F8'  yAxisMaxValue='"+maxValue+"'  showValues='1'  maxColWidth='20' plotSpacePercent='40'  > ";
			data2+=categories;
			data2+=dataset;
			data2+="</chart>" ;
	}else{
		var data2 ="<chart baseFont='微软雅黑' alternateVGridColor='FFFFFF'  caption='"+name+"' rotateLabels='0' showPlotBorder='0' "
  		+" divLineColor='999999' showAlternateHGridColor='0' canvasBorderColor='F8F8F8' canvasBaseColor='F8F8F8' canvasBorderThickness='1'" 
  		+" slantLabels='1'  showBorder='0' "
	   	+"  bgColor='F0F0F0' canvasBgColor='#F8F8F8'  yAxisMaxValue='"+maxValue+"'  showValues='1'  maxColWidth='20' plotSpacePercent='40'  > ";
		data2+=categories;
		data2+=dataset;
		data2+="</chart>" ;
	}
	return data2;
}
/**
 * 组装两色仪表盘数据xml
 * @param kpiId 		指标用于辨别下转URL
 * @param value 		柱状图信息数据集
 * @param alertValue	提示值
 * @param warningValue	警告值
 * @param minValue		最小值
* @param minValue		最大值
 * @param columnName 柱状图标题
 * @return
 */
function getAngularGaugeData1_1(kpiId,value,warningValue,minValue,maxValue,isPercent){
	if(value==0){
		return "<chart></chart>";
 	}
	var color1="8BBA00";
	var color2="FF654F";
	if(maxValue>1000){
		
	}else{
		
	}
	var data;
	if(isPercent=="Y"){
		data="<chart lowerLimit='"+minValue+"' upperLimit='"+maxValue+"' gaugeStartAngle='180' gaugeEndAngle='0' gaugeFillMix='' majortmnumber='0' "
			 	+"pivotFillMix='{F0EFEA}, {BEBCB0}' pivotBorderColor='BEBCB0'  showBorder='1' borderThickness='0.1' borderColor='#F8F8F8' bgColor='#F0F0F0' manageResize='1'  gaugeInnerRadius='70%'"
			 	+" chartLeftMargin='3' chartRightMargin='3' chartBottomMargin='40' placeValuesInside='1'  palette='1' numberSuffix='%'"
			 	+" showtickvalues='0' tickValueDistance='13'  showValue='0' clickURL='javascript:getBranchUrl("+kpiId+")'>"
		   	+"<colorRange>"
	 			+"<color minValue='"+minValue+"' maxValue='"+warningValue+"' code='"+color1+"'/><color minValue='"+warningValue+"' maxValue='"+maxValue+"' code='"+color2+"'/>"
		   	+"</colorRange>"
	 		+"<dials>"
 				+"<dial value='"+value+"' baseWidth='8' radius='75' borderThickness='1' borderAlpha='0' />"
 			+"</dials>"
	 		+"<trendpoints>"
		 		+"<point valueInside='1' startValue='"+warningValue+"' displayValue='"+parseFloat(warningValue)+"%' color='"+color1+"' thickness='1' alpha='100'/>"
		 		+"<point valueInside='1' startValue='"+minValue+"' displayValue='"+parseFloat(minValue)+"%' color='"+color1+"' thickness='1' alpha='100'/>"
		   		+"<point valueInside='1' startValue='"+maxValue+"' displayValue='"+parseFloat(maxValue)+"%' color='"+color1+"' thickness='1' alpha='100'/>"
		   	+"</trendpoints>"
	 	+"</chart>"; 
	}else{
		minValue = (minValue*0.01).toFixed(0);
		maxValue = (maxValue*0.01).toFixed(0);
		warningValue = (warningValue*0.01).toFixed(0);
		data="<chart lowerLimit='"+minValue+"' upperLimit='"+maxValue+"' gaugeStartAngle='180' gaugeEndAngle='0' gaugeFillMix='' majortmnumber='0' "
		 	+"pivotFillMix='{F0EFEA}, {BEBCB0}' pivotBorderColor='BEBCB0'  showBorder='1' borderThickness='0.1' borderColor='#F8F8F8' bgColor='#F0F0F0' manageResize='1'  gaugeInnerRadius='70%'"
		 	+" chartLeftMargin='3' chartRightMargin='3' chartBottomMargin='40' placeValuesInside='1'  palette='1' "
		 	+" showtickvalues='0' tickValueDistance='13'  showValue='0' clickURL='javascript:getBranchUrl("+kpiId+")'>"
	   	+"<colorRange>"
				+"<color minValue='"+minValue+"' maxValue='"+warningValue+"' code='"+color1+"'/><color minValue='"+warningValue+"' maxValue='"+maxValue+"' code='"+color2+"'/>"
	   	+"</colorRange>"
			+"<dials>"
				+"<dial value='"+value+"' baseWidth='8' radius='75' borderThickness='1' borderAlpha='0' />"
			+"</dials>"
			+"<trendpoints>"
	 		+"<point valueInside='1' startValue='"+warningValue+"' displayValue='"+parseFloat(warningValue)+"' color='"+color1+"' thickness='1' alpha='100'/>"
	 		+"<point valueInside='1' startValue='"+minValue+"' displayValue='"+parseFloat(minValue)+"' color='"+color1+"' thickness='1' alpha='100'/>"
	   		+"<point valueInside='1' startValue='"+maxValue+"' displayValue='"+parseFloat(maxValue)+"' color='"+color1+"' thickness='1' alpha='100'/>"
	   	+"</trendpoints>"
		+"</chart>"; 
	}
	return data;
}
/**
 * 组装三色仪表盘数据xml
 * @param kpiId 		指标用于辨别下转URL
 * @param value 		柱状图信息数据集
 * @param alertValue	提示值
 * @param warningValue	警告值
 * @param minValue		最小值
* @param minValue		最大值
 * @param columnName 柱状图标题
 * @return
 */
//获取三色仪表盘数据
function getAngularGaugeData(kpiId,value,alertValue,warningValue,minValue,maxValue,isPercent){
	if(value==0){
	 	return "<chart></chart>";
	}
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
	var data;
	if(isPercent=="Y"){
			data="<chart lowerLimit='"+minValue+"'  upperLimit='"+maxValue+"' gaugeStartAngle='180' gaugeEndAngle='0' gaugeFillMix='' "
			 	+"pivotFillMix='{F0EFEA}, {BEBCB0}' pivotBorderColor='BEBCB0'  showBorder='1' borderThickness='0.1' borderColor='#F8F8F8' bgColor='#F0F0F0' manageResize='1'  gaugeInnerRadius='70%'"
			 	+" chartLeftMargin='3' chartRightMargin='3' chartBottomMargin='40' placeValuesInside='1'  palette='1' numberSuffix='%'"
			 	+" showtickvalues='0'  tickValueDistance='13'  showValue='0' clickURL='javascript:getBranchUrl("+kpiId+")'>"
		   	+"<colorRange>"
				+"<color minValue='"+minValue+"' maxValue='"+value1+"' code='"+color1+"'/>"
				+"<color minValue='"+value1+"' maxValue='"+value2+"' code='"+color2+"'/>"
		   		+"<color minValue='"+value2+"' maxValue='"+maxValue+"' code='"+color3+"'/>"
					+"</colorRange>"
				+"<dials>"
					+"<dial value='"+value+"' baseWidth='8' radius='75' borderThickness='1' borderAlpha='0'/>"
		   	+"</dials>"
				+"<trendpoints>"
				//+"<point radius='70' innerRadius='80' valueInside='0' startValue='"+value+"' displayValue='"+parseFloat(value)+"%' color='ffffff'  thickness='1' alpha='100'/>"
			    +"<point  valueInside='1' startValue='"+value1+"' displayValue='"+parseFloat(value1)+"%' color='"+color1+"' thickness='1' alpha='100'/>"
		   		+"<point valueInside='1' startValue='"+value2+"' displayValue='"+parseFloat(value2)+"%' color='"+color2+"' thickness='1' alpha='100'/>"
		   		+"<point valueInside='1' startValue='"+minValue+"' displayValue='"+parseFloat(minValue)+"%' color='"+color1+"' thickness='1' alpha='100'/>"
		   		+"<point valueInside='1' startValue='"+maxValue+"' displayValue='"+parseFloat(maxValue)+"%' color='"+color1+"' thickness='1' alpha='100'/>"
		   	+"</trendpoints>"
		   	+"</chart>"; 
	}else{
		minValue = (minValue*0.01).toFixed(0);
		maxValue = (maxValue*0.01).toFixed(0);
		value1 = (value1*0.01).toFixed(0);
		value2 = (value2*0.01).toFixed(0);
		data="<chart lowerLimit='"+minValue+"'  upperLimit='"+maxValue+"' gaugeStartAngle='180' gaugeEndAngle='0' gaugeFillMix='' "
		 	+"pivotFillMix='{F0EFEA}, {BEBCB0}' pivotBorderColor='BEBCB0'  showBorder='1' borderThickness='0.1' borderColor='#F8F8F8' bgColor='#F0F0F0' manageResize='1'  gaugeInnerRadius='70%'"
		 	+" chartLeftMargin='3' chartRightMargin='3' chartBottomMargin='40' placeValuesInside='1'  palette='1' "
		 	+" showtickvalues='0'  tickValueDistance='13'  showValue='0' clickURL='javascript:getBranchUrl("+kpiId+")'>"
	   	+"<colorRange>"
			+"<color minValue='"+minValue+"' maxValue='"+value1+"' code='"+color1+"'/>"
			+"<color minValue='"+value1+"' maxValue='"+value2+"' code='"+color2+"'/>"
	   		+"<color minValue='"+value2+"' maxValue='"+maxValue+"' code='"+color3+"'/>"
				+"</colorRange>"
			+"<dials>"
				+"<dial value='"+value+"' baseWidth='8' radius='75' borderThickness='1' borderAlpha='0'/>"
	   	+"</dials>"
			+"<trendpoints>"
			//+"<point radius='70' innerRadius='80' valueInside='0' startValue='"+value+"' displayValue='"+parseFloat(value)+"%' color='ffffff'  thickness='1' alpha='100'/>"
		    +"<point  valueInside='1' startValue='"+value1+"' displayValue='"+parseFloat(value1)+"' color='"+color1+"' thickness='1' alpha='100'/>"
	   		+"<point valueInside='1' startValue='"+value2+"' displayValue='"+parseFloat(value2)+"' color='"+color2+"' thickness='1' alpha='100'/>"
	   		+"<point valueInside='1' startValue='"+minValue+"' displayValue='"+parseFloat(minValue)+"' color='"+color1+"' thickness='1' alpha='100'/>"
	   		+"<point valueInside='1' startValue='"+maxValue+"' displayValue='"+parseFloat(maxValue)+"' color='"+color1+"' thickness='1' alpha='100'/>"
	   	+"</trendpoints>"
	   	+"</chart>"; 
	}
	return data;
}


/**
 * 生成相应展示图
 * @param chartType 展现图类型
 * @param datas 数据信息集
 * @param name 展现图标题
 * @param divId 展现图设置位置
 * @param flexPath 展示图形swf
 * @param ChartId 图表id
 * @return
 */
function showFlex(chartType,datas,name,divId,flexPath,ChartId){
	var xml="";
	if(chartType=="line"){
		xml = getLineData(datas,name,1);
	}else if(chartType=="column"){
		xml = getMSColumn3DData(datas,name);
	}
	showAnchor(xml,divId,flexPath,ChartId);
}



/**
 * 
 * @param xml 组装图表xml
 * @param divId 展现图设置位置
 * @param flexPath 展示图形swf
 * @param ChartId 图表id
 * @return
 */
function showAnchor(xml,divId,flexPath,ChartId){

	if(flexPath==null||flexPath==""){
		alert("");
		return;
		}
	
	var myChart = getChartFromId(ChartId);
	if(myChart==null){
		myChart = new FusionCharts(flexPath,ChartId, "100%","100%","0","1");
		myChart.setDataXML(xml);
		myChart.render(divId);
		}else{
		myChart.setDataXML(xml);
			}
}

/**
 * 地图XML组装 
 * @param datas  数据
 * @param mapName 地图名
 * @param minValue 最小值
 * @param value1 最分界值 1
 * @param Value2 分界值 2
 * @param maxValue 最大值
 * @param sortType 警示方向
 * @param isAlert 是否有提示值
 * @param func 链接方法名
 * @return
 */
function getMapDate (datas,mapName,sortType,func){
	  var xml="";
		var jsonxml="";
		//提醒值
		var jjxml=""
		var data = datas;
		var obj = eval(data);
		if(obj==null||obj==""){
			return xml;
		}
		 var minValue=obj[datas.length-1].minValue;
		 var value1=obj[datas.length-1].alertValue;
		 var value2=obj[datas.length-1].warningValue;
		 var isAlert=obj[datas.length-1].isAlert;
		 var maxValue=obj[datas.length-1].maxValue;
		 maxValue=maxValue*100;
		 value2=value2*100
		 value1=value1*100
		 isAlert='N';
		  //数据区
		for(i=0;i<obj.length-1;i++){
		jsonxml+="<entity  id='"+obj[i].mapCode+"' value='"+obj[i].amountCurr+"'  link='javascript:"+func+"("+i+")'/>" 
	}  
		  if(isAlert=='N'){
		    	 if (sortType=="POSITIVE"){
		             jjxml=  "   <colorRange> "+	             
			             "  <color minValue='"+minValue+"' maxValue='"+value2+"' displayValue='正常' color='92D300' /> "+
			             "  <color minValue='"+value2+"' maxValue='"+maxValue+"' displayValue='预警' color='EA0000' /> "+
			             " </colorRange>"
				   }else {
		             jjxml=  "   <colorRange> "+	             
			             "  <color minValue='"+minValue+"' maxValue='"+value2+"' displayValue='预警' color='EA0000' /> "+
			             "  <color minValue='"+value2+"' maxValue='"+maxValue+"' displayValue='正常' color='92D300' /> "+
			             " </colorRange>"
					   }	
		         }else {
			   if (sortType=="POSITIVE"){
		              jjxml=  "   <colorRange> "+	             
			             "  <color minValue='"+minValue+"' maxValue='"+value1+"' displayValue='正常' color='92D300' /> "+
			             "  <color minValue='"+value1+"' maxValue='"+value2+"' displayValue='提醒' color='FFC000' /> "+
			             "  <color minValue='"+value2+"' maxValue='"+maxValue+"' displayValue='预警' color='EA0000' /> "+
			             " </colorRange>"
				   
				   }else {
		              jjxml=  "   <colorRange> "+	             
			             "  <color minValue='"+minValue+"' maxValue='"+value2+"' displayValue='预警' color='EA0000' /> "+
			             "  <color minValue='"+value2+"' maxValue='"+value1+"' displayValue='提醒' color='FFC000' /> "+
			             "  <color minValue='"+value1+"' maxValue='"+maxValue+"' displayValue='正常' color='92D300' /> "+
			             " </colorRange>"
				    }	

		     } 
    //xml头
      xml="<map caption ='"+mapName+"' baseFont='微软雅黑'   showAboutMenuItem='1' aboutMenuItemLabel='' aboutMenuItemLink=''  animation='1' "+
    		 " bgColor='FFFFFF'  bgAlpha='300,300'  bgRatio='0,100' bgAngle='0'  showShadow='1'  showBevel='1'  includeValueInLabels='1'  labelSepChar='{br}' "+
    		 "  numberSuffix='%' "+
    		 "  fillColor='F0FAFF' "+
    		 " baseFontSize='12'"+
    		 "  legendPosition='right'"+
    		 "  legendBorderColor='FFFFFF' "+
    		 "  legendShadow='0' "+
    		 " useHoverColor='1'"+
    		 " borderColor='FFFFFF' showBorder='1' borderThickness='0.1'"+
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
        	
        return xml ;
  		} 
/**
 * 清除div中HTML内容和已创建图表对象
 * @param name divID
 * @param chartId 图表对象ID
 * @return
 */
function clearDiv(name,chartId){
	var myChart = getChartFromId(chartId);
	if(myChart==null){
		return;
	}else{
	FusionCharts(chartId).dispose();
	document.getElementById(name).innerHTML = '';
	}
}
/**
 * 返回当前选定月份的前12个月
 * @param period 当前选定月份
 * @return
 */
function monthArry(period){
	var year = 0;// 获取年份
	var month = 0;// 获取月份
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