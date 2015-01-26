package com.dglt.comm.util.pageInfo;

import java.util.List;
import java.util.Map;

import com.dglt.comm.util.gson.JsonUtil;
import com.google.gson.annotations.SerializedName;

public class DataPageSkelectonGrid implements DataPageIntf
{
	@SerializedName("pageSize")private int pageSize = 10; // ÿҳ�ļ�¼��

	@SerializedName("start")private int start; // ��ǰҳ��һ��������List�е�λ��,��0��ʼ

	@SerializedName("rows")private List data; // ��ǰҳ�д�ŵļ�¼,����һ��ΪList

	@SerializedName("rowCount")private int totalCount; // �ܼ�¼��
	
	@SerializedName("pageNo")private int pageNo; // ��ǰҳ��
	
	@SerializedName("pageNum")private int totalPageCount; // ��ҳ��
	
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