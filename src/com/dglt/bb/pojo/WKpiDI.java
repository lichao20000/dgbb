package com.dglt.bb.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dglt.comm.base.BaseVO;

//首页视图实体
@Entity
@Table(name="W_KPI_DI")
public class WKpiDI extends BaseVO {
	private static final long serialVersionUID = 3L;
	
	@Id 
	@Column(name="ROW_ID") 
	private Long rowId; 
	
	@Column(name="Kpi_Name") 
	private String kpiName; 
	
	@Column(name="MONTH_ID") 
	private String monthId; 
	
	@Column(name="MONTH_DSC") 
	private String monthDsc; 
	
	@Column(name="SP_CODE") 
	private String spCode; 
	
	@Column(name="SP_NAME") 
	private String spName; 
	
	@Column(name="District_Branch_Code") 
	private String districtBranchCode; 
	
	@Column(name="District_Branch_Name") 
	private String districtBranchName; 
	
	@Column(name="Busi_Sc_Code") 
	private String busiScCode; 
	
	@Column(name="Busi_Sc_Name") 
	private String busiScName;  
	
	@Column(name="ATTRIBUTE1") 
	private String attribute1;
	
	@Column(name="Amount_Curr") 
	private String amountCurr;

	public Long getRowId() {
		return rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
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

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getDistrictBranchCode() {
		return districtBranchCode;
	}

	public void setDistrictBranchCode(String districtBranchCode) {
		this.districtBranchCode = districtBranchCode;
	}

	public String getDistrictBranchName() {
		return districtBranchName;
	}

	public void setDistrictBranchName(String districtBranchName) {
		this.districtBranchName = districtBranchName;
	}

	public String getBusiScCode() {
		return busiScCode;
	}

	public void setBusiScCode(String busiScCode) {
		this.busiScCode = busiScCode;
	}

	public String getBusiScName() {
		return busiScName;
	}

	public void setBusiScName(String busiScName) {
		this.busiScName = busiScName;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAmountCurr() {
		return amountCurr;
	}

	public void setAmountCurr(String amountCurr) {
		this.amountCurr = amountCurr;
	} 
	
	
}
