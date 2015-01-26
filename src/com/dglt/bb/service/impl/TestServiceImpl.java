package com.dglt.bb.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dglt.bb.dao.TestDAO;
import com.dglt.bb.service.TestService;
import com.dglt.comm.base.BaseServiceImpl;

@Service("testService")
public class TestServiceImpl extends BaseServiceImpl implements TestService
{
	
	@Resource(name = "testDAO")
	private TestDAO testDAO;
	
	@Transactional
	public Object insert(Object entity)
	{
		return persist(entity);
	}
	@Transactional
	public int exec(String jpql)
	{
		return this.executeUpdate(jpql);
	}
	
	public void testJdbc(){
		testDAO.a();
	}
}