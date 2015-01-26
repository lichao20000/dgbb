package com.dglt.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.deloitte.si.core.utils.DateUtils;

import com.dglt.base.util.ClassUtil;
import com.dglt.bb.pojo.ExcelTemp;
import com.dglt.bb.service.GetTableDataService;
import com.dglt.comm.base.BaseAction;
import com.dglt.comm.base.BaseDAO;
import com.dglt.comm.base.Globals;
import com.dglt.comm.util.DateTimeUtil;
import com.dglt.comm.util.SqlUtil;
import com.dglt.statement.service.GetFormDataService;
import com.dglt.statement.vo.FormName;
import com.dglt.statement.vo.HisStatParamVo;
import com.dglt.statement.vo.ReportName;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
@Controller
@RequestMapping("/exportExcel.do")  
public class ExportController extends BaseAction {

	@Resource(name = "ExportService")
	private com.dglt.bb.service.ExportService exportService;
	@Resource(name = "getFormDataService")
	private GetFormDataService getFormDataService;
	@Resource(name = "GetTableDataService")
	private GetTableDataService  getTableDataService ;
	@Resource(name = "baseDAO")
	private BaseDAO  baseDAO ;
	
	 
    @RequestMapping(params = "method=export")    
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,HisStatParamVo paramVo)     
    throws Exception {    
    	String path =request.getRealPath("/");
    	
    	String dgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
    	String columnsStr=	(String)request.getParameter("columns") ;
    	String[] arry = {};
		String a = request.getParameter("sql");
		String monthId = request.getParameter("monthId");
		String text = request.getParameter("reportid");
		String categoryId = request.getParameter("categorytext");
		String yearId = request.getParameter("yearId");
		String styletext = request.getParameter("styletext");
		String cttext = request.getParameter("cttext");
		String unit = request.getParameter("unit");
		String sqlNo = "";
		String sql = "";
		if ("sql1".equals(a)) {
			sql = FormName.sql1;
			arry = FormName.arr1;
			sqlNo = "1";
		}
		if ("sql2".equals(a)) {
			sql = FormName.sql2;
			arry = FormName.arr2;
			sqlNo = "2";
		}
		if ("sql3".equals(a)) {
			sql = FormName.sql3;
			arry = FormName.arr3;
			sqlNo = "3";
		}
		if ("sql4".equals(a)) {
			sql = FormName.sql4;
			arry = FormName.arr4;
			sqlNo = "4";
		}
		if ("sql5".equals(a)) {
			sql = FormName.sql5;
			arry = FormName.arr5;
			sqlNo = "5";
		}
		if ("sql6".equals(a)) {
			sql = FormName.sql6;
			arry = FormName.arr6;
			sqlNo = "6";
		}
		if ("sql7".equals(a)) {
			sql = FormName.sql7;
			arry = FormName.arr7;
			sqlNo = "7";
		}
		if ("sql8".equals(a)) {
			sql = FormName.sql8;
			arry = FormName.arr8;
			sqlNo = "8";
		}
		if ("sql9".equals(a)) {
			sql = FormName.sql9;
			arry = FormName.arr9;
			sqlNo = "9";
		}
		if ("sql10".equals(a)) {
			sql = FormName.sql10;
			arry = FormName.arr10;
			sqlNo = "10";
		}
		if ("sql11".equals(a)) {
			sql = FormName.sql11;
			arry = FormName.arr11;
			sqlNo = "11";
		}
		if ("sql12".equals(a)) {
			sql = FormName.sql12;
			arry = FormName.arr12;
			sqlNo = "12";
		}
		if ("sql13".equals(a)) {
			sql = FormName.sql13;
			arry = FormName.arr13;
			sqlNo = "13";
		}
		//售前支撑评分
		if("sql3002".equals(a)){
			//业务类型
			String bizTypeCode = request.getParameter("bizTypeCode");
			//工单类型
			String formTypeCode = request.getParameter("formTypeCode");
			//时间类型
			String timeType = request.getParameter("timeType");
			//开始时间
			String fromDate = request.getParameter("fromDate");
			//结束时间
			String toDate = request.getParameter("toDate");
			
			//根据查询条件拼接SQL
			sql = SqlUtil.connectSqlForPreSale(paramVo) ;
			arry = ReportName.arr_3002;
			sqlNo = "3002";
		}
		
		
		if("sql90013".equals(a))
		{
			
			sql = ReportName.sql_90013;
			sqlNo = "90013";
	
			String businessTypeCode= request.getParameter("businessTypeCode");
			String reportFormStatusCode = request.getParameter("reportFormStatusCode");
			String customerTypeCode = request.getParameter("customerTypeCode");
			String fromDate = request.getParameter("fromDate");
			String product = request.getParameter("Product");
			
			String dispatchform= request.getParameter("Dispatchform");
			String formsender = request.getParameter("Formsender");
			String formtitle = request.getParameter("Formtitle");
			String toDate = request.getParameter("toDate");

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

			
		}
		
		
		if("sql90014".equals(a))
		{
			sql = ReportName.sql_90014;
			sqlNo = "90014";
	

			String businessTypeCode= request.getParameter("businessTypeCode");
			String reportFormStatusCode = request.getParameter("reportFormStatusCode");
			String customerTypeCode = request.getParameter("customerTypeCode");
			String fromDate = request.getParameter("fromDate");
			String product = request.getParameter("Product");
			
			String dispatchform= request.getParameter("Dispatchform");
			String formsender = request.getParameter("Formsender");
			String formtitle = request.getParameter("Formtitle");
			String toDate = request.getParameter("toDate");
			


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
			sqlNo = "90014";
			
			System.out.println("90014="+sql);
			
			
		}
		
		if("sql90015".equals(a))
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

			sql = ReportName.sql_90015;
			sqlNo = "90015";

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
			
			
		}
		
		
		
		if("sql90016".equals(a))
		{
			
			sql = ReportName.sql_90016;
			sqlNo = "90016";
			String businessTypeCode= request.getParameter("businessTypeCode");
			String reportFormStatusCode = request.getParameter("reportFormStatusCode");
			String customerTypeCode = request.getParameter("customerTypeCode");
			String fromDate = request.getParameter("fromDate");
			String product = request.getParameter("product");
			
			String dispatchform= request.getParameter("dispatchform");
			String formsender = request.getParameter("formsender");
			String formtitle = request.getParameter("formtitle");
			String toDate = request.getParameter("toDate");

			

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
			sqlNo = "90016";

		}
		
		if("sql90017".equals(a))
		{

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
			sqlNo = "90017";

		}
		
		List l = this.getFormDataService.getData(sqlNo, "", "", "", "",
				arry, sql, monthId, text,categoryId,yearId ,styletext,
				cttext);
		
		
		
		List l1 =  null ;
		 if("sql3".equals(a)){
             l=this.getFormDataService.doJkList(l);
		 }
		 if ("sql10".equals(a)){
			    sql = FormName.sql9;
				arry = FormName.arr9;
				sqlNo = "9";
			l1 = this.getFormDataService.getData(sqlNo, "", "", "", "",
						arry, sql, monthId, text,categoryId,yearId ,styletext,
						cttext);	
			 
			 l=this.getFormDataService.doKHLYList(l, l1, text) ;
			 
		 }
		 
	 	 ExcelTemp t = Globals.getExcelTempByCode(a) ;
	 	String time = DateTimeUtil.getCurDate();
	 	 String fileName=t.getName()+time;
	 	 if(!"".equals(unit) && unit!=null){
	 		fileName+=unit;
	 	}
	 	 
	 	fileName+=".xlsx";
		 OutputStream ouputStream = response.getOutputStream();  
		  response.setContentType("application/vnd.ms-excel");    
	      response.setHeader("Content-disposition", "attachment;filename="+new String(fileName.getBytes("gbk"), "ISO8859-1"));    
          exportService.export(columnsStr,l,ouputStream,t,path,dgPath,sqlNo);    
   }    
    
    @RequestMapping(params="method=tableexport")
    public void exportTable(HttpServletRequest request, HttpServletResponse response)     
    throws Exception {    
		String path =request.getRealPath("/");
		String columnsStr=	(String)request.getParameter("columns");
		String profess=	(String)request.getParameter("excelProfess");
		String datas=	(String)request.getParameter("datas");
		String mergedCells=	(String)request.getParameter("mergedCells");
		String period=	(String)request.getParameter("excelProfess");
		String client=	(String)request.getParameter("excelClient");
		String product=	(String)request.getParameter("excelProduct");
		String myType=	(String)request.getParameter("myType");
		String kpiId=	(String)request.getParameter("kpiId");
		String [] arr={"qufen_name","quyu_name","sp_name","client_name","product_name","month01","month02","month03","month04","month05","month06","month07","month08","month09","month010","month011","month012"};
		List<Object> tables = ClassUtil.jsonStingToList(datas,arr);
		String [] mergedCellsArr={"colSpan","columnIndex","rowIndex","rowSpan"};
		List<Object> mergedCellsList = ClassUtil.jsonStingToList(mergedCells,mergedCellsArr);
		ExcelTemp t = Globals.getExcelTempByCode("kpiTemp") ;
		String s =(String) baseDAO.findByNativeQuery("select t.show_name from W_KPI_D t where t.row_id="+kpiId).get(0);
		String time = DateTimeUtil.getCurDate();
		if("branch".equals(myType)){
			s+="_分公司";
		}else if("bizcs".equals(myType)){
			s+="_营服中心";
		}else if("manager".equals(myType)){
			s+="_销售经理";
		}
		String fileName=s+time;
		OutputStream ouputStream = response.getOutputStream();  
		response.setContentType("application/vnd.ms-excel");    
		response.setHeader("Content-disposition", "attachment;filename="+new String(fileName.getBytes("gb2312"), "ISO8859-1"));   
		exportService.exportXZ(columnsStr,tables,ouputStream,t,path,mergedCellsList); 
    }
}
