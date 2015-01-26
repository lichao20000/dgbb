package com.dglt.bb.pojo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dglt.comm.base.BaseVO;

//首页视图实体
@Entity
@Table(name="W_KPI_CONFIG_V")
public class WKipConfigV extends BaseVO {
	private static final long serialVersionUID = 2L;
	@Id 
	@Column(name="Kpi_Id") 
	private String kpiId; 
	
	@Column(name="Kpi_Name") 
	private String kpName; 
	
	@Column(name="Page_Type") 
	private String pageType; 
	
	@Column(name="Url") 
	private String url; 
	
	@Column(name="Min_Value") 
	private String minVale; 
	
	@Column(name="Warning_Value") 
	private String warningValue; 
	
	@Column(name="Alert_Value") 
	private String alertVale; 


	@Column(name="Max_Value") 
	private String maxValue; 
	
	@Column(name="Is_Alert") 
	private String isAlert;

	@Column(name="Attrib1") 
	private String attrib1;

	@Column(name="Attrib2") 
	private String attrib2;

	@Column(name="Attrib3") 
	private String attrib3;

	@Column(name="Attrib4") 
	private String attrib4;

	@Column(name="Attrib5") 
	private String attrib5;

	@Column(name="Attrib6") 
	private String attrib6;

	@Column(name="Attrib7") 
	private String attrib7;

	public String getKpiId() {
		return kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}

	public String getKpName() {
		return kpName;
	}
	

	public void setKpName(String kpName) {
		this.kpName = kpName;
	}
	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMinVale() {
		return minVale;
	}

	public void setMinVale(String minVale) {
		this.minVale = minVale;
	}

	public String getWarningValue() {
		return warningValue;
	}

	public void setWarningValue(String warningValue) {
		this.warningValue = warningValue;
	}

	public String getAlertVale() {
		return alertVale;
	}

	public void setAlertVale(String alertVale) {
		this.alertVale = alertVale;
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

	public String getAttrib6() {
		return attrib6;
	}

	public void setAttrib6(String attrib6) {
		this.attrib6 = attrib6;
	}

	public String getAttrib7() {
		return attrib7;
	}

	public void setAttrib7(String attrib7) {
		this.attrib7 = attrib7;
	}

	public String getAttrib8() {
		return attrib8;
	}

	public void setAttrib8(String attrib8) {
		this.attrib8 = attrib8;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Column(name="Attrib8") 
	private String attrib8;





	
	
}
