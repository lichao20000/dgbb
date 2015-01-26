package com.dglt.bb.service;


import java.util.List;

import com.dglt.comm.base.BaseService;

public interface Turnover3GService  extends BaseService {

	//2g��ʧ��--����ֲ���ʾ
	public List getTurnover3GArea(String period,String profess,String branch,String bizcs,String productName);
	
	//3G��ʧ��--������ʾ
	public List getTurnover3GLine(String period,String profess,String branch,String bizcs,int monthId ,String productName);
	
	//3G��ʧ��--�����ʾ
	public List getTurnover3GForm(String period,String profess,String branch,String bizcs,int monthId ,String productName);
	
}
