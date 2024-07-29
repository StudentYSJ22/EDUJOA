package com.edujoa.ssz.webmail.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.edujoa.ssz.webmail.model.service.MailService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MailController {
	private final MailService service;
	
	//페이지 이동용 메소드
	@GetMapping("/mailbox/mailsend")
	public String mailSend() {
		return "/ssz/sendmail";
	}
	
	@PostMapping("/mailbox/sendmail")
	public String mailSendToDb(@RequestBody Map<String, String>param) {
		System.out.println("jsp에서 가로챈 이메일: "+param);
		//content에 자동으로 미리 설정해놓은 내용 들어가기 때문에 가로챈 내용에는 공백 있으니 trim할 것.
		String mailContent=param.get("message").trim();
		System.out.println("트림해서 가져온 메일내용 잘나오냐? "+mailContent);
		int result = service.createSendMail(param);
		if(result>0) {
			return "/ssz/sendmail";
		}else {
			return "/#";
		}
		
	}
}
