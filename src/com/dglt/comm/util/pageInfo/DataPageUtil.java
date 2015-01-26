package com.dglt.comm.util.pageInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dglt.comm.base.DataPage;

public class DataPageUtil 
{
	private static Map gridAdapter=new HashMap();
	static
	{
		gridAdapter.put("jqgrid", new JQGridAdapter());
		gridAdapter.put("fSpiriteGrid", new FSpiriteGridAdapter());
		gridAdapter.put("skelectonGrid", new SkelectonGridAdapter());
	}

	public static GridAdapter getGridAdapter(String grid)
	{
		return (GridAdapter)gridAdapter.get(grid);
	}
	
	public static GridAdapter getGridAdapter(DataPageIntf pageInfo)
	{
		return (GridAdapter)gridAdapter.get(pageInfo.getGrid());
	}
	
	public static DataPageIntf getPageInfoFromParameterMap(HttpServletRequest request)
	{
		String grid=request.getParameter("grid");
		GridAdapter adapter=(GridAdapter)gridAdapter.get(grid);
		if(adapter!=null)
		{
			DataPageIntf dataPage=adapter.getPageInfoFromParameterMap(request);
			return dataPage;
		}
		else
		{
			return new DataPageSkelectonGrid();
		}
	}
	
	public static void copyFromDataPage(DataPageIntf dest,DataPage dataPage)
	{
		List newList=new ArrayList();
		List oldList=dataPage.getData();
		if(null != oldList)
		{
//			int size=oldList.size();
//			for(int j=0;j<size;j++)
//				newList.add(oldList.get(j));
			newList.addAll(oldList);
		}
		dest.setData(newList);
		dest.setPageNo(dataPage.getCurrentPageNo());
		dest.setPageSize(dataPage.getPageSize());
		dest.setStart(dataPage.getStart());
		dest.setTotalCount(dataPage.getTotalCount());
	}
}
