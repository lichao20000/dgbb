package com.dglt.bb.service;

import java.util.List;

import com.dglt.comm.base.BaseService;

/**
 * �Ǳ������ݲ�ѯ
 * @author Administrator
 *
 */
public interface DashBoardService extends BaseService {

     //ȡ�������Ԥ�������--�����Ǳ�������
	public List getIncomeBudgetCompletRate(int kpid,int monthId,String specialty,String client,String product);
	
	//ȡ�������Ԥ�������--��������
	
	
    //ȡ����Ԥ�������--�����Ǳ�������
	
	
	//ȡ����Ԥ�������--��������
	
	
	//ȡ2Gҵ����ʧ��--�����Ǳ�������
	
	
	//ȡ2Gҵ����ʧ��--��������
	
	
	//3G�����󸶷��û���ʧ--�����Ǳ�������
	
	
	//3G�����󸶷��û���ʧ--��������
	
	
	//Ƿ����--�����Ǳ�������
	public List getOweRateChart(int kpid,String period,String profess,String branch,String bizcs);
	
	//Ƿ����--��������
	public List getOweRateArea(int kpid,int period,String profess,String branch,String bizcs);
	
	//��ȡ���Ʒ�������
	public List getTop5Data(int kpid,int period, String specialty, String client, String product);
	
	//��ȡרҵ����������
	public String getSpecialtyData();
	//��ȡ���Ʒ�������
	public List getLineData(int kpid,int period, String specialty, String client, String product);
	
	//��ȡռ�ȷ�������
	public List getPieData(int kpid,int period, String specialty, String client, String product);
	//��ȡ�ͻ�����������
	public String getClientData();
	//��ȡ��Ʒ����������
	public String getProductData(String specialty);
	//��ȡָ���������������
	public String getkpiType();
	//��ȡָ��չʾ��ʽ
	public String getchartType(String kpiId);
	//��ȡָ����������
	public List getKpiName(String kpiType,String kpiName,String area,String menuId);
	
	public List getChartDataBySql(int kpid,int monthId,String specialty,String client,String product);
	public List getOweRateBySql(int kpid,int period, String specialty, String client,
			String product);
}
