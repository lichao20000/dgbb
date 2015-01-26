package com.dglt.comm.util.pageInfo;

import java.util.List;
import java.util.Map;
public interface DataPageIntf
{
	public void setPageNo(int pageNo);
	public int getPageNo();
	public int getTotalCount();
	public int getTotalPageCount();
	public int getPageSize();
	public List getData();
	public int getCurrentPageNo();
	public boolean hasNextPage();
	public boolean hasPreviousPage();
//	public int getStartOfPage(int pageNo, int pageSize);
	public void setData(List data);
	public int getStart();
	public void setStart(int start);
	public void setPageSize(int pageSize);
	public void setTotalCount(int totalCount);
	
	public String getSortAttribute();
	public void setSortAttribute(String sortAttribute);
	public String getSortOrder();
	public void setSortOrder(String sortOrder);
	public String getGrid();
	
    public String toString();
    public String toString(Map userData);
}
