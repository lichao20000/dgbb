package com.dglt.comm.util.pageInfo;

import javax.servlet.http.HttpServletRequest;

import com.dglt.comm.util.RegexpUtil;
import com.dglt.comm.util.gson.JsonUtil;

public class FSpiriteGridAdapter implements GridAdapter
{
	public String getPageString(DataPageIntf pageInfo)
	{
		/*
		JSONObject gridData=new JSONObject();
		try
		{
			gridData.put("totalpages",""+pageInfo.getTotalPageCount());
			gridData.put("currpage",""+pageInfo.getCurrentPageNo());
			gridData.put("totalrecords",""+pageInfo.getTotalCount());
			gridData.put("pagesize",""+pageInfo.getPageSize());
			List records=pageInfo.getFileList();
			String recordsJson=JsonUtil.toJson(records);
			JSONArray rows=new JSONArray(recordsJson);
			gridData.put("invdata",rows);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return gridData;*/
		return JsonUtil.toJson(pageInfo);
	}
	
	public DataPageIntf getPageInfoFromParameterMap(HttpServletRequest request)
	{
		DataPageIntf pageInfo=new DataPageJQGrid();
		String pageStr=request.getParameter("page");
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
