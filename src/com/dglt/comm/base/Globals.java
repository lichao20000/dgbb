package com.dglt.comm.base;

import java.util.HashMap;

import com.dglt.bb.pojo.ExcelTemp;

public class Globals {

	public static String CONTEXT_ROOT = null;
	//地图的地区标识ID
	public static HashMap<String,String> hamp = new HashMap<String,String>();
	//地图的Felxe路经
	public static HashMap<String,String> flexPath = new HashMap<String,String>();
    //报表的ID对应路径
	public static HashMap<String,String> statementPath = new HashMap<String,String>();
	public static  HashMap<String,ExcelTemp> excelTemp =new   HashMap<String,ExcelTemp>();
	//数字对应字母
	public static HashMap<Integer,String> numMap =new HashMap<Integer,String>();
	
	static {
	    if (CONTEXT_ROOT == null) {
	    	CONTEXT_ROOT = System.getProperty("dgllbb");
	    }
	    hamp.put("FCQ", "CN.GD.DG.DG");	
	    hamp.put("FTX", "CN.GD.DG.TX");	
	    hamp.put("FQX", "CN.GD.DG.QX");	
	    hamp.put("FZMT","CN.GD.DG.ZMT");
	    hamp.put("FCP", "CN.GD.DG.CP");	
	    hamp.put("FSL", "CN.GD.DG.SL");	
	    hamp.put("FCA", "CN.GD.DG.CA");	
	    hamp.put("FHM", "CN.GD.DG.HM");	
	    hamp.put("FHJ", "CN.GD.DG.HJ");	
	    hamp.put("FWJ", "CN.GD.DG.WJ");	
	    hamp.put("FSP", "CN.GD.DG.SP");	
	    hamp.put("QCPLBDK","CN.GD.DG.CP.LB");
	    hamp.put("QCPDONGK","CN.GD.DG.CP.DK");
	    hamp.put("QCPDL","CN.GD.DG.CP.DL");
	    //hamp.put("QCPDK","CN.GD.DG.CP.CP");
	    hamp.put("QCPX","CN.GD.DG.CP.CP");//营服中心常平没有数据，用常平本部（虚拟）营服中心代替
	    hamp.put("QCPD","CN.GD.DG.CP.TT");
	    hamp.put("QCPDLS","CN.GD.DG.CA.DLS");
	    hamp.put("QCAD","CN.GD.DG.CA.CS");
	    hamp.put("QCAZ","CN.GD.DG.CA.XA");
	    hamp.put("QCAX","CN.GD.DG.CA.FH");
	    hamp.put("QCQGC","CN.GD.DG.CQ.GC");
	    hamp.put("QCQDCLH","CN.GD.DG.CQ.DCLH");
	    hamp.put("QCQDC1","CN.GD.DG.CQ.DCSB");
	    hamp.put("QCQDC2","CN.GD.DG.CQ.DCN");
	    hamp.put("QCQNC","CN.GD.DG.CQ.NC");
	    hamp.put("QCQSSH","CN.GD.DG.CQ.SSH");
	    hamp.put("QHJDK","CN.GD.DG.HJ.HJ");
	    hamp.put("QHMST","CN.GD.DG.HM.ST");
	    hamp.put("QHM1","CN.GD.DG.HM.BS");
	    hamp.put("QHM2","CN.GD.DG.HM.NS");
	    hamp.put("QQXB","CN.GD.DG.QX.QXB");
	    hamp.put("QQXN","CN.GD.DG.QX.QXN");
	    hamp.put("QSLSJ","CN.GD.DG.SL.SJ");
	    hamp.put("QSLSL","CN.GD.DG.SL.SL");
	    hamp.put("QSLCS","CN.GD.DG.SL.CS");
	    hamp.put("QSLSP","CN.GD.DG.SP.SP");
	    hamp.put("QSPQS","CN.GD.DG.SP.QS");
	    hamp.put("QSLHL","CN.GD.DG.SP.HL");
	    //hamp.put("QSPDKQT","CN.GD.DG.SP.QT");
	    hamp.put("QSLQT","CN.GD.DG.SP.QT");//营服中心桥头营服中心没有数据，用桥头大客团队代替
	    hamp.put("QTXA","CN.GD.DG.TX.SC");
	    hamp.put("QTXB","CN.GD.DG.TX.SG");
	    hamp.put("QTXFG","CN.GD.DG.TX.FG");
	    hamp.put("QCQSX","CN.GD.DG.WJ.GB");
	    hamp.put("QWJZT","CN.GD.DG.WJ.ZT");
	    hamp.put("QWJMC","CN.GD.DG.WJ.MY");
	    hamp.put("QCQWJ","CN.GD.DG.WJ.WJ");
	    hamp.put("QWJWND","CN.GD.DG.WJ.WND");
	    hamp.put("QWJDJ","CN.GD.DG.WJ.DJ");
	    hamp.put("QWJHM","CN.GD.DG.WJ.HM");
	    hamp.put("QZMTXG","CN.GD.DG.ZMT.ZMT");
	    hamp.put("QZMTHJ","CN.GD.DG.ZMT.HJ");
	    hamp.put("QXG","CN.GD.DG.ZMT.XG");
	    
	    flexPath.put("FCQ", "FCMap_GD_Dgcq.swf");	
	    flexPath.put("FTX", "FCMap_GD_Tangxia.swf");	
	    flexPath.put("FQX", "FCMap_GD_Qingxi.swf");	
	    flexPath.put("FZMT","FCMap_GD_Zhangmutou.swf");
	    flexPath.put("FCP", "FCMap_GD_Changping.swf");	
	    flexPath.put("FSL", "FCMap_GD_Shilong.swf");	
	    flexPath.put("FCA", "FCMap_GD_Changan.swf");	
	    flexPath.put("FHM", "FCMap_GD_Humen.swf");	
	    flexPath.put("FHJ", "FCMap_GD_Houjie.swf");	
	    flexPath.put("FWJ", "FCMap_GD_Wanjiang.swf");	
	    flexPath.put("FSP", "FCMap_GD_Shipai.swf");	
	    flexPath.put("FDW", "FCMap_GD_Dongguan.swf");
	    
	  //报表路径
	    statementPath.put("90001", "/statement/Assessmentincome");
	    statementPath.put("90002", "/statement/ComprehensivePerformance");
	    statementPath.put("90003", "/statement/SettlementStatement");
	    statementPath.put("90004", "/statement/Sgincome");
	    statementPath.put("90005", "/statement/Yearlybudget");
	    statementPath.put("90006", "/statement/Rollingbudget");
	    statementPath.put("90007", "/statement/Departmentalbudget");
	    statementPath.put("90008", "/statement/Appraisalprofit");
	    statementPath.put("90009", "/statement/Comprehensiveevaluation");
	    
	    //新增报表：集客售前：售前支撑工单评分报表
	    statementPath.put("3001", "/report/localDispatchLedger");
	    statementPath.put("3002", "/report/preSaleReport");
	    
	    //调度基本台帐报表
	    statementPath.put("90013", "/report/local");
	    //集客拆机报表
	    statementPath.put("90014", "/report/local2");
	    
	    //日报
	    statementPath.put("90015", "/report/local3");
	    
	    //调度工单在途状态
	    statementPath.put("90016", "/report/local4");
	    
	    //工程项目概况-下钻明细表
	    statementPath.put("90017", "/report/local5");
	    
	    
	    //报表模块  sql8.xlsx
	    excelTemp.put("sql8", new ExcelTemp("excelTemp/考核收入.xlsx",1,3,true,"考核收入",4,"totalcount")) ;
	    excelTemp.put("sql1", new ExcelTemp("excelTemp/收入明细.xlsx",1,2,false,"收入明细",-1,"-1")) ;
	    excelTemp.put("sql2", new ExcelTemp("excelTemp/网内网间结算.xlsx",1,3,false,"网内网间结算",-1,"-1")) ;
	    excelTemp.put("sql3", new ExcelTemp("excelTemp/集客收入.xlsx",2,0,false,"集客收入",-1,"-1")) ;
	    excelTemp.put("sql7", new ExcelTemp("excelTemp/年度预算.xlsx",3,0,false,"年度预算",-1,"-1")) ;
	    excelTemp.put("sql5", new ExcelTemp("excelTemp/滚动预算.xlsx",2,1,false,"滚动预算",-1,"-1")) ;    
	    excelTemp.put("sql6", new ExcelTemp("excelTemp/部门预算.xlsx",2,0,false,"部门预算",-1,"-1")) ;
	    excelTemp.put("sql4", new ExcelTemp("excelTemp/综合评价.xlsx",2,0,false,"综合评价",-1,"-1")) ;
	    excelTemp.put("sql10", new ExcelTemp("excelTemp/考核利润.xlsx",1,0,false,"考核利润",-1,"-1")) ;
	    excelTemp.put("kpiTemp", new ExcelTemp("excelTemp/指标下钻导出.xlsx",1,0,true,"11",-1,"-1")) ;
	    excelTemp.put("sql3002",new ExcelTemp("excelTemp/售前支撑评分.xlsx",1,0,false,"售前支撑评分",-1,"-1")) ;
	    
	    //调度基本台帐报表
	    excelTemp.put("sql90013",new ExcelTemp("excelTemp/调度基本台帐报表.xlsx",1,0,false,"调度基本台帐报表",-1,"-1")) ;
	    excelTemp.put("sql90014",new ExcelTemp("excelTemp/集客拆机报表.xlsx",1,0,false,"集客拆机报表",-1,"-1")) ;
	    
	    excelTemp.put("sql90016",new ExcelTemp("excelTemp/调度工单在途状态.xlsx",1,0,false,"调度工单在途状态",-1,"-1")) ;
	    excelTemp.put("sql90015",new ExcelTemp("excelTemp/日报.xlsx",2,0,false,"日报",-1,"-1")) ;
	    
	    
	  //工程项目概况-下钻明细表
	    excelTemp.put("sql90017",new ExcelTemp("excelTemp/工程项目概况-下钻明细表.xlsx",3,0,false,"工程项目概况-下钻明细表",-1,"-1")) ;

	    //新增报表：集客售前：售前支撑工单评分报表
	    excelTemp.put("sql_10001", new ExcelTemp("excelTemp/售前支撑工单评分.xlsx",1,3,true,"售前支撑工单评分报表",4,"totalcount")) ;
	    
	    //数字对应字母
	    numMap.put(1,"A");
	    numMap.put(2,"B");
	    numMap.put(3,"C");
	    numMap.put(4,"D");
	    numMap.put(5,"E");
	    numMap.put(6,"F");
	    numMap.put(7,"G");
	    numMap.put(8,"H");
	    numMap.put(9,"I");
	    numMap.put(10,"J");
	    numMap.put(11,"K");
	    numMap.put(12,"L");
	    numMap.put(13,"M");
	    numMap.put(14,"N");
	    numMap.put(15,"O");
	    numMap.put(16,"P");
	    numMap.put(17,"Q");
	    numMap.put(18,"R");
	    numMap.put(19,"S");
	    numMap.put(20,"T");
	    numMap.put(21,"U");
	    numMap.put(22,"V");
	    numMap.put(23,"W");
	    numMap.put(24,"X");
	    numMap.put(25,"Y");
	    numMap.put(26,"Z");    
	}
	
	
	public static String getMapIdByCode(String code){
		return hamp.get(code);
	}
	
	public static String getFlexNameByCode(String code){
		return flexPath.get(code);
	}
	
	public static String getReportNameByreportId(String code){
		return statementPath.get(code);
	}
	
	public static  ExcelTemp getExcelTempByCode(String code){
		return excelTemp.get(code);
		
	}
	
	public static String getNumMap(int id){
		
		return numMap.get(id);
	}
}
