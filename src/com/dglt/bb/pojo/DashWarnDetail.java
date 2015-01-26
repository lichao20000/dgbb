package com.dglt.bb.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dglt.comm.base.BaseVO;

@Entity
@Table(name = "W_DASH_WARN_DETAIL")
public class DashWarnDetail extends BaseVO {
	private static final long serialVersionUID = 13L;

	@Id
	@Column(name = "WARN_DETAIL_ID")
	private String warnDetailId;
	@Column(name = "WARN_INFO_ID")
	private String warnInfoId;
	@Column(name = "RECIEVER")
	private String reciever;
	@Column(name = "SEND_TYPE")
	private String sendType;
	@Column(name = "MOBILE")
	private String mobile;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "SEND_STATUS")
	private String sendStatus;
	@Column(name = "SEND_DATE")
	private String sendDate;
	@Column(name = "ATTRIB1")
	private String attrib1;
	@Column(name = "ATTRIB2")
	private String attrib2;
	@Column(name = "ATTRIB3")
	private String attrib3;
	public String getWarnDetailId() {
		return warnDetailId;
	}
	public void setWarnDetailId(String warnDetailId) {
		this.warnDetailId = warnDetailId;
	}
	public String getWarnInfoId() {
		return warnInfoId;
	}
	public void setWarnInfoId(String warnInfoId) {
		this.warnInfoId = warnInfoId;
	}
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
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

}
