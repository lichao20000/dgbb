package com.dglt.bb.service;

import java.util.List;

import com.dglt.comm.base.BaseService;

public interface BusiScService extends BaseService  {
	//Ƿ����--����ֲ���ʾ
	public List getOweRateBusiScArea(String period,String profess,String branch,String bizcs,int monthId,String companyCode);
	
	//Ƿ����--������ʾ
	public List getOweRateBusiScLine(String period,String profess,String branch,String bizcs,String busiScCode,int monthId,String companyCode);
		
	//����Ƿ����--����ֲ���ʾ
	public List getOweRateBusiScAreaC(String period,String profess,String branch,String bizcs,int monthId,String companyCode);
	
	//����Ƿ����--������ʾ
	public List getOweRateBusiScLineC(String period,String profess,String branch,String bizcs,String busiScCode,int monthId,String companyCode);
	
	//����Ƿ����--����ֲ���ʾ
	public List getOweRateBusiScAreaZ(String period,String profess,String branch,String bizcs,int monthId,String companyCode);
	
	//����Ƿ����--������ʾ
	public List getOweRateBusiScLineZ(String period,String profess,String branch,String bizcs,String busiScCode,int monthId,String companyCode);
	
}
