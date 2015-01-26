package com.dglt.bb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dglt.base.util.ClassUtil;
import com.dglt.bb.pojo.DashTagging;
import com.dglt.bb.pojo.DashTaggingReply;
import com.dglt.bb.service.DashTaggingService;
import com.dglt.comm.base.BaseServiceImpl;

@Service(value = "DashTaggingService")
public class DashTaggingServiceImpl extends BaseServiceImpl implements
		DashTaggingService {

	// 根据指标ID、期间ID、专业ID、客户ID、产品ID、发表人查询批注
	@Override
	public List<DashTagging> getDashTaggingByCon(String kpiId, String monthId,
			String spId, String clientCode, String productCode, String userName) {
		StringBuffer jpql = new StringBuffer(
				"from DashTagging dt where dt.kpiId='").append(kpiId).append(
				"' and dt.monthId='").append(monthId).append("'");
		if (null != spId && !spId.equals("")) {
			jpql.append(" and dt.spcode='").append(spId).append("'");
		}
		if (null != clientCode && !clientCode.equals("")) {
			jpql.append(" and dt.clientCode='").append(clientCode).append("'");
		}
		if (null != productCode && !productCode.equals("")) {
			jpql.append(" and dt.productCode='").append(productCode).append("'");
		}
		if (null != userName && !userName.equals("")) {
			jpql.append(" and dt.userName='").append(userName).append("'");
		}
		return this.find(jpql.toString());
	}

	// 根据批注ID查询单个批注
	@Override
	public DashTagging getDashTaggingById(String taggingId) {
		StringBuffer jpql = new StringBuffer(
				"from DashTagging dt where dt.taggingId='").append(taggingId).append(
				"'");
		List l = this.find(jpql.toString());
		return (l==null)?null:(DashTagging)l.get(0);
	}

	// 根据批注ID查询批注回复
	@Override
	public List<DashTaggingReply> getDashTaggingReplyByTagId(String taggingId) {
		StringBuffer jpql = new StringBuffer(
		"from DashTaggingReply dtr where dtr.taggingId='").append(taggingId).append(
		"'");
		return this.find(jpql.toString());
	}

	// 保存批注
	@Override
	@Transactional
	public DashTagging insertDashTagging(DashTagging dashTagging) {
		dashTagging.setTaggingId(this.persist(dashTagging));
		return dashTagging;
	}

	// 保存批注回复
	@Override
	@Transactional
	public DashTaggingReply insertDashTaggingReply(
			DashTaggingReply dashTaggingReply) {
		dashTaggingReply.setTaggingReplyId(this.persist(dashTaggingReply));
		return dashTaggingReply;
	}

	@Override
	public String getShowStyle(String kpiId) {
		String sql = "select t.attribute_type,t.attribute_code from W_ATTRIBUTE_CONF_D t where t.kpi_name='KPI_DEMENSIONCONTROL' AND T.ROW_ID="+kpiId +" order by t.attribute_type";
		List l = this.findByNativeQuery(sql);
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<l.size();i++){
			sb.append(((Object [])l.get(i))[1]);
			sb.append(",");
		}
		return sb.toString();
	}

	
}
