package com.dglt.bb.service;

import java.util.ArrayList;
import java.util.List;

import com.dglt.bb.pojo.DashTagging;
import com.dglt.bb.pojo.DashTaggingReply;

/**
 * �Ǳ�����ҳ����ע����
 * @author Administrator
 *
 */
public interface DashTaggingService {

	//����ָ��ID���ڼ�ID��רҵID���ͻ�ID����ƷID�������˲�ѯ��ע
	public List<DashTagging> getDashTaggingByCon(String kpiId,String monthId,String spId,
			String clientCode,String productCode,String userName);
	
	//������עID��ѯ������ע 
	public DashTagging getDashTaggingById(String taggingId);
	
	//������עID��ѯ��ע�ظ�
	public List<DashTaggingReply> getDashTaggingReplyByTagId(String taggingId);
	
	//������ע
	public DashTagging insertDashTagging(DashTagging dashTagging);
	
	//������ע�ظ�
	public DashTaggingReply insertDashTaggingReply(DashTaggingReply dashTaggingReply);
	//�������ݿ�涨�����������Ƿ����
	public String getShowStyle(String kpiId);
}
