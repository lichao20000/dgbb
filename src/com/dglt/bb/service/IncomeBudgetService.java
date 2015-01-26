package com.dglt.bb.service;


import java.util.List;

import com.dglt.comm.base.BaseService;

public interface IncomeBudgetService  extends BaseService {

	//收入进度预算完成率--地域分布显示 -- 分公司
	//客户不起作用
	public List getArea(String period,String profess,String branch,String bizcs ,String productName,String client );
	
	//收入进度预算完成率--曲线显示 -- 分公司
	//客户不起作用
	public List getLine(String period,String profess,String branch,String bizcs,int monthId ,String productName,String client );
	
}
