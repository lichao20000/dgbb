package com.dglt.bb.service.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import com.dglt.comm.base.BaseServiceImpl;
import com.dglt.base.util.ClassUtil;
import com.dglt.bb.pojo.SqlPara;
import com.dglt.bb.service.KipManagerService;
//跳转页面

@Service(value = "KipManagerService")
public class KipManagerServiceImpl extends BaseServiceImpl implements KipManagerService {
	 /*查询饼图数据
	    * 参数
	    *    period 期间编号
	    *    profess 专业编号 
	    *    branch  分公司编号
	    *    bizcs   营服编号
	    *    productName 产品编号
	    *    client  客户编号
	    *    type    页面层层类型
	    *    kipId   指标ID
	    * */
	@Override
	public List getPie(String period,String profess,String branch,String bizcs ,String productName,String client,String type ,String kipId ) {
	    //通过数据库配置 得到饼图的数据配置
		String sqlPara =  "SELECT * FROM W_KPI_DATA_CONF c WHERE c.kpi_id ="+kipId+"  AND c.page_type ='"+type+"' AND c.map_type='pie'" ;   
    	List sqlL=  this.findByNativeQuery(sqlPara);
    	Object[] sqlOb =(Object[])sqlL.get(0);
    	String sqlStr=(String)sqlOb[4];
    	String colunmString =(String)sqlOb[6] ;
    	String [] colunm = colunmString.split(",");
    	
       //取对应 的SQL
	   //拼接饼图的数据查询SQL   
    	
		StringBuffer sql = new StringBuffer("");
		sql.append(" and v.Month_Id = ");
		sql.append(period) ;
		if(profess!=null&&!"".equals(profess)){
			sql.append(" and v.Sp_Code = '");
			sql.append(profess) ;	
			sql.append("'");
		}
		if(branch!=null&&!"".equals(branch)){
			sql.append(" and v.District_Branch_Code = '");
			sql.append(branch) ;		
			sql.append("'");
	     }
		if(bizcs!=null&&!"".equals(bizcs)){
			sql.append(" and v.Busi_Sc_Code = '");
			sql.append(bizcs) ;
			sql.append("'");
		}
		if(productName!=null&&!"".equals(productName)){
			sql.append(" and v.Product_Code = '");
			sql.append(productName) ;
			sql.append("'");
		}
		if(client!=null&&!"".equals(client)){
			sql.append(" and v.client_id = '");
			sql.append(client) ;
			sql.append("'");
		}

		sqlStr=	sqlStr.replace("#SQLCON#", sql.toString());
		System.out.println(sqlStr);
		List l = this.findByNativeQuery(sqlStr);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnList(l, colunm);
		return al;
	}
 
	 /*查询 线图数据
	    * 参数
	    *    period 期间编号
	    *    profess 专业编号 
	    *    branch  分公司编号
	    *    bizcs   营服编号
	    *    productName 产品编号
	    *    client  客户编号
	    *    type    页面层层类型
	    *    kipId   指标ID
	    * */
	@Override
	 public List getLine(String period,String profess,String branch,String bizcs,int monthId ,String productCode,String client ,String managerCode ,String type ,String kipId ) {
		StringBuffer sql = new StringBuffer("");
		//通过数据库配置 得到线图的数据配置
		  String sqlPara =  "SELECT * FROM W_KPI_DATA_CONF c WHERE c.kpi_id ="+kipId+"  AND c.page_type ='"+type+"' AND c.map_type='line'" ;  
	    	List sqlL=  this.findByNativeQuery(sqlPara);
	    	Object[] sqlOb =(Object[])sqlL.get(0);
	    	String sqlStr=(String)sqlOb[4];
	    	String colunmString =(String)sqlOb[6] ;
	    	String [] colunm = colunmString.split(",");
		
		sql.append(" and  v.MONTH_ID in( ") ;
	    sql.append(ClassUtil.getMonthIdStr(ClassUtil.getMonthId(monthId)));
	    sql.append(") ") ;
	    if(profess!=null&&!"".equals(profess)){
			sql.append("and Sp_Code = '");
			sql.append(profess) ;	
			sql.append("'");
		}
		if(branch!=null&&!"".equals(branch)){
			sql.append("and v.District_Branch_Code = '");
			sql.append(branch) ;	
			sql.append("'");
	     }
		if(bizcs!=null&&!"".equals(bizcs)){
			sql.append("and v.Busi_Sc_Code = '");
			sql.append(bizcs) ;
			sql.append("'");
		}
		if(productCode!=null&&!"".equals(productCode)){
			sql.append("and v.Product_Code = '");
			sql.append(productCode) ;
			sql.append("'");
		}
		
		if(client!=null&&!"".equals(client)){
			sql.append(" and v.client_id = '");
			sql.append(client) ;
			sql.append("'");
		}
		if(managerCode!=null&&!"".equals(managerCode)){
			sql.append(" and v.MANAGER_NO = '");
			sql.append(managerCode) ;
			sql.append("'");
		}
		

		sqlStr=	sqlStr.replace("#SQLCON#", sql.toString());
		System.out.println(sql.toString());
		List l = this.findByNativeQuery(sqlStr.toString());
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnList(l, colunm);
        //报表查询，返回数据不是持久化对象时，进行转换
		 return al;
	}
   /*查询 地图数据
    * 参数
    *    period 期间编号
    *    profess 专业编号 
    *    branch  分公司编号
    *    bizcs   营服编号
    *    productName 产品编号
    *    client  客户编号
    *    type    页面层层类型
    *    kipId   指标ID
    * */
	
	public List getMap(String period,String profess,String branch,String bizcs ,String productName,String client,String type  ,String kipId ){
		//通过数据库配置 得到地图的数据配置
	    String sqlPara =  "SELECT * FROM W_KPI_DATA_CONF c WHERE c.kpi_id ="+kipId+"  AND c.page_type ='"+type+"' AND c.map_type='map'" ;   
    	List sqlL=  this.findByNativeQuery(sqlPara);
    	Object[] sqlOb =(Object[])sqlL.get(0);
    	String sqlStr=(String)sqlOb[4];
    	String colunmString =(String)sqlOb[6] ;
    	String [] colunm = colunmString.split(",");
    	
       //取对应 的SQL
	   //拼接SQL   
		StringBuffer sql = new StringBuffer("");
		sql.append(" and v.Month_Id = ");
		sql.append(period) ;
		if(profess!=null&&!"".equals(profess)){
			sql.append(" and v.Sp_Code = '");
			sql.append(profess) ;	
			sql.append("'");
		}
		if(branch!=null&&!"".equals(branch)){
			sql.append(" and v.District_Branch_Code = '");
			sql.append(branch) ;		
			sql.append("'");
	     }
		if(bizcs!=null&&!"".equals(bizcs)){
			sql.append(" and v.Busi_Sc_Code = '");
			sql.append(bizcs) ;
			sql.append("'");
		}
		if(productName!=null&&!"".equals(productName)){
			sql.append(" and v.Product_Code = '");
			sql.append(productName) ;
			sql.append("'");
		}
		if(client!=null&&!"".equals(client)){
			sql.append(" and v.client_id = '");
			sql.append(client) ;
			sql.append("'");
		}
		sqlStr=	sqlStr.replace("#SQLCON#", sql.toString());
		System.out.println(sqlStr);
		List l = this.findByNativeQuery(sqlStr);
		//报表查询，返回数据不是持久化对象时，进行转换
		ArrayList<HashMap> al = ClassUtil.getReturnListArea(l, colunm);
		return al;
	}
	
	
	
}
