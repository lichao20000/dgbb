package com.dglt.bb.service;

import java.util.ArrayList;
import java.util.List;

import com.dglt.bb.pojo.DashTagging;
import com.dglt.bb.pojo.DashTaggingReply;

/**
 * 仪表盘首页的批注功能
 * @author Administrator
 *
 */
public interface DashTaggingService {

	//根据指标ID、期间ID、专业ID、客户ID、产品ID、发表人查询批注
	public List<DashTagging> getDashTaggingByCon(String kpiId,String monthId,String spId,
			String clientCode,String productCode,String userName);
	
	//根据批注ID查询单个批注 
	public DashTagging getDashTaggingById(String taggingId);
	
	//根据批注ID查询批注回复
	public List<DashTaggingReply> getDashTaggingReplyByTagId(String taggingId);
	
	//保存批注
	public DashTagging insertDashTagging(DashTagging dashTagging);
	
	//保存批注回复
	public DashTaggingReply insertDashTaggingReply(DashTaggingReply dashTaggingReply);
	//根据数据库规定下钻下拉框是否禁用
	public String getShowStyle(String kpiId);
}
