<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../taglib.jsp"%>
<head>
<title>动态填充仪表盘</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script type="text/javascript" language="Javascript"	src="${path}/js/FusionCharts.js"></script>
<style type="text/css">
#chartContainer1{ width: 100%; height: 100%; padding: 0; border:none;}
#chartContainer2{ width: 100%; height: 100%; padding: 0; border:none;}
#chartContainer3{ width: 100%; height: 100%; padding: 0; border:none;}
#chartContainer4{ width: 100%; height: 100%; padding: 0; border:none;}
.chartSpan{width: 50%; height:250px; float: left; }
.tdcn{border:1px solid #ccc;width:50%;height:250px;}
</style>
</head>
<body>

<div style="height:400px;" class="block-body">
	<span style="width:25%;float:left;">
		<div align="center" id="chartContainer1"></div>
	</span>
	<span style="width:25%;float:left;">
		<div align="center" id="chartContainer2"></div>
	</span>
	<span style="width:25%;float:left;">
		<div align="center" id="chartContainer3"></div>
	</span>
	<span style="width:25%;float:left;">
		<div align="center" id="chartContainer4"></div>
	</span>
	<div align="center" id="chartContainer5"></div>
</div>
<script type="text/javascript">

	var _path = '${path}';
	var _amountCurr = "";
	function onClickBt(kpiId) {
		var _url = _path + "/dashBoard.do?method=homePage";
		$("button").css("background-color", "red");
		alert(0);
		$.ajax( {
			type :"get",
			url :_url,
			dataType :'json',
			success : function(data) {
				alert(data[0].yearId);
			}
		});
		alert(1);

	}
	function flushAngular(amountCurr) {

	}
	var data = "<chart lowerLimit='0' showBorder='0' bgColor='FFFFFF' manageResize='1'  gaugeInnerRadius='75%' chartLeftMargin='3' chartRightMargin='3' placeValuesInside='1' upperLimit='200'  gaugeStartAngle='225' gaugeEndAngle='-45' palette='1' numberSuffix='%' tickValueDistance='20' showValue='1'>"
			+ "<colorRange>"
			+ "<color minValue='0' maxValue='4' code='FF0000'/>"
			+ "<color minValue='4' maxValue='10' code='00FF00'/>"
			+ "</colorRange>"
			+ "<dials>"
			+ "<dial value='15' rearExtension='1'/>" + "</dials>" + "</chart>";
	var _swf = _path + "/swf/AngularGauge.swf";
	//new FusionCharts(swfUrl,id,width,height,debugMode(0/1),registerWithJS,bgColor,scaleMode);
	var chart_FG_AvgQuan = new FusionCharts(_swf, "FG_AvgQuan", "100%", "100%",	"0", "1");
	chart_FG_AvgQuan.setDataXML(data);
	chart_FG_AvgQuan.render("chartContainer1");

	var data2 = "<chart manageresize='1' origw='300' origh='300' bgcolor='FFFFFF' upperlimit='100' lowerlimit='0' basefontcolor='FFFFFF' majortmnumber='11' majortmcolor='FFFFFF' majortmheight='8' minortmnumber='5' minortmcolor='FFFFFF' minortmheight='3' tooltipbordercolor='FFFFFF' tooltipbgcolor='333333' gaugeouterradius='100' gaugestartangle='225' gaugeendangle='-45' placevaluesinside='1' gaugeinnerradius='80%' annrenderdelay='0' gaugefillmix='' pivotradius='10' showpivotborder='0' pivotfillmix='{CCCCCC},{333333}' pivotfillratio='50,50' showshadow='0' gaugeoriginx='150' gaugeoriginy='150' >"
	+"<colorrange>"
	+"<color minvalue='0' maxvalue='50' code='C1E1C1' alpha='40' />"
	+"<color minvalue='50' maxvalue='85' code='F6F164' alpha='40' />"
	+"<color minvalue='85' maxvalue='120' code='F70118' alpha='40' />"
	+"</colorrange>"
	+"<dials>"
	+"<dial value='65' bordercolor='FFFFFF' bgcolor='000000,CCCCCC,000000' borderalpha='0' basewidth='10' />"
	+"</dials>"
	+"<annotations>"
	+"<annotationgroup x='150' y='150' showbelow='1'>"
	+"<annotation type='circle' x='0' y='0' radius='145' fillcolor='CCCCCC,111111' fillpattern='linear' fillalpha='100,100' fillratio='50,50' fillangle='-45' />"
	+"<annotation type='circle' x='0' y='0' radius='120' fillcolor='111111,cccccc' fillpattern='linear' fillalpha='100,100' fillratio='50,50' fillangle='-45' />"
	+"<annotation type='circle' x='0' y='0' radius='110' color='666666' />"
	+"</annotationgroup>"
	+"</annotations>"
	+"</chart>";
	var chart_FG_AvgQuan2 = new FusionCharts(_swf, "FG_AvgQuan2", "100%","100%", "0", "1");
	chart_FG_AvgQuan2.setDataXML(data2);
	chart_FG_AvgQuan2.render("chartContainer2");

	var data3 = "<chart showValues='0' maxColWidth='20' plotSpacePercent='40'  yAxisMaxValue='34000'  numberPrefix='$'>"
			+"<set label='星期一' value='14400' code='8BBA00'/>"
			+"<set label='Week 2' value='19600' code='F6BD0F'/>"
			+"<set label='Week 3' value='24000' code='FF654F'/>"
			+"<set label='Week 4' value='15700' code='99CC00'/>"
			+"</chart>";
	var chart_FG_AvgQuan3 = new FusionCharts("/dgltbb/swf/Column2D.swf", "FG_AvgQuan3", "100%","100%", "0");
	chart_FG_AvgQuan3.setDataXML(data3);
	chart_FG_AvgQuan3.render("chartContainer3");

	var data4 = "<chart lowerLimit='0' upperLimit='20' majorTMNumber='6' minorTMNumber='3' gaugeFillMix='' pivotFillMix='{F0EFEA}, {BEBCB0}' pivotBorderColor='BEBCB0'  showBorder='1' bgColor='ffffff' palette='0' manageResize='1'   gaugeStartAngle='180' gaugeEndAngle='1' gaugeOuterRadius='100' numberSuffix='%' tickValueDistance='20' showValue='1'>"
			+ "<colorRange>"
			+ "<color minValue='0' maxValue='7' code='8BBA00'/>"
			+ "<color minValue='7' maxValue='9' code='F6BD0F'/>"
			+ "<color minValue='9' maxValue='11' code='FF654F'/>"
			+ "</colorRange>"
			+ "<dials>"
			+ "<dial value='8' rearExtension='1'/>" + "</dials>" + "</chart>";
	var chart_FG_AvgQuan4 = new FusionCharts(_swf, "FG_AvgQuan4", "100%","100%", "0", "1");
	chart_FG_AvgQuan4.setDataXML(data4);
	chart_FG_AvgQuan4.render("chartContainer4");
	
	var data5 = "<chart manageresize='1' origw='300' origh='300' palette='3' bgcolor='333333, 453269' bgalpha='100' lowerlimit='0' upperlimit='100' gaugestartangle='240' gaugeendangle='-60' gaugeouterradius='100%'"
	+" gaugeinnerradius='60%' gaugefillmix='{light-10},{light-30},{light-20},{dark-5},{color},{light-30},{light-20},{dark-10}' gaugefillratio='' basefontcolor='FFFFFF' tooltipbgcolor='333333' tooltipbordercolor='333333'"
	+" decimals='1' gaugeoriginx='150' gaugeoriginy='150' chartRightMargin='25' chartLeftMargin='125'>"
	+"<colorrange><color minvalue='0' maxvalue='30' /><color minvalue='30' maxvalue='50' /><color minvalue='50' maxvalue='80' /><color minvalue='80' maxvalue='100' />"
	+"</colorrange><dials><dial id='Dial1' value='60.2' basewidth='6' topwidth='1' editmode='1' showvalue='1' rearextension='10' valuey='270' />"
	+"<dial id='Dial2' value='50.3' basewidth='6' topwidth='1' editmode='1' rearextension='10' showvalue='1' valuey='250' /></dials></chart>"
	var chart_FG_AvgQuan5 = new FusionCharts(_swf, "FG_AvgQuan5", "100%","100%", "0", "1");
	chart_FG_AvgQuan5.setDataXML(data5);
	chart_FG_AvgQuan5.render("chartContainer5");
</script>
</body>
</html>
