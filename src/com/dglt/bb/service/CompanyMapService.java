package com.dglt.bb.service;

import java.util.List;

import com.dglt.comm.base.BaseService;

public interface CompanyMapService extends BaseService {
	
	//欠费率--地域分布显示
	public List getOweRateCompanyArea(String period,String profess,String branch,String bizcs,int monthId);
	
	//欠费率--曲线显示
	public List getOweRateCompanyLine(String period,String profess,String branch,String bizcs,String companyCode,int monthId);
	
	//长期欠费率--地域分布显示
	public List getOweRateCompanyAreaC(String period,String profess,String branch,String bizcs,int monthId);
	
	//长期欠费率--曲线显示
	public List getOweRateCompanyLineC(String period,String profess,String branch,String bizcs,String companyCode,int monthId);
	
	//总体欠费率--地域分布显示
	public List getOweRateCompanyAreaZ(String period,String profess,String branch,String bizcs,int monthId);
	
	//总体欠费率--曲线显示
	public List getOweRateCompanyLineZ(String period,String profess,String branch,String bizcs,String companyCode,int monthId);
	
	
	
	
	
}
