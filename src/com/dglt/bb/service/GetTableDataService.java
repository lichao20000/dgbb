package com.dglt.bb.service;


import java.util.List;

import com.dglt.bb.pojo.WKipConfigV;
import com.dglt.bb.pojo.WKpiD;
import com.dglt.comm.base.BaseService;

public interface GetTableDataService  extends BaseService {
   //此类用于调用过程得到数据

	//得到表格数据
	public List getTableData(String period,String profess,String branch,String bizcs ,String manager_No,String productName,String kipId ,String clentId);
	
	public List getTableDataxsjl(String period,String profess,String branch,String bizcs ,String manager_No,String productName,String kipId ,String clentId);
	
	public List getTableDataYf(String period,String profess,String branch,String bizcs ,String manager_No,String productName,String kipId ,String clentId);
    //根据指标Id和页面层次 得到到指标的设置对象
	//kpiId 指标ID   pageType 页面层次类型
	public WKipConfigV getWKipConfigVbyKpiId(String kpiId ,String pageType) ;

	//首页非四龙对比指标区域分公司显示
	public List getDashBoardReportQufenP(String period,String profess,String branch,String bizcs ,String productName,String kipId ,String clentId);
 
	//龙对比指标显示
	public List getDashBoardReportSilongP(String period,String profess,String branch,String bizcs ,String productName,String kipId ,String clentId);
 
	//区分公司地图数据显示 
	public List getDashBoardRepDituQufenP(String period,String profess,String branch,String bizcs ,String productName,String kipId ,String clentId);
 
	//首页非四龙对比指标区域分公司显示
	public List getDashBoardRepDituYfP(String period,String profess,String branch,String bizcs ,String productName,String kipId ,String clentId);
	
	//首页非四龙对比指标区域分公司显示
	public List getDashBoardRepDituxsjlP(String period,String profess,String branch,String bizcs ,String productName,String kipId ,String clentId);
	
	//首页非四龙对比指标区域分公司显示
	public double getDashBoardKpiP(String period,String profess,String branch,String bizcs ,String productName,String kipId ,String clentId);
    
	//获取分公司折线图数据
	public List getDashBoardRepQufenPLine(String period,String profess,String branch,String bizcs ,String productName,String kipId ,String clentId);
	
	//获取营服折线图数据
	public List getDashBoardRepYfPLine(String period,String profess,String branch,String bizcs ,String productName,String kipId ,String clentId);
	
	//获取销售经理折线图数据
	public List getDashBoardRepxsjlPLine(String period,String profess,String branch,String bizcs ,String manager_No,String productName,String kipId ,String clentId);
}
