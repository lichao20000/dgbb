package com.dglt.bb.pojo;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


/**
 * The persistent class for the T_APP_STATION database table.
 * 
 */
@Entity
@Table(name="T_APP_STATION")
@NamedQuery(name="TAppStation.findAll", query="SELECT t FROM TAppStation t")
public class TAppStation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PK_ID")
	@GeneratedValue(generator = "system-uuid")  
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String pkId;

	@Temporal(TemporalType.DATE)
	@Column(name="LOCAL_AGENT_DATE")
	private Date localAgentDate;

	@Column(name="LOCAL_AGENT_ID")
	private String localAgentId;

	@Column(name="LOCAL_AGENT_NAME")
	private String localAgentName;

	@Column(name="LOCAL_MANAGER_ID")
	private String localManagerId;

	@Column(name="LOCAL_MANAGER_NAME")
	private String localManagerName;

	@Column(name="LOCAL_QUYU_ID")
	private String localQuyuId;

	@Column(name="LOCAL_QUYU_NAME")
	private String localQuyuName;

	@Column(name="LOCAL_TYPE_ID")
	private String localTypeId;

	@Column(name="LOCAL_TYPE_NAME")
	private String localTypeName;

	@Column(name="MAP_STATION_CODE")
	private String mapStationCode;

	@Column(name="MAP_STATION_NAME")
	private String mapStationName;

	@Column(name="STATION_ADDR")
	private String stationAddr;

	@Column(name="STATION_CODE")
	private String stationCode;

	@Column(name="STATION_NAME")
	private String stationName;

	@Column(name="ZHONGXIAO_FLAG")
	private String zhongxiaoFlag;

	public TAppStation() {
	}

	public String getPkId() {
		return this.pkId;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	public Date getLocalAgentDate() {
		return this.localAgentDate;
	}

	public void setLocalAgentDate(Date localAgentDate) {
		this.localAgentDate = localAgentDate;
	}

	public String getLocalAgentId() {
		return this.localAgentId;
	}

	public void setLocalAgentId(String localAgentId) {
		this.localAgentId = localAgentId;
	}

	public String getLocalAgentName() {
		return this.localAgentName;
	}

	public void setLocalAgentName(String localAgentName) {
		this.localAgentName = localAgentName;
	}

	public String getLocalManagerId() {
		return this.localManagerId;
	}

	public void setLocalManagerId(String localManagerId) {
		this.localManagerId = localManagerId;
	}

	public String getLocalManagerName() {
		return this.localManagerName;
	}

	public void setLocalManagerName(String localManagerName) {
		this.localManagerName = localManagerName;
	}

	public String getLocalQuyuId() {
		return this.localQuyuId;
	}

	public void setLocalQuyuId(String localQuyuId) {
		this.localQuyuId = localQuyuId;
	}

	public String getLocalQuyuName() {
		return this.localQuyuName;
	}

	public void setLocalQuyuName(String localQuyuName) {
		this.localQuyuName = localQuyuName;
	}

	public String getLocalTypeId() {
		return this.localTypeId;
	}

	public void setLocalTypeId(String localTypeId) {
		this.localTypeId = localTypeId;
	}

	public String getLocalTypeName() {
		return this.localTypeName;
	}

	public void setLocalTypeName(String localTypeName) {
		this.localTypeName = localTypeName;
	}

	public String getMapStationCode() {
		return this.mapStationCode;
	}

	public void setMapStationCode(String mapStationCode) {
		this.mapStationCode = mapStationCode;
	}

	public String getMapStationName() {
		return this.mapStationName;
	}

	public void setMapStationName(String mapStationName) {
		this.mapStationName = mapStationName;
	}

	public String getStationAddr() {
		return this.stationAddr;
	}

	public void setStationAddr(String stationAddr) {
		this.stationAddr = stationAddr;
	}

	public String getStationCode() {
		return this.stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getStationName() {
		return this.stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getZhongxiaoFlag() {
		return this.zhongxiaoFlag;
	}

	public void setZhongxiaoFlag(String zhongxiaoFlag) {
		this.zhongxiaoFlag = zhongxiaoFlag;
	}

}