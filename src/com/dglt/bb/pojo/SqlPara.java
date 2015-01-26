package com.dglt.bb.pojo;
  //统一定义SQL 和返回列
public class SqlPara {
	//2G业务流失率 地图表SQL 分公司
	 public final static String turnover2gMap ="select t.District_Branch_Code districtBranchCode, t.District_Branch_Name districtBranchName ,sum(t.Amount_Curr) amountCurr ,'' mapCode  from W_2G_BIZ_LOSS_RATE_V t where 1=1 ";
	//2G业务流失率 曲线SQL 分公司
	 public final static String turnover2gLine ="select t.Month_Id monthId, t.Month_Dsc monthDsc ,sum(t.Amount_Curr) amountCurr  from W_2G_BIZ_LOSS_RATE_V t where 1=1";
	//2G业务流失率 数据SQL 分公司
	 public final static String turnover2gTabe ="SELECT  t.Kpi_Id kpiId ,  t.Kpi_Name kpiName, t.Month_Id monthId  ,t.Month_Dsc monthDsc , t.Sp_Code spCode ,t.Sp_Name spName ,T.DISTRICT_BRANCH_CODE DISTRICTBRANCHCODE, T.DISTRICT_BRANCH_NAME DISTRICTBRANCHNAME ,t.Busi_Sc_Code   busiSccode,t.Busi_Sc_Name   busiScName ,T.AMOUNT_CURR AMOUNTCURR FROM W_2G_BIZ_LOSS_RATE_V T WHERE 1 = 1";
	//2G业务流失率 地图  返回列名   分公司
     public final static String [] arr12gMap={"districtBranchCode","districtBranchName","amountCurr","mapCode"};
   //2G业务流失率 曲线返回列名 分公司
     public final static String [] arr12gLine={"monthId","monthDsc","amountCurr"};
   //2G业务流失率 数据返回列名 分公司
     public final static String [] arr12gTable={"sp_id","product_code","client_code","kpi_id","qufen_id","quyu_id","month01","month02","month03",	"month04",	"month05",	"month06","month07","month08","month09","month10","month11","month12"};
     //2G业务流失率 地图表SQL 营业厅
	 public final static String turnover2gBizcsMap ="select t.Busi_Sc_Code busiSccode ,t.Busi_Sc_Name busiScName  ,sum(t.Amount_Curr) amountCurr  ,'' mapCode  from W_2G_BIZ_LOSS_RATE_V t where 1=1 ";
	//2G业务流失率 曲线SQL  营业厅
	 public final static String turnover2gBizcsLine ="select t.Month_Id monthId, t.Month_Dsc monthDsc ,sum(t.Amount_Curr) amountCurr  from W_2G_BIZ_LOSS_RATE_V t where 1=1";
	//2G业务流失率 数据SQL  营业厅
	 public final static String turnover2gBizcsTabe ="SELECT  t.Kpi_Id kpiId ,  t.Kpi_Name kpiName, t.Month_Id monthId  ,t.Month_Dsc monthDsc , t.Sp_Code spCode ,t.Sp_Name spName ,T.DISTRICT_BRANCH_CODE DISTRICTBRANCHCODE, T.DISTRICT_BRANCH_NAME DISTRICTBRANCHNAME ,t.Busi_Sc_Code   busiSccode,t.Busi_Sc_Name   busiScName ,T.AMOUNT_CURR AMOUNTCURR FROM W_2G_BIZ_LOSS_RATE_V T WHERE 1 = 1";
		//2G业务流失率 地图  返回列名    营业厅
     public final static String [] arr12gBizcsMap={"busiSccode","busiScName","amountCurr","mapCode"};
   //2G业务流失率 曲线返回列名  营业厅
     public final static String [] arr12gBizcsLine={"monthId","monthDsc","amountCurr"};
   //2G业务流失率 数据返回列名  营业厅
     public final static String [] arr12gBizcsTable={"kpiId","kpiName","monthId","monthDsc","spCode","spName" ,"DISTRICTBRANCHCODE" ,"districtbranchName","busiSccode","busiScName","amountCurr"};
   
     
     //2G业务流失率   地图表SQL 经理
	 public final static String turnover2gManegersMap ="select t.Busi_Sc_Code busiSccode ,t.Busi_Sc_Name busiScName  ,sum(t.Amount_Curr) amountCurr  from W_2G_BIZ_LOSS_RATE_V t where 1=1 ";
	//2G业务流失率   曲线SQL  经理
	 public final static String turnover2gManegersLine ="select t.Month_Id monthId, t.Month_Dsc monthDsc ,sum(t.Amount_Curr) amountCurr  from W_2G_BIZ_LOSS_RATE_V t where 1=1";
	//2G业务流失率   返回列名    经理
     public final static String [] arrturnover2gManegersMap={"managerNo","chartLabelName","chartLabelValue"};
    //2G业务流失率   曲线返回列名 经理
     public final static String [] arrturnover2gManegersLine={"monthId","monthDsc","amountCurr"};
     
     
     
