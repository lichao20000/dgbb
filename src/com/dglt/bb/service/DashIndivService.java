package com.dglt.bb.service;

import com.dglt.bb.pojo.DashIndiv;
import com.dglt.bb.pojo.DashWarnDetail;

/**
 * 仪表盘个性化功能实现
 * @author Administrator
 *
 */
public interface DashIndivService {

	//根据用户ID取个性化数据
	public DashIndiv getDashIndivByUserId(String userId);
	
	//根据用户ID,menuId取个性化数据
	public DashIndiv getDashIndivByUserIdAndMenuId(String userId,String menuId);
	
	//新增个性化数据(初始化)
	public DashIndiv insertDashIndiv(String userId,String menuId); 
	
	//更新个性化数据
	public DashIndiv updateDashIndiv(DashIndiv indiv);
	
	//预警信息发送
	public DashWarnDetail insertDashWarnDetail(DashWarnDetail wdetail);
	
	//根据kpiID取接收邮件人
	public String getReciever(String kpiId);
	//根据kpiID取接短信联系人
	public String getSMSReciever(String kpiId);
	//调用过程发送短信
	public String sendSMS(String tel,String kpi_name,
			String month_desc,String product_name,String client_name,String sp_name,String amount_curr,String warn_value);
}
