package com.dglt.statement.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dglt.comm.base.BaseServiceImpl;
import com.dglt.statement.service.DateChooserService;

@Service(value = "dateChooserService")
public class DateChooserServiceImpl extends BaseServiceImpl implements
		DateChooserService {

	// 当前月的上月
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);

	// 获取表头月份的数据格式

	@Override
	public String GetDateData() {
		String lastMonth = "";
		lastMonth = year + "" + month;
		if (month < 10) {
			lastMonth = year + "0" + month;
		} else {
			lastMonth = year + "" + month;
		}
		String sql = "select to_char(to_date(aa.month_id, 'yyyymm'), 'mm'),to_number(to_char(to_date(aa.month_id, 'yyyymm'), 'mm')) || '月' from w_period_m_d aa where  aa.year_id = '2010'";// ,distinct
		// w.year_code,distinct
		// w.year_dsc
		List list = this.findByNativeQuery(sql);
		// 获取数据
		String s = "[";
		for (int i = 0; i < list.size(); i++) {
			Object[] v = (Object[]) list.get(i);
			if (i > 0) {
				s += ",";
			}
			s += "{'monthCode':'";
			s += v[0] == null ? "" : v[0] + "";
			s += "','monthDsc':'";
			s += v[1] == null ? "" : v[1] + "";
			s += "'}";
		}
		s += "]";
		return s;
	}

	// 获取考核利润报表开头的选择格式
	@Override
	public List GetEnotherReport() {
		String sql = "select t.row_id,t.attribute1 from t_009_8_1_zhjxcblr_khlr_f t where t.row_id = (select min(row_id) from t_009_8_1_zhjxcblr_khlr_f where attribute1 = t.attribute1) and t.row_id != 1027301";
		List l = this.findByNativeQuery(sql);
		// 获取报表选择的下拉框数据
		ArrayList<HashMap> al = new ArrayList<HashMap>();
		for (int i = 0; i < l.size(); i++) {
			HashMap<String, String> h = new HashMap<String, String>();
			Object[] v = (Object[]) l.get(i);
			h.put("id", v[0].toString());
			h.put("text", v[1].toString());
			al.add(h);
		}
		return al;
	}

	// 获取表格开头的年份格式

	@Override
	public String GetyearData() {
		String nowyear = year + "";
		String sql = "select distinct w.year_code,w.year_dsc from w_period_m_d w where w.year_code between '2013' and '"
				+ nowyear + "' order by w.year_code desc";
		List list = this.findByNativeQuery(sql);
		String s = "[";
		for (int i = 0; i < list.size(); i++) {
			Object[] v = (Object[]) list.get(i);
			if (i > 0) {
				s += ",";
			}
			s += "{'yearId':'";
			s += v[0] == null ? "" : v[0] + "";
			s += "','yearDsc':'";
			s += v[1] == null ? "" : v[1] + "";
			s += "'}";
		}
		s += "]";
		return s;
	}

	// 获取类别下拉格式
	@Override
	public String GetCategoryData(String categorytext) {
		String sql = "select t.row_id,t.kpi_classify from  t_009_1_1_zhjxsr_khsr_f t where t.row_id =(select min(row_id) from t_009_1_1_zhjxsr_khsr_f where kpi_classify = t.kpi_classify)";
		List l = this.findByNativeQuery(sql);
		String s = "[";
		for (int i = 0; i < l.size(); i++) {
			Object[] v = (Object[]) l.get(i);
			if (i > 0) {
				s += ",";
			}
			s += "{'rowId':'";
			s += v[0] == null ? "" : v[0] + "";
			s += "','kpiClassify':'";
			s += v[1] == null ? "" : v[1] + "";
			s += "'}";
		}
		s += "]";
		return s;
		/*
		 * String sql =
		 * "select t.row_id,t.kpi_classify from  t_009_1_1_zhjxsr_khsr_f t where t.row_id =(select min(row_id) from t_009_1_1_zhjxsr_khsr_f where kpi_classify = t.kpi_classify)"
		 * ; List l= this.findByNativeQuery(sql); String s = "["; for(int i=0 ;
		 * i<l.size() ; i++){ Object[] v = (Object[])l.get(i); if(i>0){ s+=",";
		 * } s+="{'rowId':'"; s+=v[0]==null?"":v[0]+""; s+="','kpiClassify':'";
		 * s+=v[1]==null?"":v[1]+""; s+="'}"; } s+="]"; return s;
		 */
	}

	@Override
	public String GetQuarterData() {
		String sql = " select t.row_id,t.period  from t_009_6_1_zhjxcblr_gdys_f t where row_id =( select min(row_id) from t_009_6_1_zhjxcblr_gdys_f t where period =t.period)";
		List list = this.findByNativeQuery(sql);
		String s = "[";
		for (int i = 0; i < list.size(); i++) {
			Object[] v = (Object[]) list.get(i);
			if (i > 0) {
				s += ",";
			}
			s += "{'rowId':'";
			s += v[0] == null ? "" : v[0] + "";
			s += "','perIod':'";
			s += v[1] == null ? "" : v[1] + "";
			s += "'}";
		}
		s += "]";
		return s;
	}

	// 获取90007类别
	@Override
	public String GetStyleData(String styletext) {
		String sql = "select  tt.attribute2,tt.report_kpi_type from w_kpi_mapping_d tt where tt.report_id='90007' and tt.attribute2 =(select min(attribute2) from w_kpi_mapping_d where report_kpi_type = tt.report_kpi_type) order by tt.attribute2 ";
		List l = this.findByNativeQuery(sql);
		String s = "[";
		for (int i = 0; i < l.size(); i++) {
			Object[] v = (Object[]) l.get(i);
			if (i > 0) {
				s += ",";
			}
			s += "{'attreibute':'";
			s += v[0] == null ? "" : v[0] + "";
			s += "','styletext':'";
			s += v[1] == null ? "" : v[1] + "";
			s += "'}";
		}
		s += "]";
		return s;
	}

	@Override
	public <T> List<T> findBy(Class<T> entityClass, String propertyName,
			Object value, Map<String, Boolean> orderBy) {
		// TODO Auto-generated method stub
		return null;
	}

	// 获取90009专业
	@Override
	public String GetCtData(String cttext) {
		String sql = "select wsd.row_id,wsd.sp_name from w_sp_d wsd order by wsd.row_id";
		List l = this.findByNativeQuery(sql);
		String s = "[";
		for (int i = 0; i < l.size(); i++) {
			Object[] v = (Object[]) l.get(i);
			if (i > 0) {
				s += ",";
			}
			s += "{'ctid':'";
			s += v[0] == null ? "" : v[0] + "";
			s += "','cttext':'";
			s += v[1] == null ? "" : v[1] + "";
			s += "'}";
		}
		s += "]";
		return s;
	}

}