	 //3G语音后付费用户流失率  地图表SQL  分公司
	 public final static String turnover3gMap ="select t.District_Branch_Code districtBranchCode, t.District_Branch_Name districtBranchName ,sum(t.Amount_Curr) amountCurr   ,'' mapCode from w_3g_speech_user_loss_rate_v t where 1=1 ";
		//3G语音后付费用户流失率 曲线SQL 分公司
	 public final static String turnover3gLine ="select t.Month_Id monthId, t.Month_Dsc monthDsc ,sum(t.Amount_Curr) amountCurr  from w_3g_speech_user_loss_rate_v t where 1=1";
		  //3G语音后付费用户流失率  数据SQL 分公司
	 public final static String turnover3gTabe ="SELECT  t.Kpi_Id kpiId ,  t.Kpi_Name kpiName, t.Month_Id monthId  ,t.Month_Dsc monthDsc , t.Sp_Code spCode ,t.Sp_Name spName ,T.DISTRICT_BRANCH_CODE DISTRICTBRANCHCODE, T.DISTRICT_BRANCH_NAME DISTRICTBRANCHNAME ,t.Busi_Sc_Code   busiSccode,t.Busi_Sc_Name   busiScName ,T.AMOUNT_CURR AMOUNTCURR FROM w_3g_speech_user_loss_rate_v T WHERE 1 = 1";
	 	//3G语音后付费用户流失率  地图表返回列名 分公司
     public final static String [] arr13gMap={"districtBranchCode","districtBranchName","amountCurr","mapCode"};
   //3G语音后付费用户流失率 曲线返回列名 分公司
     public final static String [] arr13gLine={"monthId","monthDsc","amountCurr"};
   //3G语音后付费用户流失率  数据返回列名 分公司
     public final static String [] arr13gTable={"kpiId","kpiName","monthId","monthDsc","spCode","spName" ,"DISTRICTBRANCHCODE" ,"districtbranchName","busiSccode","busiScName","amountCurr"};
  	//3G语音后付费用户流失率  地图表SQL  营业厅
	 public final static String turnover3gBizcsMap ="select t.Busi_Sc_Code busiSccode ,t.Busi_Sc_Name busiScName ,sum(t.Amount_Curr) amountCurr  ,'' mapCode  from w_3g_speech_user_loss_rate_v t where 1=1 ";
		//3G语音后付费用户流失率 曲线SQL  营业厅
	 public final static String turnover3gBizcsLine ="select t.Month_Id monthId, t.Month_Dsc monthDsc ,sum(t.Amount_Curr) amountCurr  from w_3g_speech_user_loss_rate_v t where 1=1";
		  //3G语音后付费用户流失率  数据SQL  营业厅
	 public final static String turnover3gBizcsTabe ="SELECT  t.Kpi_Id kpiId ,  t.Kpi_Name kpiName, t.Month_Id monthId  ,t.Month_Dsc monthDsc , t.Sp_Code spCode ,t.Sp_Name spName ,T.DISTRICT_BRANCH_CODE DISTRICTBRANCHCODE, T.DISTRICT_BRANCH_NAME DISTRICTBRANCHNAME ,t.Busi_Sc_Code   busiSccode,t.Busi_Sc_Name   busiScName ,T.AMOUNT_CURR AMOUNTCURR FROM w_3g_speech_user_loss_rate_v T WHERE 1 = 1";
	 	//3G语音后付费用户流失率  地图表返回列名  营业厅
     public final static String [] arr13gBizcsMap={"busiSccode","busiScName","amountCurr","mapCode"};
   //3G语音后付费用户流失率 曲线返回列名  营业厅
     public final static String [] arr13gBizcsLine={"monthId","monthDsc","amountCurr"};
   //3G语音后付费用户流失率  数据返回列名  营业厅
     public final static String [] arr13gBizcsTable={"kpiId","kpiName","monthId","monthDsc","spCode","spName" ,"DISTRICTBRANCHCODE" ,"districtbranchName","busiSccode","busiScName","amountCurr"};
   
