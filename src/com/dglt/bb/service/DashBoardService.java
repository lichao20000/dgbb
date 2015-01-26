package com.dglt.bb.service;

import java.util.List;

import com.dglt.comm.base.BaseService;

/**
 * 仪表盘数据查询
 * @author Administrator
 *
 */
public interface DashBoardService extends BaseService {

     //取收入进度预算完成率--本月仪表盘数据
	public List getIncomeBudgetCompletRate(int kpid,int monthId,String specialty,String client,String product);
	
	//取收入进度预算完成率--各区数据
	
	
    //取利润预算完成率--本月仪表盘数据
	
	
	//取利润预算完成率--各区数据
	
	
	//取2G业务流失率--本月仪表盘数据
	
	
	//取2G业务流失率--各区数据
	
	
	//3G语音后付费用户流失--本月仪表盘数据
	
	
	//3G语音后付费用户流失--各区数据
	
	
	//欠费率--本月仪表盘数据
	public List getOweRateChart(int kpid,String period,String profess,String branch,String bizcs);
	
	//欠费率--各区数据
	public List getOweRateArea(int kpid,int period,String profess,String branch,String bizcs);
	
	//获取趋势分析数据
	public List getTop5Data(int kpid,int period, String specialty, String client, String product);
	
	//获取专业下来框数据
	public String getSpecialtyData();
	//获取趋势分析数据
	public List getLineData(int kpid,int period, String specialty, String client, String product);
	
	//获取占比分析数据
	public List getPieData(int kpid,int period, String specialty, String client, String product);
	//获取客户下拉框数据
	public String getClientData();
	//获取产品下拉框数据
	public String getProductData(String specialty);
	//获取指标大类下拉框数据
	public String getkpiType();
	//获取指标展示方式
	public String getchartType(String kpiId);
	//获取指标名称数据
	public List getKpiName(String kpiType,String kpiName,String area,String menuId);
	
	public List getChartDataBySql(int kpid,int monthId,String specialty,String client,String product);
	public List getOweRateBySql(int kpid,int period, String specialty, String client,
			String product);
}
