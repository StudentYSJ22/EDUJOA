package com.edujoa.khj.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edujoa.khj.main.dao.MailMapper;
import com.edujoa.ssz.webmail.model.dto.Mail;

@Service
public class MailMainService {
    @Autowired
    private MailMapper mailMapper;

    public Mail getMailSummary(int mailId) {
        return mailMapper.selectMailSummary(mailId);
    }
}
