package com.dglt.bb.service;


import java.util.List;

import com.dglt.comm.base.BaseService;

public interface KipManagerService  extends BaseService {

	//��ͼ
	//�ͻ���������
	public List getPie(String period,String profess,String branch,String bizcs ,String productName,String client,String type  ,String kipId );
	
	//������ʾ
	//�ͻ���������
	public List getLine(String period,String profess,String branch,String bizcs,int monthId ,String productCode,String client ,String managerCode ,String type  ,String kipId );
	
	//��ͼ 
	//�ͻ���������
	public List getMap(String period,String profess,String branch,String bizcs ,String productName,String client,String type  ,String kipId );
	
	
}
