package com.dglt.bb.dao.impl;

import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

//import oracle.jdbc.driver.OracleTypes;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dglt.base.util.ClassUtil;
import com.dglt.bb.dao.DashBoardDao;
import com.dglt.comm.base.BaseDAOImpl;
import com.dglt.comm.base.Globals;

@Repository("dashBoardDao")
public class DashBoardDaoImpl extends BaseDAOImpl implements DashBoardDao {

	/**
	 * 获取仪表盘网格报表数据
	 * 
	 * @param map
	 *            里值分别为{"month_id":期间'yyyymm格式',"sp_id":'专业ID' product_code
	 *            产品编码,client_code 客户编码 kpi_id 指标ID,qufen_id 区分公司ID quyu_id
	 *            营服中心ID}
	 * @return 网络数据LIST
	 */
	public List getDashBoardGridData(Map<String, String> map) {

		EntityManager em = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List mapList = new ArrayList();
		DecimalFormat   df = new DecimalFormat("#,##0.00%");
		df.setRoundingMode(RoundingMode.HALF_UP);  
		try {
			int x_count = 0;
			int x_retcode = 0;
			String x_message = "";
			em = this.entityManager.getEntityManagerFactory()
					.createEntityManager();
			con = em.unwrap(Session.class).connection();
			cs = con
					.prepareCall("{call jzgl_dw_pkg.gen_dashboard_report_p(?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, map.get("month_id").toString());
			cs.setString(2, map.get("sp_id").toString());
			cs.setString(3, map.get("product_code").toString());
			cs.setString(4, map.get("client_code").toString());
			cs.setString(5, map.get("kpi_id").toString());
			cs.setString(6, map.get("qufen_id").toString());
			cs.registerOutParameter(7,java.sql.Types.INTEGER);
			cs.registerOutParameter(8,oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(9,java.sql.Types.INTEGER);
			cs.registerOutParameter(10,java.sql.Types.VARCHAR);
			cs.execute();

			x_count = cs.getInt(7);
			x_retcode = cs.getInt(9);
			x_message = cs.getString(10);
			if (x_count > 0) {
				rs = (ResultSet) cs.getObject(8);
				while (rs.next()) {
					Map<String, String> rowMap = new HashMap<String, String>();
					rowMap.put("sp_id", rs.getString("sp_id"));
					rowMap.put("sp_name", rs.getString("ap_name"));
					rowMap.put("product_code", rs.getString("product_id"));
					rowMap.put("product_name", rs.getString("product_name"));
					rowMap.put("client_code", rs.getString("client_id"));
				  	rowMap.put("client_name", rs.getString("client_name"));
					rowMap.put("kpi_id", rs.getString("kpi_id"));
					rowMap.put("kpi_name", rs.getString("kpi_name"));
					rowMap.put("qufen_id", rs.getString("qufen_id"));
					rowMap.put("qufen_name", rs.getString("qufen_name"));
					rowMap.put("quyu_id", rs.getString("quyu_id"));
					rowMap.put("quyu_name", rs.getString("quyu_name"));
					rowMap.put("month01", df.format(rs.getDouble("month12")));
					rowMap.put("month02", df.format(rs.getDouble("month11")));
					rowMap.put("month03", df.format(rs.getDouble("month10")));
					rowMap.put("month04", df.format(rs.getDouble("month09")));
					rowMap.put("month05", df.format(rs.getDouble("month08")));
					rowMap.put("month06", df.format(rs.getDouble("month07")));
					rowMap.put("month07", df.format(rs.getDouble("month06")));
					rowMap.put("month08", df.format(rs.getDouble("month05")));
					rowMap.put("month09", df.format(rs.getDouble("month04")));
					rowMap.put("month010", df.format(rs.getDouble("month03")));
					rowMap.put("month011", df.format(rs.getDouble("month02")));
					rowMap.put("month012", df.format(rs.getDouble("month01")));
			
					System.out.print(rs.getDouble("month12")) ;
					System.out.print("=") ;
					System.out.print(df.format(rs.getDouble("month12"))) ;
					System.out.println("+++++++++++++++++++++++++++") ;
					mapList.add(rowMap);
				}
			}
			//rs.close();
		//	cs.close();
		//	con.close();
			/**存储过程sp_id,
                         product_id,
                         client_id,
                         kpi_id,
                         qufen_id,
                         quyu_id,
			 procedure gen_dashboard_report_p(x_month IN varchar2,--'yyyymm格式'
                   x_sp_id  IN varchar2,
                   x_product_id varchar2,
                   x_clent_id varchar2,
                   x_kpi_id varchar2,
                   x_qufen_id varchar2,
                   x_quyu_id varchar2,
                   x_count       OUT NUMBER,
                   x_recordset   OUT record_set,
                   x_retcode     OUT NUMBER,
                   x_message     OUT VARCHAR2)
		 */
		} catch (Exception e) {
	
			e.printStackTrace();
		} finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			
			try {
				if (cs != null)
					cs.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
			try {
				if (em != null)
					em.close();
			} catch (Exception e) {
			}
		}
		return mapList;
	}
	/**
	 * 获取仪表盘网格报表数据
	 * 
	 * @param map
	 *            里值分别为{"month_id":期间'yyyymm格式',"sp_id":'专业ID' product_code
	 *            产品编码,client_code 客户编码 kpi_id 指标ID,qufen_id 区分公司ID quyu_id
	 *            营服中心ID}
	 * @return 网络数据LIST
	 */
	public List getDashBoardGridDataxsjl(Map<String, String> map) {

		EntityManager em = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List mapList = new ArrayList();
		DecimalFormat   df = new DecimalFormat("#,##0.00%");
		df.setRoundingMode(RoundingMode.HALF_UP);  
		try {
			int x_count = 0;
			int x_retcode = 0;
			String x_message = "";
			em = this.entityManager.getEntityManagerFactory()
					.createEntityManager();
			con = em.unwrap(Session.class).connection();
			cs = con
					.prepareCall("{call jzgl_dw_pkg.gen_dashboard_report_xsjl_p(?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, map.get("month_id").toString());
			cs.setString(2, map.get("sp_id").toString());
			cs.setString(3, map.get("product_code").toString());
			cs.setString(4, map.get("client_code").toString());
			cs.setString(5, map.get("kpi_id").toString());
			cs.setString(6, map.get("qufen_id").toString());
			cs.setString(7, map.get("quyu_id").toString());
			cs.setString(8, map.get("manager_No").toString());
			cs.registerOutParameter(9,java.sql.Types.INTEGER);
			cs.registerOutParameter(10,oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(11,java.sql.Types.INTEGER);
			cs.registerOutParameter(12,java.sql.Types.VARCHAR);
			cs.execute();

			x_count = cs.getInt(9);
			x_retcode = cs.getInt(11);
			x_message = cs.getString(12);
			if (x_count > 0) {
				rs = (ResultSet) cs.getObject(10);
				while (rs.next()) {
					Map<String, String> rowMap = new HashMap<String, String>();
					rowMap.put("sp_id", rs.getString("sp_id"));
					rowMap.put("sp_name", rs.getString("ap_name"));
					rowMap.put("product_code", rs.getString("product_id"));
					rowMap.put("product_name", rs.getString("product_name"));
					rowMap.put("client_code", rs.getString("client_id"));
				  	rowMap.put("client_name", rs.getString("client_name"));
					rowMap.put("kpi_id", rs.getString("kpi_id"));
					rowMap.put("kpi_name", rs.getString("kpi_name"));
					rowMap.put("qufen_id", rs.getString("qufen_id"));
					rowMap.put("qufen_name", rs.getString("qufen_name"));
					rowMap.put("quyu_id", rs.getString("quyu_id"));
					rowMap.put("quyu_name", rs.getString("quyu_name"));
					rowMap.put("salesmanager_no", rs.getString("salesmanager_no"));
					rowMap.put("salesmanager_name", rs.getString("salesmanager_name"));
					rowMap.put("month01", df.format(rs.getDouble("month12")));
					rowMap.put("month02", df.format(rs.getDouble("month11")));
					rowMap.put("month03", df.format(rs.getDouble("month10")));
					rowMap.put("month04", df.format(rs.getDouble("month09")));
					rowMap.put("month05", df.format(rs.getDouble("month08")));
					rowMap.put("month06", df.format(rs.getDouble("month07")));
					rowMap.put("month07", df.format(rs.getDouble("month06")));
					rowMap.put("month08", df.format(rs.getDouble("month05")));
					rowMap.put("month09", df.format(rs.getDouble("month04")));
					rowMap.put("month010", df.format(rs.getDouble("month03")));
					rowMap.put("month011", df.format(rs.getDouble("month02")));
					rowMap.put("month012", df.format(rs.getDouble("month01")));
			
					System.out.print(rs.getDouble("month12")) ;
					System.out.print("=") ;
					System.out.print(df.format(rs.getDouble("month12"))) ;
					System.out.println("+++++++++++++++++++++++++++") ;
					mapList.add(rowMap);
				}
			}
			//rs.close();
		//	cs.close();
		//	con.close();
			/**存储过程sp_id,
                         product_id,
                         client_id,
                         kpi_id,
                         qufen_id,
                         quyu_id,
			 procedure gen_dashboard_report_p(x_month IN varchar2,--'yyyymm格式'
                   x_sp_id  IN varchar2,
                   x_product_id varchar2,
                   x_clent_id varchar2,
                   x_kpi_id varchar2,
                   x_qufen_id varchar2,
                   x_quyu_id varchar2,
                   x_count       OUT NUMBER,
                   x_recordset   OUT record_set,
                   x_retcode     OUT NUMBER,
                   x_message     OUT VARCHAR2)
		 */
		} catch (Exception e) {
	
			e.printStackTrace();
		} finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			
			try {
				if (cs != null)
					cs.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
			try {
				if (em != null)
					em.close();
			} catch (Exception e) {
			}
		}
		return mapList;
	}
	/**
	 * 获取仪表盘网格报表数据
	 * 
	 * @param map
	 *            里值分别为{"month_id":期间'yyyymm格式',"sp_id":'专业ID' product_code
	 *            产品编码,client_code 客户编码 kpi_id 指标ID,qufen_id 区分公司ID quyu_id
	 *            营服中心ID}
	 * @return 网络数据LIST
	 */
	public List getDashBoardGridDataYf(Map<String, String> map) {

		EntityManager em = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List mapList = new ArrayList();
		DecimalFormat   df = new DecimalFormat("#,##0.00%");
		df.setRoundingMode(RoundingMode.HALF_UP);  
		try {
			int x_count = 0;
			int x_retcode = 0;
			String x_message = "";
			em = this.entityManager.getEntityManagerFactory()
					.createEntityManager();
			con = em.unwrap(Session.class).connection();
			cs = con
					.prepareCall("{call jzgl_dw_pkg.gen_dashboard_report_yf_p(?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, map.get("month_id").toString());
			cs.setString(2, map.get("sp_id").toString());
			cs.setString(3, map.get("product_code").toString());
			cs.setString(4, map.get("client_code").toString());
			cs.setString(5, map.get("kpi_id").toString());
			cs.setString(6, map.get("qufen_id").toString());
			cs.setString(7, map.get("quyu_id").toString());
			cs.registerOutParameter(8,java.sql.Types.INTEGER);
			cs.registerOutParameter(9,oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(10,java.sql.Types.INTEGER);
			cs.registerOutParameter(11,java.sql.Types.VARCHAR);
			cs.execute();

			x_count = cs.getInt(8);
			x_retcode = cs.getInt(10);
			x_message = cs.getString(11);
			if (x_count > 0) {
				rs = (ResultSet) cs.getObject(9);
				while (rs.next()) {
					Map<String, String> rowMap = new HashMap<String, String>();
					rowMap.put("sp_id", rs.getString("sp_id"));
					rowMap.put("sp_name", rs.getString("ap_name"));
					rowMap.put("product_code", rs.getString("product_id"));
					rowMap.put("product_name", rs.getString("product_name"));
					rowMap.put("client_code", rs.getString("client_id"));
				  	rowMap.put("client_name", rs.getString("client_name"));
					rowMap.put("kpi_id", rs.getString("kpi_id"));
					rowMap.put("kpi_name", rs.getString("kpi_name"));
					rowMap.put("qufen_id", rs.getString("qufen_id"));
					rowMap.put("qufen_name", rs.getString("qufen_name"));
					rowMap.put("quyu_id", rs.getString("quyu_id"));
					rowMap.put("quyu_name", rs.getString("quyu_name"));
					rowMap.put("month01", df.format(rs.getDouble("month12")));
					rowMap.put("month02", df.format(rs.getDouble("month11")));
					rowMap.put("month03", df.format(rs.getDouble("month10")));
					rowMap.put("month04", df.format(rs.getDouble("month09")));
					rowMap.put("month05", df.format(rs.getDouble("month08")));
					rowMap.put("month06", df.format(rs.getDouble("month07")));
					rowMap.put("month07", df.format(rs.getDouble("month06")));
					rowMap.put("month08", df.format(rs.getDouble("month05")));
					rowMap.put("month09", df.format(rs.getDouble("month04")));
					rowMap.put("month010", df.format(rs.getDouble("month03")));
					rowMap.put("month011", df.format(rs.getDouble("month02")));
					rowMap.put("month012", df.format(rs.getDouble("month01")));
			
					System.out.print(rs.getDouble("month12")) ;
					System.out.print("=") ;
					System.out.print(df.format(rs.getDouble("month12"))) ;
					System.out.println("+++++++++++++++++++++++++++") ;
					mapList.add(rowMap);
				}
			}
			//rs.close();
		//	cs.close();
		//	con.close();
			/**存储过程sp_id,
                         product_id,
                         client_id,
                         kpi_id,
                         qufen_id,
                         quyu_id,
			 procedure gen_dashboard_report_p(x_month IN varchar2,--'yyyymm格式'
                   x_sp_id  IN varchar2,
                   x_product_id varchar2,
                   x_clent_id varchar2,
                   x_kpi_id varchar2,
                   x_qufen_id varchar2,
                   x_quyu_id varchar2,
                   x_count       OUT NUMBER,
                   x_recordset   OUT record_set,
                   x_retcode     OUT NUMBER,
                   x_message     OUT VARCHAR2)
		 */
		} catch (Exception e) {
	
			e.printStackTrace();
		} finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			
			try {
				if (cs != null)
					cs.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
			try {
				if (em != null)
					em.close();
			} catch (Exception e) {
			}
		}
		return mapList;
	}
	@Override
	public double getDashBoardKpiP(Map<String, String> map) {
		EntityManager em = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List mapList = new ArrayList();
		double x_count = 0;
		 double x_retcode = 0;	
		//DecimalFormat   df = new DecimalFormat("#.00");
		try {
			
			String x_message = "";
			em = this.entityManager.getEntityManagerFactory()
					.createEntityManager();
			con = em.unwrap(Session.class).connection();
			cs = con
					.prepareCall("{call jzgl_dw_pkg.gen_dashboard_kpi_p(?,?,?,?,?,?,?,?)}");
			cs.setString(1, map.get("month_id").toString());
			cs.setString(2, map.get("sp_id").toString());
			cs.setString(3, map.get("product_code").toString());
			cs.setString(4, map.get("client_code").toString());
			cs.setString(5, map.get("kpi_id").toString());
			cs.registerOutParameter(6,java.sql.Types.INTEGER);
			cs.registerOutParameter(7,java.sql.Types.INTEGER);
			cs.registerOutParameter(8,java.sql.Types.VARCHAR);
			cs.execute();
			x_retcode = cs.getDouble(6);
			x_count = cs.getDouble(7);
			x_message = cs.getString(8);
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			
			try {
				if (cs != null)
					cs.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
			try {
				if (em != null)
					em.close();
			} catch (Exception e) {
			}
		}
		return x_retcode;
	}

	@Override
	public List getDashBoardRepDituQufenP(Map<String, String> map) {
		EntityManager em = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List mapList = new ArrayList();
		//DecimalFormat   df = new DecimalFormat("#.00");
		try {
			int x_count = 0;
			int x_retcode = 0;
			String x_message = "";
			em = this.entityManager.getEntityManagerFactory()
					.createEntityManager();
			con = em.unwrap(Session.class).connection();
			cs = con
					.prepareCall("{call jzgl_dw_pkg.gen_dashboard_rep_ditu_qufen_p(?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, map.get("month_id").toString());
			cs.setString(2, map.get("sp_id").toString());
			cs.setString(3, map.get("product_code").toString());
			cs.setString(4, map.get("client_code").toString());
			cs.setString(5, map.get("kpi_id").toString());
			cs.registerOutParameter(6,java.sql.Types.INTEGER);
			cs.registerOutParameter(7,oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(8,java.sql.Types.INTEGER);
			cs.registerOutParameter(9,java.sql.Types.VARCHAR);
			cs.execute();
			x_count = cs.getInt(6);
			x_retcode = cs.getInt(8);
			x_message = cs.getString(9);
			String code ="";
			if (x_count > 0) {
				rs = (ResultSet) cs.getObject(7);
				DecimalFormat   df = new DecimalFormat("0.##");
				while (rs.next()) {
					String amount_curr=rs.getString("Calamount_Curr");
					if(amount_curr!=null&&!"".equals(amount_curr)){
						Double value = rs.getDouble("Calamount_Curr")*100;
						Map<String, String> rowMap = new HashMap<String, String>();
						code= rs.getString("qufen_id");
						rowMap.put("districtBranchCode",code);
						rowMap.put("districtBranchName", rs.getString("qufen_name"));
						rowMap.put("amountCurr", df.format(value));
						rowMap.put("mapCode",Globals.getMapIdByCode(code));
						mapList.add(rowMap);
					}	
				}
			}
		} catch (Exception e) {
		
			e.printStackTrace();
		} finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			
			try {
				if (cs != null)
					cs.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
			try {
				if (em != null)
					em.close();
			} catch (Exception e) {
			}
		}
		return mapList;
	}

	@Override
	public List getDashBoardRepDituYfP(Map<String, String> map) {
		EntityManager em = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List mapList = new ArrayList();
		//DecimalFormat   df = new DecimalFormat("#.00");
		try {
			int x_count = 0;
			int x_retcode = 0;
			String x_message = "";
			em = this.entityManager.getEntityManagerFactory()
					.createEntityManager();
			con = em.unwrap(Session.class).connection();
			cs = con
					.prepareCall("{call jzgl_dw_pkg.gen_dashboard_rep_ditu_yf_p(?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, map.get("month_id").toString());
			cs.setString(2, map.get("sp_id").toString());
			cs.setString(3, map.get("product_code").toString());
			cs.setString(4, map.get("client_code").toString());
			cs.setString(5, map.get("qufen_id").toString());
			cs.setString(6, map.get("kpi_id").toString());
			cs.registerOutParameter(7,java.sql.Types.INTEGER);
			cs.registerOutParameter(8,oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(9,java.sql.Types.INTEGER);
			cs.registerOutParameter(10,java.sql.Types.VARCHAR);
			cs.execute();
			x_count = cs.getInt(7);
			x_retcode = cs.getInt(9);
			x_message = cs.getString(10);
			DecimalFormat   df = new DecimalFormat("0.##");
			if (x_count > 0) {
				rs = (ResultSet) cs.getObject(8);
				while (rs.next()) {
					String amount_curr=rs.getString("Calamount_Curr");
					if(amount_curr!=null&&!"".equals(amount_curr)){
						Double value = rs.getDouble("Calamount_Curr")*100;
						Map<String, String> rowMap = new HashMap<String, String>();
						rowMap.put("busiSccode", rs.getString("quyu_id"));
						rowMap.put("busiScName", rs.getString("quyu_name"));
						rowMap.put("amountCurr", df.format(value));
						rowMap.put("mapCode",Globals.getMapIdByCode(rs.getString("quyu_id")));
						mapList.add(rowMap);
					}
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			
			try {
				if (cs != null)
					cs.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
			try {
				if (em != null)
					em.close();
			} catch (Exception e) {
			}
		}
		return mapList;
	}

	@Override
	public List getDashBoardRepDituxsjlP(Map<String, String> map) {
		EntityManager em = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List mapList = new ArrayList();
		//DecimalFormat   df = new DecimalFormat("#.00");
		try {
			int x_count = 0;
			int x_retcode = 0;
			String x_message = "";
			em = this.entityManager.getEntityManagerFactory()
					.createEntityManager();
			con = em.unwrap(Session.class).connection();
			cs = con
					.prepareCall("{call jzgl_dw_pkg.gen_dashboard_rep_bitu_xsjl_p(?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, map.get("month_id").toString());
			cs.setString(2, map.get("sp_id").toString());
			cs.setString(3, map.get("product_code").toString());
			cs.setString(4, map.get("client_code").toString());
			cs.setString(5, map.get("qufen_id").toString());
			cs.setString(6, map.get("quyu_id").toString());
			cs.setString(7, map.get("kpi_id").toString());
			cs.registerOutParameter(8,java.sql.Types.INTEGER);
			cs.registerOutParameter(9,oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(10,java.sql.Types.INTEGER);
			cs.registerOutParameter(11,java.sql.Types.VARCHAR);
			cs.execute();
			x_count = cs.getInt(8);
			x_retcode = cs.getInt(10);
			x_message = cs.getString(11);
			DecimalFormat   df = new DecimalFormat("0.##");
			if (x_count > 0) {
				rs = (ResultSet) cs.getObject(9 );
				while (rs.next()) {
					String amount_curr=rs.getString("Calamount_Curr");
					if(amount_curr!=null&&!"".equals(amount_curr)){
						Double value = Double.parseDouble(amount_curr)*100;
						Map<String, String> rowMap = new HashMap<String, String>();
						rowMap.put("salesmanager_no", rs.getString("salesmanager_no"));
						rowMap.put("chartLabelName", rs.getString("salesmanager_name"));
						rowMap.put("chartLabelValue", df.format(value));
						mapList.add(rowMap);
					}
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			
			try {
				if (cs != null)
					cs.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
			try {
				if (em != null)
					em.close();
			} catch (Exception e) {
			}
		}
		return mapList;
	}

	@Override
	public List getDashBoardReportQufenP(Map<String, String> map) {
		EntityManager em = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List mapList = new ArrayList();
		//DecimalFormat   df = new DecimalFormat("#.00");
		try {
			double x_count = 0;
			double x_retcode = 0;
			String x_message = "";
			em = this.entityManager.getEntityManagerFactory()
					.createEntityManager();
			con = em.unwrap(Session.class).connection();
			cs = con
					.prepareCall("{call jzgl_dw_pkg.gen_dashboard_report_qufen_p(?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, map.get("month_id").toString());
			cs.setString(2, map.get("sp_id").toString());
			cs.setString(3, map.get("product_code").toString());
			cs.setString(4, map.get("client_code").toString());
			cs.setString(5, map.get("kpi_id").toString());
			cs.registerOutParameter(6,java.sql.Types.INTEGER);
			cs.registerOutParameter(7,oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(8,java.sql.Types.INTEGER);
			cs.registerOutParameter(9,java.sql.Types.VARCHAR);
			cs.execute();
			x_count = cs.getDouble(6);
			x_retcode = cs.getDouble(8);
			x_message = cs.getString(9);
			if (x_count > 0) {
				rs = (ResultSet) cs.getObject(7);
				while (rs.next()) {
					Map<String, String> rowMap = new HashMap<String, String>();
					rowMap.put("qufen_id", rs.getString("qufen_id"));
					rowMap.put("chartLabelName", rs.getString("qufen_name"));
					rowMap.put("chartLabelValue", rs.getString("calamount_curr"));
					mapList.add(rowMap);
				}
			}
		} catch (Exception e) {
		
			e.printStackTrace();
		} finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			
			try {
				if (cs != null)
					cs.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
			try {
				if (em != null)
					em.close();
			} catch (Exception e) {
			}
		}
		return mapList;
	}

	@Override
	public List getDashBoardReportSilongP(Map<String, String> map) {
		EntityManager em = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List mapList = new ArrayList();
		//DecimalFormat   df = new DecimalFormat("#.00");
		try {
			int x_count = 0;
			int x_retcode = 0;
			String x_message = "";
			em = this.entityManager.getEntityManagerFactory()
					.createEntityManager();
			con = em.unwrap(Session.class).connection();
			cs = con
					.prepareCall("{call jzgl_dw_pkg.gen_dashboard_report_silong_p(?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, map.get("month_id").toString());
			cs.setString(2, map.get("sp_id").toString());
			cs.setString(3, map.get("product_code").toString());
			cs.setString(4, map.get("client_code").toString());
			cs.setString(5, map.get("kpi_id").toString());
			cs.registerOutParameter(6,java.sql.Types.INTEGER);
			cs.registerOutParameter(7,oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(8,java.sql.Types.INTEGER);
			cs.registerOutParameter(9,java.sql.Types.VARCHAR);
			cs.execute();
			x_count = cs.getInt(6);
			x_retcode = cs.getInt(8);
			x_message = cs.getString(9);
			if (x_count > 0) {
				rs = (ResultSet) cs.getObject(7);
				while (rs.next()) {
					Map<String, String> rowMap = new HashMap<String, String>();
					rowMap.put("chartLabelName", rs.getString("attribute1"));
					rowMap.put("chartLabelValue", rs.getString("amount_curr"));
					mapList.add(rowMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (cs != null)
					cs.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
			try {
				if (em != null)
					em.close();
			} catch (Exception e) {
			}
		}
		return mapList;
	}

	@Override
	public List getDashBoardZXYFP(Map<String, String> map) {
		EntityManager em = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List mapList = new ArrayList();
		//DecimalFormat   df = new DecimalFormat("#.00");
		try {
			int x_count = 0;
			int x_retcode = 0;
			String x_message = "";
			em = this.entityManager.getEntityManagerFactory()
					.createEntityManager();
			con = em.unwrap(Session.class).connection();
			cs = con
					.prepareCall("{call jzgl_dw_pkg.gen_dashboard_zx_yf_p(?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, map.get("month_id").toString());
			cs.setString(2, map.get("sp_id").toString());
			cs.setString(3, map.get("product_code").toString());
			cs.setString(4, map.get("client_code").toString());
			cs.setString(5, map.get("kpi_id").toString());
			cs.setString(6, map.get("qufen_id").toString());
			cs.setString(7, map.get("quyu_id").toString());
			cs.registerOutParameter(8,java.sql.Types.INTEGER);
			cs.registerOutParameter(9,oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(10,java.sql.Types.INTEGER);
			cs.registerOutParameter(11,java.sql.Types.VARCHAR);
			cs.execute();
			x_count = cs.getInt(8);
			x_retcode = cs.getInt(10);
			x_message = cs.getString(11);
			if (x_count > 0) {
				rs = (ResultSet) cs.getObject(9);
				DecimalFormat   df = new DecimalFormat("0.##");
				while (rs.next()) {
					int arry[] = ClassUtil.getMonthId(Integer.parseInt(map.get("month_id").toString()));
					for(int i=1;i<arry.length+1;i++){
						Map<String, String> rowMap = new HashMap<String, String>();
						rowMap.put("chartLabelName", ""+arry[12-i]);
						String str = "";
						int j = 13-i;
						if(j>9){
							str="month"+j;
						}else{
							str="month0"+j;
						}
						rowMap.put("chartLabelValue", df.format(rs.getDouble(str)*100));
						mapList.add(rowMap);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (cs != null)
					cs.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
			try {
				if (em != null)
					em.close();
			} catch (Exception e) {
			}
		}
		return mapList;
	}

	@Override
	public List getDashBoardZXQuFenP(Map<String, String> map) {
		EntityManager em = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List mapList = new ArrayList();
		//DecimalFormat   df = new DecimalFormat("#.00");
		try {
			int x_count = 0;
			int x_retcode = 0;
			String x_message = "";
			em = this.entityManager.getEntityManagerFactory()
					.createEntityManager();
			con = em.unwrap(Session.class).connection();
			cs = con
					.prepareCall("{call jzgl_dw_pkg.gen_dashboard_zx_qufen_p(?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, map.get("month_id").toString());
			cs.setString(2, map.get("sp_id").toString());
			cs.setString(3, map.get("product_code").toString());
			cs.setString(4, map.get("client_code").toString());
			cs.setString(5, map.get("kpi_id").toString());
			cs.setString(6, map.get("qufen_id").toString());
			cs.registerOutParameter(7,java.sql.Types.INTEGER);
			cs.registerOutParameter(8,oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(9,java.sql.Types.INTEGER);
			cs.registerOutParameter(10,java.sql.Types.VARCHAR);
			cs.execute();
			x_count = cs.getInt(7);
			x_retcode = cs.getInt(9);
			x_message = cs.getString(10);
			if (x_count > 0) {
				rs = (ResultSet) cs.getObject(8);
				DecimalFormat   df = new DecimalFormat("0.##");
				while (rs.next()) {
					int arry[] = ClassUtil.getMonthId(Integer.parseInt(map.get("month_id").toString()));
					for(int i=1;i<arry.length+1;i++){
						Map<String, String> rowMap = new HashMap<String, String>();
						rowMap.put("chartLabelName", ""+arry[12-i]);
						String str = "";
						int j = 13-i;
						if(j>9){
							str="month"+j;
						}else{
							str="month0"+j;
						}
						rowMap.put("chartLabelValue",  df.format(rs.getDouble(str)*100));
						mapList.add(rowMap);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (cs != null)
					cs.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
			try {
				if (em != null)
					em.close();
			} catch (Exception e) {
			}
		}
		return mapList;
	}

	@Override
	public List getDashBoardZXxsjlP(Map<String, String> map) {
		EntityManager em = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List mapList = new ArrayList();
		//DecimalFormat   df = new DecimalFormat("#.00");
		try {
			int x_count = 0;
			int x_retcode = 0;
			String x_message = "";
			em = this.entityManager.getEntityManagerFactory()
					.createEntityManager();
			con = em.unwrap(Session.class).connection();
			cs = con
					.prepareCall("{call jzgl_dw_pkg.gen_dashboard_zx_xsjl_p(?,?,?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, map.get("month_id").toString());
			cs.setString(2, map.get("sp_id").toString());
			cs.setString(3, map.get("product_code").toString());
			cs.setString(4, map.get("client_code").toString());
			cs.setString(5, map.get("kpi_id").toString());
			cs.setString(6, map.get("qufen_id").toString());
			cs.setString(7, map.get("quyu_id").toString());
			cs.setString(8, map.get("manager_No").toString());
			cs.registerOutParameter(9,java.sql.Types.INTEGER);
			cs.registerOutParameter(10,oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(11,java.sql.Types.INTEGER);
			cs.registerOutParameter(12,java.sql.Types.VARCHAR);
			cs.execute();
			x_count = cs.getInt(9);
			x_retcode = cs.getInt(11);
			x_message = cs.getString(12);
			if (x_count > 0) {
				rs = (ResultSet) cs.getObject(10);
				DecimalFormat   df = new DecimalFormat("0.##");
				while (rs.next()) {
					int arry[] = ClassUtil.getMonthId(Integer.parseInt(map.get("month_id").toString()));
					for(int i=1;i<arry.length+1;i++){
						Map<String, String> rowMap = new HashMap<String, String>();
						rowMap.put("chartLabelName", ""+arry[12-i]);
						String str = "";
						int j = 13-i;
						if(j>9){
							str="month"+j;
						}else{
							str="month0"+j;
						}
						rowMap.put("chartLabelValue", df.format(rs.getDouble(str)*100));
						mapList.add(rowMap);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (cs != null)
					cs.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
			try {
				if (em != null)
					em.close();
			} catch (Exception e) {
			}
		}
		return mapList;
	}

	@Override
	public List getDashBoardZXTraceP(Map<String, String> map) {
		EntityManager em = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List mapList = new ArrayList();
		//DecimalFormat   df = new DecimalFormat("#.00");
		try {
			int x_count = 0;
			int x_retcode = 0;
			String x_message = "";
			em = this.entityManager.getEntityManagerFactory()
					.createEntityManager();
			con = em.unwrap(Session.class).connection();
			cs = con
					.prepareCall("{call jzgl_dw_pkg.gen_dashboard_zx_trace_p(?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, map.get("month_id").toString());
			cs.setString(2, map.get("sp_id").toString());
			cs.setString(3, map.get("product_code").toString());
			cs.setString(4, map.get("client_code").toString());
			cs.setString(5, map.get("kpi_id").toString());
			cs.registerOutParameter(6,java.sql.Types.INTEGER);
			cs.registerOutParameter(7,oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(8,java.sql.Types.INTEGER);
			cs.registerOutParameter(9,java.sql.Types.VARCHAR);
			cs.execute();
			x_count = cs.getInt(6);
			x_retcode = cs.getInt(8);
			x_message = cs.getString(9);
			String code ="";
			if (x_count > 0) {
				rs = (ResultSet) cs.getObject(7);
				DecimalFormat   df = new DecimalFormat("0.####");
				String str = "";
				while (rs.next()) {
					int arry[] = ClassUtil.getMonthId(Integer.parseInt(map.get("month_id").toString()));
					for(int i=6;i>0;i--){
						Map<String, String> rowMap = new HashMap<String, String>();
						rowMap.put("chartLabelName", ""+arry[i-1]);
						str="month0"+i;
						rowMap.put("chartLabelValue",  df.format(rs.getDouble(str)));
						mapList.add(rowMap);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			
			try {
				if (cs != null)
					cs.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
			try {
				if (em != null)
					em.close();
			} catch (Exception e) {
			}
		}
		return mapList;
	}

	@Override
	public List getDashBoardPieDataP(Map<String, String> map) {
		EntityManager em = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		List mapList = new ArrayList();
		//DecimalFormat   df = new DecimalFormat("#.00");
		try {
			int x_count = 0;
			int x_retcode = 0;
			String x_message = "";
			em = this.entityManager.getEntityManagerFactory()
					.createEntityManager();
			con = em.unwrap(Session.class).connection();
			cs = con
					.prepareCall("{call jzgl_dw_pkg.gen_dashboard_rep_ditu_qufen_p(?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, map.get("month_id").toString());
			cs.setString(2, map.get("sp_id").toString());
			cs.setString(3, map.get("product_code").toString());
			cs.setString(4, map.get("client_code").toString());
			cs.setString(5, map.get("kpi_id").toString());
			cs.registerOutParameter(6,java.sql.Types.INTEGER);
			cs.registerOutParameter(7,oracle.jdbc.OracleTypes.CURSOR);
			cs.registerOutParameter(8,java.sql.Types.INTEGER);
			cs.registerOutParameter(9,java.sql.Types.VARCHAR);
			cs.execute();
			x_count = cs.getInt(6);
			x_retcode = cs.getInt(8);
			x_message = cs.getString(9);
			String code ="";
			if (x_count > 0) {
				rs = (ResultSet) cs.getObject(7);
				DecimalFormat   df = new DecimalFormat("0.##");
				while (rs.next()) {
					Double value = rs.getDouble("Calamount_Curr");
					Map<String, String> rowMap = new HashMap<String, String>();
					rowMap.put("chartLabelName", rs.getString("qufen_name"));
					rowMap.put("chartLabelValue", df.format(value));
					mapList.add(rowMap);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			
			try {
				if (cs != null)
					cs.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
			try {
				if (em != null)
					em.close();
			} catch (Exception e) {
			}
		}
		return mapList;
	}
	@Override
	public String sendSMS(String tel, String content) {
		EntityManager em = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		//List mapList = new ArrayList();
		//DecimalFormat   df = new DecimalFormat("#.00");
		try {
			String x_message = "";
			em = this.entityManager.getEntityManagerFactory()
					.createEntityManager();
			con = em.unwrap(Session.class).connection();
			cs = con
					.prepareCall("{call cardsale.sp_send@dgdb3(?,?)}");
			cs.setString(1, tel);
			cs.setString(2, content);
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return "SEND_ERROR";
		} finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			
			try {
				if (cs != null)
					cs.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
			try {
				if (em != null)
					em.close();
			} catch (Exception e) {
			}
		}
		return "SUCCESS";
	}
	
	
}
