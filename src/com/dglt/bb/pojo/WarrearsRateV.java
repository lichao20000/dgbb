package com.dglt.bb.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dglt.comm.base.BaseVO;

//欠费率视图实体
@Entity
@Table(name="W_Arrears_Rate_v")
public class WarrearsRateV extends BaseVO {

private static final long serialVersionUID = 1L;
	
	@Id 
	@Column(name="ROW_WID") 
	private String rowWid; 

	@Column(name="Kpi_Id") 
	private String kpiId; 
	
	@Column(name="Kpi_Name") 
	private String kpiName; 
	
	@Column(name="Month_Id") 
	private String monthId; 
	
	@Column(name="Month_Dsc") 
	private String monthDsc; 
	
	@Column(name="Sp_Code") 
	private String spcode; 
	
	@Column(name="Sp_Name") 
	private String spname; 
	
	@Column(name="District_Branch_Code") 
	private String districtBranchCode; 
	
	@Column(name="District_Branch_Name") 
	private String districtBranchName; 
	
	@Column(name="Busi_Sc_Code") 
	private String busiScCode; 
	
	@Column(name="Busi_Sc_Name") 
	private String busiScName; 
	
	@Column(name="Amount_Curr") 
	private String amountCurr; 
	

	public String getRowWid() {
		return rowWid;
	}

	public void setRowWid(String rowWid) {
		this.rowWid = rowWid;
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

	public String getAmountCurr() {
		return amountCurr;
	}

	public void setAmountCurr(String amountCurr) {
		this.amountCurr = amountCurr;
	}
}
