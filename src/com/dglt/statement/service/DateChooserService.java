package com.dglt.statement.service;

import java.util.List;

import com.dglt.comm.base.BaseService;

/**
 * 报表页通用的时间选择器
 * 
 * @author Administrator
 * 
 */
public interface DateChooserService extends BaseService {
	// 获取不同报表的日期下拉选择数据
	public String GetDateData();

	// 获取考核利润报表的不同条件
	public List GetEnotherReport();

	// 获取集客收入的下拉年份选择
	public String GetyearData();

	// 指标类别的下拉选择
	public String GetCategoryData(String categorytext);

	// 专门获取滚动预算的期间选择
	public String GetQuarterData();

	// 专门获取部门预算的类别选择
	public String GetStyleData(String styletext);

	// 获取综合评价类别的选择
	public String GetCtData(String cttext);
}
