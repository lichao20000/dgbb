package com.dglt.bb.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dglt.base.util.ClassUtil;
import com.dglt.bb.service.CompanyMapService;
import com.dglt.comm.base.BaseServiceImpl;
@Service(value = "companyMapService")
public class CompanyMapServiceImpl extends BaseServiceImpl implements CompanyMapService{
	
	//查询地域分布数据
	@Override
	public List getOweRateCompanyArea(String period, String profess, String branch,
			String bizcs,int monthId) {
		  if(monthId==0){
			  monthId=ClassUtil.getMonthIdpre();
		  }
		  String sqlInsert = "";
		  if(!"".equals(profess)&&!"99".equals(profess)&&!"null".equals(profess)){
			  sqlInsert +=" and sp_code ='"+profess+"' ";
		  }
		  if(!"".equals(branch)&&!"null".equals(branch)){
			  sqlInsert +=" and client_code ='"+branch+"' ";
		  }
		  String sql = "select  t.District_Branch_Code,t.kpi_id," +
			"t.District_Branch_Name,round(sum(amount_Curr),4),(a.min_value)*100,(a.warning_value)*100,(a.alert_value)*100,a.is_alert,(a.max_value)*100 from W_ARREARS_RATE_short_V t,w_Kpi_d a" +		
			" where month_Id = "+monthId+" and t.kpi_id=a.row_id(+) "+sqlInsert+" group by t.District_Branch_Code,t.District_Branch_Name,t.kpi_id,a.min_value,a.warning_value,a.alert_value,a.is_alert,a.max_value";
		  String [] arry = {"districtBranchCode","kpiId","districtBranchName","amountCurr","minValue","warningValue","alertValue","isAlert","maxValue","mapCode"};
		List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnListArea(l, arry);
		return al;
	}
	//查询欠费率曲线数据
	@Override
	public List getOweRateCompanyLine(String period, String profess,
			String branch, String bizcs, String companyCode,int monthId) {
		 String sqlInsert = "";
		  if(!"".equals(profess)&&!"99".equals(profess)&&!"null".equals(profess)){
			  sqlInsert +=" and sp_code ='"+profess+"' ";
		  }
		  if(!"".equals(branch)&&!"null".equals(branch)){
			  sqlInsert +=" and client_code ='"+branch+"' ";
		  }
		String sql = "select month_Id,month_Dsc,sum(amount_Curr) from W_ARREARS_RATE_SHORT_V where month_Id in("+ClassUtil.getMonthIdStr(ClassUtil.getMonthId(monthId))+") " +
					 " and District_Branch_Code = '"+companyCode+"' "+sqlInsert+" group by month_Id,month_Dsc order by month_Id ";
		String [] arry = {"month_Id","chartLabelName","chartLabelValue"};
		List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
		return al;
	}

	//查询长期欠费率地域分布数据
	@Override
	public List getOweRateCompanyAreaC(String period, String profess,
			String branch, String bizcs,int monthId) {
		 if(monthId==0){
			  monthId=ClassUtil.getMonthIdpre();
		  }
		 String sqlInsert = "";
		  if(!"".equals(profess)&&!"99".equals(profess)&&!"null".equals(profess)){
			  sqlInsert +=" and sp_code ='"+profess+"' ";
		  }
		  if(!"".equals(branch)&&!"null".equals(branch)){
			  sqlInsert +=" and client_code ='"+branch+"' ";
		  }
		String sql = "select  t.District_Branch_Code,t.kpi_id," +
						"t.District_Branch_Name,round(sum(amount_Curr),4),(a.min_value)*100,(a.warning_value)*100,(a.alert_value)*100,a.is_alert,(a.max_value)*100 from W_ARREARS_RATE_LONG_V t,w_Kpi_d a" +		
						" where month_Id = "+monthId+" "+sqlInsert+" and t.kpi_id=a.row_id(+) group by t.District_Branch_Code,t.District_Branch_Name,t.kpi_id,a.min_value,a.warning_value,a.alert_value,a.is_alert,a.max_value";
		String [] arry = {"districtBranchCode","kpiId","districtBranchName","amountCurr","minValue","warningValue","alertValue","isAlert","maxValue","mapCode"};
		List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnListArea(l, arry);
		return al;
	}

