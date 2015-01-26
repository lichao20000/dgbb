package com.dglt.bb.service.impl;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.dglt.comm.base.BaseServiceImpl;
import com.dglt.bb.dao.DashBoardDao;
import com.dglt.bb.pojo.DashTagging;
import com.dglt.bb.pojo.SqlPara;
import com.dglt.bb.pojo.WKipConfigV;
import com.dglt.bb.pojo.WKpiD;
import com.dglt.bb.service.GetTableDataService;
//跳转页面

@Service(value = "GetTableDataService")
public class GetTableDataServiceImpl extends BaseServiceImpl implements GetTableDataService {
	
	@Resource(name = "dashBoardDao")
	private DashBoardDao  dashBoardDao ;

	@Override
	public List getTableData(String period, String profess, String branch,
			String bizcs, String manager_No,String productName, String kipId, String clentCode) {
		// TODO Auto-generated method stub
	 HashMap <String, String> map =new HashMap<String, String>();
	      	
	 period= period==null?"":period ;
	 profess= profess==null?"":profess ;
	 branch= branch==null?"":branch ;
	 bizcs= bizcs==null?"":bizcs ;
	 manager_No= manager_No==null?"":manager_No ;
	 productName= productName==null?"":productName ; 
	 kipId= kipId==null?"":kipId ;
	 clentCode= clentCode==null?"":clentCode ;
	     map.put("month_id",period);
	     map.put("sp_id",profess);
		 map.put("product_code",productName);
	     map.put("client_code",clentCode);
		 map.put("kpi_id",kipId);
		 map.put("qufen_id",branch);
	     map.put("quyu_id",bizcs);
	     map.put("manager_No",manager_No);
	     String [] arry = SqlPara.arr12gTable ;
	    List l =  this.dashBoardDao.getDashBoardGridData(map);	
		//报表查询，返回数据不是持久化对象时，进行转换
		//ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
		return l;
	}

	@Override
	public WKipConfigV getWKipConfigVbyKpiId(String kpiId ,String pageType) {
		
		StringBuffer jpql = new StringBuffer(
		"from WKipConfigV dtr where dtr.kpiId='").append(kpiId).append("' and dtr.pageType='").append(pageType).append("'");
		List l = this.find(jpql.toString());
		return (l==null|l.size()==0)?null:(WKipConfigV)l.get(0) ;
	}

	@Override
	public double getDashBoardKpiP(String period, String profess, String branch,
			String bizcs, String productName, String kipId, String clentId) {
		 HashMap <String, String> map =new HashMap<String, String>();
	      	
		 period= period==null?"":period ;
		 profess= profess==null?"":profess ;
		 branch= branch==null?"":branch ;
		 bizcs= bizcs==null?"":bizcs ;
		 productName= productName==null?"":productName ; 
		 kipId= kipId==null?"":kipId ;
		 clentId= clentId==null?"":clentId ;
		     map.put("month_id",period);
		     map.put("sp_id",profess);
			 map.put("product_code",productName);
		     map.put("client_code",clentId);
			 map.put("kpi_id",kipId);
			 map.put("qufen_id",branch);
		     map.put("quyu_id",bizcs);
		     String [] arry = SqlPara.arr12gTable ;
		     double  l =  this.dashBoardDao.getDashBoardKpiP(map);	
			//报表查询，返回数据不是持久化对象时，进行转换
			//ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
			return l;
	}

	@Override
	public List getDashBoardRepDituQufenP(String period, String profess,
			String branch, String bizcs, String productName, String kipId,
			String clentId) {
		 HashMap <String, String> map =new HashMap<String, String>();
		 period= period==null?"":period ;
		 profess= profess==null?"":profess ;
		 branch= branch==null?"":branch ;
		 bizcs= bizcs==null?"":bizcs ;
		 productName= productName==null?"":productName ; 
		 kipId= kipId==null?"":kipId ;
		 clentId= clentId==null?"":clentId ;
		     map.put("month_id",period);
		     map.put("sp_id",profess);
			 map.put("product_code",productName);
		     map.put("client_code",clentId);
			 map.put("kpi_id",kipId);
			 map.put("qufen_id",branch);
		     map.put("quyu_id",bizcs);
		     String [] arry = SqlPara.arr12gTable ;
		    List l =  this.dashBoardDao.getDashBoardRepDituQufenP(map);	
			//报表查询，返回数据不是持久化对象时，进行转换
			//ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
			return l;
	}

