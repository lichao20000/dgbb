package com.dglt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dglt.base.util.ClassUtil;
import com.dglt.base.util.ProperUtil;
import com.dglt.bb.pojo.WKipConfigV;
import com.dglt.bb.service.CompanyMapService;
import com.dglt.bb.service.GetTableDataService;
import com.dglt.comm.base.BaseAction;
import com.dglt.comm.base.Globals;
import com.dglt.comm.util.SystemUtil;
import com.dglt.comm.util.gson.JsonUtil;
@Controller
@RequestMapping("/companyMap.do")
public class CompanyMapControl extends BaseAction{
	@Resource(name = "companyMapService")
	private CompanyMapService companyMapService;
	@Resource(name = "GetTableDataService")
	private GetTableDataService  getTableDataService ;
	
	//欠费率  分公司页面全数据加载
	@RequestMapping(params = "method=companyPage")
	public String getCompanyPage(HttpServletRequest request, HttpServletResponse response,String period)
	{
		String returnPage="" ;
		try
		{
			//List l=this.companyMapService.getOweRateCompanyForm("", "", "", "", "", 0);
			List line = this.companyMapService.getOweRateCompanyLine("", "", "", "", "", 0);
			List map = this.companyMapService.getOweRateCompanyArea("","","","",1);
			request.setAttribute("line", JsonUtil.toJson(line));
			request.setAttribute("data", JsonUtil.toJson(map));
			//request.setAttribute("form", JsonUtil.toJson(l));
			//SystemUtil.responseJSONString(JsonUtil.toJson(map), response);
			//首页面下转到分公司的时候  分公司的值没有，默认为东莞的地图
			//地图的名称
			 String flexName ;
			 //地图标题
			 String mapName ="" ;
			String	branch  = (String)request.getParameter("branch");
			 if(branch==null||"".equals(branch)){ 
				 branch="FDW";
			 }
			WKipConfigV wKipConfigV = this.getTableDataService.getWKipConfigVbyKpiId("20003", "branch") ;
			request.setAttribute("wKipConfigV", wKipConfigV);
			//如果 wKipConfigV 没值 跳到错误页面  提示要配置
			 if(wKipConfigV==null){	 
				 returnPage="";
			   }else {
				   mapName = wKipConfigV.getKpName() ;
				   request.setAttribute("mapName", mapName);
				   flexName=Globals.getFlexNameByCode(branch) ;
				   request.setAttribute("flexName", flexName);	
				   returnPage=wKipConfigV.getUrl() ; 
			   }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnPage;
	}
	//欠费率  分公司页面全数据加载
	@RequestMapping(params = "method=companyPageArea")
	public String getCompanyPageArea(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			WKipConfigV wKipConfigV = this.getTableDataService.getWKipConfigVbyKpiId("20003", "branch") ;
			String monthId = (String)request.getParameter("period");
			monthId= monthId == null||"".endsWith(monthId)?"0":monthId;
			String profess = (String)request.getParameter("profess");
			profess= profess == null||"".endsWith(profess)?"":profess;
			String client = (String)request.getParameter("client");
			client= client == null||"".endsWith(client)?"":client;
			Map<String, String> rowMap = new HashMap<String, String>();
			rowMap.put("minValue",wKipConfigV.getMinVale());
			rowMap.put("alertValue",wKipConfigV.getAlertVale() );
			rowMap.put("warningValue", wKipConfigV.getWarningValue());
			rowMap.put("isAlert",wKipConfigV.getIsAlert());
			rowMap.put("maxValue",wKipConfigV.getMaxValue());
			rowMap.put("kpiId",wKipConfigV.getKpiId());
			List map = new ArrayList<HashMap>();
			if("0".equals(ProperUtil.getIsproceduGetData())){
				 map = this.companyMapService.getOweRateCompanyArea("",profess,client,"",Integer.parseInt(monthId));
			}else{
				 map = this.getTableDataService.getDashBoardRepDituQufenP(monthId, profess, "", "", "", "20003", client);
			}
			map.add(rowMap);
			SystemUtil.responseJSONString(JsonUtil.toJson(map), response);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	//欠费率  ajax联动分公司曲线数据加载
	@RequestMapping(params = "method=getOweRateCompanyBight")
	public String getOweRateCompanyBight(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			List line = new ArrayList();
			String code = (String)request.getParameter("districtBranchCode");
			String monthId = (String)request.getParameter("period");
			monthId= monthId == null||"".endsWith(monthId)?"0":monthId;
			String profess = (String)request.getParameter("profess");
			profess= profess == null||"".endsWith(profess)?"":profess;
			String client = (String)request.getParameter("client");
			client= client == null||"".endsWith(client)?"":client;
			if("0".equals(ProperUtil.getIsproceduGetData())){
				line = this.companyMapService.getOweRateCompanyLine("", profess,client, "", code, Integer.parseInt(monthId));
			}else{
				line = this.getTableDataService.getDashBoardRepQufenPLine(monthId, profess, code, "", "", "20003", client);
			}
			SystemUtil.responseJSONString(JsonUtil.toJson(line), response);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}	
	
	//长期欠费率  分公司页面全数据加载
	@RequestMapping(params = "method=companyPageAreaC")
	public String getCompanyPageAreaC(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			WKipConfigV wKipConfigV = this.getTableDataService.getWKipConfigVbyKpiId("20004", "branch") ;
			String monthId = (String)request.getParameter("period");
			monthId= monthId == null||"".endsWith(monthId)?"0":monthId;
			String profess = (String)request.getParameter("profess");
			profess= profess == null||"".endsWith(profess)?"":profess;
			String client = (String)request.getParameter("client");
			client= client == null||"".endsWith(client)?"":client;
			Map<String, String> rowMap = new HashMap<String, String>();
			rowMap.put("minValue",wKipConfigV.getMinVale());
			rowMap.put("alertValue",wKipConfigV.getAlertVale() );
			rowMap.put("warningValue", wKipConfigV.getWarningValue());
			rowMap.put("isAlert",wKipConfigV.getIsAlert());
			rowMap.put("maxValue",wKipConfigV.getMaxValue());
			rowMap.put("kpiId",wKipConfigV.getKpiId());
			List map = new ArrayList<HashMap>();
			if("0".equals(ProperUtil.getIsproceduGetData())){
				 map = this.companyMapService.getOweRateCompanyAreaC("",profess,client,"",Integer.parseInt(monthId));
			}else{
				 map = this.getTableDataService.getDashBoardRepDituQufenP(monthId, profess, "", "", "", "20004", client);
			}
			map.add(rowMap);
			SystemUtil.responseJSONString(JsonUtil.toJson(map), response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	//长期欠费率  ajax联动分公司曲线数据加载
	@RequestMapping(params = "method=getOweRateCompanyBightC")
	public String getOweRateCompanyBightC(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			List line = new ArrayList();
			String code = (String)request.getParameter("districtBranchCode");
			String monthId = (String)request.getParameter("period");
			monthId= monthId == null||"".endsWith(monthId)?"0":monthId;
			String profess = (String)request.getParameter("profess");
			profess= profess == null||"".endsWith(profess)?"":profess;
			String client = (String)request.getParameter("client");
			client= client == null||"".endsWith(client)?"":client;
			if("0".equals(ProperUtil.getIsproceduGetData())){
				line = this.companyMapService.getOweRateCompanyLineC("", profess,client, "", code, Integer.parseInt(monthId));
			}else{
				line = this.getTableDataService.getDashBoardRepQufenPLine(monthId, profess, code, "", "", "20004", client);
			}
			
			SystemUtil.responseJSONString(JsonUtil.toJson(line), response);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}	
	
	//总体欠费率  分公司页面全数据加载
	@RequestMapping(params = "method=companyPageAreaZ")
	public String getCompanyPageAreaZ(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			WKipConfigV wKipConfigV = this.getTableDataService.getWKipConfigVbyKpiId("20005", "branch") ;
			String monthId = (String)request.getParameter("period");
			monthId= monthId == null||"".endsWith(monthId)?"0":monthId;
			String profess = (String)request.getParameter("profess");
			profess= profess == null||"".endsWith(profess)?"":profess;
			String client = (String)request.getParameter("client");
			client= client == null||"".endsWith(client)?"":client;
			Map<String, String> rowMap = new HashMap<String, String>();
			rowMap.put("minValue",wKipConfigV.getMinVale());
			rowMap.put("alertValue",wKipConfigV.getAlertVale() );
			rowMap.put("warningValue", wKipConfigV.getWarningValue());
			rowMap.put("isAlert",wKipConfigV.getIsAlert());
			rowMap.put("maxValue",wKipConfigV.getMaxValue());
			rowMap.put("kpiId",wKipConfigV.getKpiId());
			List map = new ArrayList<HashMap>();
			if("0".equals(ProperUtil.getIsproceduGetData())){
				 map = this.companyMapService.getOweRateCompanyAreaZ("",profess,client,"",Integer.parseInt(monthId));
			}else{
				 map = this.getTableDataService.getDashBoardRepDituQufenP(monthId, profess, "", "", "", "20005", client);
			}
			map.add(rowMap);
			SystemUtil.responseJSONString(JsonUtil.toJson(map), response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	//总体欠费率  ajax联动分公司曲线数据加载
	@RequestMapping(params = "method=getOweRateCompanyBightZ")
	public String getOweRateCompanyBightZ(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			List line = new ArrayList();
			String code = (String)request.getParameter("districtBranchCode");
			String monthId = (String)request.getParameter("period");
			monthId= monthId == null||"".endsWith(monthId)?"0":monthId;
			String profess = (String)request.getParameter("profess");
			profess= profess == null||"".endsWith(profess)?"":profess;
			String client = (String)request.getParameter("client");
			client= client == null||"".endsWith(client)?"":client;
			if("0".equals(ProperUtil.getIsproceduGetData())){
				line = this.companyMapService.getOweRateCompanyLineZ("", profess,client, "", code, Integer.parseInt(monthId));
			}else{
				line = this.getTableDataService.getDashBoardRepQufenPLine(monthId, profess, code, "", "", "20005", client);
			}
			
			SystemUtil.responseJSONString(JsonUtil.toJson(line), response);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}	


}
