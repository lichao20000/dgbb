package com.dglt.statement.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.deloitte.si.core.utils.DateUtils;

import com.dglt.comm.base.Globals;
import com.dglt.statement.service.GetFormDataService;
import com.dglt.statement.vo.HisStatParamVo;
import com.eos.system.utility.StringUtil;

@Controller
public class FormChoose {

	@Resource(name = "getFormDataService")
	private GetFormDataService getFormDataService;

	// 请求进报表详情界面 路径为 /dgltbb/showReport.do?reportId=90008
	@RequestMapping("/showReport.do")
	public String getReportId(HttpServletRequest request,
			HttpServletResponse response) {

		HisStatParamVo paramVo = new HisStatParamVo();

		String fromFlag = request.getParameter("fromFlag");
		String reportId = request.getParameter("reportId");
		String url = Globals.getReportNameByreportId(reportId);
		if (url == null) {
			url = "error";
		} else {
			if (StringUtil.isNotEmpty(reportId) && StringUtil.isEmpty(fromFlag)) {
				String reportName = this.getFormDataService
						.getReportName(reportId);
				// String reportName="调度";
				request.setAttribute("reportId", reportId);
				request.setAttribute("reportName", reportName);			
			} 
			
			else if (StringUtil.isNotEmpty(reportId)
					&& StringUtil.isNotEmpty(fromFlag)) {
				org.springframework.core.io.Resource resource = new ClassPathResource(
						"/reportConfig.properties");
				try {
					Properties props = PropertiesLoaderUtils
							.loadProperties(resource);
					String reportName = (String) props.get(reportId);
					request.setAttribute("reportId", reportId);
					request.setAttribute("reportName", reportName);
					
					System.out.println();
					System.out.println("reportName:"+reportName);
					System.out.println("reportName:"+reportName);
					System.out.println();
					

				} catch (IOException e) {
					e.printStackTrace();
				}
			

			} 
			
			
			else {
				url = "error";
			}
		}
		// 初始化数据
		if (StringUtils.isEmpty(paramVo.getBizTypeCode())) {
			if(reportId.endsWith("90017"))
			{
				/*
				 * 
				 * 
		   参数名称：  
		   launchArea  值：按分公司或者团队的id
           fromDate    值：开始时间
           toDate      值：结束时间
           formStatus  值：工单状态
           bizTypeCode 值：业务类型
           blongTeam
				 * 
				 */
			String 	lanchArea = request.getParameter("launchArea");
			paramVo.setLanchAreaCode(lanchArea);
			
			String blongTeam=request.getParameter("blongTeam");
			paramVo.setBlongTeam(blongTeam);
			
			String 	fromDate = request.getParameter("fromDate");
			paramVo.setFromDate(fromDate);
			
			String  toDate = request.getParameter("toDate");
			paramVo.setToDate(toDate);

			String formStatus = request.getParameter("formStatus");
			paramVo.setFormStatus(formStatus);
			
			String bizTypeCode = request.getParameter("bizTypeCode");
			paramVo.setBizTypeCode(bizTypeCode);
			
			
			}
			
			else
			{
				paramVo.setBizTypeCode("guwang");
				paramVo.setWorkOrderType("preSale");
				paramVo.setFormTypeCode("preSale");
				Date currDay = new Date();
				
				Calendar cal = Calendar.getInstance();
				
				cal.add(Calendar.MONTH, -1);
							
				Date lastDay = cal.getTime();
				
				paramVo.setToDate(DateUtils.getDate(DateUtils.date_sdf
						.format(currDay)));
				
				System.out.println("asdfsdf"+DateUtils.getDate(DateUtils.date_sdf
						.format(currDay)));
				
				paramVo.setFromDate(DateUtils.getDate(DateUtils.date_sdf
						.format(lastDay)));
				paramVo.setTimeType("startFormTime");
			}
		}

		request.setAttribute("paramVo", paramVo);
		return url;
	}
}
