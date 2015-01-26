package com.dglt.bb.pojo;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the T_APP_ENUM_VALUE database table.
 * 
 */
@Entity
@Table(name="T_APP_ENUM_VALUE")
@NamedQuery(name="TAppEnumValue.findAll", query="SELECT t FROM TAppEnumValue t")
public class TAppEnumValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="T_APP_ENUM_VALUE_ENUM_VALUE_ID_GENERATOR", sequenceName="APP_ENUM_VALUE_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_APP_ENUM_VALUE_ENUM_VALUE_ID_GENERATOR")
	@Column(name="ENUM_VALUE_ID")
	private Long enumValueId;

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

	@Column(name="ENABLED_FLAG")
	private String enabledFlag;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="END_ACTIVE_DATE")
	private Date endActiveDate;

	@Column(name="ENUM_ITEM_CODE")
	private String enumItemCode;

	@Column(name="ENUM_VALUE")
	private String enumValue;

	@Column(name="ENUM_VALUE_MEANING")
	private String enumValueMeaning;

	@Column(name="ENUM_VALUE_NUM")
	private BigDecimal enumValueNum;

	@Column(name="FORM_VER")
	private BigDecimal formVer;

	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATED_DATE")
	private Date lastUpdatedDate;

	@Column(name="PARENT_ENUM_VALUE")
	private String parentEnumValue;

    @Column(name="SCENE")
    private String scene;

	@Column(name="SORT_NUM")
	private BigDecimal sortNum;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="START_ACTIVE_DATE")
	private Date startActiveDate;

	//bi-directional many-to-one association to TAppEnumItem
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="ENUM_ITEM_ID", referencedColumnName="ENUM_ITEM_ID")
	private TAppEnumItem TAppEnumItem;

	public TAppEnumValue() {
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

	public String getEnumValue() {
		return this.enumValue;
	}

	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}

	public String getEnumValueMeaning() {
		return this.enumValueMeaning;
	}

	public void setEnumValueMeaning(String enumValueMeaning) {
		this.enumValueMeaning = enumValueMeaning;
	}

	public BigDecimal getEnumValueNum() {
		return this.enumValueNum;
	}

	public void setEnumValueNum(BigDecimal enumValueNum) {
		this.enumValueNum = enumValueNum;
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

	public String getParentEnumValue() {
		return this.parentEnumValue;
	}

	public void setParentEnumValue(String parentEnumValue) {
		this.parentEnumValue = parentEnumValue;
	}

	public BigDecimal getSortNum() {
		return this.sortNum;
	}

	public void setSortNum(BigDecimal sortNum) {
		this.sortNum = sortNum;
	}

	public Date getStartActiveDate() {
		return this.startActiveDate;
	}

	public void setStartActiveDate(Date startActiveDate) {
		this.startActiveDate = startActiveDate;
	}

	public TAppEnumItem getTAppEnumItem() {
		return this.TAppEnumItem;
	}

	public void setTAppEnumItem(TAppEnumItem TAppEnumItem) {
		this.TAppEnumItem = TAppEnumItem;
	}








	public Long getEnumValueId() {
		return enumValueId;
	}

	public void setEnumValueId(Long enumValueId) {
		this.enumValueId = enumValueId;
	}

	/**
     * <pre>
     * scene的取�?
     * </pre>
     * @return scene
     */
    public String getScene() {
        return scene;
    }

    /**
     * <pre>
     * scene的设�?
     * </pre>
     * @param scene the scene to set
     */
    public void setScene(String scene) {
        this.scene = scene;
    }

}