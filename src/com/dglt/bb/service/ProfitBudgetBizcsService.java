package com.dglt.bb.service;


import java.util.List;

import com.dglt.comm.base.BaseService;

public interface ProfitBudgetBizcsService  extends BaseService {

	 // ����Ԥ�������--����ֲ���ʾ -- Ӫҵ��
	//�ͻ���������
	public List getArea(String period,String profess,String branch,String bizcs ,String productName,String client );
	
	//����Ԥ�������--������ʾ -- Ӫҵ��
	//�ͻ���������
	public List getLine(String period,String profess,String branch,String bizcs,int monthId ,String productName,String client );
	
}
