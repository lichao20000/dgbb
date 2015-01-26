package com.dglt.bb.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.dglt.bb.dao.TestDAO;
import com.dglt.comm.base.BaseDAOImpl;

@Repository("testDAO")
public class TestDAOImpl extends BaseDAOImpl implements TestDAO
{
	//获取JDBC连接测试
	public void a()
	{
		
		Statement s = null;
		Connection con = null;
		EntityManager em = null;
		try {
			
			em=this.entityManager.getEntityManagerFactory().createEntityManager();
			con = em.unwrap(Session.class).connection();
			s = con.createStatement();
			ResultSet results = s.executeQuery("select count(*) from W_INCOME_BUDGET_V t");
			results.next();
			double d = results.getDouble(1);
			System.out.println("W_INCOME_BUDGET_V nuber:"+d);
			results.close();
			s.close();
			con.close();
			
			/**存储过程返回游标示例
			 * public void test(){
				  Connection conn=null;
				  CallableStatement cs=null;
				  ResultSet rs=null;
				  try {
				   conn=OracleConnection.getConnection();
				   cs=conn.prepareCall("{call getList(?,?)}");
				   cs.setString(1, "a1");
				   cs.registerOutParameter(2, OracleTypes.CURSOR);
				   cs.execute();
				   rs=(ResultSet)cs.getObject(2);
				   while(rs.next()&&rs!=null){
				    System.out.println("ID="+rs.getInt("id1"));
				    System.out.println("NAME="+rs.getString("name1"));
				    System.out.println("NAME="+rs.getString("createtime1"));
				   }
				   
				  } catch (Exception e) {
				   e.printStackTrace();
				  }
				 }
			 * 
			 * 
			 */
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if (s != null)
					s.close();
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
	}
}
