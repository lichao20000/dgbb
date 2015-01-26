package com.dglt.comm.util.pageInfo;

import javax.servlet.http.HttpServletRequest;

import com.dglt.comm.util.RegexpUtil;
import com.dglt.comm.util.gson.JsonUtil;

public class JQGridAdapter implements GridAdapter
{
	public String getPageString(DataPageIntf dataPage)
	{
		/*
		JSONObject gridData=new JSONObject();
		try
		{
			gridData.put("totalpages",""+pageInfo.getTotalPageCount());
			gridData.put("currpage",""+pageInfo.getCurrentPageNo());
			gridData.put("totalrecords",""+pageInfo.getTotalCount());
			List records=pageInfo.getFileList();
			String recordsJson=JsonUtil.toJson(records);
			JSONArray rows=new JSONArray(recordsJson);
			gridData.put("invdata",rows);
			
			JSONObject userData=new JSONObject();
			userData.put("totalpages",gridData.getString("totalpages"));
			gridData.put("userdata", userData);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return gridData;
		*/
		return JsonUtil.toJson(dataPage);
	}
	
	public DataPageIntf getPageInfoFromParameterMap(HttpServletRequest request)
	{
		DataPageIntf pageInfo=new DataPageJQGrid();
		String pageStr=request.getParameter("page");
		if(RegexpUtil.isSoftRegexpValidate(pageStr, RegexpUtil.positive_integer_regexp))
		{
			pageInfo.setPageNo(new Integer(pageStr));
		}
		String pageSizeStr=request.getParameter("rows");
		if(RegexpUtil.isSoftRegexpValidate(pageSizeStr, RegexpUtil.positive_integer_regexp))
		{
			pageInfo.setPageSize(new Integer(pageSizeStr));
		}
		pageInfo.setSortAttribute(request.getParameter("sidx"));
		pageInfo.setSortOrder(request.getParameter("sord"));
		return pageInfo;
	}
}
