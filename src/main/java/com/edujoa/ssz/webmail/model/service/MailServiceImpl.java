package com.edujoa.ssz.webmail.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.edujoa.ssz.webmail.model.dao.MailDao;
import com.edujoa.ssz.webmail.model.dto.Mail;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{
	private final MailDao dao;
	private final SqlSession session;
	@Override
	public List<Mail> getAllMails(Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createSendMail(Map<String, String> param) {
		return dao.createSendMail(session, param);
	}

	@Override
	public int deleteMail(String mailId) {
		// TODO Auto-generated method stub
		return 0;
	}



}
