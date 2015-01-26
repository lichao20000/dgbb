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
	 * �õ��ö��������ݿ���ӳ������������
	 * @param entityClass
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����02:10:09
	 */
	public String getNewId(Class entityClass)
	{
		String idsql = "select " + entityClass.getSimpleName()
				+ "_S.nextval from dual";
		return String.valueOf(entityManager
				.createNativeQuery(idsql).getResultList().get(0));
	}

	/**
	 * �õ��ö��������ݿ���ӳ������������
	 * @param entity
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����02:13:31
	 */
	public String getNewId(Object entity)
	{
		return getNewId(entity.getClass()); 
	}

	/**
	 * �ֶ��������е�entity���󲢱��浽���ݿ�
	 * @param entity
	 * @author tanw
	 * @since 2011-2-14 ����02:16:13
	 */
	public void persistById(Object entity)
	{
		entityManager.persist(entity);
	}

	/**
	 * ����entity�����Զ�����������У�������������ֵ
	 * @param entity
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����02:17:24
	 */
	public String persist(Object entity)
	{
		String id = getNewId(entity);
		BeanUtils.setObjId(entity, id);
		entityManager.persist(entity);
		return id;
	}

	/**
	 * ���ݶ������ͺ��������Ҷ��󲢷��ظ����͵Ķ���
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����02:19:51
	 */
	public <T> T get(Class<T> entityClass,Serializable id)
	{
		return entityManager.find(entityClass, id);
	}

	/**
	 * ���¸ö��󲢷��ظ��º�Ķ���
	 * @param <T>
	 * @param entity
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����02:21:57
	 */
	public <T> T merge(T entity)
	{
		return entityManager.merge(entity);
	}

	/**
	 * ɾ��entity����
	 * @param entity
	 * @author tanw
	 * @since 2011-2-14 ����02:22:48
	 */
	public void remove(Object entity)
	{
		entityManager.remove(entity);
	}

	/**
	 * ���ݶ������ͺ�����ɾ���ö���
	 * @param entityClass
	 * @param id
	 * @author tanw
	 * @since 2011-2-14 ����02:23:07
	 */
	public void removeById(Class entityClass,Serializable id)
	{
		remove(entityManager.getReference(entityClass, id));
	}

	/**
	 * 
	 * ����jpql�������ݿ��ѯ �����ؽ����
	 * 
	 * @param jpql
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����02:28:26
	 */
	public List find(final String jpql)
	{
		Assert.hasText(jpql);
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
	
	/**
	 * 
	 * ����sql�������ݿ��ѯ �����ؽ����
	 * 
	 * @param sql
	 * @return
	 * @author tanw
	 * @since 2014-2-10 ����02:28:26
	 */
	public List findByNativeQuery(final String sql)
	{
		Assert.hasText(sql);
		Query query = entityManager.createNativeQuery(sql);
		return query.getResultList();
	}
	
	/**
	 * 
	 * ����jpql�������ݿ��ѯ,������Ԥ������������ؽ����
	 * 
	 * @param jpql
	 * @param values
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����02:25:44
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
	 * ���ݶ������ͺ����Զ�Ӧֵ���в�ѯ ���ؽ����
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����03:11:02
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
	 * ���ݶ������ͺ����Զ�Ӧֵ���в�ѯ,���ݶ�������Խ������� ���ؽ����
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @param orderBy
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����03:12:27
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
	 * ����jpql���в�ѯ�����������ҳ�ź�����
	 * @param jpql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����03:13:04
	 */
	public DataPage pagedQuery(final String jpql, final int pageNo, final int pageSize)
	{
		Assert.hasText(jpql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count��ѯ
		String countQueryString = " select count (*) "
				+ removeSelect(removeOrders(jpql));
		int totalCount = ((Long) entityManager.createQuery(countQueryString).getResultList().get(0)).intValue();
		
		if (totalCount < 1)
			return new DataPage();
		// ʵ�ʲ�ѯ���ط�ҳ����
		int startIndex = DataPage.getStartOfPage(pageNo, pageSize);
		List list = entityManager.createQuery(jpql).setFirstResult(startIndex).setMaxResults(pageSize).getResultList();
		entityManager.clear();
		return new DataPage(startIndex, totalCount, pageSize, list);
	}

	/**
	 * ����������ѯ������Ԥ���������������
	 * @param jpql
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����03:51:59
	 */
	public DataPage pagedQuery(final String jpql, final int pageNo,final int pageSize,final Object... values)
	{
		Assert.hasText(jpql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count��ѯ
		String countQueryString = " select count (*) "
				+ removeSelect(removeOrders(jpql));
		int totalCount = ((Long)find(countQueryString,values).get(0)).intValue();
		if (totalCount < 1)
			return new DataPage();
		// ʵ�ʲ�ѯ���ط�ҳ����
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
	 * ����ִ�� update delete ��� 
	 * @param jpql
	 * @return
	 * @author tanw
	 * @since 2011-3-1 ����04:10:50
	 */
	public int executeUpdate(final String jpql)
	{
		Integer i = entityManager.createQuery(jpql).executeUpdate();
		entityManager.clear();
		return i;
	}
	
	/**
	 * ȡ��Order by �Ӿ�
	 * @param jpql
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����03:29:03
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
	 * ��ȥ��һ��select
	 * @param jpql
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����03:29:27
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
