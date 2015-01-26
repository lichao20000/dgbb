package com.dglt.bb.service.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import com.dglt.comm.base.BaseServiceImpl;
import com.dglt.base.util.ClassUtil;
import com.dglt.bb.pojo.SqlPara;
import com.dglt.bb.service.ProfitBudgetService;
//跳转页面

@Service(value = "ProfitBudgetService")
public class ProfitBudgetServiceImpl extends BaseServiceImpl implements ProfitBudgetService {

	@Override
	public List getArea(String period, String profess, String branch,
	      String bizcs,String productName,String client ) {
		  Calendar ca = Calendar.getInstance();
	      int year = ca.get(Calendar.YEAR);//获取年份
	      int month=ca.get(Calendar.MONTH)+1;//获取月份
	      int monthId = 0;
	      month--;
	      if(month==0){
    		  year --;
    		  month=12;
    	  }
    	
    	  if (period==null||"".equals(period)){
    		  monthId= year*100+month;
        	 // monthId = 201201;  
    	  }else  {
    		  monthId=Integer.parseInt(period) ; 
    		  
    	  }
    	  
	   //拼接SQL   
		StringBuffer sql = new StringBuffer("");
		sql.append(SqlPara.profitBudgetMap) ;
		sql.append(" and t.Month_Id = ");
		sql.append(monthId) ;
		if(profess!=null&&!"".equals(profess)){
			sql.append(" and t.Sp_Code = '");
			sql.append(profess) ;	
			sql.append("'");
		}
		if(branch!=null&&!"".equals(branch)){
			sql.append(" and t.District_Branch_Code = '");
			sql.append(branch) ;		
			sql.append("'");
	     }
		if(bizcs!=null&&!"".equals(bizcs)){
			sql.append(" and t.Busi_Sc_Code = '");
			sql.append(bizcs) ;
			sql.append("'");
		}
		if(productName!=null&&!"".equals(productName)){
			sql.append(" and t.Product_Code = '");
			sql.append(productName) ;
			sql.append("'");
		}
		sql.append(" group by t.District_Branch_Code,t.District_Branch_Name");
		System.out.println(sql.toString());
		String [] arry = SqlPara.arrprofitBudgetMap ;
		List l = this.findByNativeQuery(sql.toString());
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnListArea(l, arry);
		return al;
	}



	@Override
	public List getLine(String period, String profess, String branch,
			String bizcs,  int monthId,String productCode,String client  ) {
		StringBuffer sql = new StringBuffer("");
		sql.append(SqlPara.profitBudgetLine) ;
		sql.append(" and  MONTH_ID in( ") ;
	    sql.append(ClassUtil.getMonthIdStr(ClassUtil.getMonthId(monthId)));
	    sql.append(") ") ;
	    if(profess!=null&&!"".equals(profess)){
			sql.append("and t.Sp_Code = '");
			sql.append(profess) ;	
			sql.append("'");
		}
		if(branch!=null&&!"".equals(branch)){
			sql.append("and t.District_Branch_Code = '");
			sql.append(branch) ;	
			sql.append("'");
	     }
		if(bizcs!=null&&!"".equals(bizcs)){
			sql.append("and t.Busi_Sc_Code = '");
			sql.append(bizcs) ;
			sql.append("'");
		}
		if(productCode!=null&&!"".equals(productCode)){
			sql.append("and t.Product_Code = '");
			sql.append(productCode) ;
			sql.append("'");
		}
		sql.append(" group by t.Month_Id,t.Month_Dsc ORDER BY  t.Month_Id ");
        String [] arry = SqlPara.arrprofitBudgetLine ;
		System.out.println(sql.toString());
	    List l = this.findByNativeQuery(sql.toString());
        //报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
				return al;
	}
 

	
}