   //3G语音后付费用户流失率   地图表SQL 经理
	 public final static String turnover3gManegersMap ="select t.Busi_Sc_Code busiSccode ,t.Busi_Sc_Name busiScName  ,sum(t.Amount_Curr) amountCurr  from W_2G_BIZ_LOSS_RATE_V t where 1=1 ";
	//3G语音后付费用户流失率   曲线SQL  经理
	 public final static String turnover3gManegersLine ="select t.Month_Id monthId, t.Month_Dsc monthDsc ,sum(t.Amount_Curr) amountCurr  from W_2G_BIZ_LOSS_RATE_V t where 1=1";
	//3G语音后付费用户流失率  返回列名    经理
     public final static String [] arrturnover3gManegersMap={"managerNo","chartLabelName","chartLabelValue"};
    //3G语音后付费用户流失率   曲线返回列名 经理
     public final static String [] arrturnover3gManegersLine={"monthId","monthDsc","amountCurr"};
     
     
   
    //收入进度预算完成率 地图表SQL 分公司
	 public final static String incomeBudgetMap ="select t.District_Branch_Code districtBranchCode, t.District_Branch_Name districtBranchName ,sum(t.Amount_Curr) amountCurr ,'' mapCode  from W_INCOME_BUDGET_V t where 1=1 ";
	//2收入进度预算完成率 曲线SQL 分公司
	 public final static String incomeBudgetLine ="select t.Month_Id monthId, t.Month_Dsc monthDsc ,sum(t.Amount_Curr) amountCurr  from W_INCOME_BUDGET_V t where 1=1";
	//收入进度预算完成率 地图  返回列名   分公司
     public final static String [] arrIncomeBudgetMap={"districtBranchCode","districtBranchName","amountCurr","mapCode"};
    //收入进度预算完成率 曲线返回列名 分公司
     public final static String [] arrIncomeBudgetLine={"monthId","monthDsc","amountCurr"};
    //收入进度预算完成率 地图表SQL 营业厅
	 public final static String incomeBudgetBizcsMap ="select t.Busi_Sc_Code busiSccode ,t.Busi_Sc_Name busiScName  ,sum(t.Amount_Curr) amountCurr  ,'' mapCode  from W_INCOME_BUDGET_V t where 1=1 ";
	 //收入进度预算完成率 曲线SQL  营业厅
	 public final static String incomeBudgetBizcsLine ="select t.Month_Id monthId, t.Month_Dsc monthDsc ,sum(t.Amount_Curr) amountCurr  from W_INCOME_BUDGET_V t where 1=1";
    //收入进度预算完成率 返回列名    营业厅
     public final static String [] arr1incomeBudgetBizcsMap={"busiSccode","busiScName","amountCurr","mapCode"};
    //收入进度预算完成率 曲线返回列名  营业厅
     public final static String [] arr1incomeBudgetBizcsLine={"monthId","monthDsc","amountCurr"};
   
