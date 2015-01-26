package com.dglt.comm.util.pageInfo;

import java.util.List;
import java.util.Map;

import com.dglt.comm.util.gson.JsonUtil;
import com.google.gson.annotations.SerializedName;

public class DataPageSkelectonGrid implements DataPageIntf
{
	@SerializedName("pageSize")private int pageSize = 10; // 每页的记录数

	@SerializedName("start")private int start; // 当前页第一条数据在List中的位置,从0开始

	@SerializedName("rows")private List data; // 当前页中存放的记录,类型一般为List

	@SerializedName("rowCount")private int totalCount; // 总记录数
	
	@SerializedName("pageNo")private int pageNo; // 当前页码
	
	@SerializedName("pageNum")private int totalPageCount; // 总页数
	
	@SerializedName("userdata") public Map userData;
	
	protected String grid="skelectonGrid";
    private String sortAttribute=null;
    private String sortOrder=null;
    
	public String getSortAttribute() 
	{
		if(sortAttribute==null || sortAttribute.length()==0)
			return null;
		return sortAttribute;
	}

	public void setSortAttribute(String sortAttribute) {
		this.sortAttribute = sortAttribute;
	}

	public String getSortOrder() {
		if(sortOrder==null || sortOrder.length()==0)
			return null;
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public String getGrid() {
		return grid;
	}
	
    public String toString()
    {
    	this.totalPageCount=getTotalPageCount();
    	return JsonUtil.toJson(this);
    }
    
    public String toString(Map userData)
    {
    	this.userData=userData;
    	return toString();
    }
    
	@Override
	public int getPageNo()
	{
		return this.pageNo;
	}

	@Override
	public void setPageNo(int pageNo)
	{
		this.pageNo=pageNo;
	}
	
    public int calcStartOfPage(int page)
    {
        return DataPageSkelectonGrid.calcStartOfPage(page,10);
    }
    
    public int calcStartOfThisPage()
    {
        return DataPageSkelectonGrid.calcStartOfPage(this.pageNo, this.pageSize);
    }
    
    public static int calcStartOfPage(int pageNo, int pageSize)
    {
        if(pageSize <= 0)
            pageSize = 10;
        if(pageNo <= 0)
        	pageNo = 1;
        return (pageNo - 1) * pageSize;
    }

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

    public int getTotalPageCount()
    {
        if(totalCount % pageSize == 0)
            return (int)totalCount / pageSize;
        else
            return (int)totalCount / pageSize + 1;
    }

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public Map getUserData() {
		return userData;
	}

	public void setUserData(Map userData) {
		this.userData = userData;
	}

	public void setGrid(String grid) {
		this.grid = grid;
	}

	@Override
	public int getCurrentPageNo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasNextPage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPreviousPage() {
		// TODO Auto-generated method stub
		return false;
	}
}