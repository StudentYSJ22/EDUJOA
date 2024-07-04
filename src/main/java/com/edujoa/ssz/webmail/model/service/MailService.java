package com.edujoa.ssz.webmail.model.service;

import java.util.List;
import java.util.Map;

import com.edujoa.ssz.webmail.model.DTO.Mail;

public interface MailService {
	//수신 메일 전체 조회
	List<Mail> getAllMails(Map<String,String>param);
	//메일 작성
	int createMail(Mail mail);
	//메일 삭제
	int deleteMail(String mailId);
}
