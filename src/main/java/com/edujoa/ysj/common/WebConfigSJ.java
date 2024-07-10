package com.edujoa.ysj.common;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component //bean등록하기
public class WebConfigSJ implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/schedule/schedule.do").setViewName("ysj/schedule");
	
	}

	
	
}
