package com.dglt.bb.service;

import java.util.List;

import com.dglt.comm.base.BaseService;

public interface CompanyMapService extends BaseService {
	
	//Ƿ����--����ֲ���ʾ
	public List getOweRateCompanyArea(String period,String profess,String branch,String bizcs,int monthId);
	
	//Ƿ����--������ʾ
	public List getOweRateCompanyLine(String period,String profess,String branch,String bizcs,String companyCode,int monthId);
	
	//����Ƿ����--����ֲ���ʾ
	public List getOweRateCompanyAreaC(String period,String profess,String branch,String bizcs,int monthId);
	
	//����Ƿ����--������ʾ
	public List getOweRateCompanyLineC(String period,String profess,String branch,String bizcs,String companyCode,int monthId);
	
	//����Ƿ����--����ֲ���ʾ
	public List getOweRateCompanyAreaZ(String period,String profess,String branch,String bizcs,int monthId);
	
	//����Ƿ����--������ʾ
	public List getOweRateCompanyLineZ(String period,String profess,String branch,String bizcs,String companyCode,int monthId);
	
	
	
	
	
}
