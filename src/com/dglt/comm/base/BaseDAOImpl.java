package com.dglt.comm.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.mapping.PersistentClass;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository("baseDAO")
public class BaseDAOImpl implements BaseDAO
{

	protected Log log = LogFactory.getLog(this.getClass());

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * 得到该对象在数据库中映射表的主键序列
	 * @param entityClass
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午02:10:09
	 */
	public String getNewId(Class entityClass)
	{
		String idsql = "select " + entityClass.getSimpleName()
				+ "_S.nextval from dual";
		return String.valueOf(entityManager
				.createNativeQuery(idsql).getResultList().get(0));
	}

	/**
	 * 得到该对象在数据库中映射表的主键序列
	 * @param entity
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午02:13:31
	 */
	public String getNewId(Object entity)
	{
		return getNewId(entity.getClass()); 
	}

	/**
	 * 手动主键序列到entity对象并保存到数据库
	 * @param entity
	 * @author tanw
	 * @since 2011-2-14 下午02:16:13
	 */
	public void persistById(Object entity)
	{
		entityManager.persist(entity);
	}

	/**
	 * 保存entity对象，自动填充主键序列，返回主键序列值
	 * @param entity
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午02:17:24
	 */
	public String persist(Object entity)
	{
		String id = getNewId(entity);
		BeanUtils.setObjId(entity, id);
		entityManager.persist(entity);
		return id;
	}

	/**
	 * 根据对象类型和主键查找对象并返回该类型的对象
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午02:19:51
	 */
	public <T> T get(Class<T> entityClass,Serializable id)
	{
		return entityManager.find(entityClass, id);
	}

	/**
	 * 更新该对象并返回更新后的对象
	 * @param <T>
	 * @param entity
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午02:21:57
	 */
	public <T> T merge(T entity)
	{
		return entityManager.merge(entity);
	}

	/**
	 * 删除entity对象
	 * @param entity
	 * @author tanw
	 * @since 2011-2-14 下午02:22:48
	 */
	public void remove(Object entity)
	{
		entityManager.remove(entity);
	}

	/**
	 * 根据对象类型和主键删除该对象
	 * @param entityClass
	 * @param id
	 * @author tanw
	 * @since 2011-2-14 下午02:23:07
	 */
	public void removeById(Class entityClass,Serializable id)
	{
		remove(entityManager.getReference(entityClass, id));
	}

	/**
	 * 
	 * 根据jpql进行数据库查询 ，返回结果集
	 * 
	 * @param jpql
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午02:28:26
	 */
	public List find(final String jpql)
	{
		Assert.hasText(jpql);
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
	
	/**
	 * 
	 * 根据sql进行数据库查询 ，返回结果集
	 * 
	 * @param sql
	 * @return
	 * @author tanw
	 * @since 2014-2-10 下午02:28:26
	 */
	public List findByNativeQuery(final String sql)
	{
		Assert.hasText(sql);
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
	}
	
	/**
	 * 
	 * 根据jpql进行数据库查询,并传递预编译参数。返回结果集
	 * 
	 * @param jpql
	 * @param values
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午02:25:44
	 */
	public List find(final String jpql, final Object... values)
	{
		Assert.hasText(jpql);
		Query query = entityManager.createQuery(jpql);
		if( values != null )
		{
			for (int i = 0; i < values.length; i++)
			{
				query.setParameter(i, values[i]);
			}
		}
		return query.getResultList();
	}

	/**
	 * 根据对象类型和属性对应值进行查询 返回结果集
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午03:11:02
	 */
	public <T> List<T> findBy(final Class<T> entityClass,final String propertyName,final Object value)
	{
		Assert.hasText(propertyName);
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(entityClass);
		Root<T> root = query.from(entityClass);
		query = query.select(root).where(
				cb.equal(root.get(propertyName), value));
		entityManager.clear();
		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * 根据对象类型和属性对应值进行查询,根据对象的属性进行排序， 返回结果集
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @param orderBy
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午03:12:27
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findBy(final Class<T> entityClass,final String propertyName, final Object value,final Map<String, Boolean> orderBy)
	{
		Assert.hasText(propertyName);
		//Assert.isNull(orderBy);
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(entityClass);
		Root<T> root = query.from(entityClass);
		query = query.select(root).where(
				cb.equal(root.get(propertyName), value));
		List<Order> lo = new ArrayList();
		for (String key : orderBy.keySet())
		{
			if (orderBy.get(key))
			{
				lo.add(cb.asc(root.get(key)));
			}
			lo.add(cb.desc(root.get(key)));
		}
		query.orderBy(lo);
		entityManager.clear();
		return entityManager.createQuery(query).getResultList();
	}

	/**
	 * 根据jpql进行查询结果集，传递页号和条数
	 * @param jpql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午03:13:04
	 */
	public DataPage pagedQuery(final String jpql, final int pageNo, final int pageSize)
	{
		Assert.hasText(jpql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count (*) "
				+ removeSelect(removeOrders(jpql));
		int totalCount = ((Long) entityManager.createQuery(countQueryString).getResultList().get(0)).intValue();
		
		if (totalCount < 1)
			return new DataPage();
		// 实际查询返回分页对象
		int startIndex = DataPage.getStartOfPage(pageNo, pageSize);
		List list = entityManager.createQuery(jpql).setFirstResult(startIndex).setMaxResults(pageSize).getResultList();
		entityManager.clear();
		return new DataPage(startIndex, totalCount, pageSize, list);
	}

	/**
	 * 根据条件查询，传递预编译所需参数数组
	 * @param jpql
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午03:51:59
	 */
	public DataPage pagedQuery(final String jpql, final int pageNo,final int pageSize,final Object... values)
	{
		Assert.hasText(jpql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count (*) "
				+ removeSelect(removeOrders(jpql));
		int totalCount = ((Long)find(countQueryString,values).get(0)).intValue();
		if (totalCount < 1)
			return new DataPage();
		// 实际查询返回分页对象
		int startIndex = DataPage.getStartOfPage(pageNo, pageSize);
		Query query  = entityManager.createQuery(jpql);
		 for(int i = 0 ; i < values.length ; i++ )
		 {
			query.setParameter(i, values[0]); 
		 }
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).getResultList();
		entityManager.clear();
		return new DataPage(startIndex, totalCount, pageSize, list);
	}
	
	/**
	 * 批量执行 update delete 语句 
	 * @param jpql
	 * @return
	 * @author tanw
	 * @since 2011-3-1 下午04:10:50
	 */
	public int executeUpdate(final String jpql)
	{
		Integer i = entityManager.createQuery(jpql).executeUpdate();
		entityManager.clear();
		return i;
	}
	
	/**
	 * 取出Order by 子句
	 * @param jpql
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午03:29:03
	 */
	private String removeOrders(final String jpql)
	{
		Assert.hasText(jpql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(jpql);
		StringBuffer sb = new StringBuffer();
		while (m.find())
		{
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 脱去第一层select
	 * @param jpql
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午03:29:27
	 */
	private String removeSelect(final String jpql) {
		Assert.hasText(jpql);
		String jql = jpql;
		int beginPos = jql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " jpql : " + jpql
				+ " must has a keyword 'from'");
		return jql.substring(beginPos).replaceAll("fetch", "");
	}

	public long queryCount(String jpql)
	{
		String countQueryString = " select count (*) "+ removeSelect(removeOrders(jpql));
		return  ((Long) entityManager.createQuery(countQueryString).getResultList().get(0)).longValue();
	}
}
