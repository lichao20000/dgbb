package com.dglt.bb.service;


import java.util.List;

import com.dglt.comm.base.BaseService;
//营服 2g流失率 
public interface Turnover2GBizcsService  extends BaseService {

	//2g流失率--地域分布显示
	public List getTurnover2GBizcsArea(String period,String profess,String branch,String bizcs ,String productName);
	
	//2g流失率--曲线显示
	public List getTurnover2GBizcsLine(String period,String profess,String branch,String bizcs,int monthId ,String productName);
	
	//2g流失率--表格显示
	public List getTurnover2GBizcsForm(String period,String profess,String branch,String bizcs,int monthId ,String productName);
	
}
