package com.dglt.statement.service;

import java.util.List;

import com.dglt.comm.base.BaseService;

/**
 * ����ҳͨ�õ�ʱ��ѡ����
 * 
 * @author Administrator
 * 
 */
public interface DateChooserService extends BaseService {
	// ��ȡ��ͬ�������������ѡ������
	public String GetDateData();

	// ��ȡ�������󱨱�Ĳ�ͬ����
	public List GetEnotherReport();

	// ��ȡ����������������ѡ��
	public String GetyearData();

	// ָ����������ѡ��
	public String GetCategoryData(String categorytext);

	// ר�Ż�ȡ����Ԥ����ڼ�ѡ��
	public String GetQuarterData();

	// ר�Ż�ȡ����Ԥ������ѡ��
	public String GetStyleData(String styletext);

	// ��ȡ�ۺ���������ѡ��
	public String GetCtData(String cttext);
}
