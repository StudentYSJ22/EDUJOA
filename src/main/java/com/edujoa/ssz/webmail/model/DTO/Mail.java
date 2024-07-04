package com.edujoa.ssz.webmail.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mail {
	private String mailId; 
	private String empId;
	private String empEmail;
	private String mailTitle;
	private String mailCarboncopy;
	private String mailContent;
	private String mailAttachment;
	private Signature signature;
	private MailAttachment attachment;
}
