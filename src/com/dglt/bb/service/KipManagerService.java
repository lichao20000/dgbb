package com.dglt.bb.service;


import java.util.List;

import com.dglt.comm.base.BaseService;

public interface KipManagerService  extends BaseService {

	//饼图
	//客户不起作用
	public List getPie(String period,String profess,String branch,String bizcs ,String productName,String client,String type  ,String kipId );
	
	//曲线显示
	//客户不起作用
	public List getLine(String period,String profess,String branch,String bizcs,int monthId ,String productCode,String client ,String managerCode ,String type  ,String kipId );
	
	//地图 
	//客户不起作用
	public List getMap(String period,String profess,String branch,String bizcs ,String productName,String client,String type  ,String kipId );
	
	
}
