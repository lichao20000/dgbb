package com.dglt.bb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dglt.base.util.ClassUtil;
import com.dglt.bb.dao.DashBoardDao;
import com.dglt.bb.service.DashBoardService;
import com.dglt.comm.base.BaseServiceImpl;

@Service(value = "dashBoardService")
public class DashBoardServiceImpl extends BaseServiceImpl implements
		DashBoardService {
	@Resource(name="dashBoardDao")
	private DashBoardDao dashBoardDao;
	
	
	
	/*
	 * ����kpidArrayString��ѯ�Ǳ���Ԥ��ֵ���ɲ�һ����������ѯ�����ָ��ֵ�ö��ŷָ�
	 * @see com.dglt.bb.service.DashBoardService#getIncomeBudgetCompletRate(java.lang.String)
	 */
	public List getIncomeBudgetCompletRate(int kpid,int monthId,String specialty,String client,String product){
		String sql = "select distinct a1.row_id,a1.min_value,a1.alert_value,a1.warning_value,a1.max_value,a1.is_alert,a1.is_percent from w_kpi_d a1 where a1.row_id="+kpid;
		List data = this.findByNativeQuery(sql);
		HashMap <String, String> map = new HashMap<String, String>();
		map.put("month_id", monthId+"");
		map.put("sp_id", specialty);
		map.put("product_code", product);
		map.put("client_code", client);
		map.put("kpi_id", kpid+"");
		
		double value = dashBoardDao.getDashBoardKpiP(map);
		Object [] o =null;
		if(data.size()>0){//���ݿ�û������ʱ����
			o =(Object [])data.get(0);
		}
		ArrayList<HashMap> al;
		String[] arry ={"rowId","minValue","alertValue","warningValue","maxValue","isAlert","isPercent"};
		//ǰ��������ȡ�±�����飬���и������District_Branch_NameΪ��ǰ�˲������⴦������д��city
		al = ClassUtil.getReturnList(data, arry);
		al.get(0).put("chartLabelValue", value);
		return al;
	}
	
	

	@Override
	public List getTop5Data(int kpid, int period, String specialty,
			String client, String product) {
		HashMap <String, String> map = new HashMap<String, String>();
		map.put("month_id", period+"");
		map.put("sp_id", specialty);
		map.put("product_code", product);
		map.put("client_code", client);
		map.put("kpi_id", kpid+"");
		List data=null;
			data = dashBoardDao.getDashBoardReportQufenP(map);
		ArrayList<HashMap> al;
		String[] arry ={"chartLabelName","chartLabelValue"};
		//al = ClassUtil.getReturnList(data, arry);
		//return al;
		return data;
	}



	/*
	 * ��ѯ��״ͼ������ͼ����ͼ������
	 * @see com.dglt.bb.service.DashBoardService#getOweRateArea(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List getOweRateArea(int kpid,int period, String specialty, String client,
			String product) {
		HashMap <String, String> map = new HashMap<String, String>();
		map.put("month_id", period+"");
		map.put("sp_id", specialty);
		map.put("product_code", product);
		map.put("client_code", client);
		map.put("kpi_id", kpid+"");
		List data=null;
		//��ѯ�Ƿ�����������
		data = dashBoardDao.getDashBoardReportSilongP(map);
		ArrayList<HashMap> al;
		String[] arry ={"chartLabelName","chartLabelValue"};
		//al = ClassUtil.getReturnList(data, arry);
		//return al;
		return data;
	}
	
	public List getLineData(int kpid,int period, String specialty, String client,
			String product) {
		HashMap <String, String> map = new HashMap<String, String>();
		map.put("month_id", period+"");
		map.put("sp_id", specialty);
		map.put("product_code", product);
		map.put("client_code", client);
		map.put("kpi_id", kpid+"");
		List data=null;
		//��ȡǰ6���µ���������
		data = dashBoardDao.getDashBoardZXTraceP(map);
		//ArrayList<HashMap> al;
		//String[] arry ={"chartLabelName","chartLabelValue"};
		//al = ClassUtil.getReturnList(data, arry);
		//return al;
		return data;
	}
	@Override
	public List getPieData(int kpid,int period, String specialty, String client,
			String product) {
		HashMap <String, String> map = new HashMap<String, String>();
		map.put("month_id", period+"");
		map.put("sp_id", specialty);
		map.put("product_code", product);
		map.put("client_code", client);
		map.put("kpi_id", kpid+"");
		map.put("qufen_id", "");
		List data=null;
		//���ݱ�ͼ�洢���̻�ȡ��ͼ����
		data = dashBoardDao.getDashBoardPieDataP(map);
		//ArrayList<HashMap> al;
		//String[] arry ={"chartLabelName","chartLabelValue"};
		//al = ClassUtil.getReturnList(data, arry);
		//return al;
		return data;
	}
	@Override
	public List getOweRateChart(int kpid,String period, String profess, String branch,
			String bizcs) {
		// TODO Auto-generated method stub
		List l = this.find("select t.AMOUNT_CURR from Wperiodmd t group by t.yearId");
		ArrayList<HashMap> al = new ArrayList<HashMap>();
		for (int i=0;i<l.size();i++){
			HashMap<String,String> h = new HashMap<String,String>();
			//ת����KEY VALUE����ʽ
			Object[]  v = (Object[])l.get(i);
			h.put("yearId", v[0]==null?"":v[0]+"");
			h.put("countNum", v[1]==null?"":v[1]+"");
			al.add(h);
		}
		return al;
	}


	/*
	 * ��ȡ�ͻ�������������
	 * @see com.dglt.bb.service.DashBoardService#getClientData()
	 */
	@Override
	public String getClientData() {
		List l = this.findByNativeQuery("SELECT A1.CLIENT_CODE,A1.CLIENT_NAME From W_CLIENT_D A1");
		//ƴ��ǰ����Ҫ�õ���������ʽ���ַ���
		StringBuffer s = new StringBuffer();
		s.append("[");
		for (int i=0;i<l.size();i++){
			Object[]  v = (Object[])l.get(i);
			if(i>0){
				s.append(",");
			}
				s.append("{'clientCode':'");
				s.append(v[0]==null?"":v[0]+"");
				s.append("','clientName':'");
				s.append(v[1]==null?"":v[1]+"");
				s.append("'}");
		}
		s.append("]");
		return s.toString();
	}

	/*
	 * ��ȡרҵ����������
	 * @see com.dglt.bb.service.DashBoardService#getClientData()
	 */
	@Override
	public String getSpecialtyData() {
		List l = this.findByNativeQuery("SeLECT A1.SP_CODE,A1.LOCAL_CODE From  W_SP_D A1");
		//ƴ��ǰ����Ҫ�õ���������ʽ���ַ���
		StringBuffer s = new StringBuffer();
		s.append("[");
		for (int i=0;i<l.size();i++){
			Object[]  v = (Object[])l.get(i);
			
			if("ȫרҵ".equals(v[1])){
			}else{
				if(i>0){
					s.append(",");
				}
				s.append("{'spCode':'");
				s.append(v[0]==null?"":v[0]+"");
				s.append("','localCode':'");
				s.append(v[1]==null?"":v[1]+"");
				s.append("'}");
			}
		}
		s.append("]");
		return s.toString();
	}

	/*
	 * ��ȡ��Ʒ����������
	 * @see com.dglt.bb.service.DashBoardService#getClientData()
	 */
	@Override
	public String getProductData(String specialty) {
		String sql="";
		if(specialty=="" || "99".equals(specialty)){
			sql ="SELECT A1.BRAND_CODE,A1.BRAND_NAME FROM W_SP_BRAND_M A1 WHERE A1.BRAND_NAME IS NOT NULL ORDER BY A1.BRAND_NAME DESC";
		}else{
			sql ="SELECT A1.BRAND_CODE,A1.BRAND_NAME FROM W_SP_BRAND_M A1 where A1.BRAND_NAME IS NOT NULL AND A1.SP_CODE='"+specialty+"' ORDER BY A1.BRAND_NAME DESC";
		}
		List l = this.findByNativeQuery(sql);
		//ƴ��ǰ����Ҫ�õ���������ʽ���ַ���
		StringBuffer s = new StringBuffer();
		s.append("[");
		for (int i=0;i<l.size();i++){
			Object[]  v = (Object[])l.get(i);
			if(i>0){
				s.append(",");
			}
			s.append("{'productCode':'");
			s.append(v[0]==null?"":v[0]+"");
			s.append("','productName':'");
			s.append(v[1]==null?"":v[1]+"");
			s.append("'}");
		}
		s.append("]");
		return s.toString();
	}


	@Override
	public List getKpiName(String kpiType, String kpiName,String area,String menuId) {
		
		String sql="select distinct A.KPI_ID,A.KPI_SHOW_NAME from w_kpi_info_v a where  a.belond_area ='"+area+"'";
		if(!"".equals(kpiType)){//ƴ��ָ�����
			sql+=" and A.KPI_TYPE_NAME ='"+kpiType+"'";
		}
		if(!"".equals(kpiName)){//���ָ������ģ����ѯ����
			sql+=" and A.KPI_SHOW_NAME like '%"+kpiName+"%'";
		}
		if(!"".equals(menuId)){//���ָ������ģ����ѯ����
			sql+=" and a.MENU_CODE="+menuId ;
		}
		List kpiNameList = this.findByNativeQuery(sql);
		ArrayList<HashMap> al;
		String[] arry ={"kpiId","kpiName"};
		al = ClassUtil.getReturnList(kpiNameList, arry);
		return al;
	}

	@Override
	public String getkpiType() {
		String sql ="select distinct A.KPI_TYPE_NAME from w_kpi_info_v a";
		List l = this.findByNativeQuery(sql);
		//ƴ��ǰ����Ҫ�õ���������ʽ���ַ���
		String s="[";
		for (int i=0;i<l.size();i++){
			if(i>0){
				s+=",";
			}
			s+="{'type':'";
			s+=l.get(i)==null?"":l.get(i)+"";
			s+="'}";
		}
		s+="]";
		return s;
	}

	@Override
	public String getchartType(String kpiId) {
		String sql ="select t.attribute_code from w_attribute_conf_d t where t.kpi_name ='KPI_SHOWSTYLE' and t.row_id="+kpiId+" order by t.attribute_type";
		List l = this.findByNativeQuery(sql);
		//ƴ��ǰ����Ҫ�õ���������ʽ���ַ���
		String s=(String) l.get(0)+","+(String) l.get(1)+","+(String) l.get(2)+","+(String) l.get(3);
		return s;
	}

	private String getSqlByKpid(int kpid,int period,String specialty,String client,String product){
		String table="";
		String hql2="";
		switch (kpid) {
		case 20003://����Ƿ����
			if(table==""){
				table="W_ARREARS_RATE_SHORT_V";
			}
		case 20004://����Ƿ����
			if(table==""){//�ǵ�һ��case����жϱ��Ƿ�Ϊ�յ��ж��Ƿ������case�����ѯ������ƴ��
				table="W_ARREARS_RATE_LONG_V";
			}
		case 20005://����Ƿ����
			if(table==""){//�ǵ�һ��case����жϱ��Ƿ�Ϊ�յ��ж��Ƿ������case�����ѯ������ƴ��
				table="W_ARREARS_RATE_ALL_V";
			}
			/*�˴�ƾ�費��Ҫ��Ʒ��Ϊ��ѯ������SQL��ǰ�����е�case��û��break��
			 * �պ��޸Ŀ���ֱ�ӰѲ���Ҫ��Ʒ��Ϊ��ѯ��������רҵ���ͻ���Ҫ��Ϊ����,����ֱ�����(case kpiId) 
			 */
			hql2+=" t where month_Id = '"+period+"'";
			if(specialty!="" && !"99".equals(specialty)){
				hql2+=" and sp_Code = '"+specialty+"'";
			}
			if(client!=""){
				hql2+=" and client_code = '"+client+"'";
			}
			
			break;
		case 20006:
			if(table==""){
				table="W_INCOME_BUDGET_V";
			}
		case 20007:
			if(table==""){//�ǵ�һ��case����жϱ��Ƿ�Ϊ�յ��ж��Ƿ������case�����ѯ������ƴ��
				table="W_PROFIT_BUDGET_V";
			}
		case 20008:
			if(table==""){//�ǵ�һ��case����жϱ��Ƿ�Ϊ�յ��ж��Ƿ������case�����ѯ������ƴ��
				table="W_2G_BIZ_LOSS_RATE_V";
			}
		case 20009:
			if(table==""){//�ǵ�һ��case����жϱ��Ƿ�Ϊ�յ��ж��Ƿ������case�����ѯ������ƴ��
				table="W_3G_SPEECH_USER_LOSS_RATE_V";
			}
			//�˴�ƾ�費��Ҫ�ͻ���Ϊ��ѯ������SQL
			hql2+=" t where month_Id = '"+period+"'";
			if(specialty!="" && !"99".equals(specialty)){
				hql2+=" and sp_Code = '"+specialty+"'";
			}
			if(product!=""){
				hql2+=" and Product_Code = '"+product+"'";
			}
			break;	
		default:
			return null;
	}
		return table+hql2;
	}
	@Override
	public List getChartDataBySql(int kpid,int monthId,String specialty,String client,String product){
		String hql2 = getSqlByKpid(kpid,monthId,specialty,client,product);
		if(hql2==null) return null;
		//��ѯ�Ǳ���ʱ�Ĳ��������ͷ�����������t.City is null or t.City='��ݸ'������ֱ�Ӳ�ѯ���������ݺ�û���������ݵ�
		String s1_1=" and a1.row_id = t.Kpi_Id and t.City='��ݸ'";
		String s1_2=" and a1.row_id = t.Kpi_Id and t.City is null group by a1.row_id ,a1.kpi_name,a1.min_value,a1.alert_value,a1.warning_value,a1.max_value,a1.is_alert";
		String sql1_2 = "select distinct a1.row_id,a1.min_value,a1.alert_value,a1.warning_value,a1.max_value,a1.is_alert,avg(t.amount_curr) from w_kpi_d a1,";
		String sql1 = "select distinct a1.row_id,a1.min_value,a1.alert_value,a1.warning_value,a1.max_value,a1.is_alert,t.amount_curr from w_kpi_d a1,";
		String sql = sql1+hql2+s1_1;
		System.out.println("�Ǳ����ж�ݸ����SQL��"+sql);
		List data = this.findByNativeQuery(sql);
		Object [] o =null;
		if(data.size()>0){//���ݿ�û������ʱ����
			o =(Object [])data.get(0);
		}
		ArrayList<HashMap> al;
		String[] arry ={"rowId","minValue","alertValue","warningValue","maxValue","isAlert","chartLabelValue"};
		if(o ==null || o[2]==""||o[2]==null){//�жϲ�ѯ�Ľ���г������Ƿ�Ϊ�գ�Ϊ�ռ�û����������
			sql = sql1_2+hql2+s1_2;
			System.out.println("�Ǳ���û�ж�ݸ����SQL:"+sql);
			List l = this.findByNativeQuery(sql);
			//ǰ��������ȡ�±�����飬���и������District_Branch_NameΪ��ǰ�˲������⴦������д��city
			al = ClassUtil.getReturnList(l, arry);
		}else{
			al = ClassUtil.getReturnList(data, arry);
		}
		return al;
	}
	@Override
	public List getOweRateBySql(int kpid,int period, String specialty, String client,
			String product) {
		//��ѯ�������ݲ�Ϊ�յ�����
		String hql0 = "select distinct t.city,t.amount_Curr from ";
		//�����������Ϊ��ʱ����ѯ�ֹ�˾����
		String hql1="select * from (select distinct t.District_Branch_Name,round(AVG(t.Amount_Curr),3) Amount_Curr from ";
		
		String hql2 = getSqlByKpid(kpid,period,specialty,client,product);//��ȡ��ѯ����SQL
		if(hql2==null) return null;
		String sql = hql0+hql2;
		sql+=" and t.City is not null order by t.amount_Curr  desc";
		System.out.println("��ѯ��������SQL:"+sql);
		List data = this.findByNativeQuery(sql);
		ArrayList<HashMap> al;
		Object [] o =null;
		if(data.size()>0){//���ݿ�û������ʱ����
			o =(Object [])data.get(0);
		}
		String[] arry ={"chartLabelName","chartLabelValue"};
		if(o ==null || o[0]==""||o[0]==null){//�жϲ�ѯ�Ľ���г������Ƿ�Ϊ�գ�Ϊ�ռ�û����������
			sql = hql1+hql2 ;
			sql+=" group by t.District_Branch_Name  ORDER  BY round(AVG(t.Amount_Curr),3) DESC) WHERE  Rownum < 6 ";
			System.out.println("��ѯ�ֹ�˾����SQL:"+sql);
			List l = this.findByNativeQuery(sql);
			//ǰ��������ȡ�±�����飬���и������District_Branch_NameΪ��ǰ�˲������⴦������д��city
			
			al = ClassUtil.getReturnList(l, arry);
		}else{
			al = ClassUtil.getReturnList(data, arry);
		}
		return al;
	}
	
	@Override
	public <T> List<T> findBy(Class<T> entityClass, String propertyName,
			Object value, Map<String, Boolean> orderBy) {
		// TODO Auto-generated method stub
		return null;
	}
	
/**	//������
	@Transactional
	public int executeUpdate(String sql){
		return super.executeUpdate(sql);
	}
**/
}
