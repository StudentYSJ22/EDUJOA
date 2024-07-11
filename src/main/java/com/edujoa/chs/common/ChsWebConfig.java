package com.edujoa.chs.common;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component //bean등록하기
public class ChsWebConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/employee/insert").setViewName("chs/personnel/insert_emp_personnel");
	}
	
	
}