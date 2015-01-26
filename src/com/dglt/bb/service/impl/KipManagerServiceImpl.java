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
//��תҳ��

@Service(value = "KipManagerService")
public class KipManagerServiceImpl extends BaseServiceImpl implements KipManagerService {
	 /*��ѯ��ͼ����
	    * ����
	    *    period �ڼ���
	    *    profess רҵ��� 
	    *    branch  �ֹ�˾���
	    *    bizcs   Ӫ�����
	    *    productName ��Ʒ���
	    *    client  �ͻ����
	    *    type    ҳ��������
	    *    kipId   ָ��ID
	    * */
	@Override
	public List getPie(String period,String profess,String branch,String bizcs ,String productName,String client,String type ,String kipId ) {
	    //ͨ�����ݿ����� �õ���ͼ����������
		String sqlPara =  "SELECT * FROM W_KPI_DATA_CONF c WHERE c.kpi_id ="+kipId+"  AND c.page_type ='"+type+"' AND c.map_type='pie'" ;   
    	List sqlL=  this.findByNativeQuery(sqlPara);
    	Object[] sqlOb =(Object[])sqlL.get(0);
    	String sqlStr=(String)sqlOb[4];
    	String colunmString =(String)sqlOb[6] ;
    	String [] colunm = colunmString.split(",");
    	
       //ȡ��Ӧ ��SQL
	   //ƴ�ӱ�ͼ�����ݲ�ѯSQL   
    	
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
		//�����ѯ���������ݲ��ǳ־û�����ʱ������ת��
		ArrayList<HashMap> al = ClassUtil.getReturnList(l, colunm);
		return al;
	}
 
	 /*��ѯ ��ͼ����
	    * ����
	    *    period �ڼ���
	    *    profess רҵ��� 
	    *    branch  �ֹ�˾���
	    *    bizcs   Ӫ�����
	    *    productName ��Ʒ���
	    *    client  �ͻ����
	    *    type    ҳ��������
	    *    kipId   ָ��ID
	    * */
	@Override
	 public List getLine(String period,String profess,String branch,String bizcs,int monthId ,String productCode,String client ,String managerCode ,String type ,String kipId ) {
		StringBuffer sql = new StringBuffer("");
		//ͨ�����ݿ����� �õ���ͼ����������
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
		//�����ѯ���������ݲ��ǳ־û�����ʱ������ת��
		ArrayList<HashMap> al = ClassUtil.getReturnList(l, colunm);
        //�����ѯ���������ݲ��ǳ־û�����ʱ������ת��
		 return al;
	}
   /*��ѯ ��ͼ����
    * ����
    *    period �ڼ���
    *    profess רҵ��� 
    *    branch  �ֹ�˾���
    *    bizcs   Ӫ�����
    *    productName ��Ʒ���
    *    client  �ͻ����
    *    type    ҳ��������
    *    kipId   ָ��ID
    * */
	
	public List getMap(String period,String profess,String branch,String bizcs ,String productName,String client,String type  ,String kipId ){
		//ͨ�����ݿ����� �õ���ͼ����������
	    String sqlPara =  "SELECT * FROM W_KPI_DATA_CONF c WHERE c.kpi_id ="+kipId+"  AND c.page_type ='"+type+"' AND c.map_type='map'" ;   
    	List sqlL=  this.findByNativeQuery(sqlPara);
    	Object[] sqlOb =(Object[])sqlL.get(0);
    	String sqlStr=(String)sqlOb[4];
    	String colunmString =(String)sqlOb[6] ;
    	String [] colunm = colunmString.split(",");
    	
       //ȡ��Ӧ ��SQL
	   //ƴ��SQL   
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
		//�����ѯ���������ݲ��ǳ־û�����ʱ������ת��
		ArrayList<HashMap> al = ClassUtil.getReturnListArea(l, colunm);
		return al;
	}
	
	
	
}
