package com.dglt.comm.util;

import com.dglt.comm.base.BaseVO;

public class PageBean extends BaseVO{
	private static final long serialVersionUID = 8922951952061188176L;
	private int curPage;// ��ǰҳ
	private int totalRows;// ����¼��
	private int pageSize;// ÿҳ��ʾ��¼��
	private int pageCount;// ��ҳ��
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
			pager.append("<font color='#999999'> [��ҳ] </font>");
			pager.append("<font color='#999999'> [��һҳ] </font>");
		} else if ( curPage > 1 ) 
		{
			pager.append("<a href=\"javascript:" + method + "(1)\"> [��ҳ] </a>");
			pager.append("<a href=\"javascript:" + method + "(" + (curPage - 1) + ")\"> [��һҳ] </a>");
		}
		
		if ( curPage < pageCount ) 
		{
			pager.append("<a href=\"javascript:" + method + "(" + ( curPage + 1 ) + ")\"> [��һҳ] ");
			pager.append("<a href=\"javascript:" + method + "(" + pageCount + ")\"> [βҳ] </a>");
		} else if ( curPage == pageCount ) {
			pager.append(" <font color='#999999'>[��һҳ]</font> ");
			pager.append(" <font color='#999999'>[βҳ]</font> ");
		}
		
		pager.append(" ��<font color='red'>" + curPage + "</font>/");
		pager.append(pageCount + "ҳ ");
		pager.append(" ��" + totalRows + "����¼ ");
		
		pager.append("ת��<input id='pageNoInput' type='text' class='line' size='4' onKeypress='if (event.keyCode < 48 || event.keyCode > 57) event.returnValue = false;' value='" + curPage + "'/>ҳ");
		pager.append("<input type=\"button\" value=\"Go\" onclick=\"" + method +"(document.getElementById('pageNoInput').value);\"/>");
		pager.append("</div>");
		
		this.pageToolsBar = pager.toString();
	}
	
	public static void main(String[]args) {
		PageBean p = new PageBean(23,2,5,"getabc");
		System.out.println(p.getPageToolsBar());
	}
	
}
