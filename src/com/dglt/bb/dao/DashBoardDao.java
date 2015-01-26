package com.dglt.bb.dao;

import java.util.List;
import java.util.Map;

import com.dglt.comm.base.BaseDAO;

public interface DashBoardDao extends BaseDAO {

    /**获取仪表盘网格报表数据
     * @param  map 里值分别为{"month_id":期间'yyyymm格式',"sp_id":'专业ID'
     *                        product_code 产品编码,client_code 客户编码
     *                        kpi_id 指标ID,qufen_id 区分公司ID
     *                        quyu_id 营服中心ID}
     * @return 网络数据LIST 
     */
	public List getDashBoardGridData(Map<String,String> map);
	
	/**
	 * 首页刻度盘指标显示
	 * gen_dashboard_kpi_p
	 * @author Administrator
	 * @param  map{"month_id":期间'yyyymm格式',"sp_id":'专业ID'
     *                        product_code 产品编码,client_code 客户编码
     *                        kpi_id 指标ID}
     * @return 刻度盘指标值                   
	 * **/
	public double getDashBoardKpiP(Map<String,String> map);
	
    /**首页非四龙对比指标区域分公司显示  gen_dashboard_report_qufen_p
     * @param  map 里值分别为{"month_id":期间'yyyymm格式',"sp_id":'专业ID'
     *                        product_code 产品编码,client_code 客户编码
     *                        kpi_id 指标ID}
     * @return 非四龙对比指标LIST 
     */
	public List getDashBoardReportQufenP(Map<String,String> map);
	
	
	 /**首页四龙对比指标显示 gen_dashboard_report_silong_p
     * @param  map 里值分别为{"month_id":期间'yyyymm格式',"sp_id":'专业ID'
     *                        product_code 产品编码,client_code 客户编码
     *                        kpi_id 指标ID}
     * @return 四龙对比指标LIST 
     */
	public List getDashBoardReportSilongP(Map<String,String> map);
	
	
	 /**区分公司地图数据显示  gen_dashboard_rep_ditu_qufen_p
     * @param  map 里值分别为{"month_id":期间'yyyymm格式',"sp_id":'专业ID'
     *                        product_code 产品编码,client_code 客户编码
     *                        kpi_id 指标ID}
     * @return 区分公司地图数据 LIST 
     */
	public List getDashBoardRepDituQufenP(Map<String,String> map);
	
	
	/**区分公司地图数据显示 gen_dashboard_rep_ditu_yf_p
     * @param  map 里值分别为{"month_id":期间'yyyymm格式',"sp_id":'专业ID'
     *                        product_code 产品编码,client_code 客户编码
     *                        kpi_id 指标ID
     *                        x_qufen_id  分公司编号
     *                        }
     * @return 区分公司地图数据 LIST 
     */
	public List getDashBoardRepDituYfP(Map<String,String> map);
	
	/**营服线性12月数据显示 gen_dashboard_zx_yf_p
     * @param  map 里值分别为{"month_id":期间'yyyymm格式',"sp_id":'专业ID'
     *                        product_code 产品编码,client_code 客户编码
     *                        kpi_id 指标ID
     *                        x_qufen_id  分公司编号
     *                        }
     * @return 营服线性12月数据显示 LIST 
     */
	public List getDashBoardZXYFP(Map<String,String> map);
	
	/**区分线性12月数据显示 gen_dashboard_zx_qufen_p
     * @param  map 里值分别为{"month_id":期间'yyyymm格式',"sp_id":'专业ID'
     *                        product_code 产品编码,client_code 客户编码
     *                        kpi_id 指标ID
     *                        x_qufen_id  分公司编号
     *                        }
     * @return 区分线性12月数据显示 LIST 
     */
	public List getDashBoardZXQuFenP(Map<String,String> map);
	
	/**销售经理线性12月数据显示 gen_dashboard_zx_qufen_p
     * @param  map 里值分别为{"month_id":期间'yyyymm格式',"sp_id":'专业ID'
     *                        product_code 产品编码,client_code 客户编码
     *                        kpi_id 指标ID
     *                        x_qufen_id  分公司编号
     *                        }
     * @return 区分线性12月数据显示 LIST 
     */
	public List getDashBoardZXxsjlP(Map<String,String> map);
	/**趋势分析6个月回溯数据显示 gen_dashboard_zx_trace_p
     * @param  map 里值分别为{"month_id":期间'yyyymm格式',"sp_id":'专业ID'
     *                        product_code 产品编码,client_code 客户编码
     *                        kpi_id 指标ID
     *                        x_qufen_id  分公司编号
     *                        }
     * @return 区分线性12月数据显示 LIST 
     */
	public List getDashBoardZXTraceP(Map<String,String> map);
	
	/**首页饼图占比分析 gen_dashboard_rep_ditu_qufen_p
     * @param  map 里值分别为{"month_id":期间'yyyymm格式',"sp_id":'专业ID'
     *                        product_code 产品编码,client_code 客户编码
     *                        kpi_id 指标ID
     *                        x_qufen_id  分公司编号
     *                        }
     * @return 区分线性12月数据显示 LIST 
     */
	public List getDashBoardPieDataP(Map<String,String> map);
	 /**获取营服网格报表数据
     * @param  map 里值分别为{"month_id":期间'yyyymm格式',"sp_id":'专业ID'
     *                        product_code 产品编码,client_code 客户编码
     *                        kpi_id 指标ID,qufen_id 区分公司ID
     *                        quyu_id 营服中心ID}
     * @return 网络数据LIST 
     */
	public List getDashBoardGridDataYf(Map<String,String> map);
	 /**获取销售经理网格报表数据
     * @param  map 里值分别为{"month_id":期间'yyyymm格式',"sp_id":'专业ID'
     *                        product_code 产品编码,client_code 客户编码
     *                        kpi_id 指标ID,qufen_id 区分公司ID
     *                        quyu_id 营服中心ID}
     * @return 网络数据LIST 
     */
	public List getDashBoardGridDataxsjl(Map<String,String> map);
	/**获取销售经理网格报表数据
     * @param  map 里值分别为{"month_id":期间'yyyymm格式',"sp_id":'专业ID'
     *                        product_code 产品编码,client_code 客户编码
     *                        kpi_id 指标ID,qufen_id 区分公司ID
     *                        quyu_id 营服中心ID}
     * @return 网络数据LIST 
     */
	public List getDashBoardRepDituxsjlP(Map<String,String> map);
	
	/**发送短信
	 * @param tel：电话号码，content：短信内容
	 * 
	 */
	public String sendSMS(String tel,String content);
}
