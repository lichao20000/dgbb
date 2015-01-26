package com.dglt.bb.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dglt.comm.base.BaseVO;

@Entity
@Table(name = "W_DASH_TAGGING_REPLY")
public class DashTaggingReply extends BaseVO {
	private static final long serialVersionUID = 10L;

	@Id
	@Column(name = "TAGGING_REPLY_ID")
	private String taggingReplyId;

	@Column(name = "TAGGING_ID")
	private String taggingId;

	@Column(name = "REPLYER")
	private String replyer;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "REMARK")
	private String remark;

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

	public String getTaggingReplyId() {
		return taggingReplyId;
	}

	public void setTaggingReplyId(String taggingReplyId) {
		this.taggingReplyId = taggingReplyId;
	}

	public String getTaggingId() {
		return taggingId;
	}

	public void setTaggingId(String taggingId) {
		this.taggingId = taggingId;
	}

	public String getReplyer() {
		return replyer;
	}

	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
