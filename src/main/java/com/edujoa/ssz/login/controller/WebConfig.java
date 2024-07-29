package com.edujoa.ssz.login.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component //bean등록하기
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//registry.addViewController("/").setViewName("index");
		registry.addViewController("/login").setViewName("login/login");
		registry.addViewController("/noticeboard").setViewName("ssz/noticeboard");
//		registry.addViewController("/chatting").setViewName("ssz/chatting");
		registry.addViewController("/sendmail").setViewName("ssz/sendmail");
		registry.addViewController("/mailbox").setViewName("ssz/mailbox");
		registry.addViewController("/findempid").setViewName("khj/findempid");
		registry.addViewController("/noticeboarddetail").setViewName("ssz/notiecboarddetail");
		registry.addViewController("/chatting").setViewName("ssz/chattingtest");
//		registry.addViewController("/main/home").setViewName("views/index");
//									url에 입력될 주소				jsp파일 위치
	}

}