	@Override
	public List getDashBoardRepDituYfP(String period, String profess,
			String branch, String bizcs, String productName, String kipId,
			String clentId) {
		 HashMap <String, String> map =new HashMap<String, String>();
		 period= period==null?"":period ;
		 profess= profess==null?"":profess ;
		 branch= branch==null?"":branch ;
		 bizcs= bizcs==null?"":bizcs ;
		 productName= productName==null?"":productName ; 
		 kipId= kipId==null?"":kipId ;
		 clentId= clentId==null?"":clentId ;
		     map.put("month_id",period);
		     map.put("sp_id",profess);
			 map.put("product_code",productName);
		     map.put("client_code",clentId);
			 map.put("kpi_id",kipId);
			 map.put("qufen_id",branch);
		     map.put("quyu_id",bizcs);
		     String [] arry = SqlPara.arr12gTable ;
		    List l =  this.dashBoardDao.getDashBoardRepDituYfP(map);	
			//报表查询，返回数据不是持久化对象时，进行转换
			//ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
			return l;
	}

	@Override
	public List getDashBoardReportQufenP(String period, String profess,
			String branch, String bizcs, String productName, String kipId,
			String clentId) {
		 HashMap <String, String> map =new HashMap<String, String>();
		 period= period==null?"":period ;
		 profess= profess==null?"":profess ;
		 branch= branch==null?"":branch ;
		 bizcs= bizcs==null?"":bizcs ;
		 productName= productName==null?"":productName ; 
		 kipId= kipId==null?"":kipId ;
		 clentId= clentId==null?"":clentId ;
		     map.put("month_id",period);
		     map.put("sp_id",profess);
			 map.put("product_code",productName);
		     map.put("client_code",clentId);
			 map.put("kpi_id",kipId);
			 map.put("qufen_id",branch);
		     map.put("quyu_id",bizcs);
		     String [] arry = SqlPara.arr12gTable ;
		    List l =  this.dashBoardDao.getDashBoardReportQufenP(map);	
			//报表查询，返回数据不是持久化对象时，进行转换
			//ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
			return l;
	}

	@Override
	public List getDashBoardReportSilongP(String period, String profess,
			String branch, String bizcs, String productName, String kipId,
			String clentId) {
		 HashMap <String, String> map =new HashMap<String, String>();
		 period= period==null?"":period ;
		 profess= profess==null?"":profess ;
		 branch= branch==null?"":branch ;
		 bizcs= bizcs==null?"":bizcs ;
		 productName= productName==null?"":productName ; 
		 kipId= kipId==null?"":kipId ;
		 clentId= clentId==null?"":clentId ;
		     map.put("month_id",period);
		     map.put("sp_id",profess);
			 map.put("product_code",productName);
		     map.put("client_code",clentId);
			 map.put("kpi_id",kipId);
			 map.put("qufen_id",branch);
		     map.put("quyu_id",bizcs);
		     String [] arry = SqlPara.arr12gTable ;
		    List l =  this.dashBoardDao.getDashBoardReportSilongP(map);	
			//报表查询，返回数据不是持久化对象时，进行转换
			//ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
			return l;
	}

	@Override
	public List getDashBoardRepQufenPLine(String period, String profess,
			String branch, String bizcs, String productName, String kipId,
			String clentId) {
		 HashMap <String, String> map =new HashMap<String, String>();
		 period= period==null?"":period ;
		 profess= profess==null?"":profess ;
		 branch= branch==null?"":branch ;
		 bizcs= bizcs==null?"":bizcs ;
		 productName= productName==null?"":productName ; 
		 kipId= kipId==null?"":kipId ;
		 clentId= clentId==null?"":clentId ;
		     map.put("month_id",period);
		     map.put("sp_id",profess);
			 map.put("product_code",productName);
		     map.put("client_code",clentId);
			 map.put("kpi_id",kipId);
			 map.put("qufen_id",branch);
		     map.put("quyu_id",bizcs);
		     String [] arry = SqlPara.arr12gTable ;
		    List l =  this.dashBoardDao.getDashBoardZXQuFenP(map);	
			//报表查询，返回数据不是持久化对象时，进行转换
			//ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
			return l;
	}

