package com.dglt.bb.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dglt.base.util.ClassUtil;
import com.dglt.base.util.ProperUtil;
import com.dglt.bb.dao.DashBoardDao;
import com.dglt.bb.pojo.DashIndiv;
import com.dglt.bb.pojo.DashWarnDetail;
import com.dglt.bb.service.DashIndivService;
import com.dglt.comm.base.BaseServiceImpl;
import com.dglt.comm.util.DateUtils;

@Service(value = "dashIndivService")
public class DashIndivServiceImpl extends BaseServiceImpl implements
DashIndivService {
	@Resource(name="dashBoardDao")
	private DashBoardDao dashBoardDao;
	/**
	 * �����û�ID��ѯ�Ǳ��̸��Ի�����
	 */
	public DashIndiv getDashIndivByUserId(String userId){
		List result=this.findBy(DashIndiv.class, "userId", userId);
		if(result==null || result.size()==0)
			return null;
		else
			return (DashIndiv)result.get(0);
	}
	
	
	@Override
	public DashIndiv getDashIndivByUserIdAndMenuId(String userId, String menuId) {
		String sql = "select t.indiv_id from W_DASH_INDIV t where t.user_id='"+userId+"'  and t.menu_code="+menuId;
		List result=this.findByNativeQuery(sql);
		if(result==null || result.size()==0)
			return null;
		else{
			String indivId = result.get(0).toString();
			List result2=this.findBy(DashIndiv.class, "indivId", indivId);
			return (DashIndiv)result2.get(0);
		}
	}


	/**
	 * �����û�ID��ʼ�����Ի���
	 * @param userId
	 * @return ���ظ��Ի���ID
	 */
	@Transactional
	public DashIndiv insertDashIndiv(String userId,String menuId){
		DashIndiv indiv = new DashIndiv();
		//String sql = "select * from W_KPI_CUS_V where userId='"+userId+"' and menu_code='"+menuId+"' order by BELOND_AREA";
		String sql = "select kpi_id,KPI_NAME,attribute_type from W_KPI_CUS_V where  menu_code='"+menuId+"' order by BELOND_AREA";
		List list = this.findByNativeQuery(sql);
		String [] arry ={"kpi_id","KPI_NAME","attribute_type"};
		List l = ClassUtil.getReturnList(list, arry);
		indiv.setUserId(userId);
		indiv.setKpiId1((String)((HashMap)l.get(0)).get(arry[0]));
		indiv.setKpiId2((String)((HashMap)l.get(1)).get(arry[0]));
		indiv.setKpiId3((String)((HashMap)l.get(2)).get(arry[0]));
		indiv.setKpiId4((String)((HashMap)l.get(3)).get(arry[0]));
		indiv.setMenuCode(menuId);
		indiv.setKpiName1((String)((HashMap)l.get(0)).get(arry[1]));
		indiv.setKpiName2((String)((HashMap)l.get(1)).get(arry[1]));
		indiv.setKpiName3((String)((HashMap)l.get(2)).get(arry[1]));
		indiv.setKpiName4((String)((HashMap)l.get(3)).get(arry[1]));
		indiv.setKpi1ChartType((String)((HashMap)l.get(0)).get(arry[2]));
		indiv.setKpi2ChartType((String)((HashMap)l.get(1)).get(arry[2]));
		indiv.setKpi3ChartType((String)((HashMap)l.get(2)).get(arry[2]));
		indiv.setKpi4ChartType((String)((HashMap)l.get(3)).get(arry[2]));
		//indiv.setCreatedBy(userId);
		indiv.setCreationDate(DateUtils.getCCYYMMDDhhmmss());
		indiv.setIndivId(this.persist(indiv));
		return indiv;
	}
	
	/**
	 * ���¸��Ի������ݣ�һ����ǰ̨����userId��ѯ��¼���ٸ�����Ӧֵ���и���
	 */
	@Transactional
	public DashIndiv updateDashIndiv(DashIndiv indiv){
		return this.merge(indiv);
	}
	
	/**
	 * Ԥ����Ϣ������־��¼
	 */
	@Transactional
	public DashWarnDetail insertDashWarnDetail(DashWarnDetail wdetail){
		wdetail.setWarnDetailId(this.persist(wdetail));
		return wdetail;
	}
	/**
	 * ����kpiIDȡ�����ʼ���
	 */
	public String getReciever(String kpiId){
		List result=this.findByNativeQuery("SELECT d.Attribute3 FROM   w_Kpi_d d WHERE  d.Is_Dashboard_Kpi = 'Y' AND  d.Row_Id = '"+kpiId+"'");
		if(result==null || result.size()==0)
			return null;
		else
			return (String)result.get(0);
	}
	/**
	 * ����kpiIDȡ������ϵ��
	 */
	public String getSMSReciever(String kpiId){
		List result=this.findByNativeQuery("SELECT d.Attribute6 FROM   w_Kpi_d d WHERE  d.Is_Dashboard_Kpi = 'Y' AND  d.Row_Id = '"+kpiId+"'");
		if(result==null || result.size()==0)
			return null;
		else
			return (String)result.get(0);
	}

	@Override
	public String sendSMS(String tel,String kpi_name,
			String month_desc,String product_name,String client_name,String sp_name,String amount_curr,String warn_value) {
		String templateContent = ProperUtil.getEmailContentMap().get("dashWarnContent.txt");
		templateContent = templateContent.replaceAll("<!--kpi_name-->", kpi_name);
		templateContent = templateContent.replaceAll("<!--month_desc-->", month_desc);
		templateContent = templateContent.replaceAll("<!--product_name-->", product_name);
		templateContent = templateContent.replaceAll("<!--client_name-->", client_name);
		templateContent = templateContent.replaceAll("<!--sp_name-->", sp_name);
		templateContent = templateContent.replaceAll("<!--amount_curr-->", amount_curr);
		templateContent = templateContent.replaceAll("<!--warn_value-->", warn_value);
		return dashBoardDao.sendSMS(tel, templateContent);
	}
	
}
