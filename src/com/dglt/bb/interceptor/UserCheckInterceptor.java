package com.dglt.bb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 用户登录、及相关应用要处理的拦截
 * 
 * @author Administrator
 * 
 */
public class UserCheckInterceptor extends HandlerInterceptorAdapter {
	
	//进行登录校验，没登录跳转，有登录信息，则登录
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception{
		//代码待更新
		String user = request.getParameter("user");
		if (user!=null && user.equals("1")){
			System.out.println("出错啦！！");
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);   
            return false;
		}
		return true;
	}

	// 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
