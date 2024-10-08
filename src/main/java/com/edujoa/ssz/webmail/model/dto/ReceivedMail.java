package com.edujoa.ssz.webmail.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceivedMail {
	private String rcvMailId;
	private String rcvMailSender;
	private List<String> rcvMailReceiver;
	private List<String> rcvMailCc;
	private List<String> rcvMailBcc;
	private String rcvMailTitle;
	private String rcvMailContent;
	private String rcvMailDate;
	private String rcvMailFileName;
	private String rcvMailContentType;
	private String rcvMailFileUrl;
	private String rcvMailType;
	private String rcvMailRead;
}
