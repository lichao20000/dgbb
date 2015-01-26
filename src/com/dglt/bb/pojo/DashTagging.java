package com.dglt.bb.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dglt.comm.base.BaseVO;

@Entity
@Table(name = "W_DASH_TAGGING")
public class DashTagging extends BaseVO {
	private static final long serialVersionUID = 11L;

	@Id
	@Column(name = "TAGGING_ID")
	private String taggingId;
	@Column(name = "KPI_ID")
	private String kpiId;
	@Column(name = "KPI_NAME")
	private String kpiName;
	@Column(name = "MONTH_ID")
	private String monthId;
	@Column(name = "MONTH_DSC")
	private String monthDsc;
	@Column(name = "SP_CODE")
	private String spcode;
	@Column(name = "SP_NAME")
	private String spname;
	@Column(name = "CLIENT_CODE")
	private String clientCode;
	@Column(name = "CIENT_NAME")
	private String clientName;
	@Column(name = "PRODUCT_CODE")
	private String productCode;
	@Column(name = "PRODUCT_NAME")
	private String productName;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "CONTENT")
	private String content;
	@Column(name = "ATTRIB1")
	private String attrib1;
	@Column(name = "ATTRIB2")
	private String attrib2;
	@Column(name = "ATTRIB3")
	private String attrib3;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATION_DATE")
	private String creationDate;
	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;
	@Column(name = "LAST_UPDATE_DATE")
	private String lastUpdateDate;
	public String getTaggingId() {
		return taggingId;
	}
	public void setTaggingId(String taggingId) {
		this.taggingId = taggingId;
	}
	public String getKpiId() {
		return kpiId;
	}
	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}
	public String getKpiName() {
		return kpiName;
	}
	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}
	public String getMonthId() {
		return monthId;
	}
	public void setMonthId(String monthId) {
		this.monthId = monthId;
	}
	public String getMonthDsc() {
		return monthDsc;
	}
	public void setMonthDsc(String monthDsc) {
		this.monthDsc = monthDsc;
	}
	public String getSpcode() {
		return spcode;
	}
	public void setSpcode(String spcode) {
		this.spcode = spcode;
	}
	public String getSpname() {
		return spname;
	}
	public void setSpname(String spname) {
		this.spname = spname;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAttrib1() {
		return attrib1;
	}
	public void setAttrib1(String attrib1) {
		this.attrib1 = attrib1;
	}
	public String getAttrib2() {
		return attrib2;
	}
	public void setAttrib2(String attrib2) {
		this.attrib2 = attrib2;
	}
	public String getAttrib3() {
		return attrib3;
	}
	public void setAttrib3(String attrib3) {
		this.attrib3 = attrib3;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
}
