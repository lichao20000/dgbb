package com.dglt.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dglt.bb.service.CompanyMapService;
import com.dglt.bb.service.ManagerService;
import com.dglt.comm.base.BaseAction;
import com.dglt.comm.base.Globals;
import com.dglt.comm.util.SystemUtil;
import com.dglt.comm.util.gson.JsonUtil;
@Controller
@RequestMapping("/Manager.do")
public class ManagerControl extends BaseAction{
	@Resource(name = "managerService")
	private ManagerService managerService;
	@RequestMapping(params = "method=managerPage")
	public String getbusiScPage(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			String districtBranchCode = (String)request.getParameter("districtBranchCode");
			String busiScCode = (String)request.getParameter("busiScCode");
			String monthId = (String)request.getParameter("period");
			monthId= monthId == null||"".endsWith(monthId)?"0":monthId;
			String profess = (String)request.getParameter("profess");
			String client = (String)request.getParameter("client");
			String typeCode = (String)request.getParameter("typeCode");
			String flexName="Pie2D.swf";
			request.setAttribute("code",districtBranchCode);
			request.setAttribute("busiScCode",busiScCode);
			request.setAttribute("period", monthId);
			request.setAttribute("profess", profess);
			request.setAttribute("client", client);
			request.setAttribute("typeCode", typeCode);
			request.setAttribute("flexName", flexName);
			request.setAttribute("lastPage", "1");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "ManagerMapDisplay/managerMap";
	}
	//欠费率  营服页面全数据加载
	@RequestMapping(params = "method=managerPageArea")
	public String getCompanyPageArea(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			String districtBranchCode = (String)request.getParameter("districtBranchCode");
			String busiScCode = (String)request.getParameter("busiScCode");
			String monthId = (String)request.getParameter("period");
			monthId= monthId == null||"".endsWith(monthId)?"0":monthId;
			String profess = (String)request.getParameter("profess");
			String client = (String)request.getParameter("client");
			List map = this.managerService.getOweRateManagerArea("","","","",Integer.parseInt(monthId),districtBranchCode, busiScCode);
			SystemUtil.responseJSONString(JsonUtil.toJson(map), response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	//欠费率  ajax联动营服曲线数据加载
	@RequestMapping(params = "method=getOweRatemanagerBight")
	public String getOweRatebusiScBight(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			List line = new ArrayList();
			String busiScCode = (String)request.getParameter("busiScCode");
			String managerNo = (String)request.getParameter("managerNo");
			String monthId = (String)request.getParameter("monthId");
			String profess = (String)request.getParameter("profess");
			String client = (String)request.getParameter("client");
			line = this.managerService.getOweRateManagerLine("", "", "", "", busiScCode, "".equals(monthId)||monthId==null?0:Integer.parseInt(monthId),"", managerNo);
			SystemUtil.responseJSONString(JsonUtil.toJson(line), response);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}	
	
	//长期欠费率  营服页面全数据加载
	@RequestMapping(params = "method=managerPageAreaC")
	public String getCompanyPageAreaC(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			String districtBranchCode = (String)request.getParameter("districtBranchCode");
			String busiScCode = (String)request.getParameter("busiScCode");
			String monthId = (String)request.getParameter("period");
			monthId= monthId == null||"".endsWith(monthId)?"0":monthId;
			String profess = (String)request.getParameter("profess");
			String client = (String)request.getParameter("client");
			List map = this.managerService.getOweRateManagerAreaC("","","","",Integer.parseInt(monthId),districtBranchCode, busiScCode);
			SystemUtil.responseJSONString(JsonUtil.toJson(map), response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	//长期欠费率  ajax联动营服曲线数据加载
	@RequestMapping(params = "method=getOweRatemanagerBightC")
	public String getOweRatebusiScBightC(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			List line = new ArrayList();
			String busiScCode = (String)request.getParameter("busiScCode");
			String managerNo = (String)request.getParameter("managerNo");
			String monthId = (String)request.getParameter("monthId");
			String profess = (String)request.getParameter("profess");
			String client = (String)request.getParameter("client");
			line = this.managerService.getOweRateManagerLineC("", "", "", "", busiScCode, "".equals(monthId)||monthId==null?0:Integer.parseInt(monthId),"", managerNo);
			SystemUtil.responseJSONString(JsonUtil.toJson(line), response);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}	
	
	//总体欠费率  营服页面全数据加载
	@RequestMapping(params = "method=managerPageAreaZ")
	public String getCompanyPageAreaZ(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			String districtBranchCode = (String)request.getParameter("districtBranchCode");
			String busiScCode = (String)request.getParameter("busiScCode");
			String monthId = (String)request.getParameter("period");
			monthId= monthId == null||"".endsWith(monthId)?"0":monthId;
			String profess = (String)request.getParameter("profess");
			String client = (String)request.getParameter("client");
			List map = this.managerService.getOweRateManagerAreaZ("","","","",Integer.parseInt(monthId),districtBranchCode, busiScCode);
			SystemUtil.responseJSONString(JsonUtil.toJson(map), response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	//总体欠费率  ajax联动营服曲线数据加载
@RequestMapping(params = "method=getOweRatemanagerBightZ")
	public String getOweRatebusiScBightZ(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			List line = new ArrayList();
			String districtBranchCode = (String)request.getParameter("districtBranchCode");
			String busiScCode = (String)request.getParameter("busiScCode");
			String managerNo = (String)request.getParameter("managerNo");
			String monthId = (String)request.getParameter("period");
			String profess = (String)request.getParameter("profess");
			String client = (String)request.getParameter("client");
			line = this.managerService.getOweRateManagerLineZ("", "", "", "", busiScCode, "".equals(monthId)||monthId==null?0:Integer.parseInt(monthId),"", managerNo);
			SystemUtil.responseJSONString(JsonUtil.toJson(line), response);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}	
}
