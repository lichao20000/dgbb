package com.dglt.bb.service;

import com.dglt.bb.pojo.DashIndiv;
import com.dglt.bb.pojo.DashWarnDetail;

/**
 * �Ǳ��̸��Ի�����ʵ��
 * @author Administrator
 *
 */
public interface DashIndivService {

	//�����û�IDȡ���Ի�����
	public DashIndiv getDashIndivByUserId(String userId);
	
	//�����û�ID,menuIdȡ���Ի�����
	public DashIndiv getDashIndivByUserIdAndMenuId(String userId,String menuId);
	
	//�������Ի�����(��ʼ��)
	public DashIndiv insertDashIndiv(String userId,String menuId); 
	
	//���¸��Ի�����
	public DashIndiv updateDashIndiv(DashIndiv indiv);
	
	//Ԥ����Ϣ����
	public DashWarnDetail insertDashWarnDetail(DashWarnDetail wdetail);
	
	//����kpiIDȡ�����ʼ���
	public String getReciever(String kpiId);
	//����kpiIDȡ�Ӷ�����ϵ��
	public String getSMSReciever(String kpiId);
	//���ù��̷��Ͷ���
	public String sendSMS(String tel,String kpi_name,
			String month_desc,String product_name,String client_name,String sp_name,String amount_curr,String warn_value);
}
