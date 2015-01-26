package com.dglt.comm.util.pageInfo;

import javax.servlet.http.HttpServletRequest;

import com.dglt.comm.util.RegexpUtil;
import com.dglt.comm.util.gson.JsonUtil;

public class SkelectonGridAdapter implements GridAdapter
{
	public String getPageString(DataPageIntf dataPage)
	{
		/*
		Map gridData=new HashMap();
		try
		{
			gridData.put("pageNum",pageInfo.getTotalPageCount());
			gridData.put("pageNo",pageInfo.getCurrentPageNo());
			gridData.put("rowCount",pageInfo.getTotalCount());
			gridData.put("pageSize",pageInfo.getPageSize());
			gridData.put("rows",pageInfo.getFileList());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		return JsonUtil.toJson(dataPage);
	}
	
	public DataPageIntf getPageInfoFromParameterMap(HttpServletRequest request)
	{
		DataPageIntf pageInfo=new DataPageSkelectonGrid();
		String pageStr=request.getParameter("pageNo");
		if(RegexpUtil.isSoftRegexpValidate(pageStr, RegexpUtil.positive_integer_regexp))
		{
			pageInfo.setPageNo(new Integer(pageStr));
		}
		String pageSizeStr=request.getParameter("pageSize");
		if(RegexpUtil.isSoftRegexpValidate(pageSizeStr, RegexpUtil.positive_integer_regexp))
		{
			pageInfo.setPageSize(new Integer(pageSizeStr));
		}
		pageInfo.setSortAttribute(request.getParameter("sidx"));
		pageInfo.setSortOrder(request.getParameter("sord"));
		return pageInfo;
	}
}