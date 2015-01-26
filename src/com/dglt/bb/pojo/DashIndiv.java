package com.dglt.bb.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dglt.comm.base.BaseVO;

@Entity
@Table(name = "W_DASH_INDIV")
public class DashIndiv extends BaseVO {
	private static final long serialVersionUID = 12L;

	@Id
	@Column(name = "INDIV_ID")
	private String indivId;
	@Column(name = "USER_ID")
	private String userId;
	@Column(name = "KPI_ID1")
	private String kpiId1;
	@Column(name = "KPI_ID2")
	private String kpiId2;
	@Column(name = "KPI_ID3")
	private String kpiId3;
	@Column(name = "KPI_ID4")
	private String kpiId4;
	@Column(name = "KPI_NAME1")
	private String kpiName1;
	@Column(name = "KPI_NAME2")
	private String kpiName2;
	@Column(name = "KPI_NAME3")
	private String kpiName3;
	@Column(name = "KPI_NAME4")
	private String kpiName4;
	@Column(name = "KPI1_CHART_TYPE")
	private String kpi1ChartType;
	@Column(name = "KPI2_CHART_TYPE")
	private String kpi2ChartType;
	@Column(name = "KPI3_CHART_TYPE")
	private String kpi3ChartType;
	@Column(name = "KPI4_CHART_TYPE")
	private String kpi4ChartType;
	@Column(name = "ATTRIB1")
	private String attrib1;
	@Column(name = "ATTRIB2")
	private String attrib2;
	@Column(name = "ATTRIB3")
	private String attrib3;
	@Column(name = "ATTRIB4")
	private String attrib4;
	@Column(name = "ATTRIB5")
	private String attrib5;
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "CREATION_DATE")
	private String creationDate;
	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;
	@Column(name = "LAST_UPDATE_DATE")
	private String lastUpdateDate;
	
	@Column(name = "MENU_CODE")
	private String menuCode;
	public String getIndivId() {
		return indivId;
	}
	public void setIndivId(String indivId) {
		this.indivId = indivId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getKpiId1() {
		return kpiId1;
	}
	public void setKpiId1(String kpiId1) {
		this.kpiId1 = kpiId1;
	}
	public String getKpiId2() {
		return kpiId2;
	}
	public void setKpiId2(String kpiId2) {
		this.kpiId2 = kpiId2;
	}
	public String getKpiId3() {
		return kpiId3;
	}
	public void setKpiId3(String kpiId3) {
		this.kpiId3 = kpiId3;
	}
	public String getKpiId4() {
		return kpiId4;
	}
	public void setKpiId4(String kpiId4) {
		this.kpiId4 = kpiId4;
	}
	public String getKpiName1() {
		return kpiName1;
	}
	public void setKpiName1(String kpiName1) {
		this.kpiName1 = kpiName1;
	}
	public String getKpiName2() {
		return kpiName2;
	}
	public void setKpiName2(String kpiName2) {
		this.kpiName2 = kpiName2;
	}
	public String getKpiName3() {
		return kpiName3;
	}
	public void setKpiName3(String kpiName3) {
		this.kpiName3 = kpiName3;
	}
	public String getKpiName4() {
		return kpiName4;
	}
	public void setKpiName4(String kpiName4) {
		this.kpiName4 = kpiName4;
	}
	public String getKpi1ChartType() {
		return kpi1ChartType;
	}
	public void setKpi1ChartType(String kpi1ChartType) {
		this.kpi1ChartType = kpi1ChartType;
	}
	public String getKpi2ChartType() {
		return kpi2ChartType;
	}
	public void setKpi2ChartType(String kpi2ChartType) {
		this.kpi2ChartType = kpi2ChartType;
	}
	public String getKpi3ChartType() {
		return kpi3ChartType;
	}
	public void setKpi3ChartType(String kpi3ChartType) {
		this.kpi3ChartType = kpi3ChartType;
	}
	public String getKpi4ChartType() {
		return kpi4ChartType;
	}
	public void setKpi4ChartType(String kpi4ChartType) {
		this.kpi4ChartType = kpi4ChartType;
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
	public String getAttrib4() {
		return attrib4;
	}
	public void setAttrib4(String attrib4) {
		this.attrib4 = attrib4;
	}
	public String getAttrib5() {
		return attrib5;
	}
	public void setAttrib5(String attrib5) {
		this.attrib5 = attrib5;
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
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

}
