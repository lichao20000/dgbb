package com.dglt.bb.pojo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dglt.comm.base.BaseVO;

//首页视图实体
@Entity
@Table(name="W_KPI_D")
public class WKpiD extends BaseVO {
	private static final long serialVersionUID = 2L;
	
	@Id 
	@Column(name="ROW_ID") 
	private Long rowId; 
	
	@Column(name="Kpi_Name") 
	private String kpiName; 
	
	@Column(name="Min_Value") 
	private String minValue; 
	
	@Column(name="Alert_Value") 
	private String alertValue; 
	
	@Column(name="Warning_Value") 
	private String warningValue; 
	
	@Column(name="Max_Value") 
	private String maxValue; 
	
	@Column(name="Is_Alert") 
	private String isAlert;









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

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getAlertValue() {
		return alertValue;
	}

	public void setAlertValue(String alertValue) {
		this.alertValue = alertValue;
	}

	public String getWarningValue() {
		return warningValue;
	}

	public void setWarningValue(String warningValue) {
		this.warningValue = warningValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getIsAlert() {
		return isAlert;
	}

	public void setIsAlert(String isAlert) {
		this.isAlert = isAlert;
	}
	
	
}
