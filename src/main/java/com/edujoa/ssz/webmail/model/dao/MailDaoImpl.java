package com.edujoa.ssz.webmail.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.ssz.webmail.model.dto.Mail;
import com.edujoa.ssz.webmail.model.dto.ReceivedMail;

@Repository
public class MailDaoImpl implements MailDao{

	

	@Override
	public int insertReceivedMail(SqlSession session, List<ReceivedMail> rcvMails) {
		// TODO Auto-generated method stub
		System.out.println("dao매개변수: "+rcvMails);	
		return session.insert("receivedmail.mergeReceivedMails", rcvMails);
	}

	@Override
	public int createSendMail(SqlSession session, Map<String, String> param) {
		// TODO Auto-generated method stub
		return session.insert("mail.insertSendMail", param);
	}

	@Override
	public List<ReceivedMail> selectReceivedMails(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectList("receivedmail.selectReceivedMails", session);
	}



	
}
