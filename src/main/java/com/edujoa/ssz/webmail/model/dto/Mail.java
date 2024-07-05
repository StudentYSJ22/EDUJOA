package com.edujoa.ssz.webmail.model.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//메일 보낼 때
public class Mail {
	private String senderEmail;//발신자
	private List<String> receiverEmails;//수신자
	private List<String> mailCarboncopies;//참조
	private String mailTitle;
	private String mailContent;
	private Date mailDate;
	private List<MailAttachment> attachments;
}
