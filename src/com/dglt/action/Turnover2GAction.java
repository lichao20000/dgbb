package com.dglt.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dglt.bb.service.Turnover2GService;
import com.dglt.bb.service.Turnover3GService;
import com.dglt.bb.service.Turnover2GBizcsService;
import com.dglt.bb.service.Turnover3GBizcsService;
import com.dglt.comm.base.BaseAction;
import com.dglt.comm.util.SystemUtil;
import com.dglt.comm.util.gson.JsonUtil;
@Controller
@RequestMapping("/Trunover2g.do")  
public class Turnover2GAction   extends BaseAction{
	@Resource(name = "Turnover2GService")
	private Turnover2GService  turnover2GService ;
	@Resource(name = "Turnover3GService")
	private Turnover3GService  turnover3GService ;
	@Resource(name = "Turnover2GBizcsService")
	private Turnover2GBizcsService  turnover2GBizcsService ;
	@Resource(name = "Turnover3GBizcsService")
	private Turnover3GBizcsService  turnover3GBizcsService ;
	public String getbusiScPage(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			String code = (String)request.getParameter("districtBranchCode");
			List line = this.turnover2GService.getTurnover2GLine("", "", code, "", 0,"");
			List map = this.turnover2GService.getTurnover2GArea("","",code,"","");
			List tableDate =this.turnover2GService.getTurnover2GForm("", "", code, "", 0,"") ;
			request.setAttribute("line", JsonUtil.toJson(line));
			request.setAttribute("data", JsonUtil.toJson(map));	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "campMapDisplay/branch2GMap";
	}	

	//20g流失率公司曲线数据加载
	@RequestMapping(params = "method=turnover2GLine")
	public String getTurnover2GLine(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			List line = new ArrayList();
			String period  =(String)request.getParameter("period") ;
			String profess =(String)request.getParameter("profess") ;
			String branch  =(String)request.getParameter("branch") ;
			String bizcs =(String)request.getParameter("bizcs") ;
			String productName =(String)request.getParameter("productName") ;
			String myType =(String)request.getParameter("myType") ;
			int monthId=0 ;
			
			if("".equals(period)||period==null){
				monthId=0 ; 
			}else{
				monthId=Integer.parseInt(period) ;
			}
			if("2g".equals(myType)){
				line = this.turnover2GService.getTurnover2GLine(period, profess, branch, bizcs, monthId,productName) ;
			}else if("3g".equals(myType)){
				line = this.turnover3GService.getTurnover3GLine(period, profess, branch, bizcs, monthId,productName) ;
			}else if("3gBizcs".equals(myType)){
				line = this.turnover2GBizcsService.getTurnover2GBizcsLine(period, profess, branch, bizcs, monthId,productName) ;
			}else if("2gBizcs".equals(myType)){
				line = this.turnover3GBizcsService.getTurnover3GBizcsLine(period, profess, branch, bizcs, monthId,productName) ;	
			}
			
			
			
			SystemUtil.responseJSONString(JsonUtil.toJson(line), response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		 }
		return null;
	}	
	
	//2g流失率公司地图数据加载
	
	@RequestMapping(params = "method=turnover2GMap")
	public String getTurnover2GMap(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			List maps = new ArrayList();
			String period  =(String)request.getParameter("period") ;
			String profess =(String)request.getParameter("profess") ;
			String branch  =(String)request.getParameter("branch") ;
			String bizcs =(String)request.getParameter("bizcs") ;
			String productName =(String)request.getParameter("productName") ;
			String myType =(String)request.getParameter("myType") ;
			if("2g".equals(myType)){
				maps = this.turnover2GService.getTurnover2GArea(period, profess, branch, bizcs,productName) ;
			}else if("3g".equals(myType)){
				maps = this.turnover3GService.getTurnover3GArea(period, profess, branch, bizcs,productName) ;
			}else if("3gBizcs".equals(myType)){
				maps = this.turnover3GBizcsService.getTurnover3GBizcsArea(period, profess, branch, bizcs,productName) ;
			}else if("2gBizcs".equals(myType)){
				maps = this.turnover2GBizcsService.getTurnover2GBizcsArea(period, profess, branch, bizcs,productName) ;
			}
			SystemUtil.responseJSONString(JsonUtil.toJson(maps), response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		 }
		return null; 
	}	

	
	
	//2g流失率公司表格数据加载
	@RequestMapping(params = "method=turnover2GTable")
	public String getTurnover2GTable(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			List tables = new ArrayList();
			String period  =(String)request.getParameter("period") ;
			String profess =(String)request.getParameter("profess") ;
			String branch  =(String)request.getParameter("branch") ;
			String bizcs =(String)request.getParameter("bizcs") ;
			String productName =(String)request.getParameter("productName") ;
			String myType =(String)request.getParameter("myType") ;
			int monthId=0 ;
			
			if("".equals(period)||period==null){
				monthId=0 ; 
			}else{
				monthId=Integer.parseInt(period) ;
			}
			
			if("2g".equals(myType)){
				tables = this.turnover2GService.getTurnover2GForm(period, profess, branch, bizcs,monthId,productName) ;
			}else if("3g".equals(myType)){
				tables = this.turnover3GService.getTurnover3GForm(period, profess, branch, bizcs,monthId,productName) ;
			}else if("3gBizcs".equals(myType)){
				tables = this.turnover2GBizcsService.getTurnover2GBizcsForm(period, profess, branch, bizcs,monthId,productName) ;
			}else if("2gBizcs".equals(myType)){
				tables = this.turnover3GBizcsService.getTurnover3GBizcsForm(period, profess, branch, bizcs,monthId,productName) ;
			}
			
			SystemUtil.responseJSONString(JsonUtil.toJson(tables), response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		 }
		return null;
	}	
	
	
	
	
	
}
