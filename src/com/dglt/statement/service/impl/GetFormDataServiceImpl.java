package com.dglt.statement.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dglt.base.util.ClassUtil;
import com.dglt.comm.base.BaseServiceImpl;
import com.dglt.statement.service.GetFormDataService;

@Service(value = "getFormDataService")
public class GetFormDataServiceImpl extends BaseServiceImpl implements
		GetFormDataService {
	public String order = " order by t.period,wkd.row_id";

	@Override
	public List getData(String sqlNo, String period, String profess,
			String branch, String bizcs, String[] arry, String sql,
			String monthId, String text, String categorytext, String yearId,
			String style, String cttext) {
		// 获取当前日期的前一个月
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		// 考核利润关联的第九张报表sql，当ID为9时拼接条件查询
		String timer = yearId + "-" + monthId;
		
		String initSql = sql;

		if (!("9".equals(sqlNo))) {
			if (!("10".equals(sqlNo))) {
				if ("3".equals(sqlNo)) {
					sql += " where to_char(t.period,'yyyy') = '" + yearId
							+ "' group by t.period order by t.period";
				} else {
					if ("8".equals(sqlNo)) {
						// 待修改
						if (yearId == null) {
							sql = "select kpi_classify,project,kpi_name,year,attribute1,sum(decode(month, '01', current_total, null)) month1,sum(decode(month, '02', current_total, null)) month2,sum(decode(month, '03', current_total, null)) month3,sum(decode(month, '04', current_total, null)) month4,sum(decode(month, '05', current_total, null)) month5,sum(decode(month, '06', current_total, null)) month6,sum(decode(month, '07', current_total, null)) month7,sum(decode(month, '08', current_total, null)) month8,sum(decode(month, '09', current_total, null)) month9,sum(decode(month, '10', current_total, null)) month10,sum(decode(month, '11', current_total, null)) month11,sum(decode(month, '12', current_total, null)) month12 from (select b.kpi_classify,b.project,c.report_kpi_name kpi_name,to_char(b.period, 'yyyy') year,to_char(b.period, 'mm') month, b.current_total,b.kpi_name attribute1,c.report_id || c.attribute3 attribute3 from t_009_1_1_zhjxsr_khsr_f b, w_kpi_mapping_d c where b.kpi_name = c.report_id || c.attribute2) where year = '"
									+ yearId
									+ "'"
									+ " group by kpi_classify, project, kpi_name, year, attribute1, attribute3 order by attribute1";
						} else {
							sql = "select kpi_classify,project,kpi_name,year,attribute1,sum(decode(month, '01', current_total, null)) month1,sum(decode(month, '02', current_total, null)) month2,sum(decode(month, '03', current_total, null)) month3,sum(decode(month, '04', current_total, null)) month4,sum(decode(month, '05', current_total, null)) month5,sum(decode(month, '06', current_total, null)) month6,sum(decode(month, '07', current_total, null)) month7,sum(decode(month, '08', current_total, null)) month8,sum(decode(month, '09', current_total, null)) month9,sum(decode(month, '10', current_total, null)) month10,sum(decode(month, '11', current_total, null)) month11,sum(decode(month, '12', current_total, null)) month12 from (select b.kpi_classify,b.project,c.report_kpi_name kpi_name,to_char(b.period, 'yyyy') year,to_char(b.period, 'mm') month, b.current_total,b.kpi_name attribute1,c.report_id || c.attribute3 attribute3 from t_009_1_1_zhjxsr_khsr_f b, w_kpi_mapping_d c where b.kpi_name = c.report_id || c.attribute2) where year = '"
									+ yearId
									+ "'"
									+ " group by kpi_classify, project, kpi_name, year, attribute1, attribute3 order by attribute1";
						}
					} else {
						if ("2".equals(sqlNo)) {
							if (monthId == null) {
								monthId = "2013" + "-0" + month;
								sql = "select t.PERIOD,t.CATAGORY,t.LOCAL_TELE_TYPE,t.KPI_NAME,t.CURRENT_NUM,t.MONTH_ON_MONTH_LIMIT,t.TOTAL_AMOUNT,t.SCHEDUAL_AVERAGE,wkmd.ATTRIBUTE1 from T_009_3_1_ZHJXSR_JS_F t,W_KPI_MAPPING_D wkmd WHERE t.ATTRIBUTE1 = wkmd.REPORT_ID||wkmd.ATTRIBUTE2"
										+ " and to_char(t.period,'yyyy-MM') = '"
										+ monthId
										+ "'"
										+ " ORDER BY t.PERIOD,wkmd.REPORT_ID||wkmd.ATTRIBUTE2";
								sql = sql.toLowerCase();
							} else {
								sql = "select t.PERIOD,t.CATAGORY,t.LOCAL_TELE_TYPE,t.KPI_NAME,t.CURRENT_NUM,t.MONTH_ON_MONTH_LIMIT,t.TOTAL_AMOUNT,t.SCHEDUAL_AVERAGE,wkmd.ATTRIBUTE1 from T_009_3_1_ZHJXSR_JS_F t,W_KPI_MAPPING_D wkmd WHERE t.ATTRIBUTE1 = wkmd.REPORT_ID||wkmd.ATTRIBUTE2"
										+ " and to_char(t.period,'yyyy-MM') = '"
										+ timer
										+ "'"
										+ " ORDER BY t.PERIOD,wkmd.REPORT_ID||wkmd.ATTRIBUTE2";
							}
						} else {
							if ("5".equals(sqlNo)) {
								if (monthId == null) {
									monthId = "2013Q3";
									sql = "select t.period,w.report_kpi_name,t.QUARTER_FULL_COST_BUDGET,t.FIRST_QUARTER_BUDGET,t.SECOND_QUARTER_BUDGET,t.THIRD_QUARTER_BUDGET,t.FOURTH_QUARTER_BUDGET,t.YEAR_ACCUMULATION,t.over_budget from t_009_6_1_zhjxcblr_gdys_f t,w_kpi_mapping_d w where t.kpi_name = w.report_id||w.attribute2 and w.report_id = '90006' and t.period ='"
											+ monthId
											+ "'  order by w.report_id,w.attribute2";
								} else {
									sql = "select t.period,w.report_kpi_name,t.QUARTER_FULL_COST_BUDGET,t.FIRST_QUARTER_BUDGET,t.SECOND_QUARTER_BUDGET,t.THIRD_QUARTER_BUDGET,t.FOURTH_QUARTER_BUDGET,t.YEAR_ACCUMULATION,t.over_budget from t_009_6_1_zhjxcblr_gdys_f t,w_kpi_mapping_d w where t.kpi_name = w.report_id||w.attribute2 and w.report_id = '90006' and t.period ='"
											+ monthId
											+ "'  order by w.report_id,w.attribute2";
								}

							} else {
								sql = sql.toLowerCase();
								int k = sql.indexOf("where");
								if (k == -1) {
									sql += " where 1=1";
								}
								if (monthId == null) {
									monthId = year + "-" + month;
									sql += " and to_char(t.period,'yyyy-MM') = '"
											+ monthId + "'";
								} else {
									sql += " and to_char(t.period,'yyyy-MM') = '"
											+ timer + "'";
								}
								int g = sql
										.indexOf("wkd.report_id||wkd.attribute2");
								if (g != -1) {
									sql += "order by t.period, wkd.report_id||wkd.attribute2";
								} else {
									int l = sql
											.indexOf(" where t.kpi_name = wkmd.report_id || wkmd.attribute2 and t.attribute1 = wsd.sp_code");
									if (l != -1) {
										sql += " and wsd.sp_name ='" + cttext
												+ "'";
									}
									int z = sql
											.indexOf("where t.kpi_name = wkmd.report_kpi_id and wkmd.report_id = '90007'");
									if (z != -1) {
										sql += " and  wkmd.report_kpi_type='"
												+ style + "'";
									}
									int j = sql.indexOf("w_kpi_mapping_d");
									if (j != -1) {
										sql += " order by wkmd.report_kpi_id";
									}
								}
								int i = sql.indexOf("w_kpi_d");
								if (i != -1) {
									sql += order;
								}

							}
						}
					}
				}
			} else {
				if (monthId == null) {
					sql += "(select tt.period,aa.report_kpi_name,aa.report_id,aa.attribute2,aa.attribute3,tt.kpi_name,to_char(tt.period, 'yyyy') year,to_char(tt.period, 'mm') month,tt.current_total from T_009_8_1_ZHJXCBLR_KHLR_F tt, w_kpi_mapping_d aa where tt.attribute1 = '利润考核指标执行情况' and tt.kpi_name = aa.report_id || aa.attribute2 order by aa.report_id, aa.attribute3) where year = '"
							+ monthId
							+ "' group by report_kpi_name, year, report_id, attribute2,attribute3, kpi_name order by year, report_id || attribute2";
				} else {
					sql += "(select tt.period,aa.report_kpi_name,aa.report_id,aa.attribute2,aa.attribute3,tt.kpi_name,to_char(tt.period, 'yyyy') year,to_char(tt.period, 'mm') month,tt.current_total from T_009_8_1_ZHJXCBLR_KHLR_F tt, w_kpi_mapping_d aa where tt.attribute1 = '利润考核指标执行情况' and tt.kpi_name = aa.report_id || aa.attribute2 order by aa.report_id, aa.attribute3) where year = '"
							+ monthId
							+ "' group by report_kpi_name, year, report_id, attribute2,attribute3, kpi_name order by year, report_id || attribute2";
				}
			}
		} else {
			if (text == null) {
				if (monthId == null) {
					monthId = year + "";
					sql += "(select tt.period,aa.report_kpi_name,aa.report_id,aa.attribute2,aa.attribute3,tt.kpi_name,to_char(tt.period, 'yyyy') year,to_char(tt.period, 'mm') month,tt.current_total from T_009_8_1_ZHJXCBLR_KHLR_F tt, w_kpi_mapping_d aa where tt.kpi_name = aa.report_id || aa.attribute2 order by aa.report_id, aa.attribute3) where year = '"
							+ monthId
							+ "' group by report_kpi_name, year, report_id, attribute2,attribute3, kpi_name order by year, report_id || attribute2";

				} else {
					sql += "(select tt.period,aa.report_kpi_name,aa.report_id,aa.attribute2,aa.attribute3,tt.kpi_name,to_char(tt.period, 'yyyy') year,to_char(tt.period, 'mm') month,tt.current_total from T_009_8_1_ZHJXCBLR_KHLR_F tt, w_kpi_mapping_d aa where tt.kpi_name = aa.report_id || aa.attribute2 order by aa.report_id, aa.attribute3) where year = '"
							+ monthId
							+ "' group by report_kpi_name, year, report_id, attribute2,attribute3, kpi_name order by year, report_id || attribute2";
				}
			} else {
				sql += "(select tt.period,aa.report_kpi_name,aa.report_id,aa.attribute2,aa.attribute3,tt.kpi_name,to_char(tt.period, 'yyyy') year,to_char(tt.period, 'mm') month,tt.current_total from T_009_8_1_ZHJXCBLR_KHLR_F tt, w_kpi_mapping_d aa where tt.attribute1 ='"
						+ text
						+ "' and tt.kpi_name = aa.report_id || aa.attribute2 order by aa.report_id, aa.attribute3)  where year = '"
						+ monthId
						+ "' group by report_kpi_name, year, report_id, attribute2,attribute3, kpi_name order by year, report_id || attribute2";
			}
		}
		
		if ("3002".equals(sqlNo)) {
			sql = initSql;
		}
		
		if ("90013".equals(sqlNo)||"90014".equals(sqlNo)||"90015".equals(sqlNo)||"90016".equals(sqlNo)||"90017".equals(sqlNo)) {
			sql = initSql;
		}
		
		System.out.println("sql="+sql);
		List l = this.findByNativeQuery(sql);
		// 报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnList(sqlNo,l, arry);
		return al;
	}

	@Override
	public <T> List<T> findBy(Class<T> entityClass, String propertyName,
			Object value, Map<String, Boolean> orderBy) {
		// TODO Auto-generated method stub
		return null;
	}

	// 获取报表名称
	@Override
	public String getReportName(String rowId) {
		String sql = "select wrd.show_name from w_report_d wrd where wrd.row_id='"
				+ rowId + "'";
		List l = this.findByNativeQuery(sql);
		
		System.out.println("(String) l.get(0)"+(String) l.get(0));
		
		return (l == null) ? null : (String) l.get(0);
	}
	
  // 处理集客合计
	@Override
	public List<HashMap<String, String>> doJkList(List<HashMap<String, String>> l){
		   int size = l.size() ;
		   
		   if(size==0){
			    return l ;
		   }else {
			   HashMap<String, String> hm =new HashMap<String, String>(); 
			   hm.put("perIod", "合计"); 
				for (int j=1;j<27;j++) {
					Double total = 0.0;
					String kpi = "kpi600";
					if (j<10){
						kpi = kpi+ "0"+j;
					}else{
						kpi = kpi+j;	
					}
					for (int i = 0; i < size; i++) {
						HashMap<String, String> row = l.get(i);
						String kpi_value = row.get(kpi);
						Double value=0.0  ;
						if(kpi_value!=null&!"".equals(kpi_value)){
							value =Double.parseDouble(kpi_value) ;
						}
						total += value;
					}
					hm.put(kpi, total+"");
				}
	           Double kpi60027 =0.0 ;
	            if(Double.parseDouble(hm.get("kpi60023"))!=0.0){
	            	kpi60027 =Double.parseDouble(hm.get("kpi60016"))/Double.parseDouble(hm.get("kpi60023"));
	             }
	            
	            Double kpi60028 =0.0 ;
	            if(Double.parseDouble(hm.get("kpi60023"))!=0.0){
	            	kpi60028 =Double.parseDouble(hm.get("kpi60019"))/Double.parseDouble(hm.get("kpi60024"));
	             }
	            Double kpi60029 =0.0 ;
	            if(Double.parseDouble(hm.get("kpi60023"))!=0.0){
	            	 kpi60029 =Double.parseDouble(hm.get("kpi60018"))/Double.parseDouble(hm.get("kpi60025"));
	             }
	            Double kpi60030 =0.0 ;
	            if(Double.parseDouble(hm.get("kpi60023"))!=0.0){
	            	 kpi60030 =(Double.parseDouble(hm.get("kpi60016"))+Double.parseDouble(hm.get("kpi60018"))+Double.parseDouble(hm.get("kpi60019")))/Double.parseDouble(hm.get("kpi60026"));
	     			
	             }
	         	hm.put("kpi60027", kpi60027+"") ;
				hm.put("kpi60028", kpi60028+"");
				hm.put("kpi60029", kpi60029+"") ;
				hm.put("kpi60030", kpi60030+"");
				l.add(hm) ;   
		   }
		 
		 return  l ;
	}
  //处理考核利润
	
	@Override
	public List<HashMap<String, String>> doKHLYList(List<HashMap<String, String>> l ,List<HashMap<String, String>> l1 ,String name){
		

		HashMap<String, String> h1 =new HashMap<String, String>();
		 h1.put("month10"," ");
		 h1.put("month5"," ");
		 h1.put("month4"," ");
		 h1.put("month7"," ");
		 h1.put("month6"," ");
		 h1.put("month1"," ");
		 h1.put("kpiName"," ");
		 h1.put("month3"," ");
		 h1.put("month2"," ");
		 h1.put("month12"," ");
		 h1.put("year"," ");
		 h1.put("month11"," ");
		 h1.put("month9"," ");
		 h1.put("month8"," ");
		 HashMap<String, String> h2 =new HashMap<String, String>(); 
		 h2.put("month10"," ");
		 h2.put("month5"," ");
		 h2.put("month4"," ");
		 h2.put("month7"," ");
		 h2.put("month6"," ");
		 h2.put("month1"," ");
		 h2.put("kpiName",name);
		 h2.put("month3","  ");
		 h2.put("month2","  ");
		 h2.put("month12"," ");
		 h2.put("year","  ");
		 h2.put("month11"," ");
		 h2.put("month9"," ");
		 h2.put("month8"," ");
		 HashMap<String, String> h3 =new HashMap<String, String>();
		 h3.put("month10","十月");
		 h3.put("month5","五月");
		 h3.put("month4","四月");
		 h3.put("month7","七月");
		 h3.put("month6","六月");
		 h3.put("month1","一月");
		 h3.put("kpiName","项目");
		 h3.put("month3","三月");
		 h3.put("month2","二月");
		 h3.put("month12","十二月");
		 h3.put("year","年");
		 h3.put("month11","十一月");
		 h3.put("month9","九月");
		 h3.put("month8","八月");
		 l.add(h1);
		 l.add(h2) ;
		 l.add(h3);
		 l.addAll(l1);
		 return  l ;
	}
	
	
}
