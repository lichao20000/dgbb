package com.dglt.bb.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the T_APP_ENUM_ITEM database table.
 * 
 */
@Entity
@Table(name="T_APP_ENUM_ITEM")
@NamedQuery(name="TAppEnumItem.findAll", query="SELECT t FROM TAppEnumItem t")
public class TAppEnumItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_APP_ENUM_ITEM_ENUM_ITEM_ID_GENERATOR", sequenceName="APP_ENUM_ITEM_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_APP_ENUM_ITEM_ENUM_ITEM_ID_GENERATOR")
	@Column(name="ENUM_ITEM_ID")
	private Long enumItemId;

	private String attribute1;

	private String attribute2;

	private String attribute3;

	private String attribute4;

	private String attribute5;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="DELETED_BY")
	private String deletedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DELETED_DATE")
	private Date deletedDate;

	@Column(name="DELETED_FLAG")
	private BigDecimal deletedFlag;

	private String description;

	@Column(name="ENABLED_FLAG")
	private String enabledFlag;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="END_ACTIVE_DATE")
	private Date endActiveDate;

	@Column(name="ENUM_ITEM_CODE")
	private String enumItemCode;

	@Column(name="ENUM_ITEM_NAME")
	private String enumItemName;

	@Column(name="FORM_VER")
	private BigDecimal formVer;

	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATED_DATE")
	private Date lastUpdatedDate;

	@Column(name="PARENT_ENUM_ITEM_CODE")
	private String parentEnumItemCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="START_ACTIVE_DATE")
	private Date startActiveDate;

	//bi-directional many-to-one association to TAppEnumValue
	@OneToMany(mappedBy="TAppEnumItem", cascade={CascadeType.ALL})
	private List<TAppEnumValue> TAppEnumValues;

	public TAppEnumItem() {
	}

	public String getAttribute1() {
		return this.attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return this.attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return this.attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return this.attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return this.attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDeletedBy() {
		return this.deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Date getDeletedDate() {
		return this.deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public BigDecimal getDeletedFlag() {
		return this.deletedFlag;
	}

	public void setDeletedFlag(BigDecimal deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnabledFlag() {
		return this.enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public Date getEndActiveDate() {
		return this.endActiveDate;
	}

	public void setEndActiveDate(Date endActiveDate) {
		this.endActiveDate = endActiveDate;
	}

	public String getEnumItemCode() {
		return this.enumItemCode;
	}

	public void setEnumItemCode(String enumItemCode) {
		this.enumItemCode = enumItemCode;
	}

	public String getEnumItemName() {
		return this.enumItemName;
	}

	public void setEnumItemName(String enumItemName) {
		this.enumItemName = enumItemName;
	}

	public BigDecimal getFormVer() {
		return this.formVer;
	}

	public void setFormVer(BigDecimal formVer) {
		this.formVer = formVer;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdatedDate() {
		return this.lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getParentEnumItemCode() {
		return this.parentEnumItemCode;
	}

	public void setParentEnumItemCode(String parentEnumItemCode) {
		this.parentEnumItemCode = parentEnumItemCode;
	}

	public Date getStartActiveDate() {
		return this.startActiveDate;
	}

	public void setStartActiveDate(Date startActiveDate) {
		this.startActiveDate = startActiveDate;
	}

	public List<TAppEnumValue> getTAppEnumValues() {
		return this.TAppEnumValues;
	}

	public void setTAppEnumValues(List<TAppEnumValue> TAppEnumValues) {
		this.TAppEnumValues = TAppEnumValues;
	}

	public TAppEnumValue addTAppEnumValue(TAppEnumValue TAppEnumValue) {
		getTAppEnumValues().add(TAppEnumValue);
		TAppEnumValue.setTAppEnumItem(this);

		return TAppEnumValue;
	}

	public TAppEnumValue removeTAppEnumValue(TAppEnumValue TAppEnumValue) {
		getTAppEnumValues().remove(TAppEnumValue);
		TAppEnumValue.setTAppEnumItem(null);

		return TAppEnumValue;
	}

	public Long getEnumItemId() {
		return enumItemId;
	}

	public void setEnumItemId(Long enumItemId) {
		this.enumItemId = enumItemId;
	}



}