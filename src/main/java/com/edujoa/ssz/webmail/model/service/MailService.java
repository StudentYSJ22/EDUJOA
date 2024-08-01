package com.edujoa.ssz.webmail.model.service;

import java.util.List;
import java.util.Map;

import com.edujoa.ssz.webmail.model.dto.Mail;
import com.edujoa.ssz.webmail.model.dto.ReceivedMail;

public interface MailService {
	//수신 메일 전체 저장
	int insertReceivedMail(List<ReceivedMail> rcvMails);
	
	//보낸 메일 작성
	int createSendMail(Map<String,String>param);
	
	//수신메일 전체 조회
	List<ReceivedMail> selectReceivedMails();
	
	//메일 삭제 라벨
	int delete(List<Long> param);

	//메일 복구 라벨
	int restore(List<Long> param);
	
	ReceivedMail getSelectedMail(String emailId);
	
	List<ReceivedMail> getDeletedMail();
	
	int saveDraft(Map<String, String>param);
	
	List<Mail> getTempMail();
	
	List<Mail> getSentMail();
}
