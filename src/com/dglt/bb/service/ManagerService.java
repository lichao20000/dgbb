package com.dglt.bb.service;

import java.util.List;

import com.dglt.comm.base.BaseService;

public interface ManagerService extends BaseService {
	//Ƿ����--����ֲ���ʾ
	public List getOweRateManagerArea(String period,String profess,String branch,String bizcs,int monthId,String companyCode,String busiScCode);
	
	//Ƿ����--������ʾ
	public List getOweRateManagerLine(String period,String profess,String branch,String bizcs,String busiScCode,int monthId,String companyCode,String managerNo);
		
	//����Ƿ����--����ֲ���ʾ
	public List getOweRateManagerAreaC(String period,String profess,String branch,String bizcs,int monthId,String companyCode,String busiScCode);
	
	//����Ƿ����--������ʾ
	public List getOweRateManagerLineC(String period,String profess,String branch,String bizcs,String busiScCode,int monthId,String companyCode,String managerNo);
	
	//����Ƿ����--����ֲ���ʾ
	public List getOweRateManagerAreaZ(String period,String profess,String branch,String bizcs,int monthId,String companyCode,String busiScCode);
	
	//����Ƿ����--������ʾ
	public List getOweRateManagerLineZ(String period,String profess,String branch,String bizcs,String busiScCode,int monthId,String companyCode,String managerNo);
	
}
