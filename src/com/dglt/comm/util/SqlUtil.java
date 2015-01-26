package com.dglt.comm.util;

import java.text.ParseException;
import java.util.Date;

import cn.com.deloitte.si.core.utils.DateUtils;

import com.dglt.statement.vo.HisStatParamVo;
import com.dglt.statement.vo.ReportName;

public class SqlUtil {

	/**
	 * @see 个性化报表sql
	 * @author zhyoy
	 * @return
	 */
	public static String connectSqlForPreSale(HisStatParamVo paramVo) {
		// 业务字段
		Date toDate = null;
		Date fromDate = null;
		try {
			toDate = DateUtils.datetimeFormat.parse(paramVo.getToDate()
					+ " 23:59:59");
			fromDate = DateUtils.datetimeFormat.parse(paramVo.getFromDate()
					+ " 00:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String bizTypeCode = paramVo.getBizTypeCode();
		String formTypeCode = paramVo.getFormTypeCode();
		String timeType = paramVo.getTimeType();
		String sql="";
		String timeCondition = null;
		// 工单查询条件
		StringBuffer condition = new StringBuffer();
		
		String whereCondition = null;
		if ("startFormTime".equals(timeType)) {// 发单时间
			timeCondition = "fm.send_time";
		} else {
			timeCondition = "wp.endtime";// 结束时间
		}
		whereCondition = " and " + timeCondition + ">=to_date('"
				+ new java.sql.Date(fromDate.getTime())
				+ "','yyyy-mm-dd') and " + timeCondition + "<to_date('"
				+ new java.sql.Date(toDate.getTime())
				+ "','yyyy-mm-dd') and fm.biz_type='" + bizTypeCode
				+ "' and fm.req_type='" + formTypeCode + "'" + condition;
		if(bizTypeCode.equals("guwang"))
		{
		 sql =ReportName.sql_3002;
		}
		
		else if(bizTypeCode.equals("ict"))
		{
			sql="select distinct fm.req_type,fm.prcs_inst_id,fm.pk_id pk_id ,wi.workitemid,wi.activityinstid,fm.form_seq form_seq,fm.send_time send_time,fm.form_title form_title,fp.assess_score assessment_score,p.user_name user_name,ww.partiname partiname,p.dept_name dept_name,case when fp.assess_score is NULL then null else  fp.last_updated_date end assessment_time,tfs.opt_comment enum_value_meaning  from T_FORM_MAIN fm,WFWIPARTICIPANT ww,T_FORM_ICT_PRESALES fp,t_form_step tfs,info_person p,wfprocessinst wp , wfworkitem wi where ww.workitemname like '%方案评价' and ww.participantname=ww.partiname and ww.partiintype like '%EXE'  and ww.processinstid=fm.prcs_inst_id and fp.parent_form_id=fm.pk_id and fp.form_no=fm.form_seq and fm.send_user_id=p.user_id  and wp.processinstid= fm.prcs_inst_id and wi.processinstid=wp.processinstid and wi.activitydefid='DraftActivity'and tfs.prcs_inst_id=fm.prcs_inst_id and tfs.opt_type='submitAssase' and 1=1"
					+" union all "
				 +"select distinct fm.req_type,fm.prcs_inst_id,fm.pk_id pk_id ,wi.workitemid,wi.activityinstid,fm.form_seq form_seq,fm.send_time send_time,fm.form_title form_title,fp.assess_score assessment_score,p.user_name user_name,whw.partiname partiname,p.dept_name dept_name,case when fp.assess_score is NULL then null else  fp.last_updated_date end assessment_time,tfs.opt_comment enum_value_meaning  from T_FORM_MAIN fm,wf_h_wiparticipant whw,T_FORM_ICT_PRESALES fp,t_form_step tfs,info_person p,wfprocessinst wp , wfworkitem wi where whw.workitemname like '%方案评价' and whw.participantname=whw.partiname and whw.partiintype like '%EXE'  and whw.processinstid=fm.prcs_inst_id and fp.parent_form_id=fm.pk_id and fp.form_no=fm.form_seq and fm.send_user_id=p.user_id  and wp.processinstid= fm.prcs_inst_id and wi.processinstid=wp.processinstid and wi.activitydefid='DraftActivity'and tfs.prcs_inst_id=fm.prcs_inst_id and tfs.opt_type='submitAssase' and 1=1";
			
			System.out.println("123:"+bizTypeCode);
		
		
		}
			
		else   //需求待定，只能是固网和ICT
		
			sql="select distinct fm.req_type,fm.prcs_inst_id,fm.pk_id pk_id ,wi.workitemid,wi.activityinstid,fm.form_seq form_seq,fm.send_time send_time,fm.form_title form_title,fp.assess_score assessment_score,p.user_name user_name,ww.partiname partiname,p.dept_name dept_name,case when fp.assess_score is NULL then null else  fp.last_updated_date end assessment_time,tfs.opt_comment enum_value_meaning  from T_FORM_MAIN fm,WFWIPARTICIPANT ww,T_FORM_ICT_PRESALES fp,t_form_step tfs,info_person p,wfprocessinst wp , wfworkitem wi where ww.workitemname like '%方案评价' and ww.participantname=ww.partiname and ww.partiintype like '%EXE'  and ww.processinstid=fm.prcs_inst_id and fp.parent_form_id=fm.pk_id and fp.form_no=fm.form_seq and fm.send_user_id=p.user_id  and wp.processinstid= fm.prcs_inst_id and wi.processinstid=wp.processinstid and wi.activitydefid='DraftActivity'and tfs.prcs_inst_id=fm.prcs_inst_id and tfs.opt_type='submitAssase'";

			sql=sql.replaceAll("and 1=1", whereCondition);
			
			System.out.println("输出售前支撑评分的sql:"+sql);
			
		return sql;
	}
}
