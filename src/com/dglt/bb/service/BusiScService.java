package com.dglt.bb.service;

import java.util.List;

import com.dglt.comm.base.BaseService;

public interface BusiScService extends BaseService  {
	//欠费率--地域分布显示
	public List getOweRateBusiScArea(String period,String profess,String branch,String bizcs,int monthId,String companyCode);
	
	//欠费率--曲线显示
	public List getOweRateBusiScLine(String period,String profess,String branch,String bizcs,String busiScCode,int monthId,String companyCode);
		
	//长期欠费率--地域分布显示
	public List getOweRateBusiScAreaC(String period,String profess,String branch,String bizcs,int monthId,String companyCode);
	
	//长期欠费率--曲线显示
	public List getOweRateBusiScLineC(String period,String profess,String branch,String bizcs,String busiScCode,int monthId,String companyCode);
	
	//总体欠费率--地域分布显示
	public List getOweRateBusiScAreaZ(String period,String profess,String branch,String bizcs,int monthId,String companyCode);
	
	//总体欠费率--曲线显示
	public List getOweRateBusiScLineZ(String period,String profess,String branch,String bizcs,String busiScCode,int monthId,String companyCode);
	
}
