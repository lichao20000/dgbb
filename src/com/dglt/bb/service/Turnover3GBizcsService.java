package com.dglt.bb.service;


import java.util.List;

import com.dglt.comm.base.BaseService;
//3g Ӫ�� ��ʧ��
public interface Turnover3GBizcsService  extends BaseService {

	//2g��ʧ��--����ֲ���ʾ
	public List getTurnover3GBizcsArea(String period,String profess,String branch,String bizcs ,String productName);
	
	//3GBizcs��ʧ��--������ʾ
	public List getTurnover3GBizcsLine(String period,String profess,String branch,String bizcs,int monthId ,String productName);
	
	//3GBizcs��ʧ��--�����ʾ
	public List getTurnover3GBizcsForm(String period,String profess,String branch,String bizcs,int monthId ,String productName);
	
}
