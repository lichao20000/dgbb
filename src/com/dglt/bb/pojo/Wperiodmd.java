package com.dglt.bb.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dglt.comm.base.BaseVO;

@Entity
@Table(name="W_PERIOD_M_D")
public class Wperiodmd extends BaseVO {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@Column(name="ROW_WID") 
	private String rowWid; 
	
	@Column(name="QUARTER_ID") 
	private Long quarterId; 
	
	@Column(name="QUARTER_CODE") 
	private String quarterCode; 
	
	@Column(name="QUARTER_DSC") 
	private String quarterDsc; 
                        
	@Column(name="MONTH_ID") 
	private Long monthId;   
	
	@Column(name="MONTH_CODE") 
	private String monthCode; 
	
	@Column(name="MONTH_DSC") 
	private String monthDsc;  
	
	@Column(name="YEAR_ID") 
	private Long yearId;   
	
	@Column(name="YEAR_CODE") 
	private String yearCode;   
	
	@Column(name="YEAR_DSC") 
	private String yearDsc;      
	
	@Column(name="V_VALUE1") 
	private String vvalue1;   
	
	@Column(name="V_VALUE2") 
	private String vvalue2;   
	
	@Column(name="V_VALUE3") 
	private String vvalue3;   
	
	@Column(name="V_VALUE4") 
	private String vvalue4;   
	
	@Column(name="V_VALUE5") 
	private String vvalue5;   
	
	public String getRowWid() {
		return rowWid;
	}

	public void setRowWid(String rowWid) {
		this.rowWid = rowWid;
	}
	
	public Long getQuarterId() {
		return quarterId;
	}

	public void setQuarterId(Long quarterId) {
		this.quarterId = quarterId;
	}

	public String getQuarterCode() {
		return quarterCode;
	}

	public void setQuarterCode(String quarterCode) {
		this.quarterCode = quarterCode;
	}

	public String getQuarterDsc() {
		return quarterDsc;
	}

	public void setQuarterDsc(String quarterDsc) {
		this.quarterDsc = quarterDsc;
	}

	public Long getMonthId() {
		return monthId;
	}

	public void setMonthId(Long monthId) {
		this.monthId = monthId;
	}

	public String getMonthCode() {
		return monthCode;
	}

	public void setMonthCode(String monthCode) {
		this.monthCode = monthCode;
	}

	public String getMonthDsc() {
		return monthDsc;
	}

	public void setMonthDsc(String monthDsc) {
		this.monthDsc = monthDsc;
	}

	public Long getYearId() {
		return yearId;
	}

	public void setYearId(Long yearId) {
		this.yearId = yearId;
	}

	public String getYearCode() {
		return yearCode;
	}

	public void setYearCode(String yearCode) {
		this.yearCode = yearCode;
	}

	public String getYearDsc() {
		return yearDsc;
	}

	public void setYearDsc(String yearDsc) {
		this.yearDsc = yearDsc;
	}

	public String getVvalue1() {
		return vvalue1;
	}

	public void setVvalue1(String vvalue1) {
		this.vvalue1 = vvalue1;
	}

	public String getVvalue2() {
		return vvalue2;
	}

	public void setVvalue2(String vvalue2) {
		this.vvalue2 = vvalue2;
	}

	public String getVvalue3() {
		return vvalue3;
	}

	public void setVvalue3(String vvalue3) {
		this.vvalue3 = vvalue3;
	}

	public String getVvalue4() {
		return vvalue4;
	}

	public void setVvalue4(String vvalue4) {
		this.vvalue4 = vvalue4;
	}

	public String getVvalue5() {
		return vvalue5;
	}

	public void setVvalue5(String vvalue5) {
		this.vvalue5 = vvalue5;
	}

	
}
