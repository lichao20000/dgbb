package com.dglt.bb.service;


import java.util.List;

import com.dglt.bb.pojo.WKipConfigV;
import com.dglt.bb.pojo.WKpiD;
import com.dglt.comm.base.BaseService;

public interface GetTableDataService  extends BaseService {
   //�������ڵ��ù��̵õ�����

	//�õ��������
	public List getTableData(String period,String profess,String branch,String bizcs ,String manager_No,String productName,String kipId ,String clentId);
	
	public List getTableDataxsjl(String period,String profess,String branch,String bizcs ,String manager_No,String productName,String kipId ,String clentId);
	
	public List getTableDataYf(String period,String profess,String branch,String bizcs ,String manager_No,String productName,String kipId ,String clentId);
    //����ָ��Id��ҳ���� �õ���ָ������ö���
	//kpiId ָ��ID   pageType ҳ��������
	public WKipConfigV getWKipConfigVbyKpiId(String kpiId ,String pageType) ;

	//��ҳ�������Ա�ָ������ֹ�˾��ʾ
	public List getDashBoardReportQufenP(String period,String profess,String branch,String bizcs ,String productName,String kipId ,String clentId);
 
	//���Ա�ָ����ʾ
	public List getDashBoardReportSilongP(String period,String profess,String branch,String bizcs ,String productName,String kipId ,String clentId);
 
	//���ֹ�˾��ͼ������ʾ 
	public List getDashBoardRepDituQufenP(String period,String profess,String branch,String bizcs ,String productName,String kipId ,String clentId);
 
	//��ҳ�������Ա�ָ������ֹ�˾��ʾ
	public List getDashBoardRepDituYfP(String period,String profess,String branch,String bizcs ,String productName,String kipId ,String clentId);
	
	//��ҳ�������Ա�ָ������ֹ�˾��ʾ
	public List getDashBoardRepDituxsjlP(String period,String profess,String branch,String bizcs ,String productName,String kipId ,String clentId);
	
	//��ҳ�������Ա�ָ������ֹ�˾��ʾ
	public double getDashBoardKpiP(String period,String profess,String branch,String bizcs ,String productName,String kipId ,String clentId);
    
	//��ȡ�ֹ�˾����ͼ����
	public List getDashBoardRepQufenPLine(String period,String profess,String branch,String bizcs ,String productName,String kipId ,String clentId);
	
	//��ȡӪ������ͼ����
	public List getDashBoardRepYfPLine(String period,String profess,String branch,String bizcs ,String productName,String kipId ,String clentId);
	
	//��ȡ���۾�������ͼ����
	public List getDashBoardRepxsjlPLine(String period,String profess,String branch,String bizcs ,String manager_No,String productName,String kipId ,String clentId);
}
