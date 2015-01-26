package com.dglt.comm.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseServiceImpl implements BaseService {
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	private BaseDAO baseDAO;

	/**
	 * �õ��ö��������ݿ���ӳ������������
	 * @param entityClass
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����02:10:09
	 */
	public String getNewId(Class entityClass)
	{
		return baseDAO.getNewId(entityClass);
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
		return baseDAO.getNewId(entity); 
	}

	/**
	 * �ֶ��������е�entity���󲢱��浽���ݿ�
	 * @param entity
	 * @author tanw
	 * @since 2011-2-14 ����02:16:13
	 */

	public void persistById(Object entity)
	{
		baseDAO.persistById(entity);
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
		return baseDAO.persist(entity);
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
		return baseDAO.get(entityClass, id);
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
		return baseDAO.merge(entity);
	}

	/**
	 * ɾ��entity����
	 * @param entity
	 * @author tanw
	 * @since 2011-2-14 ����02:22:48
	 */

	public void remove(Object entity)
	{
		baseDAO.remove(entity);
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
		baseDAO.removeById(entityClass, id);
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
		return baseDAO.find(jpql);
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
		return baseDAO.find(jpql, values);
	}
	
	public List findByNativeQuery(final String sql){
		return baseDAO.findByNativeQuery(sql);
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
		return baseDAO.findBy(entityClass, propertyName, value);
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
		return baseDAO.findBy(entityClass, propertyName, value, orderBy);
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
		return baseDAO.pagedQuery(jpql, pageNo, pageSize);
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
		return baseDAO.pagedQuery(jpql, pageNo, pageSize, values);
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
		return baseDAO.executeUpdate(jpql);
	}

	public long queryCount(String jpql)
	{
		return this.baseDAO.queryCount(jpql);
	}

}
