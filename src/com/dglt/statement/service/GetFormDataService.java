package com.dglt.statement.service;

import java.util.HashMap;
import java.util.List;

import com.dglt.comm.base.BaseService;

public interface GetFormDataService extends BaseService {
	// ��ȡ�����ݵķ���
	public List getData(String sqlNo, String period, String profess,
			String branch, String bizcs, String[] arry, String sql,
			String monthId, String text, String categorytext, String yearId,
			String style, String cttext);

	// ��ȥ�������Ƶķ���
	public String getReportName(String rowId);
	// �����ͺϼ�
	public List<HashMap<String, String>> doJkList(List<HashMap<String, String>> l) ;
	//����������
	public List<HashMap<String, String>> doKHLYList(List<HashMap<String, String>> l ,List<HashMap<String, String>> l1 ,String name);
}
