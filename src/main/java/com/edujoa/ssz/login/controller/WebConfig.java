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
		registry.addViewController("/mailbox/mailsend").setViewName("ssz/mailsend");
		registry.addViewController("/mailbox").setViewName("ssz/mailbox");
		registry.addViewController("/findempid").setViewName("khj/findempid");
		registry.addViewController("/noticeboard/detail").setViewName("ssz/notiecboarddetail");
		registry.addViewController("/noticeboard/boardwrite").setViewName("ssz/notiecboardwrite");
		registry.addViewController("/chatting").setViewName("ssz/chattingtest");
		registry.addViewController("/mailbox/maildetail").setViewName("ssz/maildetail");
		registry.addViewController("/mailbox/sentmaildetail").setViewName("ssz/sentmaildetail");
		registry.addViewController("/mailbox/sendtempmail").setViewName("ssz/sendtempmail");
		registry.addViewController("/mailbox/replymail").setViewName("ssz/replymail");
		registry.addViewController("/mailbox/deletebox").setViewName("ssz/deletebox");
		registry.addViewController("/mailbox/tempbox").setViewName("ssz/tempbox");
//		registry.addViewController("/main/home").setViewName("views/index");
//									url에 입력될 주소				jsp파일 위치
	}

}
