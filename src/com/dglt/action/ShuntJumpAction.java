package com.dglt.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dglt.base.util.ProperUtil;
import com.dglt.bb.pojo.DashTagging;
import com.dglt.bb.pojo.DashTaggingReply;
import com.dglt.bb.pojo.WKipConfigV;
import com.dglt.bb.service.DashTaggingService;
import com.dglt.bb.service.GetTableDataService;
import com.dglt.bb.service.IncomeBudgetBizcsService;
import com.dglt.bb.service.IncomeBudgetService;
import com.dglt.bb.service.KipManagerService;
import com.dglt.bb.service.ManagerService;
import com.dglt.bb.service.ProfitBudgetBizcsService;
import com.dglt.bb.service.ProfitBudgetService;
import com.dglt.bb.service.Turnover2GService;
import com.dglt.bb.service.Turnover3GService;
import com.dglt.bb.service.Turnover2GBizcsService;
import com.dglt.bb.service.Turnover3GBizcsService;
import com.dglt.comm.base.BaseAction;
import com.dglt.comm.base.Globals;
import com.dglt.comm.util.SystemUtil;
import com.dglt.comm.util.gson.JsonUtil;
@Controller
@RequestMapping("/shuntJumpAction.do")  
public class ShuntJumpAction   extends BaseAction{
	/*@Resource(name = "Turnover2GService")
	private Turnover2GService  turnover2GService ;
	@Resource(name = "Turnover3GService")
	private Turnover3GService  turnover3GService ;
	@Resource(name = "Turnover2GBizcsService")
	private Turnover2GBizcsService  turnover2GBizcsService ;
	@Resource(name = "Turnover3GBizcsService")
	private Turnover3GBizcsService  turnover3GBizcsService ;*/
	@Resource(name = "GetTableDataService")
	private GetTableDataService  getTableDataService ;
/*	@Resource(name = "IncomeBudgetBizcsService")
	private IncomeBudgetBizcsService  incomeBudgetBizcsService ;
	@Resource(name = "IncomeBudgetService")
	private IncomeBudgetService  incomeBudgetService ;
	@Resource(name = "ProfitBudgetBizcsService")
	private ProfitBudgetBizcsService  profitBudgetBizcsService ;
	@Resource(name = "ProfitBudgetService")
	private ProfitBudgetService  profitBudgetService ;*/
	@Resource(name = "DashTaggingService")
	private DashTaggingService  dashTaggingService ;
	@Resource(name = "managerService")
	private ManagerService  managerService ;
	@Resource(name = "KipManagerService")
	private KipManagerService kipManagerService ;
	
	
	//������תҳ�涼�����
	@RequestMapping(params = "method=getNextPage")
	public String getNextPage(HttpServletRequest request, HttpServletResponse response)
	{
		 String returnPage="" ;
		 //��ͼ������
		 String flexName ;
		 //��ͼ����
		 String mapName ="" ;
		try
		{
		 //�ڼ�
		String	period = (String)request.getParameter("period");
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
		String	branch  = (String)request.getParameter("branch");
		//Ӫҵ��	
		String	bizcs  = (String)request.getParameter("bizcs");
		String showStyle = this.getShowStyle(kpid);
		request.setAttribute("period", period);	
		request.setAttribute("profess", profess);
		request.setAttribute("client", client);	
		request.setAttribute("productCode", productCode);
		request.setAttribute("kpid", kpid);	
		request.setAttribute("myType", myType);
		request.setAttribute("branch", branch);	
		request.setAttribute("bizcs", bizcs);
		request.setAttribute("showStyle", showStyle);
		
		//��ҳ����ת���ֹ�˾��ʱ��  �ֹ�˾��ֵû�У�Ĭ��Ϊ��ݸ�ĵ�ͼ
		 if(branch==null||"".equals(branch)){ 
			 branch="FDW";
		 }
		WKipConfigV wKipConfigV = this.getTableDataService.getWKipConfigVbyKpiId(kpid, myType) ;
		request.setAttribute("wKipConfigV", wKipConfigV);
		//��� wKipConfigV ûֵ ��������ҳ��  ��ʾҪ����
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
		return returnPage ;
	}	

	//ͨ�õõ��������ݼ���
	@RequestMapping(params = "method=turnover2GLine")
	public String getTurnover2GLine(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			List line = new ArrayList();
			String period  =(String)request.getParameter("period") ;
			String profess =(String)request.getParameter("profess") ;
			//�����ȫרҵ  ����
			if("99".equals(profess)){
				profess="";
			}
			String productCode =(String)request.getParameter("productCode") ;
			String branch  =(String)request.getParameter("branch") ;
			String bizcs =(String)request.getParameter("bizcs") ;
			String managerNo =(String)request.getParameter("managerNo") ;
			String myType =(String)request.getParameter("myType") ;
			String	kpid  = (String)request.getParameter("kpid");
			String client =(String)request.getParameter("client"); 
			String managerCode =(String)request.getParameter("managerNo");
			int monthId=0 ;
			
			if("".equals(period)||period==null){
				monthId=0 ; 
			}else{
				monthId=Integer.parseInt(period) ;
			}
			
			if("1".equals(ProperUtil.getIsproceduGetData())){
			if("branch".endsWith(myType)){
				 line = this.getTableDataService.getDashBoardRepQufenPLine(period, profess, branch, bizcs, productCode, kpid, client);
			}else if("bizcs".endsWith(myType)){
				line = this.getTableDataService.getDashBoardRepYfPLine(period, profess, branch, bizcs, productCode, kpid, client);
			}else if("manager".equals(myType)){
				line = this.getTableDataService.getDashBoardRepxsjlPLine(period, profess, branch, bizcs,managerNo, productCode, kpid, client);
			   //if("20003".equals(kpid)){
			   // 	 line = this.managerService.getOweRateManagerLine(period, profess, client, bizcs, bizcs, Integer.parseInt(period), branch, managerNo);
			   // }else if("20004".equals(kpid)){
			   // 	 line = this.managerService.getOweRateManagerLineC(period, profess, client, bizcs, bizcs, Integer.parseInt(period), branch, managerNo);
			   //  }else if("20005".equals(kpid)){
			   // 	 line = this.managerService.getOweRateManagerLineZ(period, profess, client, bizcs, bizcs, Integer.parseInt(period), branch, managerNo);
			   //  } else {
			   // 	 line= this.kipManagerService.getLine(period, profess, branch, bizcs, monthId, productCode, client, managerCode, myType, kpid) ;
			   //  }
			}
			}else{
				if("20003".equals(kpid)){
			    	 line = this.managerService.getOweRateManagerLine(period, profess, client, bizcs, bizcs, Integer.parseInt(period), branch, managerNo);
			     }else if("20004".equals(kpid)){
			    	 line = this.managerService.getOweRateManagerLineC(period, profess, client, bizcs, bizcs, Integer.parseInt(period), branch, managerNo);
			     }else if("20005".equals(kpid)){
			    	 line = this.managerService.getOweRateManagerLineZ(period, profess, client, bizcs, bizcs, Integer.parseInt(period), branch, managerNo);
			     } else {
			    	 line= this.kipManagerService.getLine(period, profess, branch, bizcs, monthId, productCode, client, managerCode, myType, kpid) ;
			     }
				
			}
			SystemUtil.responseJSONString(JsonUtil.toJson(line), response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		 }
		return null;
	}	
	
	//��ͼ���ݼ���
	
	@RequestMapping(params = "method=turnover2GMap")
	public String getTurnover2GMap(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			List maps = new ArrayList();
			String period  =(String)request.getParameter("period") ;
			String profess =(String)request.getParameter("profess") ;
			//�����ȫרҵ  ����
			if("99".equals(profess)){
				profess="";
			}
			String branch  =(String)request.getParameter("branch") ;
			String bizcs =(String)request.getParameter("bizcs") ;
			String productCode =(String)request.getParameter("productCode") ;
			String myType =(String)request.getParameter("myType") ;
			String	kpid  = (String)request.getParameter("kpid");
			String client =(String)request.getParameter("client"); 	
			
			if("1".equals(ProperUtil.getIsproceduGetData())){
				if("branch".equals(myType)){
		    		   //ͨ�õõ���ͼ
		    		   	  //ͨ������ �ķ�ʽ�õ���ͼ����  
		    		 maps=this.getTableDataService.getDashBoardRepDituQufenP(period, profess, branch, bizcs, productCode, kpid, client);
			    	} else if("bizcs".equals(myType)){
			    			maps=this.getTableDataService.getDashBoardRepDituYfP(period, profess, branch, bizcs, productCode, kpid, client);
			    	}  else if("manager".equals(myType)){
			    		//�õ���ͼ
			    		 maps = this.getTableDataService.getDashBoardRepDituxsjlP(period, profess, branch, bizcs, productCode, kpid, client);
			    	}
			}else { 
			    	 if("20003".equals(kpid)){
				    	 maps = this.managerService.getOweRateManagerArea(period, profess, client, bizcs, Integer.parseInt(period), branch, bizcs);
				     }else if("20004".equals(kpid)){
				    	 maps = this.managerService.getOweRateManagerAreaC(period, profess, client, bizcs, Integer.parseInt(period), branch, bizcs);
				     }else if("20005".equals(kpid)){
				    	 maps = this.managerService.getOweRateManagerAreaZ(period, profess, client, bizcs, Integer.parseInt(period), branch, bizcs);
				     }else{
				    	 if("branch".equals(myType)){
				    		   //ͨ�õõ���ͼ
				    		 maps = this.kipManagerService.getMap(period, profess, branch, bizcs, productCode, client, myType, kpid);
				    		  //ͨ������ �ķ�ʽ�õ���ͼ����  
				    		// maps=this.getTableDataService.getDashBoardRepDituQufenP(period, profess, branch, bizcs, productCode, kpid, client);
					    	} else if("bizcs".equals(myType)){
					    		
					    		maps = this.kipManagerService.getMap(period, profess, branch, bizcs, productCode, client, myType, kpid);
					    		//maps=this.getTableDataService.getDashBoardRepDituYfP(period, profess, branch, bizcs, productCode, kpid, client);
					    	}  else if("manager".equals(myType)){
					    		//�õ���ͼ
					    		maps=this.kipManagerService.getPie(period, profess, branch, bizcs, productCode, client, myType, kpid) ;
					    	}
				     }
			}		      
			SystemUtil.responseJSONString(JsonUtil.toJson(maps), response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		 }
		return null; 
	}	

	//ͨ�õõ�������ݼ���
	@RequestMapping(params = "method=getTableData")
	public String getTurnover2GTable(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			List tables = new ArrayList();
			String period  =(String)request.getParameter("period") ;
			String profess =(String)request.getParameter("profess") ;
			String managerNo =(String)(request.getParameter("managerNo")==null?"":request.getParameter("managerNo") );
			//�����ȫרҵ  ����
			if("99".equals(profess)){
				profess="";
			}
			String branch  =(String)request.getParameter("branch") ;
			String bizcs =(String)request.getParameter("bizcs") ;
			String productCode =(String)request.getParameter("productCode") ;
			//String myType =(String)request.getParameter("myType") ;
			String	kpid  = (String)request.getParameter("kpid");
		    String client = (String)request.getParameter("client");
		    String myType =(String)request.getParameter("myType") ;
			tables=	this.getTableDataService.getTableData(period, profess, branch, bizcs,managerNo, productCode, kpid, client)  ;
			if("branch".equals(myType)){
	    		    
				tables=	this.getTableDataService.getTableData(period, profess, branch, bizcs,managerNo, productCode, kpid, client);
		    	} else if("bizcs".equals(myType)){
		    		tables=this.getTableDataService.getTableDataYf(period, profess, branch, bizcs, managerNo, productCode, kpid, client);
		    	}  else if("manager".equals(myType)){
		    		tables=this.getTableDataService.getTableDataxsjl(period, profess, branch, bizcs, managerNo, productCode, kpid, client);
		    	}
			SystemUtil.responseJSONString(JsonUtil.toJson(tables), response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		 }
		return null;
	}	
	
	//��ѯ��ע
	@RequestMapping(params ="method=taggingQuery")
	public String taggingQuery(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			List queryResult = new ArrayList();
			String monthId  =(String)request.getParameter("monthId") ;
			String userName =(String)request.getParameter("userName") ;
			String productCode =(String)request.getParameter("productCode") ;
			String clientCode =(String)request.getParameter("clientCode") ;
			String spId =(String)request.getParameter("spId") ;
			String kpiId =(String)request.getParameter("kpiId") ;	
			queryResult =this.dashTaggingService.getDashTaggingByCon( kpiId,  monthId, spId,  clientCode,  productCode,  userName) ;
			//tables=	this.getTableDataService.getTableData(period, profess, branch, bizcs, productCode, kpid, clentId)  ;
			SystemUtil.responseJSONString(JsonUtil.toJson(queryResult), response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		 }
		return null;
	}	
	
	
	//�����ע
	@RequestMapping(params ="method=taggingInsert")
	public String taggingInsert(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			String date =(String)request.getParameter("data") ;  
			 date =date.replace("[", "").replace("]", "") ;
			DashTagging dashTagging   =(DashTagging)JsonUtil.fromJson(date,DashTagging.class) ;
			dashTagging =this.dashTaggingService.insertDashTagging(dashTagging) ; 
			SystemUtil.responseJSONString(JsonUtil.toJson(dashTagging), response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		 }
		return null;
	}		
	
	//�����ע�ظ�
	
	@RequestMapping(params ="method=taggingReplyInsert")
	public String taggingReplyInsert(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{			
	    	java.util.Date date = new Date();
	    	 Calendar c1 = Calendar.getInstance();
	         c1.setTime(date);
	         SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");    
			DashTaggingReply	dashTaggingReply = new DashTaggingReply();
			String taggingId  = (String)request.getParameter("taggingId") ;
			String replyer =(String)request.getParameter("replyer")==null?"ϵͳ����Ա":request.getParameter("replyer") ;
			String content =(String)request.getParameter("replyContent") ;
			dashTaggingReply.setCreationDate(format2.format(c1.getTime()));
			dashTaggingReply.setTaggingId(taggingId);
			dashTaggingReply.setReplyer(replyer);
			dashTaggingReply.setContent(content);
			dashTaggingReply =this.dashTaggingService.insertDashTaggingReply(dashTaggingReply) ; 	
			SystemUtil.responseJSONString(JsonUtil.toJson(dashTaggingReply), response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		 }
		return null;
	}	
	
 //	��ѯ��ע�ظ�
	@RequestMapping(params ="method=taggingReplyQuery")
	public String taggingReplyQuery(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{			
			List	dashTaggingReplyList =  new ArrayList();
			String taggingId  =(String)request.getParameter("taggingId") ;
			dashTaggingReplyList =this.dashTaggingService.getDashTaggingReplyByTagId(taggingId) ; ; 
			SystemUtil.responseJSONString(JsonUtil.toJson(dashTaggingReplyList), response);
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
