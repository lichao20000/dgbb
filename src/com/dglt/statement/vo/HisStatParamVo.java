package com.dglt.statement.vo;

/**
 * 工单用时统计的查询参数
 * 
 * @author zhyoy
 */
public class HisStatParamVo {
	/**
	 * 查询开始时间(YYYY-MM-DD HH24:MI:SS)
	 */
	private String fromDate;
	
	/**
	 * 查询结束时间(YYYY-MM-DD HH24:MI:SS)
	 */
	private String toDate;

	/**
	 * 业务类型Code
	 */
	private String bizTypeCode;
	
	/**
	 * 业务类型名称
	 */
	private String bizTypeName;
	
	/**
	 * 工单类型
	 */
	private String formTypeCode;
	
	/**
	 * 需求类型
	 */
	private String workOrderType;

	/**
	 * 工单类型名
	 */
	private String formTypeName;
	/**
	 * 时间类型
	 */
	private String timeType;
	/**
	 * 工单标题
	 */
	private String formTitle;
	/**
	 * 工单流水号
	 */
	private String formSeq;
	/**
	 * 环节统计类型
	 */
	private String linkStatType;
	
	/**
	 * 明细报表类型名称
	 */
	private String individualTypeName;
	/**
	 * 工单状态（包括自定义状态）
	 */
	private String formStatus;
	/**
	 * 工单是否整体超时
	 */
	private String overtimeFlag;
		
	/**
	 * 第几页
	 */
	
	//业务类型
	private String bizType;
	
	//按分公司
	private String lanchAreaCode;

	
	//按网格
	private String blongTeam;
	
	//审批工单状态
	
	private String applyFormStatus;
	
	//施工工单状态
	
	
	
	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getLanchAreaCode() {
		return lanchAreaCode;
	}

	public void setLanchAreaCode(String lanchAreaCode) {
		this.lanchAreaCode = lanchAreaCode;
	}

	public String getBlongTeam() {
		return blongTeam;
	}

	public void setBlongTeam(String blongTeam) {
		this.blongTeam = blongTeam;
	}

	public String getApplyFormStatus() {
		return applyFormStatus;
	}

	public void setApplyFormStatus(String applyFormStatus) {
		this.applyFormStatus = applyFormStatus;
	}

	int curPage;
	
	/**
	 * 每页记录数
	 */
	int pageSize;

	/**
	 * 总记录数
	 * 该值是out类型参数返回给页面使用
	 */
	int pageTotal;
	
	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public int getCurPage() {
		if(curPage == 0) curPage = 1;
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}


	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public String getFormTypeCode() {
		return formTypeCode;
	}

	public void setFormTypeCode(String formTypeCode) {
		this.formTypeCode = formTypeCode;
	}

	public String getBizTypeCode() {
		return bizTypeCode;
	}

	public void setBizTypeCode(String bizTypeCode) {
		this.bizTypeCode = bizTypeCode;
	}

	public String getBizTypeName() {
		return bizTypeName;
	}

	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
	}

	public String getFormTypeName() {
		return formTypeName;
	}

	public void setFormTypeName(String formTypeName) {
		this.formTypeName = formTypeName;
	}

	public String getWorkOrderType() {
		return workOrderType;
	}

	public void setWorkOrderType(String workOrderType) {
		this.workOrderType = workOrderType;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getFormTitle() {
		return formTitle;
	}

	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}

	public String getFormSeq() {
		return formSeq;
	}

	public void setFormSeq(String formSeq) {
		this.formSeq = formSeq;
	}

	public String getLinkStatType() {
		return linkStatType;
	}

	public void setLinkStatType(String linkStatType) {
		this.linkStatType = linkStatType;
	}

	public String getIndividualTypeName() {
		return individualTypeName;
	}

	public void setIndividualTypeName(String individualTypeName) {
		this.individualTypeName = individualTypeName;
	}

	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}

	public String getOvertimeFlag() {
		return overtimeFlag;
	}

	public void setOvertimeFlag(String overtimeFlag) {
		this.overtimeFlag = overtimeFlag;
	}
	
	
}
