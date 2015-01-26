package com.dglt.action;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dglt.base.util.ClassUtil;
import com.dglt.base.util.ProperUtil;
import com.dglt.bb.pojo.DashIndiv;
import com.dglt.bb.pojo.DashWarnDetail;
import com.dglt.bb.service.DashBoardService;
import com.dglt.bb.service.DashIndivService;
import com.dglt.comm.base.BaseAction;
import com.dglt.comm.email.EmailSendHelper;
import com.dglt.comm.util.DateUtils;
import com.dglt.comm.util.StringUtil;
import com.dglt.comm.util.SystemUtil;
import com.dglt.comm.util.gson.GsonUtil;
import com.dglt.comm.util.gson.JsonUtil;

@Controller
@RequestMapping("/dashBoard.do")
public class DashBoardControl extends BaseAction {
	@Resource(name = "dashBoardService")
	private DashBoardService dashBoardService;
	@Resource(name = "dashIndivService")
	private DashIndivService dashIndivService;

	// 手动预警、发送邮件
	@RequestMapping(params = "method=sendMail")
	public String sendMail(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		//短信发送状态
		String states = "SUCCESS";
		//暂时默认用户ID=0
		String userId = "0";
		
		String kpiId=StringUtil.defaultString(request.getParameter("kpiId"),null);
		String kpiName=StringUtil.defaultString(request.getParameter("kpiName"),""); 
		String monthDesc=StringUtil.defaultString(request.getParameter("monthDesc"),""); 
		String productName=StringUtil.defaultString(request.getParameter("productName"),""); 
		String clientName=StringUtil.defaultString(request.getParameter("clientName"),""); 
		String spName=StringUtil.defaultString(request.getParameter("spName"),""); 
		String amountcurr=StringUtil.defaultString(request.getParameter("amountcurr"),""); 
		String warnValue=StringUtil.defaultString(request.getParameter("warnValue"),""); 
		DashWarnDetail wd = new DashWarnDetail();
		try
		{
			//根据kpiId取接收邮件对象
			String reciever = dashIndivService.getReciever(kpiId);
			if (reciever != null){
				//发送邮件
			   String userEmail = reciever + "@chinaunicom.cn"; 
			   //以下为测试数据
			   //userEmail = "tanwei@hsmd.com.cn";
			   //---------------
			   //发送邮件
			   DecimalFormat df = new DecimalFormat("#.###");
			   amountcurr = df.format(Double.valueOf(amountcurr)*100)+"%";
			   if (EmailSendHelper.SendDashWarnContent(userEmail, kpiName, monthDesc,
					   productName, clientName, spName, amountcurr,warnValue)){
				   
				   wd.setEmail(userEmail);
				   wd.setReciever(reciever);
				   wd.setSendType("1");
				   wd.setSendStatus("1");
				   wd.setAttrib1(kpiName);
				   wd.setWarnInfoId(kpiId);
				   wd.setSendDate(DateUtils.getCCYYMMDDhhmmss());
				   
			   } else {
				   states = "SEND_ERROR";
			   }
			}
		}
		catch(Exception e)
		{
			wd.setSendStatus("2");
			states = "SEND_ERROR";
			e.printStackTrace();
		}
		finally{
			
			dashIndivService.insertDashWarnDetail(wd);
		}
		HashMap map = new HashMap();
		map.put("states", states);		
		SystemUtil.printJSONString(GsonUtil.toJson(map),response);
		return null;
	}

	
	@RequestMapping(params = "method=sendSMS")
	public String sendSMS(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		//短信发送状态
		String states = "SUCCESS";
		//暂时默认用户ID=0
		//String userId = "0";
		
		String kpiId=StringUtil.defaultString(request.getParameter("kpiId"),null);
		String kpiName=StringUtil.defaultString(request.getParameter("kpiName"),""); 
		String monthDesc=StringUtil.defaultString(request.getParameter("monthDesc"),""); 
		String productName=StringUtil.defaultString(request.getParameter("productName"),""); 
		String clientName=StringUtil.defaultString(request.getParameter("clientName"),""); 
		String spName=StringUtil.defaultString(request.getParameter("spName"),""); 
		String amountcurr=StringUtil.defaultString(request.getParameter("amountcurr"),""); 
		String warnValue=StringUtil.defaultString(request.getParameter("warnValue"),""); 
		DashWarnDetail wd = new DashWarnDetail();
		try
		{
			//根据kpiId取接收短信对象
			String tel = dashIndivService.getSMSReciever(kpiId);
			String reciever = dashIndivService.getReciever(kpiId);
			if (tel != null){
			   DecimalFormat df = new DecimalFormat("#.###");
			   amountcurr = df.format(Double.valueOf(amountcurr)*100)+"%";
			 //发送短信
			   dashIndivService.sendSMS(tel, kpiName, monthDesc,
					   productName, clientName, spName, amountcurr,warnValue);
			   wd.setMobile(tel);
			   wd.setReciever(reciever);
			   wd.setSendType("2");
			   wd.setSendStatus("1");
			   wd.setAttrib1(kpiName);
			   wd.setWarnInfoId(kpiId);
			   wd.setSendDate(DateUtils.getCCYYMMDDhhmmss());
			}
		}
		catch(Exception e)
		{
			wd.setSendStatus("2");
			states = "SEND_ERROR";
			e.printStackTrace();
		}
		finally{
			
			dashIndivService.insertDashWarnDetail(wd);
		}
		HashMap map = new HashMap();
		map.put("states", states);		
		SystemUtil.printJSONString(GsonUtil.toJson(map),response);
		return null;
	}
	
	
	// 首页
	@RequestMapping(params = "method=homePage")
	public String getHomePage(HttpServletRequest request,
			HttpServletResponse response) {
		String id=StringUtil.defaultString(request.getParameter("id"),""); 
		String userId=StringUtil.defaultString(request.getParameter("userId"),""); 
		request.setAttribute("menuId", id);
		request.setAttribute("userId", userId);
		return "index2";
	}

