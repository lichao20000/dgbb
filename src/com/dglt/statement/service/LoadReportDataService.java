package com.dglt.statement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dglt.comm.base.BaseService;

public interface LoadReportDataService extends BaseService {
	// 获取表单数据的方法
	public List getData(String sqlNo, String sql, Map<String,String>searchCond,String[] arry) ;

	// 回去报表名称的方法
	public String getReportName(String rowId);
	// 处理集客合计
	public List<HashMap<String, String>> doJkList(List<HashMap<String, String>> l) ;
	//处理考核利润
	public List<HashMap<String, String>> doKHLYList(List<HashMap<String, String>> l ,List<HashMap<String, String>> l1 ,String name);
}
