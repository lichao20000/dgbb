package com.dglt.comm.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService {
	/**
	 * 得到该对象在数据库中映射表的主键序列
	 * @param entityClass
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午02:10:09
	 */
	public String getNewId(Class entityClass);

	/**
	 * 得到该对象在数据库中映射表的主键序列
	 * @param entity
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午02:13:31
	 */
	public String getNewId(Object entity);
	/**
	 * 手动主键序列到entity对象并保存到数据库
	 * @param entity
	 * @author tanw
	 * @since 2011-2-14 下午02:16:13
	 */
	public void persistById(Object entity);

	/**
	 * 保存entity对象，自动填充主键序列，返回主键序列值
	 * @param entity
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午02:17:24
	 */
	public String persist(Object entity);

	/**
	 * 根据对象类型和主键查找对象并返回该类型的对象
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午02:19:51
	 */
	public <T> T get(Class<T> entityClass,Serializable id);

	/**
	 * 更新该对象并返回更新后的对象
	 * @param <T>
	 * @param entity
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午02:21:57
	 */
	public <T> T merge(T entity);

	/**
	 * 删除entity对象
	 * @param entity
	 * @author tanw
	 * @since 2011-2-14 下午02:22:48
	 */
	public void remove(Object entity);
	/**
	 * 根据对象类型和主键删除该对象
	 * @param entityClass
	 * @param id
	 * @author tanw
	 * @since 2011-2-14 下午02:23:07
	 */
	public void removeById(Class entityClass,Serializable id);
	/**
	 * 
	 * 根据jpql进行数据库查询 ，返回结果集
	 * 
	 * @param jpql
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午02:28:26
	 */
	public List find(final String jpql);
	
	public List findByNativeQuery(final String sql);
	
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
	public List find(final String jpql, final Object... values);
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
	public <T> List<T> findBy(final Class<T> entityClass,final String propertyName,final Object value);

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
	public <T> List<T> findBy(final Class<T> entityClass,final String propertyName, final Object value,final Map<String, Boolean> orderBy);

	/**
	 * 根据jpql进行查询结果集，传递页号和条数
	 * @param jpql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @author tanw
	 * @since 2011-2-14 下午03:13:04
	 */
	public DataPage pagedQuery(final String jpql, final int pageNo, final int pageSize);

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
	public DataPage pagedQuery(final String jpql, final int pageNo,final int pageSize,final Object... values);
	
	/**
	 * 批量执行 update delete 语句 
	 * @param jpql
	 * @return
	 * @author tanw
	 * @since 2011-3-1 下午04:10:50
	 */
	public int executeUpdate(final String jpql);
	
	/**
	 * 取得jpql语句所查询的结果的行数
	 * @param jpql
	 * @return
	 * @author 卢斌晖
	 * @since Mar 3, 2011 1:44:55 PM
	 */
	public long queryCount(final String jpql);
}