	@RequestMapping(params = "method=upDateIndiv")
	public String upDateIndiv(HttpServletRequest request,
			HttpServletResponse response) {
		String result="success";
		String userId=StringUtil.defaultString(request.getParameter("userId"),""); 
		String menuId=StringUtil.defaultString(request.getParameter("menuId"),""); 
		//String userId = "0";
		if(userId==""){
			result="userId没找到";
		}else{
			String index=StringUtil.defaultString(request.getParameter("index"),""); 
			String kpiId=StringUtil.defaultString(request.getParameter("kpiId"),""); 
			String kpiName=StringUtil.defaultString(request.getParameter("kpiName"),"");
			String kpiType=StringUtil.defaultString(request.getParameter("kpiType"),"");
			System.out.println(kpiName+"  "+kpiType);
			if(index=="" ||kpiId==""){
				result="获取指标失败";
			}else{
				DashIndiv indiv = dashIndivService.getDashIndivByUserIdAndMenuId(userId,menuId);
				switch (Integer.parseInt(index)) {
				case 0:
					indiv.setKpiId1(kpiId);
					indiv.setKpiName1(kpiName);
					indiv.setKpi1ChartType(kpiType);
					break;
				case 1:
					indiv.setKpiId2(kpiId);
					indiv.setKpiName2(kpiName);
					indiv.setKpi2ChartType(kpiType);
					break;
				case 2:
					indiv.setKpiId3(kpiId);
					indiv.setKpiName3(kpiName);
					indiv.setKpi3ChartType(kpiType);
					break;
				case 3:
					indiv.setKpiId4(kpiId);
					indiv.setKpiName4(kpiName);
					indiv.setKpi4ChartType(kpiType);
					break;
				}
				dashIndivService.updateDashIndiv(indiv);
			}
		}
		try {
			SystemUtil.responseJSONString(JsonUtil.toJson(result), response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String getKpiString(String userId,String menuId){
		DashIndiv indiv = this.getindiv(userId,menuId);
		//拼接指标ID和指标的展示方式，传到前台使用
		StringBuffer kpiIdString = new StringBuffer();
		kpiIdString.append(indiv.getKpiId1());
		kpiIdString.append(",");
		kpiIdString.append(indiv.getKpiId2());
		kpiIdString.append(",");
		kpiIdString.append(indiv.getKpiId3());
		kpiIdString.append(",");
		kpiIdString.append(indiv.getKpiId4());
		kpiIdString.append("&");
		kpiIdString.append(indiv.getKpi1ChartType()==null?1:indiv.getKpi1ChartType());
		kpiIdString.append("#");
		kpiIdString.append(indiv.getKpi2ChartType()==null?1:indiv.getKpi2ChartType());
		kpiIdString.append("#");
		kpiIdString.append(indiv.getKpi3ChartType()==null?1:indiv.getKpi3ChartType());
		kpiIdString.append("#");
		kpiIdString.append(indiv.getKpi4ChartType()==null?1:indiv.getKpi4ChartType());
		kpiIdString.append("#");
		return kpiIdString.toString();
	}
	private DashIndiv getindiv(String userId,String menuId){
		// 取个性化数据
		DashIndiv indiv = dashIndivService.getDashIndivByUserIdAndMenuId(userId,menuId);
		if (indiv == null) {
			indiv = dashIndivService.insertDashIndiv(userId,menuId);
		}
		if(indiv.getKpi1ChartType()==null){
			indiv.setKpi1ChartType("3");
		}
		if(indiv.getKpi2ChartType()==null){
			indiv.setKpi2ChartType("3");
		}
		if(indiv.getKpi3ChartType()==null){
			indiv.setKpi3ChartType("3");
		}
		if(indiv.getKpi4ChartType()==null){
			indiv.setKpi4ChartType("3");
		}
		return indiv;
	}
	@RequestMapping(params = "method=index")
	public String getArrearsRate(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String[] kpidArr = null;
			String[] chartTypeArray = null;
			String kpidArrayString = request.getParameter("kpidString") ;
			String userId = request.getParameter("userId") ;
			String menuId = request.getParameter("menuId") ;
			String chartTypeArrayString = request.getParameter("chartType") ;
			//如果前端没有传要加载的指标ID（kpidArrayString），页面首次加载或者刷新时调用
			if(kpidArrayString == null || "".equals(kpidArrayString) || kpidArrayString.split(",").length>1){
				String temp = this.getKpiString(userId,menuId);
				String [] tempArr = temp.split("&");
				kpidArr = tempArr[0].split(",");//获取要查询的指标ID数组
				chartTypeArray = tempArr[1].split("#");//获取要查询的指标类型数组
			}else{
				kpidArr = kpidArrayString.split(",");
			}
			String monthId = request.getParameter("monthId") == null ? "0"
					: request.getParameter("monthId");
			String specialty = request.getParameter("specialty") == null ? ""
					: request.getParameter("specialty");
			String client = request.getParameter("client") == null ? ""
					: request.getParameter("client");
			String product = request.getParameter("product") == null ? ""
					: request.getParameter("product");
			List arrearsRateList = null;
			HashMap<String, List> h = new HashMap<String, List>();
			// 根据kpidArr长度查询一个或多个非仪表盘数据
			for (int i = 0;kpidArr!=null && i < kpidArr.length; i++) {
				if(chartTypeArray!=null){
					chartTypeArrayString = chartTypeArray[i];
				}
				String flag = ProperUtil.getIsproceduGetData();
				if("1".equals(flag)){//flag为1时使用存储过程，否则用SQL，相应设置根据platformConfig.properties文件is.procedu.get.data
					if("1".equals(chartTypeArrayString)){
						//柱状图数据
						arrearsRateList = this.dashBoardService.getOweRateArea(Integer
								.parseInt(kpidArr[i]), Integer.parseInt(monthId),
								specialty, client, product);
					}else if("2".equals(chartTypeArrayString)){
						arrearsRateList = this.dashBoardService.getTop5Data(Integer
								.parseInt(kpidArr[i]), Integer.parseInt(monthId),
								specialty, client, product);
					}else if("3".equals(chartTypeArrayString)){
						arrearsRateList = this.dashBoardService.getLineData(Integer
								.parseInt(kpidArr[i]), Integer.parseInt(monthId),
								specialty, client, product);
					}else if("4".equals(chartTypeArrayString)){
						arrearsRateList = this.dashBoardService.getPieData(Integer
								.parseInt(kpidArr[i]), Integer.parseInt(monthId),
								specialty, client, product);
					}
					h.put(kpidArr[i], arrearsRateList);
					//查询仪表盘数据
					List dashDoardList = this.dashBoardService
							.getIncomeBudgetCompletRate(Integer
									.parseInt(kpidArr[i]), Integer
									.parseInt(monthId), specialty, client, product);
					h.put("dashDoard" + kpidArr[i], dashDoardList);
				}else{
					arrearsRateList = this.dashBoardService.getOweRateBySql(Integer
							.parseInt(kpidArr[i]), Integer.parseInt(monthId),
							specialty, client, product);
					h.put(kpidArr[i], arrearsRateList);
					//查询仪表盘数据
					List dashDoardList = this.dashBoardService
							.getChartDataBySql(Integer
									.parseInt(kpidArr[i]), Integer
									.parseInt(monthId), specialty, client, product);
					h.put("dashDoard" + kpidArr[i], dashDoardList);
				}
				
			}
			List s = new ArrayList(); 
			s.add(getindiv(userId,menuId));
			h.put("kapidArray", s);
			SystemUtil.responseJSONString(JsonUtil.toJson(h), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(params = "method=specialty")
	public String getSpecialtyData(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String l = this.dashBoardService.getSpecialtyData();
			SystemUtil.responseJSONString(l, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(params = "method=month")
	public String getMonthData(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			SystemUtil.responseJSONString(JsonUtil.toJson(ClassUtil
					.getDashMonthList()), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(params = "method=client")
	public String getClientData(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String l = this.dashBoardService.getClientData();
			SystemUtil.responseJSONString(l, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(params = "method=product")
	public String getProductData(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String specialty = request.getParameter("specialty") == null ? ""
					: request.getParameter("specialty");
			String l = this.dashBoardService.getProductData(specialty);
			SystemUtil.responseJSONString(l, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(params = "method=kpiName")
	public String getKpiName(HttpServletRequest request,
			HttpServletResponse response){
		String kpiType = request.getParameter("kpiType") == null ? ""
				: request.getParameter("kpiType");
		String kpiName = request.getParameter("kpiName") == null ? ""
				: request.getParameter("kpiName");
		String area = request.getParameter("area") == null ? ""
				: request.getParameter("area");
		String menuId = request.getParameter("menuId") == null ? ""
						: request.getParameter("menuId");
		System.out.println(menuId);
		List kpiData =  dashBoardService.getKpiName(kpiType, kpiName,area,menuId);
		try {
			SystemUtil.printJSONString(GsonUtil.toJson(kpiData),response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//获取指标大类
	@RequestMapping(params = "method=kpiType")
	public String getKpiType(HttpServletRequest request,
			HttpServletResponse response){
		try {
			String l = this.dashBoardService.getkpiType();
			SystemUtil.responseJSONString(l, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(params ="method=chartType")
	public String getCharttype(HttpServletRequest request,
			HttpServletResponse response){
		String kpiId=StringUtil.defaultString(request.getParameter("kpiId"),""); 
		try {
			String l = this.dashBoardService.getchartType(kpiId);
			SystemUtil.responseJSONString(l, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
