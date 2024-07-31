package com.edujoa.ssz.webmail.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.ssz.webmail.model.dto.ReceivedMail;



public interface MailDao {
	//수신 메일 전체 조회
	int insertReceivedMail(SqlSession session, List<ReceivedMail> rcvMails);
	//메일 작성
	int createSendMail(SqlSession session, Map<String, String>param);
	
	List<ReceivedMail> selectReceivedMails(SqlSession session);
	
	//메일 삭제
	int delete(SqlSession session, Map<String,String>param);
	
	ReceivedMail getSelectedMail(SqlSession session, String emailId);
}