	@Override
	public List getDashBoardRepYfPLine(String period, String profess,
			String branch, String bizcs, String productName, String kipId,
			String clentId) {
		 HashMap <String, String> map =new HashMap<String, String>();
		 period= period==null?"":period ;
		 profess= profess==null?"":profess ;
		 branch= branch==null?"":branch ;
		 bizcs= bizcs==null?"":bizcs ;
		 productName= productName==null?"":productName ; 
		 kipId= kipId==null?"":kipId ;
		 clentId= clentId==null?"":clentId ;
		     map.put("month_id",period);
		     map.put("sp_id",profess);
			 map.put("product_code",productName);
		     map.put("client_code",clentId);
			 map.put("kpi_id",kipId);
			 map.put("qufen_id",branch);
		     map.put("quyu_id",bizcs);
		     String [] arry = SqlPara.arr12gTable ;
		    List l =  this.dashBoardDao.getDashBoardZXYFP(map);	
			//报表查询，返回数据不是持久化对象时，进行转换
			//ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
			return l;
	}
	@Override
	public List getDashBoardRepxsjlPLine(String period, String profess,
			String branch, String bizcs,String manager_No, String productName, String kipId,
			String clentId) {
		 HashMap <String, String> map =new HashMap<String, String>();
		 period= period==null?"":period ;
		 profess= profess==null?"":profess ;
		 branch= branch==null?"":branch ;
		 bizcs= bizcs==null?"":bizcs ;
		 manager_No= manager_No==null?"":manager_No ;
		 productName= productName==null?"":productName ; 
		 kipId= kipId==null?"":kipId ;
		 clentId= clentId==null?"":clentId ;
		     map.put("month_id",period);
		     map.put("sp_id",profess);
			 map.put("product_code",productName);
		     map.put("client_code",clentId);
			 map.put("kpi_id",kipId);
			 map.put("qufen_id",branch);
		     map.put("quyu_id",bizcs);
		     map.put("manager_No",manager_No);
		     String [] arry = SqlPara.arr12gTable ;
		    List l =  this.dashBoardDao.getDashBoardZXxsjlP(map);	
			//报表查询，返回数据不是持久化对象时，进行转换
			//ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
			return l;
	}

	@Override
	public List getTableDataYf(String period, String profess, String branch,
			String bizcs, String manager_No, String productName, String kipId,
			String clentId) {
		 HashMap <String, String> map =new HashMap<String, String>();
	      	
		 period= period==null?"":period ;
		 profess= profess==null?"":profess ;
		 branch= branch==null?"":branch ;
		 bizcs= bizcs==null?"":bizcs ;
		 manager_No= manager_No==null?"":manager_No ;
		 productName= productName==null?"":productName ; 
		 kipId= kipId==null?"":kipId ;
		 clentId= clentId==null?"":clentId ;
		     map.put("month_id",period);
		     map.put("sp_id",profess);
			 map.put("product_code",productName);
		     map.put("client_code",clentId);
			 map.put("kpi_id",kipId);
			 map.put("qufen_id",branch);
		     map.put("quyu_id",bizcs);
		     map.put("manager_No",manager_No);
		     String [] arry = SqlPara.arr12gTable ;
		    List l =  this.dashBoardDao.getDashBoardGridDataYf(map);
		    ;	
			//报表查询，返回数据不是持久化对象时，进行转换
			//ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
			return l;
	}

	@Override
	public List getTableDataxsjl(String period, String profess, String branch,
			String bizcs, String manager_No, String productName, String kipId,
			String clentId) {
		 HashMap <String, String> map =new HashMap<String, String>();
	      	
		 period= period==null?"":period ;
		 profess= profess==null?"":profess ;
		 branch= branch==null?"":branch ;
		 bizcs= bizcs==null?"":bizcs ;
		 manager_No= manager_No==null?"":manager_No ;
		 productName= productName==null?"":productName ; 
		 kipId= kipId==null?"":kipId ;
		 clentId= clentId==null?"":clentId ;
		     map.put("month_id",period);
		     map.put("sp_id",profess);
			 map.put("product_code",productName);
		     map.put("client_code",clentId);
			 map.put("kpi_id",kipId);
			 map.put("qufen_id",branch);
		     map.put("quyu_id",bizcs);
		     map.put("manager_No",manager_No);
		     String [] arry = SqlPara.arr12gTable ;
		    List l =  this.dashBoardDao.getDashBoardGridDataxsjl(map);	
			//报表查询，返回数据不是持久化对象时，进行转换
			//ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
			return l;
	}

	@Override
	public List getDashBoardRepDituxsjlP(String period, String profess,
			String branch, String bizcs, String productName, String kipId,
			String clentId) {
		 HashMap <String, String> map =new HashMap<String, String>();
		 period= period==null?"":period ;
		 profess= profess==null?"":profess ;
		 branch= branch==null?"":branch ;
		 bizcs= bizcs==null?"":bizcs ;
		 productName= productName==null?"":productName ; 
		 kipId= kipId==null?"":kipId ;
		 clentId= clentId==null?"":clentId ;
		     map.put("month_id",period);
		     map.put("sp_id",profess);
			 map.put("product_code",productName);
		     map.put("client_code",clentId);
			 map.put("kpi_id",kipId);
			 map.put("qufen_id",branch);
		     map.put("quyu_id",bizcs);
		     String [] arry = SqlPara.arr12gTable ;
		    List l =  this.dashBoardDao.getDashBoardRepDituxsjlP(map);	
			//报表查询，返回数据不是持久化对象时，进行转换
			//ArrayList<HashMap> al = ClassUtil.getReturnList(l, arry);
			return l;
	}
}
