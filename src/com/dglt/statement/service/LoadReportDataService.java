package com.dglt.statement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dglt.comm.base.BaseService;

public interface LoadReportDataService extends BaseService {
	// ��ȡ�����ݵķ���
	public List getData(String sqlNo, String sql, Map<String,String>searchCond,String[] arry) ;

	// ��ȥ�������Ƶķ���
	public String getReportName(String rowId);
	// �����ͺϼ�
	public List<HashMap<String, String>> doJkList(List<HashMap<String, String>> l) ;
	//����������
	public List<HashMap<String, String>> doKHLYList(List<HashMap<String, String>> l ,List<HashMap<String, String>> l1 ,String name);
}
