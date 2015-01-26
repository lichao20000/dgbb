package com.dglt.action;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dglt.base.util.ProperUtil;
import com.dglt.bb.pojo.WKipConfigV;
import com.dglt.bb.service.BusiScService;
import com.dglt.bb.service.DashTaggingService;
import com.dglt.bb.service.GetTableDataService;
import com.dglt.comm.base.BaseAction;
import com.dglt.comm.base.Globals;
import com.dglt.comm.util.SystemUtil;
import com.dglt.comm.util.gson.JsonUtil;

@Controller
@RequestMapping("/busiSc.do")
public class BusiScControl extends BaseAction{
	@Resource(name = "BusiScService")
	private BusiScService busiScService;
	@Resource(name = "GetTableDataService")
	private GetTableDataService  getTableDataService ;
	@Resource(name = "DashTaggingService")
	private DashTaggingService  dashTaggingService ;
	//Ƿ����  Ӫ��ҳ��ȫ���ݼ���
	@RequestMapping(params = "method=busiScPage")
	public String getbusiScPage(HttpServletRequest request, HttpServletResponse response,String period)
	{
		String returnPage="" ;
		try
		{
			
			String typeCode = (String)request.getParameter("typeCode");
			request.setAttribute("typeCode", typeCode);
			 //��ͼ������
			 String flexName ;
			 //��ͼ����
			 String mapName ="" ;
			 //�ڼ�
			period = (String)request.getParameter("period");
			//רҵ
			String	profess  = (String)request.getParameter("profess");
			//�ͻ�
			String	client   = (String)request.getParameter("client");
			//��Ʒ
			String	productCode  = (String)request.getParameter("productCode");
			//ָ��
			String	kpid  = (String)request.getParameter("kpid");
			//��ת����
			String	myType  = (String)request.getParameter("myType");
			//�ֹ�˾
			String districtBranchCode = (String)request.getParameter("districtBranchCode");
			//Ӫҵ��	
			String	bizcs  = (String)request.getParameter("bizcs");
			String showStyle = this.getShowStyle(kpid);
			request.setAttribute("period", period);	
			request.setAttribute("profess", profess);
			request.setAttribute("client", client);	
			request.setAttribute("productCode", productCode);
			request.setAttribute("kpid", kpid);	
			request.setAttribute("myType", myType);
			request.setAttribute("code",districtBranchCode);
			request.setAttribute("bizcs", bizcs);
			request.setAttribute("showStyle", showStyle);
			WKipConfigV wKipConfigV = this.getTableDataService.getWKipConfigVbyKpiId(kpid, myType) ;
			request.setAttribute("wKipConfigV", wKipConfigV);
			//��� wKipConfigV ûֵ ��������ҳ��  ��ʾҪ����
			 if(wKipConfigV==null){	 
				 returnPage="";
			   }else {
				   mapName = wKipConfigV.getKpName() ;
				   request.setAttribute("mapName", mapName);
				   flexName=Globals.getFlexNameByCode(districtBranchCode) ;
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
	
	//Ƿ����  Ӫ��ҳ��ȫ���ݼ���
	@RequestMapping(params = "method=busiScPageArea")
	public String getCompanyPageArea(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			WKipConfigV wKipConfigV = this.getTableDataService.getWKipConfigVbyKpiId("20003", "branch") ;
			String districtBranchCode = (String)request.getParameter("districtBranchCode");
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
				 map = this.busiScService.getOweRateBusiScArea("", profess,client,"",Integer.parseInt(monthId),districtBranchCode);
			}else{
				map = this.getTableDataService.getDashBoardRepDituYfP(monthId, profess, districtBranchCode, "", "", "20003", client);
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
	//Ƿ����  ajax����Ӫ���������ݼ���
	@RequestMapping(params = "method=getOweRatebusiScBight")
	public String getOweRatebusiScBight(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			List line = new ArrayList();
			String busiScCode = (String)request.getParameter("busiScCode");
			String monthId = (String)request.getParameter("period");
			monthId= monthId == null||"".endsWith(monthId)?"0":monthId;
			String profess = (String)request.getParameter("profess");
			profess= profess == null||"".endsWith(profess)?"":profess;
			String client = (String)request.getParameter("client");
			client= client == null||"".endsWith(client)?"":client;
			if("0".equals(ProperUtil.getIsproceduGetData())){
				line = this.busiScService.getOweRateBusiScLine("",  profess,client, "", busiScCode, "".equals(monthId)||monthId==null?0:Integer.parseInt(monthId),"");
			}else{
				line = this.getTableDataService.getDashBoardRepYfPLine(monthId, profess, "", busiScCode, "", "20003", client);
			}
			
			SystemUtil.responseJSONString(JsonUtil.toJson(line), response);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}	
	
	//����Ƿ����  Ӫ��ҳ��ȫ���ݼ���
	@RequestMapping(params = "method=busiScPageAreaC")
	public String getCompanyPageAreaC(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			WKipConfigV wKipConfigV = this.getTableDataService.getWKipConfigVbyKpiId("20004", "branch") ;
			String districtBranchCode = (String)request.getParameter("districtBranchCode");
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
				 map = this.busiScService.getOweRateBusiScAreaC("", profess,client,"",Integer.parseInt(monthId),districtBranchCode);
			}else{
				map = this.getTableDataService.getDashBoardRepDituYfP(monthId, profess, districtBranchCode, "", "", "20004", client);
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
	//����Ƿ����  ajax����Ӫ���������ݼ���
	@RequestMapping(params = "method=getOweRatebusiScBightC")
	public String getOweRatebusiScBightC(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			List line = new ArrayList();
			String busiScCode = (String)request.getParameter("busiScCode");
			String monthId = (String)request.getParameter("period");
			monthId= monthId == null||"".endsWith(monthId)?"0":monthId;
			String profess = (String)request.getParameter("profess");
			profess= profess == null||"".endsWith(profess)?"":profess;
			String client = (String)request.getParameter("client");
			client= client == null||"".endsWith(client)?"":client;
			if("0".equals(ProperUtil.getIsproceduGetData())){
				line = this.busiScService.getOweRateBusiScLineC("",  profess,client, "", busiScCode, "".equals(monthId)||monthId==null?0:Integer.parseInt(monthId),"");
			}else{
				line = this.getTableDataService.getDashBoardRepYfPLine(monthId, profess, "", busiScCode, "", "20004", client);
			}
			
			SystemUtil.responseJSONString(JsonUtil.toJson(line), response);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}	
	
	//����Ƿ����  Ӫ��ҳ��ȫ���ݼ���
	@RequestMapping(params = "method=busiScPageAreaZ")
	public String getCompanyPageAreaZ(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			WKipConfigV wKipConfigV = this.getTableDataService.getWKipConfigVbyKpiId("20005", "branch") ;
			String districtBranchCode = (String)request.getParameter("districtBranchCode");
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
				 map = this.busiScService.getOweRateBusiScAreaZ("", profess,client,"",Integer.parseInt(monthId),districtBranchCode);
			}else{
				map = this.getTableDataService.getDashBoardRepDituYfP(monthId, profess, districtBranchCode, "", "", "20005", client);
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
	//����Ƿ����  ajax����Ӫ���������ݼ���
	@RequestMapping(params = "method=getOweRatebusiScBightZ")
	public String getOweRatebusiScBightZ(HttpServletRequest request, HttpServletResponse response,String period)
	{
		try
		{
			List line = new ArrayList();
			String busiScCode = (String)request.getParameter("busiScCode");
			String monthId = (String)request.getParameter("period");
			monthId= monthId == null||"".endsWith(monthId)?"0":monthId;
			String profess = (String)request.getParameter("profess");
			profess= profess == null||"".endsWith(profess)?"":profess;
			String client = (String)request.getParameter("client");
			client= client == null||"".endsWith(client)?"":client;
			if("0".equals(ProperUtil.getIsproceduGetData())){
				line = this.busiScService.getOweRateBusiScLineZ("",  profess,client, "", busiScCode, "".equals(monthId)||monthId==null?0:Integer.parseInt(monthId),"");
			}else{
				line = this.getTableDataService.getDashBoardRepYfPLine(monthId, profess, "", busiScCode, "", "20005", client);
			}
			
			SystemUtil.responseJSONString(JsonUtil.toJson(line), response);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}	
	private String getShowStyle(String kpiId){
		String m = dashTaggingService.getShowStyle(kpiId);
		return m;
	}
	
}
