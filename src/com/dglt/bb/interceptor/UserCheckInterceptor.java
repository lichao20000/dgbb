package com.dglt.bb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * �û���¼�������Ӧ��Ҫ���������
 * 
 * @author Administrator
 * 
 */
public class UserCheckInterceptor extends HandlerInterceptorAdapter {
	
	//���е�¼У�飬û��¼��ת���е�¼��Ϣ�����¼
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception{
		//���������
		String user = request.getParameter("user");
		if (user!=null && user.equals("1")){
			System.out.println("����������");
			request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);   
            return false;
		}
		return true;
	}

	// ��ҵ��������������ִ����ɺ�,������ͼ֮ǰִ�еĶ���
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
