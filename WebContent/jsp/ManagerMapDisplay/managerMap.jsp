<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../../taglib.jsp"  %>
<%String path = request.getContextPath();String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		 //期间
		String	period = (String)request.getAttribute("period");
		//专业
		String	profess  = (String)request.getAttribute("profess");
		//客户
		String	client   = (String)request.getAttribute("client");
		//产品
		String	productCode  = (String)request.getAttribute("productCode");
		//指标
		String	kpid  = (String)request.getAttribute("kpid");
		//跳转类型
		String	myType  = (String)request.getAttribute("myType");
		//跳转类型
		String	mapName  = (String)request.getAttribute("mapName");
		//默认标签页code
		String	typeCode  = (String)request.getAttribute("typeCode");
		
		String	flexName  = (String)request.getAttribute("flexName");
		
		String	code  = (String)request.getAttribute("code");
		
		String	busiScCode  = (String)request.getAttribute("busiScCode");
		
		String lastPage = (String)request.getAttribute("lastPage");
        %>
<html>
  <head>
    <title>销售经理曲线图</title>
	<script type="text/javascript" src="<%=basePath%>js/FusionCharts.js"></script>
	<style type="text/css"> 
	.tip_title{ color:#FFF;padding-left:1%;height:20px;padding-top:5px;vertical-align:middle;font:bold;font-size:16px;}
	.condition{margin-left:3%;margin-top:2%;}
	.image_title_selected{background-image:url(${path}/images/u97_original.png); background-repeat:no-repeat; width:121px; height:26px; vertical-align:middle; color:#FFF; text-align:center; padding-top:5px;font-size:18px;}
	.image_title{background-image:url(${path}/images/u93_original.png);background-repeat:no-repeat; width:121px; height:26px; vertical-align:middle; color:#000; text-align:center; padding-top:5px;font-size:18px;}
	.clien_title{height:20px;padding-top:5px;vertical-align:middle;color:#FFF;font:bold;padding-left:5px; font-size:18px }
</style>
<script type="text/javascript" >
   		$(document).ready(
   				function(e){
   	 				sui.parse();
   	 				//sui.get("clients").load();
   	 				//标签页转换方法设置
   	 			var nowDate = "<%=period%>";
 				var nowPro = "<%=profess%>";
 				var nowCli = "<%=client%>";
 				setValue("period",nowDate);
 				setValue("profess",nowPro);
 				setValue("Client",nowCli);
   	 				var index = <%=typeCode%>
					sui.get("tabs").activeTab(parseInt(index));
					var flag = false;
					if(parseInt(index)!=0){
						flag = true;
  	 					}
					var flexPath ="${path}/swf/<%=flexName%>" ;
   	 				sui.get("tabs").on("activechanged",function(e){
   	    				if(e.tab.title=="长期欠费率"){
   	        				//重新加载长期欠费率饼图
   	        				
   	        				var url="Manager.do?method=managerPageAreaC";
   	        				url=createUrlWithParam(url);
   	        		        showAnchor3(url,"changeFunsionC","branchMap3DC","myChartId3","长期",flexPath,1);
   	        		        flag=false;	
   	    				}else if(e.tab.title=="总体欠费率"){
   	    					//重新加载总欠费率饼图
   	    					var url="Manager.do?method=managerPageAreaZ";
   	    					url=createUrlWithParam(url);
   	    						showAnchor3(url,"changeFunsionZ","branchMap3DZ","myChartId4","总体",flexPath,2);
   	    						flag=false;
   	    				}else if(e.tab.title=="短期欠费率"){
   	   	    				if(!flag){
   	   	    				var url="Manager.do?method=managerPageArea";
	    					    url=createUrlWithParam(url);
	    						showAnchor3(url,"changeFunsion","branchMap3D","myChartId2","短期",flexPath,0);
	    						flag=false;
	        				}
   	   	   	    		  }	
   	    			});
   	 			}
		);
   </script>
   


     <script type="text/javascript">
   //拼接字符串for循环用到的局部变量
     var i;
     var j=-1;
     var obja;
   //加入条件筛选加载图表数据  
        function createUrlWithParam(url){
        	 var period = sui.get("period").value==""||sui.get("period").value==null?"":sui.get("period").value; 
             var profess = sui.get("profess").value==""||sui.get("profess").value==null?"":sui.get("profess").value; 
             var client = sui.get("Client").value==""||sui.get("Client").value==null?"":sui.get("Client").value; 
				if(period!=null&&period!=""){
					url+="&period="+period;
					}
				if(profess!=null&&profess!=""){
					url+="&profess="+profess;
					}
				if(client!=null&&client!=""){
					url+="&client="+client;
					}
				return url;
            }
    //传入指定条件查询相关数据
        function onChange(){
            var url = "";
            var urlt = "";
            var flexPath ="${path}/swf/<%=flexName%>"
        	if(sui.get("tabs").activeIndex==1){
				url="Manager.do?method=managerPageAreaC";
				urlt = createUrlWithParam(url);	
				showAnchor3(urlt,"changeFunsionC","branchMap3DC","myChartId3","长期",flexPath,1);
				}
			if(sui.get("tabs").activeIndex==2){
				url="Manager.do?method=managerPageAreaZ";
				urlt = createUrlWithParam(url);
				showAnchor3(urlt,"changeFunsionZ","branchMap3DZ","myChartId4","总体",flexPath,2);
			}
			if(sui.get("tabs").activeIndex==0){
				url="Manager.do?method=managerPageArea";
				urlt = createUrlWithParam(url);
				showAnchor3(urlt,"changeFunsion","branchMap3D","myChartId2","短期",flexPath,0);
			}
    }
  	
	//标签变换地图显示
	function showAnchor3(url,func,divId,chartId,str,flexPath,type){
		var xml="";
		var jsonxml="";
		var _path = "${path}";
		var _url = _path+"/"+url;
		var busiScCode = "<%=busiScCode%>";
		$.ajax({
			type:"post",
			dataType:"json",
			url: _url,
			data:{busiScCode:busiScCode},
			success:function(datas){
				obja = datas;
				var mapName = "销售经理"+str+"欠费率分布图";
				var xml = getPieDate(obja,mapName,5,8,func);
				showAnchor(xml,divId,flexPath,chartId);	
					if(type==0){
						changeFunsion(0);
						}
					if(type==1){
						changeFunsionC(0);
						}
					if(type==2){
						changeFunsionZ(0);
						}	
			}
		});
		}
	//通过<set元素>的link得到值传入后台去解析分公司名称
	//ajax传给后台用于联动折线图
	function changeFunsion(label){
		//将label解析成label中的值传递给后台
        var url = "/Manager.do?method=getOweRatemanagerBight";
		setParam(label,url,0,"clients0","busiScPage","line","branchMapzhe","${path}/swf/MSLine.swf","myChartId1");
	}
	//ajax传给后台用于联动折线图
	function changeFunsionC(label){
        var url = "/Manager.do?method=getOweRatemanagerBightC";
		setParam(label,url,1,"clients1","busiScPage","line","branchMapzheC","${path}/swf/MSLine.swf","myChartId5");
		}
	
	//ajax传给后台用于联动折线图
	function changeFunsionZ(label){
		var url = "/Manager.do?method=getOweRatemanagerBightZ";
		setParam(label,url,2,"clients2","busiScPage","line","branchMapzheZ","${path}/swf/MSLine.swf","myChartId6");
		}
	//传递参数生成与地图关联的相关图表
	function setParam(label,url,index,formId,method,chartType,divId,flexPath,chartId){
		var code = "<%=code%>";
		var busiScCode= "<%=busiScCode%>";
		var managerNo= obja[label].managerNo;
		var kpid = obja[label].kpiId;
		var period = sui.get("period").value==""||sui.get("period").value==null?"":sui.get("period").value; 
        var profess = sui.get("profess").value==""||sui.get("profess").value==null?"":sui.get("profess").value; 
        var client = sui.get("Client").value==""||sui.get("Client").value==null?"":sui.get("Client").value;
		
		 var lastPage = "<%=lastPage%>";
	        if(lastPage=="0"){
	        	forwordNextPage(label);
	    		if(key){
	    			nextPage(code,busiScCode,"${path}/busiSc.do?method="+method,index);
	    			}
	    		setTimeout(
	    				function(){
	    					   if(j==-1){
	    						   getLineDate(label,code,busiScCode,managerNo,url,chartType,divId,flexPath,chartId);
	    							chargeParaTable(period, profess, code, busiScCode,"","",kpid,formId,client);
	    						   }
	    			        },300);
	            }else{
	            	getLineDate(label,code,busiScCode,managerNo,url,chartType,divId,flexPath,chartId);
	        		chargeParaTable(period, profess, code, busiScCode,"","",kpid,formId,client);
	                }			
		}
	//ajax取后台折线数据
	function getLineDate(label,districtBranchCode,busiScCode,managerNo,url,line,divId,flexPath,chartId){	
		var lineName= obja[label].managerName;
		var _path = "${path}";
		var _url = _path+url+"&districtBranchCode="+districtBranchCode+"&busiScCode="+busiScCode+"&managerNo="+managerNo;
			_url = createUrlWithParam(_url);
		$.ajax({
			type:"post",
			dataType:"json",
			url: _url,
			success:function(datas){
				showFlex(line,datas,lineName,divId,flexPath,chartId);
			}
		});
	}
	//转入下一层页面
	function forwordNextPage(label){ 
		if(label==j){
			  key=true;
          }else {
		   j=label;
		   setTimeout(
				   function(){j=-1;},250);  
	}
}

	//跳转页面
   function nextPage(districtBranchCode,busiScCode,url,typeCode){
		  var url = url+"&districtBranchCode="+districtBranchCode+"&busiScCode="+busiScCode+"&typeCode="+typeCode;
		  url=createUrlWithParam(url);
	  	  window.location.href=url;
		}
	//读取表格数据
	function chargeParaTable(period, profess, branch, bizcs,productCode,myType,kpid,tableId,client){
		    var grid = sui.get(tableId);
		    grid.load({ "period": period ,"profess": profess  ,"branch": branch ,"bizcs": bizcs,"productCode":productCode ,"myType":myType,"kpid":kpid,"client":client});
   }
	//设定页面条件值
	function setValue(id,value){
		if(value!=null&&value!=""&&value!="null"){
			sui.get(id).setValue(value);
			}
		}
	 </script>
  </head>
  
  <body>
    <div style="background-image:url(${path}/images/u11_original.jpg);width:100%;font-size:10px;padding-bottom:5px;">
    当前位置：仪表盘管理系统 > 营服地图展示 >销售经理地图展示
    </div>    
    <div style="background-image:url(${path}/images/u11_original.jpg);width:100%">
      	<div style="background-image:url(${path}/images/u5_original.png);height:29px;width:100%; background-repeat: no-repeat;">
    		<div class="tip_title">销售经理地图展示</div>
    	</div>
    </div>
    <div style="background-color:#0000; background-repeat: repeat-y;width:100%;height:25px;margin-top:2px;"  >
        <font style="padding-right:20px;">报表期间:&nbsp;
    		<input id="period" name="period" url="${path}/dashBoard.do?method=month"  class="sui-combobox" value="<%=period%>" textField="text" valueField="id" onvaluechanged="onChange()"/>
    	</font>
        <font style="padding-right:20px;">专业:&nbsp;<input id="profess" name="Specialty" showNullItem="true" nullItemText="请选择..."
         class="sui-combobox" url="${path}/dashBoard.do?method=specialty" textField="localCode" valueField="spCode" onvaluechanged="onChange()" /></font>
        <font style="padding-right:20px;">客户:&nbsp;<input id="Client" name="Client" showNullItem="true" nullItemText="请选择..."
         class="sui-combobox" url="${path}/dashBoard.do?method=client" textField="clientName" valueField="clientCode" onvaluechanged="onChange()" /></font>
        <font style="padding-right:20px;">产品:&nbsp;<input id="Product" showNullItem="true" nullItemText="请选择..."
         name="Product" class="sui-combobox"  textField="productName" valueField="productCode"  onvaluechanged="onChange()"/></font>
    </div>
     <div id="tabs" class="sui-tabs" activeIndex="0" style="background-image:url(${path}/images/u284_original.png); background-repeat: no-repeat;width:100%;margin-top:1%;padding-left:1%;padding-top:10px;height:678px;">
        <div id = "tab1" title="短期欠费率" class="image_title_selected"  style="margin-bottom:-28px;">
        
    	<div id="shortOweRate" style="padding-top:2px;width:100%;background-repeat: no-repeat;height:49%;margin-bottom:10px;" >
            <!-- <span style="padding-left:5px;" id="branchMap3D"></span> -->
            <!-- <span style="margin-left:47px;margin-bottom:50px;" id="branchMapzhe"></span>-->
       	  <div id="branchMap3D" style="width:50%;height:320px;background-color:#FAFBFE;border:1px solid #000;float:left;"></div>
          <div id="branchMapzhe" style="width:49%;height:320px;background-color:#FAFBFE;border:1px solid #000;float:right;"></div>
        </div>
       <div style="background-image:url(${path}/images/u11_original.jpg);background-repeat: repeat-x;height:49%;width:100%">
      	<div style="background-image:url(${path}/images/u5_original.png);width:100%;height:10%; background-repeat: no-repeat;">
    		<div class="clien_title">
            	客户群
              </div>
    	</div>
        <div id="clients0" class="sui-datagrid" style="width:100%;height:90%;" allowAlternating="true"
                 idField="itemId" dataField="items" allowCellSelect="true"
                url="${path}/shuntJumpAction.do?method=getTableData" showPager="fasle" 
            >

            <div property="columns"> 
            	<div field="qufen_name" id="qufen_id" width="80" align="center" headerAlign="center" allowSort="true">分公司</div>
            	<div field="quyu_name" id="quyu_id" width="40" align="center" headerAlign="center" allowSort="true">营业厅</div> 
                <div field="sp_name" id="sp_id" width="80" align="center" headerAlign="center" allowSort="true">专业</div>     
                <div field="client_name" id="client_code" width="80" align="center" headerAlign="center" allowSort="true">客户</div>  
                <div field="salesmanager_name" id="quyu_id" width="60" align="left" headerAlign="center" allowSort="true">销售经理</div>     
                <div field="product_name" id="product_code" width="80" align="center" headerAlign="center" allowSort="true">产品 </div>  
                    
                <div field="month01" id="month01" width="40" align="center" headerAlign="center" allowSort="true">前1个月</div>      
                <div field="month02" id="month02" width="40" align="center" headerAlign="center" allowSort="true">前2个月</div>      
                <div field="month03" id="month03" width="40" align="center" headerAlign="center" allowSort="true">前3个月</div>         
                <div field="month04" id="month04" width="40" align="center" headerAlign="center" allowSort="true">前4个月</div>
                <div field="month05" id="month05" width="40" align="center" headerAlign="center" allowSort="true">前5个月</div>     
                <div field="month06" id="month06" width="40" align="center" headerAlign="center" allowSort="true">前6个月</div>      
                <div field="month07" id="month07" width="40" align="center" headerAlign="center" allowSort="true">前7个月</div>      
                <div field="month08" id="month08" width="40" align="center" headerAlign="center" allowSort="true">前8个月</div>         
                <div field="month09" id="month09" width="40" align="center" headerAlign="center" allowSort="true">前9个月</div>
                <div field="month010" id="month010" width="40" align="center" headerAlign="center" allowSort="true">前10个月</div>      
                <div field="month011" id="month011" width="40" align="center" headerAlign="center" allowSort="true">前11个月</div>         
                <div field="month012" id="month012" width="40" align="center" headerAlign="center" allowSort="true">前12个月</div>
            </div>
    </div>    
    </div>
   
        </div>
        <div id = "tab2" title="长期欠费率" class="image_title"   style="margin-left:121px;margin-top:-28px;">
          
    	<div id="shortOweRate" style="padding-top:2px;width:100%;background-repeat: no-repeat;height:49%;margin-bottom:10px;" >
            <!-- <span style="padding-left:5px;" id="branchMap3D"></span> -->
            <!-- <span style="margin-left:47px;margin-bottom:50px;" id="branchMapzhe"></span>-->
       	  <div id="branchMap3DC" style="width:50%;height:320px;background-color:#FAFBFE;border:1px solid #000;float:left;"></div>
          <div id="branchMapzheC" style="width:49%;height:320px;background-color:#FAFBFE;border:1px solid #000;float:right;"></div>
        </div>
       <div style="background-image:url(${path}/images/u11_original.jpg);background-repeat: repeat-x;height:49%;;width:100%">
      	<div style="background-image:url(${path}/images/u5_original.png);width:100%;height:10%; background-repeat: no-repeat;">
    		<div class="clien_title">
            	客户群
              </div>
    	</div>
            <div id="clients1" class="sui-datagrid" style="width:100%;height:90%"
                 idField="itemId" dataField="items" allowCellSelect="true"
                url="${path}/shuntJumpAction.do?method=getTableData" showPager="fasle" 
            >

            <div property="columns"> 
                <div field="sp_name" id="sp_id" width="80" align="center" headerAlign="center" allowSort="true">专业</div>     
                <div field="product_name" id="product_code" width="80" align="center" headerAlign="center" allowSort="true">产品 </div>      
                <div field="client_name" id="client_code" width="80" align="center" headerAlign="center" allowSort="true">客户</div>      
                <div field="kpi_name" id="kpi_id" width="80" align="center" headerAlign="center" allowSort="true">指标</div>         
                <div field="qufen_name" id="qufen_id" width="80" align="center" headerAlign="center" allowSort="true">分公司</div>
                <div field="quyu_name" id="quyu_id" width="40" align="center" headerAlign="center" allowSort="true">营业厅</div>     
                <div field="month01" id="month01" width="40" align="center" headerAlign="center" allowSort="true">前1个月</div>      
                <div field="month02" id="month02" width="40" align="center" headerAlign="center" allowSort="true">前2个月</div>      
                <div field="month03" id="month03" width="40" align="center" headerAlign="center" allowSort="true">前3个月</div>         
                <div field="month04" id="month04" width="40" align="center" headerAlign="center" allowSort="true">前4个月</div>
                <div field="month05" id="month05" width="40" align="center" headerAlign="center" allowSort="true">前5个月</div>     
                <div field="month06" id="month06" width="40" align="center" headerAlign="center" allowSort="true">前6个月</div>      
                <div field="month07" id="month07" width="40" align="center" headerAlign="center" allowSort="true">前7个月</div>      
                <div field="month08" id="month08" width="40" align="center" headerAlign="center" allowSort="true">前8个月</div>         
                <div field="month09" id="month09" width="40" align="center" headerAlign="center" allowSort="true">前9个月</div>
                <div field="month010" id="month010" width="40" align="center" headerAlign="center" allowSort="true">前10个月</div>      
                <div field="month011" id="month011" width="40" align="center" headerAlign="center" allowSort="true">前11个月</div>         
                <div field="month012" id="month012" width="40" align="center" headerAlign="center" allowSort="true">前12个月</div>
            </div>
    </div>
    </div>
        
        </div>
        <div id = "tab3"  title="总体欠费率" class="image_title"  style="margin-left:242px;margin-top:-30px;">
        
    	<div id="shortOweRate" style="padding-top:2px;width:100%;background-repeat: no-repeat;height:49%;margin-bottom:10px;;" >
            <!-- <span style="padding-left:5px;" id="branchMap3D"></span> -->
            <!-- <span style="margin-left:47px;margin-bottom:50px;" id="branchMapzhe"></span>-->
       	  <div id="branchMap3DZ" style="width:50%;height:320px;background-color:#FAFBFE;border:1px solid #000;float:left;"></div>
          <div id="branchMapzheZ" style="width:49%;height:320px;background-color:#FAFBFE;border:1px solid #000;float:right;"></div>
        </div>
       <div style="background-image:url(${path}/images/u11_original.jpg);background-repeat: repeat-x;height:49%;width:100%">
      	<div style="background-image:url(${path}/images/u5_original.png);width:100%;height:10%; background-repeat: no-repeat;">
    		<div class="clien_title">
            	客户群
              </div>
    	</div>
            <div id="clients2" class="sui-datagrid" style="width:100%;height:90%"
                 idField="itemId" dataField="items" allowCellSelect="true"
                url="${path}/shuntJumpAction.do?method=getTableData" showPager="fasle" 
            >

             <div property="columns"> 
                <div field="sp_name" id="sp_id" width="80" align="center" headerAlign="center" allowSort="true">专业</div>     
                <div field="product_name" id="product_code" width="80" align="center" headerAlign="center" allowSort="true">产品 </div>      
                <div field="client_name" id="client_code" width="80" align="center" headerAlign="center" allowSort="true">客户</div>      
                <div field="kpi_name" id="kpi_id" width="80" align="center" headerAlign="center" allowSort="true">指标</div>         
                <div field="qufen_name" id="qufen_id" width="80" align="center" headerAlign="center" allowSort="true">分公司</div>
                <div field="quyu_name" id="quyu_id" width="40" align="center" headerAlign="center" allowSort="true">营业厅</div>     
                <div field="month01" id="month01" width="40" align="center" headerAlign="center" allowSort="true">前1个月</div>      
                <div field="month02" id="month02" width="40" align="center" headerAlign="center" allowSort="true">前2个月</div>      
                <div field="month03" id="month03" width="40" align="center" headerAlign="center" allowSort="true">前3个月</div>         
                <div field="month04" id="month04" width="40" align="center" headerAlign="center" allowSort="true">前4个月</div>
                <div field="month05" id="month05" width="40" align="center" headerAlign="center" allowSort="true">前5个月</div>     
                <div field="month06" id="month06" width="40" align="center" headerAlign="center" allowSort="true">前6个月</div>      
                <div field="month07" id="month07" width="40" align="center" headerAlign="center" allowSort="true">前7个月</div>      
                <div field="month08" id="month08" width="40" align="center" headerAlign="center" allowSort="true">前8个月</div>         
                <div field="month09" id="month09" width="40" align="center" headerAlign="center" allowSort="true">前9个月</div>
                <div field="month010" id="month010" width="40" align="center" headerAlign="center" allowSort="true">前10个月</div>      
                <div field="month011" id="month011" width="40" align="center" headerAlign="center" allowSort="true">前11个月</div>         
                <div field="month012" id="month012" width="40" align="center" headerAlign="center" allowSort="true">前12个月</div>
            </div>
    </div>
    </div>
       
        
        </div>
    </div>
</body>
</html>