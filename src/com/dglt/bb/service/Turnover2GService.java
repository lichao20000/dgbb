package com.dglt.bb.service;


import java.util.List;

import com.dglt.comm.base.BaseService;

public interface Turnover2GService  extends BaseService {

	//2g��ʧ��--����ֲ���ʾ
	public List getTurnover2GArea(String period,String profess,String branch,String bizcs ,String productName);
	
	//2g��ʧ��--������ʾ
	public List getTurnover2GLine(String period,String profess,String branch,String bizcs,int monthId ,String productName);
	
	//2g��ʧ��--�����ʾ
	public List getTurnover2GForm(String period,String profess,String branch,String bizcs,int monthId ,String productName);
   //�õ��������
	
	
	
}
