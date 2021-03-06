package cn.itcast.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.po.User;

public class HandlerIntercepter1 implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		//登入验证
		if(request.getRequestURI().contains("/login.action") || request.getRequestURI().contains("/submit.action")){
			return true;
		}
		User user = (User) request.getSession().getAttribute("user");
		if(user == null){
			response.sendRedirect("/WEB-INF/jsp/login.jsp");
			return false;
		}else{
			return true;
		}
		
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}


}
