package com.edujoa.ssz.webmail.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.ssz.webmail.model.dto.Mail;

@Repository
public class MailDaoImpl implements MailDao{

	@Override
	public List<Mail> getAllMails(SqlSession session, Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createSendMail(SqlSession session, Map<String, String> param) {
		// TODO Auto-generated method stub
		return session.insert("mail.insertSendMail", param);
	}

	@Override
	public int deleteMail(SqlSession session, String mailId) {
		// TODO Auto-generated method stub
		return 0;
	}


	
}
