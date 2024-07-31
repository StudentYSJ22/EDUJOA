package com.edujoa.ssz.webmail.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.edujoa.ssz.webmail.model.dao.MailDao;
import com.edujoa.ssz.webmail.model.dto.ReceivedMail;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{
	private final MailDao dao;
	private final SqlSession session;
	@Override
	public int insertReceivedMail(List<ReceivedMail> rcvMails) {
		System.out.println("dao들어가기전 service쪽 매개변수 :"+rcvMails);
		return dao.insertReceivedMail(session, rcvMails);
	}

	@Override
	public int createSendMail(Map<String, String> param) {
		return dao.createSendMail(session, param);
	}

	@Override
	public List<ReceivedMail> selectReceivedMails() {
		// TODO Auto-generated method stub
		return dao.selectReceivedMails(session);
	}

	@Override
	public int delete(Map<String, String> param) {
		return dao.delete(session, param);
	}

	@Override
	public ReceivedMail getSelectedMail(String emailId) {
		return dao.getSelectedMail(session, emailId);
	}

	

	



}
