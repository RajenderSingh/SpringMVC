package com.demo.interceptor.v1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SpringInterceptor extends HandlerInterceptorAdapter {
	
	private final Logger LOGGER = LoggerFactory.getLogger(SpringInterceptor.class);
	static int counter = 0;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {			
	
		boolean flag = true;
		counter++;
		
		try {
			if (counter % 2 == 0) {
				LOGGER.info("Login credentials validated successfully");
			}else {
				LOGGER.info("Invalid Login credentials");
				throw new Exception();
			}
			
		} catch (Exception e) {
			LOGGER.info("Exception "+e);
			flag = false;
			response.sendRedirect(request.getContextPath() + "/v1/login/");
		}

		return flag;
	}
	
} 