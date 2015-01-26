package com.dglt.bb.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dglt.base.util.ClassUtil;
import com.dglt.bb.service.BusiScService;
import com.dglt.comm.base.BaseServiceImpl;
import com.dglt.comm.base.DataPage;
@Service(value = "BusiScService")
public class BusiScServiceImpl extends BaseServiceImpl implements BusiScService {

	
	@Override
	public List getOweRateBusiScArea(String period, String profess,
			String branch, String bizcs,int monthId, String companyCode) {
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
		String sql = "select t.busi_Sc_Code,t.kpi_Id," +
						"t.busi_Sc_Name,sum(amount_Curr),(a.min_value)*100,(a.warning_value)*100,(a.alert_value)*100,a.is_alert,(a.max_value)*100 from w_arrears_rate_short_v t,w_Kpi_d a" +
						" where t.month_Id ="+monthId+" and t.district_Branch_Code='"+companyCode+"' and t.kpi_id=a.row_id(+) "+sqlInsert+" group by t.busi_Sc_Code,t.busi_Sc_Name,t.kpi_Id," +
						"a.min_value,a.warning_value,a.alert_value,a.is_alert,a.max_value";
		String [] arry = {"busiScCode","kpiId","busiScName","amountCurr","minValue","warningValue","alertValue","isAlert","maxValue","mapCode"};
		List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnListArea(l, arry);
		return al;
	}



	@Override
	public List getOweRateBusiScLine(String period, String profess,
			String branch, String bizcs, String busiScCode, int monthId,String companyCode) {
		String sqlInsert = "";
		  if(!"".equals(profess)&&!"99".equals(profess)&&!"null".equals(profess)){
			  sqlInsert +=" and sp_code ='"+profess+"' ";
		  }
		  if(!"".equals(branch)&&!"null".equals(branch)){
			  sqlInsert +=" and client_code ='"+branch+"' ";
		  }
		String sql = "select month_Id,month_Dsc,sum(amount_Curr) from w_arrears_rate_short_v where month_Id in("+ClassUtil.getMonthIdStr(ClassUtil.getMonthId(monthId))+") " +
					 " and busi_Sc_Code = '"+busiScCode+"' "+sqlInsert+" group by month_Id,month_Dsc order by month_Id";
		String [] arry = {"month_Id","chartLabelName","chartLabelValue"};
		List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
		return al;
	
	}

	@Override
	public List getOweRateBusiScAreaC(String period, String profess,
			String branch, String bizcs,int monthId, String companyCode) {
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
		String sql = "select t.busi_Sc_Code,t.kpi_Id," +
		"t.busi_Sc_Name,sum(amount_Curr),(a.min_value)*100,(a.warning_value)*100,(a.alert_value)*100,a.is_alert,(a.max_value)*100 from w_arrears_rate_long_v t,w_Kpi_d a" +
		" where t.month_Id ="+monthId+" and t.district_Branch_Code='"+companyCode+"' and t.kpi_id=a.row_id(+) "+sqlInsert+" group by t.busi_Sc_Code,t.busi_Sc_Name,t.kpi_Id," +
		"a.min_value,a.warning_value,a.alert_value,a.is_alert,a.max_value";
		String [] arry = {"busiScCode","kpiId","busiScName","amountCurr","minValue","warningValue","alertValue","isAlert","maxValue","mapCode"};
		List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnListArea(l, arry);
		return al;
	}

	@Override
	public List getOweRateBusiScAreaZ(String period, String profess,
			String branch, String bizcs,int monthId, String companyCode) {
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
		String sql = "select t.busi_Sc_Code,t.kpi_Id," +
		"t.busi_Sc_Name,sum(amount_Curr),(a.min_value)*100,(a.warning_value)*100,(a.alert_value)*100,a.is_alert,(a.max_value)*100 from w_arrears_rate_all_v t,w_Kpi_d a" +
		" where t.month_Id ="+monthId+" and t.district_Branch_Code='"+companyCode+"' and t.kpi_id=a.row_id(+) "+sqlInsert+" group by t.busi_Sc_Code,t.busi_Sc_Name,t.kpi_Id," +
		"a.min_value,a.warning_value,a.alert_value,a.is_alert,a.max_value";
		String [] arry = {"busiScCode","kpiId","busiScName","amountCurr","minValue","warningValue","alertValue","isAlert","maxValue","mapCode"};
		List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnListArea(l, arry);
		return al;
	}

	@Override
	public List getOweRateBusiScLineC(String period, String profess,
			String branch, String bizcs, String busiScCode, int monthId,
			String companyCode) {
		String sqlInsert = "";
		  if(!"".equals(profess)&&!"99".equals(profess)&&!"null".equals(profess)){
			  sqlInsert +=" and sp_code ='"+profess+"' ";
		  }
		  if(!"".equals(branch)&&!"null".equals(branch)){
			  sqlInsert +=" and client_code ='"+branch+"' ";
		  }
		String sql = "select month_Id,month_Dsc,sum(amount_Curr) from w_arrears_rate_long_v where month_Id in("+ClassUtil.getMonthIdStr(ClassUtil.getMonthId(monthId))+") " +
					 " and busi_Sc_Code = '"+busiScCode+"' "+sqlInsert+" group by month_Id,month_Dsc order by month_Id";
		String [] arry = {"month_Id","chartLabelName","chartLabelValue"};
		List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
		return al;
	}

	@Override
	public List getOweRateBusiScLineZ(String period, String profess,
			String branch, String bizcs, String busiScCode, int monthId,
			String companyCode) {
		String sqlInsert = "";
		  if(!"".equals(profess)&&!"99".equals(profess)&&!"null".equals(profess)){
			  sqlInsert +=" and sp_code ='"+profess+"' ";
		  }
		  if(!"".equals(branch)&&!"null".equals(branch)){
			  sqlInsert +=" and client_code ='"+branch+"' ";
		  }
		String sql = "select month_Id,month_Dsc,sum(amount_Curr) from w_arrears_rate_all_v where month_Id in("+ClassUtil.getMonthIdStr(ClassUtil.getMonthId(monthId))+") " +
					 " and busi_Sc_Code = '"+busiScCode+"' "+sqlInsert+" group by month_Id,month_Dsc order by month_Id";
		String [] arry = {"month_Id","chartLabelName","chartLabelValue"};
		List l = this.findByNativeQuery(sql);
			//报表查询，返回数据不是持久化对象时，进行转换
			ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
			return al;
	}
	

}