     //收入进度预算完成率 曲线返回列名   地图表SQL 经理
	 public final static String incomeBudgetManegersMap ="select t.Busi_Sc_Code busiSccode ,t.Busi_Sc_Name busiScName  ,sum(t.Amount_Curr) amountCurr  from W_2G_BIZ_LOSS_RATE_V t where 1=1 ";
	//收入进度预算完成率 曲线返回列名   曲线SQL  经理
	 public final static String incomeBudgetManegersLine ="select t.Month_Id monthId, t.Month_Dsc monthDsc ,sum(t.Amount_Curr) amountCurr  from W_2G_BIZ_LOSS_RATE_V t where 1=1";
	//收入进度预算完成率 曲线返回列名  返回列名    经理
     public final static String [] arrincomeBudgetManegersMap={"managerNo","chartLabelName","chartLabelValue"};
    //收入进度预算完成率 曲线返回列名   曲线返回列名 经理
     public final static String [] arrincomeBudgetManegersLine={"monthId","monthDsc","amountCurr"};
     
     
     
    //收入进度预算完成率 地图表SQL 分公司
	 public final static String profitBudgetMap ="select t.District_Branch_Code districtBranchCode, t.District_Branch_Name districtBranchName ,sum(t.Amount_Curr) amountCurr ,'' mapCode  from W_PROFIT_BUDGET_V t where 1=1 ";
	//2收入进度预算完成率 曲线SQL 分公司
	 public final static String profitBudgetLine ="select t.Month_Id monthId, t.Month_Dsc monthDsc ,sum(t.Amount_Curr) amountCurr  from W_PROFIT_BUDGET_V t where 1=1";
	//收入进度预算完成率 数据SQL 分公司
	     public final static String [] arrprofitBudgetMap={"districtBranchCode","districtBranchName","amountCurr","mapCode"};
    //收入进度预算完成率 曲线返回列名 分公司
     public final static String [] arrprofitBudgetLine={"monthId","monthDsc","amountCurr"};
    //收入进度预算完成率 地图表SQL 营业厅
	 public final static String profitBudgetBizcsMap ="select t.Busi_Sc_Code busiSccode ,t.Busi_Sc_Name busiScName  ,sum(t.Amount_Curr) amountCurr  ,'' mapCode  from W_PROFIT_BUDGET_V t where 1=1 ";
	//收入进度预算完成率 曲线SQL  营业厅
	 public final static String profitBudgetBizcsLine ="select t.Month_Id monthId, t.Month_Dsc monthDsc ,sum(t.Amount_Curr) amountCurr  from W_PROFIT_BUDGET_V t where 1=1";
	//收入进度预算完成率 返回列名    营业厅
     public final static String [] arr1profitBudgetBizcsMap={"busiSccode","busiScName","amountCurr","mapCode"};
    //收入进度预算完成率 曲线返回列名  营业厅
     public final static String [] arr1profitBudgetBizcsLine={"monthId","monthDsc","amountCurr"};
  
     //收入进度预算完成率   地图表SQL 经理
	 public final static String profitBudgetManegersMap ="select t.Busi_Sc_Code busiSccode ,t.Busi_Sc_Name busiScName  ,sum(t.Amount_Curr) amountCurr  from W_2G_BIZ_LOSS_RATE_V t where 1=1 ";
	//收入进度预算完成率   曲线SQL  经理
	 public final static String profitBudgetManegersLine ="select t.Month_Id monthId, t.Month_Dsc monthDsc ,sum(t.Amount_Curr) amountCurr  from W_2G_BIZ_LOSS_RATE_V t where 1=1";
	//收入进度预算完成率  返回列名    经理
     public final static String [] arrprofitBudgetManegersMap={"managerNo","chartLabelName","chartLabelValue"};
    //收入进度预算完成率   曲线返回列名 经理
     public final static String [] arrprofitBudgetManegersLine={"monthId","monthDsc","amountCurr"};
     
     
}
