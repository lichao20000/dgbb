package com.dglt.statement.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.deloitte.si.core.utils.DateUtils;

import com.dglt.comm.util.SqlUtil;
import com.dglt.comm.util.SystemUtil;
import com.dglt.comm.util.gson.JsonUtil;
import com.dglt.statement.service.LoadReportDataService;
import com.dglt.statement.vo.HisStatParamVo;
import com.dglt.statement.vo.ReportName;

@Controller
@RequestMapping("/loadReportData.do")
public class LoadReportData {
	@Resource(name = "loadReportDataService")
	private LoadReportDataService loadReportDataService;

	
	List l=new ArrayList();
	
	Map<String,String> search;
	
	// 获取报表参数
	@RequestMapping(params = "method=loadData")
	public String loadData(HttpServletRequest request,
			HttpServletResponse response, String period,HisStatParamVo paramVo) {
		try {
			String[] arry = {};
			String sqlNo = request.getParameter("sqlNo");
			String sql = "";
			
			if("90013".equals(sqlNo))
			{
				
				String businessTypeCode= request.getParameter("businessTypeCode");
				String reportFormStatusCode = request.getParameter("reportFormStatusCode");
				String customerTypeCode = request.getParameter("customerTypeCode");
				String fromDate = request.getParameter("fromDate");
				String product = request.getParameter("product");
				
				String dispatchform= request.getParameter("dispatchform");
				String formsender = request.getParameter("formsender");
				String formtitle = request.getParameter("formtitle");
				String toDate = request.getParameter("toDate");
				System.out.println("显示数据");
				System.out.println("显示数据");
				
				
				System.out.println("customerTypeCode="+customerTypeCode);
				System.out.println("businessTypeCode="+businessTypeCode);
				System.out.println("reportFormStatusCodee="+reportFormStatusCode);
				System.out.println("dispatchform="+dispatchform);
				System.out.println("formsender="+formsender);
				System.out.println("formtitle="+formtitle);
				System.out.println("product="+product);
				System.out.println();
				System.out.println();

				sql = ReportName.sql_90013;
				


				if(!(dispatchform==null||dispatchform.equals("")))
				{

					if(sqlNo.equals("90013"))
						sql+=" and  tfld.form_no like '%"+dispatchform+"%'";
					else
						sql+=" and  tfbd.form_no like '%"+dispatchform+"%'";
				}
				
				if(!(formsender==null||formsender.equals("")))
				{

					sql+=" and  ip.user_name like '%"+formsender+"%'";
				}
				
				if(!(formtitle==null||formtitle.equals("")))
				{

					sql+=" and  tfm.form_title like '%"+formtitle+"%'";
				}
				
				if(!(product==null||product.equals("")))
				{
					
					if(sqlNo.equals("90013"))
						sql+=" and  tfld.BSS_PRODUCT_NO like '%"+product+"%'";
					else
						sql+=" and  tfbd.BSS_PRODUCT_NO like '%"+product+"%'";
					
				}
				
				if(!(businessTypeCode==null||businessTypeCode.equals("")))
				{

					sql+=" and taev1.enum_value = '"+businessTypeCode+"'";
				}
				
				
				if(!(reportFormStatusCode==null||reportFormStatusCode.equals("")))
				{

					if(!reportFormStatusCode.equals("101"))
					{
						if(reportFormStatusCode.equals("11"))
						{
													
							String [] str=sql.split("where");
							
							sql=str[0]+", WFWIPARTICIPANT wp where "+str[1];
							
							sql+=" and tfm.prcs_inst_id=wp.processinstid and (wp.activitydefid = 'DraftActivity' and wp.currentstate = 10 and wp.partiintype = 'EXE')";
						}
						
						else
						{
							sql+=" and tfm.form_status = '"+reportFormStatusCode+"'";
						}
					}
				}
				
				if(!(customerTypeCode==null||customerTypeCode.equals("")))
				{
					sql+=" and taev2.enum_value = '"+customerTypeCode+"'";
					
					System.out.println("电路性质："+sql);
				}
				

				if(!(toDate==null||toDate.equals(""))&&!(fromDate==null||fromDate.equals("")))
				{
					
					if(toDate.length()<11)
					{
					  
					    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//小时
					    int minute=Calendar.getInstance().get(Calendar.MINUTE);//分           
					    int second=Calendar.getInstance().get(Calendar.SECOND);//秒 
						//String today = year + "-" + month + "-" + (day+1);
					    String time=hour+":"+minute+":"+second;
						toDate+=" "+time;						
					}
					
					if(fromDate.length()<11)
					{
					  
					    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//小时
					    int minute=Calendar.getInstance().get(Calendar.MINUTE);//分           
					    int second=Calendar.getInstance().get(Calendar.SECOND);//秒 
						//String today = year + "-" + month + "-" + (day+1);
					    String time=hour+":"+minute+":"+second;
					    fromDate+=" "+time;					
					}
					
				sql+=" and tfld.created_date between to_date('"+fromDate+"','yyyy-mm-dd hh24:mi:ss') and to_date('"+toDate+"','yyyy-mm-dd hh24:mi:ss')";

				
				}
				else
				{
					Date currDay = new Date();
					
					Calendar cal = Calendar.getInstance();
					
					cal.add(Calendar.MONTH, -1);
								
					Date lastDay = cal.getTime();
					
					fromDate=DateUtils.getDate(DateUtils.date_sdf
							.format(lastDay));
					
					toDate=DateUtils.getDate(DateUtils.date_sdf
							.format(currDay));
					
				    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//小时
				    int minute=Calendar.getInstance().get(Calendar.MINUTE);//分           
				    int second=Calendar.getInstance().get(Calendar.SECOND);//秒 
					//String today = year + "-" + month + "-" + (day+1);
				    String time=hour+":"+minute+":"+second;
					
				    fromDate+=" "+time;
					toDate+=" "+time;
					sql+=" and tfld.created_date between to_date('"+fromDate+"','yyyy-mm-dd hh24:mi:ss') and to_date('"+toDate+"','yyyy-mm-dd hh24:mi:ss')";
					
				}
				
				
				
				if(sqlNo.equals("90014"))
				{
					sql=sql.replaceAll("tfld", "tfbd");
				}

				
				sql+=" order by tfm.form_seq desc";
				
				arry = ReportName.arr_90013;
				
				
				 l = this.loadReportDataService.getData(sqlNo, sql, search, arry);
				
			}
			
			if("90014".equals(sqlNo))
			{
				
				String businessTypeCode= request.getParameter("businessTypeCode");
				String reportFormStatusCode = request.getParameter("reportFormStatusCode");
				String customerTypeCode = request.getParameter("customerTypeCode");
				String fromDate = request.getParameter("fromDate");
				String product = request.getParameter("product");
				
				String dispatchform= request.getParameter("dispatchform");
				String formsender = request.getParameter("formsender");
				String formtitle = request.getParameter("formtitle");
				String toDate = request.getParameter("toDate");
				System.out.println("显示数据");
				System.out.println("显示数据");
				
				
				System.out.println("customerTypeCode="+customerTypeCode);
				System.out.println("businessTypeCode="+businessTypeCode);
				System.out.println("reportFormStatusCodee="+reportFormStatusCode);
				System.out.println("dispatchform="+dispatchform);
				System.out.println("formsender="+formsender);
				System.out.println("formtitle="+formtitle);
				System.out.println("product="+product);
				System.out.println("fromDate="+fromDate);
				System.out.println("fromDate="+toDate);
				
				
				sql = ReportName.sql_90014;


				if(!(dispatchform==null||dispatchform.equals("")))
				{
					
					if(sqlNo.equals("90013"))
						sql+=" and  tfld.form_no like '%"+dispatchform+"%'";
					else
						sql+=" and  tfbd.form_no like '%"+dispatchform+"%'";
				}
				
				if(!(formsender==null||formsender.equals("")))
				{

					sql+=" and  ip.user_name like '%"+formsender+"%'";
				}
				
				if(!(formtitle==null||formtitle.equals("")))
				{

					sql+=" and  tfm.form_title like '%"+formtitle+"%'";
				}
				
				if(!(product==null||product.equals("")))
				{

					
					if(sqlNo.equals("90013"))
						sql+=" and  tfld.BSS_PRODUCT_NO like '%"+product+"%'";
					else
						sql+=" and  tfbd.BSS_PRODUCT_NO like '%"+product+"%'";
					
				}
				
				if(!(businessTypeCode==null||businessTypeCode.equals("")))
				{

					sql+=" and taev1.enum_value = '"+businessTypeCode+"'";
				}
				
				
				if(!(reportFormStatusCode==null||reportFormStatusCode.equals("")))
				{

					if(!reportFormStatusCode.equals("101"))
					{
						if(reportFormStatusCode.equals("11"))
						{
													
							String [] str=sql.split("where");
							
							sql=str[0]+", WFWIPARTICIPANT wp where "+str[1];
							
							sql+=" and tfm.prcs_inst_id=wp.processinstid and (wp.activitydefid = 'DraftActivity' and wp.currentstate = 10 and wp.partiintype = 'EXE')";
						}
						
						else
						{
							sql+=" and tfm.form_status = '"+reportFormStatusCode+"'";
						}
					}
				}
				
				if(!(customerTypeCode==null||customerTypeCode.equals("")))
				{
					sql+=" and taev2.enum_value = '"+customerTypeCode+"'";
					
					System.out.println("电路性质："+sql);
				}
				
				if(!(toDate==null||toDate.equals(""))&&!(fromDate==null||fromDate.equals("")))
				{
					
					if(toDate.length()<11)
					{
					  
					    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//小时
					    int minute=Calendar.getInstance().get(Calendar.MINUTE);//分           
					    int second=Calendar.getInstance().get(Calendar.SECOND);//秒 
						//String today = year + "-" + month + "-" + (day+1);
					    String time=hour+":"+minute+":"+second;
						toDate+=" "+time;						
					}
					
					if(fromDate.length()<11)
					{
					  
					    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//小时
					    int minute=Calendar.getInstance().get(Calendar.MINUTE);//分           
					    int second=Calendar.getInstance().get(Calendar.SECOND);//秒 
						//String today = year + "-" + month + "-" + (day+1);
					    String time=hour+":"+minute+":"+second;
					    fromDate+=" "+time;					
					}
					sql+=" and tfld.created_date between to_date('"+fromDate+"','yyyy-mm-dd hh24:mi:ss') and to_date('"+toDate+"','yyyy-mm-dd hh24:mi:ss')";

						
				}
				else
				{
					Date currDay = new Date();
					
					Calendar cal = Calendar.getInstance();
					
					cal.add(Calendar.MONTH, -1);
								
					Date lastDay = cal.getTime();
					
					fromDate=DateUtils.getDate(DateUtils.date_sdf
							.format(lastDay));
					
					toDate=DateUtils.getDate(DateUtils.date_sdf
							.format(currDay));
					
				    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//小时
				    int minute=Calendar.getInstance().get(Calendar.MINUTE);//分           
				    int second=Calendar.getInstance().get(Calendar.SECOND);//秒 
					//String today = year + "-" + month + "-" + (day+1);
				    String time=hour+":"+minute+":"+second;
					
				    fromDate+=" "+time;
					toDate+=" "+time;
					sql+=" and tfld.created_date between to_date('"+fromDate+"','yyyy-mm-dd hh24:mi:ss') and to_date('"+toDate+"','yyyy-mm-dd hh24:mi:ss')";
					
				}
				
				
				
				
				if(sqlNo.equals("90014"))
				{
					sql=sql.replaceAll("tfld", "tfbd");
				}
					


				
				sql+=" order by tfm.form_seq desc";
				arry = ReportName.arr_90014;
	
				 l = this.loadReportDataService.getData(sqlNo, sql, search, arry);
				
			}
			
			if("90015".equals(sqlNo))
			{
				
				sql = ReportName.sql_90015;
				String businessTypeCode= request.getParameter("businessTypeCode");
				String reportFormStatusCode = request.getParameter("reportFormStatusCode");
				String customerTypeCode = request.getParameter("customerTypeCode");
				String fromDate = request.getParameter("fromDate");
				String product = request.getParameter("product");
				
				String dispatchform= request.getParameter("dispatchform");
				String formsender = request.getParameter("formsender");
				String formtitle = request.getParameter("formtitle");
				String toDate = request.getParameter("toDate");
				System.out.println("显示数据");
				System.out.println("显示数据");
				
				

				
				
				sql = ReportName.sql_90015;

				String whereCondition ="";
				
				if(!(dispatchform==null||dispatchform.equals("")))
				{
					
					whereCondition+=" and  tfld.form_no like '%"+dispatchform+"%'";
	
				}
				
				if(!(formsender==null||formsender.equals("")))
				{

					whereCondition+=" and  ip.user_name like '%"+formsender+"%'";
				}
				
				if(!(formtitle==null||formtitle.equals("")))
				{

					whereCondition+=" and  tfm.form_title like '%"+formtitle+"%'";
				}
				
				if(!(product==null||product.equals("")))
				{

					whereCondition+=" and  tfld.BSS_PRODUCT_NO like '%"+product+"%'";

					
				}
				
				if(!(businessTypeCode==null||businessTypeCode.equals("")))
				{

					whereCondition+=" and taev3.enum_value = '"+businessTypeCode+"'";
				}
				
				
				if(!(reportFormStatusCode==null||reportFormStatusCode.equals("")))
				{

					if(!reportFormStatusCode.equals("101"))
					{
						if(reportFormStatusCode.equals("11"))
						{
													
							
							whereCondition+=" and (wp.activitydefid = 'DraftActivity' and wp.currentstate = 10 and wp.partiintype = 'EXE')";
						}
						
						else
						{
							whereCondition+=" and tfm.form_status = '"+reportFormStatusCode+"'";
						}
					}
				}
				
				if(!(customerTypeCode==null||customerTypeCode.equals("")))
				{
					whereCondition+=" and taev2.enum_value = '"+customerTypeCode+"'";
					
					System.out.println("电路性质："+whereCondition);
				}
				
				
				if(!(toDate==null||toDate.equals(""))&&!(fromDate==null||fromDate.equals("")))
				{
					
					if(toDate.length()<11)
					{
					  
					    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//小时
					    int minute=Calendar.getInstance().get(Calendar.MINUTE);//分           
					    int second=Calendar.getInstance().get(Calendar.SECOND);//秒 
						//String today = year + "-" + month + "-" + (day+1);
					    String time=hour+":"+minute+":"+second;
						toDate+=" "+time;						
					}
					
					if(fromDate.length()<11)
					{
					  
					    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//小时
					    int minute=Calendar.getInstance().get(Calendar.MINUTE);//分           
					    int second=Calendar.getInstance().get(Calendar.SECOND);//秒 
						//String today = year + "-" + month + "-" + (day+1);
					    String time=hour+":"+minute+":"+second;
					    fromDate+=" "+time;					
					}
					
				whereCondition+=" and tfld.created_date between to_date('"+fromDate+"','yyyy-mm-dd hh24:mi:ss') and to_date('"+toDate+"','yyyy-mm-dd hh24:mi:ss')";

						
	
				}
				
				else
				{
					Date currDay = new Date();
					
					Calendar cal = Calendar.getInstance();
					
					cal.add(Calendar.MONTH, -1);
								
					Date lastDay = cal.getTime();
					
					fromDate=DateUtils.getDate(DateUtils.date_sdf
							.format(lastDay));
					
					toDate=DateUtils.getDate(DateUtils.date_sdf
							.format(currDay));
					
				    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//小时
				    int minute=Calendar.getInstance().get(Calendar.MINUTE);//分           
				    int second=Calendar.getInstance().get(Calendar.SECOND);//秒 
					//String today = year + "-" + month + "-" + (day+1);
				    String time=hour+":"+minute+":"+second;
					
				    fromDate+=" "+time;
					toDate+=" "+time;
					whereCondition+=" and tfld.created_date between to_date('"+fromDate+"','yyyy-mm-dd hh24:mi:ss') and to_date('"+toDate+"','yyyy-mm-dd hh24:mi:ss')";		
					
				}
			
				if(!whereCondition.equals(""))
				{
					sql=sql.replaceAll("and 1=1", whereCondition);
				}
				
				
				arry = ReportName.arr_90015;
				
				
				 l = this.loadReportDataService.getData(sqlNo, sql, null, arry);
				
			}
			
			if("90016".equals(sqlNo))
			{
				
				sql = ReportName.sql_90016;
				String businessTypeCode= request.getParameter("businessTypeCode");
				String reportFormStatusCode = request.getParameter("reportFormStatusCode");
				String customerTypeCode = request.getParameter("customerTypeCode");
				String fromDate = request.getParameter("fromDate");
				String product = request.getParameter("product");
				
				String dispatchform= request.getParameter("dispatchform");
				String formsender = request.getParameter("formsender");
				String formtitle = request.getParameter("formtitle");
				String toDate = request.getParameter("toDate");
				System.out.println("显示数据");
				System.out.println("显示数据");
				
				
				System.out.println("customerTypeCode="+customerTypeCode);
				System.out.println("businessTypeCode="+businessTypeCode);
				System.out.println("reportFormStatusCodee="+reportFormStatusCode);
				System.out.println("dispatchform="+dispatchform);
				System.out.println("formsender="+formsender);
				System.out.println("formtitle="+formtitle);
				System.out.println("product="+product);
				System.out.println("fromDate="+fromDate);
				System.out.println("fromDate="+toDate);
				System.out.println("显示数据");
				System.out.println("显示数据");
				

				
				
				sql = ReportName.sql_90016;

				String whereCondition ="";
				
				if(!(dispatchform==null||dispatchform.equals("")))
				{

					whereCondition+=" and  tfld.form_no like '%"+dispatchform+"%'";
	
				}
				
				if(!(formsender==null||formsender.equals("")))
				{

					whereCondition+=" and  ip.user_name like '%"+formsender+"%'";
				}
				
				if(!(formtitle==null||formtitle.equals("")))
				{

					whereCondition+=" and  tfm.form_title like '%"+formtitle+"%'";
				}
				
				if(!(product==null||product.equals("")))
				{

					whereCondition+=" and  tfld.BSS_PRODUCT_NO like '%"+product+"%'";

					
				}
				
				if(!(businessTypeCode==null||businessTypeCode.equals("")))
				{

					whereCondition+=" and taev2.enum_value = '"+businessTypeCode+"'";
				}
				
				
				if(!(reportFormStatusCode==null||reportFormStatusCode.equals("")))
				{

					if(!reportFormStatusCode.equals("101"))
					{
						if(reportFormStatusCode.equals("11"))
						{
													
							
							whereCondition+=" and tfm.prcs_inst_id=ww.processinstid and (ww.activitydefid = 'DraftActivity' and ww.currentstate = 10 and ww.partiintype = 'EXE')";
						}
						
						else
						{
							whereCondition+=" and tfm.form_status = '"+reportFormStatusCode+"'";
						}
					}
				}
				
				if(!(customerTypeCode==null||customerTypeCode.equals("")))
				{
					whereCondition+=" and taev3.enum_value = '"+customerTypeCode+"'";
					
					System.out.println("电路性质："+whereCondition);
				}
				
				
				if(!(toDate==null||toDate.equals(""))&&!(fromDate==null||fromDate.equals("")))
				{
					
					if(toDate.length()<11)
					{
					  
					    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//小时
					    int minute=Calendar.getInstance().get(Calendar.MINUTE);//分           
					    int second=Calendar.getInstance().get(Calendar.SECOND);//秒 
						//String today = year + "-" + month + "-" + (day+1);
					    String time=hour+":"+minute+":"+second;
						toDate+=" "+time;						
					}
					
					if(fromDate.length()<11)
					{
					  
					    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//小时
					    int minute=Calendar.getInstance().get(Calendar.MINUTE);//分           
					    int second=Calendar.getInstance().get(Calendar.SECOND);//秒 
						//String today = year + "-" + month + "-" + (day+1);
					    String time=hour+":"+minute+":"+second;
					    fromDate+=" "+time;					
					}
				whereCondition+=" and tfld.created_date between to_date('"+fromDate+"','yyyy-mm-dd hh24:mi:ss') and to_date('"+toDate+"','yyyy-mm-dd hh24:mi:ss')";

						
	
				}
				
				else
				{
					Date currDay = new Date();
					
					Calendar cal = Calendar.getInstance();
					
					cal.add(Calendar.MONTH, -1);
								
					Date lastDay = cal.getTime();
					
					fromDate=DateUtils.getDate(DateUtils.date_sdf
							.format(lastDay));
					
					toDate=DateUtils.getDate(DateUtils.date_sdf
							.format(currDay));
					
				    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//小时
				    int minute=Calendar.getInstance().get(Calendar.MINUTE);//分           
				    int second=Calendar.getInstance().get(Calendar.SECOND);//秒 
					//String today = year + "-" + month + "-" + (day+1);
				    String time=hour+":"+minute+":"+second;
					
				    fromDate+=" "+time;
					toDate+=" "+time;
					whereCondition+=" and tfld.created_date between to_date('"+fromDate+"','yyyy-mm-dd hh24:mi:ss') and to_date('"+toDate+"','yyyy-mm-dd hh24:mi:ss')";
					
				}
				
			
				if(!whereCondition.equals(""))
				{
					sql=sql.replaceAll("and 1=1", whereCondition);
				}
				
				
				arry = ReportName.arr_90016;
				
				
				 l = this.loadReportDataService.getData(sqlNo, sql, null, arry);
				
			}
			
			
			
			if ("3001".equals(sqlNo)) {
				sql = ReportName.sql_3001;
				arry = ReportName.arr_3001;
				
				 l = this.loadReportDataService.getData(sqlNo, sql, null, arry);
			}
			
			
			//售前支撑评分
			if("3002".equals(sqlNo)){
				//根据查询条件拼接SQL
				sql = SqlUtil.connectSqlForPreSale(paramVo) ;
				
				arry = ReportName.arr_3002;
				
				 l = this.loadReportDataService.getData(sqlNo, sql, null, arry);
				
			}
			
			
			if ("90017".equals(sqlNo)) {
				
				String form_seq= request.getParameter("form_seq");
				String formsender = request.getParameter("formsender");
				String formtitle = request.getParameter("formtitle");
				
				String bizTypeCode = request.getParameter("bizTypeCode");
				String lanchAreaCode = request.getParameter("lanchAreaCode");
				String blongTeamCode = request.getParameter("blongTeamCode");
				String applyFormStatusCode = request.getParameter("applyFormStatusCode");
				String cstFormStatusCode = request.getParameter("cstFormStatusCode");
				String fromDate = request.getParameter("fromDate");
				String toDate = request.getParameter("toDate");
				System.out.println("显示数据");
				System.out.println("显示数据");
				
				

				System.out.println("reportFormStatusCode="+applyFormStatusCode);
				System.out.println("cstFormStatusCode="+cstFormStatusCode);
			
				sql = ReportName.sql_90017;
				
				String whereCondition ="";
				
				if(!(form_seq==null||form_seq.equals("")))
				{

					whereCondition+=" and  tfm1.form_seq like '%"+form_seq+"%' or tfm2.form_seq like '%"+form_seq+"%' ";
	
				}
				
				if(!(formsender==null||formsender.equals("")))
				{

					whereCondition+=" and  ip.user_name like '%"+formsender+"%'";
				}
				
				if(!(formtitle==null||formtitle.equals("")))
				{

					whereCondition+=" and  tfm1.form_title like '%"+formtitle+"%' or tfm1.form_title like '%"+formtitle+"%' ";
				}
				

				
				if(!(bizTypeCode==null||bizTypeCode.equals("")))
				{

					//whereCondition+=" and taev2.enum_value = '"+bizTypeCode+"'";
				}
				
				
				if(!(applyFormStatusCode==null||applyFormStatusCode.equals("")))
				{

					
					
					if(!applyFormStatusCode.equals("101"))
					{
						if(applyFormStatusCode.equals("11"))
						{
														
							sql=sql.replace("2 = 2", " t.apply_form_status='11'");
						}
						
						else
						{
							whereCondition+=" and tfm1.form_status = '"+applyFormStatusCode+"'";
						}
					}
				}
				
				if(!(cstFormStatusCode==null||cstFormStatusCode.equals("")))
				{

					
					
					if(!cstFormStatusCode.equals("101"))
					{
						if(cstFormStatusCode.equals("11"))
						{
														
							sql=sql.replace("3 = 3", " t.cst_form_status='11'");
						}
						
						else
						{
							whereCondition+=" and tfm2.form_status = '"+cstFormStatusCode+"'";
						}
					}
				}
				
				if(!(lanchAreaCode==null||lanchAreaCode.equals("")))
				{
					whereCondition+=" and tfp.lanch_area = '"+lanchAreaCode+"'";
					
				}
				
				if(!(blongTeamCode==null||blongTeamCode.equals("")))
				{
					whereCondition+=" and tfp.prj_blong_team = '"+blongTeamCode+"'";
					
				}
				
				
				if(!(toDate==null||toDate.equals(""))&&!(fromDate==null||fromDate.equals("")))
				{
					
					
					System.out.println(toDate);
					System.out.println(toDate.length());
					if(toDate.length()<11)
					{
					  
					    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//小时
					    int minute=Calendar.getInstance().get(Calendar.MINUTE);//分           
					    int second=Calendar.getInstance().get(Calendar.SECOND);//秒 
						//String today = year + "-" + month + "-" + (day+1);
					    String time=hour+":"+minute+":"+second;
						toDate+=" "+time;						
					}
					
					if(fromDate.length()<11)
					{
					  
					    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//小时
					    int minute=Calendar.getInstance().get(Calendar.MINUTE);//分           
					    int second=Calendar.getInstance().get(Calendar.SECOND);//秒 
						//String today = year + "-" + month + "-" + (day+1);
					    String time=hour+":"+minute+":"+second;
					    fromDate+=" "+time;					
					}
					
					whereCondition+=" and tfm1.send_time between to_date('"+fromDate+"','yyyy-mm-dd hh24:mi:ss') and to_date('"+toDate+"','yyyy-mm-dd hh24:mi:ss')";
	
	
				}
				
				else
				{
					Date currDay = new Date();
					
					Calendar cal = Calendar.getInstance();
					
					cal.add(Calendar.MONTH, -1);
								
					Date lastDay = cal.getTime();
					
					fromDate=DateUtils.getDate(DateUtils.date_sdf
							.format(lastDay));
					
					toDate=DateUtils.getDate(DateUtils.date_sdf
							.format(currDay));
					
				    int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//小时
				    int minute=Calendar.getInstance().get(Calendar.MINUTE);//分           
				    int second=Calendar.getInstance().get(Calendar.SECOND);//秒 
					//String today = year + "-" + month + "-" + (day+1);
				    String time=hour+":"+minute+":"+second;
					
				    fromDate+=" "+time;
					toDate+=" "+time;
					whereCondition+=" and tfm1.send_time between to_date('"+fromDate+"','yyyy-mm-dd hh24:mi:ss') and to_date('"+toDate+"','yyyy-mm-dd hh24:mi:ss')";
					
				}
				
			
				if(!whereCondition.equals(""))
				{
					sql=sql.replaceAll("and 1 = 1", whereCondition);
				}
				
				
				arry = ReportName.arr_90017;
				
				 l = this.loadReportDataService.getData(sqlNo, sql, null, arry);
			}
			
			
			SystemUtil.responseJSONString(JsonUtil.toJson(l), response);
			
//			
//			HashMap<String, String> h = new HashMap<String, String>();
//			
//			System.out.println("al.size()="+l.size());
//			
//			h.put(arry[0],"查询结果记录：");
//			h.put(arry[1],l.size()+"");
//			
//			SystemUtil.responseJSONString(JsonUtil.toJson(l), response);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
