package com.dglt.bb.service;


import java.util.List;

import com.dglt.comm.base.BaseService;
//Ӫ�� 2g��ʧ�� 
public interface Turnover2GBizcsService  extends BaseService {

	//2g��ʧ��--����ֲ���ʾ
	public List getTurnover2GBizcsArea(String period,String profess,String branch,String bizcs ,String productName);
	
	//2g��ʧ��--������ʾ
	public List getTurnover2GBizcsLine(String period,String profess,String branch,String bizcs,int monthId ,String productName);
	
	//2g��ʧ��--�����ʾ
	public List getTurnover2GBizcsForm(String period,String profess,String branch,String bizcs,int monthId ,String productName);
	
}
