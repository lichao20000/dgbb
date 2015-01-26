package com.dglt.statement.vo;

public class FormName {
	// 报表ID 90002 009-1.2综合绩效报表-收入-收入明细 对应ComprehensivePerformance.jsp页面
	public final static String sql1 = "select t.period,ws.local_code,wkd.show_name,t.local_current,t.vary_betw_curr_and_prev,t.growth_rate,t.current_year_total,wkd.is_color from t_009_2_1_zhjxsr_srmx_f t,w_kpi_d wkd,w_sp_d ws where t.kpi_name = wkd.row_id and t.local_tele_type_name = ws.sp_code";
	// 报表ID 90003 009-1.3综合绩效报表-收入-网内网间结算 对应SettlementStatement.jsp页面
	public final static String sql2 = "select t.PERIOD,t.CATAGORY,t.LOCAL_TELE_TYPE,t.KPI_NAME,t.CURRENT_NUM,t.MONTH_ON_MONTH_LIMIT,t.TOTAL_AMOUNT,t.SCHEDUAL_AVERAGE,wkmd.ATTRIBUTE1 from T_009_3_1_ZHJXSR_JS_F t,W_KPI_MAPPING_D wkmd WHERE t.ATTRIBUTE1 = wkmd.REPORT_ID||wkmd.ATTRIBUTE2 ORDER BY t.PERIOD,wkmd.REPORT_ID||wkmd.ATTRIBUTE2";
	// 报表ID 90004 009-1.4综合绩效报表-收入-集客收入 对应Sgincome.jsp页面
	public final static String sql3 = "select to_number(to_char(t.period,'MM'))||'月',sum(decode(t.kpi_name,'60001',t.current_num)) kpi60001,sum(decode(t.kpi_name,'60002',t.current_num)) kpi60002,sum(decode(t.kpi_name,'60003',t.current_num)) kpi60003,sum(decode(t.kpi_name,'60004',t.current_num)) kpi60004,sum(decode(t.kpi_name,'60005',t.current_num)) kpi60005,sum(decode(t.kpi_name,'60006',t.current_num)) kpi60006,sum(decode(t.kpi_name,'60007',t.current_num)) kpi60007,sum(decode(t.kpi_name,'60008',t.current_num)) kpi60008,sum(decode(t.kpi_name,'60009',t.current_num)) kpi60009,sum(decode(t.kpi_name,'60010',t.current_num)) kpi60010,sum(decode(t.kpi_name,'60011',t.current_num)) kpi60011,sum(decode(t.kpi_name,'60012',t.current_num)) kpi60012,sum(decode(t.kpi_name,'60013',t.current_num)) kpi60013,sum(decode(t.kpi_name,'60014',t.current_num)) kpi60014,sum(decode(t.kpi_name,'60015',t.current_num)) kpi60015,sum(decode(t.kpi_name,'60016',t.current_num)) kpi60016,sum(decode(t.kpi_name,'60017',t.current_num)) kpi60017,sum(decode(t.kpi_name,'60018',t.current_num)) kpi60018,sum(decode(t.kpi_name,'60019',t.current_num)) kpi60019,sum(decode(t.kpi_name,'60020',t.current_num)) kpi60020,sum(decode(t.kpi_name,'60021',t.current_num)) kpi60021,sum(decode(t.kpi_name,'60022',t.current_num)) kpi60022,sum(decode(t.kpi_name,'60023',t.current_num)) kpi60023,sum(decode(t.kpi_name,'60024',t.current_num)) kpi60024,sum(decode(t.kpi_name,'60025',t.current_num)) kpi60025,sum(decode(t.kpi_name,'60026',t.current_num)) kpi60026,sum(decode(t.kpi_name,'60027',t.current_num)) kpi60027,sum(decode(t.kpi_name,'60028',t.current_num)) kpi60028,sum(decode(t.kpi_name,'60029',t.current_num)) kpi60029,sum(decode(t.kpi_name,'60030',t.current_num)) kpi60030 from t_009_4_1_zhjxsr_jksr_f t";
	// 报表ID 90009 009-1.9综合绩效报表-成本利润-综合评价 对应Comprehensiveevaluation.jsp
	public final static String sql4 = "select t.period,wkmd.report_kpi_name,t.local_last_month,t.local_current,t.local_m_t0_m,t.local_m_to_m_exp,t.local_year_total,t.in_last_month,t.in_current,t.in_m_to_m_f,t.in_m_to_m_exp,t.in_year_total,t.full_cost_last_month,t.full_cost_current,t.full_cost_m_to_m,t.full_cost_m_o_m_exp,t.full_cost_year_total,t.full_cost_y_to_y,t.full_cost_y_to_y_add,t.full_cost_y_to_y_add_rate,t.last_month_outside_account_in,t.current_outside_account_in,t.year_total_outside_account,t.last_month_inside_account_in,t.current_inside_account_in,t.year_total_inside_account from t_009_9_1_zhjxcblr_zhpj_f t, w_kpi_mapping_d wkmd,w_sp_d wsd where t.kpi_name = wkmd.report_id || wkmd.attribute2 and t.attribute1 = wsd.sp_code";
	// 报表ID 90006 009-1.6综合绩效报表-成本利润-滚动预算 对应Rollingbudget.jsp页面
	public final static String sql5 = "select t.period,w.report_kpi_name,t.QUARTER_FULL_COST_BUDGET,t.FIRST_QUARTER_BUDGET,t.SECOND_QUARTER_BUDGET,t.THIRD_QUARTER_BUDGET,t.FOURTH_QUARTER_BUDGET,t.YEAR_ACCUMULATION,t.over_budget from t_009_6_1_zhjxcblr_gdys_f t,w_kpi_mapping_d w where t.kpi_name = w.report_id||w.attribute2 and w.report_id = '90006'";
	// 报表ID 90007 009-1.7综合绩效报表-成本利润-部门预算 对应Departmentalbudget.jsp页面
	public final static String sql6 = "select t.period,wkmd.attribute9,wkmd.report_kpi_name,t.year_actual_accum_amount,t.year_actual_accum_rate,t.budget_amount,t.budget_rate,t.adjustment,t.amount_over,t.amount_rate,t.complete_rate from t_009_7_1_zhjxcblr_bmys_f t, w_kpi_mapping_d wkmd where t.kpi_name = wkmd.report_kpi_id and wkmd.report_id = '90007'";
	// 报表ID 90005 009-1.5综合绩效报表-成本利润-年度预算 对应Yearlybudget.jsp页面
	public final static String sql7 = "select t.period,wkd.report_kpi_name,t.local_current_forcast,t.local_current_actual,t.local_year_accumulation,t.in_current_forcast,t.local_current_actual,t.in_year_accumulation,t.in_current_forcast,t.in_current_actual,t.in_year_accumulation,t.full_cost_current_forcast,t.full_cost_current_actual,t.full_cost_year_accumulation,t.year_budget_num,t.schedule_budget_num,t.year_budget_comp_num,t.year_budget_comp_rate,t.year_budget_accu_over,t.loc_curt_fcst_cpr_actu,t.in_curt_fcst_cpr_act,t.full_cost_curt_fcst_cpr_actu,t.year_on_year_accumulation,t.year_on_year_variety,t.year_on_year_variety_rate from t_009_5_1_zhjxcblr_ndys_f t, w_kpi_mapping_d wkd where t.kpi_name = wkd.report_id||wkd.attribute2";
	// 报表ID 90001 009-1.1综合绩效报表-收入-考核收入 对应Assessmentincome.jsp页面
	public final static String sql8 = "select kpi_classify,project,kpi_name,year,attribute1,sum(decode(month, '01', current_total, null)) month1,sum(decode(month, '02', current_total, null)) month2,sum(decode(month, '03', current_total, null)) month3,sum(decode(month, '04', current_total, null)) month4,sum(decode(month, '05', current_total, null)) month5,sum(decode(month, '06', current_total, null)) month6,sum(decode(month, '07', current_total, null)) month7,sum(decode(month, '08', current_total, null)) month8,sum(decode(month, '09', current_total, null)) month9,sum(decode(month, '10', current_total, null)) month10,sum(decode(month, '11', current_total, null)) month11,sum(decode(month, '12', current_total, null)) month12 from (select b.kpi_classify,b.project,b.kpi_name,to_char(b.period, 'yyyy') year,to_char(b.period, 'mm') month,b.current_total,b.attribute1,c.report_id || c.attribute3 attribute3 from t_009_1_1_zhjxsr_khsr_f b, w_kpi_mapping_d c where b.attribute1 = c.report_id || c.attribute2) where year = '2013' group by kpi_classify, project, kpi_name, year, attribute1, attribute3 order by attribute1";
	// 报表ID 90008 009-1.8综合绩效报表-成本利润-考核利润 对应Appraisalprofit.jsp页面
	public final static String sql9 = "select report_kpi_name,year,sum(decode(month, '01', current_total, null)) month1,sum(decode(month, '02', current_total, null)) month2,sum(decode(month, '03', current_total, null)) month3,sum(decode(month, '04', current_total, null)) month4,sum(decode(month, '05', current_total, null)) month5,sum(decode(month, '06', current_total, null)) month6,sum(decode(month, '07', current_total, null)) month7,sum(decode(month, '08', current_total, null)) month8,sum(decode(month, '09', current_total, null)) month9,sum(decode(month, '10', current_total, null)) month10,sum(decode(month, '11', current_total, null)) month11,sum(decode(month, '12', current_total, null)) month12 from";
	// 报表ID
	public final static String sql10 = "select report_kpi_name,year,sum(decode(month, '01', current_total, null)) month1,sum(decode(month, '02', current_total, null)) month2,sum(decode(month, '03', current_total, null)) month3,sum(decode(month, '04', current_total, null)) month4,sum(decode(month, '05', current_total, null)) month5,sum(decode(month, '06', current_total, null)) month6,sum(decode(month, '07', current_total, null)) month7,sum(decode(month, '08', current_total, null)) month8,sum(decode(month, '09', current_total, null)) month9,sum(decode(month, '10', current_total, null)) month10,sum(decode(month, '11', current_total, null)) month11,sum(decode(month, '12', current_total, null)) month12 from";
	// 报表ID (select
	// tt.period,aa.report_kpi_name,aa.report_id,aa.attribute2,aa.attribute3,tt.kpi_name,to_char(tt.period,
	// 'yyyy') year,to_char(tt.period, 'mm') month,tt.current_total from
	// T_009_8_1_ZHJXCBLR_KHLR_F tt, w_kpi_mapping_d aa where tt.attribute1 =
	// '利润考核指标执行情况' and tt.kpi_name = aa.report_id || aa.attribute2 order by
	// aa.report_id, aa.attribute3) where year = '2013' group by
	// report_kpi_name, year, report_id, attribute2,attribute3, kpi_name order
	// by year, report_id || attribute2
	public final static String sql11 = "";
	// 报表ID
	public final static String sql12 = "";
	// 报表ID
	public final static String sql13 = "";
	public final static String[] arr1 = { "perIod", "localCode", "showName",
			"localCurrent", "varyBttwCurrAndPrev", "growthRate",
			"currentYearTotal", "isColor" };
	public final static String[] arr2 = { "perIod", "catagory",
			"localTeleType", "showName", "currentNum", "monthOnMonthLimit",
			"totalAmount", "schedualAverage", "attribute" };
	public final static String[] arr3 = { "perIod", "kpi60001", "kpi60002",
			"kpi60003", "kpi60004", "kpi60005", "kpi60006", "kpi60007",
			"kpi60008", "kpi60009", "kpi60010", "kpi60011", "kpi60012",
			"kpi60013", "kpi60014", "kpi60015", "kpi60016", "kpi60017",
			"kpi60018", "kpi60019", "kpi60020", "kpi60021", "kpi60022",
			"kpi60023", "kpi60024", "kpi60025", "kpi60026", "kpi60027",
			"kpi60028", "kpi60029", "kpi60030" };
	public final static String[] arr4 = { "perIod", "showName",
			"localLastMonth", "localCurrent", "localMtoM", "localMtoMexp",
			"localYearTotal", "inLastMonth", "inCurrent", "inMtoMf",
			"inMtoMExp", "inYearTotal", "fullCostLastMonth", "fullCostCurrent",
			"fullCostMtoM", "fullCostMomExp", "fullCostYearTotal",
			"fullCostYtoY", "fullCostYtoYadd", "fullCostYtoYaddRate",
			"lastMonthOutsideAccountIn", "currentOutsideAccountIn",
			"yeatTotalOutsideAccount", "lastMonthInsideAccountIn",
			"yeatTotalInsideAccont" };
	public final static String[] arr5 = { "perIod", "reportKpiName",
			"quarterFullCostBudget", "firstQuarterBudget",
			"secondQuarterBudget", "thirdQuarterBudget", "fourthQuarterBudget",
			"yearAccumulation", "overBudget" };
	public final static String[] arr6 = { "perIod", "department", "showName",
			"yearAxtualAccumAmount", "yearAxtualAccumRate", "budgetAmount",
			"budgetRate", "adjustment", "amountOver", "amountRate",
			"completeRate" };
	public final static String[] arr7 = { "perIod", "showName",
			"localCurrentForcast", "localCurrentActual",
			"localYearAccumulation", "inCurrentForcast", "localCurrentActual",
			"inYeatAccumulation", "inCurrentForcast", "inCurrentActual",
			"inYearAccumulation", "fullCostCurrentForcast",
			"fullCostCurrentActual", "fullCostYearAccumulation",
			"yearBudgetNum", "scheduleBudgetNum", "yearBudgetCompNum",
			"yearBudgetCompRate", "yearBudgetAccuOver", "locCurtFcstCprActu",
			"inCurtFcstCprAct", "fullCostCurtFcstCprActu",
			"yearOnYearAccumulation", "yearOnYearVariety",
			"yearOnYearVarietyRate" };
	public final static String[] arr8 = { "kpiclassify", "project", "kpiName",
			"year", "attribute1", "month1", "month2", "month3", "month4",
			"month5", "month6", "month7", "month8", "month9", "month10",
			"month11", "month12" };
	public final static String[] arr9 = { "kpiName", "year", "month1",
			"month2", "month3", "month4", "month5", "month6", "month7",
			"month8", "month9", "month10", "month11", "month12" };
	public final static String[] arr10 = { "kpiName", "year", "month1",
			"month2", "month3", "month4", "month5", "month6", "month7",
			"month8", "month9", "month10", "month11", "month12" };
	public final static String[] arr11 = {};
	public final static String[] arr12 = {};
	public final static String[] arr13 = {};
}
