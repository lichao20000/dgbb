package com.dglt.bb.dao;

import java.util.List;
import java.util.Map;

import com.dglt.comm.base.BaseDAO;

public interface DashBoardDao extends BaseDAO {

    /**��ȡ�Ǳ������񱨱�����
     * @param  map ��ֵ�ֱ�Ϊ{"month_id":�ڼ�'yyyymm��ʽ',"sp_id":'רҵID'
     *                        product_code ��Ʒ����,client_code �ͻ�����
     *                        kpi_id ָ��ID,qufen_id ���ֹ�˾ID
     *                        quyu_id Ӫ������ID}
     * @return ��������LIST 
     */
	public List getDashBoardGridData(Map<String,String> map);
	
	/**
	 * ��ҳ�̶���ָ����ʾ
	 * gen_dashboard_kpi_p
	 * @author Administrator
	 * @param  map{"month_id":�ڼ�'yyyymm��ʽ',"sp_id":'רҵID'
     *                        product_code ��Ʒ����,client_code �ͻ�����
     *                        kpi_id ָ��ID}
     * @return �̶���ָ��ֵ                   
	 * **/
	public double getDashBoardKpiP(Map<String,String> map);
	
    /**��ҳ�������Ա�ָ������ֹ�˾��ʾ  gen_dashboard_report_qufen_p
     * @param  map ��ֵ�ֱ�Ϊ{"month_id":�ڼ�'yyyymm��ʽ',"sp_id":'רҵID'
     *                        product_code ��Ʒ����,client_code �ͻ�����
     *                        kpi_id ָ��ID}
     * @return �������Ա�ָ��LIST 
     */
	public List getDashBoardReportQufenP(Map<String,String> map);
	
	
	 /**��ҳ�����Ա�ָ����ʾ gen_dashboard_report_silong_p
     * @param  map ��ֵ�ֱ�Ϊ{"month_id":�ڼ�'yyyymm��ʽ',"sp_id":'רҵID'
     *                        product_code ��Ʒ����,client_code �ͻ�����
     *                        kpi_id ָ��ID}
     * @return �����Ա�ָ��LIST 
     */
	public List getDashBoardReportSilongP(Map<String,String> map);
	
	
	 /**���ֹ�˾��ͼ������ʾ  gen_dashboard_rep_ditu_qufen_p
     * @param  map ��ֵ�ֱ�Ϊ{"month_id":�ڼ�'yyyymm��ʽ',"sp_id":'רҵID'
     *                        product_code ��Ʒ����,client_code �ͻ�����
     *                        kpi_id ָ��ID}
     * @return ���ֹ�˾��ͼ���� LIST 
     */
	public List getDashBoardRepDituQufenP(Map<String,String> map);
	
	
	/**���ֹ�˾��ͼ������ʾ gen_dashboard_rep_ditu_yf_p
     * @param  map ��ֵ�ֱ�Ϊ{"month_id":�ڼ�'yyyymm��ʽ',"sp_id":'רҵID'
     *                        product_code ��Ʒ����,client_code �ͻ�����
     *                        kpi_id ָ��ID
     *                        x_qufen_id  �ֹ�˾���
     *                        }
     * @return ���ֹ�˾��ͼ���� LIST 
     */
	public List getDashBoardRepDituYfP(Map<String,String> map);
	
	/**Ӫ������12��������ʾ gen_dashboard_zx_yf_p
     * @param  map ��ֵ�ֱ�Ϊ{"month_id":�ڼ�'yyyymm��ʽ',"sp_id":'רҵID'
     *                        product_code ��Ʒ����,client_code �ͻ�����
     *                        kpi_id ָ��ID
     *                        x_qufen_id  �ֹ�˾���
     *                        }
     * @return Ӫ������12��������ʾ LIST 
     */
	public List getDashBoardZXYFP(Map<String,String> map);
	
	/**��������12��������ʾ gen_dashboard_zx_qufen_p
     * @param  map ��ֵ�ֱ�Ϊ{"month_id":�ڼ�'yyyymm��ʽ',"sp_id":'רҵID'
     *                        product_code ��Ʒ����,client_code �ͻ�����
     *                        kpi_id ָ��ID
     *                        x_qufen_id  �ֹ�˾���
     *                        }
     * @return ��������12��������ʾ LIST 
     */
	public List getDashBoardZXQuFenP(Map<String,String> map);
	
	/**���۾�������12��������ʾ gen_dashboard_zx_qufen_p
     * @param  map ��ֵ�ֱ�Ϊ{"month_id":�ڼ�'yyyymm��ʽ',"sp_id":'רҵID'
     *                        product_code ��Ʒ����,client_code �ͻ�����
     *                        kpi_id ָ��ID
     *                        x_qufen_id  �ֹ�˾���
     *                        }
     * @return ��������12��������ʾ LIST 
     */
	public List getDashBoardZXxsjlP(Map<String,String> map);
	/**���Ʒ���6���»���������ʾ gen_dashboard_zx_trace_p
     * @param  map ��ֵ�ֱ�Ϊ{"month_id":�ڼ�'yyyymm��ʽ',"sp_id":'רҵID'
     *                        product_code ��Ʒ����,client_code �ͻ�����
     *                        kpi_id ָ��ID
     *                        x_qufen_id  �ֹ�˾���
     *                        }
     * @return ��������12��������ʾ LIST 
     */
	public List getDashBoardZXTraceP(Map<String,String> map);
	
	/**��ҳ��ͼռ�ȷ��� gen_dashboard_rep_ditu_qufen_p
     * @param  map ��ֵ�ֱ�Ϊ{"month_id":�ڼ�'yyyymm��ʽ',"sp_id":'רҵID'
     *                        product_code ��Ʒ����,client_code �ͻ�����
     *                        kpi_id ָ��ID
     *                        x_qufen_id  �ֹ�˾���
     *                        }
     * @return ��������12��������ʾ LIST 
     */
	public List getDashBoardPieDataP(Map<String,String> map);
	 /**��ȡӪ�����񱨱�����
     * @param  map ��ֵ�ֱ�Ϊ{"month_id":�ڼ�'yyyymm��ʽ',"sp_id":'רҵID'
     *                        product_code ��Ʒ����,client_code �ͻ�����
     *                        kpi_id ָ��ID,qufen_id ���ֹ�˾ID
     *                        quyu_id Ӫ������ID}
     * @return ��������LIST 
     */
	public List getDashBoardGridDataYf(Map<String,String> map);
	 /**��ȡ���۾������񱨱�����
     * @param  map ��ֵ�ֱ�Ϊ{"month_id":�ڼ�'yyyymm��ʽ',"sp_id":'רҵID'
     *                        product_code ��Ʒ����,client_code �ͻ�����
     *                        kpi_id ָ��ID,qufen_id ���ֹ�˾ID
     *                        quyu_id Ӫ������ID}
     * @return ��������LIST 
     */
	public List getDashBoardGridDataxsjl(Map<String,String> map);
	/**��ȡ���۾������񱨱�����
     * @param  map ��ֵ�ֱ�Ϊ{"month_id":�ڼ�'yyyymm��ʽ',"sp_id":'רҵID'
     *                        product_code ��Ʒ����,client_code �ͻ�����
     *                        kpi_id ָ��ID,qufen_id ���ֹ�˾ID
     *                        quyu_id Ӫ������ID}
     * @return ��������LIST 
     */
	public List getDashBoardRepDituxsjlP(Map<String,String> map);
	
	/**���Ͷ���
	 * @param tel���绰���룬content����������
	 * 
	 */
	public String sendSMS(String tel,String content);
}
