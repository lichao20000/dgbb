package com.dglt.bb.service;

import java.util.List;

import com.dglt.comm.base.BaseService;

public interface ManagerService extends BaseService {
	//欠费率--地域分布显示
	public List getOweRateManagerArea(String period,String profess,String branch,String bizcs,int monthId,String companyCode,String busiScCode);
	
	//欠费率--曲线显示
	public List getOweRateManagerLine(String period,String profess,String branch,String bizcs,String busiScCode,int monthId,String companyCode,String managerNo);
		
	//长期欠费率--地域分布显示
	public List getOweRateManagerAreaC(String period,String profess,String branch,String bizcs,int monthId,String companyCode,String busiScCode);
	
	//长期欠费率--曲线显示
	public List getOweRateManagerLineC(String period,String profess,String branch,String bizcs,String busiScCode,int monthId,String companyCode,String managerNo);
	
	//总体欠费率--地域分布显示
	public List getOweRateManagerAreaZ(String period,String profess,String branch,String bizcs,int monthId,String companyCode,String busiScCode);
	
	//总体欠费率--曲线显示
	public List getOweRateManagerLineZ(String period,String profess,String branch,String bizcs,String busiScCode,int monthId,String companyCode,String managerNo);
	
}
