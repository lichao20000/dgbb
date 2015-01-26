package com.dglt.bb.service;

import com.dglt.comm.base.BaseService;

public interface TestService extends BaseService
{
	public Object insert(Object entity);
	public int exec(String jpql);
	public void testJdbc();
}
