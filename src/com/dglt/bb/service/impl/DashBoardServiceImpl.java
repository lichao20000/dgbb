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
	 * 根据kpidArrayString查询仪表盘预警值，可查一个或多个，查询多个是指标值用逗号分隔
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
		if(data.size()>0){//数据库没有数据时返回
			o =(Object [])data.get(0);
		}
		ArrayList<HashMap> al;
		String[] arry ={"rowId","minValue","alertValue","warningValue","maxValue","isAlert","isPercent"};
		//前端用来获取下标的数组，其中改数组的District_Branch_Name为了前端不做特殊处理这里写错city
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
	 * 查询柱状图、折线图、饼图等数据
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
		//查询是否有四龙数据
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
		//获取前6个月的折现数据
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
		//根据饼图存储过程获取饼图数据
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
			//转换成KEY VALUE的形式
			Object[]  v = (Object[])l.get(i);
			h.put("yearId", v[0]==null?"":v[0]+"");
			h.put("countNum", v[1]==null?"":v[1]+"");
			al.add(h);
		}
		return al;
	}


	/*
	 * 获取客户端下拉框数据
	 * @see com.dglt.bb.service.DashBoardService#getClientData()
	 */
	@Override
	public String getClientData() {
		List l = this.findByNativeQuery("SELECT A1.CLIENT_CODE,A1.CLIENT_NAME From W_CLIENT_D A1");
		//拼接前端需要用到的数组形式的字符串
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
	 * 获取专业下拉框数据
	 * @see com.dglt.bb.service.DashBoardService#getClientData()
	 */
	@Override
	public String getSpecialtyData() {
		List l = this.findByNativeQuery("SeLECT A1.SP_CODE,A1.LOCAL_CODE From  W_SP_D A1");
		//拼接前端需要用到的数组形式的字符串
		StringBuffer s = new StringBuffer();
		s.append("[");
		for (int i=0;i<l.size();i++){
			Object[]  v = (Object[])l.get(i);
			
			if("全专业".equals(v[1])){
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
	 * 获取产品下拉框数据
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
		//拼接前端需要用到的数组形式的字符串
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
		if(!"".equals(kpiType)){//拼接指标大类
			sql+=" and A.KPI_TYPE_NAME ='"+kpiType+"'";
		}
		if(!"".equals(kpiName)){//添加指标名称模糊查询条件
			sql+=" and A.KPI_SHOW_NAME like '%"+kpiName+"%'";
		}
		if(!"".equals(menuId)){//添加指标名称模糊查询条件
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
		//拼接前端需要用到的数组形式的字符串
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
		//拼接前端需要用到的数组形式的字符串
		String s=(String) l.get(0)+","+(String) l.get(1)+","+(String) l.get(2)+","+(String) l.get(3);
		return s;
	}

	private String getSqlByKpid(int kpid,int period,String specialty,String client,String product){
		String table="";
		String hql2="";
		switch (kpid) {
		case 20003://短期欠费率
			if(table==""){
				table="W_ARREARS_RATE_SHORT_V";
			}
		case 20004://长期欠费率
			if(table==""){//非第一个case添加判断表是否为空的判断是方便后面case里面查询条件的拼接
				table="W_ARREARS_RATE_LONG_V";
			}
		case 20005://总体欠费率
			if(table==""){//非第一个case添加判断表是否为空的判断是方便后面case里面查询条件的拼接
				table="W_ARREARS_RATE_ALL_V";
			}
			/*此处凭借不需要产品作为查询条件的SQL，前面所有的case均没有break，
			 * 日后修改可以直接把不需要产品作为查询条件，而专业、客户需要作为条件,可以直接添加(case kpiId) 
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
			if(table==""){//非第一个case添加判断表是否为空的判断是方便后面case里面查询条件的拼接
				table="W_PROFIT_BUDGET_V";
			}
		case 20008:
			if(table==""){//非第一个case添加判断表是否为空的判断是方便后面case里面查询条件的拼接
				table="W_2G_BIZ_LOSS_RATE_V";
			}
		case 20009:
			if(table==""){//非第一个case添加判断表是否为空的判断是方便后面case里面查询条件的拼接
				table="W_3G_SPEECH_USER_LOSS_RATE_V";
			}
			//此处凭借不需要客户作为查询条件的SQL
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
		//查询仪表盘时的部分条件和分组条件，（t.City is null or t.City='东莞'）可以直接查询有四龙数据和没有四龙数据的
		String s1_1=" and a1.row_id = t.Kpi_Id and t.City='东莞'";
		String s1_2=" and a1.row_id = t.Kpi_Id and t.City is null group by a1.row_id ,a1.kpi_name,a1.min_value,a1.alert_value,a1.warning_value,a1.max_value,a1.is_alert";
		String sql1_2 = "select distinct a1.row_id,a1.min_value,a1.alert_value,a1.warning_value,a1.max_value,a1.is_alert,avg(t.amount_curr) from w_kpi_d a1,";
		String sql1 = "select distinct a1.row_id,a1.min_value,a1.alert_value,a1.warning_value,a1.max_value,a1.is_alert,t.amount_curr from w_kpi_d a1,";
		String sql = sql1+hql2+s1_1;
		System.out.println("仪表盘有东莞数据SQL："+sql);
		List data = this.findByNativeQuery(sql);
		Object [] o =null;
		if(data.size()>0){//数据库没有数据时返回
			o =(Object [])data.get(0);
		}
		ArrayList<HashMap> al;
		String[] arry ={"rowId","minValue","alertValue","warningValue","maxValue","isAlert","chartLabelValue"};
		if(o ==null || o[2]==""||o[2]==null){//判断查询的结果中城市列是否为空，为空即没有四龙数据
			sql = sql1_2+hql2+s1_2;
			System.out.println("仪表盘没有东莞数据SQL:"+sql);
			List l = this.findByNativeQuery(sql);
			//前端用来获取下标的数组，其中改数组的District_Branch_Name为了前端不做特殊处理这里写错city
			al = ClassUtil.getReturnList(l, arry);
		}else{
			al = ClassUtil.getReturnList(data, arry);
		}
		return al;
	}
	@Override
	public List getOweRateBySql(int kpid,int period, String specialty, String client,
			String product) {
		//查询四龙数据不为空的数据
		String hql0 = "select distinct t.city,t.amount_Curr from ";
		//如果四龙数据为空时，查询分公司数据
		String hql1="select * from (select distinct t.District_Branch_Name,round(AVG(t.Amount_Curr),3) Amount_Curr from ";
		
		String hql2 = getSqlByKpid(kpid,period,specialty,client,product);//获取查询条件SQL
		if(hql2==null) return null;
		String sql = hql0+hql2;
		sql+=" and t.City is not null order by t.amount_Curr  desc";
		System.out.println("查询四龙数据SQL:"+sql);
		List data = this.findByNativeQuery(sql);
		ArrayList<HashMap> al;
		Object [] o =null;
		if(data.size()>0){//数据库没有数据时返回
			o =(Object [])data.get(0);
		}
		String[] arry ={"chartLabelName","chartLabelValue"};
		if(o ==null || o[0]==""||o[0]==null){//判断查询的结果中城市列是否为空，为空即没有四龙数据
			sql = hql1+hql2 ;
			sql+=" group by t.District_Branch_Name  ORDER  BY round(AVG(t.Amount_Curr),3) DESC) WHERE  Rownum < 6 ";
			System.out.println("查询分公司数据SQL:"+sql);
			List l = this.findByNativeQuery(sql);
			//前端用来获取下标的数组，其中改数组的District_Branch_Name为了前端不做特殊处理这里写错city
			
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
	
/**	//加事务
	@Transactional
	public int executeUpdate(String sql){
		return super.executeUpdate(sql);
	}
**/
}
