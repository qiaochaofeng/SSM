package cn.itcast.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomeExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception e) {
		
		CustomException customException = null;
		//1. 若是自定义异常,则直接转换
		if(e instanceof CustomException){
			customException = (CustomException)e;
		}else{ //2.若不是,则重新定义一个系统自定义异常
			customException = new CustomException("系统异常");
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", customException.getExceptionMsg());
		mv.setViewName("error");
		return mv;
	}

}
