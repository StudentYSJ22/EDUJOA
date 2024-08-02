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
		System.out.println("dao매개변수: "+rcvMails.get(0));	
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
		return session.selectList("receivedmail.selectReceivedMails");
	}

	@Override
	public int delete(SqlSession session, List<Long> param) {
		return session.update("receivedmail.updatelabeldelete", param);
	}

	@Override
	public ReceivedMail getSelectedMail(SqlSession session, String emailId) {
		ReceivedMail mail=session.selectOne("receivedmail.getSelectedMail", emailId);
		if(mail!=null) {
			session.update("receivedmail.updateMailRead", emailId);
		}
		return session.selectOne("receivedmail.getSelectedMail", emailId);
	}

	@Override
	public List<ReceivedMail> getDeletedMail(SqlSession session) {
		return session.selectList("receivedmail.getDeletedMail");
	}

	@Override
	public int restore(SqlSession session, List<Long> param) {
		// TODO Auto-generated method stub
		return session.update("receivedmail.updatelabelinbox", param);
	}

	@Override
	public int saveDraft(SqlSession session, Map<String, String> param) {
		return session.insert("mail.insertTempMail", param);
	}

	@Override
	public List<Mail> getTempMail(SqlSession session) {
		return session.selectList("mail.getTempMail");
	}

	@Override
	public List<Mail> getSentMail(SqlSession session) {
		return session.selectList("mail.getSentMail");
	}

	@Override
	public Mail getSelectedSentMail(SqlSession session, String emailId) {
		return session.selectOne("mail.getSelectedSentMail", emailId);
	}
	
}
