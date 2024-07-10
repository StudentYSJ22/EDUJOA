package com.edujoa.khj.main.dao;

import org.springframework.stereotype.Repository;

import com.edujoa.ssz.webmail.model.dto.Mail;

@Repository
/* @MapperScan */
public interface MailMapper {
	Mail selectMailSummary(int mailId);
}