	//查询总体欠费率地域分布数据
	@Override
	public List getOweRateCompanyAreaZ(String period, String profess,
			String branch, String bizcs,int monthId) {
		 if(monthId==0){
			 monthId=ClassUtil.getMonthIdpre();
		  }
		 String sqlInsert = "";
		  if(!"".equals(profess)&&!"99".equals(profess)&&!"null".equals(profess)){
			  sqlInsert +=" and sp_code ='"+profess+"' ";
		  }
		  if(!"".equals(branch)&&!"null".equals(branch)){
			  sqlInsert +=" and client_code ='"+branch+"' ";
		  }
		 String sql = "select  t.District_Branch_Code,t.kpi_id," +
			"t.District_Branch_Name,round(sum(amount_Curr),4),(a.min_value)*100,(a.warning_value)*100,(a.alert_value)*100,a.is_alert,(a.max_value)*100 from W_ARREARS_RATE_ALL_V t,w_Kpi_d a" +		
			" where month_Id = "+monthId+" and t.kpi_id=a.row_id(+) "+sqlInsert+" group by t.District_Branch_Code,t.District_Branch_Name,t.kpi_id,a.min_value,a.warning_value,a.alert_value,a.is_alert,a.max_value";
		 String [] arry = {"districtBranchCode","kpiId","districtBranchName","amountCurr","minValue","warningValue","alertValue","isAlert","maxValue","mapCode"};
		 List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnListArea(l, arry);
		return al;
	}

	//查询长期欠费率曲线数据
	@Override
	public List getOweRateCompanyLineC(String period, String profess,
			String branch, String bizcs, String companyCode, int monthId) {
		
		 String sqlInsert = "";
		  if(!"".equals(profess)&&!"99".equals(profess)&&!"null".equals(profess)){
			  sqlInsert +=" and sp_code ='"+profess+"' ";
		  }
		  if(!"".equals(branch)&&!"null".equals(branch)){
			  sqlInsert +=" and client_code ='"+branch+"' ";
		  }
		String sql = "select month_Id,month_Dsc,sum(amount_Curr) from W_ARREARS_RATE_LONG_V where month_Id in("+ClassUtil.getMonthIdStr(ClassUtil.getMonthId(monthId))+") " +
					 " and District_Branch_Code = '"+companyCode+"' "+sqlInsert+" group by month_Id,month_Dsc order by month_Id";
		String [] arry = {"month_Id","chartLabelName","chartLabelValue"};
		List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
		return al;
	}

	//查询总体欠费率曲线数据
	@Override
	public List getOweRateCompanyLineZ(String period, String profess,
			String branch, String bizcs, String companyCode, int monthId) {
		 String sqlInsert = "";
		  if(!"".equals(profess)&&!"99".equals(profess)&&!"null".equals(profess)){
			  sqlInsert +=" and sp_code ='"+profess+"' ";
		  }
		  if(!"".equals(branch)&&!"null".equals(branch)){
			  sqlInsert +=" and client_code ='"+branch+"' ";
		  }
		String sql = "select month_Id,month_Dsc,sum(amount_Curr) from W_ARREARS_RATE_ALL_V where month_Id in("+ClassUtil.getMonthIdStr(ClassUtil.getMonthId(monthId))+") " +
					 " and District_Branch_Code = '"+companyCode+"' "+sqlInsert+" group by month_Id,month_Dsc order by month_Id";
		String [] arry = {"month_Id","chartLabelName","chartLabelValue"};
		List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
		return al;
	}


}
