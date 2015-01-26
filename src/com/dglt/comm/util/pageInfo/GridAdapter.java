package com.dglt.comm.util.pageInfo;

import javax.servlet.http.HttpServletRequest;

public interface GridAdapter 
{
	String getPageString(DataPageIntf pageInfo);
	DataPageIntf getPageInfoFromParameterMap(HttpServletRequest request);
}
