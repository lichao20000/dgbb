package com.dglt.statement.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dglt.comm.util.SystemUtil;
import com.dglt.comm.util.gson.JsonUtil;
import com.dglt.statement.service.DateChooserService;
import com.dglt.statement.service.GetFormDataService;
import com.dglt.statement.vo.FormName;

@Controller
@RequestMapping("/getForm.do")
public class GetFormData {
	@Resource(name = "getFormDataService")
	private GetFormDataService getFormDataService;
	@Resource(name = "dateChooserService")
	private DateChooserService dateChooserService;

	// 获取报表参数
	@RequestMapping(params = "method=homePage")
	public String getHomePage(HttpServletRequest request,
			HttpServletResponse response, String period) {
		try {
			String[] arry = {};
			String a = request.getParameter("sql");
			String monthId = request.getParameter("monthId");
			String text = request.getParameter("reportid");
			String categoryId = request.getParameter("categorytext");
			String yearId = request.getParameter("yearId");
			String styletext = request.getParameter("styletext");
			String cttext = request.getParameter("cttext");
			String sqlNo = "";
			String sql = "";
			if ("sql1".equals(a)) {
				sql = FormName.sql1;
				arry = FormName.arr1;
				sqlNo = "1";
			}
			if ("sql2".equals(a)) {
				sql = FormName.sql2;
				arry = FormName.arr2;
				sqlNo = "2";
			}
			if ("sql3".equals(a)) {
				sql = FormName.sql3;
				arry = FormName.arr3;
				sqlNo = "3";
			}
			if ("sql4".equals(a)) {
				sql = FormName.sql4;
				arry = FormName.arr4;
				sqlNo = "4";
			}
			if ("sql5".equals(a)) {
				sql = FormName.sql5;
				arry = FormName.arr5;
				sqlNo = "5";
			}
			if ("sql6".equals(a)) {
				sql = FormName.sql6;
				arry = FormName.arr6;
				sqlNo = "6";
			}
			if ("sql7".equals(a)) {
				sql = FormName.sql7;
				arry = FormName.arr7;
				sqlNo = "7";
			}
			if ("sql8".equals(a)) {
				sql = FormName.sql8;
				arry = FormName.arr8;
				sqlNo = "8";
			}
			if ("sql9".equals(a)) {
				sql = FormName.sql9;
				arry = FormName.arr9;
				sqlNo = "9";
			}
			if ("sql10".equals(a)) {
				sql = FormName.sql10;
				arry = FormName.arr10;
				sqlNo = "10";
			}
			if ("sql11".equals(a)) {
				sql = FormName.sql11;
				arry = FormName.arr11;
				sqlNo = "11";
			}
			if ("sql12".equals(a)) {
				sql = FormName.sql12;
				arry = FormName.arr12;
				sqlNo = "12";
			}
			if ("sql13".equals(a)) {
				sql = FormName.sql13;
				arry = FormName.arr13;
				sqlNo = "13";
			}
			List l = this.getFormDataService.getData(sqlNo, "", "", "", "",
					arry, sql, monthId, text, categoryId, yearId, styletext,
					cttext);
			
			
			SystemUtil.responseJSONString(JsonUtil.toJson(l), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取月份参数
	@RequestMapping(params = "method=datachoose")
	public String getData(HttpServletRequest request,
			HttpServletResponse response, String period) {
		try {

			String l = this.dateChooserService.GetDateData();
			SystemUtil.responseJSONString(JsonUtil.toJson(l), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取年份参数
	@RequestMapping(params = "method=yearchoose")
	public String getYearData(HttpServletRequest request,
			HttpServletResponse response, String period) {
		try {

			String l = this.dateChooserService.GetyearData();
			System.out.println(l);
			SystemUtil.responseJSONString(JsonUtil.toJson(l), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取报表参数
	@RequestMapping(params = "method=reportchoose")
	public String getEnotherReport(HttpServletRequest request,
			HttpServletResponse response, String period) {
		try {

			List l = this.dateChooserService.GetEnotherReport();
			SystemUtil.responseJSONString(JsonUtil.toJson(l), response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取专业参数
	@RequestMapping(params = "method=getcartgory")
	public String getReportCartgory(HttpServletRequest request,
			HttpServletResponse response, String period) {
		try {
			String categorytext = request.getParameter("categorytext") == null ? ""
					: request.getParameter("categorytext");
			String l = this.dateChooserService.GetCategoryData(categorytext);
			SystemUtil.responseJSONString(JsonUtil.toJson(l), response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 获取滚动利润的季度选择参数
	@RequestMapping(params = "method=getquarter")
	public String getQuarter(HttpServletRequest request,
			HttpServletResponse response, String period) {
		try {
			String l = this.dateChooserService.GetQuarterData();
			SystemUtil.responseJSONString(JsonUtil.toJson(l), response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 获取类别参数
	@RequestMapping(params = "method=getstyle")
	public String getReportStyle(HttpServletRequest request,
			HttpServletResponse response, String period) {
		try {
			String styletext = request.getParameter("styletext") == null ? ""
					: request.getParameter("styletext");
			String l = this.dateChooserService.GetStyleData(styletext);
			SystemUtil.responseJSONString(JsonUtil.toJson(l), response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 获取类别参数
	@RequestMapping(params = "method=getctid")
	public String getCtId(HttpServletRequest request,
			HttpServletResponse response, String period) {
		try {
			String cttext = request.getParameter("cttext") == null ? ""
					: request.getParameter("cttext");
			String l = this.dateChooserService.GetCtData(cttext);
			SystemUtil.responseJSONString(JsonUtil.toJson(l), response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
