var i;
var j=-1;
var sum=0;
var a="";
//存放加
var mapDate ;



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
   
    //地图XML组装 
    //datas 数据  mapName 地图名 minValue最小值    value1 最分界值 1  Value2 分界值 2 max value  最大值   sortType 警示方向   isAlert是否有提示值
      function getMapDate_1 (datas,mapName,minValue ,value1,value2,maxValue ,sortType,isAlert){ 
        //  alert(maxValue);
    	  
    	  minValue = minValue*100; 
    	  maxValue = maxValue*100; 
    	  value1 = value1*100; 
    	  value2 = value2*100; 
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