package com.dglt.comm.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService {
	/**
	 * �õ��ö��������ݿ���ӳ������������
	 * @param entityClass
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����02:10:09
	 */
	public String getNewId(Class entityClass);

	/**
	 * �õ��ö��������ݿ���ӳ������������
	 * @param entity
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����02:13:31
	 */
	public String getNewId(Object entity);
	/**
	 * �ֶ��������е�entity���󲢱��浽���ݿ�
	 * @param entity
	 * @author tanw
	 * @since 2011-2-14 ����02:16:13
	 */
	public void persistById(Object entity);

	/**
	 * ����entity�����Զ�����������У�������������ֵ
	 * @param entity
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����02:17:24
	 */
	public String persist(Object entity);

	/**
	 * ���ݶ������ͺ��������Ҷ��󲢷��ظ����͵Ķ���
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����02:19:51
	 */
	public <T> T get(Class<T> entityClass,Serializable id);

	/**
	 * ���¸ö��󲢷��ظ��º�Ķ���
	 * @param <T>
	 * @param entity
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����02:21:57
	 */
	public <T> T merge(T entity);

	/**
	 * ɾ��entity����
	 * @param entity
	 * @author tanw
	 * @since 2011-2-14 ����02:22:48
	 */
	public void remove(Object entity);
	/**
	 * ���ݶ������ͺ�����ɾ���ö���
	 * @param entityClass
	 * @param id
	 * @author tanw
	 * @since 2011-2-14 ����02:23:07
	 */
	public void removeById(Class entityClass,Serializable id);
	/**
	 * 
	 * ����jpql�������ݿ��ѯ �����ؽ����
	 * 
	 * @param jpql
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����02:28:26
	 */
	public List find(final String jpql);
	
	public List findByNativeQuery(final String sql);
	
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
	public List find(final String jpql, final Object... values);
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
	public <T> List<T> findBy(final Class<T> entityClass,final String propertyName,final Object value);

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
	public <T> List<T> findBy(final Class<T> entityClass,final String propertyName, final Object value,final Map<String, Boolean> orderBy);

	/**
	 * ����jpql���в�ѯ�����������ҳ�ź�����
	 * @param jpql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @author tanw
	 * @since 2011-2-14 ����03:13:04
	 */
	public DataPage pagedQuery(final String jpql, final int pageNo, final int pageSize);

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
	public DataPage pagedQuery(final String jpql, final int pageNo,final int pageSize,final Object... values);
	
	/**
	 * ����ִ�� update delete ��� 
	 * @param jpql
	 * @return
	 * @author tanw
	 * @since 2011-3-1 ����04:10:50
	 */
	public int executeUpdate(final String jpql);
	
	/**
	 * ȡ��jpql�������ѯ�Ľ��������
	 * @param jpql
	 * @return
	 * @author ¬����
	 * @since Mar 3, 2011 1:44:55 PM
	 */
	public long queryCount(final String jpql);
}
