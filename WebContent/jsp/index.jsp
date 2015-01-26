<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">  
	<head>
		<title>报表管理</title>
        <style type="text/css" >
			.tip_title{ color:#FFF;padding-left:1%;height:20px;padding-top:5px;vertical-align:middle;font:bold;font-size:16px;}
		</style>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script type="text/javascript" src="../js/jquery.mini.1.8.3.js"></script>
		<script type="text/javascript" src="../js/FusionCharts.js"></script>	
	    <link rel="stylesheet" href="../css/font.css" type="text/css"></link>
   		<link rel="stylesheet" href="../css/style1.css" type="text/css"></link>
   		<link rel="stylesheet" href="../css/axure_rp_page.css" type="text/css"></link>
   	 	<link rel="stylesheet" href="../css/jquery-ui-themes.css" type="text/css"></link>
   	    <link rel="stylesheet" href="../css/axurerp_pagespecificstyles.css" type="text/css"></link>
   	    <script src="../scripts/sui/sui-source.js" type="text/javascript"></script>
   	    
	</head>
<body>
<div style="background-image:url(../images/u3_original.png);widh:100%;font-size:10px;padding-bottom:10px;padding-top:3px;">
    当前位置：仪表盘管理系统 > 分公司地图展示
    </div>
<div style="background-image:url(../images/u3_original.png);widh:100%;height:29px;margin-top:-5px;	">
      	<div style="background-image:url(../images/u5_original.png);height:25px;widh:100%; background-repeat: no-repeat;">
    		<div class="tip_title">分公司地图展示</div>
    	</div>
    </div>
    <table>
    	<tr align="center" >
               <td colspan="2"  align="left"> </td>
      </tr>
                <tr align="center" > 
                  <td colspan="2"  align="left"> 报表期间:
                    <select name="select2" id="se1" onchange="onSelectChange()"> 
                        <option  value="2013年一月">2013年一月</option>
                        <option  value="2013年二月">2013年二月</option>
                        <option  value="2013年三月">2013年三月</option>
                        <option  value="2013年四月">2013年四月</option>
                        <option  value="2013年五月">2013年五月</option>
                        <option  value="2013年六月">2013年六月</option>
                        <option  value="2013年七月">2013年七月</option>
                        <option  value="2013年八月">2013年八月</option>
                        <option  value="2013年九月">2013年九月</option>
                        <option  value="2013年十月">2013年十月</option>
                        <option  value="2013年十一月">2013年十一月</option>
                        <option  value="2013年十二月">2013年十二月</option>
                        <option selected value=" ">&nbsp;</option>
                    </select>
                    &nbsp; &nbsp;&nbsp;
                                                    专业	
                    <select name="select" id="se2"  onchange="onSelectChange()" >
                      <option  value="2G">2G</option>
                      <option  value="3G">3G</option>
                      <option  value="固网">固网</option>
                      <option  value="汇总">汇总</option>
                      <option selected value=" ">&nbsp;</option>
                    </select>                      
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      
					    分公司			
	     <SELECT id="se3" onchange="onSelectChange()" >
			<OPTION  value="常平分公司">常平分公司</OPTION>
			<OPTION  value="长安分公司">长安分公司</OPTION>
			<OPTION  value="城区分公司">城区分公司</OPTION>
			<OPTION  value="厚街分公司">厚街分公司</OPTION>
			<OPTION  value="虎门分公司">虎门分公司</OPTION>
			<OPTION  value="清溪分公司">清溪分公司</OPTION>
			<OPTION  value="石龙分公司">石龙分公司</OPTION>
			<OPTION  value="石排分公司">石排分公司</OPTION>
			<OPTION  value="市分本部及其它">市分本部及其它</OPTION>
			<OPTION  value="塘厦分公司">塘厦分公司</OPTION>
			<OPTION  value="万江分公司">万江分公司</OPTION>
			<OPTION  value="樟木头分公司">樟木头分公司</OPTION>
			<OPTION  value="合计">合计</OPTION>
			<OPTION selected value=" ">&nbsp;</OPTION>
            </SELECT>	
          营服中心 :
            <SELECT id="se4"  onchange="onSelectChange()"   >
				<OPTION  value="长安本部（虚拟）营服中心">长安本部（虚拟）营服中心</OPTION>
				<OPTION  value="长安长盛营服中心">长安长盛营服中心</OPTION>
				<OPTION  value="长安福海营服中心">长安福海营服中心</OPTION>
				<OPTION  value="长安新安营服中心">长安新安营服中心</OPTION>
				<OPTION  value="大岭山营服中心">大岭山营服中心</OPTION>
          <OPTION selected value=" ">&nbsp;</OPTION>
      </SELECT>    </td>
      <td><font style="padding-right:30px;">报表期间:&nbsp;<input class="sui-combobox"/></font></td>
                </tr>
    </table>
<div style="background-image:url(../images/u290_original.png); background-repeat: no-repeat;width:100%;height:9px;"></div>
    <div>
      <table border="0" cellpadding="0" cellspacing="0"
			style="margin-bottom: 8px;" id="table1" >
      <thead>
        <tr>
        <td ><table border="0" cellpadding="0" cellspacing="0"
			style="margin-bottom: 8px;" id="table2" >
          <thead>
            <tr>
              <td colspan="2">&nbsp;</td>
            </tr>
            <tr>
              <td ><div id="u78" class="u78" >
                <div id="u78_rtf">
                  <p style="text-align:center;" id="sp1"><span  style="font-family:微软雅黑;font-size:21px;font-weight:normal;font-style:normal;text-decoration:none;color:#000000;">收入进度预算完成率<input type="hidden" value="11"></span></p>
                </div>
              </div>
                <div id=u81 class="u81" >
                  <div id=u81_rtf>
                    <p style="text-align:center;"><span style="font-family:宋体;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">本月</span></p>
                  </div>
                </div>
                <div id=u82_container class="u82_container"></div>
                <div id=u79_container class="u79_container"> <br />
                </div>
                <input id=u409 type=submit class="u409" value="手动预警" />
                <img src="../images/u691_original.png" name="u70" id=u70  width="530" height="280" />
                <input name="image" type="image" class="u407" id=u407 src="../images/u407_original.png" />
                <input name="image3" type="image" class="u399" id=u399 src="../images/u399_original.png" />
                <input type="image" id=u403 src="../images/u399_original.png" class="u403" />
                <input type="image" id=u581 src="../images/u407_original.png" class="u581" />
                <input type="image" id=u401 src="../images/u399_original.png" class="u401" />
                <input type="image" id=u583 src="../images/u407_original.png" class="u583" /></td>
              <td><div id=u84 class="u84" >
                <div id=u84_rtf>
                  <p id="sp2" style="text-align:center;"><span style="font-family:微软雅黑;font-size:21px;font-weight:normal;font-style:normal;text-decoration:none;color:#000000;">利润预算完成率</span></p>
                </div>
              </div>
                <div id=u85 class="u85" >
                  <div id=u85_rtf>
                    <p style="text-align:center;"><span style="font-family:宋体;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">本月</span></p>
                  </div>
                </div>
                <div id=u86_container class="u86_container"></div>
                <div id=u88_container class="u88_container"></div>
                <input id=u397 type=submit class="u397" value="手动预警" />
                <img id=u71 src="../images/u691_original.png"  width="530" height="280" />
                <input type="image" id=u579 src="../images/u407_original.png" class="u579" /></td>
            </tr>
            <tr>
              <td><div id=u390 class="u390" >
                <div id=u390_rtf>
                  <p  id="sp3" style="text-align:center;"><span style="font-family:微软雅黑;font-size:21px;font-weight:normal;font-style:normal;text-decoration:none;color:#000000;">2G业务流失率</span></p>
                </div>
              </div>
                <div id=u391 class="u391" >
                  <div id=u391_rtf>
                    <p style="text-align:center;"><span style="font-family:宋体;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">本月</span></p>
                  </div>
                </div>
                <div id=u392_container class="u392_container"></div>
                <div id=u394_container class="u394_container"> </div>
                <input name="submit" type=submit class="u396" id=u396 value="手动预警" />
                <input name="image2" type="image" class="u405" id=u405 src="../images/u399_original.png" />
                <img src="../images/u691_original.png" name="u74" width="530" height="280" id=u74 /></td>
              <td><div id=u90 class="u90" >
                <div id=u90_rtf>
                  <p  id="sp4" style="text-align:center;"><span style="font-family:微软雅黑;font-size:21px;font-weight:normal;font-style:normal;text-decoration:none;color:#000000;">3G语音后付费用户流失率</span></p>
                </div>
              </div>
                <div id=u91 class="u91" >
                  <div id=u91_rtf>
                    <p style="text-align:center;"><span style="font-family:宋体;font-size:13px;font-weight:normal;font-style:normal;text-decoration:none;color:#333333;">本月</span></p>
                  </div>
                </div>
                <div id=u92_container class="u92_container"></div>
                <div id=u94_container class="u94_container"></div>
                <input id=u398 type=submit class="u398" value="手动预警" />
                <img id=u73 src="../images/u691_original.png" width="530" height="280" /></td>
            </tr>
          </thead>
          <tbody>
          </tbody>
        </table></td>
        </tr>
      </thead>
      </table>
    </div>
			<div class="page">&nbsp;</div>
</body>
 <script type="text/javascript">

$(document).ready(function(e){
	sui.parse();	
	var _path = "${path}";
	var _url = _path+"/dashBoard.do?method=homePage";
	//获取数据
	$.ajax({
		type: "get",
		url: _url,
		dataType:'json',
		success: function(data)
		{
			var a=0;
		}						
	});
});

 function getdata(value,alertValue,warningValue,minValue,maxValue){
	 var value1="";
	 var value2="";
	 var value3="";
	 var tickValue;
	 var color1="";//红
	 var color2="";//黄
	 var color3="";//绿
	 if(alertValue<warningValue){
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
	 var data="\
		 <chart lowerLimit='"+minValue+"' upperLimit='"+maxValue+"' lowerLimitDisplay='Bad' upperLimitDisplay='Good' gaugeStartAngle='180' gaugeEndAngle='0' palette='1' numberSuffix='%' tickValueDistance='20' showValue='1' clickURL='BranchMapDisplay/branchMap.jsp'>\
		   <colorRange>\
		      <color minValue='"+minValue+"' maxValue='"+value1+"' code='"+color1+"'/>\
		      <color minValue='"+value1+"' maxValue='"+value2+"' code='"+color2+"'/>\
		      <color minValue='"+warningValue+"' maxValue='"+maxValue+"' code='"+color3+"'/>\
		   </colorRange>\
		   <dials>\
		     <dial value='"+value+"' rearExtension='1'/>\
		   </dials>\
		  </chart>\
		 "; 
	return data;
}
 var revenueBudgetRate = getdata(8,7,9,0,13);
 var data = getdata(30,100,98,0,110);
 function aaa(){
	alert("");
 }

var data2 ="\
	<chart caption='' xAxisName='' yAxisName='' numberSuffix='%' showValues='0' > \
	   <categories>\
	      <category Label='东莞'/>\
	      <category Label='佛山'/>\
	      <category Label='广州'/>\
	      <category Label='深圳'/>\
	   </categories>\
	   <dataset seriesName='1月'>\
	       <set  value='10' /> \
	       <set  value='20' /> \
	       <set  value='12' /> \
	       <set  value='11' /> \
	   </dataset>\
	    <trendlines>\
	      <line startValue='26000' color='91C728' displayValue='Target' showOnTop='1'/>\
	   </trendlines>\
	</chart>\
" ;

var data3_json="";	
var data3 ="\
<chart caption='' xAxisName='' yAxisName='' showValues='0' numberPrefix='$' numVisiblePlot='6'>\
  <categories>\
      <category label='Jan' />\
      <category label='Feb' />\
      <category label='Mar' />\
      <category label='Apr' />\
      <category label='May' />\
      <category label='Jun' />\
      <category label='Jul' />\
      <category label='Aug' />\
      <category label='Sep' />\
      <category label='Oct' />\
      <category label='Nov' />\
      <category label='Dec' />\
   </categories>\
   <dataset seriesName='2006'>\
      <set value='27400' />\
      <set value='29800'/>\
      <set value='25800' />\
      <set value='26800' />\
      <set value='29600' />\
      <set value='32600' />\
      <set value='31800' />\
      <set value='36700' />\
      <set value='29700' />\
      <set value='31900' />\
      <set value='34800' />\
      <set value='24800' />\
   </dataset>\
   <dataset seriesName='2005'>\
      <set value='10000'/>\
      <set value='11500'/>\
      <set value='12500'/>\
      <set value='15000'/>\
      <set value='11000' />\
      <set value='9800' />\
      <set value='11800' />\
      <set value='19700' />\
      <set value='21700' />\
      <set value='21900' />\
      <set value='22900' />\
      <set value='20800' />\
   </dataset>\
   <trendlines>\
      <line startValue='26000' color='91C728' displayValue='Target' showOnTop='1'/>\
   </trendlines>\
</chart>\
" ;


   /*var chart_FG_AvgQuan = new FusionCharts("../swf/AngularGauge.swf", "FG_AvgQuan", "150", "100", "0", "1");
        // 按参数顺序：仪表盘swf、显示的区域div、显示宽度、显示高度、
        chart_FG_AvgQuan.setDataURL("?a=1");
        chart_FG_AvgQuan.setDataXML(data);
        chart_FG_AvgQuan.render("u82_container");*/
		 setFlexT0posi("1","20003","b_left") ;
		 setFlexT0posi("2","20003","b_right") ;
		 setFlexT0posi("3","20004","b_left") ;
		 setFlexT0posi("4","20004","b_right") ;
		  setFlexT0posi("5","20003","b_left") ;
		 setFlexT0posi("6","20003","b_right") ;
		 setFlexT0posi("7","20005","b_left") ;
		 setFlexT0posi("8","20005","b_right") ;
		  //$('#sp1').text('11111');
  //insertFlexToDivs("../swf/AngularGauge.swf" ,"u82_container","150","100" ,data,"FG_AvgQuan") ;    
  //insertFlexToDivs("../swf/MSColumn3D.swf" ,  "u79_container","300","205" ,data2,"tChartId") ;
  //  test ();
  
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
 
/* 通过类型和参数取数据
sameType 小类  说明他的 位置
*/ 
   function getDataByType(businessType ,sameType){
    //ajax 后台取得数据       
    var   se1_value =$("#se1  option:selected").text();
    var   se2_value  =$("#se2  option:selected").text();
    var   se3_value  =$("#se3  option:selected").text();
    var   se4_value =$("#se4  option:selected").text();
      var  mydata  ;
          if('20003'==businessType){
             if (sameType=='b_left'){
                  mydata =data;
              }else if  (sameType=='b_right'){
                  mydata =data2 ;
              }
         
          }else if('20004'==businessType){
              if (sameType=='b_left'){
                  mydata =data  ;
              }else if  (sameType=='b_right'){
                  mydata =data2  ;
              }
          }else if('20005'==businessType){
            if (sameType=='b_left'){
                  mydata =data  ;
              }else if  (sameType=='b_right'){
                  mydata =data3  ;
              }
          }
           return mydata ;
	}

/*通过类型得到图片 */
	function getFelxPath(businessType ,sameType){
	// 得到对应的
	  var felxPath ;
          if('20003'==businessType){
              if (sameType=='b_left'){
                felxPath ='../swf/AngularGauge.swf'  ;
              }else if  (sameType=='b_right'){
                 felxPath ='../swf/MSColumn3D.swf'  ;
              }
          
          }else if('20004'==businessType){
               if (sameType=='b_left'){
                 felxPath ='../swf/AngularGauge.swf'  ;
              }else if  (sameType=='b_right'){
                 felxPath ='../swf/MSBar3D.swf'  ;
              }         
           }else if('20005'==businessType){
            if (sameType=='b_left'){
                 felxPath ='../swf/AngularGauge.swf'  ;
              }else if  (sameType=='b_right'){
                felxPath ='../swf/MSLine.swf'  ;
              }
          } else {
           felxPath='' ;
          }
         if(felxPath==""){
            alert("未取到对应图片");
             return false ;
         }       
       return   felxPath  ;
	}
  
    function getTileName(businessType){
         return  "2G流量流失率";
    
    }
  
  /* 
    指定类型到一个地方  
   */
   function  setFlexT0posi(postType,businessType,sameType){
          var  divId1 ;
          var  divId2 ;
          var  tielID ;
          var  width ;
          var  hgiht ;
          var  url="";
        if (postType=="1"){           
			        divId1="u82_container" ;
			        name1="FG_AvgQuan1"+"1";
			        width='150';	 
			        hgiht='100'  ;  
          }else if(postType=="3") {
                    divId1="u86_container" ;
			        name1="FG_AvgQuan1"+"3";
			        width='150';	 
			        hgiht='100' ;  
           } else if (postType=="5"){
                    divId1="u392_container" ;
			        name1="FG_AvgQuan1"+"5";
			         width='150';	 
			        hgiht='100'   ;
           }else if(postType=="7") {
                    divId1="u92_container" ;
			         name1="FG_AvgQuan1"+"7";
			        width='150';	 
			        hgiht='100'   ;
            }else if(postType=="2") {
			        divId1="u79_container" ;
			        name1="FG_AvgQuan2"+"2";
			         width='300';	 
			        hgiht='195' 
				    url = "BranchMapDisplay/branchMap.jsp"  ;
           } else if (postType=="4"){     
			        divId1="u88_container" ;
			        name1="FG_AvgQuan2"+"4";
			         width='300';	 
			        hgiht='195'   ;
           }else if(postType=="6") {           
			        divId1="u394_container" ;	 
			        name1="FG_AvgQuan2"+"6";
			         width='300';	 
			         hgiht='195'   ;
            }
            else if(postType=="8") {
			        divId1="u94_container" ;
			        name1="FG_AvgQuan2"+"8";
			        width='300';	 
			         hgiht='195'   ;
            }
           data1     = getDataByType(businessType ,sameType);     
           flexPath1 = getFelxPath(businessType ,sameType);
           insertFlexToDivs(flexPath1,divId1,width,hgiht ,data1,name1) ;  
   }


  function onSelectChange(){
        var   se1_value =$("#se1  option:selected").text();
    	var   se2_value =$("#se2  option:selected").text();
   		var   se3_value =$("#se3  option:selected").text();
        var   se4_value =$("#se4  option:selected").text();
       // alert("se1_value" +se1_value+"se2_value" +se2_value+"se3_value" +se3_value+"se4_value" +se4_value) ;
  
  }

   $('#se1').change(onSelectChange()) ;
    $('#se2').change(onSelectChange())  ;
   $('#se3').change(onSelectChange())  ;
    $('#se4').change(onSelectChange())  ;
 

 </script>
</html>