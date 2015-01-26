package com.dglt.bb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dglt.base.util.ClassUtil;
import com.dglt.bb.service.ManagerService;
import com.dglt.comm.base.BaseServiceImpl;

@Service(value = "managerService")
public class ManagerServiceImpl extends BaseServiceImpl implements ManagerService {

	@Override
	public List getOweRateManagerArea(String period, String profess,
			String branch, String bizcs, int monthId, String companyCode,String busiScCode) {
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
		String sql = "select t.manager_no,t.kpi_Id," +
						"t.manager_name,sum(amount_Curr) from W_ARREARS_RATE_short_V t" +
						//" where monthId = "+monthId+"";
						" where month_Id ="+monthId+" and busi_Sc_Code='"+busiScCode+"' "+sqlInsert+" group by t.manager_no,t.manager_name,t.kpi_Id";
		String [] arry = {"managerNo","kpiId","chartLabelName","chartLabelValue"};
		List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
		return al;
	}

	@Override
	public List getOweRateManagerAreaC(String period, String profess,
			String branch, String bizcs, int monthId, String companyCode,String busiScCode) {
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
		String sql = "select t.manager_no,t.kpi_Id," +
						"t.manager_name,sum(amount_Curr) from W_ARREARS_RATE_long_V t" +
						//" where monthId = "+monthId+"";
						" where month_Id ="+monthId+" and busi_Sc_Code='"+busiScCode+"'"+sqlInsert+" group by t.manager_no,t.manager_name,t.kpi_Id";
		String [] arry = {"managerNo","kpiId","chartLabelName","chartLabelValue"};
		List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
		return al;
	}

	@Override
	public List getOweRateManagerAreaZ(String period, String profess,
			String branch, String bizcs, int monthId, String companyCode,String busiScCode) {
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
		String sql = "select t.manager_no,t.kpi_Id," +
						"t.manager_name,sum(amount_Curr) from W_ARREARS_RATE_all_V t" +
						//" where monthId = "+monthId+"";
						" where month_Id ="+monthId+" and busi_Sc_Code='"+busiScCode+"'"+sqlInsert+" group by t.manager_no,t.manager_name,t.kpi_Id";
		String [] arry = {"managerNo","kpiId","chartLabelName","chartLabelValue"};
		List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
		return al;
	}

	@Override
	public List getOweRateManagerLine(String period, String profess,
			String branch, String bizcs, String busiScCode, int monthId,
			String companyCode,String managerNo) {
		String sqlInsert = "";
		  if(!"".equals(profess)&&!"99".equals(profess)&&!"null".equals(profess)){
			  sqlInsert +=" and sp_code ='"+profess+"' ";
		  }
		  if(!"".equals(branch)&&!"null".equals(branch)){
			  sqlInsert +=" and client_code ='"+branch+"' ";
		  }
		String sql = "select month_Id,month_Dsc,sum(amount_Curr) from W_ARREARS_RATE_short_V where month_Id in("+ClassUtil.getMonthIdStr(ClassUtil.getMonthId(monthId))+") " +
		 " and busi_Sc_Code = '"+busiScCode+"' and manager_No = '"+managerNo+"' "+sqlInsert+" group by month_Id,month_Dsc order by month_Id";
		String [] arry = {"month_Id","chartLabelName","chartLabelValue"};
		List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
		return al;
	}

	@Override
	public List getOweRateManagerLineC(String period, String profess,
			String branch, String bizcs, String busiScCode, int monthId,
			String companyCode,String managerNo) {
		String sqlInsert = "";
		  if(!"".equals(profess)&&!"99".equals(profess)&&!"null".equals(profess)){
			  sqlInsert +=" and sp_code ='"+profess+"' ";
		  }
		  if(!"".equals(branch)&&!"null".equals(branch)){
			  sqlInsert +=" and client_code ='"+branch+"' ";
		  }
		String sql = "select month_Id,month_Dsc,sum(amount_Curr) from W_ARREARS_RATE_long_V where month_Id in("+ClassUtil.getMonthIdStr(ClassUtil.getMonthId(monthId))+") " +
		 " and busi_Sc_Code = '"+busiScCode+"' and manager_No = '"+managerNo+"'"+sqlInsert+" group by month_Id,month_Dsc order by month_Id";
		String [] arry = {"month_Id","chartLabelName","chartLabelValue"};
		List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
		return al;
	}

	@Override
	public List getOweRateManagerLineZ(String period, String profess,
			String branch, String bizcs, String busiScCode, int monthId,
			String companyCode,String managerNo) {
		String sqlInsert = "";
		  if(!"".equals(profess)&&!"99".equals(profess)&&!"null".equals(profess)){
			  sqlInsert +=" and sp_code ='"+profess+"' ";
		  }
		  if(!"".equals(branch)&&!"null".equals(branch)){
			  sqlInsert +=" and client_code ='"+branch+"' ";
		  }
		String sql = "select month_Id,month_Dsc,sum(amount_Curr) from W_ARREARS_RATE_all_V where month_Id in("+ClassUtil.getMonthIdStr(ClassUtil.getMonthId(monthId))+") " +
		 " and busi_Sc_Code = '"+busiScCode+"' and manager_No = '"+managerNo+"'"+sqlInsert+" group by month_Id,month_Dsc order by month_Id";
		String [] arry = {"month_Id","chartLabelName","chartLabelValue"};
		List l = this.findByNativeQuery(sql);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
		return al;
	}

}
