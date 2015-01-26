package com.dglt.comm.util;

import com.dglt.comm.base.BaseVO;

public class PageBean extends BaseVO{
	private static final long serialVersionUID = 8922951952061188176L;
	private int curPage;// 当前页
	private int totalRows;// 最大记录数
	private int pageSize;// 每页显示记录数
	private int pageCount;// 总页数
	private String pageToolsBar;
	
	public PageBean(){
	}
	
	public PageBean(int totalRows,int curPage,int pageSize){
		setCurPage(curPage);
		setTotalRows(totalRows);
		setPageSize(pageSize);
		setPageCount( ( this.totalRows - 1 ) / this.pageSize + 1 );
		if ( curPage > pageCount )
		{
			setCurPage(pageCount);
		}
	}
	
	public PageBean(int totalRows,int curPage,int pageSize,String method){
		setCurPage(curPage);
		setTotalRows(totalRows);
		setPageSize(pageSize);
		setPageCount( ( this.totalRows - 1 ) / this.pageSize + 1 );
		if ( curPage > pageCount )
		{
			setCurPage(pageCount);
		}
		initToolsBar(method);
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getPageToolsBar() {
		return pageToolsBar;
	}

	public void setPageToolsBar(String pageToolsBar) {
		this.pageToolsBar = pageToolsBar;
	}

	public void initPage(int totalRows,int curPage,int pageSize,String method){
		setCurPage(curPage);
		setTotalRows(totalRows);
		setPageSize(pageSize);
		setPageCount( ( this.totalRows - 1 ) / this.pageSize + 1 );
		if ( curPage > pageCount )
		{
			setCurPage(pageCount);
		}
		initToolsBar(method);
	}
	 
	public void initToolsBar(String method) {
		StringBuffer pager = new StringBuffer();
		pager.append("<div>");
		if ( curPage == 1 ) 
		{
			pager.append("<font color='#999999'> [首页] </font>");
			pager.append("<font color='#999999'> [上一页] </font>");
		} else if ( curPage > 1 ) 
		{
			pager.append("<a href=\"javascript:" + method + "(1)\"> [首页] </a>");
			pager.append("<a href=\"javascript:" + method + "(" + (curPage - 1) + ")\"> [上一页] </a>");
		}
		
		if ( curPage < pageCount ) 
		{
			pager.append("<a href=\"javascript:" + method + "(" + ( curPage + 1 ) + ")\"> [下一页] ");
			pager.append("<a href=\"javascript:" + method + "(" + pageCount + ")\"> [尾页] </a>");
		} else if ( curPage == pageCount ) {
			pager.append(" <font color='#999999'>[下一页]</font> ");
			pager.append(" <font color='#999999'>[尾页]</font> ");
		}
		
		pager.append(" 第<font color='red'>" + curPage + "</font>/");
		pager.append(pageCount + "页 ");
		pager.append(" 共" + totalRows + "条记录 ");
		
		pager.append("转到<input id='pageNoInput' type='text' class='line' size='4' onKeypress='if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;' value='" + curPage + "'/>页");
		pager.append("<input type=\"button\" value=\"Go\" onclick=\"" + method +"(document.getElementById('pageNoInput').value);\"/>");
		pager.append("</div>");
		
		this.pageToolsBar = pager.toString();
	}
	
	public static void main(String[]args) {
		PageBean p = new PageBean(23,2,5,"getabc");
		System.out.println(p.getPageToolsBar());
	}
	
}
