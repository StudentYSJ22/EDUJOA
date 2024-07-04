package com.edujoa.ssz.webmail.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.ssz.webmail.model.dto.Mail;



public interface MailDao {
	//수신 메일 전체 조회
	List<Mail> getAllMails(SqlSession session, Map<String,String>param);
	//메일 작성
	int createMail(SqlSession session, Mail mail);
	//메일 삭제
	int deleteMail(SqlSession session, String mailId);
}
