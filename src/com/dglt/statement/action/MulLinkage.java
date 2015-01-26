package com.dglt.statement.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dglt.base.util.SpringUtil;
import com.dglt.bb.pojo.TAppEnumValue;
import com.dglt.statement.service.AppEnumService;
import com.eos.system.utility.StringUtil;


@Controller
@RequestMapping(value = "/MulLinkage")
public final class MulLinkage {

	// 根据上级获得下级
	@RequestMapping(value = "/enumEration.do")
	public void enumEration(HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		AppEnumService appEnumService = (AppEnumService)SpringUtil.getBean("appEnumService");
		String dictId = request.getParameter("id");
		String valuestr = request.getParameter("value");
		String strScene = request.getParameter("scene");
		StringBuilder json = new StringBuilder("[");
		if (dictId != null && !"".equals(dictId) && valuestr != null && !"".equals(valuestr)) {

		    // 枚举�?
		    List<TAppEnumValue> appEnumValueList = null;
	        if (StringUtil.isEmpty(strScene)) {
	            appEnumValueList = appEnumService.getAppEnumValues(dictId);
	        } else {
	            String[] strs = strScene.split(",");
	            appEnumValueList = appEnumService.getAppEnumValues(dictId, strs);
	        }

			if (appEnumValueList == null) {
				appEnumValueList = new ArrayList<TAppEnumValue>();
			}
			TAppEnumValue appEnumValue = null;
			for (int i = 0; i < appEnumValueList.size(); i++) {
				appEnumValue = appEnumValueList.get(i);
				// �?��的父枚举�?
				String pid = appEnumValue.getParentEnumValue();
				if (pid == null) {
					pid = "";
				}
				boolean have = false;
				String[] pids = pid.split(",");
				for(String id:pids){
				    if(valuestr.equals(id)){
				        have = true;
				        break;
				    }
				}
				if ("all".equals(pid) || have) {
					json.append("{value:" + '"');
					json.append(appEnumValue.getEnumValue());
					json.append('"');
					json.append(",");
					json.append("text:" + '"');
					json.append(appEnumValue.getEnumValueMeaning() + '"');
					json.append("},");
				}
			}
			if(json.length() > 1){
				json.setCharAt(json.length() - 1, ']');
			} else {
				json.append("]");
			}
		} else {
			json.append("]");
		}

		response.getWriter().print(json.toString());
	}
	
	
	@RequestMapping(value = "/enumEration4All")
	public void enumEration4All(HttpServletResponse response, HttpServletRequest request) throws IOException {
		AppEnumService appEnumService = (AppEnumService)SpringUtil.getBean("appEnumService");
		String dictId = request.getParameter("id");
		String strScene = request.getParameter("scene");
		StringBuilder json = new StringBuilder("[");
		if (dictId != null && !"".equals(dictId) ) {

		    // 枚举�?
		    List<TAppEnumValue> appEnumValueList = null;
	        if (StringUtil.isEmpty(strScene)) {
	            appEnumValueList = appEnumService.getAppEnumValues(dictId);
	        } else {
	            String[] strs = strScene.split(",");
	            appEnumValueList = appEnumService.getAppEnumValues(dictId, strs);
	        }

			if (appEnumValueList == null) {
				appEnumValueList = new ArrayList<TAppEnumValue>();
			}
			TAppEnumValue appEnumValue = null;
			for (int i = 0; i < appEnumValueList.size(); i++) {
				appEnumValue = appEnumValueList.get(i);
					json.append("{value:" + '"');
					json.append(appEnumValue.getEnumValue());
					json.append('"');
					json.append(",");
					json.append("text:" + '"');
					json.append(appEnumValue.getEnumValueMeaning() + '"');
					json.append("},");
				
			}
			if(json.length() > 1){
				json.setCharAt(json.length() - 1, ']');
			} else {
				json.append("]");
			}
		} else {
			json.append("]");
		}

		response.getWriter().print(json.toString());
	}


}
