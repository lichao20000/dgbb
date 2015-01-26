package com.dglt.statement.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dglt.base.util.ClassUtil;
import com.dglt.comm.base.BaseServiceImpl;
import com.dglt.statement.service.LoadReportDataService;

@Service(value = "loadReportDataService")
public class LoadReportDataServiceImpl extends BaseServiceImpl implements
LoadReportDataService {
	public String order = " order by t.period,wkd.row_id";

	@Override
	public List getData(String sqlNo, String sql, Map<String,String>searchCond,String[] arry) {
//		// 获取当前日期的前一个月
//		Calendar cal = Calendar.getInstance();
//		int year = cal.get(Calendar.YEAR);
//		int month = cal.get(Calendar.MONTH);
		

		
		System.out.println("sql显示="+sql);
		
		@SuppressWarnings("rawtypes")
		List l = this.findByNativeQuery(sql);
		// 报表查询，返回数据不是持久化对象时，进行转换
		
		System.out.println("l=:"+l.size());
		
		
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
