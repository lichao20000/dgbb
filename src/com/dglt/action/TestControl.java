package com.dglt.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dglt.bb.pojo.DashIndiv;
import com.dglt.bb.service.DashIndivService;
import com.dglt.bb.service.TestService;
import com.dglt.comm.base.BaseAction;
import com.dglt.comm.util.SystemUtil;
import com.dglt.comm.util.gson.JsonUtil;

@Controller
public class TestControl extends BaseAction {

	@Resource(name = "testService")
	private TestService testService;
	@Resource(name = "dashIndivService")
	private DashIndivService dashIndivService;

	@RequestMapping("/test1.do")
	public String testJdbc(HttpServletRequest request,
			HttpServletResponse response) {
		// 测试获取jdbc连接
		testService.testJdbc();
		return "error";
	}

	@RequestMapping("/test2.do")
	public String getIndiv(HttpServletRequest request,
			HttpServletResponse response) {

		String userId = "0";
		try {
			DashIndiv indiv = dashIndivService.getDashIndivByUserId(userId);
			if (indiv == null) {
				//indiv = dashIndivService.insertDashIndiv(userId);
			}
			SystemUtil.responseJSONString(JsonUtil.toJson(indiv), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
