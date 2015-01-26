package com.dglt.bb.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dglt.comm.base.BaseVO;

@Entity
@Table(name="TcsAudience")
public class Test extends BaseVO
{
	private static final long serialVersionUID = 1L;
	
	@Id 
	@Column(name="audienceNo") 
	private String audienceNo; 
	
	@Column(name="audienceCode") 
	private String audienceCode; 
	
	@Column(name="audienceDesc") 
	private String audienceDesc; 
	
	public String getAudienceNo()
	{
		return audienceNo;
	}
	public void setAudienceNo(String audienceNo)
	{
		this.audienceNo = audienceNo;
	}
	public String getAudienceCode()
	{
		return audienceCode;
	}
	public void setAudienceCode(String audienceCode)
	{
		this.audienceCode = audienceCode;
	}
	public String getAudienceDesc()
	{
		return audienceDesc;
	}
	public void setAudienceDesc(String audienceDesc)
	{
		this.audienceDesc = audienceDesc;
	}
}
