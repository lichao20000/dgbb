package com.dglt.statement.vo;

/**
 * ������ʱͳ�ƵĲ�ѯ����
 * 
 * @author zhyoy
 */
public class HisStatParamVo {
	/**
	 * ��ѯ��ʼʱ��(YYYY-MM-DD HH24:MI:SS)
	 */
	private String fromDate;
	
	/**
	 * ��ѯ����ʱ��(YYYY-MM-DD HH24:MI:SS)
	 */
	private String toDate;

	/**
	 * ҵ������Code
	 */
	private String bizTypeCode;
	
	/**
	 * ҵ����������
	 */
	private String bizTypeName;
	
	/**
	 * ��������
	 */
	private String formTypeCode;
	
	/**
	 * ��������
	 */
	private String workOrderType;

	/**
	 * ����������
	 */
	private String formTypeName;
	/**
	 * ʱ������
	 */
	private String timeType;
	/**
	 * ��������
	 */
	private String formTitle;
	/**
	 * ������ˮ��
	 */
	private String formSeq;
	/**
	 * ����ͳ������
	 */
	private String linkStatType;
	
	/**
	 * ��ϸ������������
	 */
	private String individualTypeName;
	/**
	 * ����״̬�������Զ���״̬��
	 */
	private String formStatus;
	/**
	 * �����Ƿ����峬ʱ
	 */
	private String overtimeFlag;
		
	/**
	 * �ڼ�ҳ
	 */
	
	//ҵ������
	private String bizType;
	
	//���ֹ�˾
	private String lanchAreaCode;

	
	//������
	private String blongTeam;
	
	//��������״̬
	
	private String applyFormStatus;
	
	//ʩ������״̬
	
	
	
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
	 * ÿҳ��¼��
	 */
	int pageSize;

	/**
	 * �ܼ�¼��
	 * ��ֵ��out���Ͳ������ظ�ҳ��ʹ��
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
